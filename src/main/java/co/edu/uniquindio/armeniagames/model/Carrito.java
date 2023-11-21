package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;

public class Carrito {

    private int totalCarrito;

    private int unidadesCarrito;
    private String documentoJugadorCarrito, jugadorCarrito, apellidoCarrito, codigoCarrito, nombreVideojuegoCarrito;

    private TipoFormatoVideojuego tipoFormatoVideojuegoCarrito;

    private TipoGeneroVideojuego tipoGeneroVideojuegoCarrito;

    public Carrito(){}

    public int getTotalCarrito() {
        return totalCarrito;
    }

    public void setTotalCarrito(int totalCarrito) {
        this.totalCarrito = totalCarrito;
    }

    public String getDocumentoJugadorCarrito() {
        return documentoJugadorCarrito;
    }

    public int getUnidadesCarrito() {
        return unidadesCarrito;
    }

    public void setUnidadesCarrito(int unidadesCarrito) {
        this.unidadesCarrito = unidadesCarrito;
    }

    public void setDocumentoJugadorCarrito(String documentoJugadorCarrito) {
        this.documentoJugadorCarrito = documentoJugadorCarrito;
    }

    public String getJugadorCarrito() {
        return jugadorCarrito;
    }

    public void setJugadorCarrito(String jugadorCarrito) {
        this.jugadorCarrito = jugadorCarrito;
    }

    public String getApellidoCarrito() {
        return apellidoCarrito;
    }

    public void setApellidoCarrito(String apellidoCarrito) {
        this.apellidoCarrito = apellidoCarrito;
    }

    public String getCodigoCarrito() {
        return codigoCarrito;
    }

    public void setCodigoCarrito(String codigoCarrito) {
        this.codigoCarrito = codigoCarrito;
    }

    public String getNombreVideojuegoCarrito() {
        return nombreVideojuegoCarrito;
    }

    public void setNombreVideojuegoCarrito(String nombreVideojuegoCarrito) {
        this.nombreVideojuegoCarrito = nombreVideojuegoCarrito;
    }

    public TipoFormatoVideojuego getTipoFormatoVideojuegoCarrito() {
        return tipoFormatoVideojuegoCarrito;
    }

    public void setTipoFormatoVideojuegoCarrito(TipoFormatoVideojuego tipoFormatoVideojuegoCarrito) {
        this.tipoFormatoVideojuegoCarrito = tipoFormatoVideojuegoCarrito;
    }

    public TipoGeneroVideojuego getTipoGeneroVideojuegoCarrito() {
        return tipoGeneroVideojuegoCarrito;
    }

    public void setTipoGeneroVideojuegoCarrito(TipoGeneroVideojuego tipoGeneroVideojuegoCarrito) {
        this.tipoGeneroVideojuegoCarrito = tipoGeneroVideojuegoCarrito;
    }

}
