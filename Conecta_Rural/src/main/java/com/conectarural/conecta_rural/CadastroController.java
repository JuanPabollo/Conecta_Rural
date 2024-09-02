package com.conectarural.conecta_rural;


import com.conectarural.conecta_rural.exceptions.ElementoJaExistenteException;
import com.conectarural.conecta_rural.models.Empresa;
import com.conectarural.conecta_rural.models.Estudante;
import com.conectarural.conecta_rural.models.Usuario;
import com.conectarural.conecta_rural.negocio.ControllerUsuario;
import com.conectarural.conecta_rural.negocio.ControllerUsuarioSessao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CadastroController {
    @FXML
    private Button cadastrarBT;

    @FXML
    private Button redirecionarLoginBT;

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField cnpjField;
    @FXML
    private TextField telefoneField;
    @FXML
    private TextField enderecoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField areaDaEmpresaField;
    @FXML
    private TextArea descricaoArea;
    @FXML
    private TextField quantidadeDeFuncionariosField;
    @FXML
    private ChoiceBox<String> escolhaPerfil = new ChoiceBox<>();

    ControllerUsuarioSessao controladorSessao = ControllerUsuarioSessao.getInstance();
    ControllerUsuario controladorUsuario = ControllerUsuario.getInstance();

    @FXML
    public void initialize() {
        escolhaPerfil.getItems().add("Estudante");
        escolhaPerfil.getItems().add("Empresa");
    }

    @FXML
    public void cadastrarOnAction(ActionEvent event) throws IOException, ElementoJaExistenteException {
//        Parent parent = FXMLLoader.load(getClass().getResource("CadastroRealizado.fxml"));

        //Scene mscene = new Scene(parent, 600, 400 );
        //Stage nstage = new Stage();
        //nstage.setScene(mscene);
       // nstage.show();

        String nome = nameField.getText();
        String email = emailField.getText();
        String cnpj = cnpjField.getText();
        String telefone = telefoneField.getText();
        String endereco = enderecoField.getText();
        String password = passwordField.getText();
        String escolha = escolhaPerfil.getSelectionModel().getSelectedItem();
        String quantidadeFuncionarios = quantidadeDeFuncionariosField.getText();
        String areaDaEmpresa = areaDaEmpresaField.getText();
        String descricao = descricaoArea.getText();


        if(escolha.equals("Estudante")){
            Estudante estudante = new Estudante(nome,email,telefone,endereco,password,cnpj);
            //controladorSessao.setUsuarioLogado(estudante);
            controladorUsuario.adicionar(estudante);
        }else{
            Empresa empresa = new Empresa(nome,email,telefone,endereco,password,cnpj, quantidadeFuncionarios, areaDaEmpresa, descricao);
            //controladorSessao.setUsuarioLogado(empresa);
            controladorUsuario.adicionar(empresa);
        }


        Parent root = FXMLLoader.load(HelloApplication.class.getResource("CadastroRealizado.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @FXML
    public void redirecionarLoginOnAction(ActionEvent event) throws IOException{
//        Parent parent = FXMLLoader.load(getClass().getResource("Login.fxml"));

        //Scene mscene = new Scene(parent, 600, 400 );
        //Stage nstage = new Stage();
        //nstage.setScene(mscene);
       // nstage.show();
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("Login.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

    //@Override

}

