package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

/**
 * 파라메터로 전략을 전달
 * */
@Slf4j
public class Context_V2 {

    public void execute(Strategy strategy){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        strategy.call();//위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);
    }
}
