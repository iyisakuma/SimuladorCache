package br.com.aoc2.cache.simulator;

public class Bloco implements Cloneable {
    //Bit de validade
    private boolean valido = false;
    private String tag;
    private int bitsTag;
    private Palavra[] palavra;
    private int bitsIndice;
    private int bitsOffSet;

    public Bloco(int qntPalavras, int bitsTag, int bitsIndice, int bitsOffSet) {
        var palavras = new Palavra[qntPalavras];
        this.bitsTag = bitsTag;
        this.bitsIndice = bitsIndice;
        this.bitsOffSet = bitsOffSet;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
