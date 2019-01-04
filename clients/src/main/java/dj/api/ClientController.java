package dj.api;

import dj.model.Client;
import dj.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Client> getClients(){
        return clientService.getAll();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params="id")
    public ResponseEntity<Client> getClientById(@RequestParam long id){
        Optional<Client> clientOptional = clientService.getById(id);
        return clientOptional
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> addClient(@RequestBody Client clientData){
        Optional<Client> clientOptional = clientService.add(clientData);
        return clientOptional
                .map(client -> new ResponseEntity<>(client, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(@RequestBody Client clientData){
        Optional <Client> clientOptional=  clientService.update(clientData);
        return clientOptional
                .map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @DeleteMapping()
    public ResponseEntity deleteClient(@RequestParam long id){
        clientService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
