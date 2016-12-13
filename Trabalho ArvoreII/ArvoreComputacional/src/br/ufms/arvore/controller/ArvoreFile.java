/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvore.controller;

import br.ufms.arvore.model.TreeNo;
import java.util.ArrayList;

/**
 *
 * @author rafael
 */
public class ArvoreFile {

    TreeNo raiz = null;

    public ArvoreFile(String nameFile, Long tamanhoFile,String uriToFile,ArrayList<TreeNo> subTree) {
        raiz = new TreeNo();
        raiz.setNameFile(nameFile);
        raiz.setUriToFile(uriToFile);
        raiz.setTamanhoFile(tamanhoFile);
        raiz.setSubTreeFile(subTree);        
    }
    public ArvoreFile() {
        raiz = new TreeNo();
    }
    
    void addSubTree(){
        
    }
}
