package testAlfa.testAlfa.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import testAlfa.testAlfa.client.GiphyClient;
import testAlfa.testAlfa.model.Data;
import testAlfa.testAlfa.model.GiphyResponse;
import testAlfa.testAlfa.model.Meta;
import testAlfa.testAlfa.props.GiphyProps;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GiphyServiceImplTest {

    private static String ALLURL = "someURL";
    private static String MESSAGE = "OK";
    private static String REQ_ID = "e9c4dbf6ee7bab983f623c667d60cfbc70514751";

    @Mock
    GiphyClient giphyClient;
    @Mock
    GiphyProps giphyProps;
    @InjectMocks
    GiphyServiceImpl giphyService;

    final GiphyResponse giphyResponse = new GiphyResponse();
    final Meta meta = new Meta();
    final Data data = new Data();

    public GiphyServiceImplTest() {
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
    public void getGif() {
        when(giphyProps.getApiKey()).thenReturn("1");
        when(giphyClient.getGif("rich", "1")).thenReturn(giphyResponse);
        GiphyResponse response = giphyService.getGif("rich");
        Assertions.assertEquals(ALLURL, response.getData().getUrl());
        Assertions.assertEquals(ALLURL, response.getData().getBitly_url());
        Assertions.assertEquals(ALLURL, response.getData().getEmbed_url());
        Assertions.assertEquals(ALLURL, response.getData().getImage_url());
        Assertions.assertEquals(MESSAGE, response.getMeta().getMsg());
        Assertions.assertEquals(REQ_ID, response.getMeta().getResponse_id());
    }
}