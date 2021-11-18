package hello.advanced.app.v5;


import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;

import org.springframework.stereotype.Service;

@Service

public class OrderService_V5 {

    private final OrderRepository_V5 orderRepository;
    private final TraceTemplate template;
    public OrderService_V5(OrderRepository_V5 orderRepository, LogTrace trace) {
        this.orderRepository = orderRepository;
        this.template = new TraceTemplate(trace);
    }

    public void orderItem(String itemId){
        template.execute("OrderService.orderItem()", () -> {
            orderRepository.save(itemId);
            return null;
        });
    }
}
