package dj.api;

import dj.dto.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    private final RestTemplate restTemplate;
    private final String url = "http://localhost:8086/pricelist";

    @Autowired
    public ServiceController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List getService(){
        return restTemplate.getForObject(url, List.class);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params = "id")
    public ResponseEntity<ServiceData> getServiceById(@RequestParam long id){
        return restTemplate.getForEntity(url + "?id=" + id, ServiceData.class);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceData> addService(@RequestBody ServiceData serviceData){
        return restTemplate.postForEntity(url, serviceData, ServiceData.class);
    }
}
