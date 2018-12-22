package hairly;

import hairly.model.Client;
import hairly.repository.ClientRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static hairly.model.Gender.FEMALE;
import static hairly.model.Gender.MALE;

@SpringBootApplication
public class ClientsApplicationMain
{
    private ClientRepository clientRepository;

    @Autowired
    ClientsApplicationMain(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    public static void main( String[] args )
    {
        SpringApplication.run(ClientsApplicationMain.class, args);
    }

    @Bean
    InitializingBean fillDatabase(){
        return () -> {
          clientRepository.save(new Client("Abigail", "Lambert", "782843956", FEMALE));
          clientRepository.save(new Client("Millie", "Richards", "676127585", FEMALE));
          clientRepository.save(new Client("Louise", "Barber", "532574323", FEMALE));
          clientRepository.save(new Client("Hayden", "Ryan", "728495047", MALE));
          clientRepository.save(new Client("Evan", "Goodwin", "672249106", MALE));
          clientRepository.save(new Client("Gabriel", "Patterson", "694755285", MALE));
        };
    }
}
