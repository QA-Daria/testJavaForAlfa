package testAlfa.testAlfa.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.Accessors;

@lombok.Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Data {
    String url;
    String bitly_url;
    String embed_url;
    String image_url;
}
