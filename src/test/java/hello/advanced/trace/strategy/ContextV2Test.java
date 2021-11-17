package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV2Test {


    /**
     * 전략패턴
     */
    @Test
    @DisplayName("전략패턴")
    void StrategyV1(){
        Context_V2 context = new Context_V2();
        context.execute(new StrategyLogic1());
        context.execute(new StrategyLogic2());
    }
    /**
     * 전략패턴 익명 내부 클래스
     */
    @Test
    @DisplayName("전략패턴 익명 내부 클래스")
    void StrategyV2(){
        Context_V2 context = new Context_V2();
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        context.execute(new Strategy() {
            @Override
            public void call() {
                log.info("비즈니스 로직 2 실행");
            }
        });
    }
    /**
     * 전략패턴 익명 내부 클래스 - Lambda
     */
    @Test
    @DisplayName("전략패턴 익명 내부 클래스 - Lambda")
    void StrategyV3(){
        Context_V2 context = new Context_V2();
        context.execute(() -> log.info("비즈니스 로직 1 실행"));
        context.execute(() -> log.info("비즈니스 로직 2 실행"));
    }

}
