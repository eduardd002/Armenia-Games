package co.edu.uniquindio.armeniagames.main;

import co.edu.uniquindio.armeniagames.constant.MensajesExcepcionConstant;
import co.edu.uniquindio.armeniagames.constant.MensajesInformacionConstant;
import co.edu.uniquindio.armeniagames.interfacce.MainService;
import co.edu.uniquindio.armeniagames.interfacce.TiendaService;
import co.edu.uniquindio.armeniagames.persistence.Persistencia;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application implements MainService {
    public Persistencia persistencia = new Persistencia();
    public MensajesExcepcionConstant mensajesExcepcionConstant = new MensajesExcepcionConstant();
    public MensajesInformacionConstant mensajesInformacionConstant = new MensajesInformacionConstant();

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("Armenia Games");
        stage.setScene(scene);
        stage.show();
        persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Login");
    }

    public void cargarVentanaLogin() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("LoginView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Login");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Login");

        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Login" + e.getMessage());
        }
    }

    public void cargarVentanaRegistroUsuario() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegistroUsuarioView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Registro Usuario");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Registro Usuario");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Registro Usuario" + e.getMessage());
        }
    }

    public void cargarVentanaRegistroAdministrador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegistroAdministradorView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Registro Administrador");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Registro Administrador");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Registro Administrador" + e.getMessage());
        }
    }

    public void cargarVentanaRegistroJugador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegistroJugadorView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Registro Jugador");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Registro Jugador");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Registro Jugador" + e.getMessage());
        }
    }

    public void cargarVentanaChat() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ChatView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Chat");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Chat");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Chat" + e.getMessage());
        }
    }

    public void cargarVentanaAdministrador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdministradorView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Administrador");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Administrador");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Administrador" + e.getMessage());
        }
    }

    public void cargarVentanaJugador() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("JugadorView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Jugador");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Jugador");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Jugador" + e.getMessage());
        }
    }

    public void cargarVentanaGuardarVideojuego() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("GuardarVideojuegoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Guardar Videojuego");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Guardar Videojuego");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Guardar Videojuego" + e.getMessage());
        }
    }

    public void cargarVentanaEliminarVideojuego() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EliminarVideojuegoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Eliminar Videojuego");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Eliminar Videojuego");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Eliminar Videojuego" + e.getMessage());
        }
    }

    public void cargarVentanaActualizarVideojuego() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ActualizarVideojuegoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Actualizar Videojuego");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Actualizar Videojuego");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Actualizar Videojuego" + e.getMessage());
        }
    }

    public void cargarVentanaRecuperarClave() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RecuperarClaveView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Recuperar Clave");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Recuperar Clave");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Recuperar Clave" + e.getMessage());
        }
    }

    public void cargarVentanaDevolverVideojuego() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("DevolverVideojuegoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Devolver Videojuego");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Devolver Videojuego");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Devolver Videojuego" + e.getMessage());
        }
    }

    public void cargarVentanaEnvio() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EnvioView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Envio");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Envio");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Envio" + e.getMessage());
        }
    }

    public void cargarVentanaPago() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PagoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Pago");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Pago");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Pago" + e.getMessage());
        }
    }

    public void cargarVentanaEnvio2() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EnvioCarritoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Envio");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Envio");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Envio" + e.getMessage());
        }
    }

    public void cargarVentanaPago2() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("PagoCarritoView.fxml"));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.centerOnScreen();
            newStage.setTitle("Ventana Pago");
            newStage.setResizable(false);
            newStage.setScene(scene);
            newStage.show();
            persistencia.guardaRegistroLog(mensajesInformacionConstant.INFORMACION_CARGAR_VENTANA, 1, "Cargar Ventana Pago");
        } catch (Exception e) {
            e.printStackTrace();
            persistencia.guardaRegistroLog(mensajesExcepcionConstant.ERROR_CARGAR_VENTANA, 3,
                    "Cargar Ventana Pago" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
}