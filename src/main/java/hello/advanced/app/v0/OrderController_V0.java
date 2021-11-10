package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController_V0 {

    private final OrderService_V0 orderService_v0;

    @GetMapping("/v0/request")
    public String request(String itemId){
        orderService_v0.orderItem(itemId);
        return "ok";
    }
}
