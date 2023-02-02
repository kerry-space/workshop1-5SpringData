package lexicon.workshop15springdata.entity;

import lexicon.workshop15springdata.eception.DataDuplicateException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();


    //helper methods
    public void addAuthor(Author author){
        if (authors.contains(author)){
            throw new DataDuplicateException("Data Duplicate Exception");
        }
        authors.add(author);
    }

    public void removeAuthor(Author author){
        if (!authors.contains(author)){
            throw new DataDuplicateException("Data Duplicate Exception");
        }
        authors.remove(author);
    }




}
