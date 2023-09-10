package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.enumm.TipoUsuario;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Administrador;
import co.edu.uniquindio.armeniagames.model.Jugador;
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
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RecuperarClaveController implements Initializable{

    public RecuperarClaveSubcontroller subcontroller;
    private final ObservableList<TipoUsuario> listaTipoUsuario = FXCollections.observableArrayList();
    public Main main = new Main();

    @FXML
    private Button btnSalir, btnCambiar, btnBuscar;

    @FXML
    private TextField txtNombre, txtConfirmacion, txtApellido, txtDocumento, txtTelefono, txtCorreo,
            txtClave;

    @FXML
    private ComboBox<TipoUsuario> comboTipoUsuario;


    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaLogin();
    }

    @FXML
    public void cambiar() {
        cambiarClave();
    }

    @FXML
    public void buscar() {
        buscarUsuario();
    }

    public void buscarUsuario() {

        Administrador administrador;
        Jugador jugador;
        String documento = txtDocumento.getText();
        TipoUsuario tipo = comboTipoUsuario.getSelectionModel().getSelectedItem();

        if(tipo.equals(TipoUsuario.Administrador)) {

            administrador = subcontroller.traerDatosAdministrador(documento);

            if (administrador != null) {

                txtNombre.setText(administrador.getNombrePersona());
                txtApellido.setText(administrador.getApellido());
                txtCorreo.setText(administrador.getCorreo());
                txtTelefono.setText(administrador.getTelefono());

                txtClave.setDisable(false);

            } else{

                txtNombre.setText("");
                txtApellido.setText("");
                txtCorreo.setText("");
                txtTelefono.setText("");

                txtClave.setDisable(true);
                txtConfirmacion.setDisable(true);

            }
        } else {

            jugador = subcontroller.traerDatosJugador(documento);

            if (jugador != null) {

                txtNombre.setText(jugador.getNombrePersona());
                txtApellido.setText(jugador.getApellido());
                txtCorreo.setText(jugador.getCorreo());
                txtTelefono.setText(jugador.getTelefono());

                txtClave.setDisable(false);

            } else {

                txtNombre.setText("");
                txtApellido.setText("");
                txtCorreo.setText("");
                txtTelefono.setText("");

                txtClave.setDisable(true);
                txtConfirmacion.setDisable(true);

            }
        }
    }

    @FXML
    public void eventoText(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtDocumento)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtDocumento.deletePreviousChar();
            }

            comboTipoUsuario.setDisable(txtDocumento.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText2(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtClave)) {
            txtConfirmacion.setDisable(txtClave.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText3(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtConfirmacion)) {
            btnCambiar.setDisable(txtConfirmacion.getText().isEmpty());
        }
    }

    @FXML
    public void eventoComboBox(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboTipoUsuario)) {
            btnBuscar.setDisable(comboTipoUsuario.getSelectionModel().isEmpty());
        }
    }

    public void cambiarClave() {

        Usuario usuario = new Usuario();

        String documento = txtDocumento.getText();
        TipoUsuario tipo = comboTipoUsuario.getSelectionModel().getSelectedItem();
        String clave = txtClave.getText();
        String confirmacion = txtConfirmacion.getText();

        if(tipo.equals(TipoUsuario.Administrador)) {

            usuario.setClave(clave);
            usuario.setConfirmacionClave(confirmacion);

            boolean cambio = subcontroller.cambiarClaveAdministrador(documento, clave, confirmacion);

            if(cambio){
                cerrarVentana(btnCambiar);
                main.cargarVentanaAdministrador();
            }
        }

        if(tipo.equals(TipoUsuario.Jugador)) {

            usuario.setClave(clave);
            usuario.setConfirmacionClave(confirmacion);

            boolean cambio = subcontroller.cambiarClaveJugador(documento, clave, confirmacion);

            if(cambio){
                cerrarVentana(btnCambiar);
                main.cargarVentanaJugador();
            }
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
