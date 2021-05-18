package devoxx.university.cashregister.model;

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
    public String toString() {
        return "BasketItem{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
