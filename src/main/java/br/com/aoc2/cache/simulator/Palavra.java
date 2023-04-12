package br.com.aoc2.cache.simulator;

public class Palavra {
    //Como é um simulador, sera guardado o indice.
    private String info;

    public Palavra(String info) {
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
