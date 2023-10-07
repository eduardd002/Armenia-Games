package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.*;

import java.time.LocalDate;

public class Jugador extends Usuario{

    private TipoBanco tipoBanco;

    private TipoCuenta tipoCuenta;

    private String numeroCuenta, titular, codigoPostal, direccion, barrio, municipio;

    private LocalDate fechaCaducidad;

    private TipoDepartamento tipoDepartamento;

    private TipoResidencia tipoResidencia;
    private int videojuegosComprados, intentos;

    private TipoRestriccion tipoRestriccion;

    public Jugador(){}

    public TipoCuenta getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(TipoCuenta tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public LocalDate getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(LocalDate fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public TipoRestriccion getTipoRestriccion() {
        return tipoRestriccion;
    }

    public void setTipoRestriccion(TipoRestriccion tipoRestriccion) {
        this.tipoRestriccion = tipoRestriccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public TipoDepartamento getTipoDepartamento() {
        return tipoDepartamento;
    }

    public void setTipoDepartamento(TipoDepartamento tipoDepartamento) {
        this.tipoDepartamento = tipoDepartamento;
    }

    public TipoResidencia getTipoResidencia() {
        return tipoResidencia;
    }

    public void setTipoResidencia(TipoResidencia tipoResidencia) {
        this.tipoResidencia = tipoResidencia;
    }

    public TipoBanco getTipoBanco() {
        return tipoBanco;
    }

    public void setTipoBanco(TipoBanco tipoBanco) {
        this.tipoBanco = tipoBanco;
    }

    public int getVideojuegosComprados() {
        return videojuegosComprados;
    }

    public void setVideojuegosComprados(int videojuegosComprados) {
        this.videojuegosComprados = videojuegosComprados;
    }
}
