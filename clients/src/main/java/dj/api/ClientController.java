package dj.api;

import dj.model.Client;
import dj.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Client getClientById(@RequestParam long id){
        return clientService.getById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Client addClient(@RequestBody Client client){
        return clientService.add(client);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public Client updateClient(@RequestBody Client client){
        return clientService.update(client);
    }

    @DeleteMapping()
    public ResponseEntity deleteClient(@RequestParam long id){
        clientService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
