package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Videojuego;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.GuardarVideojuegoSubcontroller;
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

public class GuardarVideojuegoController implements Initializable {

    public Main main = new Main();
    public GuardarVideojuegoSubcontroller subcontroller;
    private final ObservableList<TipoFormatoVideojuego> listaTipoFormatoVideojuego = FXCollections.observableArrayList();
    private final ObservableList<TipoGeneroVideojuego> listaTipoGeneroVideojuego = FXCollections.observableArrayList();

    @FXML
    private ComboBox<TipoFormatoVideojuego> comboTipoFormatoVideojuego;

    @FXML
    private TextField txtAnioLanzamiento, txtNombre, txtPrecio, txtClasificacion, txtUnidades, txtCodigo;

    @FXML
    private Button btnSalir, btnGuardar;

    @FXML
    private ComboBox<TipoGeneroVideojuego> comboGeneroVideojuego;


    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaAdministrador();
    }

    @FXML
    public void guardar() {
        guardarVideojuego();
    }

    public void guardarVideojuego() {

        Videojuego videojuego;
        Videojuego vid = new Videojuego();

        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        int precio = Integer.parseInt(txtPrecio.getText());
        TipoGeneroVideojuego tipoGeneroVideojuego = comboGeneroVideojuego.getSelectionModel().getSelectedItem();
        TipoFormatoVideojuego tipoFormatoVideojuego = comboTipoFormatoVideojuego.getSelectionModel().getSelectedItem();
        String anioLanzamiento = txtAnioLanzamiento.getText();
        int clasificacion = Integer.parseInt(txtClasificacion.getText());
        int unidades = Integer.parseInt(txtUnidades.getText());

        vid.setCodigo(codigo);
        vid.setNombreVideojuego(nombre);
        vid.setPrecio(precio);
        vid.setTipoGeneroVideojuego(tipoGeneroVideojuego);
        vid.setTipoFormatoVideojuego(tipoFormatoVideojuego);
        vid.setAnioLanzamiento(anioLanzamiento);
        vid.setClasificacion(clasificacion);
        vid.setUnidades(unidades);

        videojuego = subcontroller.guardarVideojuego(vid);

        if (videojuego != null) {
            cerrarVentana(btnGuardar);
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

            txtNombre.setDisable(txtCodigo.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText2(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtNombre)) {
            txtPrecio.setDisable(txtNombre.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText3(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtPrecio)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtPrecio.deletePreviousChar();
            }

            comboTipoFormatoVideojuego.setDisable(txtPrecio.getText().isEmpty());
        }
    }

    @FXML
    public void eventoComboBox(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboTipoFormatoVideojuego)) {
            comboGeneroVideojuego.setDisable(comboTipoFormatoVideojuego.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoComboBox2(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboGeneroVideojuego)) {
            txtAnioLanzamiento.setDisable(comboGeneroVideojuego.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoText4(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtAnioLanzamiento)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtAnioLanzamiento.deletePreviousChar();
            }

            txtClasificacion.setDisable(txtAnioLanzamiento.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText5(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtClasificacion)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtClasificacion.deletePreviousChar();
            }
            txtUnidades.setDisable(txtClasificacion.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText6(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtUnidades)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtUnidades.deletePreviousChar();
            }

            btnGuardar.setDisable(txtUnidades.getText().isEmpty());
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

        listaTipoFormatoVideojuego.add(TipoFormatoVideojuego.USB);
        listaTipoFormatoVideojuego.add(TipoFormatoVideojuego.Digital);
        listaTipoFormatoVideojuego.add(TipoFormatoVideojuego.Disquet);
        listaTipoFormatoVideojuego.add(TipoFormatoVideojuego.CD);

        comboTipoFormatoVideojuego.setItems(listaTipoFormatoVideojuego);
    }

        public void cerrarVentana(Button btn) {
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        }

    public void iniciarDatos(){
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new GuardarVideojuegoSubcontroller(factoryController);
        new GuardarVideojuegoController();
        cargarTipoGeneroVideojuego();
        cargarTipoFormatoVideojuego();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
