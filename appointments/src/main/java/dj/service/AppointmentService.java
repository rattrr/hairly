package dj.service;

import dj.model.Appointment;
import dj.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    public Optional<Appointment> add(Appointment appointment){
        if(timeperiodIsNotOverlaping(appointment) && timeperiodIsValid(appointment)){
            return Optional.of(appointmentRepository.save(appointment));
        }
        return Optional.empty();
    }

    public void delete(long id){
        if(appointmentExistById(id)){
            appointmentRepository.deleteById(id);
        }
    }

    private boolean timeperiodIsNotOverlaping(Appointment appointment){
        return appointmentRepository.getAppointmentsByStartTimeLessThanAndEndTimeGreaterThan(
                appointment.getEndTime(), appointment.getStartTime()).isEmpty();
    }

    private boolean appointmentExistById(long id){
        return appointmentRepository.findById(id).isPresent();
    }

    private boolean timeperiodIsValid(Appointment appointment){
        return appointment.getStartTime().isBefore(appointment.getEndTime());
    }
}
