package testAlfa.testAlfa.service;

import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.model.RatesResponse;

public interface ExchangeRatesService {

    RatesResponse getRatesNow();

    RatesResponse getRatesYesterday(String yesterdayDate);

    RatesResponse getRatesYesterday();

    GiphyResponse resolveOpenGif();
}
