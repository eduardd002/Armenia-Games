package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Tienda;
import co.edu.uniquindio.armeniagames.model.Jugador;

public class RegistroJugadorSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public RegistroJugadorSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public void email(String titulo, String cuerpo, String correo, String imagen) {
        factoryController.correo(titulo, cuerpo, correo, imagen);
    }

    public Jugador registrarJugador(Jugador jug){
        return factoryController.guardarJugador(jug);
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
