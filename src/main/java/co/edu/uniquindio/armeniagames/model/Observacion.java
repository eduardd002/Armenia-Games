package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoObservacion;

public class Observacion {

    private TipoObservacion tipoObservacion;
    private String observacion, documentoObservacion, nombreObservacion, apellidoObservacion;

    public Observacion(){

    }

    public String getDocumentoObservacion() {
        return documentoObservacion;
    }

    public void setDocumentoObservacion(String documentoObservacion) {
        this.documentoObservacion = documentoObservacion;
    }

    public String getNombreObservacion() {
        return nombreObservacion;
    }

    public void setNombreObservacion(String nombreObservacion) {
        this.nombreObservacion = nombreObservacion;
    }

    public String getApellidoObservacion() {
        return apellidoObservacion;
    }

    public void setApellidoObservacion(String apellidoObservacion) {
        this.apellidoObservacion = apellidoObservacion;
    }

    public TipoObservacion getTipoObservacion() {
        return tipoObservacion;
    }

    public void setTipoObservacion(TipoObservacion tipoObservacion) {
        this.tipoObservacion = tipoObservacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}
