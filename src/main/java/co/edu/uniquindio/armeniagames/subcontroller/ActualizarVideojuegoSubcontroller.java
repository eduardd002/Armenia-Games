package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Tienda;
import co.edu.uniquindio.armeniagames.model.Videojuego;

public class ActualizarVideojuegoSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public ActualizarVideojuegoSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public Videojuego obtenerVideojuego(String codigo) {
        return factoryController.obtenerVideojuego(codigo);
    }

    public boolean actualizarVideojuego(Videojuego videojuego) {
        return factoryController.actualizarVideojuego(videojuego);
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
