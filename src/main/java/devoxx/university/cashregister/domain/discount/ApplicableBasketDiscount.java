package devoxx.university.cashregister.domain.discount;

import devoxx.university.cashregister.domain.BasketItem;

import java.util.List;

public interface ApplicableBasketDiscount {

    String getName();

    long getAmount();

    boolean isApplicable(List<BasketItem> fruits);
}
