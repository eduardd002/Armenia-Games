package co.edu.uniquindio.armeniagames.interfacce;

import co.edu.uniquindio.armeniagames.enumm.TipoUsuario;
import co.edu.uniquindio.armeniagames.exception.*;
import co.edu.uniquindio.armeniagames.model.*;

import java.io.IOException;
import java.util.ArrayList;

public interface TiendaService {

    boolean validarUsuario(Usuario usu) throws IOException;

    Usuario login(Usuario usuario)
            throws JugadorNoExisteException, AdministradorNoExisteException;

    boolean iniciarSesion(Usuario usuario)
            throws IOException, UsuarioNoExisteException;

    Administrador guardarAdministrador(Administrador ad) throws JugadorExisteException, ContraseniasNoCoincidenException, ClaveNoSeguraException;

    boolean eliminarVideojuego(String codigo) throws VideojuegoNoExisteException;

    boolean verificarAdministradorExiste(String documento, TipoUsuario tipoUsuario);

    Jugador obtenerJugador(String documento);

    Jugador guardarJugador(Jugador jug) throws JugadorExisteException, ContraseniasNoCoincidenException, ClaveNoSeguraException;

    Compra guardarCompra(Compra compra);

    boolean verificarJugadorExiste(String documento, TipoUsuario tipoUsuario);

    Administrador obtenerAdministrador(String documento);

    boolean claveIncorrecta(String clave, String confirmacion);

    Videojuego obtenerVideojuego(String codigo);

    Compra obtenerCompra(int factura);

    boolean verificarVideojuegoExiste(String codigo);

    Videojuego mostrarDatosVideojuego(String codigo) throws VideojuegoNoExisteException;

    Videojuego guardarVideojuego(Videojuego videojuego) throws VideojuegoExisteException, IOException;

    boolean devolverVideojuego(int factura) throws CompraNoExisteException;

    boolean actualizarVideojuego(Videojuego videojuego)
            throws VideojuegoNoExisteException;

    void actualizarAdministrador(Administrador administrador) throws ClaveNoSeguraException;

    boolean eliminarAdministrador(String documento) throws AdministradorNoExisteException;

    void actualizarJugador(Jugador jugador) throws ClaveNoSeguraException;

    boolean eliminarJugador(String documento) throws JugadorNoExisteException;

    boolean cambiarClaveJugador(String documento, String clave, String confirmacion)
            throws JugadorNoExisteException, ContraseniasNoCoincidenException;

    boolean cambiarClaveAdministrador(String documento, String clave, String confirmacion)
            throws AdministradorNoExisteException, ContraseniasNoCoincidenException;

    Administrador mostrarDatosAdministrador(String documento) throws AdministradorNoExisteException;

    Jugador mostrarDatosJugador(String documento) throws JugadorNoExisteException;

    int generarNumAleatorio();

    void disminuirInventario(String videojuego, int inventarioActual);

    void incrementarInventario(String videojuego, int inventarioActual);

    String chat(int opcion) throws InterruptedException;

    void actualizarHistorial(String jugador, int comprados);

    void actualizarHistorial2(String jugador, int comprados);

    ArrayList<Videojuego> getListaVideojuegos();

    ArrayList<Jugador> getListaJugadores();

    ArrayList<Administrador> getListaAdministradores();

    ArrayList<Compra> getListaCompras();

}
