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

    public Optional<HairdressingService> getById(long id){
        return priceListRepository.findById(id);
    }

    public HairdressingService add(HairdressingServiceData dtService){
        Category category;
        if(categoryExists(dtService.getCategoryName())) {
            category = getCategoryByName(dtService.getCategoryName());
        }else {
            category = new Category(dtService.getCategoryName());
        }
        HairdressingService service = new HairdressingService(dtService.getName(), dtService.getPrice(), dtService.getDurationInMinutes());
        service.setCategory(category);
        category.addService(service);
        categoryRepository.save(category);
        return priceListRepository.save(service);
    }

    public Optional<Category> addCategory(CategoryData categoryData){
        if(!categoryExists(categoryData.getName())){
            return Optional.of(categoryRepository.save(new Category(categoryData.getName())));
        }
        return Optional.empty();
    }

    private Category getCategoryByName(String name){
        return categoryRepository.getByNameEquals(name).orElse(null);
    }

    private boolean categoryExists(String category){
        return categoryRepository.getByNameEquals(category).isPresent();
    }
}
