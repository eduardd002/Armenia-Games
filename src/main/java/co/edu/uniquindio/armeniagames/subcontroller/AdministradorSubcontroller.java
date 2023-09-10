package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;

import java.util.ArrayList;

public class AdministradorSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public AdministradorSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public void actualizarAdministrador(Administrador administrador) {
        factoryController.actualizarAdministrador(administrador);
    }

    public boolean eliminarAdministrador(String documento) {
        return factoryController.eliminarAdministrador(documento);
    }

    public ArrayList<Videojuego> obtenerVideojuegos(){
        return factoryController.getListaVideojuegos();
    }

    public ArrayList<Jugador> obtenerJugadores(){
        return factoryController.getListaJugadores();
    }

    public ArrayList<Compra> obtenerAlquileres(){
        return factoryController.getListaCompras2();
    }

    public Usuario traerUsuarioAuxiliar() {
        return factoryController.getUsuarioAuxiliar();
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
