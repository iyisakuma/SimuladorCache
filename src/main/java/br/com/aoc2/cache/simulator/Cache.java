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

    public Cache(int numeroConjutos, Conjunto conjunto,  PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;

    }
}
