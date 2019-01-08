package dj;

import dj.dto.AppointmentData;
import dj.dto.ClientData;
import dj.dto.HairdressingServiceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

import static dj.dto.Gender.FEMALE;
import static dj.dto.Gender.MALE;

@Component
public class DataLoader {

    private final RestTemplate restTemplate;

    @Autowired
    public DataLoader(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
        loadClientsData();
        loadPriceListData();
        loadAppointmentsData();
    }

    private void loadClientsData(){
        final String clientsURL = "http://localhost:8083/clients";
        restTemplate.postForObject(clientsURL, new ClientData("Abigail", "Lambert", "782843956", FEMALE), String.class);
        restTemplate.postForObject(clientsURL, new ClientData("Millie", "Richards", "676127585", FEMALE), String.class);
        restTemplate.postForObject(clientsURL, new ClientData("Louise", "Barber", "532574323", FEMALE), String.class);
        restTemplate.postForObject(clientsURL, new ClientData("Hayden", "Ryan", "728495047", MALE), String.class);
        restTemplate.postForObject(clientsURL, new ClientData("Evan", "Goodwin", "672249106", MALE), String.class);
        restTemplate.postForObject(clientsURL, new ClientData("Gabriel", "Patterson", "694755285", MALE), String.class);
    }

    private void loadPriceListData(){
        final String servicesURL = "http://localhost:8086/pricelist";
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("wash, styling", 55, 40, "WOMEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("wash, haircut, styling", 85, 60, "WOMEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("wash, styling, color", 190, 180, "WOMEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("wash, haircut, styling, color", 220, 240, "WOMEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("decolouring", 115, 90, "WOMEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("updo", 135, 60, "WOMEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("wash, styling", 40, 30, "MEN"), String.class);
        restTemplate.postForObject(servicesURL, new HairdressingServiceData("wash, haircut, styling", 65, 60, "MEN"), String.class);
    }

    private void loadAppointmentsData(){
        final String appointmentsURL = "http://localhost:8089/appointments";
        restTemplate.postForObject(appointmentsURL, new AppointmentData(0, 0,
                LocalDateTime.of(2018, 12, 1, 10, 0),
                LocalDateTime.of(2018, 12, 1, 10, 30), "short hair"), String.class);
        restTemplate.postForObject(appointmentsURL, new AppointmentData(2, 2,
                LocalDateTime.of(2018, 12, 1, 11, 0),
                LocalDateTime.of(2018, 12, 1, 13, 0), ""), String.class);
        restTemplate.postForObject(appointmentsURL, new AppointmentData(4, 6,
                LocalDateTime.of(2018, 12, 2, 17, 0),
                LocalDateTime.of(2018, 12, 2, 17, 30), ""), String.class);
    }
}
