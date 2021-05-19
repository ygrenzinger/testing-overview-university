package devoxx.university.cashregister.infrastructure;

import devoxx.university.cashregister.domain.fruit.FruitPrice;
import org.springframework.stereotype.Service;

@Service
public class FruitPriceRepository implements FruitPrice {
    private final FruitRepository fruitRepository;

    public FruitPriceRepository(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    @Override
    public long retrieveFruitPrice(String fruitName) {
        return fruitRepository
                .findPriceFruitByName(fruitName)
                .map(Fruit::getPrice)
                .orElseThrow(() -> new RuntimeException("There is no " + fruitName + " in the repository"));
    }
}
