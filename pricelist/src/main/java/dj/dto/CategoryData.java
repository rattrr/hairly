package dj.dto;

import dj.model.HairdressingService;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CategoryData {
    private String name;
}
