package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;
import javafx.collections.ObservableList;

import java.util.ArrayList;

public class PagoSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public PagoSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public void acualizarVid(int cantidad){
        factoryController.actualizarVid(cantidad);
    }

    public String obtenerVideojuegoPrimerMomento(){
        return factoryController.getVidMomementaneo();
    }

    public String obtenerMunicipioSegundoMomento(){
        return factoryController.getMunMomentaneo();
    }

    public String obtenerDepartamentoSegundoMomento(){
        return factoryController.getDepMomentaneo();
    }

    public String obtenerPostalSegundoMomento(){
        return factoryController.getCodigMomentaneo();
    }

    public String obtenerDireccionSegundoMomento(){
        return factoryController.getDireMomentaneo();
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

    public int obtenerCantidadPrimerMomento(){
        return factoryController.getCantMomentanea();
    }

    public int obtenerCantidad2PrimerMomento(){
        return factoryController.getCantMomentanea2();
    }

    public Usuario traerUsuarioAuxiliar() {
        return factoryController.getUsuarioAuxiliar();
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

    public int traerVideojuegos(){
        return factoryController.traerVideojuegos();
    }

    public Compra guardarPrestamo(Compra prestamo){
        return factoryController.guardarCompra(prestamo);
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
