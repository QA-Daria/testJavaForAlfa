package testAlfa.testAlfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testAlfa.testAlfa.client.ExchangeRatesClient;
import testAlfa.testAlfa.client.GiphyClient;
import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.model.RatesResponse;
import testAlfa.testAlfa.props.ExchangeProps;
import testAlfa.testAlfa.util.DateUtil;


@RequiredArgsConstructor
@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

    private final ExchangeRatesClient exchangeRatesClient;
    private final GiphyService giphyService;
    private final ExchangeProps exchangeProps;
    DateUtil dateUtil = new DateUtil();
    String yesterdayDateString = "";

    @Override
    public RatesResponse getRatesNow() {
        return exchangeRatesClient.getRatesNow(exchangeProps.getAppId(), exchangeProps.getSymbols());
    }

    @Override
    public RatesResponse getRatesYesterday(String yesterdayDate) {
        return exchangeRatesClient.getRatesYesterday(yesterdayDate, exchangeProps.getAppId(), exchangeProps.getSymbols());
    }

    @Override
    public RatesResponse getRatesYesterday() {
        yesterdayDateString = dateUtil.getYesterdayDateString();
        return getRatesYesterday(yesterdayDateString);
    }



    @Override
    public GiphyResponse resolveOpenGif() {

        RatesResponse ratesResponseNow = getRatesNow();
        yesterdayDateString = dateUtil.getYesterdayDateString();
        RatesResponse ratesResponseYesterday = getRatesYesterday(yesterdayDateString);
        System.out.println(yesterdayDateString);

        Float valueRatesNow = ratesResponseNow.getRates().get("RUB");
        Float valueRatestYesterday = ratesResponseYesterday.getRates().get("RUB");

        if (valueRatestYesterday >= valueRatesNow) {
            return giphyService.getGif("rich");
        } else {
            return giphyService.getGif("broke");
        }

    }


}

