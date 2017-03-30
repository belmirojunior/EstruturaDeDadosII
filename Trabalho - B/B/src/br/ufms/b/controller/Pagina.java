package br.ufms.b.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import br.ufms.b.modell.No;
import br.ufms.b.modell.No;

/**
 *
 * @author rafael
 */
//Class Pagina
public class Pagina {

    int n;
    No[] chave;// Array chave da Pagina que e um No
    Pagina[] c; // Array de paginas que tem Nos por sua vez
    
    // Metodo Constutro
    public Pagina(int n) {
        this.n = 0;
        this.chave = new No[n];
        this.c = new Pagina[n + 1];
    }
}
