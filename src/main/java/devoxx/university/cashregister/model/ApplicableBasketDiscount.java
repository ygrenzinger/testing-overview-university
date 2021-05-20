package devoxx.university.cashregister.model;

import java.util.List;

public interface ApplicableBasketDiscount {

    String getName();

    long getAmount();

    boolean isApplicable(List<BasketItem> fruits);
}
