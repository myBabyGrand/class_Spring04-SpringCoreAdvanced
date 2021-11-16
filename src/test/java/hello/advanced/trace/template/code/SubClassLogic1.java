package hello.advanced.trace.template.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SubClassLogic1 extends AbstratTemplate{
    @Override
    protected void call() {
        log.info("business Logic : {} ",this.getClass().getSimpleName() );
    }
}