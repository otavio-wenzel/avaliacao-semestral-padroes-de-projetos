package Questao01;

import java.time.Instant;
import java.util.Map;

public final class ReportContext {
    private final Instant start;
    private final Instant end;
    private final String region;
    private final Map<String, Object> meta;

    private ReportContext(Builder b) {
        this.start = b.start;
        this.end = b.end;
        this.region = b.region;
        this.meta = b.meta;
    }

    public Instant start() { return start; }
    public Instant end() { return end; }
    public String region() { return region; }
    public Map<String, Object> meta() { return meta; }

    public static final class Builder {
        private Instant start;
        private Instant end;
        private String region = "GLOBAL";
        private Map<String, Object> meta = Map.of();

        public Builder start(Instant v){ this.start = v; return this; }
        public Builder end(Instant v){ this.end = v; return this; }
        public Builder region(String v){ this.region = v; return this; }
        public Builder meta(Map<String, Object> v){ this.meta = v; return this; }

        public ReportContext build(){ return new ReportContext(this); }
    }
}