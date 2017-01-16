/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvore.app.model;

import java.io.File;

/**
 *
 * @author rafael
 */
public class TreeFileModel {

    private String nome;
    private String url;
    private File filethis;
    private Long tamanho;
    private TreeFileModel TreeDict[];

    public TreeFileModel(String diretorio) {
        File TreeFile = new File(diretorio);
        this.nome = TreeFile.getName();
        this.url = TreeFile.getAbsolutePath();
        this.tamanho = TreeFile.length();
        this.filethis = TreeFile;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public File getFilethis() {
        return filethis;
    }

    public void setFilethis(File filethis) {
        this.filethis = filethis;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public void setTreeDict(int n) {
        this.TreeDict = new TreeFileModel[n];
    }

    public TreeFileModel getTreeDict(int i) {
        return TreeDict[i];
    }

    public TreeFileModel[] getTreeDict() {
        return TreeDict;
    }

    public void setTreeDict(int i, TreeFileModel TreeDict) {
        this.TreeDict[i] = TreeDict;
    }

}
