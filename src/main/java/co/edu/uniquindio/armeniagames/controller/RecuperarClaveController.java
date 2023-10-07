package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.constant.MensajesInformacionConstant;
import co.edu.uniquindio.armeniagames.enumm.TipoUsuario;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Usuario;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.RecuperarClaveSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecuperarClaveController implements Initializable{

    private static int numero;

    public RecuperarClaveSubcontroller subcontroller;
    private final ObservableList<TipoUsuario> listaTipoUsuario = FXCollections.observableArrayList();
    public Main main = new Main();

    @FXML
    private Button btnSalir, btnCambiar, btnEnviar, btnVerificar;

    @FXML
    private TextField txtConfirmacion, txtCorreo, txtClave, txtCodigo;

    @FXML
    private ComboBox<TipoUsuario> comboTipoUsuario;

    @FXML
    private Label lblMensaje;

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaLogin();
    }

    @FXML
    public void enviar() {
        txtCodigo.setDisable(false);
        enviarCodigo();
    }
    @FXML
    public void verificar() {
        verificarCodigo();
    }

    @FXML
    public void cambiar() {
        cambiarClave();
    }

    private void enviarCodigo() {
        int codigo = subcontroller.generarNum();
        numero = codigo;
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        subcontroller.email(mensajes.MENSAJE_RESTABLECER_CLAVE, mensajes.MENSAJE_RESTABLECER_CLAVE_CUERPO, txtCorreo.getText(), codigo);
    }

    @FXML
    public void eventoText(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtCorreo)) {

            comboTipoUsuario.setDisable(txtCorreo.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText3(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtCodigo)) {

            btnVerificar.setDisable(txtCodigo.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText4(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtClave)) {

            txtConfirmacion.setDisable(txtClave.getText().isEmpty());
        }
    }
    @FXML
    public void eventoText5(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtConfirmacion)) {

            btnCambiar.setDisable(txtConfirmacion.getText().isEmpty());
        }
    }



    private void verificarCodigo() {
        int codigo = Integer.parseInt(txtCodigo.getText());
        if(numero == codigo){
            txtClave.setDisable(false);
            lblMensaje.setVisible(false);
        }else{
            lblMensaje.setVisible(true);
            txtClave.setDisable(true);
        }
    }

    @FXML
    public void eventoText2(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtClave)) {
            txtConfirmacion.setDisable(txtClave.getText().isEmpty());
        }
    }

    public void cambiarClave() {

        Usuario usuario = new Usuario();

        String correo = txtCorreo.getText();
        TipoUsuario tipo = comboTipoUsuario.getSelectionModel().getSelectedItem();
        String clave = txtClave.getText();
        String confirmacion = txtConfirmacion.getText();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        MensajesInformacionConstant mensajes2 = new MensajesInformacionConstant();
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\cambiarclave.jpg";

        if(tipo.equals(TipoUsuario.Administrador)) {

            usuario.setClave(clave);
            usuario.setConfirmacionClave(confirmacion);

            boolean cambio = subcontroller.cambiarClaveAdministrador(correo, clave, confirmacion);

            if(cambio){
                subcontroller.email2(mensajes.MENSAJE_CLAVE, mensajes2.INFORMACION_CAMBIAR_CLAVE, correo, img);
                cerrarVentana(btnCambiar);
                main.cargarVentanaLogin();
            }
        }

        if(tipo.equals(TipoUsuario.Jugador)) {

            usuario.setClave(clave);
            usuario.setConfirmacionClave(confirmacion);

            boolean cambio = subcontroller.cambiarClaveJugador(correo, clave, confirmacion);

            if(cambio){
                subcontroller.email2(mensajes.MENSAJE_CLAVE, mensajes2.INFORMACION_CAMBIAR_CLAVE, correo, img);
                cerrarVentana(btnCambiar);
                main.cargarVentanaLogin();
            }
        }
    }

    @FXML
    public void eventoComboBox(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboTipoUsuario)) {
            btnEnviar.setDisable(comboTipoUsuario.getSelectionModel().isEmpty());
        }
    }



    public void cargarTipoUsuario() {

        listaTipoUsuario.add(TipoUsuario.Administrador);
        listaTipoUsuario.add(TipoUsuario.Jugador);

        comboTipoUsuario.setItems(listaTipoUsuario);
    }


    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new RecuperarClaveSubcontroller(factoryController);
        new LoginController();
        cargarTipoUsuario();

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
