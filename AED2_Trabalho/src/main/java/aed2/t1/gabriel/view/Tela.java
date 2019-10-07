/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aed2.t1.gabriel.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import aed2.t1.gabriel.avl.No;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

/**
 *
 * @author Gabriel
 */
public class Tela extends JFrame {

    private static int t = 600;
    private String estrutura;
    private No raiz;
    private int alturaArvore;
    public static int contadorLegenda = 0;

    public Tela(String estrutura, No raiz, int alturaArvore) {
        this.estrutura = estrutura;
        this.raiz = raiz;
        this.alturaArvore = alturaArvore;
        initGUI();
    }

    public void initGUI() {
        Desenhando panel = new Desenhando(raiz,alturaArvore);
        this.setSize(1040, 700);
        this.setTitle("Visualização da Estrutura");
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.setBounds(0, 100, 1024, 600);
        this.add(panel);
        this.setVisible(true);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        Font font = new Font("Century", Font.PLAIN, 25);
        g.setFont(font);
        g.drawString(estrutura, 512, 75);
    }

    public static void main(String[] args) {
        new Tela("AVL", new No("sda"),1);
    }

    public class Desenhando extends JPanel {

        public No no;
        public int alturaArv;

        public Desenhando(No no, int alturaArv) {
            this.no = no;
            this.alturaArv = alturaArv;
        }

        @Override
        protected void paintComponent(Graphics g) {
            g.setFont(new Font("Century", Font.BOLD, 15));
            int largura = getWidth();
            int altura = getHeight();
            pintar(g, 0, largura, 0, altura / alturaArv, no);
        }

        public void pintar(Graphics g, int larguraInicial, int larguraFinal, int alturaInicial, int Nivel, No no) {
            //contadorLegenda++;
            //String valor = contadorLegenda + "";
            String valor = no.valor;
            FontMetrics fm = g.getFontMetrics();
            int larguraValor = fm.stringWidth(valor);
            g.drawRect(((larguraInicial - 18 + larguraFinal) / 2 - (larguraValor / 2)), alturaInicial - 28 + (Nivel / 2), 100, 40);
            g.drawString(valor, (larguraInicial + larguraFinal) / 2 - (larguraValor / 2), alturaInicial + (Nivel / 2));
            if (no.esquerda != null && no.direita != null) {
                g.drawLine((larguraInicial + 18 + larguraFinal) / 2 - larguraValor / 2, alturaInicial + 12 + Nivel / 2, (larguraInicial + ((larguraInicial + larguraFinal) / 2)) / 2, (alturaInicial - 28 + Nivel) + Nivel / 2);
                g.drawLine((larguraInicial + 18 + larguraFinal) / 2 - larguraValor / 2, alturaInicial + 12 + Nivel / 2, (((larguraInicial + larguraFinal) / 2) + larguraFinal) / 2 - larguraValor / 2, (alturaInicial - 28 + Nivel) + Nivel / 2);
            }
            if (no.esquerda != null) {
                g.drawLine((larguraInicial + 18 + larguraFinal) / 2 - larguraValor / 2, alturaInicial + 12 + Nivel / 2, (larguraInicial + ((larguraInicial + larguraFinal) / 2)) / 2, (alturaInicial - 28 + Nivel) + Nivel / 2);
                pintar(g, larguraInicial, (larguraInicial + larguraFinal) / 2, alturaInicial + Nivel, Nivel, no.esquerda);
            }
            if (no.direita != null) {
                g.drawLine((larguraInicial + 18 + larguraFinal) / 2 - larguraValor / 2, alturaInicial + 12 + Nivel / 2, (((larguraInicial + larguraFinal) / 2) + larguraFinal) / 2 - larguraValor / 2, (alturaInicial - 28 + Nivel) + Nivel / 2);
                pintar(g, (larguraInicial + larguraFinal) / 2, larguraFinal, alturaInicial + Nivel, Nivel, no.direita);
            }
        }

    }

    public class JDesenho extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            this.setBackground(Color.white);
            Graphics2D g2d = (Graphics2D) g;

            pintarNo(g2d, 512, 50, raiz, alturaArvore);
        }

        public void pintar(Graphics2D g2d, double x, double y, int rot, double width, int elevado) {
            Graphics2D g = g2d;
            if (rot < 7) {
                g.setColor(Color.black);
                g.fillRect((int) x, (int) y, 4, 4);
                rot++;
                elevado--;
                double xesq = x + (Math.pow(2, elevado));
                y = y + 50;
                double xdir = x - (Math.pow(2, elevado));
                //x = 1.5*x;
                pintar(g2d, xesq, y, rot, 1024, elevado);
                pintar(g2d, xdir, y, rot, 1024, elevado);
                //pintar(g2d,xesq,y+50,rot++,width);
            }
        }

        public void pintarNo(Graphics2D g2d, double x, double y, No no, int elevado) {
            Graphics2D g = g2d;
            Graphics g1 = g2d;
            g.setColor(Color.black);
            g.fillRect((int) x, (int) y, 4, 4);
            contadorLegenda++;
            g.drawString(estrutura, t, t);
            g.drawString((contadorLegenda + ""), (int) x, (int) (y - 5));

            elevado--;
            double xesq = x + (Math.pow(2, elevado));
            y = y + 50;
            double xdir = x - (Math.pow(2, elevado));
            //x = 1.5*x;
            System.out.println(contadorLegenda + " - " + no.valor);
            if (no.esquerda != null) {
                pintarNo(g2d, xesq, y, x.esquerda, elevado);
            }
            if (no.direita != null) {
                pintar(g2d, xdir, y, x.direita, elevado);
            }
            //pintar(g2d,xesq,y+50,rot++,width);

        }
    }
}
