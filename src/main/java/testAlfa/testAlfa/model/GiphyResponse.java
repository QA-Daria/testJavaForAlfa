package testAlfa.testAlfa.model;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class GiphyResponse {
   testAlfa.testAlfa.model.Data data;
   Meta meta;
}
