package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.constant.MensajesInformacionConstant;
import co.edu.uniquindio.armeniagames.enumm.TipoBanco;
import co.edu.uniquindio.armeniagames.enumm.TipoCuenta;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.model.*;
import co.edu.uniquindio.armeniagames.subcontroller.PagoSubcontroller2;
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
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PagoCarritoController implements Initializable {

    public Main main = new Main();
    public PagoSubcontroller2 subcontroller;
    private final ArrayList<Compra> compras = new ArrayList<>();

    private final ObservableList<TipoCuenta> listaTipoCuenta = FXCollections.observableArrayList();

    private TipoBanco tipoBanco;

    @FXML
    private TextField txtCuenta, txtTitular;

    @FXML
    private ComboBox<TipoCuenta> comboTipoCuenta;

    @FXML
    private DatePicker dateCaducidad;

    @FXML
    private Button btnComprar, btnSalir;

    @FXML
    private RadioButton radioBancolombia, radioDavivienda, radioOccidente, radioBogota;

    @FXML
    public void comprarCarrito() {

        MensajesInformacionConstant mensajesInformacionConstant = new MensajesInformacionConstant();
        ObservableList<Carrito> lista = subcontroller.obtenerCarrito();
        int videojuegos = subcontroller.traerVideojuegos();
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\compra.jpg";

        Compra compra;
        Compra comp = new Compra();

        for (Carrito carrito : lista) {

            Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(carrito.getNombreVideojuegoCarrito());
            LocalDate fecha = LocalDate.now();
            int unidades = carrito.getUnidadesCarrito();

            comp.setFactura(videojuegos + 1);
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
            comp.setTotal(videojuego.getPrecio() * unidades);

            compra = subcontroller.guardarPrestamo(comp);

            if (compra != null) {
                compras.add(compra);
                for (Administrador admin : subcontroller.traerAdmins()) {
                    subcontroller.email(mensajes.MENSAJE_VENTA, (mensajes.MENSAJE_VENTA_CUERPO + comp.getNombreVideojuego() + mensajes.MENSAJE_VENTA_CUERPO2 + jugador.getNombrePersona()), admin.getCorreo(), img);
                }
                subcontroller.actualizarVideojuego(comp.getNombreVideojuego(), videojuego.getUnidades(), comp.getUnidades());
            }
        }
        mostrarMensaje("Notificacion Guardado", "Compra Guardada", mensajesInformacionConstant.INFORMACION_PRESTAMO_GUARDADO,
                Alert.AlertType.INFORMATION);
        subcontroller.email(mensajes.MENSAJE_CARRITO, (mensajes.MENSAJE_CARRITO_CUERPO + correo()), jugador.getCorreo(), img);
        subcontroller.acualizarVid(videojuegos+1);
        actualizarHistorial(jugador.getDocumento(), jugador.getVideojuegosComprados() + 1);
        cerrarVentana(btnComprar);
        main.cargarVentanaJugador();
    }

    public void actualizarHistorial(String lector, int librosLeidos) {
        subcontroller.actualizarHistorial(lector, librosLeidos);
    }

    public String correo(){
        StringBuilder mensaje = new StringBuilder();
        for (Compra com : compras) {

            int precioUnidad = com.getTotal() / com.getUnidades();

            mensaje.append("<br>" + "<br>" + "DATOS DEL VIDEOJUEGO" + "<br>" + "<br>" + "Nombre: ").append(com.getNombreVideojuego()).append("<br>").append("Precio: ").append(precioUnidad).append("<br>").append("Formato: ").append(com.getTipoFormatoVideojuego()).append("<br>").append("Genero: ").append(com.getTipoGeneroVideojuego()).append("<br>").append("Unidades: ").append(com.getUnidades()).append("<br>").append("Total: ").append(com.getTotal()).append("<br>").append("<br>").append("DATOS DE PAGO").append("<br>").append("<br>").append("Banco: ").append(tipoBanco).append("<br>").append("Tipo de cuenta: ").append(comboTipoCuenta.getSelectionModel().getSelectedItem()).append("<br>").append("Numero de cuenta: ").append(txtCuenta.getText()).append("<br>").append("Titular: ").append(txtTitular.getText()).append("<br>").append("<br>").append("DATOS DE ENVIO").append("<br>").append("<br>").append("Departamento: ").append(subcontroller.obtenerDepartamentoSegundoMomento()).append("<br>").append("Muncipio: ").append(subcontroller.obtenerMunicipioSegundoMomento()).append("<br>").append("Codigo postal: ").append(subcontroller.obtenerPostalSegundoMomento()).append("<br>").append("Direccion: ").append(subcontroller.obtenerDireccionSegundoMomento()).append("<br>").append("<br>").append("--------------------------------------------------------------------------------------------");
        }
         return mensaje.toString();
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

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaEnvio();
    }

    public void iniciarDatos() {
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new PagoSubcontroller2(factoryController);
        new PagoCarritoController();
        cargarTipoCuenta();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}

