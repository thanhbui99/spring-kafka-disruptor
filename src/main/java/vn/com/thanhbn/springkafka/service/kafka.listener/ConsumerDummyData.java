package vn.com.thanhbn.springkafka.service.kafka.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import vn.com.thanhbn.springkafka.disruptor.DisruptorBean;
import vn.com.thanhbn.springkafka.disruptor.GenericEventProducer;
import vn.com.thanhbn.springkafka.model.Dummy;

import static vn.com.thanhbn.springkafka.config.KafkaConsumerConfig.CONCURRENCY;

@Configuration
public class ConsumerDummyData {

    @Autowired
    DisruptorBean disruptorBean;
    public static final String TOPIC = "THANH-BN-2";


    @KafkaListener(topics = TOPIC, groupId = "thanhbn", containerFactory = "kafkaListenerContainerFactoryLocal")
    public void listen(ConsumerRecord<String, Dummy> consumerRecord, Acknowledgment ack) {
        // 1 concurrency corresponds to 1 Ringbuffer
        int concurrency = consumerRecord.partition() % CONCURRENCY;
        System.out.println("partition " + consumerRecord.partition() + " - Thread: " + Thread.currentThread().getName());
        GenericEventProducer<Dummy> producer = new GenericEventProducer<Dummy>(disruptorBean.DISRUPTOR_MAP.get(concurrency));
        producer.onData(consumerRecord.value());
        ack.acknowledge();
    }
}
