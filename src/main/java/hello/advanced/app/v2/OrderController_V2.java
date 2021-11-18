package hello.advanced.app.v2;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTrace_V2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController_V2 {

    private final OrderService_V2 orderService;
    private final HelloTrace_V2 trace;


    @GetMapping("/v2/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");

            orderService.orderItem(itemId, status.getTraceId());

            trace.end(status);
            return "ok";
        }catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 다시 던져준다.
        }
    }
}
