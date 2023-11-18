package co.edu.uniquindio.armeniagames.subcontroller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.model.*;

import java.util.ArrayList;

public class EnvioSubcontroller {

    private ModelFactory factoryController;
    private Tienda tienda;

    public EnvioSubcontroller(ModelFactory factoryController) {
        this.factoryController = factoryController;
        tienda = factoryController.getTienda();
    }

    public void compraSegundaEtapa(String departamento, String municipio, String postal, String direccion){
        factoryController.compraSegundaEtapa(departamento, municipio, postal, direccion);
    }

    public String obtenerVideojuegoSegundoMomento(){
        return factoryController.getVidMomementaneo();
    }

    public Videojuego obtenerVideojuego(String codigo) {
        return factoryController.obtenerVideojuego(codigo);
    }

    public void email(String titulo, String cuerpo, String correo, String img) {
        factoryController.correo(titulo, cuerpo, correo, img);
    }

    public ArrayList<Administrador> traerAdmins(){
        return factoryController.getListaAdministradores();
    }

    public Videojuego traerVideojuegoAuxiliar(String nombre){
        return factoryController.getVideojuegoAuxiliar(nombre);
    }

    public Compra guardarPrestamo(Compra prestamo){
        return factoryController.guardarCompra(prestamo);
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
