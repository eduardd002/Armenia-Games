package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.constant.MensajesEmailConstant;
import co.edu.uniquindio.armeniagames.enumm.*;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.JugadorSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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

public class JugadorController implements Initializable {

    public Carrito carritoSeleccionado;
    public Favorito favoritoSeleccionado;
    public Main main = new Main();
    public ModelFactory factoryController = new ModelFactory();
    public JugadorSubcontroller subcontroller;
    private final ObservableList<TipoEstadoCivil> listaTipoEstadoCivil = FXCollections.observableArrayList();
    private final ObservableList<TipoCuenta> listaTipoCuenta = FXCollections.observableArrayList();
    private final ObservableList<TipoBanco> listaTipoBanco = FXCollections.observableArrayList();
    private final ObservableList<TipoResidencia> listaTipoResidencia = FXCollections.observableArrayList();
    private final ObservableList<TipoDepartamento> listaTipoDepartamento = FXCollections.observableArrayList();
    private final ObservableList<String> listaTipoMunicipios = FXCollections.observableArrayList();
    private final ObservableList<String> listaVideojuegos = FXCollections.observableArrayList();
    private final ObservableList<Compra> listaPrestamos = FXCollections.observableArrayList();
    private final ObservableList<Compra> listaPrestamosNueva = FXCollections.observableArrayList();
    private final ObservableList<Carrito> listaCarrito = FXCollections.observableArrayList();
    private final ObservableList<Carrito> listaCarritoNueva = FXCollections.observableArrayList();
    private final ObservableList<Favorito> listaFavorito = FXCollections.observableArrayList();
    private final ObservableList<Favorito> listaFavoritoNueva = FXCollections.observableArrayList();

    @FXML
    private ComboBox<TipoBanco> comboBanco;

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgVideojuego;

    @FXML
    private Button btnEliminarCuenta, btnConsulta, btnPrestamo, btnSalir,
            btnActualizarPago, btnActualizar, btnActualizarEnvio, btnCarrito, btnFavorito,
            btnEliminarCarrito, btnEliminarFavorito, btnComprarCarrito;

    @FXML
    private DatePicker dateCaducidad;

    @FXML
    private TableColumn<Compra, LocalDate> colFechaFinalizacion, colFechaPrestamo;

    @FXML
    private TableColumn<Compra, String> colNombre, colCodigo;

    @FXML
    private TextField txtUnidadesDisponibles, txtCuenta,
            txtNombre, txtTelefono, txtApellido, txtTitular, txtPostal, txtDireccion,
            txtBarrio, txtCorreo, txtPrecio, txtUnidadesComprar, txtTotalCarrito;

    @FXML
    private TableView<Compra> tablaPrestamos;

    @FXML
    private TableColumn<Compra, Integer> colPrecio, colFactura;

    @FXML
    private TableColumn<Compra, Integer> colCantidad;

    @FXML
    private ComboBox<TipoEstadoCivil> comboEstadoCivil;

    @FXML
    private ComboBox<TipoFormatoVideojuego> comboTipoFormato;

    @FXML
    private ComboBox<String> comboMunicipio;

    @FXML
    private ComboBox<TipoDepartamento> comboDepartamento;

    @FXML
    private ComboBox<String> comboVideojuegosDisponiblesAlquiler;

    @FXML
    private ComboBox<TipoCuenta> comboTipoCuenta;

    @FXML
    private ComboBox<TipoResidencia> comboResidencia;

    @FXML
    private PasswordField txtContrasenia;

    /*
     ******************************   Apartado ventana principal   ************************************
     */
    @FXML
    public void prestamo() {
        compraJugador();
        limpiarCampos();
    }

    @FXML
    public void comprarCarrito() {
        comprarElCarrito();
    }

    @FXML
    public void favorito() {
        agregarFavorito();
    }

    @FXML
    public void carrito() {
        agregarCarrito();
    }

    @FXML
    public void eliminarFavorito() {
        eliminarVideojuegoFavorito();
    }

    @FXML
    public void eliminarCarrito() {
        eliminarVideojuegoCarrito();
    }

    private void eliminarVideojuegoFavorito() {
        if (favoritoSeleccionado != null) {
            subcontroller.eliminarFavorito(favoritoSeleccionado);
            listaFavoritoNueva.remove(favoritoSeleccionado);
            tablaFavorito.setItems(listaFavoritoNueva);
            tablaFavorito.refresh();
        }
    }

    private void eliminarVideojuegoCarrito() {
        if (carritoSeleccionado != null) {
            subcontroller.eliminarCarrito(carritoSeleccionado);
            listaCarritoNueva.remove(carritoSeleccionado);
            tablaCarrito.setItems(listaCarritoNueva);
            tablaCarrito.refresh();
            cargarPrecioCarrito();
        }
    }

    @FXML
    public void consulta() {
        consultaJugador();
    }

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaLogin();
    }

    public void compraJugador() {

        Compra compra;
        Compra comp = new Compra();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\compra.jpg";

        Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem());
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
        LocalDate fecha = LocalDate.now();
        int unidades = Integer.parseInt(txtUnidadesComprar.getText());

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
            subcontroller.email(mensajes.MENSAJE_COMPRA, (mensajes.MENSAJE_COMPRA_CUERPO + comp.getNombreVideojuego()), jugador.getCorreo(), img);
            for (Administrador admin : subcontroller.traerAdmins()) {
                subcontroller.email(mensajes.MENSAJE_VENTA, (mensajes.MENSAJE_VENTA_CUERPO + comp.getNombreVideojuego() + mensajes.MENSAJE_VENTA_CUERPO2 + jugador.getNombrePersona()), admin.getCorreo(), img);
            }
            listaPrestamosNueva.add(compra);
            tablaPrestamos.setItems(listaPrestamosNueva);
            actualizarInventario();
            actualizarHistorial(jugador.getDocumento(), jugador.getVideojuegosComprados() + 1);
        }
    }

    public void comprarElCarrito() {
        for(int i = 0; i < listaCarritoNueva.size(); i++){
            Compra compra;
            Compra comp = new Compra();
            MensajesEmailConstant mensajes = new MensajesEmailConstant();
            String img = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\compra.jpg";

            Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(listaCarritoNueva.get(i).getNombreVideojuegoCarrito());
            Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
            LocalDate fecha = LocalDate.now();
            int unidades = listaCarritoNueva.get(i).getUnidadesCarrito();

            int bandera = subcontroller.traerAlquileres().size();

            comp.setFactura(bandera + 1);
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
                subcontroller.email(mensajes.MENSAJE_COMPRA, (mensajes.MENSAJE_COMPRA_CUERPO + comp.getNombreVideojuego()), jugador.getCorreo(), img);
                for (Administrador admin : subcontroller.traerAdmins()) {
                    subcontroller.email(mensajes.MENSAJE_VENTA, (mensajes.MENSAJE_VENTA_CUERPO + comp.getNombreVideojuego() + mensajes.MENSAJE_VENTA_CUERPO2 + jugador.getNombrePersona()), admin.getCorreo(), img);
                }
                listaPrestamosNueva.add(compra);
                tablaPrestamos.setItems(listaPrestamosNueva);
                subcontroller.actualizarVideojuego(comp.getNombreVideojuego(), videojuego.getUnidades(), comp.getUnidades());
                txtUnidadesDisponibles.setText(String.valueOf(videojuego.getUnidades()));
                actualizarHistorial(jugador.getDocumento(), jugador.getVideojuegosComprados() + 1);
            }
        }
    }

    public void agregarCarrito() {

        Carrito car;
        Carrito carrito = new Carrito();
        int unidades = Integer.parseInt(txtUnidadesComprar.getText());
        Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem());
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();

        carrito.setDocumentoJugadorCarrito(jugador.getDocumento());
        carrito.setJugadorCarrito(jugador.getNombrePersona());
        carrito.setApellidoCarrito(jugador.getApellido());
        carrito.setCodigoCarrito(videojuego.getCodigo());
        carrito.setUnidadesCarrito(unidades);
        carrito.setNombreVideojuegoCarrito(videojuego.getNombreVideojuego());
        carrito.setTipoGeneroVideojuegoCarrito(videojuego.getTipoGeneroVideojuego());
        carrito.setTipoFormatoVideojuegoCarrito(videojuego.getTipoFormatoVideojuego());
        carrito.setTotalCarrito(videojuego.getPrecio());

        car = subcontroller.guardarCarrito(carrito);

        if (car != null) {
            listaCarritoNueva.add(car);
            tablaCarrito.setItems(listaCarritoNueva);
            cargarPrecioCarrito();
        }
    }

    @FXML
    public void stock(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtUnidadesComprar)) {
            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtUnidadesComprar.deletePreviousChar();
            }
            try{
                int disponibles = Integer.parseInt(txtUnidadesDisponibles.getText());
                int comprar = Integer.parseInt(txtUnidadesComprar.getText());
                btnPrestamo.setDisable((comprar > disponibles) || comprar < 1);
                btnCarrito.setDisable((comprar > disponibles) || comprar < 1);
                btnFavorito.setDisable((comprar > disponibles) || comprar < 1);
            }catch (NumberFormatException e){

            }
        }
    }

    public void agregarFavorito() {

        Favorito car;
        Favorito carrito = new Favorito();

        Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem());
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();

        carrito.setDocumentoJugadorFavorito(jugador.getDocumento());
        carrito.setJugadorFavorito(jugador.getNombrePersona());
        carrito.setApellidoFavorito(jugador.getApellido());
        carrito.setCodigoFavorito(videojuego.getCodigo());
        carrito.setNombreVideojuegoFavorito(videojuego.getNombreVideojuego());
        carrito.setTipoGeneroVideojuegoFavorito(videojuego.getTipoGeneroVideojuego());
        carrito.setTipoFormatoVideojuegoFavorito(videojuego.getTipoFormatoVideojuego());
        carrito.setTotalFavorito(videojuego.getPrecio());

        car = subcontroller.guardarFavorito(carrito);

        if (car != null) {
            listaFavoritoNueva.add(car);
            tablaCarrito.setItems(listaCarritoNueva);
        }

    }

    public void limpiarCampos(){
        comboVideojuegosDisponiblesAlquiler.setValue(null);
        txtUnidadesComprar.setText(null);
        comboTipoFormato.setValue(null);
        txtPrecio.setText(null);
        txtUnidadesDisponibles.setText(null);
        btnPrestamo.setDisable(true);
        btnConsulta.setDisable(false);
        imgVideojuego.setImage(null);
    }

    public void consultaJugador() {

        String codigo = comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem();

        Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(codigo);

        txtUnidadesDisponibles.setText(String.valueOf(videojuego.getUnidades()));
        txtPrecio.setText(String.valueOf(videojuego.getPrecio()));
        comboTipoFormato.setValue(videojuego.getTipoFormatoVideojuego());
        Image img = new Image(videojuego.getImagenVideojuego());
        imgVideojuego.setImage(img);
        txtUnidadesComprar.setDisable(false);


        if (videojuego.getUnidades() == 0) {
            txtUnidadesComprar.setDisable(true);
        }
    }

    public void actualizarInventario() {

        Videojuego vid = subcontroller.obtenerVideojuego(comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem());

        int inventarioActual = Integer.parseInt(txtUnidadesDisponibles.getText());
        int compradas = Integer.parseInt(txtUnidadesComprar.getText());

        subcontroller.actualizarVideojuego(vid.getNombreVideojuego(), inventarioActual, compradas);
        txtUnidadesDisponibles.setText(String.valueOf(vid.getUnidades()));
    }

    public void actualizarHistorial(String lector, int librosLeidos) {
        subcontroller.actualizarHistorial(lector, librosLeidos);
    }

    public void cargarVideojuegos() {

        for (Videojuego videojuego : subcontroller.traerListaVideojuegos()) {
            listaVideojuegos.add(videojuego.getNombreVideojuego());
        }
        comboVideojuegosDisponiblesAlquiler.setItems(listaVideojuegos);
    }

    @FXML
    public void eventoComboBox(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboVideojuegosDisponiblesAlquiler)) {
            btnConsulta.setDisable(comboVideojuegosDisponiblesAlquiler.getSelectionModel().isEmpty());
        }
    }

    /*
     ******************************   Apartado mi perfil   ************************************
     */
    @FXML
    public void actualizarDatos() {
        actualizarDatosJugador();
    }

    @FXML
    public void eliminarCuenta() {
        eliminarCuentaJugador();
    }

    public void eliminarCuentaJugador() {

        Usuario jugador = subcontroller.traerUsuarioAuxiliar();
        boolean cuentaEliminada, rsMensaje;
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        rsMensaje = factoryController.mostrarMensajeConfirmacion("¿Seguro desea eliminar la cuenta?");
        String imgCorreo = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\eliminado.jpg";
        if (rsMensaje) {
            cuentaEliminada = subcontroller.eliminarJugador(jugador.getDocumento());

            if (cuentaEliminada) {
                subcontroller.email(mensajes.MENSAJE_ELIMINADO, mensajes.MENSAJE_ELIMINADO_CUERPO, txtCorreo.getText(), imgCorreo);
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

    public void actualizarDatosJugador() {

        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
        MensajesEmailConstant mensajes = new MensajesEmailConstant();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String clave = txtContrasenia.getText();
        TipoEstadoCivil estado = comboEstadoCivil.getSelectionModel().getSelectedItem();
        String img = imgUsuario.getImage().getUrl();
        String imgCorreo = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\actualizado.jpg";

        jugador.setNombrePersona(nombre);
        jugador.setApellido(apellido);
        jugador.setTelefono(telefono);
        jugador.setCorreo(correo);
        jugador.setClave(clave);
        jugador.setConfirmacionClave(clave);
        jugador.setTipoEstadoCivil(estado);

        TipoBanco banco = comboBanco.getSelectionModel().getSelectedItem();
        TipoCuenta cuenta = comboTipoCuenta.getSelectionModel().getSelectedItem();
        String titular = txtTitular.getText();
        String numCuenta = txtCuenta.getText();
        LocalDate fecha = dateCaducidad.getValue();

        jugador.setTipoBanco(banco);
        jugador.setTipoCuenta(cuenta);
        jugador.setTitular(titular);
        jugador.setNumeroCuenta(numCuenta);
        jugador.setFechaCaducidad(fecha);

        TipoResidencia residencia = comboResidencia.getSelectionModel().getSelectedItem();
        String postal = txtPostal.getText();
        String barrio = txtBarrio.getText();
        String direccion = txtDireccion.getText();
        TipoDepartamento departamento = comboDepartamento.getSelectionModel().getSelectedItem();
        String municipio = comboMunicipio.getSelectionModel().getSelectedItem();

        jugador.setTipoResidencia(residencia);
        jugador.setCodigoPostal(postal);
        jugador.setBarrio(barrio);
        jugador.setDireccion(direccion);
        jugador.setTipoDepartamento(departamento);
        jugador.setMunicipio(municipio);
        jugador.setImagen(img);

        subcontroller.actualizarJugador(jugador);
        subcontroller.email(mensajes.MENSAJE_ACTUALIZADO, mensajes.MENSAJE_ACTUALIZADO_CUERPO, correo, imgCorreo);

    }

    @FXML
    public void eventoText(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtNombre) || evt.equals(txtApellido) || evt.equals(txtCorreo)
                || evt.equals(txtContrasenia) || evt.equals(txtTelefono)) {

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

            btnActualizar.setDisable(txtNombre.getText().isEmpty() ||
                    txtApellido.getText().isEmpty() ||
                    !txtCorreo.getText().contains("@gmail.com") ||
                    txtContrasenia.getText().isEmpty() ||
                    txtTelefono.getText().isEmpty());
        }
    }

    public void mostrarDatosJugador() {

        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();

        txtNombre.setText(jugador.getNombrePersona());
        txtApellido.setText(jugador.getApellido());
        comboEstadoCivil.setValue(jugador.getTipoEstadoCivil());
        txtTelefono.setText(jugador.getTelefono());
        txtCorreo.setText(jugador.getCorreo());
        txtContrasenia.setText(jugador.getClave());
        Image img = new Image(jugador.getImagen());
        imgUsuario.setImage(img);

        comboBanco.setValue(jugador.getTipoBanco());
        comboTipoCuenta.setValue(jugador.getTipoCuenta());
        txtTitular.setText(jugador.getTitular());
        txtCuenta.setText(jugador.getNumeroCuenta());
        dateCaducidad.setValue(jugador.getFechaCaducidad());

        comboResidencia.setValue(jugador.getTipoResidencia());
        txtPostal.setText(jugador.getCodigoPostal());
        txtBarrio.setText(jugador.getBarrio());
        txtDireccion.setText(jugador.getDireccion());
        comboDepartamento.setValue(jugador.getTipoDepartamento());
        comboMunicipio.setValue(jugador.getMunicipio());

    }

    /*
     ******************************   Apartado detalle   ************************************
     */

    @FXML
    private TextField txtFacturaDetalle, txtCodigoDetalle, txtVideojuegoDetalle, txtFormatoDetalle,
            txtGeneroDetalle, txtCompraDetalle, txtDevolucionDetalle, txtBancoDetalle, txtCuentaDetalle,
            txtDepartamentoDetalle, txtMunicipioDetalle, txtValorDetalle, txtDireccionDetalle,
            txtUnidadesDetalle, txtTotalDetalle;

    @FXML
    private Button btnGenerarDetalle;

    @FXML
    public void generarDetalle() {
        generarDetalleCompra();
    }

    public void generarDetalleCompra(){

        Compra compra = subcontroller.traerCompra(Integer.parseInt(txtFacturaDetalle.getText()));

        if(compra != null){

            Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();
            Videojuego vid = subcontroller.obtenerVideojuego(compra.getNombreVideojuego());

            int unidades = compra.getUnidades();
            int precio = vid.getPrecio();
            int total = unidades * precio;

            txtCodigoDetalle.setText(vid.getCodigo());
            txtUnidadesDetalle.setText(String.valueOf(unidades));
            txtTotalDetalle.setText(String.valueOf(total));
            txtVideojuegoDetalle.setText(vid.getNombreVideojuego());
            txtFormatoDetalle.setText(String.valueOf(vid.getTipoFormatoVideojuego()));
            txtGeneroDetalle.setText(String.valueOf(vid.getTipoGeneroVideojuego()));
            txtCompraDetalle.setText(String.valueOf(compra.getFechaCompraInicial()));
            txtDevolucionDetalle.setText(String.valueOf(compra.getFechaCompraFinal()));
            txtBancoDetalle.setText(String.valueOf(jugador.getTipoBanco()));
            txtCuentaDetalle.setText(String.valueOf(jugador.getTipoCuenta()));
            txtDepartamentoDetalle.setText(String.valueOf(jugador.getTipoDepartamento()));
            txtMunicipioDetalle.setText(jugador.getMunicipio());
            txtValorDetalle.setText(String.valueOf(precio));
            txtDireccionDetalle.setText(jugador.getDireccion());
        }

    }

    /*
     ******************************   Apartado mi pago   ************************************
     */
    @FXML
    public void actualizarPago() {
        actualizarDatos();
    }

    public void cargarTipoBanco() {

        listaTipoBanco.add(TipoBanco.Bancolombia);
        listaTipoBanco.add(TipoBanco.Bogota);
        listaTipoBanco.add(TipoBanco.Davivienda);
        listaTipoBanco.add(TipoBanco.Occidente);

        comboBanco.setItems(listaTipoBanco);
    }

    @FXML
    public void eventoText2(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(dateCaducidad) || evt.equals(txtCuenta) || evt.equals(txtTitular)){

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtCuenta.deletePreviousChar();
            }

            if (Character.isDigit(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtTitular.deletePreviousChar();
            }

            btnActualizarPago.setDisable(txtCuenta.getText().isEmpty() ||
                    dateCaducidad.getValue() == null ||
                    txtTitular.getText().isEmpty());
        }
    }

    @FXML
    public void eventoTextx(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtFacturaDetalle)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtFacturaDetalle.deletePreviousChar();
            }
            btnGenerarDetalle.setDisable(txtFacturaDetalle.getText().isEmpty());
        }
    }

    public void cargarTipoCuenta() {

        listaTipoCuenta.add(TipoCuenta.Ahorros);
        listaTipoCuenta.add(TipoCuenta.Corriente);
        listaTipoCuenta.add(TipoCuenta.Credito);
        listaTipoCuenta.add(TipoCuenta.Debito);

        comboTipoCuenta.setItems(listaTipoCuenta);
    }

    /*
     ******************************   Apartado mi envio   ************************************
     */
    @FXML
    public void eventoDepartamento() {
        eventoDepartamentoJugador();
    }

    @FXML
    public void actualizarEnvio() {
        actualizarDatosJugador();
    }

    @FXML
    public void eventoText3(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(txtBarrio) || evt.equals(txtPostal) || evt.equals(txtDireccion)){

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtPostal.deletePreviousChar();
            }

            btnActualizarEnvio.setDisable(txtBarrio.getText().isEmpty() ||
                    txtPostal.getText().isEmpty() ||
                    txtDireccion.getText().isEmpty());
        }
    }

    public void eventoDepartamentoJugador() {

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

        comboEstadoCivil.setItems(listaTipoEstadoCivil);
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

    /*
     ******************************   Apartado mis videojuegos   ************************************
     */

    public ObservableList<Compra> getPrestamos() {
        Jugador jug = subcontroller.traerJugador(subcontroller.traerUsuarioAuxiliar().getDocumento());
        String documento = jug.getDocumento();
        listaPrestamos.addAll(subcontroller.obtenerPrestamos(documento));
        for (Compra prest : listaPrestamos) {
            if(prest.getDocumentoJugador().equals(documento)) {
                listaPrestamosNueva.add(prest);
            }else{
                System.out.println("prest jugador: " + prest.getJugador());
            }
        }
        return listaPrestamosNueva;
    }

    public ObservableList<Carrito> getCarritos() {
        Jugador jug = subcontroller.traerJugador(subcontroller.traerUsuarioAuxiliar().getDocumento());
        String documento = jug.getDocumento();
        listaCarrito.addAll(subcontroller.obtenerCarritos(documento));
        for (Carrito prest : listaCarrito) {
            if(prest.getDocumentoJugadorCarrito().equals(documento)) {
                listaCarritoNueva.add(prest);
            }else{
                System.out.println("prest jugador: " + prest.getJugadorCarrito());
            }
        }
        return listaCarritoNueva;
    }

    /*
     ******************************   Apartado mi carrito   ************************************
     */

    @FXML
    private TableView<Carrito> tablaCarrito;

    @FXML
    private TableColumn<Compra, Integer> colCodigoCarrito;

    @FXML
    private TableColumn<Compra, Integer> colVideojuegoCarrito;

    @FXML
    private TableColumn<Compra, Integer> colGeneroCarrito;

    @FXML
    private TableColumn<Compra, Integer> colTipoCarrito;

    @FXML
    private TableColumn<Compra, Integer> colUnidadesCarrito;

    @FXML
    private TableColumn<Compra, Integer> colPrecioCarrito;

    public void inicializarCarritoView() {

        colCodigoCarrito.setCellValueFactory(new PropertyValueFactory<>("codigoCarrito"));
        colVideojuegoCarrito.setCellValueFactory(new PropertyValueFactory<>("nombreVideojuegoCarrito"));
        colUnidadesCarrito.setCellValueFactory(new PropertyValueFactory<>("unidadesCarrito"));
        colTipoCarrito.setCellValueFactory(new PropertyValueFactory<>("tipoFormatoVideojuegoCarrito"));
        colPrecioCarrito.setCellValueFactory(new PropertyValueFactory<>("totalCarrito"));
        colGeneroCarrito.setCellValueFactory(new PropertyValueFactory<>("tipoGeneroVideojuegoCarrito"));

        tablaCarrito.getItems().clear();
        tablaCarrito.setItems(getCarritos());

        tablaCarrito.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            carritoSeleccionado = newSelection;
        });
    }

    /*
     ******************************   Apartado mi favorito   ************************************
     */

    @FXML
    private TableView<Favorito> tablaFavorito;

    @FXML
    private TableColumn<Compra, Integer> colCodigoFavorito;

    @FXML
    private TableColumn<Compra, Integer> colVideojuegoFavorito;

    @FXML
    private TableColumn<Compra, Integer> colGeneroFavorito;

    @FXML
    private TableColumn<Compra, Integer> colTipoFavorito;

    @FXML
    private TableColumn<Compra, Integer> colPrecioFavorito;

    public ObservableList<Favorito> getFavoritos() {
        Jugador jug = subcontroller.traerJugador(subcontroller.traerUsuarioAuxiliar().getDocumento());
        String documento = jug.getDocumento();
        listaFavorito.addAll(subcontroller.obtenerFavoritos(documento));
        for (Favorito prest : listaFavorito) {
            if(prest.getDocumentoJugadorFavorito().equals(documento)) {
                listaFavoritoNueva.add(prest);
            }else{
                System.out.println("prest jugador: " + prest.getJugadorFavorito());
            }
        }
        return listaFavoritoNueva;
    }

    public void inicializarFavoritoView() {

        colCodigoFavorito.setCellValueFactory(new PropertyValueFactory<>("codigoFavorito"));
        colVideojuegoFavorito.setCellValueFactory(new PropertyValueFactory<>("nombreVideojuegoFavorito"));
        colTipoFavorito.setCellValueFactory(new PropertyValueFactory<>("tipoFormatoVideojuegoFavorito"));
        colPrecioFavorito.setCellValueFactory(new PropertyValueFactory<>("totalFavorito"));
        colGeneroFavorito.setCellValueFactory(new PropertyValueFactory<>("tipoGeneroVideojuegoFavorito"));

        tablaFavorito.getItems().clear();
        tablaFavorito.setItems(getFavoritos());

        tablaFavorito.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            favoritoSeleccionado = newSelection;
        });
    }

    /*
    lkjlkjljlkjlkjlkj
     */

    public void inicializarComprasView() {

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colFactura.setCellValueFactory(new PropertyValueFactory<>("factura"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreVideojuego"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("total"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaCompraInicial"));
        colFechaFinalizacion.setCellValueFactory(new PropertyValueFactory<>("fechaCompraFinal"));

        tablaPrestamos.getItems().clear();
        tablaPrestamos.setItems(getPrestamos());

        tablaPrestamos.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        });
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void cerrarVentana2() {
        Stage stage = (Stage) this.btnEliminarCuenta.getScene().getWindow();
        stage.close();
    }

    public void cargarPrecioCarrito(){
        int total = 0;
        for(int i = 0; i < listaCarritoNueva.size(); i++){
            total += listaCarritoNueva.get(i).getUnidadesCarrito() * listaCarritoNueva.get(i).getTotalCarrito();
        }
        txtTotalCarrito.setText(String.valueOf(total));
    }

    public void iniciarDatos() {
        factoryController = ModelFactory.getInstance();
        subcontroller = new JugadorSubcontroller(factoryController);
        new JugadorController();
        cargarVideojuegos();
        cargarTipoEstadoCivil();
        cargarTipoDepartamento();
        cargarTipoCuenta();
        cargarTipoResidencia();
        cargarTipoBanco();
        mostrarDatosJugador();
        inicializarComprasView();
        inicializarCarritoView();
        inicializarFavoritoView();
        cargarPrecioCarrito();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }
}
