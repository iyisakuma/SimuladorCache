package br.com.aoc2.cache.simulator;

import java.io.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) throws IOException {
        if (comandoDeLinhaEhValido(args)) {
            int nroConjuntos = Integer.parseInt(args[0]);
            byte tamanhoBloco = Byte.parseByte(args[1]);
            int grauAssociatividade = Integer.parseInt(args[2]);
            var politicaSubstituicao = PoliticaSubstituicao.valor(args[3]);
            boolean saidaPadrao = args[4].equals("1");
            String nomeArquivo = args[5];
            InputStream in = null;
            try {
                in = new BufferedInputStream(new FileInputStream("./src/main/resources/enderecos/" + nomeArquivo));
                byte[] bytes = new byte[4];
                while ((in.read(bytes)) != -1) {
                    System.out.println(arrayBytesToBits(bytes));
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Arquivo não encontrado!");
            } finally {
                fechaFluxoDados(in);
            }
        } else {
            System.out.println("O comando de linha não é válido.");
        }

    }

    private static boolean comandoDeLinhaEhValido(String[] args) {
        return args.length == 6;
    }

    private static void fechaFluxoDados(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    private static String arrayBytesToBits(byte[] bytes) {
        StringBuilder bits = new StringBuilder("");
        for (byte b : bytes) {
            int i = Byte.toUnsignedInt(b);
            bits.append(i == 0 ? "00000000" : String.format("%8s", Integer.toBinaryString(i)).replace(" ", "0"));
        }
        return bits.toString();
    }
}