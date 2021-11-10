package hello.advanced.app.v0;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository_V0 {

    public void save (String itemId){
        if(itemId.equals("ex")){
            throw new IllegalStateException("item id is ex");
        }
        sleep(1000);
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
