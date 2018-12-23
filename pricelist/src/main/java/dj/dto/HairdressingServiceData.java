package dj.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dj.model.Category;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
public class HairdressingServiceData {
    private String name;
    private int price;
    private int durationInMinutes;
    private String category;

    public HairdressingServiceData(String name, int price, int duration, String category) {
        this.name = name;
        this.price = price;
        this.durationInMinutes = duration;
        this.category = category;
    }
}
