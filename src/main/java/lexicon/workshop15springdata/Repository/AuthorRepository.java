package lexicon.workshop15springdata.Repository;

import lexicon.workshop15springdata.entity.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository  extends CrudRepository<Author, Integer> {
    List<Author> findAllByWrittenBooksId(int bookId);
}
