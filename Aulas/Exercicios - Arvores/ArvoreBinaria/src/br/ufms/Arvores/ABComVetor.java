package br.ufms.Arvores;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rafael - PC
 */
public class ABComVetor {

    public static void main(String[] args) throws IOException {

        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));

        System.out.print("Informe a Altura ");
        
        int h = Integer.parseInt(ler.readLine());
        int tamanho = (int) (Math.pow(2, h) - 1);

        int vetor[] = new int[tamanho + 1];

        System.out.println("Entre com os elementos da árvore: ");
        for (int i = 1; i < tamanho + 1; i++) {
            vetor[i] = Integer.parseInt(ler.readLine());
        }

        System.out.println("-----------------");
        for (int i = 1; i < vetor.length; i++) {
            System.out.print(vetor[i] + " ");
        }
        System.out.println("\n-----------------");

        while (true) {

            System.out.println("Digite a opção desejada: ");
            System.out.println("1 - Verificar quem é o pai e os filhos de um elemento");
            System.out.println("2 - Procurar determinado número na árvore");
            System.out.println("3 - Inserir um número na árvore");

            int opcao = Integer.parseInt(ler.readLine());
            System.out.println(opcao);

            switch (opcao) {
                case 1:
                    System.out.print("\nDigite um 'i': ");
                    int i = (Integer.parseInt(ler.readLine()));
                    System.out.println("--------------------------------");
                    System.out.println("vetor[" + i + "] = " + vetor[i]);

                    if (temPai(vetor, i)) {
                        System.out.println("* O pai dele é: " + vetor[((i) / 2)]);
                    } else {
                        System.out.println("* Não possui pai");
                    }
                    if (temFilho(vetor, i)) {
                        System.out.println("* O filho da esquerda é: " + vetor[((i) * 2)]);
                        System.out.println("* O filho da direita é: " + vetor[((i) * 2) + 1]);
                    } else {
                        System.out.println("* Não possui filhos");
                    }
                    System.out.println("--------------------------------");
                    break;
                case 2:
                    System.out.print("Digite o número a ser buscado: ");
                    int procurado = Integer.parseInt(ler.readLine());
                    int posicao = procurar(vetor, procurado, 1);

                    if (posicao >= 0) {
                        System.out.println("* O número " + procurado + " está na posição "
                                + posicao + " da árvore");
                    } else {
                        System.out.println("* O número não se encontra na árvore");
                    }
                    break;
                case 3:
                    System.out.print("Digite o número a ser inserido: ");
                    int elemento = Integer.parseInt(ler.readLine());
                    inserir(vetor, elemento, tamanho);
            }
        }
    }

    public static void inserir(int vetor[], int elemento, int tamanho) {
        int posicao = Math.abs(procurar(vetor, elemento, 1));

        if (posicao > vetor.length) {
            int vetorNovo[] = new int[(int) Math.pow(2, tamanho)];
        }

        System.out.println(posicao);

    }

    public static int procurar(int vetor[], int procurado, int i) {

        if (i < vetor.length) {
            if (vetor[i] == procurado) {
                return i;
            } else if (procurado > vetor[i]) {
                return procurar(vetor, procurado, (i * 2) + 1);
            } else if (procurado < vetor[i]) {
                return procurar(vetor, procurado, i * 2);
            }
        } else {
            return i * (-1);
        }
        return i * (-1);
    }

    public static boolean temPai(int vetor[], int i) {
        return i / 2 != 0;
    }

    public static boolean temFilho(int vetor[], int i) {
        return i * 2 <= vetor.length;
    }
}