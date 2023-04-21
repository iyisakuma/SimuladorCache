package br.com.aoc2.cache.simulator;

import static br.com.aoc2.cache.simulator.util.Util.*;

import java.io.*;
public class Main {
    public static void main(String[] args) {
        args = "1 4 32 R 1 vortex.in.sem.persons.bin".split(" ");
        if (comandoDeLinhaEhValido(args)) {
            var cache = criaCache(args);
            boolean saidaPadrao = args[4].equals("1");
            var nomeArquivo = args[5];
            processaDados(cache, nomeArquivo);
            System.out.println(cache.mostraResultado(saidaPadrao));
        } else {
            System.out.println("O comando de linha não é válido.");
        }

    }
}