package com.mycompany.buscaminascristian;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class Clase que controla cuando pulsa el botón de añadir
 * jugador en la vista de seleccionar jugador tiene dos textFields que son los
 * enccargados de guardar el nombre y la ruta de la imagen que mostrará el
 * jugador nuevo
 *
 * @author Cristian
 */
public class AñadirjugadorController implements Initializable {

    @FXML
    private TextField textoNombre;//Textfield donde se escribirá el nombre
    @FXML
    private TextField textoImagen;//Textfield donde se guardará la ruta de la imagen
    
    private boolean salvar;//Booleano para averiguar si ha salido guardando
    @FXML
    private Button btAdd;//Botón para guardar y añadir la información del nuevo jugador
    @FXML
    private Button btCancelar;//Botón para para cancelar el añadir el nuevo jugador

    /**
     * Initializes the controller class.
     * Se encarga de hacer que hasta que los textfields no estén con algún texto no deje darle a guardar
     * y establece salvar a false para guardar que aún no se ha pulsado guardar
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        salvar = false;
        ObservableBooleanValue nombreIsNotEmpty = textoNombre.textProperty().isEmpty();
        ObservableBooleanValue imagenIsNotEmpty = textoImagen.textProperty().isEmpty();
        BooleanBinding todosCamposLlenos = Bindings.or(nombreIsNotEmpty, imagenIsNotEmpty);
        btAdd.disableProperty().bind(todosCamposLlenos);
    }

    /**
     * Método para guardar y crear el nuevo jugador
     *
     * @param event
     */
    @FXML
    private void addOnAction(ActionEvent event) {
        salvar = true;
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

    /**
     * Método para cancelar el añadir un nuevo jugador
     *
     * @param event
     */
    @FXML
    private void cancelarBtOnAction(ActionEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

    /**
     * Método para devolver el jugador que se ha creado al controlador de
     * seleccionar jugador
     *
     * @return Jugador
     */
    public Jugador getJugador() {
        Jugador jugador = new Jugador(textoNombre.getText(), textoImagen.getText());
        return jugador;
    }

    /**
     * Método para que el controlador de seleccionar jugador sepa si ha 
     * salido guardando o no
     *
     * @return boolean
     */
    public boolean isSalvar() {
        return salvar;
    }
}
