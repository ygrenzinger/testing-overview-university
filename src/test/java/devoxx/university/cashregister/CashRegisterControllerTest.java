package devoxx.university.cashregister;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CashRegisterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testing_receipt() throws Exception {

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/receipt")
                .content("[\n" +
                        "  {\n" +
                        "    \"name\": \"Pommes\",\n" +
                        "    \"quantity\": 3\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"Cerises\",\n" +
                        "    \"quantity\": 6\n" +
                        "  },\n" +
                        "  {\n" +
                        "    \"name\": \"Bananes\",\n" +
                        "    \"quantity\": 3\n" +
                        "  }\n" +
                        "]")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        String resultDOW = result.getResponse().getContentAsString();

        Assertions.assertNotNull(resultDOW);
        Assertions.assertEquals("{" +
                "\"items\":[" +
                "{\"fruit\":\"Pommes\",\"quantity\":3,\"total\":300}," +
                "{\"fruit\":\"Cerises\",\"quantity\":6,\"total\":375}," +
                "{\"fruit\":\"Bananes\",\"quantity\":3,\"total\":300}" +
                "]," +
                "\"discounts\":[{\"name\":\"More than 10 fruits\",\"amount\":200}]," +
                "\"total\":775" +
                "}", resultDOW);
    }

}