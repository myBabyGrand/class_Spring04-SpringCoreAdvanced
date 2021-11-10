package hello.advanced.trace;

public class TraceStatus {

    private TraceId traceId;
    private Long StartTimeMs;
    private String message;

    public TraceStatus(TraceId traceId, Long startTimeMs, String message) {
        this.traceId = traceId;
        StartTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return StartTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
