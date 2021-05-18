package devoxx.university.cashregister.repository;

import devoxx.university.cashregister.model.Fruit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FruitRepository extends CrudRepository<Fruit, Long> {
    Optional<Fruit> findPriceFruitByName(String name);
}
