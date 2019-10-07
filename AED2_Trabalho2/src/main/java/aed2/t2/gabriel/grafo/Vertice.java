/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t2.gabriel.grafo;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Vertice {

    ArrayList<Vertice> ligacoes = new ArrayList<>();
    public int chave;
    public int peso;
    public int distancia;
    public boolean visitado;
    public Vertice anterior;
    public boolean isFinal;
    public boolean isBegin;
    public boolean parede;

    public Vertice(int chave) {
        this.chave = chave;
        this.peso = peso;
    }

    public Vertice() {

    }

    public Vertice(int chave, char peso) {
        this.chave = chave;
        if (peso == '!') {
            this.peso=0;
            this.isBegin=true;
        } else {
            if (peso == '?') {
                this.peso=0;
                this.isFinal=true;
            } else {
                if (peso == '#') {
                    this.peso=10;
                    this.parede = true;
                }else{
                    this.peso = Integer.parseInt(peso+"");
                }
            }
        }
    }

}
