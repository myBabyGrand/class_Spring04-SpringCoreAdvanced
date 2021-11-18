package hello.advanced.app.v3;


import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderRepository_V3 {
    private final LogTrace trace;

    public void save (String itemId){
        TraceStatus status = null;
        try{
            status = trace.begin("OrderRepository.save()");

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
