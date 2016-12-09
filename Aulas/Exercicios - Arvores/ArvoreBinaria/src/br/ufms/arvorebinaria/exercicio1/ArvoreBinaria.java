/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorebinaria.exercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author rafael
 */
public class ArvoreBinaria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Inorme H");
//        int h = Integer.parseInt(leia.readLine());
        
//        int valorH = (int) Math.pow(2, h);
        int valorH=16;
//        Random gerador = new Random();
//        int[] arr = new int[valorH];
        int[] arr = {1,2,3,4,5,6,7,8,9,10,15,20,30,40,50,80};
//        for (int i = 0; i < valorH-1; i++) {
//            arr[i] = gerador.nextInt(50);
//        }
        for (int i = 0; i < valorH-1; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("\nInorme Um index L para localizar");
        int pai = 0;
        int filhoesq = 0;
        int filhodir = 0;

        int index = Integer.parseInt(leia.readLine()) - 1;
        if (index >= 0 && index < valorH) {
            if (index == 0) {
                filhoesq = (1);
                filhodir = (2);
                System.out.println("Index: " + arr[index] + "\nPai:Não Possui \nFilhoEsq: " + arr[filhoesq] + "\nFilhoDir: " + arr[filhodir]);
            } else if (index > 0 && index < (valorH / 2)-1) {
                pai = (index / 2)-1;
                filhoesq = index * 2+1;
                filhodir = ((index * 2) + 2);
                System.out.println("Index: " + arr[index] + "\nPai:" + arr[pai] + " \nFilhoEsq: " + arr[filhoesq] + "\nFilhoDir: " + arr[filhodir]);
            } else {
                pai = (index / 2);
                System.out.println("Index: " + arr[index] + "\nPai: " + arr[pai] + "\nEste Ponto Não Possui filhos");
            }

        } else {
            System.out.println("Ele e Começo meio e fim");
        }
    }

}
