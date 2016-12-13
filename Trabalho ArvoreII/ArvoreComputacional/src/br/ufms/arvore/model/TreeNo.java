package br.ufms.arvore.model;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class TreeNo {

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    public String getUriToFile() {
        return uriToFile;
    }

    public void setUriToFile(String uriToFile) {
        this.uriToFile = uriToFile;
    }

    public Long getTamanhoFile() {
        return tamanhoFile;
    }

    public void setTamanhoFile(Long tamanhoFile) {
        this.tamanhoFile = tamanhoFile;
    }

    public ArrayList<TreeNo> getSubTreeFile() {
        return SubTreeFile;
    }

    public void setSubTreeFile(ArrayList<TreeNo> SubTreeFile) {
        this.SubTreeFile = SubTreeFile;
    }
    
  private  String nameFile = null;
  private  String uriToFile = null;
  private  Long tamanhoFile = null;
  private  ArrayList<TreeNo> SubTreeFile ;
    
}
