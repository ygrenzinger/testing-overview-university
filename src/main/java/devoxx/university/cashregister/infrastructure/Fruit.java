package devoxx.university.cashregister.infrastructure;

import java.util.Objects;


public class Fruit {

    private long id;
    private String name;
    private long price;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return id == fruit.id &&
                price == fruit.price &&
                Objects.equals(name, fruit.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price);
    }
}
