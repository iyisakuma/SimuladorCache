package br.com.aoc2.cache.simulator.util;

import br.com.aoc2.cache.simulator.*;

import java.io.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    public static void processaDados(Cache cache, String nomeArquivo) {
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream("./src/main/resources/enderecos/" + nomeArquivo));
            byte[] bytes = new byte[4];
            //Ler ate o final do arquivo
            while ((in.read(bytes)) != -1) {
                var enderecoMemoriaBits = Util.arrayBytesToBits(bytes);
                var hit = cache.contem(enderecoMemoriaBits);
                if (!hit) {
                    cache.tratamentoFalta(enderecoMemoriaBits);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, "Arquivo não encontrado!");
        } finally {
            Util.fechaFluxoDados(in);
        }
    }

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

        /*
         * Calculo para encontrar quantos bits do endereco eh utilizado para offset
         * */
        int bitsOffSet = (int) (Math.log(tamanhoBloco) / Math.log(2));

        /*
         * Calculo para encontrar quantos bits do endereco eh utilizado para o indice
         * */
        int bitsIndice = (int) (Math.log(nroConjuntos) / Math.log(2));

        /*
         * Calculo para encontrar quantos bits do endereco eh utilizado para a tag
         * */
        int bitsTag = 32 - (bitsIndice + bitsOffSet);
        return new Cache(nroConjuntos, grauAssociatividade, bitsTag, tamanhoBloco, politicaSubstituicao);
    }



    public static int parseBinarieToDecimal(String bits) {
        int contador = 0;
        int j = 0;
        for (int i = bits.length() - 1; i >= 0; i--) {
            if (bits.charAt(i) == '1') {
                contador += Math.pow(2.0, j);
            }
            j++;
        }
        return contador;
    }

    public static int calculaIndiceCache(String enderecoBinario, int nroConjuntos, int bSize){
        int enderecoDecimal = parseBinarieToDecimal(enderecoBinario)/bSize;
        var indiceCache = enderecoDecimal % (nroConjuntos);
        return indiceCache;
    }

    public static int calculaNroBloco(Conjunto conjunto) {
        return Arrays.stream(conjunto.getBlocos()).
                filter(bloco -> bloco.isValido())
                .toList().size();
    }


}
