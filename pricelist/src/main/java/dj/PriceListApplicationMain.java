package dj;

import dj.model.Category;
import dj.dto.HairdressingServiceData;
import dj.repository.CategoryRepository;
import dj.service.PriceListService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PriceListApplicationMain
{
    private final PriceListService priceListService;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PriceListApplicationMain(PriceListService priceListService, CategoryRepository categoryRepository) {
        this.priceListService = priceListService;
        this.categoryRepository = categoryRepository;
    }

    public static void main(String[] args )
    {
        SpringApplication.run(PriceListApplicationMain.class);
    }

    @Bean
    InitializingBean fillDatabase(){
        return () -> {
            categoryRepository.save(new Category("WOMEN"));
            categoryRepository.save(new Category("MEN"));
            priceListService.add(new HairdressingServiceData("wash, styling", 55, 40, "WOMEN"));
            priceListService.add(new HairdressingServiceData("wash, haircut, styling", 85, 60, "WOMEN"));
            priceListService.add(new HairdressingServiceData("wash, styling, color", 190, 180, "WOMEN"));
            priceListService.add(new HairdressingServiceData("wash, haircut, styling, color", 220, 240, "WOMEN"));
            priceListService.add(new HairdressingServiceData("decolouring", 115, 90, "WOMEN"));
            priceListService.add(new HairdressingServiceData("updo", 135, 60, "WOMEN"));
            priceListService.add(new HairdressingServiceData("wash, styling", 40, 30, "MEN"));
            priceListService.add(new HairdressingServiceData("wash, haircut, styling", 65, 60, "MEN"));
        };
    }
}
