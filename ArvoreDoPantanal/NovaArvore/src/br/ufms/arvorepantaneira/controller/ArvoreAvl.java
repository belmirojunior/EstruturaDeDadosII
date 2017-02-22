package br.ufms.arvorepantaneira.controller;

import br.ufms.arvorepantaneira.model.Animal;

public class ArvoreAvl {

    public  int[] abVetor;
    protected Animal raiz;

    public void inserir(String nome, int idade, String sexo, String cor) {
        Animal n = new Animal(nome, idade, sexo, cor);
        inserirAVL(this.raiz, n);
    }

    public void inserirAVL(Animal aComparar, Animal aInserir) {

        if (aComparar == null) {
            this.raiz = aInserir;

        } else {

            if (aInserir.getChave() < aComparar.getChave()) {

                if (aComparar.getEsquerda() == null) {
                    aComparar.setEsquerda(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);

                } else {
                    inserirAVL(aComparar.getEsquerda(), aInserir);
                }

            } else if (aInserir.getChave() > aComparar.getChave()) {

                if (aComparar.getDireita() == null) {
                    aComparar.setDireita(aInserir);
                    aInserir.setPai(aComparar);
                    verificarBalanceamento(aComparar);

                } else {
                    inserirAVL(aComparar.getDireita(), aInserir);
                }

            } else {
                // O nó já existe
            }
        }
    }

    public void verificarBalanceamento(Animal atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2) {

            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                atual = rotacaoDireita(atual);

            } else {
                atual = duplaRotacaoEsquerdaDireita(atual);
            }

        } else if (balanceamento == 2) {

            if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                atual = rotacaoEsquerda(atual);

            } else {
                atual = duplaRotacaoDireitaEsquerda(atual);
            }
        }

        if (atual.getPai() != null) {
            verificarBalanceamento(atual.getPai());
        } else {
            this.raiz = atual;
        }
    }

    public void remover(int k) {
        removerAVL(this.raiz, k);
    }

    public void removerAVL(Animal atual, int k) {
        if (atual == null) {
            return;

        } else {

            if (atual.getChave() > k) {
                removerAVL(atual.getEsquerda(), k);

            } else if (atual.getChave() < k) {
                removerAVL(atual.getDireita(), k);

            } else if (atual.getChave() == k) {
                removerNoEncontrado(atual);
            }
        }
    }

    public void removerNoEncontrado(Animal aRemover) {
        Animal r;

        if (aRemover.getEsquerda() == null || aRemover.getDireita() == null) {

            if (aRemover.getPai() == null) {
                this.raiz = null;
                aRemover = null;
                return;
            }
            r = aRemover;

        } else {
            r = sucessor(aRemover);
            aRemover.setChave(r.getChave());
        }

        Animal p;
        if (r.getEsquerda() != null) {
            p = r.getEsquerda();
        } else {
            p = r.getDireita();
        }

        if (p != null) {
            p.setPai(r.getPai());
        }

        if (r.getPai() == null) {
            this.raiz = p;
        } else {
            if (r == r.getPai().getEsquerda()) {
                r.getPai().setEsquerda(p);
            } else {
                r.getPai().setDireita(p);
            }
            verificarBalanceamento(r.getPai());
        }
        r = null;
    }

    public Animal rotacaoEsquerda(Animal inicial) {

        Animal direita = inicial.getDireita();
        direita.setPai(inicial.getPai());

        inicial.setDireita(direita.getEsquerda());

        if (inicial.getDireita() != null) {
            inicial.getDireita().setPai(inicial);
        }

        direita.setEsquerda(inicial);
        inicial.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == inicial) {
                direita.getPai().setDireita(direita);

            } else if (direita.getPai().getEsquerda() == inicial) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(direita);

        return direita;
    }

    public Animal rotacaoDireita(Animal inicial) {

        Animal esquerda = inicial.getEsquerda();
        esquerda.setPai(inicial.getPai());

        inicial.setEsquerda(esquerda.getDireita());

        if (inicial.getEsquerda() != null) {
            inicial.getEsquerda().setPai(inicial);
        }

        esquerda.setDireita(inicial);
        inicial.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == inicial) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == inicial) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(inicial);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public Animal duplaRotacaoEsquerdaDireita(Animal inicial) {
        inicial.setEsquerda(rotacaoEsquerda(inicial.getEsquerda()));
        return rotacaoDireita(inicial);
    }

    public Animal duplaRotacaoDireitaEsquerda(Animal inicial) {
        inicial.setDireita(rotacaoDireita(inicial.getDireita()));
        return rotacaoEsquerda(inicial);
    }

    public Animal sucessor(Animal q) {
        if (q.getDireita() != null) {
            Animal r = q.getDireita();
            while (r.getEsquerda() != null) {
                r = r.getEsquerda();
            }
            return r;
        } else {
            Animal p = q.getPai();
            while (p != null && q == p.getDireita()) {
                q = p;
                p = q.getPai();
            }
            return p;
        }
    }

    private int altura(Animal atual) {
        if (atual == null) {
            return -1;
        }

        if (atual.getEsquerda() == null && atual.getDireita() == null) {
            return 0;

        } else if (atual.getEsquerda() == null) {
            return 1 + altura(atual.getDireita());

        } else if (atual.getDireita() == null) {
            return 1 + altura(atual.getEsquerda());

        } else {
            return 1 + Math.max(altura(atual.getEsquerda()), altura(atual.getDireita()));
        }
    }

    private void setBalanceamento(Animal no) {
        no.setBalanceamento(altura(no.getDireita()) - altura(no.getEsquerda()));
    }

    public int getNumMaxNoArvore() {
        return (int) Math.pow(2, altura(raiz) + 2) - 1;
    }

    public void criarABVetor() {
        abVetor = new int[getNumMaxNoArvore() + 1];
        preencherVetor(raiz);

    }

    public void preencherVetor(Animal a) {
        if (a.equals(raiz)) {
            a.setIndice(1);
        }
        abVetor[a.getIndice()] = a.getChave();

        if (a.getDireita() != null) {
            a.getDireita().setIndice((a.getIndice() * 2) + 1);
            preencherVetor(a.getDireita());
        }
        if (a.getEsquerda() != null) {
            a.getEsquerda().setIndice((a.getIndice() * 2));
            preencherVetor(a.getEsquerda());
        }

    }

    public void imprimirVetor() {
        for (int i = 1; i < abVetor.length; i++) {
            System.out.print(abVetor[i] + " ");
        }
        System.out.println("");
    }
}
