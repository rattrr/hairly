package hairly.repository;

import hairly.model.Client;
import hairly.model.Gender;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> getAllByFirstNameEqualsAndLastNameEqualsAndPhoneNumberEqualsAndGenderEquals(String firstName, String lastName, String phoneNumber, Gender gender);
}
