/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvore.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author rafael
 */
public class Controller {

    String[] cmd = null;
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private final NavegadorArquivos navegador = new NavegadorArquivos();

    public void iniciar() throws IOException {

        do {

            try {

                System.out.println(navegador.dataagora() + " Para ajuda, entre com o comando Help " + "\n---->: " + navegador.mostraCaminho());
                String linha = reader.readLine();
                cmd = linha.split(" ");

                if (cmd[0].equalsIgnoreCase("cd")) {
                    navegador.atualizaCaminho(cmd[1]);

                } else if (cmd[0].equalsIgnoreCase("cd..")) {
                    voltarDiretorio();
                } else if (cmd[0].equalsIgnoreCase("arvore")) {
                    navegador.arvore(navegador.getCaminho(),"-");
                } else if (cmd[0].equalsIgnoreCase("ls")) {
//                    listarDiretorio();
                    if (cmd.length > 1) {
                        navegador.listarArquivos(cmd[1]);
                    } else {
                        navegador.listarArquivos();
                    }

                } else if (cmd[0].equalsIgnoreCase("help")) {

                    help();

                } else if (cmd[0].equalsIgnoreCase("ping")) {
                    try {
                        ping(cmd[1]);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Erro digite o host " + e);
                    }
                } else if (cmd[0].equalsIgnoreCase("create")) {

                    if ((cmd[1].equalsIgnoreCase("txt"))) {

                        if (cmd[2] != null) {
                            String texto = reader.readLine();
                            createtxt(cmd[2], texto);
                        } else {
                            throw new IllegalArgumentException("arquivo em branco");
                        }
                    } else if (cmd[1].equalsIgnoreCase("bat")) {
                        if (cmd[2] != null) {
                            String texto = reader.readLine();
                            createtxt(cmd[2], texto);

                        } else {
                            throw new IllegalArgumentException("arquivo em branco");
                        }
                    }

                }
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        } while (!cmd[0].equalsIgnoreCase("exit"));
    }

    private void help() {
        System.out.println("*            Bem Vindo ao TerminalRV      *\n*\n*"
                + "* 1.0 )Comando para entrar no diretorio          *\n"
                + "*       Exemplo(cd /NomeDoDiretorio)             *\n\n*"
                + "* 2.0 )Comando para Voltar diretorio             *\n"
                + "*       Exemplo(cd..)                              *\n*\n*"
                + "* 3.0 )Comando para entrar e listar diretorio    *\n"
                + "*       Exemplo(ls)                           *\n*\n*"
                + "* 4.0 )Comando para entrar e listar diretorio    *\n"
                + "*       Exemplo(ls /NomeDoDiretorio)            * \n*\n*"
                + "* 5.0 )Comando para entrar e listar diretorio    *\n"
                + "*       Exemplo(create tipoarquivo (\\enter )(digita texto))* \n*\n*"
                + "* 6.0 )Comando para entrar e listar diretorio    *\n"
                + "*       Exemplo(ping /Host)            * \n*\n*"
                + "* 7.0 )Comando sair do TerminalTruck    *\n"
                + "*        Exemplo(exit)            * \n*\n*");
    }

    private void ping(String pinga) {
        navegador.ping(pinga);
    }

    private void voltarDiretorio() {
        navegador.voltaCaminho();
    }

    private void createtxt(String nomedoarquivo, String texto) {
        try {
            navegador.criarArquivoTxt(nomedoarquivo, texto);
            System.out.println("Criado com sucesso");
        } catch (IOException e) {
            System.out.println(e + "Erro ao criar");
        }

    }

    private void createbat(String nomedoarquivo, String texto) {
        try {
            navegador.criarArquivoBat(nomedoarquivo, texto);
            System.out.println("Criado com sucesso");
        } catch (IOException e) {
            System.out.println(e + "Erro ao criar");
        }

    }

   

}
