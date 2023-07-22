package vn.com.thanhbn.springkafka.service;

public interface DummyService<T> {
    public void dummy(String nameWorker, T data);
}
