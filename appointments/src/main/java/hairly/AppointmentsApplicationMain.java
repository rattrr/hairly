package hairly;

import hairly.model.Appointment;
import hairly.repository.AppointmentRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class AppointmentsApplicationMain
{
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentsApplicationMain(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    public static void main(String[] args )
    {
        SpringApplication.run(AppointmentsApplicationMain.class, args);
    }

    @Bean
    InitializingBean fillDatabase(){
        return () -> {
            appointmentRepository.save(new Appointment(0, 0,
                    LocalDateTime.of(2018, 12, 1, 10, 0),
                    LocalDateTime.of(2018, 12, 1, 10, 30), "short hair"));

            appointmentRepository.save(new Appointment(2, 2,
                    LocalDateTime.of(2018, 12, 1, 11, 0),
                    LocalDateTime.of(2018, 12, 1, 13, 0), ""));

            appointmentRepository.save(new Appointment(4, 6,
                    LocalDateTime.of(2018, 12, 2, 17, 0),
                    LocalDateTime.of(2018, 12, 2, 17, 30), ""));
        };
    }
}
