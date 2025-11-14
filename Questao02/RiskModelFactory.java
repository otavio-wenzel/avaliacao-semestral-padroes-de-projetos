package Questao02;

public final class RiskModelFactory {
    private RiskModelFactory() {}

    public static RiskCalcStrategy create(RiskModel model) {
        return switch (model) {
            case AGGRESSIVE   -> new AggressiveModel();
            case MODERATE     -> new ModerateModel();
            case CONSERVATIVE -> new ConservativeModel();
        };
    }

    static int clampScore(double v){ return (int)Math.round(Math.max(0, Math.min(100, v))); }
    static String label(int score){
        if (score >= 70) return "ALTO (agressivo)";
        if (score >= 40) return "MÉDIO (moderado)";
        return "BAIXO (conservador)";
    }

    private static final class AggressiveModel implements RiskCalcStrategy {
        @Override
        public RiskResult calculate(RiskInput in) {
            double base =
                  30 * in.getToleranciaVolatilidade()
                + 30 * in.getAlocacaoAcoesPct()
                + 20 * Math.min(1.0, in.getHorizonteInvestimentoAnos()/10.0)
                - 10 * in.getNecessidadeLiquidez()
                - 10 * in.getMaiorDrawdownPct();

            int score = clampScore(base + bonusRenda(in.getRendaMensal()));
            return new RiskResult(score, label(score));
        }
    }

    private static final class ModerateModel implements RiskCalcStrategy {
        @Override
        public RiskResult calculate(RiskInput in) {
            double base =
                  20 * in.getToleranciaVolatilidade()
                + 20 * in.getAlocacaoAcoesPct()
                + 25 * Math.min(1.0, in.getHorizonteInvestimentoAnos()/8.0)
                - 15 * in.getNecessidadeLiquidez()
                - 20 * in.getMaiorDrawdownPct();

            int score = clampScore(base + bonusRenda(in.getRendaMensal()) * 0.5);
            return new RiskResult(score, label(score));
        }
    }

    private static final class ConservativeModel implements RiskCalcStrategy {
        @Override
        public RiskResult calculate(RiskInput in) {
            double base =
                  10 * in.getToleranciaVolatilidade()
                + 10 * in.getAlocacaoAcoesPct()
                + 15 * Math.min(1.0, in.getHorizonteInvestimentoAnos()/6.0)
                - 30 * in.getNecessidadeLiquidez()
                - 35 * in.getMaiorDrawdownPct();

            int score = clampScore(base + Math.min(5, bonusRenda(in.getRendaMensal()) * 0.3));
            return new RiskResult(score, label(score));
        }
    }

    private static double bonusRenda(double rendaMensal){
        if (rendaMensal <= 3000) return 0;
        if (rendaMensal <= 8000) return 3;
        if (rendaMensal <= 15000) return 6;
        return 8;
    }
}