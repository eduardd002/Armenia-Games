package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Videojuego;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.ActualizarVideojuegoSubcontroller;
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

public class ActualizarVideojuegoController implements Initializable {

    public Main main = new Main();

    public ActualizarVideojuegoSubcontroller subcontroller;
    private final ObservableList<TipoFormatoVideojuego> listaTipoVideojuego = FXCollections.observableArrayList();
    private final ObservableList<TipoGeneroVideojuego> listaTipoGeneroVideojuego = FXCollections.observableArrayList();

    @FXML
    private ComboBox<TipoFormatoVideojuego> comboTipoFormatoVideojuego;

    @FXML
    private Button btnSalir, btnActualizar, btnBuscar;

    @FXML
    private TextField txtNombre, txtPrecio, txtClasificacion, txtUnidadesDisponibles, txtCodigo;

    @FXML
    private ComboBox<TipoGeneroVideojuego> comboGeneroVideojuego;

    @FXML
    public void actualizar() {
        actualizarVideojuego();
    }

    @FXML
    public void salir(){
        cerrarVentana2(btnSalir);
        main.cargarVentanaAdministrador();
    }

    @FXML
    public void buscar() {
        buscarVideojuego();
    }

    public void buscarVideojuego() {

        Videojuego videojuego;
        String codigo = txtCodigo.getText();
        videojuego = subcontroller.obtenerVideojuego(codigo);

        if (videojuego.getCodigo() != null && videojuego.getNombreVideojuego() != null) {

            txtNombre.setDisable(false);
            txtPrecio.setDisable(false);
            comboTipoFormatoVideojuego.setDisable(false);
            comboGeneroVideojuego.setDisable(false);
            txtClasificacion.setDisable(false);
            txtUnidadesDisponibles.setDisable(false);
            btnActualizar.setDisable(false);

            txtNombre.setText(videojuego.getNombreVideojuego());
            txtPrecio.setText(String.valueOf(videojuego.getPrecio()));
            comboTipoFormatoVideojuego.setValue(videojuego.getTipoFormatoVideojuego());
            comboGeneroVideojuego.setValue(videojuego.getTipoGeneroVideojuego());
            txtClasificacion.setText(String.valueOf(videojuego.getClasificacion()));
            txtUnidadesDisponibles.setText(String.valueOf(videojuego.getUnidades()));

        } else {

            txtNombre.setDisable(true);
            txtPrecio.setDisable(true);
            comboTipoFormatoVideojuego.setDisable(true);
            comboGeneroVideojuego.setDisable(true);
            txtClasificacion.setDisable(true);
            txtUnidadesDisponibles.setDisable(true);
            btnActualizar.setDisable(true);

            txtNombre.setText("");
            txtPrecio.setText("");
            comboTipoFormatoVideojuego.setValue(null);
            comboGeneroVideojuego.setValue(null);
            txtClasificacion.setText("");
            txtUnidadesDisponibles.setText("");

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
    public void eventoText2(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtNombre) || evt.equals(txtPrecio) || evt.equals(txtUnidadesDisponibles)
        || evt.equals(txtClasificacion)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtUnidadesDisponibles.deletePreviousChar();
            }

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtClasificacion.deletePreviousChar();
            }

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtPrecio.deletePreviousChar();
            }

            btnActualizar.setDisable(txtNombre.getText().isEmpty() ||
                    txtPrecio.getText().isEmpty() ||
                    txtUnidadesDisponibles.getText().isEmpty() ||
                    txtClasificacion.getText().isEmpty());
        }
    }

    public void actualizarVideojuego() {

        Videojuego videojuego = new Videojuego();
        Videojuego vid = subcontroller.obtenerVideojuego(txtCodigo.getText());

        String codigo = txtCodigo.getText();
        String nombre = txtNombre.getText();
        int precio = Integer.parseInt(txtPrecio.getText());
        TipoFormatoVideojuego formato = comboTipoFormatoVideojuego.getSelectionModel().getSelectedItem();
        TipoGeneroVideojuego genero = comboGeneroVideojuego.getSelectionModel().getSelectedItem();
        int clasificacion = Integer.parseInt(txtClasificacion.getText());
        int unidades = Integer.parseInt(txtUnidadesDisponibles.getText());
        String anio = vid.getAnioLanzamiento();

        videojuego.setCodigo(codigo);
        videojuego.setNombreVideojuego(nombre);
        videojuego.setPrecio(precio);
        videojuego.setTipoFormatoVideojuego(formato);
        videojuego.setTipoGeneroVideojuego(genero);
        videojuego.setClasificacion(clasificacion);
        videojuego.setUnidades(unidades);
        videojuego.setAnioLanzamiento(anio);

        boolean videojuegoActualizado;

        videojuegoActualizado = subcontroller.actualizarVideojuego(videojuego);

        if (videojuegoActualizado) {
            cerrarVentana();
            main.cargarVentanaAdministrador();
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

        listaTipoVideojuego.add(TipoFormatoVideojuego.USB);
        listaTipoVideojuego.add(TipoFormatoVideojuego.Digital);
        listaTipoVideojuego.add(TipoFormatoVideojuego.Disquet);
        listaTipoVideojuego.add(TipoFormatoVideojuego.CD);

        comboTipoFormatoVideojuego.setItems(listaTipoVideojuego);
    }

    public void cerrarVentana() {
        Stage stage = (Stage) this.btnActualizar.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new ActualizarVideojuegoSubcontroller(factoryController);
        new ActualizarVideojuegoController();
        cargarTipoGeneroVideojuego();
        cargarTipoFormatoVideojuego();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
