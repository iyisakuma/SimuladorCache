package br.com.aoc2.cache.simulator.util;

import br.com.aoc2.cache.simulator.*;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    public static boolean comandoDeLinhaEhValido(String[] args) {
        return args.length == 6;
    }

    public static void fechaFluxoDados(InputStream in) {
        if (in != null) {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.getMessage());
            }
        }
    }

    public static String arrayBytesToBits(byte[] bytes) {
        StringBuilder bits = new StringBuilder("");
        for (byte b : bytes) {
            int i = Byte.toUnsignedInt(b);
            bits.append(i == 0 ? "00000000" : String.format("%8s", Integer.toBinaryString(i)).replace(" ", "0"));
        }
        return bits.toString();
    }

    public static Cache criaCache(String[] args) {
        int nroConjuntos = Integer.parseInt(args[0]);
        int tamanhoBloco = Integer.parseInt(args[1]);
        int grauAssociatividade = Integer.parseInt(args[2]);
        var politicaSubstituicao = PoliticaSubstituicao.valor(args[3]);
        var conjunto = criaConjuntoParaCache(tamanhoBloco, grauAssociatividade, nroConjuntos);
        return new Cache(nroConjuntos, conjunto, politicaSubstituicao);
    }

    public static String processaDados(Cache cache, String nomeArquivo, boolean saidaPadrao) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("./src/main/resources/enderecos/" + nomeArquivo));
            byte[] bytes = new byte[4];
            while ((in.read(bytes)) != -1) {
                System.out.println(Util.arrayBytesToBits(bytes));
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Arquivo não encontrado!");
        } finally {
            Util.fechaFluxoDados(in);
        }
        return "";
    }

    private static Conjunto criaConjuntoParaCache(int tamanhoBloco, int grauAssociatividade, int nroConjuntos) {
        //Calculo de quantidades de palavras de 4bytes (32 bits)
        int qntPalavras = tamanhoBloco/4;
        int bitsOffSet = (int)(Math.log(qntPalavras) / Math.log(2));
        int bitsIndice = (int)(Math.log(nroConjuntos) / Math.log(2));
        int bitsTag = 32 - (bitsIndice + bitsOffSet);
        var bloco = new Bloco(qntPalavras, bitsTag, bitsIndice, bitsOffSet);
        return new Conjunto(grauAssociatividade, bloco);
    }
}
