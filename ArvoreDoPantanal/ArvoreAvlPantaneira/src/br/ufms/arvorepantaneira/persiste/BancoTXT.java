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
            String[] entrada = linha.split(";");
            String nome = entrada[0];
            String idade = entrada[1];
            String cor = entrada[2];
            String sexo = entrada[3];
            Animal a = new Animal(nome, Integer.parseInt(idade), cor, sexo);
            lista.add(a);
            try {
                linha = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(BancoTXT.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

}
