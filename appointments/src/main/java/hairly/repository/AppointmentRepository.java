package hairly.repository;

import hairly.model.Appointment;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> getAllByClientId(long id);
    List<Appointment> getAppointmentsByClientIdEquals(long clientId);
    List<Appointment> getAppointmentsByStartTimeLessThanEqualAndEndTimeGreaterThanEqual(
            LocalDateTime appointmentEndTime, LocalDateTime appointmentStartTime);
}
