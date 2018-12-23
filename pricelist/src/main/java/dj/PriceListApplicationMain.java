package dj;

import dj.model.HairdressingService;
import dj.repository.PriceListRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static dj.model.Category.*;

@SpringBootApplication
public class PriceListApplicationMain
{
    private PriceListRepository priceListRepository;

    @Autowired
    public PriceListApplicationMain(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public static void main(String[] args )
    {
        SpringApplication.run(PriceListApplicationMain.class);
    }

    @Bean
    InitializingBean fillDatabase(){
        return () -> {
            priceListRepository.save(new HairdressingService("wash, styling", 55, 40, FEMALE));
            priceListRepository.save(new HairdressingService("wash, haircut, styling", 85, 60, FEMALE));
            priceListRepository.save(new HairdressingService("wash, styling, color", 190, 180, FEMALE));
            priceListRepository.save(new HairdressingService("wash, haircut, styling, color", 220, 240, FEMALE));
            priceListRepository.save(new HairdressingService("decolouring", 115, 90, FEMALE));
            priceListRepository.save(new HairdressingService("updo", 135, 60, FEMALE));
            priceListRepository.save(new HairdressingService("wash, styling", 40, 30, MALE));
            priceListRepository.save(new HairdressingService("wash, haircut, styling", 65, 60, MALE));
        };
    }
}
