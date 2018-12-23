package dj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class AppointmentData {

    private long id;
    private long clientId;
    private long serviceId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String additionalInfo;

}
