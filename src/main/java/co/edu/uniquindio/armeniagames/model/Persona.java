package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoEstadoCivil;
import co.edu.uniquindio.armeniagames.enumm.TipoGenero;

import java.time.LocalDate;

public abstract class Persona {

    private String documento, nombrePersona, apellido;

    private LocalDate fechaNacimiento;

    private TipoEstadoCivil tipoEstadoCivil;

    private TipoGenero tipoGenero;

    public Persona(){}

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public TipoEstadoCivil getTipoEstadoCivil() {
        return tipoEstadoCivil;
    }

    public void setTipoEstadoCivil(TipoEstadoCivil tipoEstadoCivil) {
        this.tipoEstadoCivil = tipoEstadoCivil;
    }

    public TipoGenero getTipoGenero() {
        return tipoGenero;
    }

    public void setTipoGenero(TipoGenero tipoGenero) {
        this.tipoGenero = tipoGenero;
    }
}
