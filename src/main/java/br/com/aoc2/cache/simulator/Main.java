package br.com.aoc2.cache.simulator;

import static br.com.aoc2.cache.simulator.util.Util.*;

import java.io.*;
public class Main {
    public static void main(String[] args) {
        if (comandoDeLinhaEhValido(args)) {
            var cache = criaCache(args);
            boolean isSaidaPadrao = args[4].equals("1");
            var nomeArquivo = args[5];
            var resultado = processaDados(cache, nomeArquivo, isSaidaPadrao);
            System.out.println(resultado);
        } else {
            System.out.println("O comando de linha não é válido.");
        }

    }
}