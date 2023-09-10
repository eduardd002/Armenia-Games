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

public class DevolverVideojuegoController implements Initializable {

    public Main main = new Main();

    public DevolverVideojuegoSubcontroller subcontroller;

    @FXML
    private Button btnSalir, btnDevolver;

    @FXML
    private TextField txtFactura;

    @FXML
    public void salir(){
        cerrarVentana2(btnSalir);
        main.cargarVentanaAdministrador();
    }

    public void actualizarHistorial(String lector, int librosLeidos) {
        subcontroller.actualizarHistorial(lector, librosLeidos);
    }

    @FXML
    public void devolver() {
        devolverAlquiler();
    }

    public void devolverAlquiler() {

        int factura = Integer.parseInt(txtFactura.getText());
        ArrayList<Jugador> jugador = subcontroller.traerJugadores();
        ArrayList<Compra> compras = subcontroller.traerCompras();
        ArrayList<Videojuego> videojuegos = subcontroller.traerVideojuegos();

        boolean devuelta;

        devuelta = subcontroller.devolverCompra(factura);

        if(devuelta){
            for (Compra copm : compras) {
                for (Jugador jug : jugador) {
                    for (Videojuego vd : videojuegos){
                        if(copm.getCodigo().equals(vd.getCodigo()) &&
                                copm.getJugador().equals(jug.getNombrePersona())){
                            actualizarHistorial(jug.getDocumento(), jug.getVideojuegosComprados() - 1);
                        }
                    }
                }
            }
            cerrarVentana();
            main.cargarVentanaAdministrador();
        }
    }

    @FXML
    public void eventoText(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtFactura)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtFactura.deletePreviousChar();
            }
            btnDevolver.setDisable(txtFactura.getText().isEmpty());
        }
    }

    public void cerrarVentana() {
        Stage stage = (Stage) this.btnDevolver.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new DevolverVideojuegoSubcontroller(factoryController);
        new DevolverVideojuegoController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}