package vn.com.thanhbn.springkafka.service.impl;

import org.springframework.stereotype.Service;
import vn.com.thanhbn.springkafka.model.Dummy;
import vn.com.thanhbn.springkafka.service.DummyService;

@Service
public class DummyServiceImpl implements DummyService<Dummy> {

    @Override
    public void dummy(String nameWorker, Dummy data) {
        System.out.println("workHandler(" + nameWorker + ")" + ":" + data.getName() + ":" + Thread.currentThread().getName() + ":" + this.hashCode());
    }
}
