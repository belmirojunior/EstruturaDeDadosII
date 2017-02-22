/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorepantaneira.persiste;

import br.ufms.arvorepantaneira.model.Animal;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rafael
 */
public class BancoTXT {

    public ArrayList<Animal> LerArquivo() throws FileNotFoundException, IOException {
        FileInputStream stream = new FileInputStream("./src/br/ufms/arvorepantaneira/persiste/listaPantaneira.txt");
        InputStreamReader reader = new InputStreamReader(stream);
        BufferedReader br = new BufferedReader(reader);
        String linha = br.readLine();
        ArrayList<Animal> lista = new ArrayList<>();
        while (linha != null) {

            String nome = linha.substring(0, linha.indexOf('|'));
            String idade = linha.substring(linha.indexOf('|') + 1, linha.lastIndexOf('|') - 2);
            String cor = linha.substring(linha.indexOf('|') + 4, linha.lastIndexOf('|'));
            String sexo = linha.substring(linha.lastIndexOf('|') + 1, linha.length());
            Animal a = new Animal(nome, Integer.parseInt(idade), sexo, cor);
            lista.add(a);

            System.out.println(nome);
            System.out.println(idade);
            System.out.println(cor);
            System.out.println(sexo);
            try {
                linha = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(BancoTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

}
