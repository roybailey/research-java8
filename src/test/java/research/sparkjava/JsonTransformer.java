package research.sparkjava;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public String render(Object model) {
        String result = "";
        try {
            result = mapper.writeValueAsString(model);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

}