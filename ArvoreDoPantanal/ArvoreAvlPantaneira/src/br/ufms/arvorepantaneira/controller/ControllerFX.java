package br.ufms.arvorepantaneira.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import br.ufms.arvorepantaneira.model.Animal;
import br.ufms.arvorepantaneira.persiste.BancoTXT;
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
    private Label buscado_chave;
    @FXML
    private Label buscado_nome;
    @FXML
    private Label buscado_sexo;
    @FXML
    private Label buscado_cor;
    @FXML
    private Label buscado_idade;
    @FXML
    private TextField tf_nome;
    @FXML
    private TextField tf_idade;
    @FXML
    private TextField tf_sexo;
    @FXML
    private TextField tf_cor;
    @FXML
    private TextField tf_nome_rb;
    @FXML
    private TextArea ta_saida;

    private final ArvoreAvl arvore = new ArvoreAvl();
    private final TreePrinter t = new TreePrinter();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void inserirAnimal(ActionEvent event) throws IOException {
        buscado_chave.setText("null");
        buscado_nome.setText("null");
        buscado_sexo.setText("null");
        buscado_idade.setText("null");
        buscado_cor.setText("null");
        if (tf_nome.getText() != null && tf_idade.getText() != null && tf_cor.getText() != null && tf_sexo.getText() != null) {
            arvore.inserir(tf_nome.getText(), Integer.parseInt(tf_idade.getText()), tf_sexo.getText(), tf_cor.getText());
            tf_nome.clear();
            tf_idade.clear();
            tf_cor.clear();
            tf_sexo.clear();
        } else {
            System.out.println("Preencha todos campos");
        }

        imprimirArvore();
    }

    private void imprimirArvore() throws IOException {
        arvore.criarABVetor();
        ta_saida.clear();
        ta_saida.appendText(t.imprimirArvore(arvore.vetor, arvore.getNumeroMaximoNos()));
        arvore.imprimirVetor();
    }

    @FXML
    private void removerAnimal(ActionEvent event) throws IOException {
        arvore.remover(tf_nome_rb.getText().hashCode());
        tf_nome_rb.clear();
        imprimirArvore();
    }

    @FXML
    private void buscarAnimal(ActionEvent event) throws IOException {
        Animal a = arvore.buscar(tf_nome_rb.getText().hashCode());
        tf_nome_rb.clear();

        if (a != null) {
            buscado_chave.setText(String.valueOf(a.getChave()));
            buscado_nome.setText(a.getNome());
            buscado_sexo.setText(a.getSexo());
            buscado_idade.setText(String.valueOf(a.getIdade()));
            buscado_cor.setText(a.getCor());

            imprimirArvore();

        }
    }

    @FXML
    private void Recarregar(ActionEvent event) throws IOException {
        arvore.raiz = null;
        ta_saida.clear();
        ArmazenaComID.zerarLista();
        System.out.println("Entro aqui");
        BancoTXT banco = new BancoTXT();
        for (Animal a : banco.LerArquivo()) {
            arvore.inserir(a);
        }
        imprimirArvore();
    }

    @FXML
    private void LimparArvore(ActionEvent event) throws IOException {
        arvore.raiz = null;
        ta_saida.clear();
    }
}
