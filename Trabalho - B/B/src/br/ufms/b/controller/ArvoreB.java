package br.ufms.b.controller;
import br.ufms.b.modell.No;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author rafael
 */

// metodo que faz as funcoes da ARVORE B 
public class ArvoreB {

    private Pagina raiz; // raiz da pagina
    private int ordem, maxFilhos; //ordem e maximo de filhos

    //constutor 
    public ArvoreB(int m) {
        this.raiz = null; //raiz recebe null contrutor
        this.ordem = m;// ordem da arvore
        this.maxFilhos = 2 * m; // maximo de filhos
    }

    //imprimi a arvore B
    private void imprime(Pagina p, int nivel) {
        if (p != null) {// se for diferente de null 
            System.out.print("  Nivel " + nivel + ":");
            for (int i = 0; i < p.n; i++) {
                System.out.print(" " + p.chave[i].toString());//caminhando em seu vetor de paginas
            }
            System.out.println();
            for (int i = 0; i <= p.n; i++) {////enquanto o i for menor que seu numero de paginas
                if (p.c[i] != null) {
                    if (i < p.n) {
                        System.out.println("  Esq: " + p.chave[i].toString());// concatena a pagina da esquerda
                    } else {
                        System.out.println("  Dir: " + p.chave[i - 1].toString());// concatena a pagina da direita
                    }
                }
                imprime(p.c[i], nivel + 1);//faz o metodo recursivo para o proximo nivel e para pagina i;
            }
        }
    }

    // metodo de busca recebe o No buscado e a pagina atual funciona recursivamente
    private No buscar(No aProcurar, Pagina paginaAtual) {
        if (paginaAtual == null) { // Se pagina diferente igual a nullo ela ja retorna se n continua 
            return null;
        } else {
            int i = 0;//se ela nao for null então
            while ((i < paginaAtual.n - 1) && (aProcurar.comparar(paginaAtual.chave[i]) > 0)) {//um while ate que i < numero -1 e o reg.equlna possição i for > 0
                i++;//i e incrementado
            }
            if (aProcurar.comparar(paginaAtual.chave[i]) == 0) {
                return paginaAtual.chave[i];
            } else if (aProcurar.comparar(paginaAtual.chave[i]) < 0) {
                return ArvoreB.this.buscar(aProcurar, paginaAtual.c[i]);
            } else {
                return ArvoreB.this.buscar(aProcurar, paginaAtual.c[i + 1]);
            }
        }
    }
    
    //Metodo para adicionar uma pagina a Arvore B
    public void insere(No aInserir) {
        No noAux[] = new No[1]; // no aux e criado
        boolean cresceu[] = new boolean[1]; // ele cresceu entao e marcado com true
        Pagina pagAux = this.insere(aInserir, this.raiz, noAux, cresceu);
        if (cresceu[0]) {// ele cresceu vai ser true
            Pagina pagTemp = new Pagina(this.maxFilhos);
            pagTemp.chave[0] = noAux[0]; // recebe aux
            pagTemp.c[0] = this.raiz; // comeco da temp recebe raiz
            pagTemp.c[1] = pagAux; // recebe PagAux
            this.raiz = pagTemp; // raiz recebe pagTemp[]
            this.raiz.n++; // numero da raiz e incrementado
        } else {
            this.raiz = pagAux; 
        }
    }
    //Metodo para adicionar uma pagina a Arvore B

    private void insereNaPagina(Pagina paginaAtual, No aInserir, Pagina pagDireita) {
        int k = paginaAtual.n - 1;
        //SE ENTRAR NO WHILE, O NUMERO A SER INSERIDO É MENOR, ENTAO, OS VETORES DA PAGINA ATUAL SAO DESLOCADOS UMA POSIÇÃO PARA A DIREITA
        while ((k >= 0) && (aInserir.comparar(paginaAtual.chave[k]) < 0)) {
            paginaAtual.chave[k + 1] = paginaAtual.chave[k];
            paginaAtual.c[k + 2] = paginaAtual.c[k + 1];
            k--;
        }
        paginaAtual.chave[k + 1] = aInserir;
        paginaAtual.c[k + 2] = pagDireita;
        paginaAtual.n++;
    }   
    // metodo de inserção que e chamado pelo metodo da View
    private Pagina insere(No aInserir, Pagina paginaAtual, No[] noAux, boolean cresceu[]) {
        Pagina pagAux = null;
        if (paginaAtual == null) {
            cresceu[0] = true;
            noAux[0] = aInserir;
        } else {
            int i = 0;
            //procura a posição certa para inserção
            while ((i < paginaAtual.n - 1) && (aInserir.comparar(paginaAtual.chave[i]) > 0)) {
                i++;
            }
            if (aInserir.comparar(paginaAtual.chave[i]) == 0) {
                System.out.println("O número já se encontra na árvore!");
                cresceu[0] = false;
            } else {
                if (aInserir.comparar(paginaAtual.chave[i]) > 0) {
                    i++;
                }
                // aqui ele e inserido a pagina atual fazendo um deslocamento
                pagAux = insere(aInserir, paginaAtual.c[i], noAux, cresceu);
                if (cresceu[0]) {
                    //Ainda tem espaço na página
                    if (paginaAtual.n < this.maxFilhos) {
                        this.insereNaPagina(paginaAtual, noAux[0], pagAux);
                        cresceu[0] = false;
                        pagAux = paginaAtual;
                    } else {
                        Pagina apTemp = new Pagina(this.maxFilhos);
                        apTemp.c[0] = null;
                        if (i <= this.ordem) {
                            this.insereNaPagina(apTemp, paginaAtual.chave[this.maxFilhos - 1], paginaAtual.c[this.maxFilhos]);
                            paginaAtual.n--;
                            this.insereNaPagina(paginaAtual, noAux[0], pagAux);
                        } else {
                            this.insereNaPagina(apTemp, noAux[0], pagAux);
                        }
                        for (int j = this.ordem + 1; j < this.maxFilhos; j++) {
                            this.insereNaPagina(apTemp, paginaAtual.chave[j], paginaAtual.c[j + 1]);
                            paginaAtual.c[j + 1] = null;
                        }
                        paginaAtual.n = this.ordem;
                        apTemp.c[0] = paginaAtual.c[this.ordem + 1];
                        noAux[0] = paginaAtual.chave[this.ordem];
                        pagAux = apTemp;
                    }
                }
            }
        }

        //Se cresceu, retorno a pagina feita para retorno, se não cresceu retorno a pagina do jeito como a recebi
        return (cresceu[0] ? pagAux : paginaAtual);
    }

    // metodo do merge para reconstruir depois de um split
    private boolean reconstitui(Pagina apPag, Pagina apPai, int posPai) {
        boolean diminuiu = true;
        if (posPai < apPai.n) {
            Pagina aux = apPai.c[posPai + 1];
            int dispAux = (aux.n - this.ordem + 1) / 2;
            apPag.chave[apPag.n++] = apPai.chave[posPai];
            apPag.c[apPag.n] = aux.c[0];
            aux.c[0] = null;
            if (dispAux > 0) {
                for (int j = 0; j < dispAux - 1; j++) {
                    this.insereNaPagina(apPag, aux.chave[j], aux.c[j + 1]);
                    aux.c[j + 1] = null;
                }
                apPai.chave[posPai] = aux.chave[dispAux - 1];
                aux.n = aux.n - dispAux;
                for (int j = 0; j < aux.n; j++) {
                    aux.chave[j] = aux.chave[j + dispAux];
                }
                for (int j = 0; j <= aux.n; j++) {
                    aux.c[j] = aux.c[j + dispAux];
                }
                aux.c[aux.n + dispAux] = null;
                diminuiu = false;
            } else {
                for (int j = 0; j < this.ordem; j++) {
                    this.insereNaPagina(apPag, aux.chave[j], aux.c[j + 1]);
                    aux.c[j + 1] = null;
                }
                aux = apPai.c[posPai + 1] = null;

                for (int j = posPai; j < apPai.n - 1; j++) {
                    apPai.chave[j] = apPai.chave[j + 1];
                    apPai.c[j + 1] = apPai.c[j + 2];
                }
                apPai.c[apPai.n--] = null;
                diminuiu = apPai.n < this.ordem;
            }
        } else {
            Pagina aux = apPai.c[posPai - 1];
            int dispAux = (aux.n - this.ordem + 1) / 2;
            for (int j = apPag.n - 1; j >= 0; j--) {
                apPag.chave[j + 1] = apPag.chave[j];
            }
            apPag.chave[0] = apPai.chave[posPai - 1];
            for (int j = apPag.n; j >= 0; j--) {
                apPag.c[j + 1] = apPag.c[j];
            }
            apPag.n++;
            if (dispAux > 0) {
                for (int j = 0; j < dispAux - 1; j++) {
                    this.insereNaPagina(apPag, aux.chave[aux.n - j - 1], aux.c[aux.n - j]);
                    aux.c[aux.n - j] = null;
                }
                apPag.c[0] = aux.c[aux.n - dispAux + 1];
                aux.c[aux.n - dispAux + 1] = null;
                apPai.chave[posPai - 1] = aux.chave[aux.n - dispAux];
                aux.n = aux.n - dispAux;
                diminuiu = false;
            } else {
                for (int j = 0; j < this.ordem; j++) {
                    this.insereNaPagina(aux, apPag.chave[j], apPag.c[j + 1]);
                    apPag.c[j + 1] = null;
                }
                apPag = null; 

                apPai.c[apPai.n--] = null;
                diminuiu = apPai.n < this.ordem;  // diminuiu
            }
        }
        return diminuiu;
    }

    //metodo antecessor para corrgir remocao e a arvore diminuiu
    private boolean antecessor(Pagina ap, int ind, Pagina apPai) {
        boolean diminuiu = true;
        if (apPai.c[apPai.n] != null) {
            diminuiu = antecessor(ap, ind, apPai.c[apPai.n]);
            if (diminuiu) {
                diminuiu = reconstitui(apPai.c[apPai.n], apPai, apPai.n); // utiliza metodo acima
            }
        } else {
            ap.chave[ind] = apPai.chave[--apPai.n];
            diminuiu = apPai.n < this.ordem;
        }
        return diminuiu;
    }

    // retirar um no para remocao
    private Pagina retira(No aRemover, Pagina ap, boolean[] diminuiu) {
        if (ap == null) {
            System.out.println("Número não se encontra na árvore");
            diminuiu[0] = false;
        } else {
            int ind = 0; // index recebe 0
            while ((ind < ap.n - 1) && (aRemover.comparar(ap.chave[ind]) > 0)) {
                ind++;// index vai sendo incrementado
            }
            if (aRemover.comparar(ap.chave[ind]) == 0) {
                if (ap.c[ind] == null) {
                    ap.n--;
                    diminuiu[0] = ap.n < this.ordem;
                    for (int j = ind; j < ap.n; j++) {
                        ap.chave[j] = ap.chave[j + 1];
                        ap.c[j] = ap.c[j + 1];
                    }
                    ap.c[ap.n] = ap.c[ap.n + 1];
                    ap.c[ap.n + 1] = null;
                } else {
                    diminuiu[0] = antecessor(ap, ind, ap.c[ind]);
                    if (diminuiu[0]) {
                        diminuiu[0] = reconstitui(ap.c[ind], ap, ind);
                    }
                }
            } else {
                if (aRemover.comparar(ap.chave[ind]) > 0) {
                    ind++;
                }
                ap.c[ind] = retira(aRemover, ap.c[ind], diminuiu);//
                if (diminuiu[0]) {
                    diminuiu[0] = reconstitui(ap.c[ind], ap, ind); // utiliza o metodo reconstitui para voltar a forma correta
                }
            }
        }
        return ap;
    }

    // busca um No na Arvore
    public No buscar(No aBuscar) {
        return this.buscar(aBuscar, this.raiz);
    }

    // removao retornando void que chama o metodo acima
    public void remover(No aRemover) {
        boolean diminuiu[] = new boolean[1];
        this.raiz = this.retira(aRemover, this.raiz, diminuiu);
        if (diminuiu[0] && (this.raiz.n == 0)) {
            this.raiz = this.raiz.c[0];
        }
    }
    // imprimir  arvore recursivamente
    public void imprime() {
        System.out.println("ARVORE:");
        this.imprime(this.raiz, 0);
    }
    // metodo que e chamado no VIEW
    public void imprimir() {
        System.out.println("--------------------------");
        imprimir(raiz, "    ");
        System.out.println("--------------------------");
    }
    // metodo chamado pelos demais para fazer alianhamento dos NO
    public void imprimir(Pagina p, String espaco) {
        System.out.print(espaco);

        for (int i = 1; i <= p.n; i++) {
            System.out.print("|" + p.chave[i] + "| ");
        }
        System.out.println("");
        espaco += "    ";
        for (int i = 1; i <= p.n + 1; i++) {
            if (p.c[i] != null) {
                imprimir(p.c[i], espaco);
            }
        }
    }
}
