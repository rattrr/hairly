package hairly.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long clientId;
    private long serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String additionalInfo;

    public Appointment(long clientId, long serviceId, LocalDateTime startTime, LocalDateTime endTime, String additionalInfo) {
        this.clientId = clientId;
        this.serviceId = serviceId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.additionalInfo = additionalInfo;
    }
}
