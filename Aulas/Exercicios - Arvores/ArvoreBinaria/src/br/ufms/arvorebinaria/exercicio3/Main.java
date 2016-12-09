/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorebinaria.exercicio3;

import static br.ufms.Arvores.ABComVetor.procurar;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

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

        Integer[] arr = {-1, 50, 35, 60, 20, 36, 58, 70, 18, 26, 33, 37, 52, 59, 68, 80};
        while (true) {

            System.out.print("Digite o número a ser Inserido: ");

            int nInfo = Integer.parseInt(leia.readLine());
            Integer posicao = InserirB(arr, nInfo, 1);

            if (posicao < 0) {
                posicao = (posicao * -1);
                Integer[] NovoArr = CopiaArray(arr, posicao);
                NovoArr[posicao-1] = nInfo;
                arr = NovoArr;
                impirmir(NovoArr);
            } else {
                System.out.println("Numero ja existe no vetor");
            }
        }
    }

    public static void impirmir(Integer[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    public static Integer[] CopiaArray(Integer[] arr, int pos) {
        Integer[] arrNovo = new Integer[pos];
        System.arraycopy(arr, 0, arrNovo, 0, arr.length);
        for (int i = 0; i < arrNovo.length; i++) {
            if (arrNovo[i] == null) {
                arrNovo[i] = -1;
            }
        }
        return arrNovo;
    }

    public static int InserirB(Integer vetor[], int procurado, int i) {

        if (i < vetor.length) {
            if (vetor[i] == procurado) {
                return i;
            } else if (procurado > vetor[i]) {
                return InserirB(vetor, procurado, (i * 2) + 1);
            } else if (procurado < vetor[i]) {
                return InserirB(vetor, procurado, i * 2);
            }
        } else {
            return i * (-1);
        }
        return i * (-1);
    }
}
