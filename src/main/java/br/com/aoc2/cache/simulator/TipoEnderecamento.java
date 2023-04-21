package br.com.aoc2.cache.simulator;

public enum TipoEnderecamento {
    MAPEAMENTO_DIRETO, TOTALMENTE_ASSOCIATIVO, CONJUNTO_ASSOCIATIVO;

    public static TipoEnderecamento type(Cache cache){
        int numeroConjutos = cache.getConjuntos().length;
        int numeroBloco = cache.getConjuntos()[0].getBlocos().length;
        if(numeroBloco == 1){
            return MAPEAMENTO_DIRETO;
        }else if(numeroBloco == numeroConjutos){
            return TOTALMENTE_ASSOCIATIVO;
        }else{
            return CONJUNTO_ASSOCIATIVO;
        }
    }
}
