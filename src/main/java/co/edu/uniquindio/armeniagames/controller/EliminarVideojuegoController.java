package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Videojuego;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.EliminarVideojuegoSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EliminarVideojuegoController implements Initializable {

    public Main main = new Main();
    public EliminarVideojuegoSubcontroller subcontroller;
    private final ObservableList<TipoFormatoVideojuego> listaTipoFormatoVidejuego = FXCollections.observableArrayList();
    private final ObservableList<TipoGeneroVideojuego> listaTipoGeneroVideojuego = FXCollections.observableArrayList();

    @FXML
    private ComboBox<TipoFormatoVideojuego> comboTipoFormatoVideojuego;

    @FXML
    private TextField txtAnioLanzamiento, txtNombre, txtClasificacion, txtCodigo;

    @FXML
    private Button btnSalir, btnEliminar, btnBuscar;

    @FXML
    private ComboBox<TipoGeneroVideojuego> comboGeneroVideojuego;


    @FXML
    public void eliminar() {
        eliminarVideojuego();
    }

    @FXML
    public void buscar() {
        buscarVideojuego();
    }

    public void eliminarVideojuego() {

        String codigo = txtCodigo.getText();
        boolean videojuego;

        videojuego = subcontroller.eliminarVideojuego(codigo);

        if (videojuego) {
            cerrarVentana();
            main.cargarVentanaAdministrador();
        }
    }

    @FXML
    public void eventoText(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtCodigo)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtCodigo.deletePreviousChar();
            }
            btnBuscar.setDisable(txtCodigo.getText().isEmpty());
        }
    }

    @FXML
    public void salir(){
        cerrarVentana2(btnSalir);
        main.cargarVentanaAdministrador();
    }

    public void buscarVideojuego(){

        Videojuego videojuego;
        String codigo = txtCodigo.getText();
        videojuego = subcontroller.obtenerVideojuego(codigo);

        if (videojuego.getCodigo() != null && videojuego.getNombreVideojuego() != null) {

            txtNombre.setText(videojuego.getNombreVideojuego());
            comboTipoFormatoVideojuego.setValue(videojuego.getTipoFormatoVideojuego());
            comboGeneroVideojuego.setValue(videojuego.getTipoGeneroVideojuego());
            txtClasificacion.setText(String.valueOf(videojuego.getClasificacion()));
            txtAnioLanzamiento.setText(String.valueOf(videojuego.getAnioLanzamiento()));

            btnEliminar.setDisable(false);

        } else {
            btnEliminar.setDisable(true);
        }
    }

    public void cargarTipoGeneroVideojuego() {

        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Accion);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Aventura);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Arcade);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Deportes);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Estrategia);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Simulacion);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Mesa);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Musicales);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Rol);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.Terror);
        listaTipoGeneroVideojuego.add(TipoGeneroVideojuego.MundoAbierto);

        comboGeneroVideojuego.setItems(listaTipoGeneroVideojuego);
    }

    public void cargarTipoFormatoVideojuego() {

        listaTipoFormatoVidejuego.add(TipoFormatoVideojuego.USB);
        listaTipoFormatoVidejuego.add(TipoFormatoVideojuego.Digital);
        listaTipoFormatoVidejuego.add(TipoFormatoVideojuego.Disquet);
        listaTipoFormatoVidejuego.add(TipoFormatoVideojuego.CD);

        comboTipoFormatoVideojuego.setItems(listaTipoFormatoVidejuego);
    }

    public void cerrarVentana() {
        Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new EliminarVideojuegoSubcontroller(factoryController);
        new EliminarVideojuegoController();
        cargarTipoGeneroVideojuego();
        cargarTipoFormatoVideojuego();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
