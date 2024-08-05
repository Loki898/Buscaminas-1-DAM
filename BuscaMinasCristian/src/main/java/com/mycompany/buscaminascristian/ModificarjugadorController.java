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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class Clase que controla la pantalla para modificar a un
 * jugador
 *
 * @author Cristian
 */
public class ModificarjugadorController implements Initializable {

    @FXML
    private TextField textoNombre;//Textfield para establecer ver y cambiar el nombre a modificar
    @FXML
    private TextField textoImagen;//Textfield para establecer ver y cambiar la ruta de la imagen a cambiar
    private boolean salvar;//Booleano para saber si guarda los cambios
    @FXML
    private Button btAdd;//Botón añadir para guardar los cambios 
    @FXML
    private Button btCancelar;//Botón para cancelar la introducción de los nuevos datos

    /**
     * Initializes the controller class. Que controla que hasta que en los
     * campos no haya texto no permitirle al usuario guardar y establecer el
     * booleano a false porque todavía no se ha guardado
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
     * Método para iniciar el jugador a modificar para así ver sus datos
     * actuales en las casillas
     *
     * @param j
     */
    public void initJugador(Jugador j) {
        textoNombre.setText(j.getNombre());
        textoImagen.setText(j.getPathImagen());
    }

    /**
     * Método para aceptar los cambios y salir de la ventana que es llamado por
     * el botón add
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
     * Método para cancelar los cambios y cerrar la ventana que es llamado por
     * el botón cancelar
     *
     * @param event
     */
    @FXML
    private void cancelarBtOnAction(ActionEvent event) {
        Node n = (Node) event.getSource();
        n.getScene().getWindow().hide();
    }

    /**
     * Método para recuperar un jugador con los nuevos datos que se han
     * actualizado
     *
     * @return Jugador
     */
    public Jugador getJugador() {
        Jugador jugador = new Jugador(textoNombre.getText(), textoImagen.getText());
        return jugador;
    }

    /**
     * Método para saber si ha salido guardando o no
     *
     * @return boolean
     */
    public boolean isSalvar() {
        return salvar;
    }

}
