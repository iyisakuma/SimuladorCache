package br.com.aoc2.cache.simulator;

public class Bloco {
    private boolean bitValidade = false;
    private String tag;
    //Como é um simulador, sera guardado o indice.
    private String info;

    public boolean isBitValidade() {
        return bitValidade;
    }

    public void setBitValidade(boolean bitValidade) {
        this.bitValidade = bitValidade;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
