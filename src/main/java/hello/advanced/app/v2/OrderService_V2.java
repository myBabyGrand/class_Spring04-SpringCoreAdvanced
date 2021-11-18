package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTrace_V2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService_V2 {

    private final OrderRepository_V2 orderRepository;
    private final HelloTrace_V2 trace;

    public void orderItem(String itemId, TraceId traceId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId,"OrderService.orderItem()");

            orderRepository.save(itemId, status.getTraceId());

            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 다시 던져준다.
        }
    }
}
