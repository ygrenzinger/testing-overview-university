package devoxx.university.cashregister.domain;

import devoxx.university.cashregister.domain.discount.AppliedBasketDiscount;

import java.util.List;
import java.util.Objects;

public class Receipt {

    private final List<ReceiptItem> items;
    private final List<AppliedBasketDiscount> basketDiscounts;
    private final long total;

    public Receipt(List<ReceiptItem> items, List<AppliedBasketDiscount> basketDiscounts, long total) {
        this.items = items;
        this.basketDiscounts = basketDiscounts;
        this.total = total;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public List<AppliedBasketDiscount> getBasketDiscounts() {
        return basketDiscounts;
    }

    public long getTotal() {
        return total;
    }

}
