package gessyca;

class Arquivo {
    String nome;
    int dataUltimoAcesso;
    Arquivo esquerda;
    Arquivo direita;

    Arquivo(String nome, int dataUltimoAcesso) {
        this.nome = nome;
        this.dataUltimoAcesso = dataUltimoAcesso;
        this.esquerda = null;
        this.direita = null;
    }
}

class ArvoreArquivos {
    Arquivo raiz;

    ArvoreArquivos() {
        this.raiz = null;
    }


    void inserir(String nome, int dataUltimoAcesso) {
        raiz = inserirRecursivo(raiz, nome, dataUltimoAcesso);
    }

    Arquivo inserirRecursivo(Arquivo atual, String nome, int dataUltimoAcesso) {
        if (atual == null) {
            return new Arquivo(nome, dataUltimoAcesso);
        }

        if (nome.compareTo(atual.nome) < 0) {
            atual.esquerda = inserirRecursivo(atual.esquerda, nome, dataUltimoAcesso);
        } else if (nome.compareTo(atual.nome) > 0) {
            atual.direita = inserirRecursivo(atual.direita, nome, dataUltimoAcesso);
        }

        return atual;
    }


    void apagarAntigos(int dataLimite) {
        raiz = apagarAntigosRecursivo(raiz, dataLimite);
    }

    Arquivo apagarAntigosRecursivo(Arquivo atual, int dataLimite) {
        if (atual == null) {
            return null;
        }

        atual.esquerda = apagarAntigosRecursivo(atual.esquerda, dataLimite);
        atual.direita = apagarAntigosRecursivo(atual.direita, dataLimite);

        if (atual.dataUltimoAcesso < dataLimite) {
            if (atual.esquerda == null) {
                return atual.direita;
            } else if (atual.direita == null) {
                return atual.esquerda;
            }

            Arquivo sucessor = encontrarMinimo(atual.direita);
            atual.nome = sucessor.nome;
            atual.dataUltimoAcesso = sucessor.dataUltimoAcesso;
            atual.direita = apagarAntigosRecursivo(atual.direita, sucessor.dataUltimoAcesso);
        }

        return atual;
    }

    Arquivo encontrarMinimo(Arquivo atual) {
        while (atual.esquerda != null) {
            atual = atual.esquerda;
        }
        return atual;
    }


    void imprimirComoArvore() {
        imprimirArvoreRecursivo(raiz, "", true);
    }

    void imprimirArvoreRecursivo(Arquivo atual, String prefixo, boolean isTail) {
        if (atual != null) {
            System.out.println(prefixo + (isTail ? "└── " : "├── ") + atual.nome);
            imprimirArvoreRecursivo(atual.direita, prefixo + (isTail ? "    " : "│   "), false);
            imprimirArvoreRecursivo(atual.esquerda, prefixo + (isTail ? "    " : "│   "), true);
        }
    }
}

public class Lista1 {
    public static void main(String[] args) {
        ArvoreArquivos arvore = new ArvoreArquivos();


        arvore.inserir("A2022", 20220101);
        arvore.inserir("B2024", 20241231);
        arvore.inserir("C2024", 20240115);

        System.out.println("Árvore:");
        arvore.imprimirComoArvore();


        int dataLimite = 20240101;
        arvore.apagarAntigos(dataLimite);

        System.out.println("\nDepois de apagar arquivos antigos("+dataLimite+"): ");
        arvore.imprimirComoArvore();
    }
}

