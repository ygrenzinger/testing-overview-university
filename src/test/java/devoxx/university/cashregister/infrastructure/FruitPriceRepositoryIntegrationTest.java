package devoxx.university.cashregister.infrastructure;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactDirectory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactDirectory("target/pacts")
@PactTestFor(providerName = "FruitsProvider")
class FruitPriceRepositoryIntegrationTest {

    @Pact(consumer = "CashRegister")
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
    public void should_retrieve_a_fruit(MockServer mockServer) {
        FruitPriceRepository fruitPriceRepository = new FruitPriceRepository(mockServer.getUrl());
        long price = fruitPriceRepository.retrieveFruitPrice("Fraises");
        assertThat(price).isEqualTo(33);
    }

}
