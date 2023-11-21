package co.edu.uniquindio.armeniagames.model;

public class Jugador extends Usuario{

    private int videojuegosComprados;

    public Jugador(){}

    public int getVideojuegosComprados() {
        return videojuegosComprados;
    }

    public void setVideojuegosComprados(int videojuegosComprados) {
        this.videojuegosComprados = videojuegosComprados;
    }
}
