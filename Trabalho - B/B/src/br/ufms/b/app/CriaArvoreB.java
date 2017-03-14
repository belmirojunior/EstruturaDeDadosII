package br.ufms.b.app;
import br.ufms.b.model.controller.ArvoreB;
import br.ufms.b.model.Pag;
import java.io.*;

public class CriaArvoreB {

    public static void main(String[] args) throws Exception {
        ArvoreB b = new ArvoreB(2);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        boolean flagPrincipal = true;
        while (flagPrincipal) {
            System.out.println("-------------Menu De Criação Arvore B---------\n"
                    + "----------- 1 - Criar B        ----------\n"
                    + "----------- 2 - Busca B        ----------\n"
                    + "----------- 3 - Remover B        ----------\n"
                    + "----------- 0 - Sair        ----------\n");
            String opcao = in.readLine();
            int chave;
            switch (opcao) {
                case "1":
                    System.out.println("Informe um elemento");
                    chave = Integer.parseInt(in.readLine());
                    while (chave > 0) {
                        Pag item = new Pag(chave);
                        b.inserir(item);
                        b.print();
                        chave = Integer.parseInt(in.readLine());
                    }
                    break;
                case "2":
                    System.out.println("\nPesquisando chaves");
                    chave = Integer.parseInt(in.readLine());
                    while (chave > 0) {
                        Pag item = new Pag(chave);
                        item = (Pag) b.busca(item);
                        if (item == null) {
                            System.out.println("Item nao encontrado");
                        } else {
                            System.out.println("Item encontrado");
                        }
                        chave = Integer.parseInt(in.readLine());
                    }
                    break;
                case "3":
                    System.out.println("\nRemovendo algumas chaves");
                    chave = Integer.parseInt(in.readLine());
                    while (chave > 0) {
                        Pag item = new Pag(chave);
                        b.remover(item);
                        b.print();
                        chave = Integer.parseInt(in.readLine());
                    }
                    break;

            }
        }
    }
}
