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
    private final ObservableList<String> listaVideojuegos = FXCollections.observableArrayList();
    private final ObservableList<TipoObservacion> listaTipoObservacion = FXCollections.observableArrayList();
    private final ObservableList<Compra> listaPrestamos = FXCollections.observableArrayList();
    private final ObservableList<Compra> listaPrestamosNueva = FXCollections.observableArrayList();
    private final ObservableList<Carrito> listaCarrito = FXCollections.observableArrayList();
    private final ObservableList<Carrito> listaCarritoNueva = FXCollections.observableArrayList();
    private final ObservableList<Favorito> listaFavorito = FXCollections.observableArrayList();
    private final ObservableList<Favorito> listaFavoritoNueva = FXCollections.observableArrayList();

    @FXML
    private ImageView imgUsuario;

    @FXML
    private ImageView imgVideojuego;

    @FXML
    private Button btnEliminarCuenta, btnPrestamo, btnSalir,
            btnActualizar, btnCarrito, btnFavorito;

    @FXML
    private Label lblSgno, lblUnidades, lblFormato, lblGeneroJugador, lblClasificacion, lblMas,
    lblAComprar;

    @FXML
    private TableColumn<Compra, LocalDate> colFechaFinalizacion, colFechaPrestamo;

    @FXML
    private TableColumn<Compra, String> colNombre, colCodigo;

    @FXML
    private TextField txtUnidadesDisponibles, txtEdad, txtGeneroJugador,
            txtNombre, txtTelefono, txtApellido,
            txtCorreo, txtPrecio, txtUnidadesComprar, txtTotalCarrito, comboTipoFormato;

    @FXML
    private TableView<Compra> tablaPrestamos;

    @FXML
    private TableColumn<Compra, Integer> colPrecio, colFactura;

    @FXML
    private TableColumn<Compra, Integer> colCantidad;

    @FXML
    private ComboBox<TipoEstadoCivil> comboEstadoCivil;

    @FXML
    private ComboBox<String> comboVideojuegosDisponiblesAlquiler;

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
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaLogin();
    }

    public void compraJugador() {
        compraPrimeraEtapa();
        cerrarVentana(btnPrestamo);
        main.cargarVentanaEnvio();
    }

    public void comprarElCarrito() {
        car();
        compraPrimeraEtapa2();
        cerrarVentana(btnPrestamo);
        main.cargarVentanaEnvio2();
    }

    public void compraPrimeraEtapa(){
        subcontroller.compraPrimeraEtapa(comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem(), Integer.parseInt(txtUnidadesComprar.getText()), Integer.parseInt(txtUnidadesDisponibles.getText()));
    }

    public void compraPrimeraEtapa2(){
        subcontroller.compraPrimeraEtapa2(listaCarritoNueva);
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
                e.printStackTrace();
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
        txtUnidadesComprar.setText(null);
        comboTipoFormato.setText(null);
        txtPrecio.setText(null);
        txtUnidadesDisponibles.setText(null);
        btnPrestamo.setDisable(true);
        imgVideojuego.setImage(null);
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
            String codigo = comboVideojuegosDisponiblesAlquiler.getSelectionModel().getSelectedItem();

            Videojuego videojuego = subcontroller.traerVideojuegoAuxiliar(codigo);

            txtUnidadesDisponibles.setText(String.valueOf(videojuego.getUnidades()));
            txtEdad.setText(String.valueOf(videojuego.getClasificacion()));
            txtPrecio.setText(String.valueOf(videojuego.getPrecio()));
            txtGeneroJugador.setText(String.valueOf(videojuego.getTipoGeneroVideojuego()));
            comboTipoFormato.setText(String.valueOf(videojuego.getTipoFormatoVideojuego()));
            Image img = new Image(videojuego.getImagenVideojuego());
            imgVideojuego.setImage(img);
            txtUnidadesComprar.setDisable(false);
            lblSgno.setVisible(true);
            lblUnidades.setVisible(true);
            lblFormato.setVisible(true);
            lblClasificacion.setVisible(true);
            lblGeneroJugador.setVisible(true);
            lblMas.setVisible(true);
            lblAComprar.setVisible(true);
            txtUnidadesComprar.setVisible(true);

            if (videojuego.getUnidades() == 0) {
                txtUnidadesComprar.setDisable(true);
            }
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

        jugador.setImagen(img);

        subcontroller.actualizarJugador(jugador);
        subcontroller.email(mensajes.MENSAJE_ACTUALIZADO, mensajes.MENSAJE_ACTUALIZADO_CUERPO, correo, imgCorreo);
    }

    public void cargarTipoEstadoCivil() {

        listaTipoEstadoCivil.add(TipoEstadoCivil.Casado);
        listaTipoEstadoCivil.add(TipoEstadoCivil.Divorciado);
        listaTipoEstadoCivil.add(TipoEstadoCivil.Soltero);
        listaTipoEstadoCivil.add(TipoEstadoCivil.UnionLibre);
        listaTipoEstadoCivil.add(TipoEstadoCivil.Viudo);

        comboEstadoCivil.setItems(listaTipoEstadoCivil);
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

    }

    public void car(){
        subcontroller.car(listaCarritoNueva);
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

        tablaCarrito.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> carritoSeleccionado = newSelection);
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

        tablaFavorito.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> favoritoSeleccionado = newSelection);
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

    /*
    lkjlkjljlkjlkjlkj
     */

    @FXML
    private TextArea areaAsunto;

    @FXML
    private ComboBox<TipoObservacion> comboPqrsf;

    @FXML
    private Button btnPqrsf;

    public void cargarTipoObservacion() {

        listaTipoObservacion.add(TipoObservacion.Peticion);
        listaTipoObservacion.add(TipoObservacion.Queja);
        listaTipoObservacion.add(TipoObservacion.Reclamo);
        listaTipoObservacion.add(TipoObservacion.Sugerencia);
        listaTipoObservacion.add(TipoObservacion.Felicitacion);

        comboPqrsf.setItems(listaTipoObservacion);
    }

    @FXML
    public void eventoComboBox2(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboPqrsf)) {
            areaAsunto.setDisable(comboPqrsf.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoArea(KeyEvent event) {
        Object evt = event.getSource();

        if (evt.equals(areaAsunto)) {
            btnPqrsf.setDisable(areaAsunto.getText().isEmpty());
        }
    }

    @FXML
    public void enviarObservacion(){
        crearPqrsf();
    }

    private void crearPqrsf() {
        Observacion ob;
        Observacion obser = new Observacion();
        Jugador jugador = (Jugador) subcontroller.traerUsuarioAuxiliar();

        obser.setTipoObservacion(comboPqrsf.getSelectionModel().getSelectedItem());
        obser.setObservacion(areaAsunto.getText());
        obser.setNombreObservacion(jugador.getNombrePersona());
        obser.setApellidoObservacion(jugador.getApellido());
        obser.setDocumentoObservacion(jugador.getDocumento());

        ob = subcontroller.guardarObservacion(obser);

        if (ob != null) {
            areaAsunto.clear();
        }
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
        for (Carrito carrito : listaCarritoNueva) {
            total += carrito.getUnidadesCarrito() * carrito.getTotalCarrito();
        }
        txtTotalCarrito.setText(String.valueOf(total));
    }

    public void iniciarDatos() {
        factoryController = ModelFactory.getInstance();
        subcontroller = new JugadorSubcontroller(factoryController);
        new JugadorController();
        cargarVideojuegos();
        cargarTipoEstadoCivil();
        cargarTipoObservacion();
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
