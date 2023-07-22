package vn.com.thanhbn.springkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.thanhbn.springkafka.model.Dummy;
import vn.com.thanhbn.springkafka.utility.JSON;

import static vn.com.thanhbn.springkafka.service.kafka.listener.ConsumerDummyData.TOPIC;

@RestController
@RequestMapping("/api")
public class PushKafkaController {

    @Autowired
    @Qualifier("kafkaTemplate")
    KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    JSON json;


    @GetMapping("/push")
    public String home(@RequestParam String key, @RequestParam int loop, @RequestParam String data) {
        Dummy dummy = new Dummy("thanh", 24,"hung yen");
        for (int i = 0; i < loop; i++) {
            kafkaTemplate.send(TOPIC, key, json.toJson(dummy));
        }
        return "ok";
    }
}
