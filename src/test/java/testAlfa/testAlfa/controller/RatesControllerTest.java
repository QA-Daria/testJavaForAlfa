package testAlfa.testAlfa.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testAlfa.testAlfa.model.Data;
import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.model.Meta;
import testAlfa.testAlfa.model.RatesResponse;
import testAlfa.testAlfa.service.ExchangeRatesService;
import testAlfa.testAlfa.service.ExchangeRatesServiceImpl;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RatesControllerTest {

    private static String BASE = "USD";
    private static int TIMES_TANP = 1608422340;
    private static String LICENSE = "https://openexchangerates.org/license";
    private static String DISCLAIMER = "Usage subject to terms: https://openexchangerates.org/terms";
    private static String ALLURL = "someURL";
    private static String MESSAGE = "OK";
    private static String REQ_ID = "e9c4dbf6ee7bab983f623c667d60cfbc70514751";

    @Mock
    ExchangeRatesService mockExchangeRatesService;
    //@Mock
    //ExchangeRatesServiceImpl mockExchangeRatesServiceImp;

    @InjectMocks
    RatesController ratesController;
    RatesResponse ratesResponse = new RatesResponse();
    GiphyResponse giphyResponse = new GiphyResponse();
    Meta meta = new Meta();
    Data data = new Data();

    public RatesControllerTest() {
        ratesResponse.setBase("USD")
                .setTimestamp(1608422340)
                .setLicense("https://openexchangerates.org/license")
                .setDisclaimer("Usage subject to terms: https://openexchangerates.org/terms")
                .getRates().put("RUB", (float) 73.4285);

        meta.setMsg("OK")
                .setResponse_id("e9c4dbf6ee7bab983f623c667d60cfbc70514751")
                .setStatus(200);
        data.setBitly_url("someURL")
                .setEmbed_url("someURL")
                .setImage_url("someURL")
                .setUrl("someURL");

        giphyResponse.setMeta(meta);
        giphyResponse.setData(data);
    }

    @Test
    public void getRatesNow() {
        when(mockExchangeRatesService.getRatesNow()).thenReturn(ratesResponse);
        RatesResponse response = ratesController.getRatesNow();
        Assertions.assertEquals(BASE, response.getBase());
        Assertions.assertEquals(TIMES_TANP, response.getTimestamp());
        Assertions.assertEquals(LICENSE, response.getLicense());
        Assertions.assertEquals(DISCLAIMER, response.getDisclaimer());
        Assertions.assertNotNull(response.getRates());
    }

    @Test
    public void getRatesYesterday() {
        when(mockExchangeRatesService.getRatesYesterday()).thenReturn(ratesResponse);
        RatesResponse response = ratesController.getRatesYesterday();
        Assertions.assertEquals(BASE, response.getBase());
        Assertions.assertEquals(TIMES_TANP, response.getTimestamp());
        Assertions.assertEquals(LICENSE, response.getLicense());
        Assertions.assertEquals(DISCLAIMER, response.getDisclaimer());
        Assertions.assertNotNull(response.getRates());
    }

    @Test
    public void resolveOpenGif() {
        when(mockExchangeRatesService.resolveOpenGif()).thenReturn(giphyResponse);
        GiphyResponse response = ratesController.resolveOpenGif();
        Assertions.assertEquals(ALLURL, response.getData().getUrl());
        Assertions.assertEquals(ALLURL, response.getData().getBitly_url());
        Assertions.assertEquals(ALLURL, response.getData().getEmbed_url());
        Assertions.assertEquals(ALLURL, response.getData().getImage_url());
        Assertions.assertEquals(MESSAGE, response.getMeta().getMsg());
        Assertions.assertEquals(REQ_ID, response.getMeta().getResponse_id());
    }
}