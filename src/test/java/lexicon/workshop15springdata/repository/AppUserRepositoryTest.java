package lexicon.workshop15springdata.repository;

import lexicon.workshop15springdata.Repository.AppUserRepository;
import lexicon.workshop15springdata.entity.AppUser;
import lexicon.workshop15springdata.entity.Details;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest

public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository testObject;

    AppUser createdAppUser;

    @BeforeEach
    public void setup(){
        Details details = new Details("kerry95@gmail.com", "kerry", LocalDate.parse("1995-10-30"));
        AppUser appUser = new AppUser("kerryspace","lovelove",details);
       createdAppUser = testObject.save(appUser);
        assertNotNull(createdAppUser);
    }



    @Test
    public void test_findById(){
       Optional<AppUser> appUserOptional = testObject.findById(createdAppUser.getAppUserId());
       assertTrue(appUserOptional.isPresent());
       AppUser actual = appUserOptional.get();
       AppUser expected = createdAppUser;
       assertEquals(expected,actual);
    }

    @Test
    public void test_findByUsername(){
        Optional<AppUser> appUserOptional = testObject.findByUsername(createdAppUser.getUsername());
        assertTrue(appUserOptional.isPresent());
        AppUser actualData = appUserOptional.get();
        AppUser expectedData = createdAppUser;
        assertEquals(expectedData, actualData);

    }

}
