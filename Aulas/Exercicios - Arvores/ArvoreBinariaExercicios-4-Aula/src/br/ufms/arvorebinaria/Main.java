/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorebinaria;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author rafael
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Arvore<Integer> arvore = new Arvore<>();
        System.out.println("Informe um array:");
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));

        String[] info = leia.readLine().split(" ");
        for (String string : info) {
            arvore.adicionar(Integer.parseInt(string), arvore.ptr_raiz);
        }
        boolean flag = true;
        while (flag) {
            System.out.println("Informe um valor a ser buscado");
            Integer buscado = Integer.parseInt(leia.readLine());
            if (arvore.buscar(buscado, arvore.ptr_raiz)) {
                System.out.println("Achou");
            } else {
                System.out.println("Nao achou");
            }
            System.out.println("Deseja contiuar ou sair ? \n 0 - Sair");
            String escolha = leia.readLine();

            if (escolha.equals("0")) {
                flag = false;
            }
        }
    }

}
