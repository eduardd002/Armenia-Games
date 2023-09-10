package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Tienda;
import co.edu.uniquindio.armeniagames.model.Videojuego;

public class EliminarVideojuegoSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public EliminarVideojuegoSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public ModelFactory getFactoryController() {
        return factoryController;
    }

    public boolean eliminarVideojuego(String codigo) {
        return factoryController.eliminarVideojuego(codigo);
    }

    public void setFactoryController(ModelFactory factoryController) {
        this.factoryController = factoryController;
    }

    public Videojuego obtenerVideojuego(String codigo) {
        return factoryController.obtenerVideojuego(codigo);
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

}
