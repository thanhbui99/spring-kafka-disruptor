package vn.com.thanhbn.springkafka.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class JSON {
    private ObjectMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new ObjectMapper();
    }

    public String toJson(Object data) {
        try {
            return mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            return null;
        }
    }
}
