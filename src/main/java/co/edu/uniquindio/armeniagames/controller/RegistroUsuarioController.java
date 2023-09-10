package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.main.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistroUsuarioController implements Initializable {

    public Main main = new Main();

    @FXML
    private Button btnSalir;

    @FXML
    private RadioButton radioAdministrador, radioJugador;


    @FXML
    public void salir() {
        main.cargarVentanaLogin();
        cerrarVentana(btnSalir);
    }

    @FXML
    public void eventoAdministrador() {
        main.cargarVentanaRegistroAdministrador();
        cerrarVentana2(radioAdministrador);
    }

    @FXML
    public void eventoJugador() {
        main.cargarVentanaRegistroJugador();
        cerrarVentana2(radioJugador);
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2(RadioButton btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
