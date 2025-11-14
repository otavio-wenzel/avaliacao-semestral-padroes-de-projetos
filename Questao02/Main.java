package Questao02;

public class Main {
    public static void main(String[] args) {
        var input = new RiskInput.Builder()
                .idade(34)
                .rendaMensal(9000)
                .horizonteInvestimentoAnos(12)
                .toleranciaVolatilidade(0.7)
                .necessidadeLiquidez(0.3)
                .alocacaoAcoesPct(0.65)
                .maiorDrawdownPct(0.18)
                .build();

        var analyzer = new RiskAnalyzer(RiskModelFactory.create(RiskModel.AGGRESSIVE));
        System.out.println("AGGRESSIVE  -> " + analyzer.analyze(input));

        analyzer.setStrategy(RiskModelFactory.create(RiskModel.MODERATE));
        System.out.println("MODERATE    -> " + analyzer.analyze(input));

        analyzer.setStrategy(RiskModelFactory.create(RiskModel.CONSERVATIVE));
        System.out.println("CONSERVATIVE-> " + analyzer.analyze(input));

        var inputLiquidezAlta = new RiskInput.Builder()
                .idade(52)
                .rendaMensal(6000)
                .horizonteInvestimentoAnos(4)
                .toleranciaVolatilidade(0.35)
                .necessidadeLiquidez(0.8)
                .alocacaoAcoesPct(0.25)
                .maiorDrawdownPct(0.1)
                .build();

        analyzer.setStrategy(RiskModelFactory.create(RiskModel.MODERATE));
        System.out.println("MODERATE (liquidez alta) -> " + analyzer.analyze(inputLiquidezAlta));
    }
}