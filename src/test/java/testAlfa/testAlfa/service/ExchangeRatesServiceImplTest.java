package testAlfa.testAlfa.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testAlfa.testAlfa.client.ExchangeRatesClient;
import testAlfa.testAlfa.client.GiphyClient;
import testAlfa.testAlfa.model.Data;
import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.model.Meta;
import testAlfa.testAlfa.model.RatesResponse;
import testAlfa.testAlfa.props.ExchangeProps;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExchangeRatesServiceImplTest {

    private static String BASE = "USD";
    private static int TIMES_TANP = 1608422340;
    private static String LICENSE = "https://openexchangerates.org/license";
    private static String DISCLAIMER = "Usage subject to terms: https://openexchangerates.org/terms";
    private static String ALLURL = "someURL";
    private static String MESSAGE = "OK";
    private static String REQ_ID = "e9c4dbf6ee7bab983f623c667d60cfbc70514751";

    @Mock
    ExchangeRatesClient mockExchangeRatesClient;
    @Mock
    GiphyService giphyService;

    @Mock
    ExchangeProps exchangeProps;
    @InjectMocks
    ExchangeRatesServiceImpl exchangeRatesService;

    RatesResponse ratesResponse = new RatesResponse();
    GiphyResponse giphyResponse = new GiphyResponse();
    Meta meta = new Meta();
    Data data = new Data();

    @BeforeEach
    public void before() {
//        when(exchangeProps.getAppId()).thenReturn("1");
  //      when(exchangeProps.getSymbols()).thenReturn("2");
    }

    public ExchangeRatesServiceImplTest(){
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
        when(exchangeProps.getAppId()).thenReturn("1");
        when(exchangeProps.getSymbols()).thenReturn("2");
        when(mockExchangeRatesClient.getRatesNow("1", "2")).thenReturn(ratesResponse);
        RatesResponse response = exchangeRatesService.getRatesNow();
        Assertions.assertEquals(BASE, response.getBase());
        Assertions.assertEquals(TIMES_TANP, response.getTimestamp());
        Assertions.assertEquals(LICENSE, response.getLicense());
        Assertions.assertEquals(DISCLAIMER, response.getDisclaimer());
        Assertions.assertNotNull(response.getRates());
    }

    @Test
    public void getRatesYesterday() {
        when(mockExchangeRatesClient.getRatesYesterday("2020-12-19", "1", "2")).thenReturn(ratesResponse);
        RatesResponse response = mockExchangeRatesClient.getRatesYesterday("2020-12-19", "1", "2");
        Assertions.assertEquals(BASE, response.getBase());
        Assertions.assertEquals(TIMES_TANP, response.getTimestamp());
        Assertions.assertEquals(LICENSE, response.getLicense());
        Assertions.assertEquals(DISCLAIMER, response.getDisclaimer());
        Assertions.assertNotNull(response.getRates());
    }

    @Test
    public void testGetRatesYesterday() {
        when(mockExchangeRatesClient.getRatesYesterday(any(), any(), any())).thenReturn(ratesResponse);
        RatesResponse response = exchangeRatesService.getRatesYesterday("2020-12-19");
        Assertions.assertEquals(BASE, response.getBase());
        Assertions.assertEquals(TIMES_TANP, response.getTimestamp());
        Assertions.assertEquals(LICENSE, response.getLicense());
        Assertions.assertEquals(DISCLAIMER, response.getDisclaimer());
        Assertions.assertNotNull(response.getRates());
    }

    @Test
    public void resolveOpenGif() {
        when(exchangeProps.getAppId()).thenReturn("1");
        when(exchangeProps.getSymbols()).thenReturn("2");
        when(giphyService.getGif(any())).thenReturn(giphyResponse);
        when(mockExchangeRatesClient.getRatesYesterday(any(), eq("1"), eq("2"))).thenReturn(ratesResponse);
        when(mockExchangeRatesClient.getRatesNow(eq("1"), eq("2"))).thenReturn(ratesResponse);
        GiphyResponse response = exchangeRatesService.resolveOpenGif();
        Assertions.assertEquals(ALLURL, response.getData().getUrl());
        Assertions.assertEquals(ALLURL, response.getData().getBitly_url());
        Assertions.assertEquals(ALLURL, response.getData().getEmbed_url());
        Assertions.assertEquals(ALLURL, response.getData().getImage_url());
        Assertions.assertEquals(MESSAGE, response.getMeta().getMsg());
        Assertions.assertEquals(REQ_ID, response.getMeta().getResponse_id());
    }
}