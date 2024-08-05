package com.mycompany.buscaminascristian;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import com.mycompany.buscaminascristian.Herramientas.Auxiliar;
import com.mycompany.buscaminascristian.Jugador;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * Clase que se encarga de gestionar la ventana de seleccionar un jugador para poder jugar al buscaminas 
 *
 * @author Cristian
 */
public class SeleccionarjugadorController implements Initializable {
    
    @FXML
    private TableView<Jugador> vistaJugadores;//Tableview donde se mostrarán los jugadores
    @FXML
    private TableColumn<Jugador, String> nombreColumna;//Columna con el nombre de los jugadores
    @FXML
    private TableColumn<Jugador, String> imagenColumna;//Imagen con la imagen del jugador
    //Array y observable list para guardar y pasar los jugadores al tableview
    private ArrayList ju = new ArrayList();
    private ObservableList<Jugador> jugadores;
    @FXML
    private Button btSeleccion;//Botón para seleccionar el jugador que haya seleccionado
    @FXML
    private Button btSalir;//Botón para salir sin seleccionar jugador
    @FXML
    private Button btModificar;//Botón para modificar un jugador seleccionado
    private Jugador jugador;//Variable jugador para guardar el jugador con el que jugaremos
    private boolean saliendo;//Booleano para controlar si el usuario sale seleccionando o no
    @FXML
    private Button btBorrar;//Botón para borrar un jugador

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //Inicializamos el booleano para que a no ser que seleccione el programa siempre diga que salió
        saliendo = true;
        //Inicializamos ju llamando a que lea el fichero de jugadores 
        ju = Auxiliar.leerJugadores("Jugadores.txt");
        //Establecemos como se verán los datos en el tableview
        nombreColumna.setCellValueFactory(cellData -> cellData.getValue().NombreProperty());
        imagenColumna.setCellValueFactory(cellData -> cellData.getValue().pathImagenProperty());
        imagenColumna.setCellFactory(columna -> {
            return new TableCell<Jugador, String>() {
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
        //Añadimos a la observable list el array con los jugadores y lo añadimos al tableview
        jugadores = FXCollections.observableList(ju);
        vistaJugadores.setItems(jugadores);
        //Bindings para que los botones, seleccionar, modificar y borrar solo estén habilitados cuando haya un jugador seleccionado
        btSeleccion.disableProperty().bind(Bindings.equal(-1, vistaJugadores.getSelectionModel().selectedIndexProperty()));
        btModificar.disableProperty().bind(Bindings.equal(-1, vistaJugadores.getSelectionModel().selectedIndexProperty()));
        btBorrar.disableProperty().bind(Bindings.equal(-1, vistaJugadores.getSelectionModel().selectedIndexProperty()));
        //Evento onAction que ocurrirá cuando el usuario pulse salir que preguntará al usuario si quiere guardar los
        //cambios en el fichero o no y cerrará la ventana
        btSalir.setOnAction((ActionEvent event) -> {
            int guardar = guardarCambios();
            if (guardar == 1) {
                Auxiliar.grabarJugadores("Jugadores.txt", jugadores);
                Node n = (Node) event.getSource();
                n.getScene().getWindow().hide();
            } else if (guardar == -1) {
                Node n = (Node) event.getSource();
                n.getScene().getWindow().hide();
            }
            
        });
        //Evento llevado a cabo con un método de conveniencia y una expresión lambda para borrar el jugador que esté seleccionado
        btBorrar.setOnAction((ActionEvent event) -> {
            jugadores.remove(vistaJugadores.getSelectionModel().getSelectedItem());
            vistaJugadores.refresh();
        });
    }
    /**
     * Método que lanza la ventana para añadir un jugador
     * Primero lanza el fxml y lanza la ventana, después cuando se cierra comprueba que se haya salido guardando y 
     * después si el jugador no existe lo guardará en la lista con los demás y si existe lanzará una alerta para avisar
     * de la imposibilidad de la duplicidad de jugadores
     * @param event
     * @throws IOException 
     */
    @FXML
    private void addJugadorOnAction(ActionEvent event) throws IOException {
        boolean existe = false;//Booleano para comprobar que el nuevo juigador no existe
        FXMLLoader miCargador = new FXMLLoader(
                getClass().getClassLoader().getResource("com/mycompany/buscaminascristian/añadirjugador.fxml")
        );
        Parent root = miCargador.load();
        AñadirjugadorController jugadorController = miCargador.getController();
        Scene scene = new Scene(root, 591, 270);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Añadir jugador");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        //Condicional que controla si se salió seleccionando
        if (jugadorController.isSalvar()) {
            //Condicional que comprueba que el nombre no esté vacío ni los campos de texto si lo está no hará nada
            if (jugadorController.getJugador().getNombre().trim().length() != 0 && !jugadorController.getJugador().getNombre().isEmpty()
                    && jugadorController.getJugador().getPathImagen().trim().length() != 0 && !jugadorController.getJugador().getPathImagen().isEmpty()) {
                Jugador jugador = jugadorController.getJugador();
                //Bucle para comprobar si el jugador existe
                for (Jugador jugadorComprobar : jugadores) {
                    //Condicional que si existe el jugador lanzará un mensaje de error
                    if (jugadorComprobar.getNombre().equals(jugador.getNombre())) {
                        existe = true;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Jugador existente");
                        alert.setContentText("No puede haber dos jugadores con el mismo nombre");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                }
                //Condicional que añade el jugador si no existe
                if (!existe) {
                    jugadores.add(jugador);
                    vistaJugadores.refresh();
                }
                
            }
        }
    }
    /**
     * Método para devolver el ObservableList actualizado
     * 
     * @return ObservableList
     */
    public ObservableList<Jugador> getJugadoresActualizados() {
        return jugadores;
    }
    /**
     * Método encargado de cuando pulsas el botón modificar lanzar la ventana para modificar el jugador seleccionado
     * Primero lanza la ventana de modificar y guarda el jugador seleccionado para pasarselo a la vista de modificar
     * entonces la lanza y espera a que se cierre, en ese caso hará las comprobaciones de si salió guardando, si no
     * está vacío y si no existe ya ese jugador
     * 
     * @param event
     * @throws IOException 
     */
    @FXML
    private void modificarOnAction(ActionEvent event) throws IOException {
        boolean existe = false;//Booleno para controlar la existencia o falta de existencia del nuevo jugador modificado
        //Cargamos el controlador y la ventana
        FXMLLoader miCargador = new FXMLLoader(
                getClass().getClassLoader().getResource("com/mycompany/buscaminascristian/modificarjugador.fxml")
        );
        Parent root = miCargador.load();
        ModificarjugadorController modificarjugadorController = miCargador.getController();
        Scene scene = new Scene(root, 591, 270);
        Stage stage = new Stage();
        Jugador jugador = vistaJugadores.getSelectionModel().getSelectedItem();
        modificarjugadorController.initJugador(jugador);
        stage.setScene(scene);
        stage.setTitle("Modificar jugador");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setResizable(false);
        stage.showAndWait();
        //Comprobamos que haya salido guardando
        if (modificarjugadorController.isSalvar()) {
            //Comprobamos que los campos no estén vacíos 
            if (modificarjugadorController.getJugador().getNombre().trim().length() != 0 && !modificarjugadorController.getJugador().getNombre().isEmpty()
                    && modificarjugadorController.getJugador().getPathImagen().trim().length() != 0 && !modificarjugadorController.getJugador().getPathImagen().isEmpty()) {
                Jugador jugadornuevo = modificarjugadorController.getJugador();
                //Bucle para comprobar que no exista el jugador nuevo
                for (Jugador jugadorComprobar : jugadores) {
                    //si existe lanza un mensaje de error
                    if (jugadorComprobar.getNombre().equals(jugadornuevo.getNombre())) {
                        existe = true;
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("ERROR");
                        alert.setHeaderText("Jugador existente");
                        alert.setContentText("No puede haber dos jugadores con el mismo nombre");
                        Optional<ButtonType> result = alert.showAndWait();
                    }
                }
                //Si no existe el jugador lo modifica eliminando al jugador original sustituyéndolo por el nuevo
                if (!existe) {
                    int indice = jugadores.indexOf(jugador);
                    jugadores.set(indice, jugadornuevo);
                    vistaJugadores.refresh();
                }
                
            }
        }
    }
    /**
     * Método para seleccionar el jugador con el que se va a jugar que lanza el método guardarCambios y depende lo que
     * elija el usuario cerrará la ventana o no
     * 
     * @param event 
     */
    @FXML
    private void seleccionarJugador(ActionEvent event) {
        if (guardarCambios() != 0) {
            saliendo = false;
            jugador = vistaJugadores.getSelectionModel().getSelectedItem();
            Node n = (Node) event.getSource();
            n.getScene().getWindow().hide();
        }
        
    }
    /**
     * Método para obtener el jugador que ha sido seleccionado
     * 
     * @return Jugador
     */
    public Jugador getJugador() {
        return jugador;
    }
    /**
     * Método para obtener si se ha salido guardando
     * 
     * @return boolean
     */
    public boolean getSalir() {
        return saliendo;
    }
    /**
     * Método que según la opción del usuario devuelve 1 si desea guardar y cerrar, -1 si desea cerrar pero no guardar
     * y 0 si no desea ni guardar ni cerrar
     * 
     * @return int
     */
    public int guardarCambios() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Saliendo");
        alert.setHeaderText("¿Desea salir guardando los cambios?");
        alert.setContentText("Elige una opción");
        ButtonType si = new ButtonType("Si");
        ButtonType no = new ButtonType("No");
        ButtonType cancelar = new ButtonType("Cancelar");
        alert.getButtonTypes().setAll(si, no, cancelar);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent()) {
            if (result.get() == si) {
                return 1;
            } else if (result.get() == no) {
                return -1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
        
    }
}
