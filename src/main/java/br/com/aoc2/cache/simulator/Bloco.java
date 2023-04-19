package br.com.aoc2.cache.simulator;

public class Bloco implements Cloneable {
    //Bit de validade
    private boolean valido = false;
    private String tag="";
    private String info="";

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
