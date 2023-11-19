package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.constant.MensajesInformacionConstant;
import co.edu.uniquindio.armeniagames.enumm.TipoBanco;
import co.edu.uniquindio.armeniagames.enumm.TipoCuenta;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.model.*;
import co.edu.uniquindio.armeniagames.subcontroller.PagoSubcontroller;
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

public class PagoController2 implements Initializable {

    public Main main = new Main();
    public PagoSubcontroller2 subcontroller;
    private ArrayList<Compra> compras = new ArrayList<>();

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
    public void comprarCarrito() {

        MensajesInformacionConstant mensajesInformacionConstant = new MensajesInformacionConstant();
        ObservableList<Carrito> lista = subcontroller.obtenerCarrito();
        int videojuegos = subcontroller.traerVideojuegos();
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\compra.jpg";

        Compra compra;
        Compra comp = new Compra();

        int total = comp.getTotal();

        for(int i = 0; i < lista.size(); i++){

            Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(lista.get(i).getNombreVideojuegoCarrito());
            LocalDate fecha = LocalDate.now();
            int unidades = lista.get(i).getUnidadesCarrito();

            comp.setFactura(videojuegos+1);
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
        subcontroller.email(mensajes.MENSAJE_CARRITO, (mensajes.MENSAJE_CARRITO_CUERPO + correo(total)), jugador.getCorreo(), img);
        subcontroller.acualizarVid(videojuegos+1);
        actualizarHistorial(jugador.getDocumento(), jugador.getVideojuegosComprados() + 1);
        cerrarVentana(btnComprar);
        main.cargarVentanaJugador();
    }

    public void actualizarHistorial(String lector, int librosLeidos) {
        subcontroller.actualizarHistorial(lector, librosLeidos);
    }

    public String correo(int total){
        String mensaje = "";
        for (Compra com : compras) {
            mensaje += "<br>" + "<br>" + "DATOS DEL VIDEOJUEGO" + "<br>" + "<br>" +
                    "Nombre: " + com.getNombreVideojuego() + "<br>" +
                    "Formato: " + com.getTipoFormatoVideojuego() + "<br>" +
                    "Genero: " + com.getTipoGeneroVideojuego() + "<br>" +
                    "Unidades: " + com.getUnidades() + "<br>" +
                    "Total: " + total + "<br>" + "<br>" + "DATOS DE PAGO" + "<br>" + "<br>" +
                    "Banco: " + tipoBanco + "<br>" +
                    "Tipo de cuenta: " + comboTipoCuenta.getSelectionModel().getSelectedItem() + "<br>" +
                    "Numero de cuenta: " + txtCuenta.getText() + "<br>" +
                    "Titular: " + txtTitular.getText() + "<br>" + "<br>" + "DATOS DE ENVIO" + "<br>" + "<br>" +
                    "Departamento: " + subcontroller.obtenerDepartamentoSegundoMomento() + "<br>" +
                    "Muncipio: " + subcontroller.obtenerMunicipioSegundoMomento() + "<br>" +
                    "Codigo postal: " + subcontroller.obtenerPostalSegundoMomento() + "<br>" +
                    "Direccion: " + subcontroller.obtenerDireccionSegundoMomento() +
                    "---------------------------------------------------------";
        }
         return mensaje;
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
        new PagoController2();
        cargarTipoCuenta();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}

