package dj.api;

import dj.dto.CategoryData;
import dj.dto.HairdressingServiceData;
import dj.model.Category;
import dj.model.HairdressingService;
import dj.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public List<HairdressingService> getService(){
        return priceListService.getAll();
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE, params = "id")
    public HairdressingService getServiceById(@RequestParam long id){
        return priceListService.getById(id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public HairdressingService addService(@RequestBody HairdressingServiceData serviceData){
        return priceListService.add(serviceData);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, path="/new_category")
    public Category addCategory(@RequestBody CategoryData categoryData){
        return priceListService.addCategory(categoryData);
    }

}
