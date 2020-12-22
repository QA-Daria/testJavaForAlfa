package testAlfa.testAlfa.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import testAlfa.testAlfa.model.GiphyResponse;

@FeignClient(name = "giphy-api", url = "${feign-client.giphy.url}")
public interface GiphyClient {

    @GetMapping("/v1/gifs/random")
    GiphyResponse getGif(@RequestParam(name = "tag") String tag,
                         @RequestParam(name = "api_key") String key);
}
