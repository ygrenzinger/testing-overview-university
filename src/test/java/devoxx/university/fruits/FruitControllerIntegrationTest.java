package devoxx.university.fruits;

import au.com.dius.pact.core.model.annotations.PactDirectory;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import au.com.dius.pact.provider.junitsupport.Provider;
import au.com.dius.pact.provider.junitsupport.loader.PactFolder;
import au.com.dius.pact.provider.junitsupport.loader.PactSource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;

@Provider("FruitsProvider")
@PactFolder("target/pacts")
public class FruitControllerIntegrationTest {

    private final FruitController fruitController = new FruitController();

    @BeforeEach
    void before(PactVerificationContext context) {
        fruitController.start();
        context.setTarget(new HttpTestTarget("localhost", 8089, "/"));
    }

    @AfterEach
    void after() {
        fruitController.stop();
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

}
