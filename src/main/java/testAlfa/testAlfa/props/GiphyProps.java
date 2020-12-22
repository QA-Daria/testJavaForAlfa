package testAlfa.testAlfa.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "giphy")
public class GiphyProps {
    private String apiKey;
}
