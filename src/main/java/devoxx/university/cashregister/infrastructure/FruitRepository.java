package devoxx.university.cashregister.infrastructure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Long> {
    Optional<Fruit> findPriceFruitByName(String name);
}
