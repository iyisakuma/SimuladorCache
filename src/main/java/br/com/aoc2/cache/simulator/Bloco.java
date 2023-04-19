package br.com.aoc2.cache.simulator;

public class Bloco implements Cloneable {
    //Bit de validade
    private boolean valido = false;
    private String tag;
    private Palavra[] palavras;

    public Bloco(int qntPalavras) {
        this.palavras = new Palavra[qntPalavras];
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

    public Palavra[] getPalavras() {
        return palavras;
    }

    public void setPalavras(Palavra[] palavras) {
        this.palavras = palavras;
    }


    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
