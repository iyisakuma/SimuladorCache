package br.com.aoc2.cache.simulator;

public class Bloco implements Cloneable {
    //Bit de validade
    private boolean valido = false;
    private String tag;
    private int bitsTag;
    private Palavra[] palavras;
    private int bitsIndice;
    private int bitsOffSet;

    public Bloco(int qntPalavras, int bitsTag, int bitsIndice, int bitsOffSet) {
        this.palavras = new Palavra[qntPalavras];
        this.bitsTag = bitsTag;
        this.bitsIndice = bitsIndice;
        this.bitsOffSet = bitsOffSet;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getBitsTag() {
        return bitsTag;
    }

    public void setBitsTag(int bitsTag) {
        this.bitsTag = bitsTag;
    }

    public Palavra[] getPalavras() {
        return palavras;
    }

    public void setPalavras(Palavra[] palavras) {
        this.palavras = palavras;
    }

    public int getBitsIndice() {
        return bitsIndice;
    }

    public void setBitsIndice(int bitsIndice) {
        this.bitsIndice = bitsIndice;
    }

    public int getBitsOffSet() {
        return bitsOffSet;
    }

    public void setBitsOffSet(int bitsOffSet) {
        this.bitsOffSet = bitsOffSet;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
