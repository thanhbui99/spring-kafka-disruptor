package vn.com.thanhbn.springkafka.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;
import vn.com.thanhbn.springkafka.model.Dummy;

import java.io.IOException;

public class DummyDeserializer implements Deserializer<Dummy> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public Dummy deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Dummy.class);
        } catch (IOException e) {
            // Handle deserialization exception as needed
            e.printStackTrace();
            return null;
        }
    }
}
