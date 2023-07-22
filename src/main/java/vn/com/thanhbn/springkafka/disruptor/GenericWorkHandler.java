package vn.com.thanhbn.springkafka.disruptor;

import com.lmax.disruptor.WorkHandler;
import vn.com.thanhbn.springkafka.model.Dummy;
import vn.com.thanhbn.springkafka.service.DummyService;
import vn.com.thanhbn.springkafka.service.impl.DummyServiceImpl;


public class GenericWorkHandler<T> implements WorkHandler<GenericEvent<T>> {

    private String name;
    private DummyService<T> dummyService;

    public GenericWorkHandler(String name, DummyService dummyService) {
        this.name = name;
        this.dummyService = dummyService;
    }

    public void onEvent(GenericEvent<T> event) throws Exception {
        dummyService.dummy(name, event.get());
        Thread.sleep(500);
    }
}
