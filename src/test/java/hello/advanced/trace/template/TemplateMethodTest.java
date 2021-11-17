package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {
    @Test
    void tempateMethod_V0(){
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

    @Test
    @DisplayName("템플릿 패턴 적용")
    void tempateMethod_V1(){
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();
        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    @Test
    @DisplayName("익명 내부클래스를 이용하여 템플릿 패턴 적용")
    void tempateMethod_V2(){
        AbstractTemplate template1 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("business Logic : {} ","template1" );
            }
        };
        log.info(template1.getClass().toString());
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate(){
            @Override
            protected void call() {
                log.info("business Logic : {} ","template2" );
            }
        };
        log.info(template2.getClass().toString());
        template2.execute();
    }
}
