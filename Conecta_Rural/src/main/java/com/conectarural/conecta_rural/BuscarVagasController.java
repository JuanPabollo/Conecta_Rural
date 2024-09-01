package com.conectarural.conecta_rural;

import com.conectarural.conecta_rural.exceptions.ElementoJaExistenteException;
import com.conectarural.conecta_rural.models.*;
import com.conectarural.conecta_rural.negocio.ControllerCandidatura;
import com.conectarural.conecta_rural.negocio.ControllerUsuario;
import com.conectarural.conecta_rural.negocio.ControllerUsuarioSessao;
import com.conectarural.conecta_rural.negocio.ControllerVaga;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class BuscarVagasController {

    @FXML
    private Stage stage;

    @FXML
    private Scene scene;

    @FXML
    private Button botaoBuscar;

    @FXML
    private Button botaoVoltar;

    @FXML
    private TableView<Vaga> table;

    @FXML
    private Button candidatarBT;



    @FXML
    private AnchorPane BuscarVagasAnchorPane;

    ControllerUsuarioSessao controllerUsuarioSessao = ControllerUsuarioSessao.getInstance();
    ControllerVaga controllerVaga = ControllerVaga.getInstance();
    ControllerUsuario controllerUsuario = ControllerUsuario.getInstance();
    ControllerCandidatura controllerCandidatura = ControllerCandidatura.getInstance();

    @FXML void initialize(){
        //coluna data
        ;//fim da coluna Data
        TableColumn<Vaga, String> colNomeVaga = new TableColumn<>("Nome");
        colNomeVaga.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNomeVaga()));

        TableColumn<Vaga,String> colEmpresa= new TableColumn<>("Empresa");
        colEmpresa.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmpresa().getNome()));

        TableColumn<Vaga, String> colRemuneracao = new TableColumn<>("Remuneração");
        colRemuneracao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getRemuneracao().toString()));
        TableColumn<Vaga, String> colDescricao = new TableColumn<>("Descrição");
        colDescricao.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescricaoVaga()));

        table.getColumns().addAll(colNomeVaga,colEmpresa,colRemuneracao,colDescricao);
        for(Vaga v : controllerVaga.listar()){
            table.getItems().add(v);
        }
        Estudante e1 = new Estudante("Gabriel","ggermanow279@gmail.com", 8123L,"Rua das mocas,77","teste",11357L, LocalDate.of(2003,01,26),new Curriculo("Teste descrição",3, Curso.CienciaDaComputacao));
        Empresa e2 = new Empresa("Teste","teste@gmail.com",32423423L,"Rua Manoel de Medeiros","teste123","12321-2121",20,"Tecnologia","teste");
        table.getItems().add(new Vaga("Estágio em Desenvolvimento de sistemas","teste",0,1250D,2,e2,001, RegimeContratacao.Estagio, StatusVaga.Aberta));
        //Empresa e2 = new Empresa("Teste","teste@gmail.com",32423423L,"Rua Manoel de Medeiros","teste123","12321-2121",20,"Tecnologia","teste");
        //Estudante e1 = new Estudante("Gabriel","ggermanow279@gmail.com", 8123L,"Rua das mocas,77","teste",11357L, LocalDate.of(2003,01,26),new Curriculo("Teste descrição",3, Curso.CienciaDaComputacao));
        //tabelaCandidatura.getItems().add(new Candidatura(e1,LocalDateTime.now(),new Vaga("Estágio em Desenvolvimento de sistemas","teste",0,1250,2,e2,001,RegimeContratacao.Estagio,StatusVaga.Aberta)));
    }

    Vaga vaga;
    @FXML
    public void clickItem(MouseEvent event) {
        if (event.getClickCount() == 2){
            System.out.println(table.getSelectionModel().getSelectedItem());
            vaga = table.getSelectionModel().getSelectedItem();
        }
    }


    @FXML
    public void acaoBotaoBuscarVagas(ActionEvent event) throws IOException {


        System.out.println("acaoBotaoBuscarVagas");
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("TelaBuscarVagas.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void acaoBotaoVoltar(ActionEvent event) throws IOException{
        System.out.println("acaoBotaoVoltar");
        Parent root = FXMLLoader.load(HelloApplication.class.getResource("TelaPrincipalEstudante.fxml"));
        stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void oncandidatarBTaction(ActionEvent event) throws IOException, ElementoJaExistenteException {
        //Empresa e2 = new Empresa("Teste","teste@gmail.com",32423423L,"Rua Manoel de Medeiros","teste123","12321-2121",20,"Tecnologia","teste");
        Estudante e1 = new Estudante("Gabriel","ggermanow279@gmail.com", 8123L,"Rua das mocas,77","teste",11357L, LocalDate.of(2003,01,26),new Curriculo("Teste descrição",3, Curso.CienciaDaComputacao));
        Candidatura c1 = new Candidatura(e1,LocalDateTime.now(),vaga);
        controllerCandidatura.adicionar(c1);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Candidatura realizada com sucesso!");
        alert.setHeaderText("Sua candidatura foi realizada.");
        alert.setContentText("confirmation");
        alert.show();
    }



}
