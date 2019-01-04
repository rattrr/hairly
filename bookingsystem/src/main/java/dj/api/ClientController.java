package dj.api;

import dj.dto.ClientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final RestTemplate restTemplate;
    private final String url = "http://localhost:8083/clients";

    @Autowired
    public ClientController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List getClients(){
        return restTemplate.getForObject(url, List.class);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params="id")
    public ResponseEntity<ClientData> getClientData(@RequestParam long id){
        return restTemplate.getForEntity(url + "?id=" + id, ClientData.class);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientData> addClient(@RequestBody ClientData client){
        return restTemplate.postForEntity(url, client, ClientData.class);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ClientData> updateClient(@RequestBody ClientData client){
        return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(client), ClientData.class);
    }

    @DeleteMapping
    public ResponseEntity deleteClient(@RequestParam long id){
        return restTemplate.exchange(url + "?id=" + id, HttpMethod.DELETE, ResponseEntity.EMPTY, ResponseEntity.class);
    }

}
