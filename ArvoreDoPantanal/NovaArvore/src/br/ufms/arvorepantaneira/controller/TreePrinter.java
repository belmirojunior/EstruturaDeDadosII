package br.ufms.arvorepantaneira.controller;

/**
 *
 * @author rafael
 */

import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;


class No {

    public int data;
    public No esquerda;
    public No direita;

    public No(int data, No esquerda, No direita) {
        this.data = data;
        this.esquerda = esquerda;
        this.direita = direita;
    }

    public String prettyPrint(int height) {
        return (prettyPrint(this, 1, height).toString());
    }

    private StringBuilder prettyPrint(No root, int currentHeight, int totalHeight) {
        StringBuilder sb = new StringBuilder();
        int spaces = getSpaceCount(totalHeight - currentHeight + 1);
        if (root == null) {
            String row = String.format("%" + (2 * spaces + 1) + "s%n", "");
            String block = new String(new char[spaces + 1]).replace("\0", row);
            return new StringBuilder(block);
        }
        if (currentHeight == totalHeight) {
            return new StringBuilder(root.data + "");
        }
        int slashes = getSlashCount(totalHeight - currentHeight + 1);
        sb.append(String.format("%" + (spaces + 1) + "s%" + spaces + "s", root.data + "", ""));
        sb.append("\n");
        char leftSlash = root.esquerda == null ? ' ' : '/';
        char rightSlash = root.direita == null ? ' ' : '\\';
        int spaceInBetween = 1;
        for (int i = 0, space = spaces - 1; i < slashes; i++, space--, spaceInBetween += 2) {
            for (int j = 0; j < space; j++) {
                sb.append(" ");
            }
            sb.append(leftSlash);
            for (int j = 0; j < spaceInBetween; j++) {
                sb.append(" ");
            }
            sb.append(rightSlash + "");
            for (int j = 0; j < space; j++) {
                sb.append(" ");
            }
            sb.append("\n");
        }

        StringBuilder leftTree = prettyPrint(root.esquerda, currentHeight + 1, totalHeight);
        StringBuilder rightTree = prettyPrint(root.direita, currentHeight + 1, totalHeight);
        Scanner leftScanner = new Scanner(leftTree.toString());
        Scanner rightScanner = new Scanner(rightTree.toString());
        while (leftScanner.hasNextLine()) {
            if (currentHeight == totalHeight - 1) {
                sb.append(String.format("%-2s %2s", leftScanner.nextLine(), rightScanner.nextLine()));
                sb.append("\n");
                spaceInBetween -= 2;
            } else {
                sb.append(leftScanner.nextLine());
                sb.append(" ");
                sb.append(rightScanner.nextLine() + "\n");
            }
        }

        return sb;

    }

    private int getSlashCount(int height) {
        if (height <= 3) {
            return height - 1;
        }
        return (int) (3 * Math.pow(2, height - 3) - 1);
    }

    private int getSpaceCount(int height) {
        return (int) (3 * Math.pow(2, height - 2) - 1);
    }

    public void print() {
        inorder(this);
        System.out.println();
    }

    private void inorder(No root) {
        if (root == null) {
            return;
        }
        inorder(root.esquerda);
        System.out.print(root.data + " ");
        inorder(root.direita);
    }

    public int getHeight() {
        return getHeight(this);
    }

    private int getHeight(No root) {
        if (root == null) {
            return 0;
        }
        return Math.max(getHeight(root.esquerda), getHeight(root.direita)) + 1;
    }

    @Override
    public String toString() {
        return this.data + "";
    }
}

public class TreePrinter {

    static int N, height;
    static No root;

    public String imprimirArvore(int[] vetor, int n) throws IOException {
        int vet[] = vetor;
        int cont = 2;
        root = new No(vet[1], null, null);

        try {
         N = n;

            System.out.println("N = " + N);

            ArrayList<No> q = new ArrayList<No>();
            q.add(root);
            for (int i = 0; i < N && !q.isEmpty(); i++) {
             
                int a = vet[cont];
                cont++;
                int b = vet[cont];
                cont++;

                if (a == 0) {
                    a = -1;
                }
                if (b == 0) {
                    b = -1;
                }
                No current = q.remove(0);

                if (a == -1) {
                    current.esquerda = null;
                } else {
                    current.esquerda = new No(a, null, null);
                    q.add(current.esquerda);
                }

                if (b == -1) {
                    current.direita = null;
                } else {
                    current.direita = new No(b, null, null);
                    q.add(current.direita);
                }
            }
            root.print();
            height = root.getHeight();
            System.out.println(root.prettyPrint(height));
            return root.prettyPrint(height);

        } catch (NumberFormatException e) {
   
            e.printStackTrace();
        }
        return "";
    }
}
