package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Administrador;
import co.edu.uniquindio.armeniagames.model.Tienda;
import co.edu.uniquindio.armeniagames.model.Jugador;

public class RecuperarClaveSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public RecuperarClaveSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public boolean cambiarClaveAdministrador(String documento, String clave, String confirmacion) {
        return factoryController.cambiarClaveAdministrador(documento, clave, confirmacion);
    }

    public boolean cambiarClaveJugador(String documento, String clave, String confirmacion) {
        return factoryController.cambiarClaveJugador(documento, clave, confirmacion);
    }

    public Administrador traerDatosAdministrador(String documento) {
        return factoryController.obtenerAdministrador(documento);
    }

    public Jugador traerDatosJugador(String documento) {
        return factoryController.obtenerJugador(documento);
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
