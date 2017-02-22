package br.ufms.arvorepantaneira.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufms.arvorepantaneira.model.Animal;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author rafael
 */
public class ControllerFX implements Initializable {

    @FXML
    private Label buscado_nome;
    @FXML
    private Label buscado_sexo;
    @FXML
    private Label buscado_cor;
    @FXML
    private Label buscado_idade;
    @FXML
    private TextField tf_diretorio;
    @FXML
    private TextField tf_chave;
    @FXML
    private Button btn_inserir;
    @FXML
    private Button btn_excluir;
    @FXML
    private Button btn_buscar;
    @FXML
    private TextArea ta_saida;

    private ArvoreAvl arvore = new ArvoreAvl();
    private TreePrinter t = new TreePrinter();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inserirAnimal(ActionEvent event) throws IOException {

        arvore.inserir(tf_chave.getText(), 1, "M", "Azul");
        imprimirArvore();
    }

    private void imprimirArvore() throws IOException {
        arvore.criarABVetor();
        ta_saida.clear();
        ta_saida.appendText(t.imprimirArvore(arvore.vetor, arvore.getNumeroMaximoNos()));
        tf_chave.setText("");
        arvore.imprimirVetor();
    }

    @FXML
    private void removerAnimal(ActionEvent event) throws IOException {
        arvore.remover(tf_chave.getText().hashCode());
        imprimirArvore();
    }

    @FXML
    private void buscarAnimal(ActionEvent event) throws IOException {
        Animal a = arvore.buscar(tf_chave.getText().hashCode());
        if (a != null) {
            buscado_nome.setText(a.getNome());
            buscado_sexo.setText(a.getSexo());
            buscado_idade.setText(String.valueOf(a.getIdade()));
            buscado_cor.setText(a.getCor());
            imprimirArvore();
        }
    }

}
