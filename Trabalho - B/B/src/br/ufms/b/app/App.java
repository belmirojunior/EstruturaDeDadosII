package br.ufms.b.app;

import br.ufms.b.controller.ArvoreB;
import br.ufms.b.modell.No;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    // view do progama aqui vao ser chamados todos metodos do motor da Arvore B
    public static void main(String[] args) throws Exception {
        ArvoreB arvore = new ArvoreB(2);
        BufferedReader ler = new BufferedReader(new InputStreamReader(System.in));
        boolean flag = true;
        while (flag) {
            System.out.print("-------------Menu De Criação Arvore B---------\n"
                    + "--------------    1 - Criar B      -----------\n"
                    + "--------------    2 - Busca B     ------------\n"
                    + "--------------    3 - Remover B   ------------\n"
                    + "--------------    0 - Sair        ------------\n"
                    + "Escolha uma das opcoes acima: ");

            String opcao = ler.readLine();
            int num;
            No item;
            switch (opcao) {
                case "1":
                    System.out.println("Informe um elemento || 0 para voltar no Menu !");
                    num = Integer.parseInt(ler.readLine());
                    while (num > 0) {
                        item = new No(num);
                        arvore.insere(item);
                        arvore.imprime();
                        System.out.println("Informe um elemento || 0 para voltar no Menu !");
                        num = Integer.parseInt(ler.readLine());
                    }
                    break;
                case "2":

                    System.out.println("Informe um elemento Pesquisar|| 0 para voltar no Menu !");
                    num = Integer.parseInt(ler.readLine());
                    while (num > 0) {
                        item = new No(num);
                        item = arvore.buscar(item);
                        if (item == null) {
                            System.out.println("Item nao encontrado");
                        } else {
                            System.out.println("Elemento encontrado");
                        }
                        System.out.println("Informe um elemento Pesquisar|| 0 para voltar no Menu !");
                        num = Integer.parseInt(ler.readLine());

                    }
                    break;
                case "3":
                    System.out.println("Informe um elemento para Remover|| 0 para voltar no Menu !");
                    num = Integer.parseInt(ler.readLine());
                    while (num > 0) {

                        item = new No(num);
                        item = arvore.buscar(item);
                        if (item == null) {
                            System.out.println("Elemento nao encontrado");
                        } else {
                            item = new No(num);
                            arvore.remover(item);
                            arvore.imprime();
                            System.out.println("Elemento Removido");
                        }
                        System.out.println("Informe um elemento para Remover|| 0 para voltar no Menu !");
                        num = Integer.parseInt(ler.readLine());
                    }
                    break;
                case "0":
                    System.out.println("############# FIM ############ ");
                    flag = false;
                    break;
                default:
                    System.out.println("Opção Invalida!!!");

            }
//        switch (opcao) {
//                case 1:
//                    System.out.println("Digite um número");
//                    int num = Integer.parseInt(ler.readLine());
//                    No item = new No(num);
//                    arvore.insere(item);
//                    arvore.imprime();
//                    break;
//                case 2:
//                    System.out.println("Digite um número:");
//                    num = Integer.parseInt(ler.readLine());
//                    item = new No(num);
//                    arvore.remover(item);
//                    arvore.imprime();
//                    break;
//                case 3:
//                    System.out.println("Digite um número");
//                    num = Integer.parseInt(ler.readLine());
//                    item = new No(num);
//                    item = arvore.buscar(item);
//                    if (item == null) {
//                        System.out.println("Item nao encontrado");
//                    } else {
//                        System.out.println("Item encontrado");
//                    }
//                    break;
//                case 0:
//                    System.exit(0);
//            }
        }
    }
}
