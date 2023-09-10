package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoGeneroVideojuego;
import co.edu.uniquindio.armeniagames.enumm.TipoFormatoVideojuego;

import java.time.LocalDate;

public class Compra {

    private int factura, total;
    private String documentoJugador, jugador, apellido, codigo, nombreVideojuego;

    private TipoFormatoVideojuego tipoFormatoVideojuego;

    private TipoGeneroVideojuego tipoGeneroVideojuego;

    private LocalDate fechaCompraInicial, fechaCompraFinal;

    public Compra(){}

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

    public LocalDate getFechaCompraInicial() {
        return fechaCompraInicial;
    }

    public void setFechaCompraInicial(LocalDate fechaCompraInicial) {
        this.fechaCompraInicial = fechaCompraInicial;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public LocalDate getFechaCompraFinal() {
        return fechaCompraFinal;
    }

    public int getFactura() {
        return factura;
    }

    public void setFactura(int factura) {
        this.factura = factura;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

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

    public void setFechaCompraFinal(LocalDate fechaCompraFinal) {
        this.fechaCompraFinal = fechaCompraFinal;
    }
}
