package co.edu.uniquindio.armeniagames.controller;

import co.edu.uniquindio.armeniagames.enumm.TipoDepartamento;
import co.edu.uniquindio.armeniagames.enumm.TipoResidencia;
import co.edu.uniquindio.armeniagames.factory.ModelFactory;
import co.edu.uniquindio.armeniagames.main.Main;
import co.edu.uniquindio.armeniagames.subcontroller.EnvioSubcontroller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class EnvioCarritoController implements Initializable {

    public Main main = new Main();
    public EnvioSubcontroller subcontroller;

    private final ObservableList<TipoResidencia>listaTipoResidencia = FXCollections.observableArrayList();
    private final ObservableList<TipoDepartamento> listaTipoDepartamento = FXCollections.observableArrayList();
    private final ObservableList<String> listaTipoMunicipios = FXCollections.observableArrayList();

    @FXML
    private TextField txtBarrio, txtDireccion, txtPostal;

    @FXML
    private Button btnRegistro, btnSalir;

    @FXML
    private ComboBox<TipoResidencia> comboResidencia;

    @FXML
    private ComboBox<TipoDepartamento> comboDepartamento;

    @FXML
    private ComboBox<String> comboMunicipio;

    @FXML
    public void registro() {
        compraSegundaEtapa();
        realizarRegistro();
    }

    private void realizarRegistro() {
        main.cargarVentanaPago2();
        cerrarVentana(btnRegistro);
    }

    @FXML
    public void eventoComboBox4(ActionEvent event) {

        Object evt = event.getSource();

        if (evt.equals(comboResidencia)) {
            txtPostal.setDisable(comboResidencia.getSelectionModel().isEmpty());
        }
    }

    @FXML
    public void eventoText12(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtPostal)) {

            if (Character.isLetter(event.getCharacter().charAt(0)) ||
                    !Character.isLetterOrDigit(event.getCharacter().charAt(0))) {
                txtPostal.deletePreviousChar();
            }

            txtBarrio.setDisable(txtPostal.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText13(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtBarrio)) {
            txtDireccion.setDisable(txtBarrio.getText().isEmpty());
        }
    }

    @FXML
    public void eventoText14(KeyEvent event) {

        Object evt = event.getSource();

        if (evt.equals(txtDireccion)) {
            comboDepartamento.setDisable(txtDireccion.getText().isEmpty());
        }
    }

    @FXML
    public void eventoComboBox5() {

        btnRegistro.setDisable(comboMunicipio.getSelectionModel().getSelectedItem() == null);
    }

    @FXML
    public void eventoDepartamento() {
        eventoDepartamentoJugador();
    }

    public void eventoDepartamentoJugador() {

        comboMunicipio.setDisable(false);
        TipoDepartamento departamento = comboDepartamento.getSelectionModel().getSelectedItem();

        if (departamento.equals(TipoDepartamento.Antioquia)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Abejorral");
            listaTipoMunicipios.add("AbriaquÃ­");
            listaTipoMunicipios.add("AlejandrÃ­a");
            listaTipoMunicipios.add("AmagÃ¡");
            listaTipoMunicipios.add("Amalfi");
            listaTipoMunicipios.add("Andes");
            listaTipoMunicipios.add("AngelÃ³polis");
            listaTipoMunicipios.add("Angostura");
            listaTipoMunicipios.add("AnorÃ­");
            listaTipoMunicipios.add("Anza");
            listaTipoMunicipios.add("ApartadÃ³");
            listaTipoMunicipios.add("Arboletes");
            listaTipoMunicipios.add("Argelia");
            listaTipoMunicipios.add("Armenia");
            listaTipoMunicipios.add("Barbosa");
            listaTipoMunicipios.add("Belmira");
            listaTipoMunicipios.add("Bello");
            listaTipoMunicipios.add("Betania");
            listaTipoMunicipios.add("Betulia");
            listaTipoMunicipios.add("BolÃ­var");
            listaTipoMunicipios.add("BriceÃ±o");
            listaTipoMunicipios.add("BuriticÃ¡");
            listaTipoMunicipios.add("CÃ¡ceres");
            listaTipoMunicipios.add("Caicedo");
            listaTipoMunicipios.add("Caldas");
            listaTipoMunicipios.add("Campamento");
            listaTipoMunicipios.add("CaÃ±asgordas");
            listaTipoMunicipios.add("CaracolÃ­");
            listaTipoMunicipios.add("Caramanta");
            listaTipoMunicipios.add("Carepa");
            listaTipoMunicipios.add("Carmen de Viboral");
            listaTipoMunicipios.add("Carolina");
            listaTipoMunicipios.add("Caucasia");
            listaTipoMunicipios.add("ChigorodÃ³");
            listaTipoMunicipios.add("Cisneros");
            listaTipoMunicipios.add("CocornÃ¡");
            listaTipoMunicipios.add("ConcepciÃ³n");
            listaTipoMunicipios.add("Concordia");
            listaTipoMunicipios.add("Copacabana");
            listaTipoMunicipios.add("Dabeiba");
            listaTipoMunicipios.add("DonmatÃ­as");
            listaTipoMunicipios.add("EbÃ©jico");
            listaTipoMunicipios.add("El Bagre");
            listaTipoMunicipios.add("EntrerrÃ­os");
            listaTipoMunicipios.add("Envigado");
            listaTipoMunicipios.add("Fredonia");
            listaTipoMunicipios.add("Frontino");
            listaTipoMunicipios.add("Giraldo");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.Atlantico)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Baranoa");
            listaTipoMunicipios.add("Barranquilla");
            listaTipoMunicipios.add("Campo de la Cruz");
            listaTipoMunicipios.add("Candelaria");
            listaTipoMunicipios.add("Galapa");
            listaTipoMunicipios.add("Juan de Acosta");
            listaTipoMunicipios.add("Luruaco");
            listaTipoMunicipios.add("Malambo");
            listaTipoMunicipios.add("ManatÃ­");
            listaTipoMunicipios.add("Palmar de Varela");
            listaTipoMunicipios.add("PiojÃ³");
            listaTipoMunicipios.add("Polonuevo");
            listaTipoMunicipios.add("Ponedera");
            listaTipoMunicipios.add("Puerto Colombia");
            listaTipoMunicipios.add("RepelÃ³n");
            listaTipoMunicipios.add("Sabanagrande");
            listaTipoMunicipios.add("Sabanalarga");
            listaTipoMunicipios.add("Santa LucÃ­a");
            listaTipoMunicipios.add("Santo TomÃ¡s");
            listaTipoMunicipios.add("Soledad");
            listaTipoMunicipios.add("Suan");
            listaTipoMunicipios.add("TubarÃ¡");
            listaTipoMunicipios.add("UsiacurÃ­");

            comboMunicipio.setItems(listaTipoMunicipios);
        }

        if (departamento.equals(TipoDepartamento.Bolivar)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Achi");
            listaTipoMunicipios.add("Altos del Rosario");
            listaTipoMunicipios.add("Arenal");
            listaTipoMunicipios.add("Arjona");
            listaTipoMunicipios.add("Arroyohondo");
            listaTipoMunicipios.add("Barranco de Loba");
            listaTipoMunicipios.add("Calamar");
            listaTipoMunicipios.add("Cantagallo");
            listaTipoMunicipios.add("Cartagena");
            listaTipoMunicipios.add("Cicuco");
            listaTipoMunicipios.add("Clemencia");
            listaTipoMunicipios.add("Cordoba");
            listaTipoMunicipios.add("El Carmen de BolÃ­var");
            listaTipoMunicipios.add("El Guamo");
            listaTipoMunicipios.add("El PeÃ±Ã³n");
            listaTipoMunicipios.add("Hatillo de Loba");
            listaTipoMunicipios.add("MaganguÃ©");
            listaTipoMunicipios.add("Mahates");
            listaTipoMunicipios.add("Margarita");
            listaTipoMunicipios.add("MarÃ­a la Baja");
            listaTipoMunicipios.add("Montecristo");
            listaTipoMunicipios.add("MompÃ³s");
            listaTipoMunicipios.add("Morales");
            listaTipoMunicipios.add("Pinillos");
            listaTipoMunicipios.add("Regidor");
            listaTipoMunicipios.add("RÃ­o Viejo");
            listaTipoMunicipios.add("San CristÃ³bal");
            listaTipoMunicipios.add("San Estanislao");
            listaTipoMunicipios.add("San Fernando");
            listaTipoMunicipios.add("San Jacinto");
            listaTipoMunicipios.add("San Jacinto del Cauca");
            listaTipoMunicipios.add("San Juan Nepomuceno");
            listaTipoMunicipios.add("San MartÃ­n de Loba");
            listaTipoMunicipios.add("San Pablo");
            listaTipoMunicipios.add("Santa Catalina");
            listaTipoMunicipios.add("Santa Rosa");
            listaTipoMunicipios.add("Santa Rosa del Sur");
            listaTipoMunicipios.add("SimitÃ­");
            listaTipoMunicipios.add("Soplaviento");
            listaTipoMunicipios.add("Talaigua Nuevo");
            listaTipoMunicipios.add("Tiquisio");
            listaTipoMunicipios.add("Turbaco");
            listaTipoMunicipios.add("Turbana");
            listaTipoMunicipios.add("Villanueva");
            listaTipoMunicipios.add("Zambrano");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.Boyaca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Almeida");
            listaTipoMunicipios.add("Aquitania");
            listaTipoMunicipios.add("Arcabuco");
            listaTipoMunicipios.add("BelÃ©n");
            listaTipoMunicipios.add("Berbeo");
            listaTipoMunicipios.add("BetÃ©itiva");
            listaTipoMunicipios.add("Boavita");
            listaTipoMunicipios.add("BoyacÃ¡");
            listaTipoMunicipios.add("BriceÃ±o");
            listaTipoMunicipios.add("Buenavista");
            listaTipoMunicipios.add("BusbanzÃ¡");
            listaTipoMunicipios.add("Caldas");
            listaTipoMunicipios.add("Campohermoso");
            listaTipoMunicipios.add("Cerinza");
            listaTipoMunicipios.add("Chinavita");
            listaTipoMunicipios.add("ChiquinquirÃ¡");
            listaTipoMunicipios.add("Chiscas");
            listaTipoMunicipios.add("Chita");
            listaTipoMunicipios.add("Chitaraque");
            listaTipoMunicipios.add("ChivatÃ¡");
            listaTipoMunicipios.add("ChÃ­quiza");
            listaTipoMunicipios.add("CiÃ©neaga");
            listaTipoMunicipios.add("CÃ³mbita");
            listaTipoMunicipios.add("Coper");
            listaTipoMunicipios.add("Corrales");
            listaTipoMunicipios.add("CovarachÃ­a");
            listaTipoMunicipios.add("CubarÃ¡");
            listaTipoMunicipios.add("Cucaita");
            listaTipoMunicipios.add("CuÃ­tiva");
            listaTipoMunicipios.add("Duitama");
            listaTipoMunicipios.add("El Cocuy");
            listaTipoMunicipios.add("El Espino");
            listaTipoMunicipios.add("Firavitoba");
            listaTipoMunicipios.add("Floresta");
            listaTipoMunicipios.add("GachantivÃ¡");
            listaTipoMunicipios.add("Gameza");
            listaTipoMunicipios.add("Garagoa");
            listaTipoMunicipios.add("Guacamayas");
            listaTipoMunicipios.add("GÃ¼icÃ¡n");
            listaTipoMunicipios.add("Iza");
            listaTipoMunicipios.add("Jenesano");
            listaTipoMunicipios.add("JericÃ³");
            listaTipoMunicipios.add("La Capilla");
            listaTipoMunicipios.add("La Uvita");
            listaTipoMunicipios.add("La Victoria");
            listaTipoMunicipios.add("Labranza Grande");
            listaTipoMunicipios.add("Macanal");
            listaTipoMunicipios.add("MaripÃ­");
            listaTipoMunicipios.add("Miraflores");

            comboMunicipio.setItems(listaTipoMunicipios);
        }
        if (departamento.equals(TipoDepartamento.Caldas)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Aguadas");
            listaTipoMunicipios.add("Anserma");
            listaTipoMunicipios.add("Aranzazu");
            listaTipoMunicipios.add("BelalcÃ¡zar");
            listaTipoMunicipios.add("ChinchinÃ¡");
            listaTipoMunicipios.add("Filadelfia");
            listaTipoMunicipios.add("La Dorada");
            listaTipoMunicipios.add("La Merced");
            listaTipoMunicipios.add("Manizales");
            listaTipoMunicipios.add("Manzanares");
            listaTipoMunicipios.add("Marmato");
            listaTipoMunicipios.add("Marquetalia");
            listaTipoMunicipios.add("Marulanda");
            listaTipoMunicipios.add("Neira");
            listaTipoMunicipios.add("Norcasia");
            listaTipoMunicipios.add("PÃ¡cora");
            listaTipoMunicipios.add("Palestina");
            listaTipoMunicipios.add("Pensilvania");
            listaTipoMunicipios.add("Riosucio");
            listaTipoMunicipios.add("Risaralda");
            listaTipoMunicipios.add("Salamina");
            listaTipoMunicipios.add("SamanÃ¡");
            listaTipoMunicipios.add("San JosÃ©");
            listaTipoMunicipios.add("SupÃ­a");
            listaTipoMunicipios.add("Victoria");
            listaTipoMunicipios.add("VillamarÃ­a");
            listaTipoMunicipios.add("Viterbo");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Casanare)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Aguazul");
            listaTipoMunicipios.add("ChÃ¡meza");
            listaTipoMunicipios.add("Hato Corozal");
            listaTipoMunicipios.add("La Salina");
            listaTipoMunicipios.add("ManÃ­");
            listaTipoMunicipios.add("Monterrey");
            listaTipoMunicipios.add("NunchÃ­a");
            listaTipoMunicipios.add("Orocue");
            listaTipoMunicipios.add("Paz de Ariporo");
            listaTipoMunicipios.add("Pore");
            listaTipoMunicipios.add("Recetor");
            listaTipoMunicipios.add("Sabanalarga");
            listaTipoMunicipios.add("SÃ¡cama");
            listaTipoMunicipios.add("San Luis de Palenque");
            listaTipoMunicipios.add("TÃ¡mara");
            listaTipoMunicipios.add("Tauramena");
            listaTipoMunicipios.add("Trinidad");
            listaTipoMunicipios.add("Villanueva");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Cauca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Almaguer");
            listaTipoMunicipios.add("Argelia");
            listaTipoMunicipios.add("Balboa");
            listaTipoMunicipios.add("BolÃ­var");
            listaTipoMunicipios.add("Buenos Aires");
            listaTipoMunicipios.add("CajibÃ­o");
            listaTipoMunicipios.add("Caldono");
            listaTipoMunicipios.add("Caloto");
            listaTipoMunicipios.add("Corinto");
            listaTipoMunicipios.add("El Tambo");
            listaTipoMunicipios.add("Florencia");
            listaTipoMunicipios.add("GuachenÃ©");
            listaTipoMunicipios.add("Guapi");
            listaTipoMunicipios.add("InzÃ¡");
            listaTipoMunicipios.add("JambalÃ³");
            listaTipoMunicipios.add("La Sierra");
            listaTipoMunicipios.add("La Vega");
            listaTipoMunicipios.add("LÃ³pez");
            listaTipoMunicipios.add("Mercaderes");
            listaTipoMunicipios.add("Miranda");
            listaTipoMunicipios.add("Morales");
            listaTipoMunicipios.add("Padilla");
            listaTipoMunicipios.add("PÃ¡ez");
            listaTipoMunicipios.add("PatÃ­a");
            listaTipoMunicipios.add("Piamonte");
            listaTipoMunicipios.add("PiendamÃ³");
            listaTipoMunicipios.add("PopayÃ¡n");
            listaTipoMunicipios.add("Puerto Tejada");
            listaTipoMunicipios.add("PuracÃ©");
            listaTipoMunicipios.add("Rosas");
            listaTipoMunicipios.add("San SebastiÃ¡n");
            listaTipoMunicipios.add("Santa Rosa");
            listaTipoMunicipios.add("Santander de Quilichao");
            listaTipoMunicipios.add("Silvia");
            listaTipoMunicipios.add("Sotara");
            listaTipoMunicipios.add("SuÃ¡rez");
            listaTipoMunicipios.add("Sucre");
            listaTipoMunicipios.add("TimbÃ­o");
            listaTipoMunicipios.add("TimbiquÃ­");
            listaTipoMunicipios.add("ToribÃ­o");
            listaTipoMunicipios.add("TotorÃ³");
            listaTipoMunicipios.add("Villa Rica");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Cundinamarca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Agua de Dios");
            listaTipoMunicipios.add("AlbÃ¡n");
            listaTipoMunicipios.add("Anapoima");
            listaTipoMunicipios.add("Anolaima");
            listaTipoMunicipios.add("ArbelÃ¡ez");
            listaTipoMunicipios.add("BeltrÃ¡n");
            listaTipoMunicipios.add("Bituima");
            listaTipoMunicipios.add("BogotÃ¡ D.C.");
            listaTipoMunicipios.add("BojacÃ¡");
            listaTipoMunicipios.add("Cabrera");
            listaTipoMunicipios.add("Cachipay");
            listaTipoMunicipios.add("CajicÃ¡");
            listaTipoMunicipios.add("CaparrapÃ­");
            listaTipoMunicipios.add("CÃ¡queza");
            listaTipoMunicipios.add("Carmen de Carupa");
            listaTipoMunicipios.add("ChaguanÃ­");
            listaTipoMunicipios.add("ChÃ­a");
            listaTipoMunicipios.add("Chipaque");
            listaTipoMunicipios.add("ChoachÃ­");
            listaTipoMunicipios.add("ChocontÃ¡");
            listaTipoMunicipios.add("Cogua");
            listaTipoMunicipios.add("Cota");
            listaTipoMunicipios.add("CucunubÃ¡");
            listaTipoMunicipios.add("El Colegio");
            listaTipoMunicipios.add("El PeÃ±Ã³n");
            listaTipoMunicipios.add("El Rosal");
            listaTipoMunicipios.add("FacatativÃ¡");
            listaTipoMunicipios.add("FÃ³meque");
            listaTipoMunicipios.add("Fosca");
            listaTipoMunicipios.add("Funza");
            listaTipoMunicipios.add("FÃºquene");
            listaTipoMunicipios.add("FusagasugÃ¡");
            listaTipoMunicipios.add("GachalÃ¡");
            listaTipoMunicipios.add("GachancipÃ¡");
            listaTipoMunicipios.add("Gacheta");
            listaTipoMunicipios.add("Gama");
            listaTipoMunicipios.add("Girardot");
            listaTipoMunicipios.add("Granada");
            listaTipoMunicipios.add("GuachetÃ¡");
            listaTipoMunicipios.add("Guaduas");
            listaTipoMunicipios.add("Guasca");
            listaTipoMunicipios.add("GuataquÃ­");
            listaTipoMunicipios.add("Guatavita");
            listaTipoMunicipios.add("Guayabal de Siquima");
            listaTipoMunicipios.add("Guayabetal");
            listaTipoMunicipios.add("GutiÃ©rrez");
            listaTipoMunicipios.add("JerusalÃ©n");
            listaTipoMunicipios.add("JunÃ­n");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.Narino)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("AlbÃ¡n");
            listaTipoMunicipios.add("Aldana");
            listaTipoMunicipios.add("AncuyÃ¡");
            listaTipoMunicipios.add("Arboleda");
            listaTipoMunicipios.add("Barbacoas");
            listaTipoMunicipios.add("BelÃ©n");
            listaTipoMunicipios.add("Buesaco");
            listaTipoMunicipios.add("ChachagÃ¼Ã­");
            listaTipoMunicipios.add("ColÃ³n");
            listaTipoMunicipios.add("Consaca");
            listaTipoMunicipios.add("Contadero");
            listaTipoMunicipios.add("CÃ³rdoba");
            listaTipoMunicipios.add("Cuaspud");
            listaTipoMunicipios.add("Cumbal");
            listaTipoMunicipios.add("Cumbitara");
            listaTipoMunicipios.add("El Charco");
            listaTipoMunicipios.add("El PeÃ±ol");
            listaTipoMunicipios.add("El Rosario");
            listaTipoMunicipios.add("El TablÃ³n de GÃ³mez");
            listaTipoMunicipios.add("Funes");
            listaTipoMunicipios.add("Guachucal");
            listaTipoMunicipios.add("Guaitarilla");
            listaTipoMunicipios.add("GualmatÃ¡n");
            listaTipoMunicipios.add("Iles");
            listaTipoMunicipios.add("ImuÃ©s");
            listaTipoMunicipios.add("Ipiales");
            listaTipoMunicipios.add("La Cruz");
            listaTipoMunicipios.add("La Florida");
            listaTipoMunicipios.add("La Llanada");
            listaTipoMunicipios.add("La Tola");
            listaTipoMunicipios.add("La UniÃ³n");
            listaTipoMunicipios.add("Leiva");
            listaTipoMunicipios.add("Linares");
            listaTipoMunicipios.add("Los Andes");
            listaTipoMunicipios.add("MagÃ¼Ã­");
            listaTipoMunicipios.add("Mallama");
            listaTipoMunicipios.add("Mosquera");
            listaTipoMunicipios.add("NariÃ±o");
            listaTipoMunicipios.add("Olaya Herrera");
            listaTipoMunicipios.add("Ospina");
            listaTipoMunicipios.add("Pasto");
            listaTipoMunicipios.add("Policarpa");
            listaTipoMunicipios.add("PotosÃ­");
            listaTipoMunicipios.add("Providencia");
            listaTipoMunicipios.add("Puerres");
            listaTipoMunicipios.add("Pupiales");
            listaTipoMunicipios.add("Ricaurte");
            listaTipoMunicipios.add("Roberto PayÃ¡n");
            listaTipoMunicipios.add("Samaniego");
            listaTipoMunicipios.add("San Bernardo");
            listaTipoMunicipios.add("San JosÃ© de AlbÃ¡n");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
        if (departamento.equals(TipoDepartamento.NorteDeSantander)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Ãbrego");
            listaTipoMunicipios.add("Arboledas");
            listaTipoMunicipios.add("Bochalema");
            listaTipoMunicipios.add("Bucarasica");
            listaTipoMunicipios.add("CÃ¡chira");
            listaTipoMunicipios.add("CÃ¡cota");
            listaTipoMunicipios.add("ChinÃ¡cota");
            listaTipoMunicipios.add("ChitagÃ¡");
            listaTipoMunicipios.add("ConvenciÃ³n");
            listaTipoMunicipios.add("CÃºcuta");
            listaTipoMunicipios.add("Cucutilla");
            listaTipoMunicipios.add("Durania");
            listaTipoMunicipios.add("El Carmen");
            listaTipoMunicipios.add("El Tarra");
            listaTipoMunicipios.add("El Zulia");
            listaTipoMunicipios.add("Gramalote");
            listaTipoMunicipios.add("HacarÃ­");
            listaTipoMunicipios.add("HerrÃ¡n");
            listaTipoMunicipios.add("La Esperanza");
            listaTipoMunicipios.add("La Playa");
            listaTipoMunicipios.add("Labateca");
            listaTipoMunicipios.add("Los Patios");
            listaTipoMunicipios.add("Lourdes");
            listaTipoMunicipios.add("Mutiscua");
            listaTipoMunicipios.add("OcaÃ±a");
            listaTipoMunicipios.add("Pamplona");
            listaTipoMunicipios.add("Pamplonita");
            listaTipoMunicipios.add("Puerto Santander");
            listaTipoMunicipios.add("Ragonvalia");
            listaTipoMunicipios.add("Salazar");
            listaTipoMunicipios.add("San Calixto");
            listaTipoMunicipios.add("San Cayetano");
            listaTipoMunicipios.add("Santiago");
            listaTipoMunicipios.add("Sardinata");
            listaTipoMunicipios.add("Silos");
            listaTipoMunicipios.add("Teorama");
            listaTipoMunicipios.add("TibÃº");
            listaTipoMunicipios.add("Toledo");
            listaTipoMunicipios.add("Villa Caro");
            listaTipoMunicipios.add("Villa Del Rosario");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Quindio)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Armenia");
            listaTipoMunicipios.add("Buenavista");
            listaTipoMunicipios.add("Calarca");
            listaTipoMunicipios.add("Circasia");
            listaTipoMunicipios.add("CÃ³rdoba");
            listaTipoMunicipios.add("Filandia");
            listaTipoMunicipios.add("GÃ©nova");
            listaTipoMunicipios.add("La Tebaida");
            listaTipoMunicipios.add("Montenegro");
            listaTipoMunicipios.add("Pijao");
            listaTipoMunicipios.add("Quimbaya");
            listaTipoMunicipios.add("Salento");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Risaralda)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("ApÃ­a");
            listaTipoMunicipios.add("Balboa");
            listaTipoMunicipios.add("BelÃ©n de UmbrÃ­a");
            listaTipoMunicipios.add("Dosquebradas");
            listaTipoMunicipios.add("GuÃ¡tica");
            listaTipoMunicipios.add("La Celia");
            listaTipoMunicipios.add("La Virginia");
            listaTipoMunicipios.add("Marsella");
            listaTipoMunicipios.add("Mistrato");
            listaTipoMunicipios.add("Pereira");
            listaTipoMunicipios.add("Pueblo Rico");
            listaTipoMunicipios.add("QuinchÃ­a");
            listaTipoMunicipios.add("Santa Rosa de Cabal");
            listaTipoMunicipios.add("Santuario");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.SanAndres)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Providencia");
            listaTipoMunicipios.add("San AndrÃ©s");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.Tolima)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("Alpujarra");
            listaTipoMunicipios.add("Alvarado");
            listaTipoMunicipios.add("Ambalema");
            listaTipoMunicipios.add("AnzoÃ¡tegui");
            listaTipoMunicipios.add("Armero");
            listaTipoMunicipios.add("Ataco");
            listaTipoMunicipios.add("Cajamarca");
            listaTipoMunicipios.add("Carmen de ApicalÃ¡");
            listaTipoMunicipios.add("Casabianca");
            listaTipoMunicipios.add("Chaparral");
            listaTipoMunicipios.add("Coello");
            listaTipoMunicipios.add("Coyaima");
            listaTipoMunicipios.add("Cunday");
            listaTipoMunicipios.add("Dolores");
            listaTipoMunicipios.add("Espinal");
            listaTipoMunicipios.add("FalÃ¡n");
            listaTipoMunicipios.add("Flandes");
            listaTipoMunicipios.add("Fresno");
            listaTipoMunicipios.add("Guamo");
            listaTipoMunicipios.add("Herveo");
            listaTipoMunicipios.add("Honda");
            listaTipoMunicipios.add("IbaguÃ©");
            listaTipoMunicipios.add("Icononzo");
            listaTipoMunicipios.add("LÃ©rida");
            listaTipoMunicipios.add("Libano");
            listaTipoMunicipios.add("Mariquita");
            listaTipoMunicipios.add("Melgar");
            listaTipoMunicipios.add("Murillo");
            listaTipoMunicipios.add("Natagaima");
            listaTipoMunicipios.add("Ortega");
            listaTipoMunicipios.add("Palocabildo");
            listaTipoMunicipios.add("Piedras");
            listaTipoMunicipios.add("Planadas");
            listaTipoMunicipios.add("Prado");
            listaTipoMunicipios.add("PurificaciÃ³n");
            listaTipoMunicipios.add("Rioblanco");
            listaTipoMunicipios.add("Roncesvalles");
            listaTipoMunicipios.add("Rovira");
            listaTipoMunicipios.add("SaldaÃ±a");
            listaTipoMunicipios.add("San Antonio");
            listaTipoMunicipios.add("San Luis");
            listaTipoMunicipios.add("Santa Isabel");
            listaTipoMunicipios.add("SuÃ¡rez");
            listaTipoMunicipios.add("Valle de San Juan");
            listaTipoMunicipios.add("Venadillo");
            listaTipoMunicipios.add("Villahermosa");
            listaTipoMunicipios.add("Villarrica");

            comboMunicipio.setItems(listaTipoMunicipios);

        }

        if (departamento.equals(TipoDepartamento.ValleDelCauca)) {

            listaTipoMunicipios.clear();

            listaTipoMunicipios.add("AlcalÃ¡");
            listaTipoMunicipios.add("AndalucÃ­a");
            listaTipoMunicipios.add("Ansermanuevo");
            listaTipoMunicipios.add("Argelia");
            listaTipoMunicipios.add("BolÃ­var");
            listaTipoMunicipios.add("Buenaventura");
            listaTipoMunicipios.add("Buga");
            listaTipoMunicipios.add("Bugalagrande");
            listaTipoMunicipios.add("Caicedonia");
            listaTipoMunicipios.add("Cali");
            listaTipoMunicipios.add("Calima");
            listaTipoMunicipios.add("Candelaria");
            listaTipoMunicipios.add("Cartago");
            listaTipoMunicipios.add("Dagua");
            listaTipoMunicipios.add("El Aguila");
            listaTipoMunicipios.add("El Cairo");
            listaTipoMunicipios.add("El Cerrito");
            listaTipoMunicipios.add("El Dovio");
            listaTipoMunicipios.add("Florida");
            listaTipoMunicipios.add("Ginebra");
            listaTipoMunicipios.add("GuacarÃ­");
            listaTipoMunicipios.add("Guadalajara de Buga");
            listaTipoMunicipios.add("JamundÃ­");
            listaTipoMunicipios.add("La Cumbre");
            listaTipoMunicipios.add("La UniÃ³n");
            listaTipoMunicipios.add("La Victoria");
            listaTipoMunicipios.add("Obando");
            listaTipoMunicipios.add("Palmira");
            listaTipoMunicipios.add("Pradera");
            listaTipoMunicipios.add("Restrepo");
            listaTipoMunicipios.add("RiofrÃ­o");
            listaTipoMunicipios.add("Roldanillo");
            listaTipoMunicipios.add("San Pedro");
            listaTipoMunicipios.add("Sevilla");
            listaTipoMunicipios.add("Toro");
            listaTipoMunicipios.add("Trujillo");
            listaTipoMunicipios.add("TulÃºa");
            listaTipoMunicipios.add("Ulloa");
            listaTipoMunicipios.add("Versalles");
            listaTipoMunicipios.add("Vijes");
            listaTipoMunicipios.add("Yotoco");
            listaTipoMunicipios.add("Yumbo");
            listaTipoMunicipios.add("Zarzal");

            comboMunicipio.setItems(listaTipoMunicipios);

        }
    }

    public void cargarTipoDepartamento() {

        listaTipoDepartamento.add(TipoDepartamento.Antioquia);
        listaTipoDepartamento.add(TipoDepartamento.Atlantico);
        listaTipoDepartamento.add(TipoDepartamento.Bolivar);
        listaTipoDepartamento.add(TipoDepartamento.Boyaca);
        listaTipoDepartamento.add(TipoDepartamento.Caldas);
        listaTipoDepartamento.add(TipoDepartamento.Casanare);
        listaTipoDepartamento.add(TipoDepartamento.Cauca);
        listaTipoDepartamento.add(TipoDepartamento.Cundinamarca);
        listaTipoDepartamento.add(TipoDepartamento.Narino);
        listaTipoDepartamento.add(TipoDepartamento.NorteDeSantander);
        listaTipoDepartamento.add(TipoDepartamento.Quindio);
        listaTipoDepartamento.add(TipoDepartamento.Risaralda);
        listaTipoDepartamento.add(TipoDepartamento.SanAndres);
        listaTipoDepartamento.add(TipoDepartamento.Tolima);
        listaTipoDepartamento.add(TipoDepartamento.ValleDelCauca);

        comboDepartamento.setItems(listaTipoDepartamento);
    }


    public void cargarTipoResidencia() {

        listaTipoResidencia.add(TipoResidencia.Rural);
        listaTipoResidencia.add(TipoResidencia.Urbana);

        comboResidencia.setItems(listaTipoResidencia);
    }

    public void compraSegundaEtapa(){

        String departamento = String.valueOf(comboDepartamento.getSelectionModel().getSelectedItem());
        String municipio = comboMunicipio.getSelectionModel().getSelectedItem();
        String codigoPostal = txtPostal.getText();
        String direccion = txtDireccion.getText();

        subcontroller.compraSegundaEtapa(departamento, municipio, codigoPostal, direccion);
    }

    @FXML
    public void salir() {
        cerrarVentana(btnSalir);
        main.cargarVentanaJugador();
    }
    public void cerrarVentana(Button btn) {
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    public void iniciarDatos() {
        ModelFactory factoryController = ModelFactory.getInstance();
        subcontroller = new EnvioSubcontroller(factoryController);
        cargarTipoDepartamento();
        cargarTipoResidencia();
        new EnvioCarritoController();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciarDatos();
    }

}

