package Questao01;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Main {
    public static void main(String[] args) {
        var engine = new ReportEngine();

        var ctxDaily = new ReportContext.Builder()
                .region("BR-SUL")
                .build();

        var ctxWeekly = new ReportContext.Builder()
                .start(Instant.now().minus(7, ChronoUnit.DAYS))
                .end(Instant.now())
                .region("BR-SP")
                .build();

        System.out.println("\n===== DIÁRIO =====");
        System.out.println(engine.gerar(ReportType.DAILY, ctxDaily));

        System.out.println("\n===== SEMANAL =====");
        System.out.println(engine.gerar(ReportType.WEEKLY, ctxWeekly));
    }
}