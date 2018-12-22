package hairly.api;

import hairly.model.HairdressingService;
import hairly.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/pricelist")
public class PriceListController {
    private final PriceListService priceListService;

    @Autowired
    public PriceListController(PriceListService priceListService) {
        this.priceListService = priceListService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<HairdressingService> getClients(){
        return priceListService.getAll();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, path="/{id}")
    public HairdressingService getClientById(@PathVariable long id){
        return priceListService.getById(id);
    }

}
