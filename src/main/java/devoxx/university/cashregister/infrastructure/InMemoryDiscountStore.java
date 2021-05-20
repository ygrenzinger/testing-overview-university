package devoxx.university.cashregister.infrastructure;

import devoxx.university.cashregister.domain.discount.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

@Service
public class InMemoryDiscountStore implements DiscountStore {

    private final Map<String, VolumeDiscount> discounts;

    public InMemoryDiscountStore() {
        this.discounts = new TreeMap<>();
        this.discounts.put("Cerises", new VolumeDiscount(4));
        this.discounts.put("Bananes", new VolumeDiscount(2));
    }

    public void addVolumeDiscount(String name, int threshold) {
        this.discounts.put(name, new VolumeDiscount(threshold));
    }

    public Optional<VolumeDiscount> getVolumeDiscount(String fruit) {
        return Optional.ofNullable(discounts.get(fruit));
    }

    public List<ApplicableBasketDiscount> getBasketDiscount() {
        return List.of(
                MoreThan10FruitsDiscountApplicable.get(),
                MoreThan4DifferentFruitsDiscountApplicable.get()
        );
    }
}
