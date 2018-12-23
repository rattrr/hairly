package dj.api;

import dj.dto.ClientData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

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
    public String getClients(){
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params="id")
    public ClientData getClientData(@RequestParam long id){
        return restTemplate.getForObject(url + "?id=" + id, ClientData.class);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ClientData addClient(@RequestBody ClientData client){
        return restTemplate.postForObject(url, client, ClientData.class);
    }

    @PutMapping(consumes = APPLICATION_JSON_VALUE)
    public void updateClient(@RequestBody ClientData client){
        restTemplate.put(url, client);
    }

    @DeleteMapping
    public void deleteClient(@RequestParam long id){
        restTemplate.delete("http://localhost:8083/clients?id=" + id);
    }

    private boolean clientExist(long clientId){
        return restTemplate.getForObject("http://localhost:8083/clients/" + clientId, String.class) != null;
    }
}
