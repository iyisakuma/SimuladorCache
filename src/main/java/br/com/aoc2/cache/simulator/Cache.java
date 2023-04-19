package br.com.aoc2.cache.simulator;

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

    private int bitsIndice;
    private int bitsTag;
    private PoliticaSubstituicao politicaSubstituicao;

    private List<Integer> aux = new ArrayList<>();

    public Cache(int numeroConjutos, int grauAssociatividade, int bitsTag, int bitsIndice, PoliticaSubstituicao politicaSubstituicao) {
        this.politicaSubstituicao = politicaSubstituicao;
        this.bitsTag = bitsTag;
        this.bitsIndice = bitsIndice;
        this.conjuntos = new Conjunto[numeroConjutos/grauAssociatividade];
        for (int i = 0; i < numeroConjutos/grauAssociatividade; i++) {
            Bloco[] blocos = new Bloco[grauAssociatividade];
            for (int j = 0; j <grauAssociatividade; j++){
                blocos[j] = new Bloco();
            }
            this.conjuntos[i] = new Conjunto(grauAssociatividade, blocos);
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
        var enderecoDecimal = parseBinarieToDecimal(enderecoBinario);
        var indiceCache = enderecoDecimal % (nroConjuntos);
        var conjunto = conjuntos[indiceCache];


        boolean hit = false;
        this.addAcesso();
        Miss miss = conjunto.contem(enderecoBinario, bitsTag);
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
                if(aux.contains(enderecoDecimal)){
                    System.out.println(enderecoDecimal);
                }
                aux.add(enderecoDecimal);
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


    public void tratamentoFalta(String enderecoBinario) {
        var nroConjuntos = this.getConjuntos().length;
        int enderecoDecimal = parseBinarieToDecimal(enderecoBinario);
        var indiceCache = enderecoDecimal % (nroConjuntos);
        var conjunto = this.getConjuntos()[indiceCache];
        if (conjunto.contemBlocoVago()) {
            for (var bloco : conjunto.getBlocos()) {
                if (bloco.getInfo().isEmpty()) {
                    bloco.setValido(true);
                    bloco.setInfo(String.valueOf(enderecoDecimal));
                    bloco.setTag(enderecoBinario.substring(0, bitsTag - 1));
                }
            }
        } else {
            switch (politicaSubstituicao) {
                case RANDOM:
                    var random = new Random();
                    int nroBlocos = conjunto.getBlocos().length;
                    int indiceBloco = random.nextInt(nroBlocos);
                    var bloco = conjunto.getBlocos()[indiceBloco];
                    bloco.setInfo(String.valueOf(enderecoDecimal));
                    bloco.setTag(enderecoBinario.substring(0, bitsTag - 1));
                    break;
            }
        }
    }

    public String mostraResultado(boolean isSaidaPadrao) {
        if (isSaidaPadrao) {
            var qntMiss = (qntMissCapacidade + qntMissCompulsorio + qntMissConflito);
            double taxaMiss = (qntMiss / totalAcesso);
            double taxaHit = 1 - taxaMiss;
            double taxaMissCompulsorio = qntMissCompulsorio / qntMiss;
            double taxaMissCapacidade = qntMissCapacidade / qntMiss;
            double taxaMissConflito = qntMissConflito / qntMiss;
            return String.format("%.2f, %.2f, %.2f, %.2f, %.2f, %.2f ",
                    totalAcesso, taxaHit, taxaMiss, taxaMissCompulsorio,
                    taxaMissCapacidade, taxaMissConflito);
        } else {
            var quantidadeMiss = (qntMissCapacidade + qntMissCompulsorio + qntMissConflito);
            var txMiss = (quantidadeMiss / totalAcesso);
            var txHit = 1 - txMiss;
            var txMissCompulsorio = qntMissCompulsorio / quantidadeMiss;
            var txMissCapacidade = qntMissCapacidade / quantidadeMiss;
            var txMissConflito = qntMissConflito / quantidadeMiss;
            return String.format("O total de acessos foi de: " + totalAcesso + "\n" + "A quantidade de miss total eh: " + quantidadeMiss + "\n" + "A taxa de miss: " + txMiss * 100 + "\n" + "A taxa de hit: " + txHit * 100 + "\n" + "A taxa de miss de Capacidade: " + txMissCapacidade * 100 + "\n" + "A taxa de miss Compulsório: " + txMissCompulsorio * 100 + "\n" + "A taxa de miss de Conflito: " + txMissConflito * 100);
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
