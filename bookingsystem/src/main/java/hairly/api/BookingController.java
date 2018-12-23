package hairly.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class BookingController {

    private final RestTemplate restTemplate;

    @Autowired
    public BookingController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping("/clients")
    public String getClients(){
        return restTemplate.getForObject("http://localhost:8083/clients", String.class);
    }

    private boolean clientExist(long clientId){
        return restTemplate.getForObject("http://localhost:8083/clients/" + clientId, String.class) != null;
    }
}
