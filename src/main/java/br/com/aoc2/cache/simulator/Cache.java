package br.com.aoc2.cache.simulator;

import static br.com.aoc2.cache.simulator.util.Util.*;
import static br.com.aoc2.cache.simulator.Miss.*;

public class Cache {
    private Conjunto[] conjuntos;
    private int totalAcesso = 0;
    private int totalMiss = 0;
    private int qntMissCompulsorio = 0;
    private int qntMissCapacidade = 0;
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

    public int getTotalAcesso() {
        return totalAcesso;
    }

    public int getTotalMiss() {
        return totalMiss;
    }

    public int getQntMissCompulsorio() {
        return qntMissCompulsorio;
    }

    public int getQntMissCapacidade() {
        return qntMissCapacidade;
    }

    public int getQntMissConflito() {
        return qntMissConflito;
    }

    public int getTotalHit() {
        return totalHit;
    }

    public PoliticaSubstituicao getPoliticaSubstituicao() {
        return politicaSubstituicao;
    }

    public void setPoliticaSubstituicao(PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
    }

    public boolean contem(String enderecoBinario) {
        var nroConjuntos = this.getConjuntos().length;
        var enderecoDecimal = parseBinarieToDecimal(enderecoBinario);
        var indiceCache = enderecoDecimal % nroConjuntos;
        var conjunto = this.getConjuntos()[indiceCache];


        boolean hit = false;
        this.addAcesso();
        Miss miss = conjunto.contem(enderecoBinario);
        switch (miss) {
            case CONFLITO:
                addTotalMiss();
                addMissConflito();
                break;
            case CAPACIDADE:
                addTotalMiss();
                addMissCapacidade();
                break;
            case COMPULSORIO:
                addTotalMiss();
                addMissCompulsorio();
                break;
            case NAO_HOUVE:
                hit = true;
                addTotalHit();
                break;

        }
        return hit;
    }


    public void tratamentoFalta(String enderecoMemoriaBits) {
    }

    public String mostraResultado(boolean isSaidaPadrao) {
        if (isSaidaPadrao) {
            var qntMiss = (qntMissCapacidade + qntMissCompulsorio + qntMissConflito);
            var taxaMiss = (qntMiss / totalAcesso);
            var taxaHit = 1 - taxaMiss;
            var taxaMissCompulsorio = qntMissCompulsorio / qntMiss;
            var taxaMissCapacidade = qntMissCapacidade / qntMiss;
            var taxaMissConflito = qntMissConflito / qntMiss;
            System.out.println(taxaMissCapacidade);
            return String.format("%d, %d, %d, %d, %d, %d ",
                    totalAcesso, taxaHit, taxaMiss, taxaMissCompulsorio,
                    taxaMissCapacidade, taxaMissConflito);
        } else {
            return "";
        }
    }

    private void addMissCompulsorio() {
        this.qntMissCompulsorio++;
    }

    private void addMissCapacidade() {
        this.qntMissCapacidade++;
    }

    private void addMissConflito() {
        this.qntMissConflito++;
    }

    private void addAcesso() {
        this.totalAcesso++;
    }

    private void addTotalMiss() {
        this.totalMiss++;
    }

    private void addTotalHit() {
        this.totalHit++;
    }
}
