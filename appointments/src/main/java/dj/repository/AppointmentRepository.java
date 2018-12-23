package dj.repository;

import dj.model.Appointment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> getAllByClientId(long id);
    List<Appointment> getAppointmentsByClientIdEquals(long clientId);
    List<Appointment> getAppointmentsByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            LocalDateTime appointmentEndTime, LocalDateTime appointmentStartTime);
}
