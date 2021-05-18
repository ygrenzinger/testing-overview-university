package devoxx.university.cashregister.model;

import java.util.List;
import java.util.Objects;

public class Receipt {

    private final List<ReceiptItem> items;
    private final List<AppliedBasketDiscount> discounts;
    private final long total;

    public Receipt(List<ReceiptItem> items, List<AppliedBasketDiscount> discounts, long total) {
        this.items = items;
        this.discounts = discounts;
        this.total = total;
    }

    public List<ReceiptItem> getItems() {
        return items;
    }

    public List<AppliedBasketDiscount> getDiscounts() {
        return discounts;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return total == receipt.total && items.equals(receipt.items) && discounts.equals(receipt.discounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, discounts, total);
    }
}
