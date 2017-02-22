/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorepantaneira.app;

import br.ufms.arvorepantaneira.controller.ArvoreAvl;
import br.ufms.arvorepantaneira.controller.TreePrinter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author rafael
 */
public class NovaArvore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader l = new BufferedReader(new InputStreamReader(System.in));

        ArvoreAvl a = new ArvoreAvl();
       

        while (true) {
             TreePrinter t = new TreePrinter();
            a.inserir(l.readLine(), 1, "M", "Azul");

            a.criarABVetor();
            System.out.println("\n-----------------------------------\n");
            a.imprimirVetor();
            t.imprimirArvore(a.vetor, a.getNumeroMaximoNos());
        }

    }

}
