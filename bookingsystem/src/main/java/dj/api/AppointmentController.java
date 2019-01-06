package dj.api;

import dj.dto.AppointmentData;
import dj.dto.ServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {
    private final RestTemplate restTemplate;
    private final String url = "http://localhost:8089/appointments";

    @Autowired
    public AppointmentController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List getAllAppointments(){
        return restTemplate.getForObject(url, List.class);
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params="clientId")
    public List getAllAppointmentsOfClient(@RequestParam long clientId){
        return restTemplate.getForObject(url + "?clientId=" + clientId, List.class);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentData> addAppointment(@RequestBody AppointmentData appointmentData){
        if(clientNotExist(appointmentData.getClientId())){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if(appointmentData.getEndTime() == null){
            LocalDateTime endTime = appointmentData.getStartTime().plusMinutes(getDefaultDurationOfService(appointmentData.getServiceId()));
            appointmentData.setEndTime(endTime);
        }
        return restTemplate.postForEntity(url, appointmentData, AppointmentData.class);
    }

    @DeleteMapping()
    public ResponseEntity deleteAppointment(@RequestParam long id){
        restTemplate.delete(url + "?id=" + id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    private boolean clientNotExist(long clientId){
        return restTemplate.getForObject("http://localhost:8083/clients?id=" + clientId, String.class) == null;
    }

    private long getDefaultDurationOfService(long serviceId){
        return restTemplate.getForObject("http://localhost:8086/pricelist?id=" + serviceId, ServiceData.class).getDurationInMinutes();
    }
}
