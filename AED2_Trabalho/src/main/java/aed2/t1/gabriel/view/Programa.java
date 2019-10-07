/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t1.gabriel.view;

import aed2.t1.gabriel.abb.Arvore;
import aed2.t1.gabriel.avl.ArvoreAvl;
import aed2.t1.gabriel.avl.No;
import aed2.t1.gabriel.pb.PesquisaBinaria;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;
import jdk.jfr.events.FileReadEvent;

/**
 *
 * @author Gabriel
 */
public class Programa {

    public static void main(String[] args) {
        double tabb, tavl, tpb;
        ArvoreAvl avl = new ArvoreAvl();
        Arvore abb = new Arvore();
        PesquisaBinaria pb = new PesquisaBinaria();
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo(entrada.txt) e a estrutura a ser utilizada(pesquisa_binaria/arvore_binaria/arvore_avl");
        String opcao = sc.nextLine();
        //separa a entrada em um vetor de dados, contendo o nome do txt e a estrutura a ser utilizada
        String[] dados = opcao.split(" ");
        //lê o arquivo e guarda em texto
        ArrayList<String> palavras = lerArquivo(dados[0]);
        //texto é uma lista de frases, logo vou criar uma lista de palavras
        String estrutura = dados[1];
        // INSERCAO NA ABB
        double tiabb = System.nanoTime();
        abb.inserirLista(palavras);
        double tfabb = System.nanoTime();
        tabb = tfabb - tiabb;// TEMPO DE EXECUCAO
        System.out.println(" TEMPO EXECUÇÃO DA ABB"+tabb);
        
        //INSERCAO AVL
        double tiavl = System.nanoTime();
        avl.inserirLista(palavras);
        double tfavl = System.nanoTime();
        tavl = tfavl - tiavl;
        System.out.println(" TEMPO EXECUÇÃO DA AVL"+tavl);
        
        //INSERCAO PESQUISA BINARIA
        double tipb = System.nanoTime();
        pb.inserirLista(palavras);
        double tfpb = System.nanoTime();
        tpb = tfpb - tipb;

        if (estrutura.equals("pesquisa_binaria")) {
            //System.out.println("pb");
        }
        if (estrutura.equals("arvore_binaria")) {
            //System.out.println("ab");
        }
        if (estrutura.equals("arvore_avl")) {
            //System.out.println("aa");
        }
        if ((tavl < tabb) && (tabb < tpb)) {
            System.out.println("Tempo AVL: " + tavl + " ns");
            System.out.println("Comparações AVL: " + avl.compAvl);
            System.out.println("Tempo ABB: " + tabb+ " ns");
            System.out.println("Comparações ABB: " + abb.compAbb);
            System.out.println("Tempo PB: " + tpb+ " ns");
            System.out.println("Comparações Pb: " + pb.compPb);
        }
        if ((tavl < tpb) && (tpb < tabb)) {
            System.out.println("Tempo AVL: " + tavl+ " ns");
            System.out.println("Comparações AVL: " + avl.compAvl);
            System.out.println("Tempo PB: " + tpb+ " ns");
            System.out.println("Comparações Pb: " + pb.compPb);
            System.out.println("Tempo ABB: " + tabb+ " ns");
            System.out.println("Comparações ABB: " + abb.compAbb);
        }
        if ((tpb < tavl) && (tavl < tabb)) {
            System.out.println("Tempo PB: " + tpb+ " ns");
            System.out.println("Comparações Pb: " + pb.compPb);
            System.out.println("Tempo AVL: " + tavl+ " ns");
            System.out.println("Comparações AVL: " + avl.compAvl);
            System.out.println("Tempo ABB: " + tabb+ " ns");
            System.out.println("Comparações ABB: " + abb.compAbb);
        }
        if ((tpb < tabb) && (tabb < tavl)) {
            System.out.println("Tempo PB: " + tpb+ " ns");
            System.out.println("Comparações Pb: " + pb.compPb);
            System.out.println("Tempo ABB: " + tabb+ " ns");
            System.out.println("Comparações ABB: " + abb.compAbb);
            System.out.println("Tempo AVL: " + tavl+ " ns");
            System.out.println("Comparações AVL: " + avl.compAvl);
        }
        if ((tabb < tpb) && (tpb < tavl)) {
            System.out.println("Tempo ABB: " + tabb+ " ns");
            System.out.println("Comparações ABB: " + abb.compAbb);
            System.out.println("Tempo PB: " + tpb+ " ns");
            System.out.println("Comparações Pb: " + pb.compPb);
            System.out.println("Tempo AVL: " + tavl+ " ns");
            System.out.println("Comparações AVL: " + avl.compAvl);
        }
        if ((tabb < tavl) && (tavl < tpb)) {
            System.out.println("Tempo ABB: " + tabb+ " ns");
            System.out.println("Comparações ABB: " + abb.compAbb);
            System.out.println("Tempo AVL: " + tavl+ " ns");
            System.out.println("Comparações AVL: " + avl.compAvl);
            System.out.println("Tempo PB: " + tpb+ " ns");
            System.out.println("Comparações Pb: " + pb.compPb);
        }
        System.out.println(" IMPRESSÃO ABB");
        imprimirArvore(abb.raiz, 1);
        System.out.println("///////");
        System.out.println(" IMPRESSÃO AVL");
        imprimirArvore(avl.raiz,1);
        System.out.println(" IMPRESSÃO PB");
        imprimirArvore(pb.raiz,1);
        System.out.println("PALAVRAS / FREQUÊNCIA");
        ArvoreAvl.erdRec(avl);
    }

    public static ArrayList<String> lerArquivo(String arquivo) {
        ArrayList<String> palavras = new ArrayList<>();
        String caracteres = " .!?,:'";
        try {
            FileReader arq = new FileReader(arquivo);
            BufferedReader ler_arq = new BufferedReader(arq);
            String frase = ler_arq.readLine();
            while (frase != null) {
                //String[] f = frase.split("[\\W][ ]");
                //USADO PARA DESCONSIDERAR OS SINAIS ACIMA
                String[] f=frase.split("["+Pattern.quote(caracteres)+"]");
                //adiciona cada palavra em uma lista de palavras
                for (int i = 0; i < f.length; i++) {
                    palavras.add(f[i].toLowerCase());
                    //Adiciona toda minúscula
                }
                frase = ler_arq.readLine();
            }
            arq.close();
        } catch (Exception e) {
            System.out.println("Arquivo não encontrado");
        }
        return palavras;
    }

    public static void imprimirArvore(No x, int nivel) {
        // FAZ A IMPRESSÃO RECURSIVA DA ÁRVORE
        System.out.println("NIVEL " + nivel + " VALOR " + x.valor);
        nivel++;
        if (x.direita != null) {
            imprimirArvore(x.direita, nivel);
        }
        if (x.esquerda != null) {
            imprimirArvore(x.esquerda, nivel);
        }

    }
}
