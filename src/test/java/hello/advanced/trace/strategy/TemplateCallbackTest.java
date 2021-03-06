package hello.advanced.trace.strategy;

import hello.advanced.trace.strategy.code.template.CallBack;
import hello.advanced.trace.strategy.code.template.TimeLogTemplate;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateCallbackTest {

    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     * */
    @Test
    @DisplayName("템플릿 콜백 패턴 - 익명 내부 클래스")
    void callback_V1(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(new CallBack() {
            @Override
            public void callback() {
                log.info("비즈니스 로직 1 실행");
            }
        });
        template.execute(new CallBack() {
            @Override
            public void callback() {
                log.info("비즈니스 로직 2 실행");
            }
        });
    }
    /**
     * 템플릿 콜백 패턴 - 익명 내부 클래스
     * */
    @Test
    @DisplayName("템플릿 콜백 패턴 - 익명 내부 클래스 - 람다")
    void callback_V2(){
        TimeLogTemplate template = new TimeLogTemplate();
        template.execute(() -> log.info("비즈니스 로직 1 실행"));
        template.execute(() -> log.info("비즈니스 로직 2 실행"));
    }
}
