package br.com.aoc2.cache.simulator;

public enum PoliticaSubstituicao {
    RANDOM("r"), FIFO("f"), LRU("l");

    private  String sigla;

    PoliticaSubstituicao(String sigla){
        this.sigla = sigla;
    }

    public static PoliticaSubstituicao valor(String sigla) throws IllegalArgumentException{
        switch (sigla.toLowerCase()){
            case "r":
                return RANDOM;
            case "f":
                return FIFO;
            case "l":
                return LRU;
        }
        throw new IllegalArgumentException("Política de substituição inválida.");
    }
}
