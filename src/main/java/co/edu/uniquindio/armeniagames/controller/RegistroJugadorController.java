package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.enumm.*;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Jugador;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.RegistroJugadorSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.input.KeyEvent;

public class RegistroJugadorController implements Initializable {

    public Main main = new Main();
    public RegistroJugadorSubcontroller subcontroller;
    private final ObservableList<TipoEstadoCivil> listaTipoEstadoCivil = FXCollections.observableArrayList();
    private final ObservableList<TipoGenero> listaTipoGenero = FXCollections.observableArrayList();

    @FXML
    private CheckBox checkLey;

    @FXML
    private TextField txtNombre, txtConfirmacion, txtTelefono, txtCorreo,
            txtDocumento,
            txtApellido, txtContrasenia;

    @FXML
    private DatePicker dateNacimiento;

    @FXML
    private Button btnRegresar, btnRegistro;

    @FXML
    private ComboBox<TipoEstadoCivil> comboEstado;

    @FXML
    private ComboBox<TipoGenero> comboTipoGenero;

    @FXML
    public void regresar() {
        main.cargarVentanaRegistroUsuario();
        cerrarVentana(btnRegresar);
    }

    @FXML
    public void registro() {
        registroJugador();
    }

    @FXML
    public void eventoText(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtDocumento)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtDocumento.deletePreviousChar();
            }

            txtNombre.setDisable(txtDocumento.getText().isEmpty());
        }
    }

    @FXML
    public void eventoCheck() {
        btnRegistro.setDisable(false);
    }

    @FXML
    public void eventoText2(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtNombre)) {

            if (Character.isDigit(event.getCharacter().charAt(0))) {
                txtNombre.deletePreviousChar();
            }
            txtApellido.setDisable(txtNombre.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText3(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtApellido)) {

            if (Character.isDigit(event.getCharacter().charAt(0))) {
                txtApellido.deletePreviousChar();
            }

            dateNacimiento.setDisable(txtApellido.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText4(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(dateNacimiento)) {
            comboTipoGenero.setDisable(dateNacimiento.getValue() == null);
        }
    }

    @FXML
    public void eventoComboBox(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboTipoGenero)) {
            comboEstado.setDisable(comboTipoGenero.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoComboBox2(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboEstado)) {
            txtTelefono.setDisable(comboEstado.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoText5(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtTelefono)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtTelefono.deletePreviousChar();
            }
            txtCorreo.setDisable(txtTelefono.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText6(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtCorreo)) {
            txtContrasenia.setDisable(!txtCorreo.getText().contains("@gmail.com"));
        }
    }

    @FXML
    public void eventoText7(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtContrasenia)) {
            txtConfirmacion.setDisable(txtContrasenia.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText8(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtConfirmacion)) {
            checkLey.setDisable(txtConfirmacion.getText().isEmpty());
        }
    }

    public void registroJugador() {

        Jugador jugador;
        Jugador jug = new Jugador();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();

        String documento = txtDocumento.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        LocalDate nacimiento = dateNacimiento.getValue();
        TipoEstadoCivil estadoCivil = comboEstado.getSelectionModel().getSelectedItem();
        TipoGenero tipoGenero = comboTipoGenero.getSelectionModel().getSelectedItem();
        String correo = txtCorreo.getText();
        String clave = txtContrasenia.getText();
        String confirmacion = txtConfirmacion.getText();
        TipoUsuario usuario = TipoUsuario.Jugador;
        String telefono = txtTelefono.getText();
        int intentos = 0;
        TipoRestriccion tipoRestriccion = TipoRestriccion.CONFIRMADO;

        int jugados = 0;
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\registrocomprador.jpg";

        jug.setDocumento(documento);
        jug.setNombrePersona(nombre);
        jug.setApellido(apellido);
        jug.setFechaNacimiento(nacimiento);
        jug.setTipoEstadoCivil(estadoCivil);
        jug.setTipoGenero(tipoGenero);
        jug.setCorreo(correo);
        jug.setClave(clave);
        jug.setConfirmacionClave(confirmacion);
        jug.setTipoUsuario(usuario);
        jug.setTelefono(telefono);

        jug.setVideojuegosComprados(jugados);

        jug.setTipoRestriccion(tipoRestriccion);
        jug.setIntentos(intentos);

        jug.setImagen("C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\logo\\usuario.png");

        jugador = subcontroller.registrarJugador(jug);

        if (jugador != null) {
            subcontroller.email(mensajes.MENSAJE_REGISTRO, mensajes.MENSAJE_REGISTRO_CUERPO, correo, img);
            cerrarVentana(btnRegistro);
            main.cargarVentanaJugador();
        }
    }

    public void cargarTipoEstadoCivil() {

        listaTipoEstadoCivil.add(TipoEstadoCivil.Casado);
        listaTipoEstadoCivil.add(TipoEstadoCivil.Divorciado);
        listaTipoEstadoCivil.add(TipoEstadoCivil.Soltero);
        listaTipoEstadoCivil.add(TipoEstadoCivil.UnionLibre);
        listaTipoEstadoCivil.add(TipoEstadoCivil.Viudo);

        comboEstado.setItems(listaTipoEstadoCivil);
    }

    public void cargarTipoGenero() {

        listaTipoGenero.add(TipoGenero.Masculino);
        listaTipoGenero.add(TipoGenero.Femenino);
        listaTipoGenero.add(TipoGenero.Otro);

        comboTipoGenero.setItems(listaTipoGenero);
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos() {
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new RegistroJugadorSubcontroller(factoryController);
        new RegistroAdministradorController();
        cargarTipoEstadoCivil();
        cargarTipoGenero();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
