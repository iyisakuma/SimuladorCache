package br.com.aoc2.cache.simulator;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Conjunto implements Cloneable {
    private Bloco[] blocos;

    private int bitsIndice;
    private int bitsOffSet;

    private int bitsTag;

    public Conjunto(int grauAssociatividade, Bloco bloco, int bitsTag, int bitsIndice, int bitsOffSet) {
        var blocos = new Bloco[grauAssociatividade];
        for (int i = 0; i < grauAssociatividade; i++) {
            try {
                blocos[i] = (Bloco) bloco.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }

        this.bitsTag = bitsTag;
        this.bitsIndice = bitsIndice;
        this.bitsOffSet = bitsOffSet;
        this.blocos = blocos;
    }

    public Bloco[] getBlocos() {
        return blocos;
    }

    public void setBlocos(Bloco[] blocos) {
        this.blocos = blocos;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Miss contem(String enderecoBinario) {
        boolean hit = false;
        //Verifica se ha blocos validos
        List<Bloco> blocosValidos = Arrays.stream(blocos).filter(Bloco::isValido).toList();
        if (blocosValidos.isEmpty()) {
            return Miss.COMPULSORIO;
        }
        for (var bloco : blocosValidos) {
            String tag = enderecoBinario.substring(0, bitsTag-1);
            List<Palavra> palavras = Arrays.stream(bloco.getPalavras()).toList();
            if (tag.equals(bloco.getTag()) && palavras.contains(enderecoBinario)) {
                hit = true;
                break;
            }
        }
        if (hit) {
            return Miss.NAO_HOUVE;
        } else {
            return Miss.CAPACIDADE;
        }

    }
}
