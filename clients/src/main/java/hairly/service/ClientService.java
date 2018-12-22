package hairly.service;

import hairly.model.Client;
import hairly.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public List<Client> getAll(){
        Iterable<Client> clientIterable = clientRepository.findAll();
        return StreamSupport.stream(clientIterable.spliterator(), false).collect(Collectors.toList());
    }

    public Client getById(long id){
        Optional<Client> client = clientRepository.findById(id);
        return client.orElse(null);
    }

    public void add(Client client){
        if(clientNotExists(client)){
            clientRepository.save(client);
        }
    }

    public void update(Client client){
        if(existsClientWithId(client.getId())){
            clientRepository.save(client);
        }
    }

    public void delete(long id){
        clientRepository.deleteById(id);
    }

    private boolean clientNotExists(Client client){
        return clientRepository.getAllByFirstNameEqualsAndLastNameEqualsAndPhoneNumberEqualsAndGenderEquals(
                client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getGender()).isEmpty();
    }

    private boolean existsClientWithId(long id){
        return getById(id) != null;
    }

}
