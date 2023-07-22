package vn.com.thanhbn.springkafka.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import vn.com.thanhbn.springkafka.model.Dummy;
import vn.com.thanhbn.springkafka.service.DummyService;

import javax.annotation.PostConstruct;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;

@Configuration
public class DisruptorBean {
    public final Map<Integer, RingBuffer> DISRUPTOR_MAP = new ConcurrentHashMap<>();

    public static HashMap<String, String> nameWorker = new HashMap<>();

    @Autowired
    DummyService dummyService;

    public String ip() {
        try (final DatagramSocket datagramSocket = new DatagramSocket()) {
            datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
            return datagramSocket.getLocalAddress().getHostAddress();
        } catch (Exception e) {
            return null;
        }
    }

    @PostConstruct
    public void initDisruptorForKafkaConsumer() {
        int concurrency = 3;
        for (int i = 0; i < concurrency; i++) {
            GenericEventFactory<GenericEvent<Dummy>> eventFactory = new GenericEventFactory<>();
            int bufferSize = 1024;
            Disruptor<GenericEvent<Dummy>> disruptor = new Disruptor(eventFactory, bufferSize, Executors.defaultThreadFactory());
            disruptor.handleEventsWithWorkerPool(new GenericWorkHandler<>(ip() + "-" + "zeus-" + Thread.currentThread().getName() + "-" + i, dummyService));
            disruptor.start();
            DISRUPTOR_MAP.put(i, disruptor.getRingBuffer());
        }
    }
}
