package dj.repository;

import dj.model.HairdressingService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceListRepository extends CrudRepository<HairdressingService, Long> {
}
