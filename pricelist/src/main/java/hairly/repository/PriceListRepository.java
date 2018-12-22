package hairly.repository;

import hairly.model.HairdressingService;
import org.springframework.data.repository.CrudRepository;

public interface PriceListRepository extends CrudRepository<HairdressingService, Long> {
}
