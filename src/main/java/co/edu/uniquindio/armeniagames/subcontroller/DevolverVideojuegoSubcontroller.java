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

    public Jugador traerJugador(String documento) {
        return factoryController.traerJugadorEnvioYPago(documento);
    }

    public Compra traerCompra(int factura) {
        return factoryController.obtenerCompra(factura);
    }

    public void actualizarHistorial(String jugador, int jugados){
        factoryController.actualizarHistorial2(jugador, jugados);
    }

    public void email(String titulo, String cuerpo, String correo, String img) {
        factoryController.correo(titulo, cuerpo, correo, img);
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
