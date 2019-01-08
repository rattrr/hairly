package dj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HairdressingServiceData {
    private String name;
    private int price;
    private int durationInMinutes;
    private String categoryName;

    public HairdressingServiceData(String name, int price, int duration, String categoryName) {
        this.name = name;
        this.price = price;
        this.durationInMinutes = duration;
        this.categoryName = categoryName;
    }
}
