package Questao01;

import java.time.Instant;
import java.util.List;

public abstract class Report {

    public final String generate(ReportContext ctx) {
        validate(ctx);
        List<String> raw = collectData(ctx);           
        List<String> prioritized = prioritize(raw);    
        return format(prioritized, ctx);               
    }

    protected abstract List<String> collectData(ReportContext ctx);

    protected abstract List<String> prioritize(List<String> raw);

    protected abstract String format(List<String> prioritized, ReportContext ctx);


    protected void validate(ReportContext ctx) {
        if (ctx == null) throw new IllegalArgumentException("ReportContext não pode ser nulo");
        if (ctx.start() != null && ctx.end() != null && ctx.end().isBefore(ctx.start())) {
            throw new IllegalArgumentException("Intervalo inválido: end < start");
        }
    }

    protected final String stamp() {
        return "generatedAt=" + Instant.now();
    }
}