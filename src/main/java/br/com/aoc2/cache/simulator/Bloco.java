package br.com.aoc2.cache.simulator;

public class Bloco {
    //Bit de validade
    private boolean valido = false;
    private String tag = "";

    public Bloco() {
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


}
