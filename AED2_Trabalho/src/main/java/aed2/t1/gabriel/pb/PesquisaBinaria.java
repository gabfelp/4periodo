/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t1.gabriel.pb;

import aed2.t1.gabriel.avl.No;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class PesquisaBinaria {
    public No raiz;
    public int compPb=0;
    No[] Nos;
    
    
    public void inserirLista(ArrayList<String> palavras){
        ArrayList<String> ordenado = ordenar(palavras);
        //SÓ PRA EVR SE É ORDENADO
        //imprimirPalavrasOrdenadas(ordenado);
        Nos = new No[ordenado.size()];
        int posiRaiz=ordenado.size()/2;
        No n=new No(ordenado.get(posiRaiz));
        Nos[posiRaiz]= n;
        raiz = n;
        Nos[posiRaiz].esquerda= criaNo((posiRaiz/2),n,ordenado);
        Nos[posiRaiz].direita= criaNo((posiRaiz/2)+(posiRaiz),n,ordenado);
        
    }
    public ArrayList<String> ordenar(ArrayList<String> palavras){
        String aux;
        //ORDENA A LISTA DE PALAVRAS
        for(int i=0;i<palavras.size();i++){
            for(int j=0;j<palavras.size()-1;j++){
                if(palavras.get(j).compareTo(palavras.get(j+1))>0){
                    aux = palavras.get(j+1);
                    palavras.set(j+1,palavras.get(j));
                    palavras.set(j,aux);
                }
            }
        }
        return palavras;
    }
    public No criaNo(int Posicao,No pai,ArrayList<String> ordenado){
        No n=new No(ordenado.get(Posicao));
        Nos[Posicao]= n;
        Nos[Posicao].pai=pai;
        if(((Posicao/2)>0)&&(Posicao>ordenado.size())){
         Nos[Posicao].esquerda = criaNo(Posicao/2,n,ordenado);   
         Nos[Posicao].direita = criaNo(((ordenado.size()-Posicao)/2)+Posicao,n,ordenado);   
        }
        compPb++;
        return Nos[Posicao];
    }
    public void imprimirPalavrasOrdenadas(ArrayList<String> ordenado){
        for(int i=0;i<ordenado.size();i++){
            System.out.println(ordenado.get(i)+" ");
        }
    }
}
