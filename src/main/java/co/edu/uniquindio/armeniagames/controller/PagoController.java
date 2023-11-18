package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.enumm.TipoBanco;
import co.edu.uniquindio.armeniagames.enumm.TipoCuenta;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.model.Administrador;
import co.edu.uniquindio.armeniagames.model.Compra;
import co.edu.uniquindio.armeniagames.model.Jugador;
import co.edu.uniquindio.armeniagames.model.Videojuego;
import co.edu.uniquindio.armeniagames.subcontroller.PagoSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class PagoController implements Initializable {

    public Main main = new Main();
    public PagoSubcontroller subcontroller;

    private final ObservableList<TipoCuenta> listaTipoCuenta = FXCollections.observableArrayList();

    private TipoBanco tipoBanco;

    @FXML
    private TextField txtCuenta, txtTitular;

    @FXML
    private ComboBox<TipoCuenta> comboTipoCuenta;

    @FXML
    private DatePicker dateCaducidad;

    @FXML
    private Button btnRegresar, btnComprar, btnSalir;

    @FXML
    private RadioButton radioBancolombia, radioDavivienda, radioOccidente, radioBogota;

    @FXML
    public void comprarVideojuego() {

        Compra compra;
        Compra comp = new Compra();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\compra.jpg";

        Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(subcontroller.obtenerVideojuegoPrimerMomento());
        System.out.println(videojuego.getNombreVideojuego());
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
        LocalDate fecha = LocalDate.now();
        int unidades = Integer.parseInt(String.valueOf(subcontroller.obtenerCantidadPrimerMomento()));

        comp.setDocumentoJugador(jugador.getDocumento());
        comp.setJugador(jugador.getNombrePersona());
        comp.setApellido(jugador.getApellido());
        comp.setCodigo(videojuego.getCodigo());
        comp.setNombreVideojuego(videojuego.getNombreVideojuego());
        comp.setTipoGeneroVideojuego(videojuego.getTipoGeneroVideojuego());
        comp.setTipoFormatoVideojuego(videojuego.getTipoFormatoVideojuego());
        comp.setFechaCompraInicial(fecha);
        comp.setFechaCompraFinal(fecha);
        comp.setUnidades(unidades);
        comp.setTotal(videojuego.getPrecio()*unidades);

        int total = comp.getTotal();

        compra = subcontroller.guardarPrestamo(comp);

        if (compra != null) {
            subcontroller.email(mensajes.MENSAJE_COMPRA, (mensajes.MENSAJE_COMPRA_CUERPO + "<br>" + "<br>" + "DATOS DEL VIDEOJUEGO" + "<br>" + "<br>" +
                    "Nombre: " + videojuego.getNombreVideojuego() + "<br>" +
                    "Precio: " + videojuego.getPrecio() + "<br>" +
                    "Formato: " + videojuego.getTipoFormatoVideojuego() + "<br>" +
                    "Genero: " + videojuego.getTipoGeneroVideojuego() + "<br>" +
                    "Clasificacion: " + videojuego.getClasificacion() + "<br>" +
                    "Unidades: " + videojuego.getUnidades() + "<br>" +
                    "Total: " + total + "<br>" + "<br>" + "DATOS DE PAGO"+ "<br>" + "<br>" +
                    "Banco: " + tipoBanco + "<br>" +
                    "Tipo de cuenta: " + comboTipoCuenta.getSelectionModel().getSelectedItem() + "<br>" +
                    "Numero de cuenta: " + txtCuenta.getText() + "<br>" +
                    "Titular: " + txtTitular.getText() + "<br>" + "<br>"  + "DATOS DE ENVIO" + "<br>" + "<br>" +
                    "Departamento: " + subcontroller.obtenerDepartamentoSegundoMomento() + "<br>" +
                    "Muncipio: " + subcontroller.obtenerMunicipioSegundoMomento() + "<br>" +
                    "Codigo postal: " + subcontroller.obtenerPostalSegundoMomento() + "<br>" +
                    "Direccion: " + subcontroller.obtenerDireccionSegundoMomento()), jugador.getCorreo(), img);
            for (Administrador admin : subcontroller.traerAdmins()) {
                subcontroller.email(mensajes.MENSAJE_VENTA, (mensajes.MENSAJE_VENTA_CUERPO + comp.getNombreVideojuego() + mensajes.MENSAJE_VENTA_CUERPO2 + jugador.getNombrePersona()), admin.getCorreo(), img);
            }
            actualizarInventario();
            actualizarHistorial(jugador.getDocumento(), jugador.getVideojuegosComprados() + 1);
            cerrarVentana(btnComprar);
            main.cargarVentanaJugador();
        }
    }

    public void actualizarInventario() {

        Videojuego vid = subcontroller.obtenerVideojuego(subcontroller.obtenerVideojuegoPrimerMomento());

        int inventarioActual = Integer.parseInt(String.valueOf(subcontroller.obtenerCantidad2PrimerMomento()));
        int compradas = Integer.parseInt(String.valueOf(subcontroller.obtenerCantidadPrimerMomento()));

        subcontroller.actualizarVideojuego(vid.getNombreVideojuego(), inventarioActual, compradas);
    }

    public void actualizarHistorial(String lector, int librosLeidos) {
        subcontroller.actualizarHistorial(lector, librosLeidos);
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

            if (Character.isDigit(event.getCharacter().charAt(0))) {
                txtTitular.deletePreviousChar();
            }

            dateCaducidad.setDisable(txtTitular.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText11(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(dateCaducidad)) {
            btnComprar.setDisable(dateCaducidad.getValue() == null);
        }
    }



    @FXML
    public void registro() {
        realizarRegistro();
    }

    private void realizarRegistro() {
        main.cargarVentanaPago();
    }

    public void cargarTipoCuenta() {

        listaTipoCuenta.add(TipoCuenta.Ahorros);
        listaTipoCuenta.add(TipoCuenta.Corriente);
        listaTipoCuenta.add(TipoCuenta.Credito);
        listaTipoCuenta.add(TipoCuenta.Debito);

        comboTipoCuenta.setItems(listaTipoCuenta);
    }


    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaEnvio();
    }

    public void iniciarDatos() {
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new PagoSubcontroller(factoryController);
        new PagoController();
        cargarTipoCuenta();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}

