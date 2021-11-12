package hello.advanced.trace.logtrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FieldLogTrace implements LogTrace{
    private static final String START_PREFIX = "-->";
    private static final String COMPLETE_PREFIX = "<--";
    private static final String EX_PREFIX = "<X-";

    private TraceId traceIdHolder; //traceId 동기화, 동시성 이슈 발생

    @Override
    public TraceStatus begin(String message) {
        syncTraceId();
        TraceId traceId = traceIdHolder;
        Long startTimeMs = System.currentTimeMillis();
        log.info("[{}][{}] {}{}", traceId.getId(), padLevel(traceId.getLevel()), addSpace(START_PREFIX, traceId.getLevel()), message);
        return new TraceStatus(traceId, startTimeMs, message);
    }

    private void syncTraceId(){
        if(traceIdHolder == null){
            traceIdHolder = new TraceId();
        }else{
            traceIdHolder = traceIdHolder.createNextId();
        }
    }

    @Override
    public void end(TraceStatus status) {
        complete(status, null);
    }

    private void complete(TraceStatus status, Exception e) {
        Long stopTimeMs = System.currentTimeMillis();
        long resultTimeMs = stopTimeMs - status.getStartTimeMs();
        TraceId traceId = status.getTraceId();
        if (e == null) {
            log.info("[{}][{}] {}{} time={}ms", traceId.getId(), padLevel(traceId.getLevel()),addSpace(COMPLETE_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs);
        } else {
            log.info("[{}][{}] {}{} time={}ms ex={}", traceId.getId(),padLevel(traceId.getLevel()),addSpace(EX_PREFIX, traceId.getLevel()), status.getMessage(), resultTimeMs,e.toString());
        }

        releaseTraceId();
    }

    private void releaseTraceId() {
        if(traceIdHolder.isFirstLevel()){
            traceIdHolder = null;// destroy;
        }else{
            traceIdHolder = traceIdHolder.createPreviousId();
        }
    }

    @Override
    public void exception(TraceStatus status, Exception e) {
        complete(status, e);
    }

    private static String addSpace(String prefix, int level) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < level; i++) {
            sb.append( (i == level - 1) ? "|" + prefix : "| ");
        }
        return sb.toString();
   }

   //depth가 1000을 넘어가진 않을 거라는 가정.
   private static String padLevel(int level){
        String str = String.valueOf(level);
        if(str.length() >3){
            return str;
        }else{
            int needs = 3 - str.length();
            StringBuilder sb = new StringBuilder();
            for(int i =0 ; i<needs;i++){
                sb.append("0");
            }
            sb.append(str);
            return sb.toString();
        }
   }
}
