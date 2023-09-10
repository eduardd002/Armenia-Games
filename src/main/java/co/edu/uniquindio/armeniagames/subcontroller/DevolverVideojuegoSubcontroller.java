package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;

import java.util.ArrayList;

public class DevolverVideojuegoSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public DevolverVideojuegoSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public ArrayList<Jugador> traerJugadores() {
        return factoryController.getListaJugadores();
    }

    public ArrayList<Compra> traerCompras() {
        return factoryController.getListaCompras2();
    }

    public ArrayList<Videojuego> traerVideojuegos() {
        return factoryController.getListaVideojuegos();
    }

    public void actualizarHistorial(String jugador, int jugados){
        factoryController.actualizarHistorial2(jugador, jugados);
    }

    public boolean devolverCompra(int factura) {
        return factoryController.devolverCompra(factura);
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
