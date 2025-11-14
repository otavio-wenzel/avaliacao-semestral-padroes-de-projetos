package Questao01;

import java.util.Objects;

public final class ReportEngine {
    public String gerar(ReportType type, ReportContext ctx) {
        Objects.requireNonNull(type, "type");
        Objects.requireNonNull(ctx, "ctx");
        Report report = ReportFactory.create(type);
        return report.generate(ctx);
    }
}