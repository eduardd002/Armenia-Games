package co.edu.uniquindio.armeniagames.factory;
import co.edu.uniquindio.armeniagames.constant.MensajesExcepcionConstant;
import co.edu.uniquindio.armeniagames.constant.MensajesInformacionConstant;
import co.edu.uniquindio.armeniagames.exception.*;
import co.edu.uniquindio.armeniagames.model.*;
import co.edu.uniquindio.armeniagames.persistence.Persistencia;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Optional;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class ModelFactory {

    public MensajesInformacionConstant mensajesInformacionConstant = new MensajesInformacionConstant();
    public MensajesExcepcionConstant mensajesExcepcionConstant = new MensajesExcepcionConstant();
    public Persistencia persistencia = new Persistencia();

    public Tienda tienda;
    public Usuario usuarioAuxiliar;
    public Videojuego videojuegoAuxiliar;


    public static class SingletonHolder {
        private final static ModelFactory eINSTANCE = new ModelFactory();
    }

    public static ModelFactory getInstance() {
        return SingletonHolder.eINSTANCE;
    }

    public ModelFactory() {
        cargarDatosDesdeArchivos();
    }

    public void cargarDatosDesdeArchivos() {

        this.tienda = new Tienda();

        try {
            ArrayList<Administrador> listaAdministradores;
            ArrayList<Jugador> listaJugadores;
            ArrayList<Videojuego> listaVideojuegos;
            ArrayList<Compra> listaCompras;
            ArrayList<Carrito> listaCarrito;
            ArrayList<Favorito> listaFavorito;

            listaAdministradores = persistencia.cargarAdministrador();
            listaJugadores = persistencia.cargarJugador();
            listaVideojuegos = persistencia.cargarVideojuego();
            listaCompras = persistencia.cargarCompra();
            listaCarrito = persistencia.cargarCarrito();
            listaFavorito = persistencia.cargarFavorito();

            getTienda().getListaAdministradores().addAll(listaAdministradores);
            getTienda().getListaJugadores().addAll(listaJugadores);
            getTienda().getListaVideojuegos().addAll(listaVideojuegos);
            getTienda().getListaCompras().addAll(listaCompras);
            getTienda().getListaCarrito().addAll(listaCarrito);
            getTienda().getListaFavoritos().addAll(listaFavorito);

        } catch (IOException e) {
            persistencia.guardaRegistroLog("Datos no cargados", 3, mensajesExcepcionConstant.ERROR_CARGAR_DATOS);
        }
    }

    public Usuario getUsuarioAuxiliar() {
        return usuarioAuxiliar;
    }

    public Usuario login(Usuario usuario) {

        Usuario usu = null;

        try {
            boolean esCorrecto = getTienda().iniciarSesion(usuario);
            if (esCorrecto) {
                usu = getTienda().login(usuario);
                persistencia.guardaRegistroLog("Ingreso Autorizado", 1, mensajesInformacionConstant.INFORMACION_INGRESO_USUARIO);
                mostrarMensajeLogin("Notificacion Ingreso", "Ingreso Autorizado",
                        mensajesInformacionConstant.INFORMACION_BIENVENIDA, Alert.AlertType.INFORMATION,
                        usu.getNombrePersona());
                usuarioAuxiliar = usu;
            }

        } catch (UsuarioNoExisteException | JugadorNoExisteException | AdministradorNoExisteException e) {
            persistencia.guardaRegistroLog("Ingresado No Autorizado", 3, mensajesExcepcionConstant.ERROR_INGRESO_USUARIO);
            mostrarMensaje("Notificacion Ingreso", "Ingreso No Autorizado", mensajesExcepcionConstant.ERROR_INGRESO_USUARIO,
                    Alert.AlertType.ERROR);
        } catch (CuentaBloqueadaException e) {
            persistencia.guardaRegistroLog("Ingresado No Autorizado", 3, mensajesExcepcionConstant.ERROR_CUENTA_BLOQUEADA);
            mostrarMensaje("Notificacion Ingreso", "Ingreso No Autorizado", mensajesExcepcionConstant.ERROR_CUENTA_BLOQUEADA,
                    Alert.AlertType.ERROR);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Ingresado No Autorizado", 3, mensajesExcepcionConstant.ERROR_GENERAL);
            mostrarMensaje("Notificacion Ingreso", "Ingreso No Autorizado", mensajesExcepcionConstant.ERROR_GENERAL,
                    Alert.AlertType.ERROR);
        }
        return usu;
    }

    public Administrador guardarAdministrador(Administrador ad) {

        Administrador administrador = null;

        try {
            administrador = getTienda().guardarAdministrador(ad);
            persistencia.guardarAdministrador(getListaAdministradores());
            mostrarMensaje("Notificación Guardado", "Administrador Guardado", mensajesInformacionConstant.INFORMACION_ADMINISTRADOR_GUARDADO,
                    Alert.AlertType.INFORMATION);
            persistencia.guardaRegistroLog("Administrador Guardado", 1, mensajesInformacionConstant.INFORMACION_ADMINISTRADOR_GUARDADO);
            usuarioAuxiliar = administrador;
        } catch (JugadorExisteException e) {
            persistencia.guardaRegistroLog("Administrador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_ADMINISTRADOR_NO_GUARDADO + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Administrador No Guardado",
                    mensajesExcepcionConstant.ERROR_ADMINISTRADOR_YA_EXISTE, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (ClaveNoSeguraException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (ContraseniasNoCoincidenException e) {
            persistencia.guardaRegistroLog("Administrador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Administrador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Administrador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Administrador No Guardado",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return administrador;
    }

    public String hora(){

        LocalTime horaActual = LocalTime.now();
        String tiempo;

        if (horaActual.isBefore(LocalTime.of(12, 0))) {
            tiempo = "\n" + "Buenos dias " + "\n" + "\n" ;
        } else if (horaActual.isBefore(LocalTime.of(18, 0))) {
            tiempo = "\n" + "Buenas tardes " + "\n" + "\n" ;
        } else {
            tiempo = "\n" + "Buenas noches " + "\n" + "\n" ;
        }
        return tiempo;
    }


    public void correo(String titulo, String cuerpo, String correo, String imagenRutaLocal) {
        String correoEnvia = "armeniagames48@gmail.com";
        String contrasena = "evawjczanrebktjd";

        Properties objetoPEC = new Properties();

        objetoPEC.put("mail.smtp.host", "smtp.gmail.com");
        objetoPEC.setProperty("mail.smtp.starttls.enable", "true");
        objetoPEC.put("mail.smtp.port", "587");
        objetoPEC.setProperty("mail.smtp.port", "587");
        objetoPEC.put("mail.smtp.user", correoEnvia);
        objetoPEC.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(objetoPEC);
        MimeMessage mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mail.setSubject(titulo);

            String mensajeHTML = "<html><body>";
            mensajeHTML += "<h3>" + hora() + "</h3>";
            mensajeHTML += "<h3>" + cuerpo + "</h3>";

            String cid = "imagen1";
            File imageFile = new File(imagenRutaLocal);

            mensajeHTML += "<img src='cid:" + cid + "'/>";
            mensajeHTML += "</body></html>";

            Multipart multipart = new MimeMultipart();

            MimeBodyPart htmlPart = new MimeBodyPart();
            htmlPart.setContent(mensajeHTML, "text/html");

            multipart.addBodyPart(htmlPart);

            MimeBodyPart imagePart = new MimeBodyPart();
            imagePart.attachFile(imageFile);
            imagePart.setContentID("<" + cid + ">");

            multipart.addBodyPart(imagePart);

            mail.setContent(multipart);

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            persistencia.guardaRegistroLog("Correo Enviado", 1, mensajesInformacionConstant.INFORMACION_CORREO_ENVIADO);

        } catch (Exception e) {
            persistencia.guardaRegistroLog("Correo No Enviado", 1, mensajesExcepcionConstant.ERROR_CORREO_NO_ENVIADO);
            e.printStackTrace();
        }
    }

    public void correo2(String titulo, String cuerpo, String correo, int codigo){

        String correoEnvia = "armeniagames48@gmail.com";
        String contrasena = "evawjczanrebktjd";
        String mensaje = titulo;

        Properties objetoPEC = new Properties();

        objetoPEC.put("mail.smtp.host", "smtp.gmail.com");
        objetoPEC.setProperty("mail.smtp.starttls.enable", "true");
        objetoPEC.put("mail.smtp.port", "587");
        objetoPEC.setProperty("mail.smtp.port", "587");
        objetoPEC.put("mail.smtp.user", correoEnvia);
        objetoPEC.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(objetoPEC);
        MimeMessage mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(correo));
            mail.setSubject(mensaje);
            mail.setText(hora() + cuerpo + codigo);

            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contrasena);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            persistencia.guardaRegistroLog("Correo Enviado", 1, mensajesInformacionConstant.INFORMACION_CORREO_ENVIADO);
        }catch (Exception e){
            e.printStackTrace();
            persistencia.guardaRegistroLog("Correo No Enviado", 1, mensajesExcepcionConstant.ERROR_CORREO_NO_ENVIADO);
        }
    }

    public Jugador guardarJugador(Jugador jug) {

        Jugador jugador = null;

        try {
            jugador = getTienda().guardarJugador(jug);
            persistencia.guardarJugador(getListaJugadores());
            mostrarMensaje("Notificación Guardado", "Jugador Guardado", mensajesInformacionConstant.INFORMACION_JUGADOR_GUARDADO,
                    Alert.AlertType.INFORMATION);
            persistencia.guardaRegistroLog("Jugador Guardado", 1, mensajesInformacionConstant.INFORMACION_JUGADOR_GUARDADO);
            usuarioAuxiliar = jugador;
        } catch (JugadorExisteException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_JUGADOR_NO_GUARDADO + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_JUGADOR_YA_EXISTE, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (ContraseniasNoCoincidenException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (ClaveNoSeguraException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return jugador;
    }

    public boolean devolverCompra(int factura, String documento, int unidades, String videojuego){
        boolean videojuegoDevuleto = false;

        try {
            videojuegoDevuleto = getTienda().devolverVideojuego(factura, documento, unidades, videojuego);
            persistencia.guardarVideojuego(getListaVideojuegos());
            persistencia.guardarCompra(getListaCompras2());
            persistencia.guardarJugador(getListaJugadores());
            mostrarMensaje("Notificación Devolucion", "Videojuego Devuelto", mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_DEVUELTO,
                    Alert.AlertType.INFORMATION);
            persistencia.guardaRegistroLog("Videojuego Devuelto", 1, mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_DEVUELTO);
        }catch (CompraNoExisteException e) {
            mostrarMensaje("Notificación Devolucion", "Videojuego No Devuelto", mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_DEVUELTO,
                    Alert.AlertType.ERROR);
            persistencia.guardaRegistroLog("Videojuego No Devuelto", 1, mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_DEVUELTO);
        }catch (JugadorNoExisteException e) {
            mostrarMensaje("Notificación Devolucion", "Videojuego No Devuelto", mensajesExcepcionConstant.ERROR_JUGADOR_NO_EXISTE,
                    Alert.AlertType.ERROR);
            persistencia.guardaRegistroLog("Videojuego No Devuelto", 1, mensajesExcepcionConstant.ERROR_JUGADOR_NO_EXISTE);
        }catch (FechaPasoException e) {
            mostrarMensaje("Notificación Devolucion", "Videojuego No Devuelto", mensajesExcepcionConstant.ERROR_FECHA_PASO,
                    Alert.AlertType.ERROR);
            persistencia.guardaRegistroLog("Videojuego No Devuelto", 1, mensajesExcepcionConstant.ERROR_FECHA_PASO);
        } catch (IOException e){
            mostrarMensaje("Notificación No Devolucion", "Videojuego No Devuelto", mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_DEVUELTO,
                    Alert.AlertType.ERROR);
            persistencia.guardaRegistroLog("Videojuego No Devuelto", 1, mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_DEVUELTO);
        }
        return videojuegoDevuleto;
    }

    public boolean eliminarVideojuego(String codigo) {

        boolean videojuegoExiste = false;

        try {
            videojuegoExiste = getTienda().eliminarVideojuego(codigo);
            persistencia.guardarVideojuego(getListaVideojuegos());
            persistencia.guardaRegistroLog("Videojuego Eliminado", 1, mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_ELIMINADO);
            mostrarMensaje("Notificacion Eliminacion", "Videojuego Eliminado",
                    mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_ELIMINADO, Alert.AlertType.INFORMATION);
        } catch (VideojuegoNoExisteException e) {
            persistencia.guardaRegistroLog("Videojuego No Eliminado", 3,
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_EXISTE + e.getMessage());
            mostrarMensaje("Notificacion Eliminacion", "Videojuego No Eliminado",
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_EXISTE, Alert.AlertType.ERROR);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Videojuego No Eliminado", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificacion Eliminiacion", "Videojuego No Eliminado",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return videojuegoExiste;
    }

    public Videojuego guardarVideojuego(Videojuego vid) {

        Videojuego videojuego = null;

        try {
            videojuego = getTienda().guardarVideojuego(vid);
            persistencia.guardarVideojuego(getListaVideojuegos());
            persistencia.guardaRegistroLog("Videojuego Guardado", 1, mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_GUARDADO);
            mostrarMensaje("Notificacion Guardado", "Videojuego Guardado", mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_GUARDADO,
                    Alert.AlertType.INFORMATION);
        } catch (VideojuegoExisteException e) {
            persistencia.guardaRegistroLog("Videojuego No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_YA_EXISTE + e.getMessage());
            mostrarMensaje("Notificacion Guardado", "Videojuego No Guardado",
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_YA_EXISTE, Alert.AlertType.ERROR);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Videojuego No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificacion Guardado", "Videojuego No Guardado",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return videojuego;
    }

    public boolean actualizarVideojuego(Videojuego videojuego) {

        boolean videojuegoExiste = false;

        try {
            videojuegoExiste = getTienda().actualizarVideojuego(videojuego);
            persistencia.guardarVideojuego(getListaVideojuegos());
            persistencia.guardaRegistroLog("Videojuego Actualizado", 1, mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_ACTUALIZADO);
            mostrarMensaje("Notificacion Actualizacion", "Videojuego Actualizado",
                    mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_ACTUALIZADO, Alert.AlertType.INFORMATION);
        } catch (VideojuegoNoExisteException e) {
            persistencia.guardaRegistroLog("Videojuego No Actualizado", 3,
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_ACTUALIZADO + e.getMessage());
            mostrarMensaje("Notificacion Actualizacion", "Videojuego No Actualizado",
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_ACTUALIZADO, Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Videojuego No Actualizado", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificacion Actualizacion", "Videojuego No Actualizado",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return videojuegoExiste;
    }

    public Compra guardarCompra(Compra comp) {

        Compra prestamo = null;

        try {
            prestamo = getTienda().guardarCompra(comp);
            persistencia.guardarCompra(getListaCompras(comp.getJugador()));
            persistencia.guardaRegistroLog("Compra Guardada", 1, mensajesInformacionConstant.INFORMACION_PRESTAMO_GUARDADO);
            mostrarMensaje("Notificacion Guardado", "Compra Guardada", mensajesInformacionConstant.INFORMACION_PRESTAMO_GUARDADO,
                    Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Compra No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificacion Guardado", "Compra No Guardado",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return prestamo;
    }

    public Carrito guardarCarrito(Carrito car) {

        Carrito carrit = null;

        try {
            carrit = getTienda().guardarCarrito(car);
            persistencia.guardarCarrito(getListaCarrito(car.getJugadorCarrito()));
            persistencia.guardaRegistroLog("Videojuego Guardado En Carrito", 1, mensajesInformacionConstant.INFORMACION_CARRITO_GUARDADO);
            mostrarMensaje("Notificacion Guardado", "Videojuego Guardado En Carrito", mensajesInformacionConstant.INFORMACION_CARRITO_GUARDADO,
                    Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Videojuego No Guardado En Carrito", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificacion Guardado", "Videojuego No Guardado En Carrito",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return carrit;
    }

    public Favorito guardarFavorito(Favorito fav) {

        Favorito favorito = null;

        try {
            favorito = getTienda().guardarFavorito(fav);
            persistencia.guardarFavorito(getListaFavorito(fav.getJugadorFavorito()));
            persistencia.guardaRegistroLog("Videojuego Guardado En Favorito", 1, mensajesInformacionConstant.INFORMACION_FAVORITO_GUARDADO);
            mostrarMensaje("Notificacion Guardado", "Videojuego Guardado En Favorito", mensajesInformacionConstant.INFORMACION_FAVORITO_GUARDADO,
                    Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Videojuego No Guardado En Favorito", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificacion Guardado", "Videojuego No Guardado En Carrito",
                    mensajesExcepcionConstant.ERROR_GENERAL, Alert.AlertType.ERROR);
        }
        return favorito;
    }

    public void actualizarAdministrador(Administrador administrador) {

        try {
            getTienda().actualizarAdministrador(administrador);
            persistencia.guardarAdministrador(getListaAdministradores());
            persistencia.guardaRegistroLog("Administrador Actualizado", 1, mensajesInformacionConstant.INFORMACION_ADMINISTRADOR_ACTUALIZADO);
            mostrarMensaje("Notificacion Actualizacion", "Administrador Actualizado",
                    mensajesInformacionConstant.INFORMACION_ADMINISTRADOR_ACTUALIZADO, Alert.AlertType.INFORMATION);
        } catch (ClaveNoSeguraException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Vendedor No Actualizado", 3,
                    mensajesExcepcionConstant.ERROR_ADMINISTRADOR_NO_ACTUALIZADO + e.getMessage());
            mostrarMensaje("Notificacion Actualizacion", "Vendedor No Actualizado",
                    mensajesExcepcionConstant.ERROR_ADMINISTRADOR_NO_ACTUALIZADO, Alert.AlertType.ERROR);
        }
    }

    public boolean eliminarAdministrador(String documento) {

        boolean administradorExiste = false;

        try {
            administradorExiste = getTienda().eliminarAdministrador(documento);
            persistencia.guardarAdministrador(getListaAdministradores());
            persistencia.guardaRegistroLog("Administrador Eliminado", 1, mensajesInformacionConstant.INFORMACION_ADMINISTRADOR_ELIMINADO);
            mostrarMensaje("Notificacion Eliminacion", "Administrador Eliminado",
                    mensajesInformacionConstant.INFORMACION_ADMINISTRADOR_ELIMINADO, Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Administrador No Eliminado", 3, mensajesExcepcionConstant.ERROR_ADMINISTRADOR_NO_ELIMINADO + e.getMessage());
            mostrarMensaje("Notificacion Eliminacion", "Administrador No Eliminado", mensajesExcepcionConstant.ERROR_ADMINISTRADOR_NO_ELIMINADO, Alert.AlertType.ERROR);
        }
        return administradorExiste;
    }

    public void actualizarJugador(Jugador jugador) {

        try {
            getTienda().actualizarJugador(jugador);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Jugador Actualizado", 1, mensajesInformacionConstant.INFORMACION_JUGADOR_ACTUALIZADO);
            mostrarMensaje("Notificacion Actualizacion", "Jugador Actualizado",
                    mensajesInformacionConstant.INFORMACION_JUGADOR_ACTUALIZADO, Alert.AlertType.INFORMATION);
        } catch (ClaveNoSeguraException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Jugador No Actualizado", 3,
                    mensajesExcepcionConstant.ERROR_JUGADOR_NO_ACTUALIZADO + e.getMessage());
            mostrarMensaje("Notificacion Actualizacion", "Jugador No Actualizado",
                    mensajesExcepcionConstant.ERROR_JUGADOR_NO_ACTUALIZADO, Alert.AlertType.ERROR);
        }
    }

    public boolean eliminarJugador(String documento) {

        boolean jugadorExiste = false;

        try {
            jugadorExiste = getTienda().eliminarJugador(documento);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Jugador Eliminado", 1, mensajesInformacionConstant.INFORMACION_JUGADOR_ELIMINADO);
            mostrarMensaje("Notificacion Eliminacion", "Jugador Eliminado",
                    mensajesInformacionConstant.INFORMACION_JUGADOR_ELIMINADO, Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Jugador No Eliminado", 3, mensajesExcepcionConstant.ERROR_JUGADOR_NO_ELIMINADO + e.getMessage());
            mostrarMensaje("Notificacion Eliminacion", "Jugador No Eliminado", mensajesExcepcionConstant.ERROR_JUGADOR_NO_ELIMINADO, Alert.AlertType.ERROR);
        }
        return jugadorExiste;
    }

    public boolean cambiarClaveJugador(String documento, String clave, String confirmacion) {

        boolean cambio = false;

        try {
            cambio = getTienda().cambiarClaveJugador(documento, clave, confirmacion);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Clave Actualizada", 1, mensajesInformacionConstant.INFORMACION_CAMBIAR_CLAVE);
            mostrarMensaje("Notificacion Actualizacion", "Clave Actualizada",
                    mensajesInformacionConstant.INFORMACION_CAMBIAR_CLAVE, Alert.AlertType.INFORMATION);
        } catch (JugadorNoExisteException e) {
            persistencia.guardaRegistroLog("Clave No Actualizada", 3,
                    mensajesExcepcionConstant.ERROR_CAMBIAR_CLAVE + e.getMessage());
            mostrarMensaje("Notificacion Actualizacion", "Clave No Actualizada",
                    mensajesExcepcionConstant.ERROR_CAMBIAR_CLAVE, Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (ClaveNoSeguraException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (ContraseniasNoCoincidenException e) {
            persistencia.guardaRegistroLog("Clave No Actualizada", 3,
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Clave No Actualizada",
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN, Alert.AlertType.ERROR);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Clave No Actualizada", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificación Actualizacion", "Clave No Actualizada", mensajesExcepcionConstant.ERROR_GENERAL,
                    Alert.AlertType.ERROR);
        }
        return cambio;
    }

    public Compra obtenerCompra(int factura){
        Compra c = null;

        for (Compra compra : getTienda().getListaCompras()) {
            if(compra.getFactura() == factura) {
                c = compra;
            }
        }
        return c;
    }

    public boolean cambiarClaveAdministrador(String documento, String clave, String confirmacion) {

        boolean cambio = false;

        try {
            cambio = getTienda().cambiarClaveAdministrador(documento, clave, confirmacion);
            persistencia.guardarAdministrador(getListaAdministradores());
            persistencia.guardaRegistroLog("Clave Actualizada", 1, mensajesInformacionConstant.INFORMACION_CAMBIAR_CLAVE);
            mostrarMensaje("Notificacion Actualizacion", "Clave Actualizada",
                    mensajesInformacionConstant.INFORMACION_CAMBIAR_CLAVE, Alert.AlertType.INFORMATION);
        } catch (AdministradorNoExisteException e) {
            persistencia.guardaRegistroLog("Clave No Actualizada", 3,
                    mensajesExcepcionConstant.ERROR_CAMBIAR_CLAVE + e.getMessage());
            mostrarMensaje("Notificacion Actualizacion", "Clave No Actualizada",
                    mensajesExcepcionConstant.ERROR_CAMBIAR_CLAVE, Alert.AlertType.ERROR);
            e.printStackTrace();
        } catch (ClaveNoSeguraException e) {
            persistencia.guardaRegistroLog("Jugador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Jugador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVE_NO_SEGURA, Alert.AlertType.ERROR);
            usuarioAuxiliar = null;
        } catch (ContraseniasNoCoincidenException e) {
            persistencia.guardaRegistroLog("Comprador No Guardado", 3,
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN + e.getMessage());
            mostrarMensaje("Notificación Guardado", "Comprador No Guardado",
                    mensajesExcepcionConstant.ERROR_CLAVES_NO_COINCIDEN, Alert.AlertType.ERROR);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Clave No Actualizada", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificación Actualizacion", "Clave No Actualizada", mensajesExcepcionConstant.ERROR_GENERAL,
                    Alert.AlertType.ERROR);
        }
        return cambio;
    }

    public Videojuego obtenerVideojuego(String codigo) {

        Videojuego videojuego = new Videojuego();

        try {
            videojuego = getTienda().mostrarDatosVideojuego(codigo);
            persistencia.guardaRegistroLog("Videojuego Encontrado", 1, mensajesInformacionConstant.INFORMACION_VIDEOJUEGO_ACTUALIZADO);
        } catch (VideojuegoNoExisteException e) {
            persistencia.guardaRegistroLog("Videojuego No Encontrado", 3,
                    mensajesExcepcionConstant.ERROR_VIDEOJUEGO_NO_EXISTE + e.getMessage());
            mostrarMensaje("Notificacion Busqueda", "Videojuego No Encontrado", "f",
                    Alert.AlertType.ERROR);
        }
        return videojuego;
    }

    public void establecerIntentos(String correo) {

        try {
            getTienda().establecerIntentos(correo);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Intento Actualizado", 1, mensajesInformacionConstant.INFORMACION_INTENTO_AUMENTADO);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Intento No Actualizado", 3,
                    mensajesExcepcionConstant.ERROR_INTENTO_NO_ACTUALIZADO + e.getMessage());
        }
    }

    public void bloquearCuenta(String correo){

        try {
            getTienda().bloquearCuenta(correo);
            persistencia.guardarJugador(getListaJugadores());
        } catch (CuentaBloqueadaException e) {
            try {
                persistencia.guardarJugador(getListaJugadores());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            correo(mensajesInformacionConstant.INFORMACION_BLOQUEO_CUENTA, "Su cuenta ha sido bloqueada",
                    correo, "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\resources\\images\\bloqueo.jpg");
            persistencia.guardaRegistroLog("Cuenta Bloqueada", 2, mensajesInformacionConstant.INFORMACION_BLOQUEO_CUENTA);
            mostrarMensaje("Notificacion Bloqueo", "Cuenta Bloqueada",
                    mensajesInformacionConstant.INFORMACION_BLOQUEO_CUENTA, Alert.AlertType.WARNING);
        } catch (IOException e) {
            persistencia.guardaRegistroLog("Cuenta No Bloqueada", 3,
                    mensajesExcepcionConstant.ERROR_GENERAL + e.getMessage());
            mostrarMensaje("Notificación Bloqueo", "Cuenta No Bloqueada", mensajesExcepcionConstant.ERROR_GENERAL,
                    Alert.AlertType.ERROR);
        }
    }

    public Jugador obtenerJugador2(String correo){
        return getTienda().obtenerJugador2(correo);
    }

    public void desbloquearCuenta(Jugador jugador) {

        try {
            getTienda().desbloquearCuenta(jugador);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Cuenta Desbloqueada", 1, mensajesInformacionConstant.INFORMACION_JUGADOR_ACTUALIZADO);
            mostrarMensaje("Notificacion Desbloqueo", "Jugador Desbloqueado",
                    mensajesInformacionConstant.INFORMACION_DESBLOQUEO_CUENTA, Alert.AlertType.INFORMATION);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Cuenta No Desbloqueada", 3,
                    mensajesExcepcionConstant.ERROR_JUGADOR_NO_ACTUALIZADO + e.getMessage());
            mostrarMensaje("Notificacion Desbloqueo", "Jugador No Desbloqueado",
                    mensajesExcepcionConstant.ERROR_DESBLOQUEO_CUENTA, Alert.AlertType.ERROR);
        }
    }

    public void eliminarFavorito(Favorito favorito) {

        try {
            getTienda().eliminarFavorito(favorito);
            persistencia.guardarFavorito(getListaFavorito2());
            persistencia.guardaRegistroLog("Favorito Eliminado", 1, mensajesInformacionConstant.INFORMACION_FAVORITO_ELIMINADO);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Favorito No Eliminado", 3,
                    mensajesExcepcionConstant.ERROR_FAVORITO_NO_ELIMINADO + e.getMessage());
        }
    }

    public void eliminarCarrito(Carrito carrito) {

        try {
            getTienda().eliminarCarrito(carrito);
            persistencia.guardarCarrito(getListaCarrito2());
            persistencia.guardaRegistroLog("Carrito Eliminado", 1, mensajesInformacionConstant.INFORMACION_CARRITO_ELIMINADO);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Carrito No Eliminado", 3,
                    mensajesExcepcionConstant.ERROR_CARRITO_NO_ELIMINADO + e.getMessage());
        }
    }

    public int generarNum(){
        return getTienda().generarNumAleatorio();
    }

    public int generarNum2(){
        return getTienda().generarNumAleatorio2();
    }

    public void mostrarMensaje(String titulo, String header, String contenido, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido);
        alert.showAndWait();
    }

    public void mostrarMensajeLogin(String titulo, String header, String contenido, Alert.AlertType alertType,
                                    String usu) {
        Alert alert = new Alert(alertType);
        alert.setTitle(titulo);
        alert.setHeaderText(header);
        alert.setContentText(contenido + " " + usu);
        alert.showAndWait();
    }

    public boolean mostrarMensajeConfirmacion(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmaci�n");
        alert.setContentText(mensaje);
        Optional<ButtonType> action = alert.showAndWait();

        return action.get() == ButtonType.OK;
    }

    public Jugador traerJugadorEnvioYPago(String documento) {

        Jugador jug = null;

            for (Jugador jugador : getTienda().getListaJugadores()) {
                if (jugador.getDocumento().equals(documento)) {
                    jug = jugador;
                }
            }
        return jug;
    }

    public Jugador traerJugadorEnvioYPago2(String documento) {

        Jugador jug = null;

            getTienda().obtenerJugador(documento);

            for (Jugador jugador : getTienda().getListaJugadores()) {
                if (jugador.getDocumento().equals(documento)) {
                    jug = jugador;
                }
            }

        return jug;
    }

    public void actualizarInventario(String videojuego, int inventarioActual, int compradas) {

        try {
            getTienda().disminuirInventario(videojuego, inventarioActual, compradas);
            persistencia.guardarVideojuego(getListaVideojuegos());
            persistencia.guardaRegistroLog("Inventario Actualizado", 1, mensajesInformacionConstant.INFORMACION_INVENTARIO_ACTUALIZADO);
        } catch (Exception e) {
            persistencia.guardaRegistroLog("Inventario No Actualizado", 3,
                    mensajesExcepcionConstant.ERROR_INVENTARIO_NO_ACTUALIZADO + e.getMessage());
        }
    }

    public String chat(int opcion){

        String mensaje = "";

       try {
           mensaje = getTienda().chat(opcion);
           persistencia.guardaRegistroLog("Respuesta Enviada", 1,
                   mensajesInformacionConstant.INFORMACION_RESPUESTA_ENVIADA);
       } catch (Exception e){
           persistencia.guardaRegistroLog("Respuesta No Enviada", 3,
                   mensajesExcepcionConstant.ERROR_RESPUESTA_NO_ENVIADA + e.getMessage());
       }
        return mensaje;
    }

    public void actualizarHistorial(String jugador, int jugados){

        try {
            getTienda().actualizarHistorial(jugador, jugados);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Historial Aumentado", 1,
                    mensajesInformacionConstant.INFORMACION_HISTORIAL_AUMENTADO);
        } catch (Exception e){
            persistencia.guardaRegistroLog("Historial No Aumentado", 3,
                    mensajesExcepcionConstant.ERROR_HISTORIAL_NO_AUMENTADO + e.getMessage());
        }
    }

    public void actualizarHistorial2(String jugador, int jugados){

        try {
            getTienda().actualizarHistorial2(jugador, jugados);
            persistencia.guardarJugador(getListaJugadores());
            persistencia.guardaRegistroLog("Historial Aumentado", 1,
                    mensajesInformacionConstant.INFORMACION_HISTORIAL_AUMENTADO);
        } catch (Exception e){
            persistencia.guardaRegistroLog("Historial No Aumentado", 3,
                    mensajesExcepcionConstant.ERROR_HISTORIAL_NO_AUMENTADO + e.getMessage());
        }
    }

    public Videojuego getVideojuegoAuxiliar(String codigo) {
        for (Videojuego videojuego : getListaVideojuegos()) {
            if(codigo.equals(videojuego.getNombreVideojuego())){
                return videojuego;
            }
        }
        return videojuegoAuxiliar;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public ArrayList<Administrador> getListaAdministradores() {
        return getTienda().getListaAdministradores();
    }

    public ArrayList<Jugador> getListaJugadores() {
        return getTienda().getListaJugadores();
    }

    public ArrayList<Videojuego> getListaVideojuegos() {
        return getTienda().getListaVideojuegos();
    }

    public ArrayList<Compra> getListaCompras(String jug) {
        return getTienda().getListaCompras();
    }

    public ArrayList<Compra> getListaCompras2() {
        return getTienda().getListaCompras();
    }

    public ArrayList<Favorito> getListaFavorito(String jug) {
        return getTienda().getListaFavoritos();
    }

    public ArrayList<Favorito> getListaFavorito2() {
        return getTienda().getListaFavoritos();
    }

    public ArrayList<Carrito> getListaCarrito(String jug) {
        return getTienda().getListaCarrito();
    }

    public ArrayList<Carrito> getListaCarrito2() {
        return getTienda().getListaCarrito();
    }

}
