package br.com.aoc2.cache.simulator;

import br.com.aoc2.cache.simulator.util.Util;

import java.util.Arrays;
import java.util.List;

import static br.com.aoc2.cache.simulator.TipoEnderecamento.*;

public class Conjunto {
    private Bloco[] blocos;


    public Conjunto(Bloco[] blocos) {
        this.blocos = blocos;
    }

    public Bloco[] getBlocos() {
        return blocos;
    }


    public Miss contem(String enderecoBinario, int bitTag, TipoEnderecamento tipo) {
        Miss miss = null;
        for (var bloco : blocos) {
            if (bloco.isValido()) {
                String tag = enderecoBinario.substring(0, bitTag);
                if (tag.equals(bloco.getTag())) {
                    return Miss.NAO_HOUVE;
                } else {
                    if(tipo == MAPEAMENTO_DIRETO){
                        miss = Miss.CONFLITO;
                    }else if(tipo == TOTALMENTE_ASSOCIATIVO){
                        miss =  Miss.CAPACIDADE;
                    }else {
                        miss =  temBlocoInvalido() ? Miss.COMPULSORIO: Miss.CONFLITO;
                    }
                }
            }
        }
        return miss == null ? Miss.COMPULSORIO : miss;
    }
    public boolean temBlocoInvalido(){
        List<Bloco> listaBloco = Arrays.stream(blocos).
                filter(bloco -> !bloco.isValido()).
                toList();
        return !listaBloco.isEmpty();
    }
}
