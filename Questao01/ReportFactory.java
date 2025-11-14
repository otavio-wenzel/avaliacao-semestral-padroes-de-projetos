package Questao01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class ReportFactory {
    private ReportFactory() {}

    public static Report create(ReportType type) {
        return switch (type) {
            case DAILY  -> new DailyReport();
            case WEEKLY -> new WeeklyReport();
        };
    }

    private static final class DailyReport extends Report {
        @Override
        protected List<String> collectData(ReportContext ctx) {
            List<String> raw = new ArrayList<>();
            raw.add("TMS:entregas=124;atrasos=7;SLA=96%");
            raw.add("WMS:expedidos=310;pendentes=12");
            raw.add("Telemetry:ocorrencias=5;paradasNaoPlanejadas=2");
            return raw;
        }

        @Override
        protected List<String> prioritize(List<String> raw) {
            return raw.stream()
                      .sorted(Comparator.comparing((String s) ->
                          s.contains("atrasos") || s.contains("ocorrencias") ? 0 : 1))
                      .toList();
        }

        @Override
        protected String format(List<String> prioritized, ReportContext ctx) {
            return """
                   # Relatório Diário (%s)
                   Região: %s
                   %s
                   %s
                   """.formatted(
                    ctx.region(),
                    ctx.region(),
                    String.join("\n", prioritized),
                    stamp()
            ).trim();
        }
    }

    private static final class WeeklyReport extends Report {
        @Override
        protected List<String> collectData(ReportContext ctx) {
            List<String> raw = new ArrayList<>();
            raw.add("DW:volumeSemana=1750;atrasos=38;SLA=94.5%");
            raw.add("Finance:freteMedio=R$ 312,40;ociosos=4");
            raw.add("Quality:NPS=72;reclamacoes=11");
            return raw;
        }

        @Override
        protected List<String> prioritize(List<String> raw) {
            return raw.stream()
                      .sorted(Comparator.comparing((String s) ->
                          s.contains("SLA") || s.contains("NPS") ? 0 : 1))
                      .toList();
        }

        @Override
        protected String format(List<String> prioritized, ReportContext ctx) {
            return """
                   # Relatório Semanal (%s → %s)
                   Região: %s
                   Consolidado:
                   %s

                   %s
                   """.formatted(
                    ctx.start(), ctx.end(),
                    ctx.region(),
                    String.join("\n", prioritized),
                    stamp()
            ).trim();
        }
    }
}