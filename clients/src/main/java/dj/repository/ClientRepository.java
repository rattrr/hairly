package dj.repository;

import dj.model.Client;
import dj.model.Gender;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {
    List<Client> getAllByFirstNameEqualsAndLastNameEqualsAndPhoneNumberEqualsAndGenderEquals(String firstName, String lastName, String phoneNumber, Gender gender);
}
