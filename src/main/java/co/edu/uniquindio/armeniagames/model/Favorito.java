package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;

public class Favorito {

    private int total;
    private String documentoJugador, jugador, apellido, codigo, nombreVideojuego;

    private TipoFormatoVideojuego tipoFormatoVideojuego;

    private TipoGeneroVideojuego tipoGeneroVideojuego;

    public Favorito (){};

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getDocumentoJugador() {
        return documentoJugador;
    }

    public void setDocumentoJugador(String documentoJugador) {
        this.documentoJugador = documentoJugador;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombreVideojuego() {
        return nombreVideojuego;
    }

    public void setNombreVideojuego(String nombreVideojuego) {
        this.nombreVideojuego = nombreVideojuego;
    }

    public TipoFormatoVideojuego getTipoFormatoVideojuego() {
        return tipoFormatoVideojuego;
    }

    public void setTipoFormatoVideojuego(TipoFormatoVideojuego tipoFormatoVideojuego) {
        this.tipoFormatoVideojuego = tipoFormatoVideojuego;
    }

    public TipoGeneroVideojuego getTipoGeneroVideojuego() {
        return tipoGeneroVideojuego;
    }

    public void setTipoGeneroVideojuego(TipoGeneroVideojuego tipoGeneroVideojuego) {
        this.tipoGeneroVideojuego = tipoGeneroVideojuego;
    }
}
