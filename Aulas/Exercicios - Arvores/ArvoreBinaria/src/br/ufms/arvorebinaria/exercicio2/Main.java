/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorebinaria.exercicio2;

import static br.ufms.Arvores.ABComVetor.procurar;
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
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Inorme um valor qualquer para adicionar");
//        int h = Integer.parseInt(leia.readLine());
//        int valorH = (int) Math.pow(2, h);
        Integer[] arr = {null,50, 35, 60, 20, 36, 58, 70, 18, 26, 33, 37, 52, 59, 68, 80};
        System.out.print("Digite o número a ser buscado: ");

        int nInfo = Integer.parseInt(leia.readLine());
        Integer posicao = buscarB(arr, nInfo, 1);

        if (posicao < 0) {
            System.out.println("* O número não se encontra na árvore");
        } else {
            System.out.println("* O número " + nInfo + " está na posição "
                    + posicao + " da árvore");
        }

    }

    public static int buscarB(Integer vetor[], int procurado, int i) {

        if (i < vetor.length) {
            if (vetor[i] == procurado) {
                return i;
            } else if (procurado > vetor[i]) {
                return buscarB(vetor, procurado, (i * 2) + 1);
            } else if (procurado < vetor[i]) {
                return buscarB(vetor, procurado, i * 2);
            }
        } else {
            return i * (-1);
        }
        return i * (-1);
    }
}
