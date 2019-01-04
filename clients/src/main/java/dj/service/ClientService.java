package dj.service;

import dj.model.Client;
import dj.repository.ClientRepository;
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

    public Optional<Client> getById(long id){
        return clientRepository.findById(id);
    }

    public Optional<Client> add(Client client){
        if(clientNotExists(client)){
            return Optional.of(clientRepository.save(client));
        }
        return Optional.empty();
    }

    public Optional<Client> update(Client client){
        if(existsClientWithId(client.getId())){
            return Optional.of(clientRepository.save(client));
        }
        return Optional.empty();
    }

    public void delete(long id){
        if(existsClientWithId(id)) {
            clientRepository.deleteById(id);
        }
    }

    private boolean clientNotExists(Client client){
        return clientRepository.getAllByFirstNameEqualsAndLastNameEqualsAndPhoneNumberEqualsAndGenderEquals(
                client.getFirstName(), client.getLastName(), client.getPhoneNumber(), client.getGender()).isEmpty();
    }

    private boolean existsClientWithId(long id){
        return getById(id).isPresent();
    }

}
