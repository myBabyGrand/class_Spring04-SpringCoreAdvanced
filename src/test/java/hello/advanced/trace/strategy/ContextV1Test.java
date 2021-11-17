package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.strategy.Context_V1;
import hello.advanced.trace.strategy.code.strategy.Strategy;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic1;
import hello.advanced.trace.strategy.code.strategy.StrategyLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class ContextV1Test {
    @Test
    void Context_V1(){
        logic1();
        logic2();
    }

    private void logic2() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        log.info("비즈니스 로직2 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);
    }

    private void logic1() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 시작
        log.info("비즈니스 로직1 실행");
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long result = endTime - startTime;
        log.info("resultTime = {}", result);
    }

    /**
     * 전략패턴
     */
    @Test
    @DisplayName("전략패턴")
    void StrategyV1(){
        StrategyLogic1 strategyLogic1 = new StrategyLogic1();
        Context_V1 context1 = new Context_V1(strategyLogic1);
        context1.execute();

        StrategyLogic2 strategyLogic2 = new StrategyLogic2();
        Context_V1 context2 = new Context_V1(strategyLogic2);
        context2.execute();
    }

    /**
     * 전략패턴 익명 내부 클래스
     */
    @Test
    @DisplayName("전략패턴 익명 내부 클래스1")
    void StrategyV2(){
        Strategy strategyLogic1 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        Context_V1 context1 = new Context_V1(strategyLogic1);
        log.info("strategyLogic1 = {}",strategyLogic1.getClass());
        context1.execute();

        Strategy strategyLogic2 = new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        };

        Context_V1 context2 = new Context_V1(strategyLogic2);
        log.info("strategyLogic2 = {}",strategyLogic2.getClass());
        context2.execute();
    }

    /**
     * 전략패턴 익명 내부 클래스2
     */
    @Test
    @DisplayName("전략패턴 익명 내부 클래스2")
    void StrategyV3(){
        Context_V1 context1 = new Context_V1(new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직1 실행");
            }
        });
        context1.execute();

        Context_V1 context2 = new Context_V1(new Strategy() {

            @Override
            public void call() {
                log.info("비즈니스 로직2 실행");
            }
        });
        context2.execute();
    }

    /**
     * 전략패턴 익명 내부 클래스2 - Lambda
     */
    @Test
    @DisplayName("전략패턴 익명 내부 클래스2 - Lambda")
    void StrategyV4(){
        Context_V1 context1 = new Context_V1(() -> log.info("비즈니스 로직1 실행"));
        context1.execute();

        Context_V1 context2 = new Context_V1(() -> log.info("비즈니스 로직2 실행"));
        context2.execute();
    }
}
