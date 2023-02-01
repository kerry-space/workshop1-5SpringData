package lexicon.workshop15springdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.xml.soap.Detail;
import java.time.LocalDate;

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
}
