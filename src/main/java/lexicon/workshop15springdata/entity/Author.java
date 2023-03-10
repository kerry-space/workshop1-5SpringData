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
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int authorId;
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "books_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<Book> writtenBooks = new ArrayList<>();



    //helper methods
    public void addAuthor(Book book){
        if (writtenBooks.contains(book)){
            throw new DataDuplicateException("Data Duplicate Exception");
        }
        writtenBooks.add(book);
    }

    public void removeAuthor(Book book){
        if (!writtenBooks.contains(book)){
            throw new DataDuplicateException("Data Duplicate Exception");
        }
        writtenBooks.remove(book);
    }


}
