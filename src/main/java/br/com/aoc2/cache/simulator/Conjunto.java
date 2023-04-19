package br.com.aoc2.cache.simulator;

import br.com.aoc2.cache.simulator.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Conjunto {
    private Bloco[] blocos;


    public Conjunto(int grauAssociatividade, Bloco[] blocos) {
        this.blocos = blocos;
    }

    public Bloco[] getBlocos() {
        return blocos;
    }

    public void setBlocos(Bloco[] blocos) {
        this.blocos = blocos;
    }


    public Miss contem(String enderecoBinario, int bitsTag) {
        boolean hit = false;
        boolean missCompulsorio = true;

        for (var bloco : blocos) {
            if (bloco.isValido()) {
                missCompulsorio = false;
                var decimal = Util.parseBinarieToDecimal(enderecoBinario);
                String tag = enderecoBinario.substring(0, bitsTag - 1);
                var enderecoDecimal = String.valueOf(decimal);
                if (tag.equals(bloco.getTag()) && bloco.getInfo().equals(enderecoDecimal)) {
                    hit = true;
                    break;
                }
            }
        }var decimal = Util.parseBinarieToDecimal(enderecoBinario);
        if (missCompulsorio) {
            return Miss.COMPULSORIO;
        }
        if (hit) {
            return Miss.NAO_HOUVE;
        } else {
            return blocos.length == 1 ? Miss.CONFLITO : Miss.CAPACIDADE;
        }

    }

    public boolean contemBlocoVago() {
        for (var bloco : blocos) {
            if (bloco.getInfo().isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
