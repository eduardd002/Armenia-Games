package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.ChatSubcontroller;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {

    public Main main = new Main();
    public ChatSubcontroller subcontroller;

    @FXML
    private Button btnSalir, btnChat;

    @FXML
    private TextField txtMensaje;

    @FXML
    private TextArea txtRespuesta;

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaLogin();
    }

    @FXML
    public void chat() {
        chatUsuario();
    }

    public void chatUsuario() {

        int opcion = Integer.parseInt(txtMensaje.getText());

        String mensaje = subcontroller.chat(opcion);

        txtRespuesta.setPrefHeight(medidas(opcion));
        txtRespuesta.setLayoutX(40);
        txtRespuesta.setLayoutY(280);
        txtRespuesta.setVisible(true);
        txtRespuesta.setText(mensaje);

    }

    public int medidas(int opcion) {

        int medida = 0;

        switch (opcion) {
            case 1 -> medida = 105;
            case 2 -> medida = 75;
            case 3 -> medida = 41;
            case 4 -> medida = 40;
            case 5 -> medida = 100;
        }
        return medida;
    }

    @FXML
    public void eventoText(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtMensaje)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtMensaje.deletePreviousChar();
            }

            btnChat.setDisable(txtMensaje.getText().isEmpty());
        }
    }

    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos() {
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new ChatSubcontroller(factoryController);
        new ChatController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}

