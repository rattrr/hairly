package dj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Category {
    private long id;
    private String name;

    public Category(String name) {
        this.name = name;
    }

}
