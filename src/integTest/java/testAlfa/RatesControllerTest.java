package testAlfa;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import testAlfa.testAlfa.TestAlfaApplication;
import testAlfa.testAlfa.service.ExchangeRatesService;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes= TestAlfaApplication.class)
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureWireMock(port = 4567, stubs = "classpath:/mappings")
public class RatesControllerTest {


    private static final String GET_REQUEST_RATES_NOW = "/latest.json";
    private static final String GET_REQUEST_RATES_YESTERDAY = "/historical";
    private static final String GET_REQUEST_GIF = "/getGif";

    @Autowired
    ExchangeRatesService exchangeRatesService;
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetRatesNow() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_REQUEST_RATES_NOW))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.disclaimer")
                        .value("Usage subject to terms: https://openexchangerates.org/terms"))
                .andExpect(jsonPath("$.license")
                        .value("https://openexchangerates.org/license"))
                .andExpect(jsonPath("$.timestamp")
                        .value(1608544800))
                .andExpect(jsonPath("$.base")
                        .value("USD"));

    }

    @Test
    public void testGetRatesYesterday() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_REQUEST_RATES_YESTERDAY))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.disclaimer")
                        .value("Usage subject to terms: https://openexchangerates.org/terms"))
                .andExpect(jsonPath("$.license")
                        .value("https://openexchangerates.org/license"))
                .andExpect(jsonPath("$.timestamp")
                        .value(1608544800))
                .andExpect(jsonPath("$.base")
                        .value("USD"));

    }

    @Test
    public void testGetGif() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(GET_REQUEST_GIF))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.data.embed_url")
                        .value("https://giphy.com/embed/LOWE17wZZP48B5zBUu"))
                .andExpect(jsonPath("$.data.image_url")
                        .value("https://media3.giphy.com/media/LOWE17wZZP48B5zBUu/giphy.gif"))
                .andExpect(jsonPath("$.data.bitly_url")
                        .value("https://gph.is/g/ZkRPV1m"))
                .andExpect(jsonPath("$.data.url")
                        .value("https://giphy.com/gifs/cbs-broke-cbs-LOWE17wZZP48B5zBUu"))
                .andExpect(jsonPath("$.meta.response_id")
                        .value("e849061b7c821c6bffc58ff714ef5334154703fb"));

    }

}
