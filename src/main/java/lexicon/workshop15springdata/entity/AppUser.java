package lexicon.workshop15springdata.entity;

import lexicon.workshop15springdata.eception.DataDuplicateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.xml.soap.Detail;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//to generate Boilerplate Code refer to blocks with fixed pattern, Setter&Getter, ToString,EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int AppUserId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    //same as setting LocalDate.now() in constructor
    @CreationTimestamp
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "details_id")
    private Details details;

    @OneToMany(mappedBy = "borrower",cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<BookLoan> bookLoans = new ArrayList<>();

    public AppUser(String username, String password, LocalDate regDate) {
        this.username = username;
        this.password = password;
        this.regDate  = regDate;

    }

    public AppUser(String username, String password, Details details) {
        this.username = username;
        this.password = password;
        this.details = details;
    }


    //helper methods
    public void  addBookLoan(BookLoan bookLoan){
       if(bookLoans.contains(bookLoan)){
           throw new DataDuplicateException("Data Duplicate Exception");
       }
       bookLoans.add(bookLoan);
       bookLoan.setBorrower(this);

    }

    public void removeBookLoan(BookLoan bookLoan) {
        if (!bookLoans.contains(bookLoan)){
            throw new DataDuplicateException("Data Duplicate Exception");
        }
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);

    }
}
