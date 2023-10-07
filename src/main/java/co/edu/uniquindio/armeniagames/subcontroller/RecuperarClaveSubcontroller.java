package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.enumm.TipoUsuario;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Tienda;

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

    public void email(String titulo, String cuerpo, String correo, int codigo) {
        factoryController.correo2(titulo, cuerpo, correo, codigo);
    }

    public void email2(String titulo, String cuerpo, String correo, String img) {
        factoryController.correo(titulo, cuerpo, correo, img);
    }

    public int generarNum(){
        return factoryController.generarNum2();
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
