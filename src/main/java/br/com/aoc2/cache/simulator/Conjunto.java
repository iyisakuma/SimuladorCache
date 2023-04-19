package br.com.aoc2.cache.simulator;

import br.com.aoc2.cache.simulator.util.Util;

public class Conjunto {
    private Bloco[] blocos;


    public Conjunto(Bloco[] blocos) {
        this.blocos = blocos;
    }

    public Bloco[] getBlocos() {
        return blocos;
    }


    public Miss contem(String enderecoBinario, int bitTag) {
        boolean hit = false;
        boolean missCompulsorio = false;
        boolean missConflito = false;

        for (var bloco : blocos) {
            if (bloco.isValido()) {
                var decimal = Util.parseBinarieToDecimal(enderecoBinario);
                String tag = enderecoBinario.substring(0, bitTag - 1);
                if (tag.equals(bloco.getTag())) {
                    hit = true;
                    break;
                } else {
                    missConflito = true;
                    missCompulsorio = false;
                }
            }else{
                missCompulsorio = true;
            }

        }

        if (hit) {
            return Miss.NAO_HOUVE;
        } else if (missCompulsorio) {
            var enderecoDecimal = String.valueOf(Util.parseBinarieToDecimal(enderecoBinario));
            return Miss.COMPULSORIO;
        } else {
            return missConflito ? Miss.CONFLITO : Miss.CAPACIDADE;
        }

    }

    public boolean contemBlocoVago() {
        for (var bloco : blocos) {
            if (bloco.isInfoVazia()) {
                return true;
            }
        }
        return false;
    }
}
