package hello.advanced.trace.strategy.code.strategy;


import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관
 * */
@Slf4j
public class Context_V1 {

    private Strategy strategy;

    public Context_V1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute(){
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        strategy.call();//위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);
    }
}
