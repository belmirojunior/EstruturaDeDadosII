package br.ufms.b.modell;

// cria uma estrutra que representara uma No Pagina
public class No {

    // chve do no
    private int chave;

    // metodo construtor
    public No(int chave) {
        this.chave = chave;
    }

//metodo que ve quem e maior ou menor ou igual se retorna 0
    public int comparar(No aComparar) {

        if (this.chave < aComparar.chave) {
            return -1;
        } else if (this.chave > aComparar.chave) {
            return 1;
        }
        return 0;
    }

    public void setChave(int chave) {
        this.chave = chave;
    }

    public int getChave() {
        return this.chave;
    }

    public String toString() {
        return "" + this.chave;
    }
}
