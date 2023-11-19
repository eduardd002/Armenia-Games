package co.edu.uniquindio.armeniagames.model;

import co.edu.uniquindio.armeniagames.enumm.TipoRestriccion;
import co.edu.uniquindio.armeniagames.enumm.TipoUsuario;
import co.edu.uniquindio.armeniagames.exception.*;
import co.edu.uniquindio.armeniagames.persistence.Persistencia;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tienda{

    public Persistencia persistencia = new Persistencia();
    private final ArrayList<Videojuego> listaVideojuegos = new ArrayList<>();
    private final ArrayList<Jugador> listaJugadores = new ArrayList<>();
    private final ArrayList<Administrador> listaAdministradores = new ArrayList<>();
    private final ArrayList<Compra> listaCompras = new ArrayList<>();
    private final ArrayList<Favorito> listaFavorito = new ArrayList<>();
    private final ArrayList<Carrito> listaCarrito = new ArrayList<>();
    private int compras;

    public Tienda() {
    }

    /**
     * Valida un usuario comparando su información con las listas de Administradores y Jugadores cargadas desde la persistencia.
     *
     * @param usu El objeto Usuario que se va a validar.
     * @return true si el usuario es válido, false si no.
     * @throws IOException              Si ocurre un error de lectura de datos desde la persistencia.
     * @throws CuentaBloqueadaException Si la cuenta del jugador está bloqueada.
     */
    public boolean validarUsuario(Usuario usu) throws IOException, CuentaBloqueadaException {

        boolean esCorrecto = false;
        // Cargar la lista de Administradores desde la persistencia
        ArrayList<Administrador> administrador = persistencia.cargarAdministrador();
        // Cargar la lista de Jugadores desde la persistencia
        ArrayList<Jugador> jugador = persistencia.cargarJugador();
        // Validar si el usuario es de tipo Jugador
        if (usu.getTipoUsuario().equals(TipoUsuario.Jugador)) {
            for (Jugador jug : jugador) {
                if (jug.getTipoRestriccion().equals(TipoRestriccion.DENEGADO) &&
                        jug.getCorreo().equals(usu.getCorreo()) && jug.getClave().equals(usu.getClave())
                        && jug.getTipoUsuario().equals(usu.getTipoUsuario())) {
                    // Si el jugador tiene restricciones DENEGADAS, lanzar una excepción de cuenta bloqueada
                    throw new CuentaBloqueadaException();
                } else if (jug.getCorreo().equals(usu.getCorreo()) && jug.getClave().equals(usu.getClave())
                        && jug.getTipoUsuario().equals(usu.getTipoUsuario()) && jug.getTipoRestriccion().equals(TipoRestriccion.CONFIRMADO)) {
                    // Si el jugador tiene restricciones CONFIRMADAS, establecer esCorrecto en true y salir del bucle
                    esCorrecto = true;
                    break;
                }
            }
        }
        // Validar si el usuario es de tipo Administrador
        if (usu.getTipoUsuario().equals(TipoUsuario.Administrador)) {
            for (Administrador admin : administrador) {
                if (admin.getCorreo().equals(usu.getCorreo()) && admin.getClave().equals(usu.getClave())
                        && admin.getTipoUsuario().equals(usu.getTipoUsuario())) {
                    // Si el administrador es válido, establecer esCorrecto en true y salir del bucle
                    esCorrecto = true;
                    break;
                }
            }
        }
        // Devolver el resultado de la validación
        return esCorrecto;
    }

    /**
     * Realiza el proceso de inicio de sesión para un usuario dado.
     *
     * @param usuario El objeto Usuario que se va a utilizar para el inicio de sesión.
     * @return Un objeto Usuario si el inicio de sesión es exitoso, o null si no se encuentra el usuario.
     * @throws JugadorNoExisteException     Si el jugador no existe en la lista de Jugadores.
     * @throws AdministradorNoExisteException Si el administrador no existe en la lista de Administradores.
     */
    public Usuario login(Usuario usuario)
            throws JugadorNoExisteException, AdministradorNoExisteException {

        Usuario usu = null;

        if (usuario.getTipoUsuario().equals(TipoUsuario.Administrador)) {
            // Proceso de inicio de sesión para Administradores
            for (int i = 0; i < getListaAdministradores().size();) {

                if (getListaAdministradores().get(i).getCorreo().equals(usuario.getCorreo())
                        && getListaAdministradores().get(i).getClave().equals(usuario.getClave())
                        && getListaAdministradores().get(i).getTipoUsuario().equals(usuario.getTipoUsuario())) {

                    usu = getListaAdministradores().get(i);
                    // Si se encuentra el administrador, salir del bucle
                    break;
                } else {
                    i++;
                }
            }
        }

        if (usuario.getTipoUsuario().equals(TipoUsuario.Jugador)) {
            // Proceso de inicio de sesión para Jugadores
            for (int i = 0; i < getListaJugadores().size();) {

                if (getListaJugadores().get(i).getCorreo().equals(usuario.getCorreo())
                        && getListaJugadores().get(i).getClave().equals(usuario.getClave())
                        && getListaJugadores().get(i).getTipoUsuario().equals(usuario.getTipoUsuario())) {

                    usu = getListaJugadores().get(i);
                    // Si se encuentra el jugador, salir del bucle
                    break;
                } else {
                    i++;
                }
            }
        }
        // Devolver el objeto Usuario encontrado o null si no se encontró ningún usuario.
        return usu;
    }

    /**
     * Valida si un jugador está bloqueado o no basado en su tipo de restricción.
     *
     * @param usu El objeto Usuario que se va a validar para bloqueo.
     * @return true si el jugador no está bloqueado (restricción CONFIRMADA) o si el usuario no es un Jugador.
     *         false si el jugador está bloqueado (restricción DENEGADA).
     * @throws IOException Si ocurre un error de lectura de datos desde la persistencia.
     */
    public boolean validarBloqueo(Usuario usu) throws IOException {
        // Por defecto, asumimos que el usuario no está bloqueado
        boolean esCorrecto = false;
        ArrayList<Jugador> jugador = persistencia.cargarJugador();
        // Si el usuario es de tipo Jugador, verificamos su restricción
        if (usu.getTipoUsuario().equals(TipoUsuario.Jugador)) {
            for (Jugador jug : jugador) {
                if (jug.getTipoRestriccion().equals(TipoRestriccion.CONFIRMADO)) {
                    esCorrecto = true;
                    break;
                }else{
                    // Si al menos un jugador tiene restricción DENEGADA, marcamos esCorrecto como false y salimos del bucle
                    esCorrecto = false;
                }
            }
        }else{
            esCorrecto = true;
        }
        // Si el usuario no es de tipo Jugador, o si no se encontró ningún Jugador con restricción DENEGADA, esCorrecto se mantiene como true
        return esCorrecto;
    }

    /**
     * Inicia sesión para un usuario verificando su existencia y estado de bloqueo.
     *
     * @param usuario El objeto Usuario que se intentará autenticar.
     * @return true si el inicio de sesión es exitoso, false en caso contrario.
     * @throws IOException              Si ocurre un error de lectura de datos desde la persistencia.
     * @throws UsuarioNoExisteException  Si el usuario no existe.
     * @throws CuentaBloqueadaException Si la cuenta del usuario está bloqueada.
     */
    public boolean iniciarSesion(Usuario usuario)
            throws IOException, UsuarioNoExisteException, CuentaBloqueadaException {
        // Validar la existencia del usuario y el estado de bloqueo
        if (validarUsuario(usuario) && validarBloqueo(usuario)) {
            // El inicio de sesión es exitoso
            return true;
        } else if(!validarUsuario(usuario)){
            // Lanzar excepción si el usuario no existe
            throw new UsuarioNoExisteException();
        } else if(!validarBloqueo(usuario)){
            // Lanzar excepción si la cuenta está bloqueada
            throw new CuentaBloqueadaException();
        }
        // Si no se cumplen las condiciones anteriores, el inicio de sesión no es exitoso
        return false;
    }

    public Administrador guardarAdministrador(Administrador ad) throws JugadorExisteException, ContraseniasNoCoincidenException, ClaveNoSeguraException {

        Administrador administrador;
        boolean existeAdministrador = verificarAdministradorExiste(ad.getDocumento(), ad.getTipoUsuario());

        if (existeAdministrador) {
            throw new JugadorExisteException();
        } else if (claveIncorrecta(ad.getClave(), ad.getConfirmacionClave())) {
            throw new ContraseniasNoCoincidenException();
        } else if (!validarClave(ad.getClave())) {
            throw new ClaveNoSeguraException();
        }else{

            administrador = new Administrador();

            administrador.setDocumento(ad.getDocumento());
            administrador.setNombrePersona(ad.getNombrePersona());
            administrador.setApellido(ad.getApellido());
            administrador.setFechaNacimiento(ad.getFechaNacimiento());
            administrador.setTipoEstadoCivil(ad.getTipoEstadoCivil());
            administrador.setTipoGenero(ad.getTipoGenero());
            administrador.setCorreo(ad.getCorreo());
            administrador.setClave(ad.getClave());
            administrador.setConfirmacionClave(ad.getConfirmacionClave());
            administrador.setTipoUsuario(ad.getTipoUsuario());
            administrador.setTelefono(ad.getTelefono());
            administrador.setCarnet(ad.getCarnet());

            administrador.setImagen(ad.getImagen());

            getListaAdministradores().add(administrador);
        }
        return administrador;
    }

    public boolean eliminarVideojuego(String codigo) throws VideojuegoNoExisteException {

        Videojuego videojuego;

        videojuego = obtenerVideojuego(codigo);

        if (videojuego != null) {
            getListaVideojuegos().remove(videojuego);
        } else {
            throw new VideojuegoNoExisteException();
        }
        return true;
    }

    public boolean verificarAdministradorExiste(String documento, TipoUsuario tipoUsuario) {

        Administrador administrador;
        boolean existeAdministrador = false;

        if (tipoUsuario == TipoUsuario.Administrador) {
            administrador = obtenerAdministrador(documento);
            existeAdministrador = administrador != null;
        }
        return existeAdministrador;
    }

    public Jugador obtenerJugador(String documento) {

        Jugador jug = null;

        for (Jugador jugador : listaJugadores) {
            if (jugador.getDocumento().equals(documento)) {
                jug = jugador;
                break;
            }
        }
        return jug;
    }

    public Jugador obtenerJugador2(String correo) {

        Jugador jug = null;

        for (Jugador jugador : listaJugadores) {
            if (jugador.getCorreo().equals(correo)) {
                jug = jugador;
                break;
            }
        }
        return jug;
    }

    public Jugador guardarJugador(Jugador jug) throws JugadorExisteException, ContraseniasNoCoincidenException, ClaveNoSeguraException {

        Jugador jugador;
        boolean existeJugador = verificarJugadorExiste(jug.getDocumento(), jug.getTipoUsuario());

        if (existeJugador) {
            throw new JugadorExisteException();
        } else if (claveIncorrecta(jug.getClave(), jug.getConfirmacionClave())) {
            throw new ContraseniasNoCoincidenException();
        } else if (!validarClave(jug.getClave())) {
            throw new ClaveNoSeguraException();
        }else{

            jugador = new Jugador();

            jugador.setDocumento(jug.getDocumento());
            jugador.setNombrePersona(jug.getNombrePersona());
            jugador.setApellido(jug.getApellido());
            jugador.setFechaNacimiento(jug.getFechaNacimiento());
            jugador.setTipoEstadoCivil(jug.getTipoEstadoCivil());
            jugador.setTipoGenero(jug.getTipoGenero());
            jugador.setCorreo(jug.getCorreo());
            jugador.setClave(jug.getClave());
            jugador.setConfirmacionClave(jug.getConfirmacionClave());
            jugador.setTipoUsuario(jug.getTipoUsuario());
            jugador.setTelefono(jug.getTelefono());

            jugador.setVideojuegosComprados(jug.getVideojuegosComprados());

            jugador.setImagen(jug.getImagen());
            jugador.setTipoRestriccion(jug.getTipoRestriccion());
            jugador.setIntentos(jug.getIntentos());

            getListaJugadores().add(jugador);
        }
        return jugador;
    }

    public Compra guardarCompra(Compra compra) {

        Compra comp = new Compra();

        comp.setDocumentoJugador(compra.getDocumentoJugador());
        comp.setFactura(compra.getFactura());
        comp.setJugador(compra.getJugador());
        comp.setApellido(compra.getApellido());
        comp.setCodigo(compra.getCodigo());
        comp.setTotal(compra.getTotal());
        comp.setNombreVideojuego(compra.getNombreVideojuego());
        comp.setTipoFormatoVideojuego(compra.getTipoFormatoVideojuego());
        comp.setTipoGeneroVideojuego(compra.getTipoGeneroVideojuego());
        comp.setFechaCompraInicial(compra.getFechaCompraInicial());
        comp.setUnidades(compra.getUnidades());
        comp.setFechaCompraFinal(compra.getFechaCompraFinal().plusMonths(3));

        getListaCompras().add(comp);

        return comp;
    }

    public Compra guardarCompraCarrito(Compra compra) {

        Compra comp = new Compra();

        comp.setFactura(compra.getFactura());
        comp.setDocumentoJugador(compra.getDocumentoJugador());
        comp.setJugador(compra.getJugador());
        comp.setApellido(compra.getApellido());
        comp.setCodigo(compra.getCodigo());
        comp.setTotal(compra.getTotal());
        comp.setNombreVideojuego(compra.getNombreVideojuego());
        comp.setTipoFormatoVideojuego(compra.getTipoFormatoVideojuego());
        comp.setTipoGeneroVideojuego(compra.getTipoGeneroVideojuego());
        comp.setFechaCompraInicial(compra.getFechaCompraInicial());
        comp.setUnidades(compra.getUnidades());
        comp.setFechaCompraFinal(compra.getFechaCompraFinal().plusMonths(3));

        getListaCompras().add(comp);

        return comp;
    }

    public Carrito guardarCarrito(Carrito carrito) {

        Carrito car = new Carrito();

        car.setDocumentoJugadorCarrito(carrito.getDocumentoJugadorCarrito());
        car.setJugadorCarrito(carrito.getJugadorCarrito());
        car.setApellidoCarrito(carrito.getApellidoCarrito());
        car.setCodigoCarrito(carrito.getCodigoCarrito());
        car.setTotalCarrito(carrito.getTotalCarrito());
        car.setUnidadesCarrito(carrito.getUnidadesCarrito());
        car.setNombreVideojuegoCarrito(carrito.getNombreVideojuegoCarrito());
        car.setTipoFormatoVideojuegoCarrito(carrito.getTipoFormatoVideojuegoCarrito());
        car.setTipoGeneroVideojuegoCarrito(carrito.getTipoGeneroVideojuegoCarrito());

        getListaCarrito().add(car);

        return car;
    }

    public Favorito guardarFavorito(Favorito fav) {

        Favorito favorito = new Favorito();

        favorito.setDocumentoJugadorFavorito(fav.getDocumentoJugadorFavorito());
        favorito.setJugadorFavorito(fav.getJugadorFavorito());
        favorito.setApellidoFavorito(fav.getApellidoFavorito());
        favorito.setCodigoFavorito(fav.getCodigoFavorito());
        favorito.setTotalFavorito(fav.getTotalFavorito());
        favorito.setNombreVideojuegoFavorito(fav.getNombreVideojuegoFavorito());
        favorito.setTipoFormatoVideojuegoFavorito(fav.getTipoFormatoVideojuegoFavorito());
        favorito.setTipoGeneroVideojuegoFavorito(fav.getTipoGeneroVideojuegoFavorito());

        getListaFavoritos().add(favorito);

        return favorito;
    }

    public boolean verificarJugadorExiste(String documento, TipoUsuario tipoUsuario) {

        Jugador jugador;
        boolean existeJugador = false;

        if (tipoUsuario == TipoUsuario.Jugador) {
            jugador = obtenerJugador(documento);
            existeJugador = jugador != null;
        }
        return existeJugador;
    }

    public Administrador obtenerAdministrador(String documento) {

        Administrador admin = null;

        for (Administrador administrador : listaAdministradores) {
            if (administrador.getDocumento().equals(documento)) {
                admin = administrador;
                break;
            }
        }
        return admin;
    }

    public Administrador obtenerAdministrador2(String correo) {

        Administrador admin = null;

        for (Administrador administrador : listaAdministradores) {
            if (administrador.getCorreo().equals(correo)) {
                admin = administrador;
                break;
            }
        }
        return admin;
    }

    public boolean claveIncorrecta(String clave, String confirmacion) {
        return !clave.equals(confirmacion);
    }

    public boolean validarClave(String clave) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(clave);
        return matcher.matches();
    }

    public Videojuego obtenerVideojuego(String codigo) {

        Videojuego vid = null;

        for (Videojuego videojuego : listaVideojuegos) {
            if (videojuego.getNombreVideojuego().equals(codigo)) {
                vid = videojuego;
                break;
            }
        }
        return vid;
    }

    public Videojuego obtenerVideojuego2(String codigo) {

        Videojuego vid = null;

        for (Videojuego videojuego : listaVideojuegos) {
            if (videojuego.getCodigo().equals(codigo)) {
                vid = videojuego;
                break;
            }
        }
        return vid;
    }

    public Compra obtenerCompra(int factura) {

        Compra comp = null;

        for (Compra compra : listaCompras) {
            if (compra.getFactura() == factura) {
                comp = compra;
                break;
            }
        }
        return comp;
    }

    public Compra obtenerCompra2(int factura) throws CompraNoExisteException {

        Compra comp = null;

        for (Compra compra : listaCompras) {
            if (compra.getFactura() == factura) {
                comp = compra;
                break;
            }else{
                throw new CompraNoExisteException();
            }
        }
        return comp;
    }

    public boolean verificarVideojuegoExiste(String codigo) {

        Videojuego videojuego;
        boolean existeVideojuego;

        videojuego = obtenerVideojuego(codigo);
        existeVideojuego = videojuego != null;

        return existeVideojuego;
    }

    public Videojuego mostrarDatosVideojuego(String codigo) throws VideojuegoNoExisteException {

        Videojuego videojuego;
        videojuego = obtenerVideojuego(codigo);

        if (videojuego != null) {

            for (int i = 0; i < getListaVideojuegos().size(); i++) {
                if (getListaVideojuegos().get(i).getNombreVideojuego().equals(codigo)) {

                    videojuego.setCodigo(videojuego.getCodigo());
                    videojuego.setNombreVideojuego(videojuego.getNombreVideojuego());
                    videojuego.setPrecio(videojuego.getPrecio());
                    videojuego.setTipoFormatoVideojuego(videojuego.getTipoFormatoVideojuego());
                    videojuego.setTipoGeneroVideojuego(videojuego.getTipoGeneroVideojuego());
                    videojuego.setAnioLanzamiento(videojuego.getAnioLanzamiento());

                    getListaVideojuegos().set(i, videojuego);
                }
            }
        } else {
            throw new VideojuegoNoExisteException();
        }
        return videojuego;
    }

    public Videojuego guardarVideojuego(Videojuego videojuego) throws VideojuegoExisteException, IOException {

        Videojuego vid;

        boolean existeVideojuego = verificarVideojuegoExiste(videojuego.getCodigo());

        if (existeVideojuego) {
            throw new VideojuegoExisteException();
        } else {
            vid = new Videojuego();

            vid.setCodigo(videojuego.getCodigo());
            vid.setNombreVideojuego(videojuego.getNombreVideojuego());
            vid.setPrecio(videojuego.getPrecio());
            vid.setTipoGeneroVideojuego(videojuego.getTipoGeneroVideojuego());
            vid.setTipoFormatoVideojuego(videojuego.getTipoFormatoVideojuego());
            vid.setAnioLanzamiento(videojuego.getAnioLanzamiento());
            vid.setClasificacion(videojuego.getClasificacion());
            vid.setUnidades(videojuego.getUnidades());
            vid.setImagenVideojuego(videojuego.getImagenVideojuego());

            getListaVideojuegos().add(vid);
        }
        return vid;
    }

    public boolean devolverVideojuego(int factura, String documento, int unidades, String videojuego) throws CompraNoExisteException, JugadorNoExisteException, FechaPasoException, VideojuegoNoExisteException, CompraNoCoincideException, CantidadExcedeException {

        Compra com;
        com = obtenerCompra(factura);
        Videojuego vid = obtenerVideojuego2(videojuego);
        Jugador jug = obtenerJugador(documento);
        if(jug == null){
            throw new JugadorNoExisteException();
        }else if(vid == null){
            throw new VideojuegoNoExisteException();
        }else if(com != null){
            if(com.getFechaCompraFinal().isBefore(LocalDate.now())) {
                throw new FechaPasoException();
            }else if(com.getFactura() != factura || !com.getDocumentoJugador().equals(documento) ||
                    !com.getCodigo().equals(videojuego)) {
                throw new CompraNoCoincideException();
            }else if(com.getUnidades() < unidades) {
                throw new CantidadExcedeException();
            }else{
                com.setUnidades(com.getUnidades()-unidades);
                if(com.getUnidades() == 0){
                    getListaCompras().remove(com);
                    jug.setVideojuegosComprados(jug.getVideojuegosComprados()-1);
                }
                Videojuego vd = obtenerVideojuego2(com.getCodigo());
                incrementarInventario(videojuego, vd.getUnidades(), unidades);
            }
        }else{
            throw new CompraNoExisteException();
        }
        return true;
    }

    public boolean eliminarJugador(String documento) throws JugadorNoExisteException {

        Jugador jugador;
        jugador = obtenerJugador(documento);

        if (jugador != null) {
            getListaJugadores().remove(jugador);
        } else {
            throw new JugadorNoExisteException();
        }
        return true;
    }

    public void eliminarCarrito(Carrito carrito) {
        getListaCarrito().remove(carrito);
    }

    public void eliminarFavorito(Favorito favorito) {
        getListaFavoritos().remove(favorito);
    }

    public boolean actualizarVideojuego(Videojuego videojuego)
            throws VideojuegoNoExisteException {

        Videojuego vid;
        boolean videojuegoExiste = false;

        vid = obtenerVideojuego(videojuego.getCodigo());

        if (vid != null) {

            for (int i = 0; i < getListaVideojuegos().size(); i++) {
                if (getListaVideojuegos().get(i).getCodigo().equals(videojuego.getCodigo())) {

                    vid.setNombreVideojuego(videojuego.getNombreVideojuego());
                    vid.setPrecio(videojuego.getPrecio());
                    vid.setTipoFormatoVideojuego(videojuego.getTipoFormatoVideojuego());
                    vid.setTipoGeneroVideojuego(videojuego.getTipoGeneroVideojuego());
                    vid.setAnioLanzamiento(videojuego.getAnioLanzamiento());
                    vid.setClasificacion(videojuego.getClasificacion());
                    vid.setUnidades(videojuego.getUnidades());
                    vid.setAnioLanzamiento(videojuego.getAnioLanzamiento());

                    getListaVideojuegos().set(i, vid);
                    videojuegoExiste = true;
                }
            }
        } else {
            throw new VideojuegoNoExisteException();
        }
        return videojuegoExiste;
    }

    public void actualizarAdministrador(Administrador administrador) throws ClaveNoSeguraException{

        Administrador admin;

        admin = obtenerAdministrador(administrador.getDocumento());

        if (!validarClave(administrador.getClave())) {
            throw new ClaveNoSeguraException();
        }

        else if (admin != null) {

            for (int i = 0; i < getListaAdministradores().size(); i++) {
                if (getListaAdministradores().get(i).getDocumento().equals(administrador.getDocumento())) {

                    admin.setNombrePersona(administrador.getNombrePersona());
                    admin.setApellido(administrador.getApellido());
                    admin.setTelefono(administrador.getTelefono());
                    admin.setCorreo(administrador.getCorreo());
                    admin.setClave(administrador.getClave());
                    admin.setClave(administrador.getConfirmacionClave());
                    admin.setTipoEstadoCivil(administrador.getTipoEstadoCivil());
                    admin.setImagen(administrador.getImagen());

                    getListaAdministradores().set(i, admin);
                }
            }
        }
    }

    public boolean eliminarAdministrador(String documento) throws AdministradorNoExisteException {

        Administrador administrador;

        administrador = obtenerAdministrador(documento);

        if (administrador != null) {
            getListaAdministradores().remove(administrador);
        } else {
            throw new AdministradorNoExisteException();
        }
        return true;
    }

    public void actualizarJugador(Jugador jugador) throws ClaveNoSeguraException{

        Jugador jug;

        jug = obtenerJugador(jugador.getDocumento());

        if (!validarClave(jugador.getClave())) {
            throw new ClaveNoSeguraException();
        }

        else if (jug != null) {

            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getDocumento().equals(jugador.getDocumento())) {

                    jug.setNombrePersona(jugador.getNombrePersona());
                    jug.setApellido(jugador.getApellido());
                    jug.setTelefono(jugador.getTelefono());
                    jug.setCorreo(jugador.getCorreo());
                    jug.setClave(jugador.getClave());
                    jug.setConfirmacionClave(jugador.getConfirmacionClave());
                    jug.setTipoEstadoCivil(jugador.getTipoEstadoCivil());
                    jug.setImagen(jugador.getImagen());

                    getListaJugadores().set(i, jug);
                }
            }
        }
    }

    public boolean cambiarClaveJugador(String correo, String clave, String confirmacion)
            throws JugadorNoExisteException, ContraseniasNoCoincidenException, ClaveNoSeguraException {

        boolean claveCambiadaConExito = false;

        Jugador jugador;

        jugador = obtenerJugador2(correo);

        if (claveIncorrecta(clave, confirmacion)) {

            throw new ContraseniasNoCoincidenException();

        } else if (!validarClave(clave)) {
            throw new ClaveNoSeguraException();
        } else if (jugador != null) {

            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getCorreo().equals(correo)) {

                    jugador.setClave(clave);
                    jugador.setConfirmacionClave(confirmacion);

                    getListaJugadores().set(i, jugador);

                    claveCambiadaConExito = true;
                }
            }
        } else {
            throw new JugadorNoExisteException();
        }
        return claveCambiadaConExito;
    }

    public boolean cambiarClaveAdministrador(String correo, String clave, String confirmacion)
            throws AdministradorNoExisteException, ContraseniasNoCoincidenException, ClaveNoSeguraException {

        boolean claveCambiadaConExito = false;

        Administrador administrador;

        administrador = obtenerAdministrador2(correo);

        if (claveIncorrecta(clave, confirmacion)) {

            throw new ContraseniasNoCoincidenException();

        } else if (!validarClave(clave)) {
            throw new ClaveNoSeguraException();
        } else if (administrador != null) {

            for (int i = 0; i < getListaAdministradores().size(); i++) {
                if (getListaAdministradores().get(i).getCorreo().equals(correo)) {

                    administrador.setClave(clave);
                    administrador.setConfirmacionClave(confirmacion);

                    getListaAdministradores().set(i, administrador);

                    claveCambiadaConExito = true;
                }
            }
        } else {
            throw new AdministradorNoExisteException();
        }
        return claveCambiadaConExito;
    }

    public void establecerIntentos(String correo) {

        Jugador jugador;
        jugador = obtenerJugador2(correo);

        if (jugador != null) {
            int var = jugador.getIntentos();
            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getCorreo().equals(correo)) {
                    jugador.setIntentos(var+=1);
                }
            }
        }
    }

    public void bloquearCuenta(String correo) throws CuentaBloqueadaException {
        Jugador jugador;
        jugador = obtenerJugador2(correo);

        if (jugador != null) {
            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getCorreo().equals(correo)) {
                    if(jugador.getIntentos() == 3){
                        jugador.setTipoRestriccion(TipoRestriccion.DENEGADO);
                        getListaJugadores().set(i, jugador);
                        throw new CuentaBloqueadaException();
                    }
                }
            }
        }
    }

    public void desbloquearCuenta(Jugador jugador) throws ClaveNoSeguraException{

        Jugador jug;

        jug = obtenerJugador(jugador.getDocumento());

        if (jug != null) {

            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getDocumento().equals(jugador.getDocumento())) {

                    jug.setTipoRestriccion(TipoRestriccion.CONFIRMADO);
                    getListaJugadores().set(i, jug);
                }
            }
        }
    }

    public int generarNumAleatorio() {
        return (int) (Math.random() * 100 + 1);
    }

    public static int generarNumAleatorio2() {
        Random rand = new Random();
        return rand.nextInt(900000) + 100000;
    }

    public void disminuirInventario(String videojuego, int inventarioActual, int compradas) {

        Videojuego vid;

        vid = obtenerVideojuego(videojuego);

        if (vid != null) {

            for (int i = 0; i < getListaVideojuegos().size(); i++) {
                if (getListaVideojuegos().get(i).getNombreVideojuego().equals(videojuego)) {

                    vid.setUnidades(inventarioActual - compradas);

                    getListaVideojuegos().set(i, vid);
                }
            }
        }
    }

    public void incrementarInventario(String videojuego, int inventarioActual, int compradas) {

        Videojuego vid;

        vid = obtenerVideojuego2(videojuego);

        if (vid != null) {

            for (int i = 0; i < getListaVideojuegos().size(); i++) {
                if (getListaVideojuegos().get(i).getCodigo().equals(videojuego)) {

                    vid.setUnidades(inventarioActual + compradas);

                    getListaVideojuegos().set(i, vid);
                }
            }
        }
    }

    public String chat(int opcion) throws InterruptedException {

        String respuesta;

        switch (opcion) {
            case 1
                ->
                 {
                    Thread.sleep(1500);
                    respuesta = "Armenia Games es una empresa dedicada al alquile de videojuegos en" +
                            "multiples formatos, contando con una gran variedad y gama junto con exclusivos" +
                            "que solo podrás encontrar aquí, ¡BIENVENIDO Y ANIMATE A COMPRAR!";
                }
            case 2
                ->
                 {
                    Thread.sleep(1500);
                    respuesta = "Nuestra dirección es Parque residencial San José torre 13 apartamento 402" +
                            " Armenia, Quindio";
                }
            case 3
                ->
                 {
                    Thread.sleep(1500);
                    respuesta = """
                            armeniagamess@gmail.com\s

                            armeniaagames@hotmail.com""";
                }
            case 4
                ->
                 {
                    Thread.sleep(1500);
                    respuesta = "3157566407";
                }
            case 5
                ->
                 {
                    Thread.sleep(1500);
                    respuesta = """
                            Eduardo Cortes Pineda\s
                            Contacto: eduardcpineda@gmail.com
                            eduardcpineda@hotmail.com""";
                }

            default
                ->
                 {
                    Thread.sleep(1500);
                    respuesta = "Respuesta incorrecta";
                }
        }
        return respuesta;
    }

    public void actualizarHistorial(String jugador, int comprados) {

        Jugador jug;

        jug = obtenerJugador(jugador);

        if (jug != null) {

            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getDocumento().equals(jugador)) {
                    jug.setVideojuegosComprados(comprados);
                    getListaJugadores().set(i, jug);
                }
            }
        }
    }

    public void actualizarHistorial2(String jugador, int jugados) throws JugadorNoExisteException {

        Jugador jug;

        jug = obtenerJugador(jugador);
        if (jug != null) {
            for (int i = 0; i < getListaJugadores().size(); i++) {
                if (getListaJugadores().get(i).getDocumento().equals(jugador)) {
                    jug.setVideojuegosComprados(jugados);
                    getListaJugadores().set(i, jug);
                }
            }
        }else{
            throw new JugadorNoExisteException();
        }
    }

    public int getCompras() {
        return compras;
    }

    public void setCompras(int compras) {
        this.compras = compras;
    }

    public ArrayList<Videojuego> getListaVideojuegos() {
        return listaVideojuegos;
    }

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return listaAdministradores;
    }

    public ArrayList<Compra> getListaCompras() {
        return listaCompras;
    }

    public ArrayList<Favorito> getListaFavoritos() {
        return listaFavorito;
    }

    public ArrayList<Carrito> getListaCarrito() {
        return listaCarrito;
    }

}
