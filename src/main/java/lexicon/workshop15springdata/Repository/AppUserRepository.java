package lexicon.workshop15springdata.Repository;

import lexicon.workshop15springdata.entity.AppUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    //custom method
    //select * from AppUser where username = 1
    //follow spring data framework name convention, don't need to implement it spring data framework will take care of it
    //or
    //Query
    Optional<AppUser> findByUsername(@Param("un") String username);
    @Query("select a from AppUser a where a.username = :un")
    Optional<AppUser> selectByUsername(@Param("un") String username);


    //select * from app_user where reg_data between 1? and 2?
   List<AppUser> findAllByRegDateBetween(LocalDate from, LocalDate to);


   @Query("select a from AppUser a where a.regDate >= :from and a.regDate <= :to")
   List<AppUser> selectByRegistrationDate(@Param("from") LocalDate from, @Param("to") LocalDate to);


   //modifying database, update/delete, select is ready only
   @Modifying
   @Query("update AppUser  a set a.password = :pwd where a.username = :un")
   void resetPassword(@Param("pwd") String username, @Param("un") String password);

}
