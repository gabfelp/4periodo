/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t2.gabriel.grafo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Gabriel
 */
public class Grafo {

    ArrayList<Vertice> Vertices = new ArrayList<Vertice>();
    int numeroLinhas = 0, numeroColunas = 0;

    //Definindo um vértice início e vários fins
    Vertice verticeInicial;
    ArrayList<Vertice> verticesFins = new ArrayList<Vertice>();
    ArrayList<ArrayList<Vertice>> menoresCaminhos = new ArrayList<ArrayList<Vertice>>();
    //Algoritmo de Dijkstra

    public void criaTudo(char[][] matriz) {
        numeroLinhas = matriz.length;
        //System.out.println("LINHAS" + numeroLinhas);
        numeroColunas = matriz[0].length;
        //System.out.println("COLUNAS" + numeroColunas);
        for (int i = 0; i < numeroLinhas; i++) {
            for (int j = 0; j < numeroColunas; j++) {
                Vertice v = new Vertice((i * numeroColunas + j), matriz[i][j]);
                Vertices.add(v);
            }
        }
        criaLigacoes();
    }

    public void criaLigacoes() {
        for (int i = 0; i < Vertices.size(); i++) {
            for (int j = 0; j < Vertices.size(); j++) {
                //USA ISSO PORQUE CASO ESTEJA NA ULTIMA COLUNA, NAO PODE PEGAR ESPACO A DIREITA
                if ((Vertices.get(j).chave == (Vertices.get(i).chave + 1)) && (((i + 1) % numeroColunas) != 0)) {
                    //CASO DO ESPAÇO A DIREITA DO VERTICE
                    criarLig(Vertices.get(i), Vertices.get(j));
                }
                //USA ISSO PORQUE CASO ESTEJA NA PRIMEIRA COLUNA, NAO PODE PEGAR ESPAÇO A ESQUERDA
                if ((Vertices.get(j).chave == (Vertices.get(i).chave - 1)) && ((i % numeroColunas) != 0)) {
                    //CASO DO ESPAÇO A ESQUERDA DO VERTICE
                    criarLig(Vertices.get(i), Vertices.get(j));
                }
                if (Vertices.get(j).chave == (Vertices.get(i).chave - numeroColunas)) {
                    //CASO DO ESPAÇO ACIMA DO VERTICE
                    criarLig(Vertices.get(i), Vertices.get(j));
                }
                if (Vertices.get(j).chave == (Vertices.get(i).chave + numeroColunas)) {
                    //CASO DO ESPAÇO ABAIXO DO VERTICE
                    criarLig(Vertices.get(i), Vertices.get(j));
                }
            }
        }
    }

    public void criarLig(Vertice v0, Vertice v1) {
        if ((!v0.parede) && (!v1.parede)) {
            v0.ligacoes.add(v1);
        }

    }

    // ENCONTRA MENOR CAMINHO COM PESO
    public ArrayList<Vertice> encontraMenorCaminho(Vertice v1, Vertice v2) {
        //CRIA VARIAVEIS P DJISKRA
        ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
        Vertice vCaminho = new Vertice();
        Vertice vAtual = new Vertice();
        Vertice vVizinho = new Vertice();
        ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();
        //
        menorCaminho.add(v1);
        for (int i = 0; i < Vertices.size(); i++) {
            //1º distancia zero e os outros 9999
            if (Vertices.get(i).chave == v1.chave) {
                Vertices.get(i).distancia = 0;
            } else {
                Vertices.get(i).distancia = 9999;
            }
            naoVisitados.add(Vertices.get(i));
        }
        Collections.sort(naoVisitados, new Comparator<Vertice>() {
            public int compare(Vertice v1, Vertice v2) {
                if (v1.distancia < v2.distancia) {
                    return -1;
                }
                if (v1.distancia == v2.distancia) {
                    return 0;
                }
                return 1;
            }
        });

        //visita todos vértices
        while (!naoVisitados.isEmpty()) {
            //pega o primeiro que está ordenado
            vAtual = naoVisitados.get(0);
            //System.out.println("Pega"+ vAtual.chave);
            for (int i = 0; i < vAtual.ligacoes.size(); i++) {
                vVizinho = vAtual.ligacoes.get(i);
                if (!vVizinho.visitado) {
                    if (vVizinho.distancia > vAtual.distancia + vAtual.ligacoes.get(i).peso) {
                        vVizinho.distancia = vAtual.distancia + vAtual.ligacoes.get(i).peso;
                        vVizinho.anterior = vAtual;
                        //se vizinho é o vertice procurado e foi feita alteração na distancia
                        if (vVizinho == v2) {
                            menorCaminho.clear();
                            vCaminho = vVizinho;
                            menorCaminho.add(vVizinho);
                            while (vCaminho.anterior != null) {
                                menorCaminho.add(vCaminho.anterior);
                                vCaminho = vCaminho.anterior;
                            }
                            //ordena do nó origem até o destino
                            Collections.sort(naoVisitados, new Comparator<Vertice>() {
                                public int compare(Vertice v1, Vertice v2) {
                                    if (v1.distancia < v2.distancia) {
                                        return -1;
                                    }
                                    if (v1.distancia == v2.distancia) {
                                        return 0;
                                    }
                                    return 1;
                                }
                            });

                        }
                    }

                }
            }
            //visitou o atual e tira da lista
            vAtual.visitado = true;
            naoVisitados.remove(vAtual);
            //OrdenaLista
            Collections.sort(naoVisitados, new Comparator<Vertice>() {
                public int compare(Vertice v1, Vertice v2) {
                    if (v1.distancia < v2.distancia) {
                        return -1;
                    }
                    if (v1.distancia == v2.distancia) {
                        return 0;
                    }
                    return 1;
                }
            });

        }
        return menorCaminho;
    }

    //ENCONTRA MENOR CAMINHO SEM PESO
    public ArrayList<Vertice> encontraMenorCaminhoSemPeso(Vertice v1, Vertice v2) {
        //CRIA VARIAVEIS P DJISKRA
        ArrayList<Vertice> menorCaminho = new ArrayList<Vertice>();
        Vertice vCaminho = new Vertice();
        Vertice vAtual = new Vertice();
        Vertice vVizinho = new Vertice();
        ArrayList<Vertice> naoVisitados = new ArrayList<Vertice>();
        //
        menorCaminho.add(v1);
        for (int i = 0; i < Vertices.size(); i++) {
            //1º distancia zero e os outros 9999
            if (Vertices.get(i).chave == v1.chave) {
                Vertices.get(i).distancia = 0;
            } else {
                Vertices.get(i).distancia = 9999;
            }
            naoVisitados.add(Vertices.get(i));
        }
        Collections.sort(naoVisitados, new Comparator<Vertice>() {
            public int compare(Vertice v1, Vertice v2) {
                if (v1.distancia < v2.distancia) {
                    return -1;
                }
                if (v1.distancia == v2.distancia) {
                    return 0;
                }
                return 1;
            }
        });

        //visita todos vértices
        while (!naoVisitados.isEmpty()) {
            //pega o primeiro que está ordenado
            vAtual = naoVisitados.get(0);
            //System.out.println("Pega"+ vAtual.chave);
            for (int i = 0; i < vAtual.ligacoes.size(); i++) {
                vVizinho = vAtual.ligacoes.get(i);
                if (!vVizinho.visitado) {
                    if (vVizinho.distancia > vAtual.distancia) {
                        vVizinho.distancia = vAtual.distancia;
                        vVizinho.anterior = vAtual;
                        //se vizinho é o vertice procurado e foi feita alteração na distancia
                        if (vVizinho == v2) {
                            menorCaminho.clear();
                            vCaminho = vVizinho;
                            menorCaminho.add(vVizinho);
                            while (vCaminho.anterior != null) {
                                menorCaminho.add(vCaminho.anterior);
                                vCaminho = vCaminho.anterior;
                            }
                            //ordena do nó origem até o destino
                            Collections.sort(naoVisitados, new Comparator<Vertice>() {
                                public int compare(Vertice v1, Vertice v2) {
                                    if (v1.distancia < v2.distancia) {
                                        return -1;
                                    }
                                    if (v1.distancia == v2.distancia) {
                                        return 0;
                                    }
                                    return 1;
                                }
                            });

                        }
                    }

                }
            }
            //visitou o atual e tira da lista
            vAtual.visitado = true;
            naoVisitados.remove(vAtual);
            //OrdenaLista
            Collections.sort(naoVisitados, new Comparator<Vertice>() {
                public int compare(Vertice v1, Vertice v2) {
                    if (v1.distancia < v2.distancia) {
                        return -1;
                    }
                    if (v1.distancia == v2.distancia) {
                        return 0;
                    }
                    return 1;
                }
            });

        }
        return menorCaminho;
    }

    public void defineFins() {
        for (int i = 0; i < Vertices.size(); i++) {
            if (Vertices.get(i).isFinal) {
                verticesFins.add(Vertices.get(i));
            }
            if (Vertices.get(i).isBegin) {
                verticeInicial = Vertices.get(i);
            }
        }
    }

    public ArrayList<ArrayList<Vertice>> achaMenoresCaminhos() {
        ArrayList<Vertice> caminhoEncontrado;
        for (int i = 0; i < verticesFins.size(); i++) {
            caminhoEncontrado = encontraMenorCaminho(verticeInicial, verticesFins.get(i));
            //VÊ se o caminho gerado possui como primeiro nó um final ( se chegou em uma saída )
            if (caminhoEncontrado.get(0).isFinal) {
                menoresCaminhos.add(caminhoEncontrado);
            }
            //VOLTA OS VALORES DOS VÉRTICES AOS INICIAIS, ANTES DE RODAR DJISKRA
            reverte();
        }
        return menoresCaminhos;
    }

    public ArrayList<ArrayList<Vertice>> achaMenoresCaminhosSemPeso() {
        ArrayList<Vertice> caminhoEncontrado;
        for (int i = 0; i < verticesFins.size(); i++) {
            caminhoEncontrado = encontraMenorCaminhoSemPeso(verticeInicial, verticesFins.get(i));
            //VÊ se o caminho gerado possui como primeiro nó um final ( se chegou em uma saída )
            if(caminhoEncontrado.get(0).isFinal){
            menoresCaminhos.add(caminhoEncontrado);
            }
            //VOLTA OS VALORES DOS VÉRTICES AOS INICIAIS, ANTES DE RODAR DJISKRA
            reverte();
        }
        return menoresCaminhos;
    }

    public void reverte() {
        // SERVE PARA VOLTAR AS CONDIÇÕES INICIAIS E RODAR DE NOVO DJISKRA 
        for (int i = 0; i < Vertices.size(); i++) {
            Vertices.get(i).distancia = 0;
            Vertices.get(i).visitado = false;
            Vertices.get(i).anterior = null;
        }
    }

    public void imprimirGrafo() {
        for (int i = 0; i < Vertices.size(); i++) {
            System.out.println(Vertices.get(i).chave + " Peso= " + Vertices.get(i).peso);
        }
    }

    public static ArrayList<Vertice> caminhoPeso(ArrayList<ArrayList<Vertice>> caminhos) {
        int[] pesos = new int[caminhos.size()];
        int soma;
        int indiceMenorPeso = 0, menorPeso = 999;
        ArrayList<Vertice> caminhoMenor = null;
        for (int i = 0; i < caminhos.size(); i++) {
            soma = 0;
            for (int j = 0; j < caminhos.get(i).size(); j++) {
                soma = soma + caminhos.get(i).get(j).peso;
            }
            //O PESO NA POSIÇÃO I SE REFERE AO CAMINHO NA POSIÇÃO I
            pesos[i] = soma;
        }
        // O VETOR DE PESO É RODADO PARA QUE SE ENCONTRE O MENOR PESO E SEU INDICE REFERENTE
        for (int k = 0; k < pesos.length; k++) {
            if (k == 0) {
                menorPeso = pesos[k];
                indiceMenorPeso = k;
            } else {
                if (menorPeso > pesos[k]) {
                    menorPeso = pesos[k];
                    indiceMenorPeso = k;
                }
            }
        }
        //COMO O PESO E OS CAMINHOS ESTÃO COM INDICES IDENTICOS, É RETORNADO O CAMINHO DE MENOR PESO
        caminhoMenor = caminhos.get(indiceMenorPeso);
        return caminhoMenor;
    }

    public void imprimirMatrizChaves() {
        int cont = 0;
        for (int i = 0; i < numeroLinhas; i++) {
            for (int k = 0; k < numeroColunas; k++) {
                if (cont < 10) {
                    System.out.print(cont + "  ");
                } else {
                    System.out.print(cont + " ");
                }
                cont++;
            }
            System.out.println("");
        }
    }

}
