package hairly.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class HairdressingService {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private int price;
    private int durationInMinutes;
    private Category category;

    public HairdressingService(String name, int price, int duration, Category category) {
        this.name = name;
        this.price = price;
        this.durationInMinutes = duration;
        this.category = category;
    }
}
