package devoxx.university.fruits;

import com.github.tomakehurst.wiremock.WireMockServer;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class FruitController {

    public final WireMockServer wireMockServer = new WireMockServer(options().port(8089));

    public void start() {
        wireMockServer.start();

        willAnswer("/fruits?name=Bananes", "{ \"id\": 1, \"name\": \"Bananes\", \"price\": 125 }");
        willAnswer("/fruits?name=Pommes", "{ \"id\": 2, \"name\": \"Pommes\", \"price\": 50 }");
        willAnswer("/fruits?name=Fraises", "{ \"id\": 3, \"name\": \"Fraises\", \"price\": 25 }");
    }

    void stop() {
        wireMockServer.stop();
    }

    private void willAnswer(String url, String response) {
        wireMockServer.stubFor(get(urlEqualTo(url))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(response)));
    }

}
