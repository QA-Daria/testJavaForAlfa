package testAlfa.testAlfa.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "exchange")
public class ExchangeProps {
    private String appId;
    private String symbols;

}
