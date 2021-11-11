package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceStatus;
import org.junit.jupiter.api.Test;

class HelloTrace_V1Test {

    @Test
    void begin_end(){
        HelloTrace_V1 traceV1 = new HelloTrace_V1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.end(status);
    }

    @Test
    void begin_exception(){
        HelloTrace_V1 traceV1 = new HelloTrace_V1();
        TraceStatus status = traceV1.begin("hello");
        traceV1.exception(status, new IllegalStateException());
    }

}