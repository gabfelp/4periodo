/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t1.gabriel.avl;

import java.util.ArrayList;

/**
 *
 * @author Gabriel
 */
public class ArvoreAvl {

    public int compAvl = 0;
    public No raiz;
    
    public static void erdRec(ArvoreAvl r) {

        if (r != null && r.raiz != null) {
            ArvoreAvl e = new ArvoreAvl();
            e.raiz = r.raiz.esquerda;
            erdRec(e);
            System.out.println(r.raiz.valor+" / "+r.raiz.frequencia);
            ArvoreAvl d = new ArvoreAvl();
            d.raiz = r.raiz.direita;
            erdRec(d);
        }
    }
    public No inserir(No comp, No ins) {
        if (comp == null) {
            raiz = ins;
            return ins;
        } else {
            if (comp.valor.compareTo(ins.valor) > 0) {
                if (comp.esquerda == null) {
                    comp.esquerda = ins;
                    ins.pai = comp;
                    // É CONFERIDO O BALANCEAMENTO DE TODOS NÓS ACIMA DO INSERIDO EXCETO ELE ( BALANCEAMENTO ZERO )
                    confereBalanceamento(comp);
                } else {
                    inserir(comp.esquerda, ins);
                }
            } else {
                if (comp.valor.compareTo(ins.valor) < 0) {
                    if (comp.direita == null) {
                        comp.direita = ins;
                        ins.pai = comp;
                        // É CONFERIDO O BALANCEAMENTO DE TODOS NÓS ACIMA DO INSERIDO EXCETO ELE ( BALANCEAMENTO ZERO )
                        confereBalanceamento(comp);
                    } else {
                        inserir(comp.direita, ins);
                    }
                } else {
                    if (comp.valor.compareTo(ins.valor) == 0) {
                        comp.frequencia++;
                        System.out.println("Nó já existente");
                    }
                }
            }
        }
        //NUMERO DE COMPARACOES
        compAvl++;
        return ins;
    }

    public void colocaParentesco(No x) {
        if (x.direita != null) {
            x.direita.pai = x;
            colocaParentesco(x.direita);
        }
        if (x.esquerda != null) {
            x.esquerda.pai = x;
            colocaParentesco(x.esquerda);
        }
    }

    public void confereBalanceamento(No atual) {
        atual.setBalanceamento(colocaBalanceamento(atual));
        int balanceamento = colocaBalanceamento(atual);

        if (balanceamento == -2) {
            if (alturaRecur(atual.esquerda.esquerda) >= alturaRecur(atual.esquerda.direita)) {
                //CASO -2 e -1
                atual = rotacaoDireita(atual);
            } else {
                //CASO -2 +1
                atual = rotacaoDuplaED(atual);
            }
        } else {
            if (balanceamento == 2) {
                if (alturaRecur(atual.direita.direita) >= alturaRecur(atual.direita.esquerda)) {
                    // CASO +2 +1
                    atual = rotacaoEsquerda(atual);
                } else {
                    //CASO +2 -1
                    atual = rotacaoDuplaDE(atual);
                }
            }
        }
        if (atual.pai != null) {
            //VAI RODANDO SUBINDO ATÈ CHEGAR a RAIZ
            confereBalanceamento(atual.pai);
        } else {
            //QUANDO O PAI É NULO, CHEGOU NA RAIZ, QUE VAI RECEBER O ATUAL
            raiz = atual;
        }
    }

    public No rotacaoEsquerda(No x) {
        No direita = x.direita;
        direita.pai = x.pai;

        x.direita = direita.esquerda;

        if (x.direita != null) {
            x.direita.pai = x;
        }

        direita.esquerda = x;
        x.pai = direita;

        if (direita.pai != null) {
            if (direita.pai.direita == x) {
                direita.pai.direita = direita;
            } else {
                if (direita.pai.esquerda == x) {
                    direita.pai.esquerda = direita;
                }
            }
        }
        colocaBalanceamento(x);
        colocaBalanceamento(direita);

        return direita;
    }

    public No rotacaoDireita(No x) {
        No esquerda = x.esquerda;
        esquerda.pai = x.pai;

        x.esquerda = esquerda.direita;
        if (x.esquerda != null) {
            x.esquerda.pai = x;
        }
        esquerda.direita = x;
        x.pai = esquerda;

        if (esquerda.pai != null) {
            if (esquerda.pai.direita == x) {
                esquerda.pai.direita = esquerda;
            } else {
                if (esquerda.pai.esquerda == x) {
                    esquerda.pai.esquerda = esquerda;
                }
            }
        }
        colocaBalanceamento(x);
        colocaBalanceamento(esquerda);

        return esquerda;
    }

    public No rotacaoDuplaED(No x) {
        x.esquerda = rotacaoEsquerda(x.esquerda);
        return rotacaoDireita(x);
    }

    public No rotacaoDuplaDE(No x) {
        x.direita = rotacaoDireita(x.direita);
        return rotacaoEsquerda(x);
    }

    public int colocaBalanceamento(No x) {
        return (alturaRecur(x.direita) - alturaRecur(x.esquerda));
    }

    public int alturaRecur(No no) {
        if (no == null) {
            return 0;
        }
        int esq = 0, dir = 0;
        if (no.esquerda != null) {
            esq = alturaRecur(no.esquerda) + 1;
        }
        if (no.direita != null) {
            dir = alturaRecur(no.direita) + 1;
        }
        if (dir > esq) {
            return dir;
        } else {
            return esq;
        }
    }

    public void inserirLista(ArrayList<String> palavras) {
        for (int i = 0; i < palavras.size(); i++) {
            No n = new No(palavras.get(i));
            inserir(raiz, n);
        }
    }
    public int alturaRecur(No no) {
        if (no == null) {
            return 0;
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
