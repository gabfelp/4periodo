/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t1.gabriel.abb;

import aed2.t1.gabriel.avl.No;
import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class Arvore {

    public int compAbb = 0;
    public No raiz;

    public No inserir(No comp, No ins) {
        if (comp == null) {
            raiz = ins;
            return ins;
        } else {
            if (comp.valor.compareTo(ins.valor) > 0) {
                if (comp.esquerda == null) {
                    comp.esquerda = ins;
                    ins.pai = comp;
                } else {
                    inserir(comp.esquerda, ins);
                }
            } else {
                if (comp.valor.compareTo(ins.valor) < 0) {
                    if (comp.direita == null) {
                        comp.direita = ins;
                        ins.pai = comp;
                    } else {
                        inserir(comp.direita, ins);
                    }
                } else {
                    if (comp.valor.compareTo(ins.valor) == 0) {
                        comp.frequencia++;
                        System.out.println("No já existente");
                    }
                }
            }
        }
        //NUMERO DE COMPARAÇÔES
        compAbb++;
        return ins;
    }

    public No buscar(String valor) {
        //Iterativo
        No x = raiz;
        while (x != null) {
            //vê se o valor é igual ao passado
            if (x.valor.compareTo(valor) == 0) {
                return x;
            } else {
                if (x.valor.compareTo(valor) > 0) {
                    x = x.esquerda;
                    continue;
                }
                if (x.valor.compareTo(valor) < 0) {
                    x = x.direita;
                }
            }

        }
        return null;
    }

    public void inserirLista(ArrayList<String> palavras) {
        for (int i = 0; i < palavras.size(); i++) {
            No n = new No(palavras.get(i));
            inserir(raiz, n);
        }
    }
    public int alturaRecur(No no) {
        if (no == null) {
            return -1;
        }
        int esq = 0, dir = 0;
        if (no.esquerda != null) {
            esq = alturaRecur(no.esquerda) + 1;
        }
        if (no.direita != null) {
            dir = alturaRecur(no.direita) + 1;
        }

        if (esq > dir) {
            //System.out.println(esq);
            return esq;
        } else {
            //System.out.println(dir);
            return dir;
        }

    }
}
