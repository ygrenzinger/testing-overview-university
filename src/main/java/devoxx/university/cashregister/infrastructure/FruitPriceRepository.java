package devoxx.university.cashregister.infrastructure;

import devoxx.university.cashregister.domain.fruit.FruitPrice;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class FruitPriceRepository implements FruitPrice {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String baseUrl;

    public FruitPriceRepository() {
        this("http://localhost:8089");
    }

    public FruitPriceRepository(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Override
    public long retrieveFruitPrice(String fruitName) {
        return Optional.ofNullable(restTemplate.getForObject(baseUrl + "/fruits?name=" + fruitName, Fruit.class))
                .map(Fruit::getPrice)
                .orElseThrow(() -> new RuntimeException("There is no " + fruitName + " in the repository"));
    }
}
