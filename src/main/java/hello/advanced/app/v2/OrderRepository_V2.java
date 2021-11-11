package hello.advanced.app.v2;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.hellotrace.HelloTrace_V1;
import hello.advanced.trace.hellotrace.HelloTrace_V2;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository_V2 {
    private final HelloTrace_V2 trace;

    public void save (String itemId, TraceId traceId){
        TraceStatus status = null;
        try{
            status = trace.beginSync(traceId, "OrderRepository.save()");

            if(itemId.equals("ex")){
                throw new IllegalStateException("item id is ex");
            }
            sleep(1000);

            trace.end(status);
        }catch(Exception e){
            trace.exception(status, e);
            throw e;//예외를 다시 던져준다.
        }

    }

    private void sleep(int millis) {
        try{
            Thread.sleep(millis);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
