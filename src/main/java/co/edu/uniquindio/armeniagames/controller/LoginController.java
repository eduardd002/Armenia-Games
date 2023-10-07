package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.enumm.TipoUsuario;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Usuario;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.LoginSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public Main main = new Main();

    public LoginSubcontroller subcontroller;
    private final ObservableList<TipoUsuario> listaTipoUsuario = FXCollections.observableArrayList();

    @FXML
    private ComboBox<TipoUsuario> comboUsuario;

    @FXML
    private Button btnChat, btnIngresar, btnRegistro;

    @FXML
    private Label lblOlvidoClave;

    @FXML
    private TextField txtContrasenia, txtUsuario;

    @FXML
    public void registro() {
        cerrarVentana(btnRegistro);
        main.cargarVentanaRegistroUsuario();
    }

    @FXML
    public void ingresar() {
        ingresarUsuario();
    }

    @FXML
    public void recuperarClave() {
        cerrarVentana2(lblOlvidoClave);
        main.cargarVentanaRecuperarClave();
    }

    @FXML
    public void chat() {
        main.cargarVentanaChat();
        cerrarVentana(btnChat);
    }

    @FXML
    public void salir() {
        System.exit(0);
    }

    @FXML
    public void eventoCombo() {
        txtUsuario.setDisable(false);
    }

    @FXML
    public void eventoText(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtUsuario)) {
            txtContrasenia.setDisable(!txtUsuario.getText().contains("@gmail.com"));
        }
    }

    @FXML
    public void eventoText2(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtContrasenia)) {
            btnIngresar.setDisable(txtContrasenia.getText().isEmpty());
        }
    }

    public void ingresarUsuario(){

        Usuario usuario;
        Usuario usu = new Usuario();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();

        String email = txtUsuario.getText();
        String clave = txtContrasenia.getText();
        TipoUsuario tipo = comboUsuario.getSelectionModel().getSelectedItem();

        usu.setCorreo(email);
        usu.setClave(clave);
        usu.setTipoUsuario(tipo);

        usuario = subcontroller.login(usu);
        String imgJug = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\logincomprador.jpg";
        String imgAdmin = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\loginadministrador.jpg";

        if(tipo.equals(TipoUsuario.Jugador)) {
            if (usuario != null) {
                subcontroller.email(mensajes.MENSAJE_INICIO_SESION, mensajes.MENSAJE_INICIO_SESION_CUERPO, email, imgJug);
                main.cargarVentanaJugador();
                cerrarVentana(btnIngresar);
            }else{
                subcontroller.establecerIntentos(email);
            }
        }else if(tipo.equals(TipoUsuario.Administrador)) {
            if (usuario != null) {
                subcontroller.email(mensajes.MENSAJE_INICIO_SESION, mensajes.MENSAJE_INICIO_SESION_CUERPO, email, imgAdmin);
                main.cargarVentanaAdministrador();
                cerrarVentana(btnIngresar);
            }
        }
    }

    public void cargarTipoUsuario() {

        listaTipoUsuario.add(TipoUsuario.Administrador);
        listaTipoUsuario.add(TipoUsuario.Jugador);

        comboUsuario.setItems(listaTipoUsuario);
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2(Label lbl) {
        Stage stage = (Stage) lbl.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new LoginSubcontroller(factoryController);
        new LoginController();
        cargarTipoUsuario();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}