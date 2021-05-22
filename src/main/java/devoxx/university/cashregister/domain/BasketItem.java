package devoxx.university.cashregister.domain;

import java.util.Objects;

public class BasketItem {

    private final String name;
    private final int quantity;

    public BasketItem(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity && name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, quantity);
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
