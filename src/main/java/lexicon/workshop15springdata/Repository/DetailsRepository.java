package lexicon.workshop15springdata.Repository;

import lexicon.workshop15springdata.entity.Details;
import org.springframework.data.repository.CrudRepository;

import javax.xml.soap.Detail;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Optional<Details> findByEmailIgnoreCase(String email);

    List<Details> findAllByNameContains(String name);

    List<Details> findAllByNameIgnoreCaseAndBirthDate(String name, LocalDate birthDate);
}
