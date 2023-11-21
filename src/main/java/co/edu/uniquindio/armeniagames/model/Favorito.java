package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;

public class Favorito {

    private int totalFavorito;
    private String documentoJugadorFavorito, jugadorFavorito, apellidoFavorito, codigoFavorito, nombreVideojuegoFavorito;

    private TipoFormatoVideojuego tipoFormatoVideojuegoFavorito;

    private TipoGeneroVideojuego tipoGeneroVideojuegoFavorito;

    public Favorito (){}

    public int getTotalFavorito() {
        return totalFavorito;
    }

    public void setTotalFavorito(int totalFavorito) {
        this.totalFavorito = totalFavorito;
    }

    public String getDocumentoJugadorFavorito() {
        return documentoJugadorFavorito;
    }

    public void setDocumentoJugadorFavorito(String documentoJugadorFavorito) {
        this.documentoJugadorFavorito = documentoJugadorFavorito;
    }

    public String getJugadorFavorito() {
        return jugadorFavorito;
    }

    public void setJugadorFavorito(String jugadorFavorito) {
        this.jugadorFavorito = jugadorFavorito;
    }

    public String getApellidoFavorito() {
        return apellidoFavorito;
    }

    public void setApellidoFavorito(String apellidoFavorito) {
        this.apellidoFavorito = apellidoFavorito;
    }

    public String getCodigoFavorito() {
        return codigoFavorito;
    }

    public void setCodigoFavorito(String codigoFavorito) {
        this.codigoFavorito = codigoFavorito;
    }

    public String getNombreVideojuegoFavorito() {
        return nombreVideojuegoFavorito;
    }

    public void setNombreVideojuegoFavorito(String nombreVideojuegoFavorito) {
        this.nombreVideojuegoFavorito = nombreVideojuegoFavorito;
    }

    public TipoFormatoVideojuego getTipoFormatoVideojuegoFavorito() {
        return tipoFormatoVideojuegoFavorito;
    }

    public void setTipoFormatoVideojuegoFavorito(TipoFormatoVideojuego tipoFormatoVideojuegoFavorito) {
        this.tipoFormatoVideojuegoFavorito = tipoFormatoVideojuegoFavorito;
    }

    public TipoGeneroVideojuego getTipoGeneroVideojuegoFavorito() {
        return tipoGeneroVideojuegoFavorito;
    }

    public void setTipoGeneroVideojuegoFavorito(TipoGeneroVideojuego tipoGeneroVideojuegoFavorito) {
        this.tipoGeneroVideojuegoFavorito = tipoGeneroVideojuegoFavorito;
    }
}
