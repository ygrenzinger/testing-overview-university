package devoxx.university.cashregister.model.discount;

import devoxx.university.cashregister.model.ApplicableBasketDiscount;
import devoxx.university.cashregister.model.BasketItem;

import java.util.List;

public class MoreThan10FruitsDiscountApplicable implements ApplicableBasketDiscount {

    @Override
    public String getName() {
        return "More than 10 fruits";
    }

    @Override
    public long getAmount() {
        return 200;
    }

    @Override
    public boolean isApplicable(List<BasketItem> fruits) {
        return fruits.stream()
                .mapToLong(BasketItem::getQuantity)
                .sum() > 5;
    }
}
