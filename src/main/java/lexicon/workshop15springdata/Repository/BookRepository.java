package lexicon.workshop15springdata.Repository;

import lexicon.workshop15springdata.entity.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {

   List<Book> findAllByIsbn(String isbn);

   List<Book> findAllByTitleContains(String title);

    List<Book> findAllByTitleContainsIgnoreCase(String title);


}
