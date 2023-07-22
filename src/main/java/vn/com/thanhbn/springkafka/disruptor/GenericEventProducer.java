package vn.com.thanhbn.springkafka.disruptor;

import com.lmax.disruptor.RingBuffer;

public class GenericEventProducer<T> {

    private final RingBuffer<GenericEvent<T>> ringBuffer;

    public GenericEventProducer(RingBuffer<GenericEvent<T>> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    public void onData(T data) {
        long sequence = ringBuffer.next();
        try {
            if (sequence == 1024L) System.out.println("sequence: "+sequence);
            GenericEvent<T> event = ringBuffer.get(sequence);
            event.set(data);
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}

