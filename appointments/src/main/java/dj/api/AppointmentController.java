package dj.api;

import dj.model.Appointment;
import dj.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Appointment> getAllAppointments(){
        return appointmentService.getAll();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params="clientId")
    public List<Appointment> getAllAppointmentsOfClient(@RequestParam long clientId){
        return appointmentService.getAllByClientId(clientId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public Appointment addAppointment(@RequestBody Appointment appointment){
        return appointmentService.add(appointment);
    }

    @DeleteMapping()
    public ResponseEntity deleteAppointment(@RequestParam long id){
        appointmentService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
