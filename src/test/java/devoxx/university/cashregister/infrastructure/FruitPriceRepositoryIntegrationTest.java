package devoxx.university.cashregister.infrastructure;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactFolder("target/pacts")
class FruitPriceRepositoryIntegrationTest {

    @Pact(provider = "FruitsProvider", consumer = "CashRegister")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Get a single fruit, by name")
                .path("/fruits").matchQuery("name", ".*", "Fraises")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(Map.of("Content-Type", "application/json"))
                .body(new PactDslJsonBody()
                        .stringType("name", "Cerises")
                        .numberType("price", 33).asBody())
                .toPact();
    }

    @Test
    void test(MockServer mockServer) throws IOException {
        FruitPriceRepository fruitPriceRepository = new FruitPriceRepository(mockServer.getUrl());
        long price = fruitPriceRepository.retrieveFruitPrice("Fraises");
        assertThat(price).isEqualTo(33);
    }

}
