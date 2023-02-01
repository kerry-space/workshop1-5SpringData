package lexicon.workshop15springdata.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data //ToString, EqualAndHashCode, Getter & Setter + RequireArgsConstructor.

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private int maxLoanDays;


}
