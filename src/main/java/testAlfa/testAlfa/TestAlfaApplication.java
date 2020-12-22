package testAlfa.testAlfa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import testAlfa.testAlfa.props.ExchangeProps;
import testAlfa.testAlfa.props.GiphyProps;

@SpringBootApplication
@EnableConfigurationProperties({ExchangeProps.class, GiphyProps.class})
@EnableFeignClients
public class TestAlfaApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestAlfaApplication.class, args);
	}

}
