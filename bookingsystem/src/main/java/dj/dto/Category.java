package dj.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class Category {
    private long id;
    private String name;
    List<ServiceData> services = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

    public void addService(ServiceData service){
        this.services.add(service);
    }
}
