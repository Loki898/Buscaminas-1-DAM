package com.mycompany.buscaminascristian;

import com.mycompany.buscaminascristian.Herramientas.Auxiliar;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * Clase encargada de gestionar la pantalla principal del buscaminas
 * 
 * @author Cristian
 */
public class PantallaPrincipalController implements Initializable {

    private ArrayList ju = new ArrayList();//Arraylist que recogerá los jugadores del fichero  Jugadores.txt
    private ObservableList<Jugador> jugadores;//ObservableList que guardará los datos del arraylist de jugadores
    private Jugador jugadorActual;//Jugador que jugará la partida
    @FXML
    private Text nombreJugador;//Texto que se mostrará en pantalla cuando selecciones al jugador


    /**
     * Inicializa la clase haciendo que ju guarde a los jugadores y añadiendolo a la observable list
     * 
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ju = Auxiliar.leerJugadores("Jugadores.txt");
        jugadores = FXCollections.observableList(ju);
    }
/**
 * Método que lanza la ventana de ver el ranking lanzando su respectivo fxml y inicializando su respectivo controller
 * 
 * @param event
 * @throws IOException 
 */
    @FXML
    private void verRanking(ActionEvent event) throws IOException {
        FXMLLoader miCargador = new FXMLLoader(
                getClass().getClassLoader().getResource("com/mycompany/buscaminascristian/ranking.fxml")
        );
        Parent root = miCargador.load();
        RankingController controladorRanking = miCargador.getController();
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Ranking de jugadores");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
    }
/**
 * Método para cerrar la aplicación dando la opción al usuario de guardar el número de jugadores que hay
 * 
 * @param event 
 */
    @FXML
    private void salirOnAction(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Saliendo");
        alert.setHeaderText("Saliendo del programa");
        alert.setContentText("¿Seguro que quieres continuar?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Auxiliar.grabarJugadores("Jugadores.txt", jugadores);
            Platform.exit();
        }
    }
/**
 * Método encargado de llamar e iniciar la ventana de seleccionar el jugador y una vez que termine sui ejecución recuperar
 * si ha sido seleccionado, el jugador con el que jugará la partida
 * 
 * @param event
 * @throws IOException 
 */
    @FXML
    private void seleccionarOnAction(ActionEvent event) throws IOException {
        nombreJugador.setText("");
        jugadorActual=null;
        FXMLLoader miCargador = new FXMLLoader(
                getClass().getClassLoader().getResource("com/mycompany/buscaminascristian/seleccionarjugador.fxml")
        );
        Parent root = miCargador.load();
        SeleccionarjugadorController seleccionarjugadorController = miCargador.getController();
        Scene scene = new Scene(root, 600, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Jugadores");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        //Mira si ha salido seleccionando y guarda el jugador en jugador actual y pone su nombre en pantalla
        if (!seleccionarjugadorController.getSalir()) {
            jugadores = seleccionarjugadorController.getJugadoresActualizados();
            jugadorActual = seleccionarjugadorController.getJugador();
            nombreJugador.setText(jugadorActual.getNombre());
        }

    }
/**
 * Método encargado de comenzar a jugar una vez que el usuario ha seleccionado un jugador, si no saldrá una alerta que
 * mencionará que no ha seleccionado un jugador.
 * Después al seleccionar uno lanzará una alerta para preguntar que dificultad desea y en función de lo que elija
 * el usuario y lanzará la ventana de juego o llamará al método de datospersonalizado para obtener los datos para
 * la partida personalizada
 * 
 * @param event
 * @throws IOException 
 */
    @FXML
    private void jugarOnAction(ActionEvent event) throws IOException {
        //Controla que haya un jugador actualmente
        if (jugadorActual != null) {
            //Carga la alerta para seleccionar la dificultad
            FXMLLoader miCargador = new FXMLLoader(
                    getClass().getClassLoader().getResource("com/mycompany/buscaminascristian/pantallajuego.fxml")
            );
            Parent root = miCargador.load();
            PantallajuegoController pantallajuegoController = miCargador.getController();
            List<String> choices = new ArrayList<>();
            choices.add("Facil");
            choices.add("Medio");
            choices.add("Dificil");
            choices.add("Personalizado");
            ChoiceDialog<String> dialog = new ChoiceDialog<>("Facil", choices);
            dialog.setTitle("Dificultad");
            dialog.setHeaderText(null);
            dialog.setContentText("Elige una dificultad:");
            Optional<String> result = dialog.showAndWait();
            //Creamos la escena y la stage que será lanzada cuando se seleccione una dificultad
            Scene scene;
            Stage stage;
            //Ponemos el booleano de personalizado a true para gestionar que solo cuando haya un error se ponga a false
            boolean personalizadoValido = true;
            //Le pasa al controlador de la pantalla de juego el jugador actual para poder luego guardar su victoria en caso de que la haya
            pantallajuegoController.setJugador(jugadorActual);
            //Condicional que gestiona si selecciona una de las opciones o no
            if (result.isPresent()) {
                //Condicional que gestiona que dificultad elige
                //facil
                if (result.get().equals(choices.get(0))) {
                    scene = new Scene(root, 1000, 950);
                    stage = new Stage();
                    pantallajuegoController.dificultadFacil(event);
                //medio
                } else if (result.get().equals(choices.get(1))) {
                    scene = new Scene(root, 1000, 950);
                    stage = new Stage();
                    pantallajuegoController.dificultadMedio(event);
                //dificil
                } else if (result.get().equals(choices.get(2))) {
                    scene = new Scene(root, 1000, 950);
                    stage = new Stage();
                    pantallajuegoController.dificultadDificil(event);
                //personalizado
                } else {
                    //try catch que controla que al establecer los datos no haya ningún error en los mismos como letras
                    try {
                        pantallajuegoController.setDatosPersonalizado(datosPersonalizado());
                        pantallajuegoController.dificultadPersonalizada(event);
                    } catch (Exception e) {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("Error");
                        error.setHeaderText("Error al introducir parámetros");
                        error.setContentText("No ha finalizado el proceso de establecer los parámetros personalizados por lo que volverá al menú principal");
                        Optional<ButtonType> resultado = error.showAndWait();
                        personalizadoValido = false;
                    }
                    scene = new Scene(root, 1000, 950);
                    stage = new Stage();

                }
                //Método que lanzará la ventana para poder jugar con libertad que solo no se lanzará si el usuario seleccionó
                //personalizado y no completó los datos correctamente
                if (personalizadoValido) {
                    stage.setScene(scene);
                    stage.setTitle("BuscaminasCristian");
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setResizable(false);
                    stage.showAndWait();
                }

            } else {
                //Si no selecciona una opción muestra la alerta para avisar al usuario de su acción
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("ERROR");
                alert.setHeaderText("No ha seleccionado una dificultad");
                alert.setContentText("Si no seleccionas una dificultad no podrás jugar");
                Optional<ButtonType> resultado = alert.showAndWait();
            }

        } else {
            //Alerta que se lanza si no hay ningún jugador seleccionado y se ha pulsado el botón de jugar
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ERROR");
            alert.setHeaderText("No ha seleccionado jugador");
            alert.setContentText("Si no seleccionas un jugador no podrás jugar");
            Optional<ButtonType> result = alert.showAndWait();
        }
        
    }
/**
 * Método que se encarga de lanzar la ventana para guardar los parámetros con los que querrá jugar el 
 * usuario al buscaminas en el nivel personalizado
 * 
 * @return int[]
 * @throws IOException 
 */
    public int[] datosPersonalizado() throws IOException {
        int[] datosPersonalizado = new int[3];
        FXMLLoader miCargador = new FXMLLoader(
                getClass().getClassLoader().getResource("com/mycompany/buscaminascristian/datosPersonalizado.fxml")
        );
        Parent root = miCargador.load();
        DatosPersonalizadoController datosPersonalizadoController = miCargador.getController();
        Scene scene = new Scene(root, 400, 400);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Datos personalizado");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        //Aquí no hay un try/catch porque en la ventana ya se gestiona que a este punto solo lleguen datos correctos
        datosPersonalizado[0] = Integer.parseInt(datosPersonalizadoController.getColumnas());
        datosPersonalizado[1] = Integer.parseInt(datosPersonalizadoController.getFilas());
        datosPersonalizado[2] = Integer.parseInt(datosPersonalizadoController.getMinas());
        return datosPersonalizado;
    }

}
