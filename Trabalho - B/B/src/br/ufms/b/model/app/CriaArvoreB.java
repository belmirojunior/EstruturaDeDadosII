package br.ufms.b.model.app;

import br.ufms.b.model.MeuItem;
import java.io.*;

public class CriaArvoreB {

    public static void main(String[] args) throws Exception {
        ArvoreB dicionario = new ArvoreB(2);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(System.in));
        boolean flagPrincipal = true;
        while (flagPrincipal) {
            System.out.println("-------------Menu De Criação Arvore B---------\n"
                    + "----------- 1 - Criar B        ----------\n"
                    + "----------- 1 - Busca B        ----------\n"
                    + "----------- 1 - Remover B        ----------\n"
                    + "----------- 1 - Criar Arvore        ----------\n");
            String opcao = in.readLine();
            int chave = Integer.parseInt(in.readLine());
            System.out.println(chave);

            switch (opcao) {
                case "1":
                    boolean flagCriacao = true;
                    System.out.println("Informe um elemento");
                    chave = Integer.parseInt(in.readLine());
                    while (flagCriacao) {
                        MeuItem item = new MeuItem(chave);
                        dicionario.insere(item);
                        dicionario.imprime();
                        System.out.println("Add = < 0 ");
                        chave = Integer.parseInt(in.readLine());
                        if (chave == 0) {
                            flagCriacao = false;
                        }
                    }
                    break;
                case "2":
                    System.out.println("\nPesquisando chaves");
                    chave = Integer.parseInt(in.readLine());
                    while (chave > 0) {
                        MeuItem item = new MeuItem(chave);
                        item = (MeuItem) dicionario.pesquisa(item);
                        if (item == null) {
                            System.out.println("Item nao encontrado");
                        } else {
                            System.out.println("Item encontrado");
                        }
                        chave = Integer.parseInt(in.readLine());
                    }
                    break;
                case "":
                    System.out.println("\nRemovendo algumas chaves");
                    chave = Integer.parseInt(in.readLine());
                    while (chave > 0) {
                        MeuItem item = new MeuItem(chave);
                        dicionario.retira(item);
                        dicionario.imprime();
                        chave = Integer.parseInt(in.readLine());
                    }
                    break;

            }

        }
    }
}
