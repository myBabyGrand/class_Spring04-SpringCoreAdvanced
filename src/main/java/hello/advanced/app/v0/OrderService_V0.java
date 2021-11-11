package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService_V0 {

    private final OrderRepository_V0 orderRepository;
    public void orderItem(String itemId){
        orderRepository.save(itemId);
    }
}
