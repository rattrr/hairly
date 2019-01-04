package dj.api;

import dj.dto.CategoryData;
import dj.dto.HairdressingServiceData;
import dj.model.Category;
import dj.model.HairdressingService;
import dj.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<HairdressingService> getServiceById(@RequestParam long id){
        Optional<HairdressingService> serviceOptional = priceListService.getById(id);
        return serviceOptional
                .map(service -> new ResponseEntity<>(service, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<HairdressingService> addService(@RequestBody HairdressingServiceData serviceData){
        Optional<HairdressingService> serviceOptional = priceListService.add(serviceData);
        return serviceOptional
                .map(service -> new ResponseEntity<>(service, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, path="/new_category")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryData categoryData){
        Optional<Category> categoryOptional = priceListService.addCategory(categoryData);
        return categoryOptional
                .map(category -> new ResponseEntity<>(category, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }

}
