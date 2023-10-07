package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.Administrador;
import co.edu.uniquindio.armeniagames.model.Tienda;

public class RegistroAdministradorSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public RegistroAdministradorSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public Administrador registrarAdministrador(Administrador ad){
        return factoryController.guardarAdministrador(ad);
    }

    public void email(String titulo, String cuerpo, String correo, String img) {
        factoryController.correo(titulo, cuerpo, correo, img);
    }

    public int generarCarnet(){
        return factoryController.generarNum();
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
