/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvorepantaneira.controller;

import br.ufms.arvorepantaneira.model.Animal;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author rafael
 */
public class ArmazenaComID {

    private static final ArrayList<Integer> Lista = new ArrayList();

    public static Integer abatece(Integer a) {
        for (int i = 0; i < Lista.size(); i++) {
            if (Objects.equals(Lista.get(i), a)) {
                return i;
            }
        }
        for (int i = 0; i < Lista.size(); i++) {
            System.out.println(Lista.get(i));
        }
        Lista.add(a);
        return Lista.size();
    }

    public static int busca(int a) {
        for (int i = 0; i < Lista.size(); i++) {
            if (Lista.get(i).equals(a)) {
               return i+1;
            }
        }
        return 0;
    }
    public static int remove(int a) {
        for (int i = 0; i < Lista.size(); i++) {
            if (Lista.get(i).equals(a)) {
                Lista.remove(i);
               return i+1;
            }
        }
        return 0;
    }

    public static void zerarLista() {
        if (!Lista.isEmpty()) {
            Lista.clear();
        }

    }

    public static int getSize() {
        return Lista.size();
    }

    public int getIntList(int i) {
        return Lista.get(i);
    }
}
