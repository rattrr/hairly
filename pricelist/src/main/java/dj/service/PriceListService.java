package dj.service;

import dj.dto.CategoryData;
import dj.dto.HairdressingServiceData;
import dj.model.Category;
import dj.model.HairdressingService;
import dj.repository.CategoryRepository;
import dj.repository.PriceListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PriceListService {
    private final PriceListRepository priceListRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public PriceListService(PriceListRepository priceListRepository, CategoryRepository categoryRepository) {
        this.priceListRepository = priceListRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<HairdressingService> getAll(){
        Iterable<HairdressingService> serviceIterable = priceListRepository.findAll();
        return StreamSupport.stream(serviceIterable.spliterator(), false).collect(Collectors.toList());
    }

    public HairdressingService getById(long id){
        Optional<HairdressingService> service = priceListRepository.findById(id);
        return service.orElse(null);
    }

    public HairdressingService add(HairdressingServiceData dtService){
        if(categoryExists(dtService.getCategory())){
            HairdressingService service = new HairdressingService(dtService.getName(), dtService.getPrice(), dtService.getDurationInMinutes());
            Category category = getCategoryByName(dtService.getCategory());
            service.setCategory(category);
            category.addService(service);
            categoryRepository.save(category);
            return priceListRepository.save(service);
        }
        return new HairdressingService();
    }

    public Category addCategory(CategoryData categoryData){
        return categoryRepository.save(new Category(categoryData.getName()));
    }

    private Category getCategoryByName(String name){
        return categoryRepository.getByNameEquals(name).orElse(null);
    }

    private boolean categoryExists(String category){
        return categoryRepository.getByNameEquals(category).isPresent();
    }
}
