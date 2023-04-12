package br.com.aoc2.cache.simulator;

public class Cache {
    private Conjunto[] conjuntos;
    private int totalAcesso = 0;
    private int totalMiss = 0;
    private int qntMissCompulsorio = 0;
    private int qntMissCapaxidade = 0;
    private int qntMissConflito = 0;
    private int totalHit = 0;
    private PoliticaSubstituicao politicaSubstituicao;

    public Cache(int numeroConjutos, Conjunto conjunto, PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
        this.conjuntos = new Conjunto[numeroConjutos];
        for (int i = 0; i < numeroConjutos; i++) {
            try {
                this.conjuntos[i] = (Conjunto) conjunto.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Conjunto[] getConjuntos() {
        return conjuntos;
    }

    public void setConjuntos(Conjunto[] conjuntos) {
        this.conjuntos = conjuntos;
    }

    public int getTotalAcesso() {
        return totalAcesso;
    }

    public void setTotalAcesso(int totalAcesso) {
        this.totalAcesso = totalAcesso;
    }

    public int getTotalMiss() {
        return totalMiss;
    }

    public void setTotalMiss(int totalMiss) {
        this.totalMiss = totalMiss;
    }

    public int getQntMissCompulsorio() {
        return qntMissCompulsorio;
    }

    public void setQntMissCompulsorio(int qntMissCompulsorio) {
        this.qntMissCompulsorio = qntMissCompulsorio;
    }

    public int getQntMissCapaxidade() {
        return qntMissCapaxidade;
    }

    public void setQntMissCapaxidade(int qntMissCapaxidade) {
        this.qntMissCapaxidade = qntMissCapaxidade;
    }

    public int getQntMissConflito() {
        return qntMissConflito;
    }

    public void setQntMissConflito(int qntMissConflito) {
        this.qntMissConflito = qntMissConflito;
    }

    public int getTotalHit() {
        return totalHit;
    }

    public void setTotalHit(int totalHit) {
        this.totalHit = totalHit;
    }

    public PoliticaSubstituicao getPoliticaSubstituicao() {
        return politicaSubstituicao;
    }

    public void setPoliticaSubstituicao(PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
    }
}
