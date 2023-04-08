package br.com.aoc2.cache.simulator;

public class Conjunto implements Cloneable {
    private Bloco[] blocos;
    public Conjunto(int grauAssociatividade, Bloco bloco) {
        var blocos = new Bloco[grauAssociatividade];
        for (int i = 0 ; i < grauAssociatividade; i++){
            try {
                blocos[i] = (Bloco) bloco.clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
        this.blocos = blocos;
    }

    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
}
