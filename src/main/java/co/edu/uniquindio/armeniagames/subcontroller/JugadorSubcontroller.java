package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class JugadorSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public JugadorSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public void actualizarJugador(Jugador lector) {
        factoryController.actualizarJugador(lector);
    }

    public void car(ObservableList<Carrito>carrito){
        factoryController.car(carrito);
    }

    public boolean eliminarJugador(String documento) {
        return factoryController.eliminarJugador(documento);
    }

    public Usuario traerUsuarioAuxiliar() {
        return factoryController.getUsuarioAuxiliar();
    }

    public Jugador traerJugador(String documento) {
        return factoryController.traerJugadorEnvioYPago(documento);
    }

    public void compraPrimeraEtapa(String v, int cantidad, int disponibles){
        factoryController.compraPrimeraEtapa(v, cantidad, disponibles);
    }

    public void compraPrimeraEtapa2(ObservableList<Carrito> carrito){
        factoryController.compraPrimeraEtapa2(carrito);
    }

    public Compra traerCompra(int factura) {
        return factoryController.traerCompra(factura);
    }

    public ArrayList<Videojuego> traerListaVideojuegos() {
        return factoryController.getTienda().getListaVideojuegos();
    }

    public ArrayList<Compra> obtenerPrestamos(String jug){
        return factoryController.getListaCompras(jug);
    }

    public ArrayList<Carrito> obtenerCarritos(String jug){
        return factoryController.getListaCarrito(jug);
    }

    public ArrayList<Favorito> obtenerFavoritos(String jug){
        return factoryController.getListaFavorito(jug);
    }

    public void eliminarFavorito(Favorito favorito) {
        factoryController.eliminarFavorito(favorito);
    }

    public void eliminarCarrito(Carrito carrito) {
        factoryController.eliminarCarrito(carrito);
    }

    public void email(String titulo, String cuerpo, String correo, String img) {
        factoryController.correo(titulo, cuerpo, correo, img);
    }

    public ArrayList<Administrador> traerAdmins(){
        return factoryController.getListaAdministradores();
    }

    public Videojuego traerVideojuegoAuxiliar(String nombre){
        return factoryController.getVideojuegoAuxiliar(nombre);
    }

    public ArrayList<Compra> traerAlquileres(){
        return factoryController.getListaCompras2();
    }

    public Compra guardarPrestamo(Compra prestamo){
        return factoryController.guardarCompra(prestamo);
    }

    public Carrito guardarCarrito(Carrito carrito){
        return factoryController.guardarCarrito(carrito);
    }

    public Favorito guardarFavorito(Favorito favorito){
        return factoryController.guardarFavorito(favorito);
    }

    public void actualizarHistorial(String jugador, int jugados){
        factoryController.actualizarHistorial(jugador, jugados);
    }

    public Videojuego obtenerVideojuego(String codigo) {
        return factoryController.obtenerVideojuego(codigo);
    }

    public void actualizarVideojuego(String videojuego, int inventarioActual, int compradas) {
        factoryController.actualizarInventario(videojuego, inventarioActual, compradas);
    }

    public ModelFactory getFactoryController() {
        return factoryController;
    }

    public void setFactoryController(ModelFactory factoryController) {
        this.factoryController = factoryController;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
}
