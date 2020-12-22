package testAlfa.testAlfa.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.model.RatesResponse;
import testAlfa.testAlfa.service.ExchangeRatesService;

@RequiredArgsConstructor
@RestController
public class RatesController {

    private final ExchangeRatesService exchangeRatesService;

    @GetMapping
    public String ExchangeRatesController() {
        return "static/greeting.html";
    }

    @GetMapping("/latest.json")
    public RatesResponse getRatesNow() {
        return exchangeRatesService.getRatesNow();
    }

    @GetMapping("/historical")
    public RatesResponse getRatesYesterday() {
        return exchangeRatesService.getRatesYesterday();
    }

    @GetMapping("/getGif")
    public GiphyResponse resolveOpenGif() {
        return exchangeRatesService.resolveOpenGif();
    }
}
