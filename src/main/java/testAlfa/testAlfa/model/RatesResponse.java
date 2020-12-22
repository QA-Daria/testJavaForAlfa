package testAlfa.testAlfa.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

@Data
@Accessors(chain = true)
public class RatesResponse {
    String disclaimer;
    String license;
    int timestamp;
    String base;
    Map<String, Float> rates = new HashMap<>();
}
