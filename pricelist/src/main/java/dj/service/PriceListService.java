package dj.service;

import dj.model.HairdressingService;
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

    @Autowired
    public PriceListService(PriceListRepository priceListRepository) {
        this.priceListRepository = priceListRepository;
    }

    public List<HairdressingService> getAll(){
        Iterable<HairdressingService> serviceIterable = priceListRepository.findAll();
        return StreamSupport.stream(serviceIterable.spliterator(), false).collect(Collectors.toList());
    }

    public HairdressingService getById(long id){
        Optional<HairdressingService> service = priceListRepository.findById(id);
        return service.orElse(null);
    }
}
