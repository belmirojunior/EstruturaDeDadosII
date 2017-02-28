package br.ufms.arvorepantaneira.controller;

import br.ufms.arvorepantaneira.model.Animal;
import br.ufms.arvorepantaneira.persiste.BancoTXT;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
    private Label buscado_altura;
    @FXML
    private Label buscado_index;
    @FXML
    private Label buscado_fb;
    @FXML
    private TextField tf_nome;
    @FXML
    private ChoiceBox<?> cb_idade;
    @FXML
    private ChoiceBox<?> cb_sexo;
    @FXML
    private TextField tf_cor;
    @FXML
    private TextField tf_nome_rb;
    @FXML
    private TextArea ta_saida;

    private final ArvoreAvl arvore = new ArvoreAvl();
    private final TreePrinter t = new TreePrinter();
    BancoTXT banco = new BancoTXT();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            for (Animal a : banco.LerArquivo()) {
                arvore.inserir(a);
            }
            imprimirArvore();
        } catch (IOException ex) {
            Logger.getLogger(ControllerFX.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    @FXML
    private void inserirAnimal(ActionEvent event) throws IOException {

        buscado_nome.setText("null");
        buscado_sexo.setText("null");
        buscado_idade.setText("null");
        buscado_cor.setText("null");
        if (tf_nome.getText() != null && cb_idade.getValue().toString() != null && tf_cor.getText() != null && cb_sexo.getValue().toString() != null) {

            Animal a = arvore.buscar(tf_nome.getText().hashCode());
            if (a != null) {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Diálogo de informação");
                dialogoInfo.setContentText("Animal ja se encontra na Arvore!!!");
                dialogoInfo.showAndWait();
            } else {
                arvore.inserir(tf_nome.getText(), Integer.parseInt(cb_idade.getValue().toString()), cb_sexo.getValue().toString(), tf_cor.getText());
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Diálogo de informação");
                dialogoInfo.setContentText("Animal Inserido com Sucesso !!!");
                dialogoInfo.showAndWait();
                imprimirArvore();
            }
        } else {
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Diálogo de informação");
            dialogoInfo.setContentText("Preencha todos os campos.!!!");
            dialogoInfo.showAndWait();
        }
        tf_nome.clear();
        tf_cor.clear();

    }

    private void imprimirArvore() throws IOException {
        arvore.NewVetor();
        ta_saida.clear();
        ta_saida.appendText(t.imprimirArvore(arvore.vetor, arvore.getNumeroMaximoNos()));
        arvore.imprimirVetor();
    }

    @FXML
    private void removerAnimal(ActionEvent event) throws IOException {
        if (tf_nome_rb.getText() != null) {
            Animal a = arvore.buscar(tf_nome_rb.getText().hashCode());
            if (a != null) {
                arvore.remover(tf_nome_rb.getText().hashCode());
                Alert dialogoInfo = new Alert(Alert.AlertType.INFORMATION);
                dialogoInfo.setTitle("Diálogo de informação");
                dialogoInfo.setContentText(a.getNome() + " Removido Com Sucesso!!!");
                dialogoInfo.showAndWait();

            } else {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Diálogo de informação");
                dialogoInfo.setContentText("Animal nao Encontrado!!!");
                dialogoInfo.showAndWait();
            }

            tf_nome.clear();
            imprimirArvore();
        } else {
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Diálogo de informação");
            dialogoInfo.setContentText("Preencha o nome!!!");
            dialogoInfo.showAndWait();
        }

    }

    @FXML
    private void buscarAnimal(ActionEvent event) throws IOException {

        if (tf_nome_rb.getText() != null) {
            Animal a = arvore.buscar(tf_nome_rb.getText().hashCode());
            tf_nome_rb.clear();
            if (a != null) {
                Alert dialogoInfo = new Alert(Alert.AlertType.CONFIRMATION);
                dialogoInfo.setTitle("Diálogo de informação");
                dialogoInfo.setHeaderText("Animal Encontrado, veja as Info. ao lado.");
                dialogoInfo.showAndWait();
                buscado_chave.setText(String.valueOf(a.getChave()));
                buscado_nome.setText(a.getNome());
                buscado_sexo.setText(a.getSexo());
                buscado_idade.setText(String.valueOf(a.getIdade()));
                buscado_cor.setText(a.getCor());
                buscado_index.setText(String.valueOf(a.getIndice()));
                buscado_fb.setText(String.valueOf(a.getBalanceamento()));
                buscado_altura.setText(String.valueOf(arvore.altura(a)));
            } else {
                Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
                dialogoInfo.setTitle("Diálogo de informação");
                dialogoInfo.setContentText("Animal nao Encontrado!!!");
                dialogoInfo.showAndWait();
            }

        } else {
            Alert dialogoInfo = new Alert(Alert.AlertType.ERROR);
            dialogoInfo.setTitle("Diálogo de informação");
            dialogoInfo.setHeaderText("Preencha o nome!!!");
            dialogoInfo.showAndWait();
        }

    }

    @FXML
    private void Recarregar(ActionEvent event) throws IOException {
        arvore.raiz = null;
        ta_saida.clear();

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
