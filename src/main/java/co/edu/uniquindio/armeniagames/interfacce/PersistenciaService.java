package co.edu.uniquindio.armeniagames.interfacce;

import co.edu.uniquindio.armeniagames.model.Administrador;
import co.edu.uniquindio.armeniagames.model.Compra;
import co.edu.uniquindio.armeniagames.model.Jugador;
import co.edu.uniquindio.armeniagames.model.Videojuego;

import java.io.IOException;
import java.util.ArrayList;

public interface PersistenciaService {

    void guardaRegistroLog(String mensajeLog, int nivel, String accion);

    void guardarAdministrador(ArrayList<Administrador> listaAdministradores) throws IOException;

    void guardarJugador(ArrayList<Jugador> listaJugadores) throws IOException;

    ArrayList<Administrador> cargarAdministrador() throws IOException;

    void guardarVideojuego(ArrayList<Videojuego> listaVideojuegos) throws IOException;

    ArrayList<Videojuego> cargarVideojuego() throws IOException;

    ArrayList<Jugador> cargarJugador() throws IOException;

    void guardarCompra(ArrayList<Compra> listaPrestamos) throws IOException;

    ArrayList<Compra> cargarCompra() throws IOException;

}
