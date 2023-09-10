package co.edu.uniquindio.armeniagames.controller;

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
    private final ObservableList<TipoCuenta> listaTipoCuenta = FXCollections.observableArrayList();
    private final ObservableList<TipoResidencia> listaTipoResidencia = FXCollections.observableArrayList();
    private final ObservableList<TipoDepartamento> listaTipoDepartamento = FXCollections.observableArrayList();
    private final ObservableList<String> listaTipoMunicipios = FXCollections.observableArrayList();

    private TipoBanco tipoBanco;

    @FXML
    private CheckBox checkLey, checkLey2;

    @FXML
    private TextField txtNombre, txtConfirmacion, txtCuenta, txtTitular, txtTelefono, txtCorreo,
            txtDocumento, txtBarrio, txtDireccion, txtPostal,
            txtApellido, txtContrasenia;

    @FXML
    private ComboBox<TipoCuenta> comboTipoCuenta;

    @FXML
    private DatePicker dateNacimiento, dateCaducidad;

    @FXML
    private Button btnRegresar, btnRegistro;

    @FXML
    private ComboBox<TipoEstadoCivil> comboEstado;

    @FXML
    private ComboBox<TipoResidencia> comboResidencia;

    @FXML
    private ComboBox<TipoDepartamento> comboDepartamento;

    @FXML
    private ComboBox<String> comboMunicipio;

    @FXML
    private ComboBox<TipoGenero> comboTipoGenero;

    @FXML
    private RadioButton radioBancolombia, radioDavivienda, radioOccidente, radioBogota;


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
    public void eventoRadioDavivienda() {
        if (radioDavivienda.isSelected()) {
            tipoBanco = TipoBanco.Davivienda;
            radioBogota.setDisable(true);
            radioOccidente.setDisable(true);
            radioBancolombia.setDisable(true);
            comboTipoCuenta.setDisable(false);
        }else{
            radioBogota.setDisable(false);
            radioOccidente.setDisable(false);
            radioBancolombia.setDisable(false);
            comboTipoCuenta.setDisable(true);
        }
    }

    @FXML
    public void eventoRadioBancolombia() {
        if (radioBancolombia.isSelected()) {
            tipoBanco = TipoBanco.Bancolombia;
            radioBogota.setDisable(true);
            radioOccidente.setDisable(true);
            radioDavivienda.setDisable(true);
            comboTipoCuenta.setDisable(false);
        }else{
            radioBogota.setDisable(false);
            radioOccidente.setDisable(false);
            radioDavivienda.setDisable(false);
            comboTipoCuenta.setDisable(true);
        }
    }

    @FXML
    public void eventoRadioBogota() {
        if (radioBogota.isSelected()) {
            tipoBanco = TipoBanco.Bogota;
            radioBancolombia.setDisable(true);
            radioOccidente.setDisable(true);
            radioDavivienda.setDisable(true);
            comboTipoCuenta.setDisable(false);
        }else{
            radioBancolombia.setDisable(false);
            radioOccidente.setDisable(false);
            radioDavivienda.setDisable(false);
            comboTipoCuenta.setDisable(true);
        }
    }

    @FXML
    public void eventoRadioOccidente() {
        if (radioOccidente.isSelected()) {
            tipoBanco = TipoBanco.Occidente;
            radioBancolombia.setDisable(true);
            radioDavivienda.setDisable(true);
            radioBogota.setDisable(true);
            comboTipoCuenta.setDisable(false);
        }else{
            radioBancolombia.setDisable(false);
            radioDavivienda.setDisable(false);
            radioBogota.setDisable(false);
            comboTipoCuenta.setDisable(true);
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

            txtNombre.setDisable(txtDocumento.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText2(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtNombre)) {

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtNombre.deletePreviousChar();
            }
            txtApellido.setDisable(txtNombre.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText3(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtApellido)) {

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
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

    @FXML
    public void eventoCheck() {

        if(checkLey.isSelected()){
            radioBancolombia.setDisable(false);
            radioDavivienda.setDisable(false);
            radioOccidente.setDisable(false);
            radioBogota.setDisable(false);
        }else{
            radioBancolombia.setDisable(true);
            radioDavivienda.setDisable(true);
            radioOccidente.setDisable(true);
            radioBogota.setDisable(true);
        }
    }

    @FXML
    public void eventoComboBox3(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboTipoCuenta)) {
            txtCuenta.setDisable(comboTipoCuenta.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoText9(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtCuenta)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtCuenta.deletePreviousChar();
            }

            txtTitular.setDisable(txtCuenta.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText10(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtTitular)) {

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtTitular.deletePreviousChar();
            }

            dateCaducidad.setDisable(txtTitular.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText11(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(dateCaducidad)) {
            checkLey2.setDisable(dateCaducidad.getValue() == null);
        }
    }

    @FXML
    public void eventoCheck2() {
        comboResidencia.setDisable(!checkLey.isSelected());
    }

    @FXML
    public void eventoComboBox4(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboResidencia)) {
            txtPostal.setDisable(comboResidencia.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoText12(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtPostal)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtPostal.deletePreviousChar();
            }

            txtBarrio.setDisable(txtPostal.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText13(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtBarrio)) {
            txtDireccion.setDisable(txtBarrio.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText14(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtDireccion)) {
            comboDepartamento.setDisable(txtDireccion.getText().isEmpty());
        }
    }

    @FXML
    public void eventoComboBox5() {

        btnRegistro.setDisable(comboMunicipio.getSelectionModel().getSelectedItem() == null);
    }

    @FXML
    public void eventoDepartamento() {
        eventoDepartamentoJugador();
    }

    public void registroJugador() {

        Jugador jugador;
        Jugador jug = new Jugador();

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

        TipoBanco banco = tipoBanco;
        TipoCuenta cuenta = comboTipoCuenta.getSelectionModel().getSelectedItem();
        String numCuenta = txtCuenta.getText();
        LocalDate caducidad = dateCaducidad.getValue();
        String titular = txtTitular.getText();

        TipoResidencia tipoResidencia = comboResidencia.getSelectionModel().getSelectedItem();
        String postal = txtPostal.getText();
        String direccion = txtDireccion.getText();
        String barrio = txtBarrio.getText();
        TipoDepartamento departamento = comboDepartamento.getSelectionModel().getSelectedItem();
        String municipio = comboMunicipio.getSelectionModel().getSelectedItem();
        int jugados = 0;

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

        jug.setTipoBanco(banco);
        jug.setTipoCuenta(cuenta);
        jug.setNumeroCuenta(numCuenta);
        jug.setFechaCaducidad(caducidad);
        jug.setTitular(titular);

        jug.setTipoResidencia(tipoResidencia);
        jug.setCodigoPostal(postal);
        jug.setDireccion(direccion);
        jug.setBarrio(barrio);
        jug.setTipoDepartamento(departamento);
        jug.setMunicipio(municipio);
        jug.setVideojuegosComprados(jugados);

        jug.setImagen("C:\\Users\\eduar\\IdeaProjects\\ArmeniaGames\\src\\main\\resources\\logo\\usuario.png");

        jugador = subcontroller.registrarJugador(jug);

        if (jugador != null) {
            cerrarVentana(btnRegistro);
            main.cargarVentanaJugador();
        }
    }

    public void eventoDepartamentoJugador() {

        comboMunicipio.setDisable(false);
        TipoDepartamento departamento = comboDepartamento.getSelectionModel().getSelectedItem();

        if (departamento.equals(TipoDepartamento.Antioquia)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Abejorral");
            listaTipoMunicipios.add("Abriaquí");
            listaTipoMunicipios.add("Alejandría");
            listaTipoMunicipios.add("Amagá");
            listaTipoMunicipios.add("Amalfi");
            listaTipoMunicipios.add("Andes");
            listaTipoMunicipios.add("Angelópolis");
            listaTipoMunicipios.add("Angostura");
            listaTipoMunicipios.add("Anorí");
            listaTipoMunicipios.add("Anza");
            listaTipoMunicipios.add("Apartadó");
            listaTipoMunicipios.add("Arboletes");
            listaTipoMunicipios.add("Argelia");
            listaTipoMunicipios.add("Armenia");
            listaTipoMunicipios.add("Barbosa");
            listaTipoMunicipios.add("Belmira");
            listaTipoMunicipios.add("Bello");
            listaTipoMunicipios.add("Betania");
            listaTipoMunicipios.add("Betulia");
            listaTipoMunicipios.add("Bolívar");
            listaTipoMunicipios.add("Briceño");
            listaTipoMunicipios.add("Buriticá");
            listaTipoMunicipios.add("Cáceres");
            listaTipoMunicipios.add("Caicedo");
            listaTipoMunicipios.add("Caldas");
            listaTipoMunicipios.add("Campamento");
            listaTipoMunicipios.add("Cañasgordas");
            listaTipoMunicipios.add("Caracolí");
            listaTipoMunicipios.add("Caramanta");
            listaTipoMunicipios.add("Carepa");
            listaTipoMunicipios.add("Carmen de Viboral");
            listaTipoMunicipios.add("Carolina");
            listaTipoMunicipios.add("Caucasia");
            listaTipoMunicipios.add("Chigorodó");
            listaTipoMunicipios.add("Cisneros");
            listaTipoMunicipios.add("Cocorná");
            listaTipoMunicipios.add("Concepción");
            listaTipoMunicipios.add("Concordia");
            listaTipoMunicipios.add("Copacabana");
            listaTipoMunicipios.add("Dabeiba");
            listaTipoMunicipios.add("Donmatías");
            listaTipoMunicipios.add("Ebéjico");
            listaTipoMunicipios.add("El Bagre");
            listaTipoMunicipios.add("Entrerríos");
            listaTipoMunicipios.add("Envigado");
            listaTipoMunicipios.add("Fredonia");
            listaTipoMunicipios.add("Frontino");
            listaTipoMunicipios.add("Giraldo");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.Atlantico)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Baranoa");
            listaTipoMunicipios.add("Barranquilla");
            listaTipoMunicipios.add("Campo de la Cruz");
            listaTipoMunicipios.add("Candelaria");
            listaTipoMunicipios.add("Galapa");
            listaTipoMunicipios.add("Juan de Acosta");
            listaTipoMunicipios.add("Luruaco");
            listaTipoMunicipios.add("Malambo");
            listaTipoMunicipios.add("Manatí");
            listaTipoMunicipios.add("Palmar de Varela");
            listaTipoMunicipios.add("Piojó");
            listaTipoMunicipios.add("Polonuevo");
            listaTipoMunicipios.add("Ponedera");
            listaTipoMunicipios.add("Puerto Colombia");
            listaTipoMunicipios.add("Repelón");
            listaTipoMunicipios.add("Sabanagrande");
            listaTipoMunicipios.add("Sabanalarga");
            listaTipoMunicipios.add("Santa Lucía");
            listaTipoMunicipios.add("Santo Tomás");
            listaTipoMunicipios.add("Soledad");
            listaTipoMunicipios.add("Suan");
            listaTipoMunicipios.add("Tubará");
            listaTipoMunicipios.add("Usiacurí");

            comboMunicipio.setItems(listaTipoMunicipios);
        }

        if (departamento.equals(TipoDepartamento.Bolivar)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Achi");
            listaTipoMunicipios.add("Altos del Rosario");
            listaTipoMunicipios.add("Arenal");
            listaTipoMunicipios.add("Arjona");
            listaTipoMunicipios.add("Arroyohondo");
            listaTipoMunicipios.add("Barranco de Loba");
            listaTipoMunicipios.add("Calamar");
            listaTipoMunicipios.add("Cantagallo");
            listaTipoMunicipios.add("Cartagena");
            listaTipoMunicipios.add("Cicuco");
            listaTipoMunicipios.add("Clemencia");
            listaTipoMunicipios.add("Cordoba");
            listaTipoMunicipios.add("El Carmen de Bolívar");
            listaTipoMunicipios.add("El Guamo");
            listaTipoMunicipios.add("El Peñón");
            listaTipoMunicipios.add("Hatillo de Loba");
            listaTipoMunicipios.add("Magangué");
            listaTipoMunicipios.add("Mahates");
            listaTipoMunicipios.add("Margarita");
            listaTipoMunicipios.add("María la Baja");
            listaTipoMunicipios.add("Montecristo");
            listaTipoMunicipios.add("Mompós");
            listaTipoMunicipios.add("Morales");
            listaTipoMunicipios.add("Pinillos");
            listaTipoMunicipios.add("Regidor");
            listaTipoMunicipios.add("Río Viejo");
            listaTipoMunicipios.add("San Cristóbal");
            listaTipoMunicipios.add("San Estanislao");
            listaTipoMunicipios.add("San Fernando");
            listaTipoMunicipios.add("San Jacinto");
            listaTipoMunicipios.add("San Jacinto del Cauca");
            listaTipoMunicipios.add("San Juan Nepomuceno");
            listaTipoMunicipios.add("San Martín de Loba");
            listaTipoMunicipios.add("San Pablo");
            listaTipoMunicipios.add("Santa Catalina");
            listaTipoMunicipios.add("Santa Rosa");
            listaTipoMunicipios.add("Santa Rosa del Sur");
            listaTipoMunicipios.add("Simití");
            listaTipoMunicipios.add("Soplaviento");
            listaTipoMunicipios.add("Talaigua Nuevo");
            listaTipoMunicipios.add("Tiquisio");
            listaTipoMunicipios.add("Turbaco");
            listaTipoMunicipios.add("Turbana");
            listaTipoMunicipios.add("Villanueva");
            listaTipoMunicipios.add("Zambrano");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.Boyaca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Almeida");
            listaTipoMunicipios.add("Aquitania");
            listaTipoMunicipios.add("Arcabuco");
            listaTipoMunicipios.add("Belén");
            listaTipoMunicipios.add("Berbeo");
            listaTipoMunicipios.add("Betéitiva");
            listaTipoMunicipios.add("Boavita");
            listaTipoMunicipios.add("Boyacá");
            listaTipoMunicipios.add("Briceño");
            listaTipoMunicipios.add("Buenavista");
            listaTipoMunicipios.add("Busbanzá");
            listaTipoMunicipios.add("Caldas");
            listaTipoMunicipios.add("Campohermoso");
            listaTipoMunicipios.add("Cerinza");
            listaTipoMunicipios.add("Chinavita");
            listaTipoMunicipios.add("Chiquinquirá");
            listaTipoMunicipios.add("Chiscas");
            listaTipoMunicipios.add("Chita");
            listaTipoMunicipios.add("Chitaraque");
            listaTipoMunicipios.add("Chivatá");
            listaTipoMunicipios.add("Chíquiza");
            listaTipoMunicipios.add("Ciéneaga");
            listaTipoMunicipios.add("Cómbita");
            listaTipoMunicipios.add("Coper");
            listaTipoMunicipios.add("Corrales");
            listaTipoMunicipios.add("Covarachía");
            listaTipoMunicipios.add("Cubará");
            listaTipoMunicipios.add("Cucaita");
            listaTipoMunicipios.add("Cuítiva");
            listaTipoMunicipios.add("Duitama");
            listaTipoMunicipios.add("El Cocuy");
            listaTipoMunicipios.add("El Espino");
            listaTipoMunicipios.add("Firavitoba");
            listaTipoMunicipios.add("Floresta");
            listaTipoMunicipios.add("Gachantivá");
            listaTipoMunicipios.add("Gameza");
            listaTipoMunicipios.add("Garagoa");
            listaTipoMunicipios.add("Guacamayas");
            listaTipoMunicipios.add("Güicán");
            listaTipoMunicipios.add("Iza");
            listaTipoMunicipios.add("Jenesano");
            listaTipoMunicipios.add("Jericó");
            listaTipoMunicipios.add("La Capilla");
            listaTipoMunicipios.add("La Uvita");
            listaTipoMunicipios.add("La Victoria");
            listaTipoMunicipios.add("Labranza Grande");
            listaTipoMunicipios.add("Macanal");
            listaTipoMunicipios.add("Maripí");
            listaTipoMunicipios.add("Miraflores");

            comboMunicipio.setItems(listaTipoMunicipios);
        }
        if (departamento.equals(TipoDepartamento.Caldas)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Aguadas");
            listaTipoMunicipios.add("Anserma");
            listaTipoMunicipios.add("Aranzazu");
            listaTipoMunicipios.add("Belalcázar");
            listaTipoMunicipios.add("Chinchiná");
            listaTipoMunicipios.add("Filadelfia");
            listaTipoMunicipios.add("La Dorada");
            listaTipoMunicipios.add("La Merced");
            listaTipoMunicipios.add("Manizales");
            listaTipoMunicipios.add("Manzanares");
            listaTipoMunicipios.add("Marmato");
            listaTipoMunicipios.add("Marquetalia");
            listaTipoMunicipios.add("Marulanda");
            listaTipoMunicipios.add("Neira");
            listaTipoMunicipios.add("Norcasia");
            listaTipoMunicipios.add("Pácora");
            listaTipoMunicipios.add("Palestina");
            listaTipoMunicipios.add("Pensilvania");
            listaTipoMunicipios.add("Riosucio");
            listaTipoMunicipios.add("Risaralda");
            listaTipoMunicipios.add("Salamina");
            listaTipoMunicipios.add("Samaná");
            listaTipoMunicipios.add("San José");
            listaTipoMunicipios.add("Supía");
            listaTipoMunicipios.add("Victoria");
            listaTipoMunicipios.add("Villamaría");
            listaTipoMunicipios.add("Viterbo");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Casanare)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Aguazul");
            listaTipoMunicipios.add("Chámeza");
            listaTipoMunicipios.add("Hato Corozal");
            listaTipoMunicipios.add("La Salina");
            listaTipoMunicipios.add("Maní");
            listaTipoMunicipios.add("Monterrey");
            listaTipoMunicipios.add("Nunchía");
            listaTipoMunicipios.add("Orocue");
            listaTipoMunicipios.add("Paz de Ariporo");
            listaTipoMunicipios.add("Pore");
            listaTipoMunicipios.add("Recetor");
            listaTipoMunicipios.add("Sabanalarga");
            listaTipoMunicipios.add("Sácama");
            listaTipoMunicipios.add("San Luis de Palenque");
            listaTipoMunicipios.add("Támara");
            listaTipoMunicipios.add("Tauramena");
            listaTipoMunicipios.add("Trinidad");
            listaTipoMunicipios.add("Villanueva");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Cauca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Almaguer");
            listaTipoMunicipios.add("Argelia");
            listaTipoMunicipios.add("Balboa");
            listaTipoMunicipios.add("Bolívar");
            listaTipoMunicipios.add("Buenos Aires");
            listaTipoMunicipios.add("Cajibío");
            listaTipoMunicipios.add("Caldono");
            listaTipoMunicipios.add("Caloto");
            listaTipoMunicipios.add("Corinto");
            listaTipoMunicipios.add("El Tambo");
            listaTipoMunicipios.add("Florencia");
            listaTipoMunicipios.add("Guachené");
            listaTipoMunicipios.add("Guapi");
            listaTipoMunicipios.add("Inzá");
            listaTipoMunicipios.add("Jambaló");
            listaTipoMunicipios.add("La Sierra");
            listaTipoMunicipios.add("La Vega");
            listaTipoMunicipios.add("López");
            listaTipoMunicipios.add("Mercaderes");
            listaTipoMunicipios.add("Miranda");
            listaTipoMunicipios.add("Morales");
            listaTipoMunicipios.add("Padilla");
            listaTipoMunicipios.add("Páez");
            listaTipoMunicipios.add("Patía");
            listaTipoMunicipios.add("Piamonte");
            listaTipoMunicipios.add("Piendamó");
            listaTipoMunicipios.add("Popayán");
            listaTipoMunicipios.add("Puerto Tejada");
            listaTipoMunicipios.add("Puracé");
            listaTipoMunicipios.add("Rosas");
            listaTipoMunicipios.add("San Sebastián");
            listaTipoMunicipios.add("Santa Rosa");
            listaTipoMunicipios.add("Santander de Quilichao");
            listaTipoMunicipios.add("Silvia");
            listaTipoMunicipios.add("Sotara");
            listaTipoMunicipios.add("Suárez");
            listaTipoMunicipios.add("Sucre");
            listaTipoMunicipios.add("Timbío");
            listaTipoMunicipios.add("Timbiquí");
            listaTipoMunicipios.add("Toribío");
            listaTipoMunicipios.add("Totoró");
            listaTipoMunicipios.add("Villa Rica");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Cundinamarca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Agua de Dios");
            listaTipoMunicipios.add("Albán");
            listaTipoMunicipios.add("Anapoima");
            listaTipoMunicipios.add("Anolaima");
            listaTipoMunicipios.add("Arbeláez");
            listaTipoMunicipios.add("Beltrán");
            listaTipoMunicipios.add("Bituima");
            listaTipoMunicipios.add("Bogotá D.C.");
            listaTipoMunicipios.add("Bojacá");
            listaTipoMunicipios.add("Cabrera");
            listaTipoMunicipios.add("Cachipay");
            listaTipoMunicipios.add("Cajicá");
            listaTipoMunicipios.add("Caparrapí");
            listaTipoMunicipios.add("Cáqueza");
            listaTipoMunicipios.add("Carmen de Carupa");
            listaTipoMunicipios.add("Chaguaní");
            listaTipoMunicipios.add("Chía");
            listaTipoMunicipios.add("Chipaque");
            listaTipoMunicipios.add("Choachí");
            listaTipoMunicipios.add("Chocontá");
            listaTipoMunicipios.add("Cogua");
            listaTipoMunicipios.add("Cota");
            listaTipoMunicipios.add("Cucunubá");
            listaTipoMunicipios.add("El Colegio");
            listaTipoMunicipios.add("El Peñón");
            listaTipoMunicipios.add("El Rosal");
            listaTipoMunicipios.add("Facatativá");
            listaTipoMunicipios.add("Fómeque");
            listaTipoMunicipios.add("Fosca");
            listaTipoMunicipios.add("Funza");
            listaTipoMunicipios.add("Fúquene");
            listaTipoMunicipios.add("Fusagasugá");
            listaTipoMunicipios.add("Gachalá");
            listaTipoMunicipios.add("Gachancipá");
            listaTipoMunicipios.add("Gacheta");
            listaTipoMunicipios.add("Gama");
            listaTipoMunicipios.add("Girardot");
            listaTipoMunicipios.add("Granada");
            listaTipoMunicipios.add("Guachetá");
            listaTipoMunicipios.add("Guaduas");
            listaTipoMunicipios.add("Guasca");
            listaTipoMunicipios.add("Guataquí");
            listaTipoMunicipios.add("Guatavita");
            listaTipoMunicipios.add("Guayabal de Siquima");
            listaTipoMunicipios.add("Guayabetal");
            listaTipoMunicipios.add("Gutiérrez");
            listaTipoMunicipios.add("Jerusalén");
            listaTipoMunicipios.add("Junín");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.Narino)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Albán");
            listaTipoMunicipios.add("Aldana");
            listaTipoMunicipios.add("Ancuyá");
            listaTipoMunicipios.add("Arboleda");
            listaTipoMunicipios.add("Barbacoas");
            listaTipoMunicipios.add("Belén");
            listaTipoMunicipios.add("Buesaco");
            listaTipoMunicipios.add("Chachagüí");
            listaTipoMunicipios.add("Colón");
            listaTipoMunicipios.add("Consaca");
            listaTipoMunicipios.add("Contadero");
            listaTipoMunicipios.add("Córdoba");
            listaTipoMunicipios.add("Cuaspud");
            listaTipoMunicipios.add("Cumbal");
            listaTipoMunicipios.add("Cumbitara");
            listaTipoMunicipios.add("El Charco");
            listaTipoMunicipios.add("El Peñol");
            listaTipoMunicipios.add("El Rosario");
            listaTipoMunicipios.add("El Tablón de Gómez");
            listaTipoMunicipios.add("Funes");
            listaTipoMunicipios.add("Guachucal");
            listaTipoMunicipios.add("Guaitarilla");
            listaTipoMunicipios.add("Gualmatán");
            listaTipoMunicipios.add("Iles");
            listaTipoMunicipios.add("Imués");
            listaTipoMunicipios.add("Ipiales");
            listaTipoMunicipios.add("La Cruz");
            listaTipoMunicipios.add("La Florida");
            listaTipoMunicipios.add("La Llanada");
            listaTipoMunicipios.add("La Tola");
            listaTipoMunicipios.add("La Unión");
            listaTipoMunicipios.add("Leiva");
            listaTipoMunicipios.add("Linares");
            listaTipoMunicipios.add("Los Andes");
            listaTipoMunicipios.add("Magüí");
            listaTipoMunicipios.add("Mallama");
            listaTipoMunicipios.add("Mosquera");
            listaTipoMunicipios.add("Nariño");
            listaTipoMunicipios.add("Olaya Herrera");
            listaTipoMunicipios.add("Ospina");
            listaTipoMunicipios.add("Pasto");
            listaTipoMunicipios.add("Policarpa");
            listaTipoMunicipios.add("Potosí");
            listaTipoMunicipios.add("Providencia");
            listaTipoMunicipios.add("Puerres");
            listaTipoMunicipios.add("Pupiales");
            listaTipoMunicipios.add("Ricaurte");
            listaTipoMunicipios.add("Roberto Payán");
            listaTipoMunicipios.add("Samaniego");
            listaTipoMunicipios.add("San Bernardo");
            listaTipoMunicipios.add("San José de Albán");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.NorteDeSantander)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Ábrego");
            listaTipoMunicipios.add("Arboledas");
            listaTipoMunicipios.add("Bochalema");
            listaTipoMunicipios.add("Bucarasica");
            listaTipoMunicipios.add("Cáchira");
            listaTipoMunicipios.add("Cácota");
            listaTipoMunicipios.add("Chinácota");
            listaTipoMunicipios.add("Chitagá");
            listaTipoMunicipios.add("Convención");
            listaTipoMunicipios.add("Cúcuta");
            listaTipoMunicipios.add("Cucutilla");
            listaTipoMunicipios.add("Durania");
            listaTipoMunicipios.add("El Carmen");
            listaTipoMunicipios.add("El Tarra");
            listaTipoMunicipios.add("El Zulia");
            listaTipoMunicipios.add("Gramalote");
            listaTipoMunicipios.add("Hacarí");
            listaTipoMunicipios.add("Herrán");
            listaTipoMunicipios.add("La Esperanza");
            listaTipoMunicipios.add("La Playa");
            listaTipoMunicipios.add("Labateca");
            listaTipoMunicipios.add("Los Patios");
            listaTipoMunicipios.add("Lourdes");
            listaTipoMunicipios.add("Mutiscua");
            listaTipoMunicipios.add("Ocaña");
            listaTipoMunicipios.add("Pamplona");
            listaTipoMunicipios.add("Pamplonita");
            listaTipoMunicipios.add("Puerto Santander");
            listaTipoMunicipios.add("Ragonvalia");
            listaTipoMunicipios.add("Salazar");
            listaTipoMunicipios.add("San Calixto");
            listaTipoMunicipios.add("San Cayetano");
            listaTipoMunicipios.add("Santiago");
            listaTipoMunicipios.add("Sardinata");
            listaTipoMunicipios.add("Silos");
            listaTipoMunicipios.add("Teorama");
            listaTipoMunicipios.add("Tibú");
            listaTipoMunicipios.add("Toledo");
            listaTipoMunicipios.add("Villa Caro");
            listaTipoMunicipios.add("Villa Del Rosario");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Quindio)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Armenia");
            listaTipoMunicipios.add("Buenavista");
            listaTipoMunicipios.add("Calarca");
            listaTipoMunicipios.add("Circasia");
            listaTipoMunicipios.add("Córdoba");
            listaTipoMunicipios.add("Filandia");
            listaTipoMunicipios.add("Génova");
            listaTipoMunicipios.add("La Tebaida");
            listaTipoMunicipios.add("Montenegro");
            listaTipoMunicipios.add("Pijao");
            listaTipoMunicipios.add("Quimbaya");
            listaTipoMunicipios.add("Salento");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Risaralda)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Apía");
            listaTipoMunicipios.add("Balboa");
            listaTipoMunicipios.add("Belén de Umbría");
            listaTipoMunicipios.add("Dosquebradas");
            listaTipoMunicipios.add("Guática");
            listaTipoMunicipios.add("La Celia");
            listaTipoMunicipios.add("La Virginia");
            listaTipoMunicipios.add("Marsella");
            listaTipoMunicipios.add("Mistrato");
            listaTipoMunicipios.add("Pereira");
            listaTipoMunicipios.add("Pueblo Rico");
            listaTipoMunicipios.add("Quinchía");
            listaTipoMunicipios.add("Santa Rosa de Cabal");
            listaTipoMunicipios.add("Santuario");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.SanAndres)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Providencia");
            listaTipoMunicipios.add("San Andrés");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Tolima)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Alpujarra");
            listaTipoMunicipios.add("Alvarado");
            listaTipoMunicipios.add("Ambalema");
            listaTipoMunicipios.add("Anzoátegui");
            listaTipoMunicipios.add("Armero");
            listaTipoMunicipios.add("Ataco");
            listaTipoMunicipios.add("Cajamarca");
            listaTipoMunicipios.add("Carmen de Apicalá");
            listaTipoMunicipios.add("Casabianca");
            listaTipoMunicipios.add("Chaparral");
            listaTipoMunicipios.add("Coello");
            listaTipoMunicipios.add("Coyaima");
            listaTipoMunicipios.add("Cunday");
            listaTipoMunicipios.add("Dolores");
            listaTipoMunicipios.add("Espinal");
            listaTipoMunicipios.add("Falán");
            listaTipoMunicipios.add("Flandes");
            listaTipoMunicipios.add("Fresno");
            listaTipoMunicipios.add("Guamo");
            listaTipoMunicipios.add("Herveo");
            listaTipoMunicipios.add("Honda");
            listaTipoMunicipios.add("Ibagué");
            listaTipoMunicipios.add("Icononzo");
            listaTipoMunicipios.add("Lérida");
            listaTipoMunicipios.add("Libano");
            listaTipoMunicipios.add("Mariquita");
            listaTipoMunicipios.add("Melgar");
            listaTipoMunicipios.add("Murillo");
            listaTipoMunicipios.add("Natagaima");
            listaTipoMunicipios.add("Ortega");
            listaTipoMunicipios.add("Palocabildo");
            listaTipoMunicipios.add("Piedras");
            listaTipoMunicipios.add("Planadas");
            listaTipoMunicipios.add("Prado");
            listaTipoMunicipios.add("Purificación");
            listaTipoMunicipios.add("Rioblanco");
            listaTipoMunicipios.add("Roncesvalles");
            listaTipoMunicipios.add("Rovira");
            listaTipoMunicipios.add("Saldaña");
            listaTipoMunicipios.add("San Antonio");
            listaTipoMunicipios.add("San Luis");
            listaTipoMunicipios.add("Santa Isabel");
            listaTipoMunicipios.add("Suárez");
            listaTipoMunicipios.add("Valle de San Juan");
            listaTipoMunicipios.add("Venadillo");
            listaTipoMunicipios.add("Villahermosa");
            listaTipoMunicipios.add("Villarrica");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.ValleDelCauca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Alcalá");
            listaTipoMunicipios.add("Andalucía");
            listaTipoMunicipios.add("Ansermanuevo");
            listaTipoMunicipios.add("Argelia");
            listaTipoMunicipios.add("Bolívar");
            listaTipoMunicipios.add("Buenaventura");
            listaTipoMunicipios.add("Buga");
            listaTipoMunicipios.add("Bugalagrande");
            listaTipoMunicipios.add("Caicedonia");
            listaTipoMunicipios.add("Cali");
            listaTipoMunicipios.add("Calima");
            listaTipoMunicipios.add("Candelaria");
            listaTipoMunicipios.add("Cartago");
            listaTipoMunicipios.add("Dagua");
            listaTipoMunicipios.add("El Aguila");
            listaTipoMunicipios.add("El Cairo");
            listaTipoMunicipios.add("El Cerrito");
            listaTipoMunicipios.add("El Dovio");
            listaTipoMunicipios.add("Florida");
            listaTipoMunicipios.add("Ginebra");
            listaTipoMunicipios.add("Guacarí");
            listaTipoMunicipios.add("Guadalajara de Buga");
            listaTipoMunicipios.add("Jamundí");
            listaTipoMunicipios.add("La Cumbre");
            listaTipoMunicipios.add("La Unión");
            listaTipoMunicipios.add("La Victoria");
            listaTipoMunicipios.add("Obando");
            listaTipoMunicipios.add("Palmira");
            listaTipoMunicipios.add("Pradera");
            listaTipoMunicipios.add("Restrepo");
            listaTipoMunicipios.add("Riofrío");
            listaTipoMunicipios.add("Roldanillo");
            listaTipoMunicipios.add("San Pedro");
            listaTipoMunicipios.add("Sevilla");
            listaTipoMunicipios.add("Toro");
            listaTipoMunicipios.add("Trujillo");
            listaTipoMunicipios.add("Tulúa");
            listaTipoMunicipios.add("Ulloa");
            listaTipoMunicipios.add("Versalles");
            listaTipoMunicipios.add("Vijes");
            listaTipoMunicipios.add("Yotoco");
            listaTipoMunicipios.add("Yumbo");
            listaTipoMunicipios.add("Zarzal");

            comboMunicipio.setItems(listaTipoMunicipios);

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

    public void cargarTipoCuenta() {

        listaTipoCuenta.add(TipoCuenta.Ahorros);
        listaTipoCuenta.add(TipoCuenta.Corriente);
        listaTipoCuenta.add(TipoCuenta.Credito);
        listaTipoCuenta.add(TipoCuenta.Debito);

        comboTipoCuenta.setItems(listaTipoCuenta);
    }

    public void cargarTipoResidencia() {

        listaTipoResidencia.add(TipoResidencia.Rural);
        listaTipoResidencia.add(TipoResidencia.Urbana);

        comboResidencia.setItems(listaTipoResidencia);
    }

    public void cargarTipoDepartamento() {

        listaTipoDepartamento.add(TipoDepartamento.Antioquia);
        listaTipoDepartamento.add(TipoDepartamento.Atlantico);
        listaTipoDepartamento.add(TipoDepartamento.Bolivar);
        listaTipoDepartamento.add(TipoDepartamento.Boyaca);
        listaTipoDepartamento.add(TipoDepartamento.Caldas);
        listaTipoDepartamento.add(TipoDepartamento.Casanare);
        listaTipoDepartamento.add(TipoDepartamento.Cauca);
        listaTipoDepartamento.add(TipoDepartamento.Cundinamarca);
        listaTipoDepartamento.add(TipoDepartamento.Narino);
        listaTipoDepartamento.add(TipoDepartamento.NorteDeSantander);
        listaTipoDepartamento.add(TipoDepartamento.Quindio);
        listaTipoDepartamento.add(TipoDepartamento.Risaralda);
        listaTipoDepartamento.add(TipoDepartamento.SanAndres);
        listaTipoDepartamento.add(TipoDepartamento.Tolima);
        listaTipoDepartamento.add(TipoDepartamento.ValleDelCauca);

        comboDepartamento.setItems(listaTipoDepartamento);
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
        cargarTipoDepartamento();
        cargarTipoCuenta();
        cargarTipoResidencia();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
