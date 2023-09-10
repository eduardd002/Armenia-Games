package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Tienda;
import co.edu.uniquindio.armeniagames.model.Videojuego;

public class GuardarVideojuegoSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public GuardarVideojuegoSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public Videojuego guardarVideojuego(Videojuego videojuego){
        return factoryController.guardarVideojuego(videojuego);
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
