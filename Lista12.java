package gessyca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class No {
    int chave;
    int frequencia;
    No esquerda, direita;

    public No(int chave) {
        this.chave = chave;
        this.frequencia = 0;
        esquerda = direita = null;
    }
}

class ArvoreBinariaDeBusca {
    No raiz;

    public void inserir(int chave) {
        raiz = inserirRec(raiz, chave);
    }

    private No inserirRec(No raiz, int chave) {
        if (raiz == null) {
            raiz = new No(chave);
            return raiz;
        }
        if (chave < raiz.chave)
            raiz.esquerda = inserirRec(raiz.esquerda, chave);
        else if (chave > raiz.chave)
            raiz.direita = inserirRec(raiz.direita, chave);

        return raiz;
    }

    public void incrementarFrequencia(int chave) {
        No no = buscar(raiz, chave);
        if (no != null) {
            no.frequencia++;
        }
    }

    private No buscar(No raiz, int chave) {
        if (raiz == null || raiz.chave == chave)
            return raiz;
        if (raiz.chave > chave)
            return buscar(raiz.esquerda, chave);
        return buscar(raiz.direita, chave);
    }

    public void reorganizar() {
        List<No> nos = new ArrayList<>();
        percursoEmOrdem(raiz, nos);

        Collections.sort(nos, new Comparator<No>() {
            @Override
            public int compare(No n1, No n2) {
                return n2.frequencia - n1.frequencia;
            }
        });

        raiz = null;
        for (No no : nos) {
            inserir(no.chave);
        }
    }

    private void percursoEmOrdem(No raiz, List<No> nos) {
        if (raiz != null) {
            percursoEmOrdem(raiz.esquerda, nos);
            nos.add(raiz);
            percursoEmOrdem(raiz.direita, nos);
        }
    }

    public void imprimirArvore() {
        imprimirArvoreRec(raiz, "", true);
    }

    private void imprimirArvoreRec(No no, String prefixo, boolean isLeft) {
        if (no != null) {
            System.out.println(prefixo + (isLeft ? "├── " : "└── ") + no.chave);
            imprimirArvoreRec(no.esquerda, prefixo + (isLeft ? "│   " : "    "), true);
            imprimirArvoreRec(no.direita, prefixo + (isLeft ? "│   " : "    "), false);
        }
    }
}

public class Lista12 {
    public static void main(String[] args) {
        ArvoreBinariaDeBusca arvore = new ArvoreBinariaDeBusca();

        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(70);
        arvore.inserir(60);
        arvore.inserir(80);

        arvore.incrementarFrequencia(50);
        arvore.incrementarFrequencia(30);
        arvore.incrementarFrequencia(30);
        arvore.incrementarFrequencia(20);
        arvore.incrementarFrequencia(70);
        arvore.incrementarFrequencia(70);
        arvore.incrementarFrequencia(70);

        System.out.println("Árvore original:");
        arvore.imprimirArvore();

        arvore.reorganizar();

        System.out.println("Árvore reorganizada:");
        arvore.imprimirArvore();
    }
}
