/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t2.gabriel.view;

import aed2.t2.gabriel.grafo.Grafo;
import aed2.t2.gabriel.grafo.Vertice;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import jdk.jfr.events.FileReadEvent;

/**
 *
 * @author Gabriel
 */
public class Programa {

    public static void main(String[] args) {
        Grafo g = new Grafo();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo onde se encontra o labirinto");
        String nomeArq = sc.nextLine();
        //separa a entrada em um vetor de dados, contendo o nome do txt e a estrutura a ser utilizada
        //lê labirinto e guarda em uma matriz
        char[][] matriz = lerArquivo(nomeArq);

        imprimirMatriz(matriz);
        System.out.println("ACHAR: 1-COM PESO / 2-SEM PESO");
        int op = sc.nextInt();
        while (op != 1 && op != 2) {
            System.out.println("OPÇÃO INVÁLIDA, DIGITE 1 OU 2");
            op = sc.nextInt();
        }
        g.criaTudo(matriz);
        //g.imprimirGrafo();
        g.defineFins();
        if (op == 1) {
            ArrayList<ArrayList<Vertice>> caminhos = g.achaMenoresCaminhos();
            imprimeCaminhos(caminhos);
            g.imprimirMatrizChaves();
            imprimeCaminho(Grafo.caminhoPeso(caminhos));
        } else {
            if (op == 2) {
                ArrayList<ArrayList<Vertice>> caminhos = g.achaMenoresCaminhosSemPeso();
                imprimeCaminhos(caminhos);
                g.imprimirMatrizChaves();
                imprimeCaminhoSemPeso(Grafo.caminhoPeso(caminhos));
            }
        }
    }

    public static char[][] lerArquivo(String arquivo) {
        ArrayList<String> texto = new ArrayList<>();
        char[][] matriz = null;

        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader ler_arq = new BufferedReader(arq);
            String frase = ler_arq.readLine();
            while (frase != null) {
                texto.add(frase);
                frase = ler_arq.readLine();
            }
            arq.close();
            //a matriz labirinto será criada na dimensão numero de linhas x numero de colunas
            matriz = new char[texto.size()][texto.get(0).length()];
            for (int i = 0; i < texto.size(); i++) {
                for (int j = 0; j < texto.get(0).length(); j++) {
                    //guarda as letras do array de frases na matriz
                    matriz[i][j] = texto.get(i).charAt(j);
                }
            }
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
        }
        return matriz;
    }

    public static void imprimirMatriz(char[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + "");
            }
            System.out.println("");
        }
    }

    public static void imprimeCaminhos(ArrayList<ArrayList<Vertice>> caminhos) {
        for (int i = 0; i < caminhos.size(); i++) {
            System.out.println("CAMINHO " + i);
            for (int j = 0; j < caminhos.get(i).size(); j++) {
                System.out.println(caminhos.get(i).get(j).chave);
            }
            System.out.println("////////////////");
        }
    }
    
    public static void imprimeCaminhoSemPeso(ArrayList<Vertice> caminho) {
        System.out.println("CAMINHO SEM CONSIDERAR PESO : ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.println("CHAVE "+caminho.get(i).chave);
        }
    }
    public static void imprimeCaminho(ArrayList<Vertice> caminho) {
        System.out.println("CAMINHO COM MENOR PESO : ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.println("CHAVE "+caminho.get(i).chave);
        }
    }

}
