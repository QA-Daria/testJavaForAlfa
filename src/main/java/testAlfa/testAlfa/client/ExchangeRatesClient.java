package testAlfa.testAlfa.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import testAlfa.testAlfa.model.RatesResponse;

@FeignClient(name = "openexchangerates-api", path = "/api", url = "${feign-client.openexchangerates.url:}")
public interface ExchangeRatesClient {


    @GetMapping("/latest.json/")
    RatesResponse getRatesNow(@RequestParam(name = "app_id") String appId,
                              @RequestParam(name = "symbols") String symbols);

    @GetMapping("/historical/{date}.json/")
    RatesResponse getRatesYesterday(
            @PathVariable(name = "date") String yesterdayDate,
            @RequestParam(name = "app_id") String appId,
            @RequestParam(name = "symbols") String symbols);

    //default RatesResponse getRatesYesterday(String yesterdayDate) {
      //  return getRatesYesterday(yesterdayDate, "c25d00ea79304f1eb35f732ad302b97c", "RUB");
   // }
}
