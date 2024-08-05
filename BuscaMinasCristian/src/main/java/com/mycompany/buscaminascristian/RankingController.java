package com.mycompany.buscaminascristian;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.mycompany.buscaminascristian.Herramientas.ComparadorMedio;
import com.mycompany.buscaminascristian.Herramientas.ComparadorFacil;
import com.mycompany.buscaminascristian.Herramientas.ComparadorDificil;
import com.mycompany.buscaminascristian.Herramientas.Auxiliar;
import com.mycompany.buscaminascristian.Jugador;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class Clase para mostrar los rankings de cada una de las
 * dificultades excluyendo el personalizado Estuve mucho rato para que esto me
 * funcinara y entre buscar en internet y consultar a la IA de chatgpt encontré
 * esta solución que consiste en para cada uno crear el tableview y así no hay
 * problemas
 *
 * @author Cristian
 */
public class RankingController implements Initializable {

    @FXML
    private GridPane lugarTabla;//Gridpane para colocar el tableview donde veremos los datos de los rankings

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    /**
     * Método que te muestra un tableview con el ranking de la dificultad facil
     * el cual primero genera el tableview y sus columnas luego establece como
     * se han de ver en pantalla cada uno de los elementos del tableview luego
     * limpia los elementos que pueda haber en el GridPane donde irá el
     * tableview añadimos a la observable list el arraylist de jugadores y lo
     * metemos en el tableview
     *
     * @param event
     */
    @FXML
    private void facilOnAction(ActionEvent event) {
//Elementos del tableview
        TableView<RankingFacil> tablaJugadores = new TableView<>();
        TableColumn<RankingFacil, String> nombreColumna = new TableColumn<>("Jugador");
        TableColumn<RankingFacil, Integer> rankingColumna = new TableColumn<>("Tiempo en s");
        TableColumn<RankingFacil, String> imagenColumna = new TableColumn<>("Imagen");
//Ajustamos el tamaño de la imagen en el tableview
        imagenColumna.setPrefWidth(165);
//Establecemos las columnas en el tableview
        tablaJugadores.getColumns().addAll(nombreColumna, rankingColumna, imagenColumna);
//Creamos el arraylist del ranking llamando a la clase Auxiliar donde están todos los métodos para leer los ficheros
//y lo ordenamos por tiempo para que salga en orden en pantalla
        ArrayList<RankingFacil> ju = Auxiliar.leerFacil("RankingFacil.txt");
        Collections.sort(ju, new ComparadorFacil());
//Creamos la observable list que utilizará el tableview
        ObservableList<RankingFacil> jugadores;
//Establecemos como queremos que se vean las columnas en pantalla para el usuario        
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().JugadorProperty());
        rankingColumna.setCellValueFactory(new PropertyValueFactory<>("facil"));
        imagenColumna.setCellValueFactory(cellData -> cellData.getValue().pathImagenProperty());
        imagenColumna.setCellFactory(columna -> {
            return new TableCell<RankingFacil, String>() {
                private ImageView view = new ImageView();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        File imageFile = new File(item);
                        String fileLocation = imageFile.toURI().toString();
                        Image image = new Image(fileLocation, 40, 40, true, true);
                        view.setImage(image);
                        setGraphic(view);
                    }
                }
            };
        });
//Limpiamos el contenido del gridpane y le añadimos el tableview para luego poner la observable list         
        lugarTabla.getChildren().clear();
        lugarTabla.getChildren().add(tablaJugadores);
        jugadores = FXCollections.observableList(ju);
//Debido al método que he utilizado he de castear jugadores a una ObservableList genérica
        tablaJugadores.setItems((ObservableList) jugadores);
    }

    /**
     * Método que te muestra un tableview con el ranking de la dificultad medio
     * el cual primero genera el tableview y sus columnas luego establece como
     * se han de ver en pantalla cada uno de los elementos del tableview luego
     * limpia los elementos que pueda haber en el GridPane donde irá el
     * tableview añadimos a la observable list el arraylist de jugadores y lo
     * metemos en el tableview
     *
     * @param event
     */
    @FXML
    private void medioOnAction(ActionEvent event) {
//Elementos del tableview
        TableView<RankingMedio> tablaJugadores = new TableView<>();
        TableColumn<RankingMedio, String> nombreColumna = new TableColumn<>("Jugador");
        TableColumn<RankingMedio, Integer> rankingColumna = new TableColumn<>("Tiempo en s");
        TableColumn<RankingMedio, String> imagenColumna = new TableColumn<>("Imagen");
//Ajustamos el tamaño de la imagen en el tableview
        imagenColumna.setPrefWidth(165);
//Establecemos las columnas en el tableview
        tablaJugadores.getColumns().addAll(nombreColumna, rankingColumna, imagenColumna);
//Creamos el arraylist del ranking llamando a la clase Auxiliar donde están todos los métodos para leer los ficheros
//y lo ordenamos por tiempo para que salga en orden en pantalla
        ArrayList<RankingMedio> ju = Auxiliar.leerMedio("RankingMedio.txt");
        Collections.sort(ju, new ComparadorMedio());
//Creamos la observable list que utilizará el tableview
        ObservableList<RankingMedio> jugadores;
//Establecemos como queremos que se vean las columnas en pantalla para el usuario
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().JugadorProperty());
        rankingColumna.setCellValueFactory(new PropertyValueFactory<>("medio"));
        imagenColumna.setCellValueFactory(cellData -> cellData.getValue().pathImagenProperty());
        imagenColumna.setCellFactory(columna -> {
            return new TableCell<RankingMedio, String>() {
                private ImageView view = new ImageView();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        File imageFile = new File(item);
                        String fileLocation = imageFile.toURI().toString();
                        Image image = new Image(fileLocation, 40, 40, true, true);
                        view.setImage(image);
                        setGraphic(view);
                    }
                }
            };
        });
//Limpiamos el contenido del gridpane y le añadimos el tableview para luego poner la observable list         
        lugarTabla.getChildren().clear();
        lugarTabla.getChildren().add(tablaJugadores);
        jugadores = FXCollections.observableList(ju);
//Debido al método que he utilizado he de castear jugadores a una ObservableList genérica
        tablaJugadores.setItems((ObservableList) jugadores);
    }

    /**
     * Método que te muestra un tableview con el ranking de la dificultad
     * dificil el cual primero genera el tableview y sus columnas luego
     * establece como se han de ver en pantalla cada uno de los elementos del
     * tableview luego limpia los elementos que pueda haber en el GridPane donde
     * irá el tableview añadimos a la observable list el arraylist de jugadores
     * y lo metemos en el tableview
     *
     * @param event
     */
    @FXML
    private void dificilOnAction(ActionEvent event) {
//Elementos del tableview        
        TableView<RankingDificil> tablaJugadores = new TableView<>();
        TableColumn<RankingDificil, String> nombreColumna = new TableColumn<>("Jugador");
        TableColumn<RankingDificil, Integer> rankingColumna = new TableColumn<>("Tiempo en s");
        TableColumn<RankingDificil, String> imagenColumna = new TableColumn<>("Imagen");
//Ajustamos el tamaño de la imagen en el tableview
        imagenColumna.setPrefWidth(165);
//Establecemos las columnas en el tableview
        tablaJugadores.getColumns().addAll(nombreColumna, rankingColumna, imagenColumna);
//Creamos el arraylist del ranking llamando a la clase Auxiliar donde están todos los métodos para leer los ficheros
//y lo ordenamos por tiempo para que salga en orden en pantalla
        ArrayList<RankingDificil> ju = Auxiliar.leerDificil("RankingDificil.txt");
        Collections.sort(ju, new ComparadorDificil());
//Creamos la observable list que utilizará el tableview
        ObservableList<RankingDificil> jugadores;
//Establecemos como queremos que se vean las columnas en pantalla para el usuario
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().JugadorProperty());
        rankingColumna.setCellValueFactory(new PropertyValueFactory<>("dificil"));
        imagenColumna.setCellValueFactory(cellData -> cellData.getValue().pathImagenProperty());
        imagenColumna.setCellFactory(columna -> {
            return new TableCell<RankingDificil, String>() {
                private ImageView view = new ImageView();

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setGraphic(null);
                    } else {
                        File imageFile = new File(item);
                        String fileLocation = imageFile.toURI().toString();
                        Image image = new Image(fileLocation, 40, 40, true, true);
                        view.setImage(image);
                        setGraphic(view);
                    }
                }
            };
        });
//Limpiamos el contenido del gridpane y le añadimos el tableview para luego poner la observable list  
        lugarTabla.getChildren().clear();
        lugarTabla.getChildren().add(tablaJugadores);
        jugadores = FXCollections.observableList(ju);
//Debido al método que he utilizado he de castear jugadores a una ObservableList genérica
        tablaJugadores.setItems((ObservableList) jugadores);
    }
/**
 * Método para cerrar la ventana que es llamado por el botón salir
 * 
 * @param event 
 */
    @FXML
    private void salirOnAction(ActionEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

}
