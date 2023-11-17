package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.*;

import java.time.LocalDate;

public class Jugador extends Usuario{

    private int videojuegosComprados, intentos;

    public Jugador(){}

    public int getIntentos() {
        return intentos;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public int getVideojuegosComprados() {
        return videojuegosComprados;
    }

    public void setVideojuegosComprados(int videojuegosComprados) {
        this.videojuegosComprados = videojuegosComprados;
    }
}
