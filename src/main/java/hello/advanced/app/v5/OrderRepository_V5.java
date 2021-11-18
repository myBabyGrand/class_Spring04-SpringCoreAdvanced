package hello.advanced.app.v5;

import hello.advanced.trace.callback.TraceTemplate;
import hello.advanced.trace.logtrace.LogTrace;

import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository_V5 {
    private final TraceTemplate template;

    public OrderRepository_V5(LogTrace trace) {
        this.template = new TraceTemplate(trace);
    }



    public void save (String itemId){
        template.execute("OrderRepository.save()", () -> {
            if(itemId.equals("ex")){
                throw new IllegalStateException("item id is ex");
            }
            sleep(1000);
            return null;
        });
    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
