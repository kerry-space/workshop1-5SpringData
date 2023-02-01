package lexicon.workshop15springdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    private LocalDate loanDate;

    private LocalDate dueDate;

    private boolean returned;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private AppUser borrower;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Book book;

    public BookLoan(LocalDate loanDate, AppUser borrower, Book book) {
        this.dueDate = loanDate.plusDays(book.getMaxLoanDays());
        this.loanDate = loanDate;
        this.borrower = borrower;
        this.book = book;
        setBorrower(borrower);
    }

    public void setBorrower(AppUser borrower) {
        this.borrower = borrower;
        this.returned = (borrower == null);
    }
}
