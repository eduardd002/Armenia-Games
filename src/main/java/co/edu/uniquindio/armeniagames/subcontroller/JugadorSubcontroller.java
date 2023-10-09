package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;
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

    public boolean eliminarJugador(String documento) {
        return factoryController.eliminarJugador(documento);
    }

    public Usuario traerUsuarioAuxiliar() {
        return factoryController.getUsuarioAuxiliar();
    }

    public Jugador traerJugador(String documento) {
        return factoryController.traerJugadorEnvioYPago(documento);
    }

    public ArrayList<Videojuego> traerListaVideojuegos() {
        return factoryController.getTienda().getListaVideojuegos();
    }

    public ArrayList<Compra> obtenerPrestamos(String jug){
        return factoryController.getListaCompras(jug);
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

    public void actualizarHistorial(String jugador, int jugados){
        factoryController.actualizarHistorial(jugador, jugados);
    }

    public Videojuego obtenerVideojuego(String codigo) {
        return factoryController.obtenerVideojuego(codigo);
    }

    public void actualizarVideojuego(String videojuego, int inventarioActual) {
        factoryController.actualizarInventario(videojuego, inventarioActual);
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
