package co.edu.uniquindio.armeniagames.persistence;

import co.edu.uniquindio.armeniagames.model.*;
import co.edu.uniquindio.armeniagames.enumm.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class Persistencia {

   public ArchivoUtil archivoUtil = new ArchivoUtil();

   public static final String rutaAdministrador = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\java\\co\\edu\\uniquindio\\armeniagames\\file\\AdministradorFile.txt";
   public static final String rutaJugador = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\java\\co\\edu\\uniquindio\\armeniagames\\file\\JugadorFile.txt";
   public static final String rutaLog = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\java\\co\\edu\\uniquindio\\armeniagames\\file\\LogFile.txt";
   public static final String rutaVideojuego = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\java\\co\\edu\\uniquindio\\armeniagames\\file\\VideojuegoFile.txt";
   public static final String rutaCompra = "C:\\Users\\eduar\\IdeaProjects\\AGE\\src\\main\\java\\co\\edu\\uniquindio\\armeniagames\\file\\CompraFile.txt";

   public void guardaRegistroLog(String mensajeLog, int nivel, String accion) {
      archivoUtil.guardarRegistroLog(mensajeLog, nivel, accion, rutaLog);
   }

   public void guardarAdministrador(ArrayList<Administrador> listaAdministradores) throws IOException {
      StringBuilder contenido = new StringBuilder();

      for (Administrador administrador : listaAdministradores) {
         contenido.append(administrador.getDocumento()).append("--").
                 append(administrador.getNombrePersona()).append("--").
                 append(administrador.getApellido()).append("--").
                 append(administrador.getFechaNacimiento()).append("--").
                 append(administrador.getTipoEstadoCivil()).append("--").
                 append(administrador.getTipoGenero()).append("--").
                 append(administrador.getCorreo()).append("--").
                 append(administrador.getClave()).append("--").
                 append(administrador.getConfirmacionClave()).append("--").
                 append(administrador.getTipoUsuario()).append("--").
                 append(administrador.getTelefono()).append("--").
                 append(administrador.getCarnet()).append("--").
                 append(administrador.getImagen()).append("\n");
      }
      archivoUtil.guardarArchivo(rutaAdministrador, contenido.toString(), false);
   }

   public void guardarJugador(ArrayList<Jugador> listaJugadores) throws IOException {
      StringBuilder contenido = new StringBuilder();

      for (Jugador jugador : listaJugadores) {
         contenido.append(jugador.getDocumento()).append("--").
                 append(jugador.getNombrePersona()).append("--").
                 append(jugador.getApellido()).append("--").
                 append(jugador.getFechaNacimiento()).append("--").
                 append(jugador.getTipoEstadoCivil()).append("--").
                 append(jugador.getTipoGenero()).append("--").
                 append(jugador.getCorreo()).append("--").
                 append(jugador.getClave()).append("--").
                 append(jugador.getConfirmacionClave()).append("--").
                 append(jugador.getTipoUsuario()).append("--").
                 append(jugador.getTelefono()).append("--").
                 append(jugador.getTipoBanco()).append("--").
                 append(jugador.getTipoCuenta()).append("--").
                 append(jugador.getNumeroCuenta()).append("--").
                 append(jugador.getTitular()).append("--").
                 append(jugador.getFechaCaducidad()).append("--").
                 append(jugador.getTipoResidencia()).append("--").
                 append(jugador.getCodigoPostal()).append("--").
                 append(jugador.getDireccion()).append("--").
                 append(jugador.getBarrio()).append("--").
                 append(jugador.getTipoDepartamento()).append("--").
                 append(jugador.getMunicipio()).append("--").
                 append(jugador.getVideojuegosComprados()).append("--").
                 append(jugador.getImagen()).append("\n");
      }
      archivoUtil.guardarArchivo(rutaJugador, contenido.toString(), false);
   }

   public ArrayList<Administrador> cargarAdministrador() throws IOException {

      ArrayList<Administrador> administrador = new ArrayList<>();
      ArrayList<String> contenido = archivoUtil.leerArchivo(rutaAdministrador);

      String linea;

      for (String s : contenido) {

         linea = s;
         Administrador admin = new Administrador();

         admin.setDocumento(linea.split("--")[0]);
         admin.setNombrePersona(linea.split("--")[1]);
         admin.setApellido(linea.split("--")[2]);
         admin.setFechaNacimiento(LocalDate.parse(linea.split("--")[3]));
         admin.setTipoEstadoCivil(TipoEstadoCivil.valueOf(linea.split("--")[4]));
         admin.setTipoGenero(TipoGenero.valueOf(linea.split("--")[5]));
         admin.setCorreo(linea.split("--")[6]);
         admin.setClave(linea.split("--")[7]);
         admin.setConfirmacionClave(linea.split("--")[8]);
         admin.setTipoUsuario(TipoUsuario.valueOf(linea.split("--")[9]));
         admin.setTelefono(linea.split("--")[10]);
         admin.setCarnet(Integer.parseInt(linea.split("--")[11]));
         admin.setImagen(linea.split("--")[12]);

         administrador.add(admin);
      }
      return administrador;
   }

   public void guardarVideojuego(ArrayList<Videojuego> listaVideojuegos) throws IOException {
      StringBuilder contenido = new StringBuilder();

      for (Videojuego videojuego : listaVideojuegos) {
         contenido.append(videojuego.getCodigo()).append("--").
                 append(videojuego.getNombreVideojuego()).append("--").
                 append(videojuego.getPrecio()).append("--").
                 append(videojuego.getTipoGeneroVideojuego()).append("--").
                 append(videojuego.getTipoFormatoVideojuego()).append("--").
                 append(videojuego.getAnioLanzamiento()).append("--").
                 append(videojuego.getClasificacion()).append("--").
                 append(videojuego.getUnidades()).append("\n");
      }
      archivoUtil.guardarArchivo(rutaVideojuego, contenido.toString(), false);
   }

   public ArrayList<Videojuego> cargarVideojuego() throws IOException {

      ArrayList<Videojuego> videojuegos = new ArrayList<>();
      ArrayList<String> contenido = archivoUtil.leerArchivo(rutaVideojuego);

      String linea;

      for (String s : contenido) {

         linea = s;
         Videojuego vid = new Videojuego();

         vid.setCodigo(linea.split("--")[0]);
         vid.setNombreVideojuego(linea.split("--")[1]);
         vid.setPrecio(Integer.parseInt(linea.split("--")[2]));
         vid.setTipoGeneroVideojuego(TipoGeneroVideojuego.valueOf(linea.split("--")[3]));
         vid.setTipoFormatoVideojuego(TipoFormatoVideojuego.valueOf(linea.split("--")[4]));
         vid.setAnioLanzamiento(linea.split("--")[5]);
         vid.setClasificacion(Integer.parseInt(linea.split("--")[6]));
         vid.setUnidades(Integer.parseInt(linea.split("--")[7]));

         videojuegos.add(vid);
      }
      return videojuegos;
   }

   public ArrayList<Jugador> cargarJugador() throws IOException {

      ArrayList<Jugador> jugadores = new ArrayList<>();
      ArrayList<String> contenido = archivoUtil.leerArchivo(rutaJugador);

      String linea;

      for (String s : contenido) {

         linea = s;

         Jugador jug = new Jugador();

         jug.setDocumento(linea.split("--")[0]);
         jug.setNombrePersona(linea.split("--")[1]);
         jug.setApellido(linea.split("--")[2]);
         jug.setFechaNacimiento(LocalDate.parse(linea.split("--")[3]));
         jug.setTipoEstadoCivil(TipoEstadoCivil.valueOf(linea.split("--")[4]));
         jug.setTipoGenero(TipoGenero.valueOf(linea.split("--")[5]));
         jug.setCorreo(linea.split("--")[6]);
         jug.setClave(linea.split("--")[7]);
         jug.setConfirmacionClave(linea.split("--")[8]);
         jug.setTipoUsuario(TipoUsuario.valueOf(linea.split("--")[9]));
         jug.setTelefono(linea.split("--")[10]);

         jug.setTipoBanco(TipoBanco.valueOf(linea.split("--")[11]));
         jug.setTipoCuenta(TipoCuenta.valueOf(linea.split("--")[12]));
         jug.setNumeroCuenta(linea.split("--")[13]);
         jug.setTitular(linea.split("--")[14]);
         jug.setFechaCaducidad(LocalDate.parse(linea.split("--")[15]));

         jug.setTipoResidencia(TipoResidencia.valueOf(linea.split("--")[16]));
         jug.setCodigoPostal(linea.split("--")[17]);
         jug.setDireccion(linea.split("--")[18]);
         jug.setBarrio(linea.split("--")[19]);
         jug.setTipoDepartamento(TipoDepartamento.valueOf(linea.split("--")[20]));
         jug.setMunicipio(linea.split("--")[21]);
         jug.setVideojuegosComprados(Integer.parseInt(linea.split("--")[22]));
         jug.setImagen(linea.split("--")[23]);

         jugadores.add(jug);
      }
      return jugadores;
   }

   public void guardarCompra(ArrayList<Compra> listaPrestamos) throws IOException {
      StringBuilder contenido = new StringBuilder();

      for (Compra prestamo : listaPrestamos) {
         contenido.append(prestamo.getDocumentoJugador()).append("--").
                 append(prestamo.getJugador()).append("--").
                 append(prestamo.getApellido()).append("--").
                 append(prestamo.getCodigo()).append("--").
                 append(prestamo.getNombreVideojuego()).append("--").
                 append(prestamo.getTipoFormatoVideojuego()).append("--").
                 append(prestamo.getTipoGeneroVideojuego()).append("--").
                 append(prestamo.getFechaCompraInicial()).append("--").
                 append(prestamo.getFechaCompraFinal()).append("--").
                 append(prestamo.getTotal()).append("--").
                 append(prestamo.getFactura()).append("\n");
      }
      archivoUtil.guardarArchivo(rutaCompra, contenido.toString(), false);
   }

   public ArrayList<Compra> cargarCompra() throws IOException {

      ArrayList<Compra> prestamo = new ArrayList<>();
      ArrayList<String> contenido = archivoUtil.leerArchivo(rutaCompra);

      String linea;

      for (String s : contenido) {

         linea = s;

         Compra prest = new Compra();

         prest.setDocumentoJugador(linea.split("--")[0]);
         prest.setJugador(linea.split("--")[1]);
         prest.setApellido(linea.split("--")[2]);
         prest.setCodigo(linea.split("--")[3]);
         prest.setNombreVideojuego(linea.split("--")[4]);
         prest.setTipoFormatoVideojuego(TipoFormatoVideojuego.valueOf(linea.split("--")[5]));
         prest.setTipoGeneroVideojuego(TipoGeneroVideojuego.valueOf(linea.split("--")[6]));

         prest.setFechaCompraInicial(LocalDate.parse(linea.split("--")[7]));
         prest.setFechaCompraFinal(LocalDate.parse(linea.split("--")[8]));
         prest.setTotal(Integer.parseInt(linea.split("--")[9]));
         prest.setFactura(Integer.parseInt(linea.split("--")[10]));

         prestamo.add(prest);
      }
      return prestamo;
   }
}
