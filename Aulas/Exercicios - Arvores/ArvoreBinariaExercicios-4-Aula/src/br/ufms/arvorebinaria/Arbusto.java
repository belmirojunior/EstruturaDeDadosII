/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorebinaria;

/**
 *
 * @author rafael
 * @param <T> generalizar para conquistar fica dica :d
 */
public class Arbusto<T> {

    private Arbusto<T> arbustoDireito = null;
    private Arbusto<T> arbustoEsquerdo = null;
    private Arbusto<T> arbustoPai =null;
    private Integer cogumelo = null;

    public Arbusto<T> getArbustoDireito() {
        return arbustoDireito;
    }

    public void setArbustoDireito(Arbusto<T> arbustoDireito) {
        this.arbustoDireito = arbustoDireito;
    }

    public Arbusto<T> getArbustoEsquerdo() {
        return arbustoEsquerdo;
    }

    public void setArbustoEsquerdo(Arbusto<T> arbustoEsquerdo) {
        this.arbustoEsquerdo = arbustoEsquerdo;
    }

    public Arbusto<T> getArbustoPai() {
        return arbustoPai;
    }

    public void setArbustoPai(Arbusto<T> arbustoPai) {
        this.arbustoPai = arbustoPai;
    }

    public Integer getCogumelo() {
        return cogumelo;
    }

    public void setCogumelo(Integer cogumelo) {
        this.cogumelo = cogumelo;
    }


}
