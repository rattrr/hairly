package dj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServiceData {
    private long id;
    private String name;
    private int price;
    private int durationInMinutes;
    private Category category;

    public ServiceData(String name, int price, int duration) {
        this.name = name;
        this.price = price;
        this.durationInMinutes = duration;
    }
}
