package br.com.aoc2.cache.simulator;

import br.com.aoc2.cache.simulator.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static br.com.aoc2.cache.simulator.util.Util.*;
import static br.com.aoc2.cache.simulator.Miss.*;

public class Cache {
    private Conjunto[] conjuntos;
    private double totalAcesso = 0;
    private double totalMiss = 0;
    private double qntMissCompulsorio = 0;
    private double qntMissCapacidade = 0;
    private double qntMissConflito = 0;
    private double totalHit = 0;

    private int bitsTag;
    private int bSize;
    private PoliticaSubstituicao politicaSubstituicao;

    public Cache(int numeroConjutos, int grauAssociatividade, int bitsTag, int bsize, PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
        this.bitsTag = bitsTag;
        this.bSize = bsize;
        this.conjuntos = new Conjunto[numeroConjutos];
        for (int i = 0; i < numeroConjutos; i++) {
            Bloco[] blocos = new Bloco[grauAssociatividade];
            for (int j = 0; j < grauAssociatividade; j++) {
                blocos[j] = new Bloco();
            }
            this.conjuntos[i] = new Conjunto(blocos);
        }
    }

    public Conjunto[] getConjuntos() {
        return conjuntos;
    }


    public PoliticaSubstituicao getPoliticaSubstituicao() {
        return politicaSubstituicao;
    }

    public void setPoliticaSubstituicao(PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
    }

    public boolean contem(String enderecoBinario) {
        var nroConjuntos = conjuntos.length;
        var indiceCache = calculaIndiceCache(enderecoBinario, nroConjuntos, bSize);
        var conjunto = conjuntos[indiceCache];


        boolean hit = false;
        this.addAcesso();
        var tipoEnderecamento = TipoEnderecamento.type(this);
        Miss miss = conjunto.contem(enderecoBinario, bitsTag, tipoEnderecamento);
        if(isFull() && miss != NAO_HOUVE){
            miss = CAPACIDADE;
        }
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

    private boolean isFull() {
        boolean isFull = true;
        for(var conjunto : getConjuntos()){
            for(var bloco: conjunto.getBlocos()){
                if(!bloco.isValido()){
                    isFull = false;
                }
            }
        }
        return isFull;
    }


    public void tratamentoFalta(String enderecoBinario) {
        var indiceCache = calculaIndiceCache(enderecoBinario, getConjuntos().length, bSize);
        var conjunto = this.getConjuntos()[indiceCache];
        if (conjunto.temBlocoInvalido()) {
            for (var bloco : conjunto.getBlocos()) {
                if (!bloco.isValido()) {
                    bloco.setValido(true);
                    bloco.setTag(enderecoBinario.substring(0, bitsTag));
                    return;
                }
            }
        } else {
            switch (politicaSubstituicao) {
                case RANDOM:
                    var random = new Random();
                    int nroBlocos = conjunto.getBlocos().length;
                    int indiceBloco = random.nextInt(nroBlocos);
                    var blocoRandom = conjunto.getBlocos()[indiceBloco];
                    blocoRandom.setTag(enderecoBinario.substring(0, bitsTag));
                    return;
                case FIFO:



            }
        }
    }

    public String mostraResultado(boolean isSaidaPadrao) {
        if (isSaidaPadrao) {
            double taxaMiss = (totalMiss / totalAcesso);
            double taxaHit = totalHit / totalAcesso;
            double taxaMissCompulsorio = qntMissCompulsorio / totalMiss;
            double taxaMissCapacidade = qntMissCapacidade / totalMiss;
            double taxaMissConflito = qntMissConflito / totalMiss;
            return String.format("%.2f, %.2f, %.2f, %.2f, %.2f, %.2f  ",
                    totalAcesso, taxaHit, taxaMiss, taxaMissCompulsorio,
                    taxaMissCapacidade, taxaMissConflito);
        } else {
            var quantidadeMiss = (qntMissCapacidade + qntMissCompulsorio + qntMissConflito);
            var txMiss = (quantidadeMiss / totalAcesso);
            var txHit = 1 - txMiss;
            var txMissCompulsorio = qntMissCompulsorio / quantidadeMiss;
            var txMissCapacidade = qntMissCapacidade / quantidadeMiss;
            var txMissConflito = qntMissConflito / quantidadeMiss;
            return "O total de acessos foi de: " + totalAcesso + "\n" + "A quantidade de miss total eh: " + quantidadeMiss + "\n" + "A taxa de miss: " + txMiss * 100 + "%\n" + "A taxa de hit: " + txHit * 100 + "%\n" + "A taxa de miss de Capacidade: " + txMissCapacidade * 100 + "%\n" + "A taxa de miss Compulsório: " + txMissCompulsorio * 100 + "%\n" + "A taxa de miss de Conflito: " + txMissConflito * 100 + "%";
        }
    }

    private int semRepetir(List<Integer> repetidos) {
        List<Integer> aux = new ArrayList<>();
        for (int a : repetidos) {
            int c = 0;
            for (int b : repetidos) {
                if (a == b) {
                    c++;
                }
            }
            if (c > 1) {
                aux.add(a);
            }
        }
        return aux.size();
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

    @Override
    public String toString() {
        return "Cache{" +
                ", totalAcesso=" + totalAcesso +
                ", totalMiss=" + totalMiss +
                ", qntMissCompulsorio=" + qntMissCompulsorio +
                ", qntMissCapacidade=" + qntMissCapacidade +
                ", qntMissConflito=" + qntMissConflito +
                ", totalHit=" + totalHit +
                ", politicaSubstituicao=" + politicaSubstituicao +
                '}';
    }
}
