/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t1.gabriel.avl;

/**
 *
 * @author Gabriel
 */
public class No {
    public No esquerda;
    public No direita;
    public No pai;
    public String valor;
    private int balanceamento;
    public int frequencia;
    
    public No(String valor){
        this.valor = valor;
        this.frequencia=1;
        this.balanceamento =0;
    }

    public int getBalanceamento() {
        return balanceamento;
    }

    public void setBalanceamento(int balanceamento) {
        this.balanceamento = balanceamento;
    }
    public int alturaRecur() {
        if (this == null) {
            return 0;
        }
        int esq = 0, dir = 0;
        if (this.esquerda != null) {
            esq = this.esquerda.alturaRecur() + 1;
        }
        if (this.direita != null) {
            dir = this.direita.alturaRecur() + 1;
        }
        if(dir>esq){
            return dir;
        }else{
            return esq;
        }
    }
    
}
