package dj.service;

import dj.model.Appointment;
import dj.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public List<Appointment> getAll(){
        Iterable<Appointment> appointmentIterable = appointmentRepository.findAll();
        return StreamSupport.stream(appointmentIterable.spliterator(), false).collect(Collectors.toList());
    }

    public List<Appointment> getAllByClientId(Long clientId){
        return appointmentRepository.getAppointmentsByClientIdEquals(clientId);
    }

    public Appointment add(Appointment appointment){
        if(timeperiodIsNotOverlaping(appointment) && timeperiodIsValid(appointment)){
            return appointmentRepository.save(appointment);
        }
        else return new Appointment();
    }

    public void delete(long id){
        if(appointmentExistById(id)){
            appointmentRepository.deleteById(id);
        }
    }

    private boolean timeperiodIsNotOverlaping(Appointment appointment){
        return appointmentRepository.getAppointmentsByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
                appointment.getEndTime(), appointment.getStartTime()).isEmpty();
    }

    private boolean appointmentExistById(long id){
        return appointmentRepository.findById(id).isPresent();
    }

    private boolean timeperiodIsValid(Appointment appointment){
        return appointment.getStartTime().isBefore(appointment.getEndTime());
    }
}
