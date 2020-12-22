package testAlfa.testAlfa.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import testAlfa.testAlfa.client.GiphyClient;
import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.props.GiphyProps;

@RequiredArgsConstructor
@Service
public class GiphyServiceImpl implements GiphyService {

    private final GiphyClient giphyClient;
    private final GiphyProps giphyProps;

    @Override
    public GiphyResponse getGif(String tag) {
        return giphyClient.getGif(tag, giphyProps.getApiKey());
    }
}
