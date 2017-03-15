package br.ufms.b.model.controller;

import br.ufms.b.model.Ppagina;

public class ArvoreB {

    private static class Pagina {

        int n;
        Ppagina t[];
        Pagina p[];

        public Pagina(int mm) {
            this.n = 0;
            this.t = new Ppagina[mm];
            this.p = new Pagina[mm + 1];
        }
    }
    static private Pagina itemraiz;
    private int m, mm;

    private void imprime(Pagina p, int nivel) {
        if (p != null) {
            System.out.print( "Nivel" + nivel + ":");
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

    private Ppagina pesquisa(Ppagina reg, Pagina ap) {
        if (ap == null) {
            return null; 
           
        } else {
            int i = 0;
            while ((i < ap.n - 1) && (reg.equals(ap.t[i]) > 0)) {
                i++;
            }
            if (reg.equals(ap.t[i]) == 0) {
                return ap.t[i];
            } else if (reg.equals(ap.t[i]) < 0) {
                return pesquisa(reg, ap.p[i]);
            } else {
                return pesquisa(reg, ap.p[i + 1]);
            }
        }
    }

    private void addNaPag(Pagina ap, Ppagina reg, Pagina apDir) {
        int k = ap.n - 1;
        while ((k >= 0) && (reg.equals(ap.t[k]) < 0)) {
            ap.t[k + 1] = ap.t[k];
            ap.p[k + 2] = ap.p[k + 1];
            k--;
        }
        ap.t[k + 1] = reg;
        ap.p[k + 2] = apDir;
        ap.n++;
    }

    private Pagina inserirItem(Ppagina novoR, Pagina ap, Ppagina[] listR,
            boolean[] cresceu) {
        Pagina r = null;
        if (ap == null) {
            cresceu[0] = true;
            listR[0] = novoR;
        } else {
            int i = 0;
            while ((i < ap.n - 1) && (novoR.equals(ap.t[i]) > 0)) {
                i++;
            }
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
                        r = apTemp;
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
            } 
            else {
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

    private Pagina removeI(Ppagina reg, Pagina ap, boolean[] removido) {
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

    public ArvoreB(int m) {
        this.itemraiz = null;
        this.m = m;
        this.mm = 2 * m;
    }

    public Ppagina busca(Ppagina reg) {
        return this.pesquisa(reg, this.itemraiz);
    }

    public void inserir(Ppagina reg) {
        Ppagina regRetorno[] = new Ppagina[1];
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

    public void remover(Ppagina reg) {
        boolean diminuiu[] = new boolean[1];
        this.itemraiz = this.removeI(reg, this.itemraiz, diminuiu);
        if (diminuiu[0] && (this.itemraiz.n == 0)) { // @{\it \'Arvore diminui na altura}@
            this.itemraiz = this.itemraiz.p[0];
        }
    }

    public void print() {
        System.out.println("ARVORE:");
        this.imprime(this.itemraiz, 0);
    }
}
