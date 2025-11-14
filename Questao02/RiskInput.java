package Questao02;

import java.util.Objects;

public final class RiskInput {
    private final int idade;                        
    private final double rendaMensal;             
    private final int horizonteInvestimentoAnos;   
    private final double toleranciaVolatilidade;  
    private final double necessidadeLiquidez;      
    private final double alocacaoAcoesPct;          
    private final double maiorDrawdownPct;    

    private RiskInput(Builder b) {
        this.idade = b.idade;
        this.rendaMensal = b.rendaMensal;
        this.horizonteInvestimentoAnos = b.horizonteInvestimentoAnos;
        this.toleranciaVolatilidade = clamp01(b.toleranciaVolatilidade);
        this.necessidadeLiquidez = clamp01(b.necessidadeLiquidez);
        this.alocacaoAcoesPct = clamp01(b.alocacaoAcoesPct);
        this.maiorDrawdownPct = clamp01(b.maiorDrawdownPct);
        Objects.requireNonNull(this);
    }

    private static double clamp01(double v){ return Math.max(0.0, Math.min(1.0, v)); }

    public int getIdade() { return idade; }
    public double getRendaMensal() { return rendaMensal; }
    public int getHorizonteInvestimentoAnos() { return horizonteInvestimentoAnos; }
    public double getToleranciaVolatilidade() { return toleranciaVolatilidade; }
    public double getNecessidadeLiquidez() { return necessidadeLiquidez; }
    public double getAlocacaoAcoesPct() { return alocacaoAcoesPct; }
    public double getMaiorDrawdownPct() { return maiorDrawdownPct; }

    public static final class Builder {
        private int idade = 30;
        private double rendaMensal = 5000.0;
        private int horizonteInvestimentoAnos = 5;
        private double toleranciaVolatilidade = 0.5;
        private double necessidadeLiquidez = 0.5;
        private double alocacaoAcoesPct = 0.5;
        private double maiorDrawdownPct = 0.15;

        public Builder idade(int v){ this.idade = v; return this; }
        public Builder rendaMensal(double v){ this.rendaMensal = v; return this; }
        public Builder horizonteInvestimentoAnos(int v){ this.horizonteInvestimentoAnos = v; return this; }
        public Builder toleranciaVolatilidade(double v){ this.toleranciaVolatilidade = v; return this; }
        public Builder necessidadeLiquidez(double v){ this.necessidadeLiquidez = v; return this; }
        public Builder alocacaoAcoesPct(double v){ this.alocacaoAcoesPct = v; return this; }
        public Builder maiorDrawdownPct(double v){ this.maiorDrawdownPct = v; return this; }

        public RiskInput build(){ return new RiskInput(this); }
    }
}

