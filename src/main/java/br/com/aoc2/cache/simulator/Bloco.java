package br.com.aoc2.cache.simulator;

import java.util.ArrayList;
import java.util.List;

public class Bloco {
    //Bit de validade
    private boolean valido = false;
    private String tag="";
    private List<String> infos= new ArrayList<>();

    private int tamanho;

    public Bloco(int tamanhoBloco) {
        this.tamanho = tamanhoBloco;
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

    public void addInfo(int info){
        int aux = this.tamanho;
        int contador = 0;
        while (aux > 0) {
            int vizinho = info +  contador;
            this.infos.add(String.valueOf(vizinho));
            aux--;
            contador++;
        }
    }

    public boolean contem(String info){
        return infos.contains(info);
    }

    public boolean isInfoVazia(){
        return infos.isEmpty();
    }



}
