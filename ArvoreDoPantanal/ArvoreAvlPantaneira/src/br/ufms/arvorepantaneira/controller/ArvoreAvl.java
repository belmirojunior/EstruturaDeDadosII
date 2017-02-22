package br.ufms.arvorepantaneira.controller;

import br.ufms.arvorepantaneira.model.Animal;

/**
 *
 * @author rafael
 */
public class ArvoreAvl {

    public String[] vetor;
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
                    verificaBF(aComparar);

                } else {
                    inserirAVL(aComparar.getEsquerda(), aInserir);
                }

            } else if (aInserir.getChave() > aComparar.getChave()) {

                if (aComparar.getDireita() == null) {
                    aComparar.setDireita(aInserir);
                    aInserir.setPai(aComparar);
                    verificaBF(aComparar);

                } else {
                    inserirAVL(aComparar.getDireita(), aInserir);
                }

            } else {
                // O nó já existe
            }
        }
    }

    public void verificaBF(Animal atual) {
        setBalanceamento(atual);
        int balanceamento = atual.getBalanceamento();

        if (balanceamento == -2) {

            if (altura(atual.getEsquerda().getEsquerda()) >= altura(atual.getEsquerda().getDireita())) {
                atual = RSD(atual);

            } else {
                atual = RDD(atual);
            }

        } else if (balanceamento == 2) {

            if (altura(atual.getDireita().getDireita()) >= altura(atual.getDireita().getEsquerda())) {
                atual = RSE(atual);

            } else {
                atual = RDE(atual);
            }
        }

        if (atual.getPai() != null) {
            verificaBF(atual.getPai());
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

    public Animal buscar(int k) {
        System.out.println("Ola eu aqui: " + k);
        Animal a = busca(this.raiz, Math.abs(k));
        if (a == null) {
            System.out.println("nullo");
        } else {
            System.out.println("me Acho !" + a.getNome());
        }
        return a;
    }

    private Animal busca(Animal atual, int k) {
        if (raiz == null) {
            return null;
        }
        if (atual.getChave() == k) {
            return atual;
        } else if (atual.getChave() < k) {
            if (atual.getDireita() != null) {
              return  busca(atual.getDireita(), k);
            }

        } else if (atual.getChave() > k) {
            if (atual.getEsquerda() != null) {
              return  busca(atual.getEsquerda(), k);
            }
        }
        return null;
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
            verificaBF(r.getPai());
        }
        r = null;
    }

    public Animal RSE(Animal a) {

        Animal direita = a.getDireita();
        direita.setPai(a.getPai());

        a.setDireita(direita.getEsquerda());

        if (a.getDireita() != null) {
            a.getDireita().setPai(a);
        }

        direita.setEsquerda(a);
        a.setPai(direita);

        if (direita.getPai() != null) {

            if (direita.getPai().getDireita() == a) {
                direita.getPai().setDireita(direita);

            } else if (direita.getPai().getEsquerda() == a) {
                direita.getPai().setEsquerda(direita);
            }
        }

        setBalanceamento(a);
        setBalanceamento(direita);

        return direita;
    }

    public Animal RSD(Animal a) {

        Animal esquerda = a.getEsquerda();
        esquerda.setPai(a.getPai());

        a.setEsquerda(esquerda.getDireita());

        if (a.getEsquerda() != null) {
            a.getEsquerda().setPai(a);
        }

        esquerda.setDireita(a);
        a.setPai(esquerda);

        if (esquerda.getPai() != null) {

            if (esquerda.getPai().getDireita() == a) {
                esquerda.getPai().setDireita(esquerda);

            } else if (esquerda.getPai().getEsquerda() == a) {
                esquerda.getPai().setEsquerda(esquerda);
            }
        }

        setBalanceamento(a);
        setBalanceamento(esquerda);

        return esquerda;
    }

    public Animal RDD(Animal a) {
        a.setEsquerda(RSE(a.getEsquerda()));
        return RSD(a);
    }

    public Animal RDE(Animal a) {
        a.setDireita(RSD(a.getDireita()));
        return RSE(a);
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

    private void setBalanceamento(Animal animal) {
        animal.setBF(altura(animal.getDireita()) - altura(animal.getEsquerda()));
    }

    public int getNumeroMaximoNos() {
        return (int) Math.pow(2, altura(raiz) + 2) - 1;
    }

    public void criarABVetor() {
        vetor = new String[getNumeroMaximoNos() + 1];
        AbasteceVetor(raiz);

    }

    public void AbasteceVetor(Animal a) {
        if (a.equals(raiz)) {
            a.setIndice(1);
        }
        vetor[a.getIndice()] = a.getNome();

        if (a.getDireita() != null) {
            a.getDireita().setIndice((a.getIndice() * 2) + 1);
            AbasteceVetor(a.getDireita());
        }
        if (a.getEsquerda() != null) {
            a.getEsquerda().setIndice((a.getIndice() * 2));
            AbasteceVetor(a.getEsquerda());
        }

    }

    public void imprimirVetor() {
        for (int i = 1; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("");
    }
}
