package br.ufms.b.model.controller;

import br.ufms.b.model.Bpagina;
// metodo que faz as funcoes da ARVORE B 

public class ArvoreB {

    private static class Pagina {// cria uma estrutra que representara uma Pagina

        int n;//numero de paginas 
        Bpagina t[]; //vetor de Ppaginas
        Pagina p[];//vetor de Paginas

        public Pagina(int mm) {// construtor da Pagina recebe int mm
            this.n = 0;// numero de paginas 
            this.t = new Bpagina[mm]; // vetor de Ppaginas
            this.p = new Pagina[mm + 1];//vetor de Paginas
        }
    }
    static private Pagina itemraiz; // criando variavel do tipo Pagina
    private int m, mm; // criando variaveis para calculo

//    static StringBuilder stringV = new StringBuilder();

//    metodo para imprimir pagina
    private void imprime(Pagina p, int nivel) {
        if (p != null) { //pergunta se a pagina e diferente de nula

//            stringV.append("Nivel").append(nivel).append(":");//imprimi o int do nivel
System.out.println("nivel");           
for (int i = 0; i < p.n; i++) {//e todos seus filhos sao concatenados por um for 
                stringV.append(p.t[i].toString()).append(" ");//caminhando em seu vetor de paginas
//            }
//            stringV.append("\n");//quebra a linha
//            for (int i = 0; i <= p.n; i++) { //enquanto o i for menor que seu numero de paginas
//                if (p.p[i] != null) {// pagina i for diferente de null
//                    if (i < p.n) { //i for menor que numero da pagina 
//                        stringV.append("  Esq: ").append(p.t[i].toString()).append("zn");// concatena a pagina da esquerda
//                    } else {//senao
//                        stringV.append("  Dir: ").append(p.t[i - 1].toString());// concatena a pagina da direita
//                    }
//                }
//
//                imprime(p.p[i], nivel + 1);//faz o metodo recursivo para o proximo nivel e para pagina i;
//            }
//        }
//    }
    private void imprime(Pagina p, int nivel) {
        if (p != null) {
            System.out.print("  Nivel " + nivel + ":");
            for (int i = 0; i < p.n; i++) {
                System.out.print(" " + p.t[i].toString());
            }
            System.out.println();
            for (int i = 0; i <= p.n; i++) {
                if (p.p[i] != null) {
                    if (i < p.n) {
                        System.out.println("  Esq: " + p.t[i].toString());
                    } else {
                        System.out.println("  Dir: " + p.t[i - 1].toString());
                    }
                }
                imprime(p.p[i], nivel + 1);
            }
        }
    }


    // Metodo para pesquisar uma pagina esse metodo tem como index uma Pagina e um VetorDePaginas 
    private Bpagina pesquisa(Bpagina reg, Pagina ap) {
        if (ap == null) {//pagina for nulll
            return null; //return pagina null

        } else {
            int i = 0;//se ela nao for null então
            while ((i < ap.n - 1) && (reg.equals(ap.t[i]) > 0)) { //um while ate que i < numero -1 e o reg.equlna possição i for > 0
                i++; //i e incrementado
            }
            if (reg.equals(ap.t[i]) == 0) {// se reg for igual a pagina possicao i
                return ap.t[i]; // se foi igual retorna a pagina encontrada
            } else if (reg.equals(ap.t[i]) < 0) {// se for menor pesquisa passando reg, pagina.p[i]
                return pesquisa(reg, ap.p[i]);
            } else {
                return pesquisa(reg, ap.p[i + 1]);// se for maior pesquisa passando reg, pagina.p[i+1]
            }
        }//
    }

    //Metodo para adicionar uma pagina a Arvore B
    private void addNaPag(Pagina ap, Bpagina reg, Pagina apDir) {
        int k = ap.n - 1; // k recebe numero de paginas - 1
        while ((k >= 0) && (reg.equals(ap.t[k]) < 0)) {// faz um while enquanto k >=0  & reg
            ap.t[k + 1] = ap.t[k];
            ap.p[k + 2] = ap.p[k + 1];
            k--; // valor de k e decrementado
        }
        ap.t[k + 1] = reg; //vetor de t na possicao k+1 recebe reg
        ap.p[k + 2] = apDir; //vetor de pagina k+2 recebe ap direita
        ap.n++;// ap recebe ++
    }

    //Metodos para inserirItem
    private Pagina inserirItem(Bpagina novoR, Pagina ap, Bpagina[] listR,
            boolean[] cresceu) {
        Pagina r = null; //inicia Pagina r = null;
        if (ap == null) {// se pagina igual a nullo
            cresceu[0] = true; //boolean na possicao 0 marcado como cresceu
            listR[0] = novoR;//ListR recebe o novoR
        } else {//
            int i = 0;// i =0
            while ((i < ap.n - 1) && (novoR.equals(ap.t[i]) > 0)) { //while ap.n -1 && novoR for igual ap.T[i]
                i++;//i++;    
                if (novoR.equals(ap.t[i]) == 0) {
                    System.out.println("Numero não encontrado");
                    cresceu[0] = false;
                } else {
                    if (novoR.equals(ap.t[i]) > 0) {
                        i++;
                    }
                    r = inserirItem(novoR, ap.p[i], listR, cresceu);
                    if (cresceu[0]) {
                        if (ap.n < this.mm) {
                            this.addNaPag(ap, listR[0], r);
                            cresceu[0] = false;
                            r = ap;
                        } else {
                            Pagina apTemp = new Pagina(this.mm);
                            apTemp.p[0] = null;
                            if (i <= this.m) {
                                this.addNaPag(apTemp, ap.t[this.mm - 1], ap.p[this.mm]);
                                ap.n--;
                                this.addNaPag(ap, listR[0], r);
                            } else {
                                this.addNaPag(apTemp, listR[0], r);
                            }
                            for (int j = this.m + 1; j < this.mm; j++) {
                                this.addNaPag(apTemp, ap.t[j], ap.p[j + 1]);
                                ap.p[j + 1] = null;
                            }
                            ap.n = this.m;
                            apTemp.p[0] = ap.p[this.m + 1];
                            listR[0] = ap.t[this.m];
                            r = apTemp;// 
                        }
                    }
                }
            }
        }
        return (cresceu[0] ? r : ap);
    }

    private boolean merge(Pagina pagAnterior, Pagina pagNovo, int pagPosPai) {
        boolean diminuiu = true;
        if (pagPosPai < pagNovo.n) {
            Pagina aux = pagNovo.p[pagPosPai + 1];
            int dispAux = (aux.n - this.m + 1) / 2;
            pagAnterior.t[pagAnterior.n++] = pagNovo.t[pagPosPai];
            pagAnterior.p[pagAnterior.n] = aux.p[0];
            aux.p[0] = null;
            if (dispAux > 0) {
                for (int j = 0; j < dispAux - 1; j++) {
                    this.addNaPag(pagAnterior, aux.t[j], aux.p[j + 1]);
                    aux.p[j + 1] = null;
                }
                pagNovo.t[pagPosPai] = aux.t[dispAux - 1];
                aux.n = aux.n - dispAux;
                for (int j = 0; j < aux.n; j++) {
                    aux.t[j] = aux.t[j + dispAux];
                }
                for (int j = 0; j <= aux.n; j++) {
                    aux.p[j] = aux.p[j + dispAux];
                }
                aux.p[aux.n + dispAux] = null;
                diminuiu = false;
            } else {
                for (int j = 0; j < this.m; j++) {
                    this.addNaPag(pagAnterior, aux.t[j], aux.p[j + 1]);
                    aux.p[j + 1] = null;
                }
                aux = pagNovo.p[pagPosPai + 1] = null;

                for (int j = pagPosPai; j < pagNovo.n - 1; j++) {
                    pagNovo.t[j] = pagNovo.t[j + 1];
                    pagNovo.p[j + 1] = pagNovo.p[j + 2];
                }
                pagNovo.p[pagNovo.n--] = null;
                diminuiu = pagNovo.n < this.m;
            }
        } else {
            Pagina aux = pagNovo.p[pagPosPai - 1];
            int dispAux = (aux.n - this.m + 1) / 2;
            for (int j = pagAnterior.n - 1; j >= 0; j--) {
                pagAnterior.t[j + 1] = pagAnterior.t[j];
            }
            pagAnterior.t[0] = pagNovo.t[pagPosPai - 1];
            for (int j = pagAnterior.n; j >= 0; j--) {
                pagAnterior.p[j + 1] = pagAnterior.p[j];
            }
            pagAnterior.n++;
            if (dispAux > 0) {
                for (int j = 0; j < dispAux - 1; j++) {
                    this.addNaPag(pagAnterior, aux.t[aux.n - j - 1], aux.p[aux.n - j]);
                    aux.p[aux.n - j] = null;
                }
                pagAnterior.p[0] = aux.p[aux.n - dispAux + 1];
                aux.p[aux.n - dispAux + 1] = null;
                pagNovo.t[pagPosPai - 1] = aux.t[aux.n - dispAux];

                aux.n = aux.n - dispAux;
                diminuiu = false;
            } else {
                for (int j = 0; j < this.m; j++) {
                    this.addNaPag(aux, pagAnterior.t[j], pagAnterior.p[j + 1]);
                    pagAnterior.p[j + 1] = null;
                }
                pagAnterior = null;

                pagNovo.p[pagNovo.n--] = null;
                diminuiu = pagNovo.n < this.m;
            }
        }
        return diminuiu;
    }
/**
 *  Passando uma pagina e um index e um Pagina Pai , pergumta se é antecessor
 * @param ap
 * @param ind
 * @param apPai
 * @return 
 */
    private boolean antecessor(Pagina ap, int ind, Pagina apPai) {
        boolean diminuiu = true;
        if (apPai.p[apPai.n] != null) {
            diminuiu = antecessor(ap, ind, apPai.p[apPai.n]);
            if (diminuiu) {
                diminuiu = merge(apPai.p[apPai.n], apPai, apPai.n);
            }
        } else {
            ap.t[ind] = apPai.t[--apPai.n];
            diminuiu = apPai.n < this.m;
        }
        return diminuiu;
    }
/**
 * Remove uma pagina 
 * @param reg
 * @param ap
 * @param removido
 * @return 
 */
    private Pagina removeI(Bpagina reg, Pagina ap, boolean[] removido) {
        if (ap == null) {
            System.out.println("Erro: Registro nao encontrado");
            removido[0] = false;
        } else {
            int ind = 0;
            while ((ind < ap.n - 1) && (reg.equals(ap.t[ind]) > 0)) {
                ind++;
            }
            if (reg.equals(ap.t[ind]) == 0) {
                if (ap.p[ind] == null) {
                    ap.n--;
                    removido[0] = ap.n < this.m;
                    for (int j = ind; j < ap.n; j++) {
                        ap.t[j] = ap.t[j + 1];
                        ap.p[j] = ap.p[j + 1];
                    }
                    ap.p[ap.n] = ap.p[ap.n + 1];
                    ap.p[ap.n + 1] = null;
                } else {
                    removido[0] = antecessor(ap, ind, ap.p[ind]);
                    if (removido[0]) {
                        removido[0] = merge(ap.p[ind], ap, ind);
                    }
                }
            } else {
                if (reg.equals(ap.t[ind]) > 0) {
                    ind++;
                }
                ap.p[ind] = removeI(reg, ap.p[ind], removido);
                if (removido[0]) {
                    removido[0] = merge(ap.p[ind], ap, ind);
                }
            }
        }
        return ap;
    }
    // Construtor da Arvore B  
    public ArvoreB(int m) {
        this.itemraiz = null;
        this.m = m;
        this.mm = 2 * m;
    }
    // Busca na Pagina
    public Bpagina busca(Bpagina reg) {
        return this.pesquisa(reg, this.itemraiz);
    }
    //Inserir Pagina 
    public void inserir(Bpagina reg) {
        Bpagina regRetorno[] = new Bpagina[1];
        boolean cresceu[] = new boolean[1];
        Pagina apRetorno = this.inserirItem(reg, this.itemraiz, regRetorno, cresceu);
        if (cresceu[0]) {
            Pagina aux = new Pagina(this.mm);
            aux.t[0] = regRetorno[0];
            aux.p[0] = this.itemraiz;
            aux.p[1] = apRetorno;
            this.itemraiz = aux;
            this.itemraiz.n++;
        } else {
            this.itemraiz = apRetorno;
        }
    }
    //Remove uma pagina
    public void remover(Bpagina reg) {
        boolean diminuiu[] = new boolean[1];
        this.itemraiz = this.removeI(reg, this.itemraiz, diminuiu);
        if (diminuiu[0] && (this.itemraiz.n == 0)) { // @{\it \'Arvore diminui na altura}@
            this.itemraiz = this.itemraiz.p[0];
        }
    }
    // imprimir a Arvore B
    public void print() {
        System.out.println("ARVORE:");
        this.imprime(this.itemraiz, 0);
    }
   
    public void imprimir(Pagina p, String espaco) {
        System.out.print(espaco);

        for (int i = 1; i <= p.n; i++) {
            System.out.print("|" + p.t[i] + "| ");
        }
        System.out.println("");
        espaco += "    ";
        for (int i = 1; i <= p.n + 1; i++) {
            if (p.p[i] != null) {
                imprimir(p.p[i], espaco);
            }
        }
    }
}
