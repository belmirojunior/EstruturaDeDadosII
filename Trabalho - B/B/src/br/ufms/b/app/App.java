package br.ufms.b.app;

import br.ufms.b.model.controller.ArvoreB;
import br.ufms.b.model.Item;
import java.io.*;

public class App {

    public static void main(String[] args) throws Exception {
        ArvoreB b = new ArvoreB(2);
        BufferedReader leia = new BufferedReader(new InputStreamReader(System.in));
        boolean flagPrincipal = true;
        while (flagPrincipal) {
            System.out.print("-------------Menu De Criação Arvore B---------\n"
                    + "--------------    1 - Criar B      -----------\n"
                    + "--------------    2 - Busca B     ------------\n"
                    + "--------------    3 - Remover B   ------------\n"
                    + "--------------    0 - Sair        ------------\n"
                    + "Escolha uma das opcoes acima: ");

            String opcao = leia.readLine();

            int chave;
            switch (opcao) {
                case "1":
                    System.out.println("Informe um elemento || 0 para voltar no Menu !");
                    chave = Integer.parseInt(leia.readLine());
                    while (chave > 0) {
                        Item item = new Item(chave);
                        b.inserir(item);
                        b.print();
                        chave = Integer.parseInt(leia.readLine());
                        System.out.println("Informe um elemento || 0 para sair !");
                    }
                    break;
                case "2":
                    System.out.println("\nPesquisando chaves");
                    chave = Integer.parseInt(leia.readLine());
                    while (chave > 0) {
                        Item item = new Item(chave);
                        item = (Item) b.busca(item);
                        if (item == null) {
                            System.out.println("Item nao encontrado");
                        } else {
                            System.out.println("Item encontrado");
                        }
                        chave = Integer.parseInt(leia.readLine());
                    }
                    break;
                case "3":
                    System.out.println("\nRemovendo algumas chaves");
                    chave = Integer.parseInt(leia.readLine());
                    while (chave > 0) {
                        Item item = new Item(chave);
                        b.remover(item);
                        b.print();
                        chave = Integer.parseInt(leia.readLine());
                    }
                    break;
                default:
                    System.out.println("Opção Invalida!!!");

            }
        }
    }
}
