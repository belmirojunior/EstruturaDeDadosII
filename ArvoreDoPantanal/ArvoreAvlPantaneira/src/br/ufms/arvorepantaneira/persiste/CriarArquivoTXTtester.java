/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorepantaneira.persiste;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author rafael
 */
public class CriarArquivoTXTtester {

    public static void main(String[] args) throws IOException {
        Scanner ler = new Scanner(System.in);

        FileWriter arq = new FileWriter("./src/br/ufms/arvorepantaneira/persiste/listaPantaneiratester.txt");
        PrintWriter gravarArq = new PrintWriter(arq);

        for (int i = 0; i <= 100; i++) {
            gravarArq.printf("%2dTucano;21;MACHO;preto\n", i);
        }

        arq.close();

    }

}
