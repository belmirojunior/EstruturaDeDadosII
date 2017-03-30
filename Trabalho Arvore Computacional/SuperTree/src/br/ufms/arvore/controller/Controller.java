/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvore.controller;

import br.ufms.arvore.views.NavegadorArquivosViews;
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
    private final NavegadorArquivosController navegador = new NavegadorArquivosController();
    private TreeController rootTree;

    public void iniciar() throws IOException {

        do {

            try {

                System.out.println(navegador.dataagora() + " Para ajuda, entre com o comando Help " + "\n ➜ : " + navegador.mostraCaminho());
                String linha = reader.readLine();
                cmd = linha.split(" ");
                if (cmd[0].equalsIgnoreCase("cd")) {
                    navegador.atualizaCaminho(cmd[1]);
                } else if (cmd[0].equalsIgnoreCase("cd..")) {
                    voltarDiretorio();
                } else if (cmd[0].equalsIgnoreCase("arvore")) {
                    rootTree = new TreeController(navegador.getCaminho());
                    System.out.println("Qual tipo de impressão deseja?\n"
                            + "1 - Pré-Ordem\n"
                            + "2 - Ordem Simetrica\n"
                            + "3 - Pós-Ordem\n");
                    String qltipo = reader.readLine();
                    switch (qltipo) {
                        case "1":
                            rootTree.imprimirTreePreOrdem(rootTree.root," ");
                            break;
                        case "2":
                            rootTree.imprimirTreeOrdemSimetrica(rootTree.root," ");
                            break;
                        case "3":
                             rootTree.imprimirTreePosOrdem(rootTree.root," ");
                            break;
                    }
                } else if (cmd[0].equalsIgnoreCase("ls")) {
                    if (cmd.length > 1) {
                        navegador.listarArquivos(cmd[1]);
                    } else {
                        navegador.listarArquivos();
                    }
                } else if (cmd[0].equalsIgnoreCase("help")) {
                    navegador.Views.help();
                } else if (cmd[0].equalsIgnoreCase("ping")) {
                    try {
                        navegador.ping(cmd[1]);
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
