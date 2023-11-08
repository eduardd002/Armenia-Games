package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.model.Compra;
import co.edu.uniquindio.armeniagames.model.Jugador;
import co.edu.uniquindio.armeniagames.model.Videojuego;
import co.edu.uniquindio.armeniagames.subcontroller.DevolverVideojuegoSubcontroller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReporteController implements Initializable {

    public Main main = new Main();

    @FXML
    private Button btnSalir;

    @FXML
    public void salir(){
        cerrarVentana2(btnSalir);
        main.cargarVentanaJugador();
    }

    public void cerrarVentana2(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        new ReporteController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}