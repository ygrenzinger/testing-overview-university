package devoxx.university.cashregister.service;

import devoxx.university.cashregister.model.Fruit;
import devoxx.university.cashregister.repository.DiscountStore;
import devoxx.university.cashregister.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.function.Supplier;

@Service
public class FruitPriceComputer {

    private final FruitRepository fruitRepository;
    private final DiscountStore discountStore;

    public FruitPriceComputer(FruitRepository fruitRepository, DiscountStore discountStore) {
        this.fruitRepository = fruitRepository;
        this.discountStore = discountStore;
    }

    public long getPriceWithDiscount(String fruitName, int number) {
        Fruit fruit = fruitRepository
                .findPriceFruitByName(fruitName)
                .orElseThrow(() -> new RuntimeException("There is no " + fruitName + " in the repository"));
        long discountAmount = discountStore.getVolumeDiscount(fruit.getName())
                .map(discount -> discount.getDiscountAmount(number, fruit.getPrice()))
                .orElse(0L);
        long total = number * fruit.getPrice();
        return total - discountAmount;
    }
}
