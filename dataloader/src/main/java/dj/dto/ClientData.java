package dj.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientData {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Gender gender;
}
