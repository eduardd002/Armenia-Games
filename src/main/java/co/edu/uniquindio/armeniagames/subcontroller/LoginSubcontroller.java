package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Jugador;
import co.edu.uniquindio.armeniagames.model.Tienda;
import co.edu.uniquindio.armeniagames.model.Usuario;

public class LoginSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public LoginSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public void email(String titulo, String cuerpo, String correo, String img) {
        factoryController.correo(titulo, cuerpo, correo, img);
    }

    public void establecerIntentos(String correo) {
        factoryController.establecerIntentos(correo);
    }

    public void bloquearCuenta(String correo) {
        factoryController.bloquearCuenta(correo);
    }

    public Usuario login(Usuario usuario) {
        return factoryController.login(usuario);
    }

    public Jugador traerJug(String correo){
        return factoryController.obtenerJugador2(correo);
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
