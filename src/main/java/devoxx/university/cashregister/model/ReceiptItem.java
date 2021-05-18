package devoxx.university.cashregister.model;

import java.util.Objects;

public class ReceiptItem {

    private final String fruit;
    private final long quantity;
    private final long total;

    public ReceiptItem(String fruit, long quantity, long total) {
        this.fruit = fruit;
        this.quantity = quantity;
        this.total = total;
    }

    public String getFruit() {
        return fruit;
    }

    public long getQuantity() {
        return quantity;
    }

    public long getTotal() {
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return quantity == that.quantity && total == that.total && Objects.equals(fruit, that.fruit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fruit, quantity, total);
    }
}
