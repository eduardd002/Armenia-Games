package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.enumm.TipoEstadoCivil;
import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoRestriccion;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.AdministradorSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AdministradorController implements Initializable {

    public Main main = new Main();

    public ModelFactory factoryController = new ModelFactory();

    public AdministradorSubcontroller subcontroller;
    private final ObservableList<Videojuego> listaVideojuegos = FXCollections.observableArrayList();
    private final ObservableList<Jugador> listaJugadores = FXCollections.observableArrayList();
    private final ObservableList<Compra> listaAlquileres = FXCollections.observableArrayList();

    @FXML
    private TableColumn<Videojuego, TipoFormatoVideojuego> colTipoVideojuegoRegistro;

    @FXML
    private TableColumn<Compra, LocalDate> colFechaFPrestamo, colFechaIPrestamo;

    @FXML
    private TableColumn<Compra, String> colNombrePrestamo, colVideojuegoPrestamo, colDocumento;
    @FXML
    private TableColumn<Compra, Integer> colFactura, colPrecio, colCantidad;

    @FXML
    private ComboBox<TipoEstadoCivil> comboEstadoCivil;

    @FXML
    private TableColumn<Videojuego, TipoGeneroVideojuego> colGeneroVideojuegoRegistro;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private Button btnAgregar, btnSalir, btnEliminar, btnActualizar,
            btnActualizarDatos, btnDevolver, btnDesbloquear;

    @FXML
    private TableColumn<Videojuego, String> colNombreVideojuegoRegistro,
            colCodigoVideojuegoRegistro;

    @FXML
    private TableColumn<Videojuego, Integer> colPrecioVideojuegoRegistro;

    @FXML
    private TextField txtCorreo, txtCarnet, txtNombre, txtTelefono, txtApellido;

    @FXML
    private TableView<Compra> tablaAlquileres;

    @FXML
    private TableView<Jugador> tablaJugadores;

    @FXML
    private TableView<Videojuego> tablaVideojuegos;

    @FXML
    private TableColumn<Jugador, String> colNOmbreJugador, colApellidoJugador, colTelefonoJugador,
            colCorreoJugador;

    @FXML
    private TableColumn<Jugador, Integer> colJuegosJugados;

    @FXML
    private TableColumn<Jugador, TipoRestriccion> colRestriccion;

    @FXML
    private TableColumn<Videojuego, LocalDate> colAnioLanzamientoVideojuegoRegistro;

    @FXML
    private PasswordField txtClave;

    /*
    ******************************   Apartado ventana principal   ************************************
     */

    @FXML
    public void actualizar() {
        cerrarVentana(btnActualizar);
        main.cargarVentanaActualizarVideojuego();
    }

    @FXML
    public void eliminar() {
        cerrarVentana(btnEliminar);
        main.cargarVentanaEliminarVideojuego();
    }

    @FXML
    public void agregar() {
        cerrarVentana(btnAgregar);
        main.cargarVentanaGuardarVideojuego();
    }

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaLogin();
    }

    @FXML
    public void eventoText(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtNombre) || evt.equals(txtApellido) || evt.equals(txtCorreo)
                || evt.equals(txtClave) || evt.equals(txtTelefono)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtTelefono.deletePreviousChar();
            }

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtNombre.deletePreviousChar();
            }

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtApellido.deletePreviousChar();
            }

            btnActualizarDatos.setDisable(txtNombre.getText().isEmpty() ||
                    txtApellido.getText().isEmpty() ||
                    !txtCorreo.getText().contains("@gmail.com") ||
                    txtClave.getText().isEmpty() ||
                    txtTelefono.getText().isEmpty());
        }
    }

    public ObservableList<Videojuego> getVideojuegos() {
        listaVideojuegos.addAll(subcontroller.obtenerVideojuegos());
        return listaVideojuegos;
    }

    public void inicializarVideojuegosView() {

        colCodigoVideojuegoRegistro.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colTipoVideojuegoRegistro.setCellValueFactory(new PropertyValueFactory<>("tipoFormatoVideojuego"));
        colNombreVideojuegoRegistro.setCellValueFactory(new PropertyValueFactory<>("nombreVideojuego"));
        colPrecioVideojuegoRegistro.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colGeneroVideojuegoRegistro.setCellValueFactory(new PropertyValueFactory<>("tipoGeneroVideojuego"));
        colAnioLanzamientoVideojuegoRegistro.setCellValueFactory(new PropertyValueFactory<>("anioLanzamiento"));

        tablaVideojuegos.getItems().clear();
        tablaVideojuegos.setItems(getVideojuegos());

        tablaVideojuegos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        });
    }

    /*
     ******************************   Apartado mi perfil   ************************************
     */

    @FXML
    public void actualizarDatos() {
        actualizarDatosAdministrador();
    }

    @FXML
    public void eliminarCuenta() {
        eliminarCuentaAdministrador();
    }

    public void eliminarCuentaAdministrador(){

        Usuario administrador = subcontroller.traerUsuarioAuxiliar();
        boolean cuentaEliminada, rsMensaje;

        rsMensaje = factoryController.mostrarMensajeConfirmacion("¿Seguro desea eliminar la cuenta?");

        if (rsMensaje) {
            cuentaEliminada = subcontroller.eliminarAdministrador(administrador.getDocumento());

            if (cuentaEliminada) {
                cerrarVentana2();
                main.cargarVentanaLogin();
            }
        }
    }

    public void cambiarFoto() {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Buscar Imagen");

        // Agregar filtros para facilitar la busqueda

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        // Obtener la imagen seleccionada

        File imgFile = fileChooser.showOpenDialog(null);

        // Mostar la imagen

        if (imgFile != null) {
            Image image = new Image(imgFile.getAbsolutePath());
            imgUsuario.setImage(image);
        }
    }

    public void actualizarDatosAdministrador(){

        Administrador administrador = (Administrador) subcontroller.traerUsuarioAuxiliar();

        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String clave = txtClave.getText();
        TipoEstadoCivil estado = comboEstadoCivil.getSelectionModel().getSelectedItem();
        int carnet = administrador.getCarnet();
        String img = imgUsuario.getImage().getUrl();

        administrador.setNombrePersona(nombre);
        administrador.setApellido(apellido);
        administrador.setTelefono(telefono);
        administrador.setCorreo(correo);
        administrador.setClave(clave);
        administrador.setConfirmacionClave(clave);
        administrador.setTipoEstadoCivil(estado);
        administrador.setCarnet(carnet);
        administrador.setImagen(img);

        subcontroller.actualizarAdministrador(administrador);

    }

    public void mostrarDatosAdministrador() {

        Administrador administrador = (Administrador) subcontroller.traerUsuarioAuxiliar();

        txtNombre.setText(administrador.getNombrePersona());
        txtApellido.setText(administrador.getApellido());
        comboEstadoCivil.setValue(administrador.getTipoEstadoCivil());
        txtTelefono.setText(administrador.getTelefono());
        txtCorreo.setText(administrador.getCorreo());
        txtClave.setText(administrador.getClave());
        txtCarnet.setText(String.valueOf(administrador.getCarnet()));
        Image img = new Image(administrador.getImagen());
        imgUsuario.setImage(img);
    }

    /*
     ******************************   Apartado mis prestamos   ************************************
     */

    public ObservableList<Compra> getAlquileres() {
        listaAlquileres.addAll(subcontroller.obtenerAlquileres());
        return listaAlquileres;
    }
    @FXML
    public void devolver() {
        cerrarVentana(btnDevolver);
        main.cargarVentanaDevolverVideojuego();
    }

    public void inicializarComprasView() {

        colFactura.setCellValueFactory(new PropertyValueFactory<>("factura"));
        colNombrePrestamo.setCellValueFactory(new PropertyValueFactory<>("jugador"));
        colDocumento.setCellValueFactory(new PropertyValueFactory<>("documentoJugador"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("total"));
        colVideojuegoPrestamo.setCellValueFactory(new PropertyValueFactory<>("nombreVideojuego"));
        colFechaIPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaCompraInicial"));
        colFechaFPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaCompraFinal"));

        tablaAlquileres.getItems().clear();
        tablaAlquileres.setItems(getAlquileres());

        tablaAlquileres.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        });
    }

    /*
     ******************************   Apartado mis clientes   ************************************
     */

    private Jugador jugadorSeleccionado;

    @FXML
    public void desbloquear() {
        desbloquearCuenta();
    }

    public ObservableList<Jugador> getJugadores() {
        listaJugadores.addAll(subcontroller.obtenerJugadores());
        return listaJugadores;
    }

    public void inicializarClientesView() {

        colNOmbreJugador.setCellValueFactory(new PropertyValueFactory<>("nombrePersona"));
        colApellidoJugador.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        colTelefonoJugador.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colCorreoJugador.setCellValueFactory(new PropertyValueFactory<>("correo"));
        colJuegosJugados.setCellValueFactory(new PropertyValueFactory<>("videojuegosComprados"));
        colRestriccion.setCellValueFactory(new PropertyValueFactory<>("tipoRestriccion"));

        tablaJugadores.getItems().clear();
        tablaJugadores.setItems(getJugadores());
        tablaJugadores.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            jugadorSeleccionado = newSelection;
        });
    }

    public void desbloquearCuenta() {
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\cuentadesbloqueada.jpg";
        if (jugadorSeleccionado != null) {
            subcontroller.desbloquearCuenta(jugadorSeleccionado);
            subcontroller.email(mensajes.MENSAJE_DESBLOQUEO, mensajes.MENSAJE_DESBLOQUEO_CUERPO, jugadorSeleccionado.getCorreo(), img);
            tablaJugadores.refresh();
        }
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2() {
        Stage stage = (Stage) this.btnEliminar.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos(){
        factoryController = ModelFactory.getInstance();
        subcontroller = new AdministradorSubcontroller(factoryController);
        new GuardarVideojuegoController();
        inicializarVideojuegosView();
        inicializarClientesView();
        inicializarComprasView();
        mostrarDatosAdministrador();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
