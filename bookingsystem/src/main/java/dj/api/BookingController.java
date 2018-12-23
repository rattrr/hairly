package dj.api;

import dj.dto.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class BookingController {

    private final RestTemplate restTemplate;

    @Autowired
    public BookingController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(value = "/clients", produces = APPLICATION_JSON_VALUE)
    public String getClients(){
        return restTemplate.getForObject("http://localhost:8083/clients", String.class);
    }

    @PostMapping(path = "/clients", consumes = APPLICATION_JSON_VALUE)
    public void addClient(@RequestBody Client client){
        restTemplate.postForObject("http://localhost:8083/clients", client, String.class);
    }

    @PutMapping(path = "/clients", consumes = APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody Client client){
        restTemplate.put("http://localhost:8083/clients", client);
    }

    @DeleteMapping(path="/clients")
    public void deleteClient(@RequestParam long id){
        restTemplate.delete("http://localhost:8083/clients?id=" + id);
    }

    private boolean clientExist(long clientId){
        return restTemplate.getForObject("http://localhost:8083/clients/" + clientId, String.class) != null;
    }
}
