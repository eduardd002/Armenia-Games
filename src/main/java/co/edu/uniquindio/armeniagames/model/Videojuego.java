package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;

public class Videojuego {

    private String codigo, nombreVideojuego, anioLanzamiento;

    private TipoGeneroVideojuego tipoGeneroVideojuego;

    private String imagenVideojuego;

    private TipoFormatoVideojuego tipoFormatoVideojuego;

    private int clasificacion, unidades, precio;

    public Videojuego(){}

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public TipoGeneroVideojuego getTipoGeneroVideojuego() {
        return tipoGeneroVideojuego;
    }

    public void setTipoGeneroVideojuego(TipoGeneroVideojuego tipoGeneroVideojuego) {
        this.tipoGeneroVideojuego = tipoGeneroVideojuego;
    }

    public TipoFormatoVideojuego getTipoFormatoVideojuego() {
        return tipoFormatoVideojuego;
    }

    public void setTipoFormatoVideojuego(TipoFormatoVideojuego tipoFormatoVideojuego) {
        this.tipoFormatoVideojuego = tipoFormatoVideojuego;
    }

    public String getAnioLanzamiento() {
        return anioLanzamiento;
    }

    public void setAnioLanzamiento(String anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    public int getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(int clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public String getImagenVideojuego() {
        return imagenVideojuego;
    }

    public void setImagenVideojuego(String imagenVideojuego) {
        this.imagenVideojuego = imagenVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }
}
