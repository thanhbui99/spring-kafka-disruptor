package vn.com.thanhbn.springkafka.disruptor;

public class GenericEvent<T> {
    private T value;

    public T get() {
        return value;
    }

    public void set(T value) {
        this.value = value;
    }
}
