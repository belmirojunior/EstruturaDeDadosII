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
        inserirAnimal(this.raiz, n);
    }

    public void inserir(Animal a) {

        inserirAnimal(this.raiz, a);
    }

    public void inserirAnimal(Animal animal, Animal novoanimal) {

        if (animal == null) {
            this.raiz = novoanimal;

        } else {

            if (novoanimal.getChave() < animal.getChave()) {

                if (animal.getEsquerda() == null) {
                    animal.setEsquerda(novoanimal);
                    novoanimal.setPai(animal);
                    verificaBF(animal);

                } else {
                    inserirAnimal(animal.getEsquerda(), novoanimal);
                }

            } else if (novoanimal.getChave() > animal.getChave()) {

                if (animal.getDireita() == null) {
                    animal.setDireita(novoanimal);
                    novoanimal.setPai(animal);
                    verificaBF(animal);

                } else {
                    inserirAnimal(animal.getDireita(), novoanimal);
                }

            } else {

            }
        }
    }

    public void verificaBF(Animal atual) {
        setBF(atual);
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
        removerAVL(this.raiz, Math.abs(k));
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

        return busca(this.raiz, Math.abs(k));
    }

    private Animal busca(Animal atual, int k) {
        if (raiz == null) {
            return null;
        }
        if (atual.getChave() == k) {
            return atual;
        } else if (atual.getChave() < k) {
            if (atual.getDireita() != null) {
                return busca(atual.getDireita(), k);
            }

        } else if (atual.getChave() > k) {
            if (atual.getEsquerda() != null) {
                return busca(atual.getEsquerda(), k);
            }
        }
        return null;
    }

    public void removerNoEncontrado(Animal AnimalRemover) {
        Animal auxAnimal;

        if (AnimalRemover.getEsquerda() == null || AnimalRemover.getDireita() == null) {

            if (AnimalRemover.getPai() == null) {
                this.raiz = null;
                AnimalRemover = null;
                return;
            }
            auxAnimal = AnimalRemover;

        } else {
            auxAnimal = sucessor(AnimalRemover);
            AnimalRemover.setChave(auxAnimal.getChave());
        }

        Animal p;
        if (auxAnimal.getEsquerda() != null) {
            p = auxAnimal.getEsquerda();
        } else {
            p = auxAnimal.getDireita();
        }

        if (p != null) {
            p.setPai(auxAnimal.getPai());
        }

        if (auxAnimal.getPai() == null) {
            this.raiz = p;
        } else {
            if (auxAnimal == auxAnimal.getPai().getEsquerda()) {
                auxAnimal.getPai().setEsquerda(p);
            } else {
                auxAnimal.getPai().setDireita(p);
            }
            verificaBF(auxAnimal.getPai());
        }
        auxAnimal = null;
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

        setBF(a);
        setBF(direita);

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

        setBF(a);
        setBF(esquerda);

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

    public  int altura(Animal atual) {
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

    private void setBF(Animal animal) {
        animal.setBF(altura(animal.getDireita()) - altura(animal.getEsquerda()));
    }

    public int getNumeroMaximoNos() {
        return (int) Math.pow(2, altura(raiz) + 2) - 1;
    }

    public void NewVetor() {
        vetor = new String[getNumeroMaximoNos() + 1];
        AbasteceVetor(raiz);

    }

    public void AbasteceVetor(Animal a) {
        if (a.equals(raiz)) {
            a.setIndice(1);
        }

        vetor[a.getIndice()] = a.getNome().substring(0, 2);
        
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
