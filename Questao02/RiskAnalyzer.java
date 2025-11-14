package Questao02;

import java.util.Objects;

public final class RiskAnalyzer {
    private RiskCalcStrategy strategy;

    public RiskAnalyzer(RiskCalcStrategy initial) {
        this.strategy = Objects.requireNonNull(initial);
    }

    public void setStrategy(RiskCalcStrategy s) {
        this.strategy = Objects.requireNonNull(s);
    }

    public RiskCalcStrategy.RiskResult analyze(RiskInput in) {
        return strategy.calculate(in);
    }
}