/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufms.arvore.app;

import br.ufms.arvore.controller.Controller;
import java.io.IOException;

/**
 *
 * @author Rafael
 */
public class NavegadorArquivosApp {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        Controller app = new Controller();
        app.iniciar();
    }
}
