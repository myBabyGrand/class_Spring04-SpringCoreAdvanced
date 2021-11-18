package hello.advanced.app.v3;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController_V3 {

    private final OrderService_V3 orderService;
    private final LogTrace trace;

    @GetMapping("/v3/request")
    public String request(String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderController.request()");

            orderService.orderItem(itemId);

            trace.end(status);
            return "ok";
        }catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 다시 던져준다.
        }
    }
}
