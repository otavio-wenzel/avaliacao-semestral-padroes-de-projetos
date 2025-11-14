package Questao02;

public interface RiskCalcStrategy {
    RiskResult calculate(RiskInput input);

    final class RiskResult {
        private final int score;
        private final String label;

        public RiskResult(int score, String label) {
            this.score = Math.max(0, Math.min(100, score));
            this.label = label;
        }
        public int score() { return score; }
        public String label() { return label; }

        @Override public String toString() {
            return "RiskResult{score=" + score + ", label='" + label + "'}";
        }
    }
}