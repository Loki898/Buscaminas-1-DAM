/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.buscaminascristian;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.value.ObservableBooleanValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class Controlador para la ventana de introducción de los
 * datos del nivel personalizado
 *
 * @author Cristian
 */
public class DatosPersonalizadoController implements Initializable {

    @FXML
    private TextField columnasText;//Textfield para escribir el número de columnas que se desea
    @FXML
    private TextField filasText;//Textfield para escribir el número de filas que se desea
    @FXML
    private TextField minasText;//Textfield para escribir el número de minas que se desea
    private boolean salir;//Booleano para detectar si ha salido guardando
    @FXML
    private Button btAdd;//Boton para guardar los cambios

    /**
     * Initializes the controller class. Controla que hasta que no se escriba en
     * todos los campos no te deje darle a aceptar y coloca el booleano de salir
     * en true para que solo cambie si guardas
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ObservableBooleanValue columnasIsNotEmpty = columnasText.textProperty().isEmpty();
        ObservableBooleanValue filasIsNotEmpty = filasText.textProperty().isEmpty();
        ObservableBooleanValue minasIsNotEmpty = minasText.textProperty().isEmpty();
        BooleanBinding todosCamposLlenos = Bindings.or(Bindings.or(columnasIsNotEmpty, filasIsNotEmpty), minasIsNotEmpty);
        btAdd.disableProperty().bind(todosCamposLlenos);
        salir = true;
    }

    /**
     * Método para cuando pulsas aceptar que se te guarde la configuración y
     * comprobar que los datos introducidos están correctos
     *
     * @param event
     */
    @FXML
    private void aceptarOnAction(ActionEvent event) {
        try {
            int columnas = Integer.parseInt(columnasText.getText());
            int filas = Integer.parseInt(filasText.getText());
            int minas = Integer.parseInt(minasText.getText());
            if (columnas <= 30 && filas <= 29 && minas < (columnas * filas)) {
                salir = false;
                Node nodo = (Node) event.getSource();
                nodo.getScene().getWindow().hide();
            }

        } catch (Exception e) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle("Error");
            error.setHeaderText("Error al introducir parámetros");
            error.setContentText("Introduce datos válidos");
            Optional<ButtonType> resultado = error.showAndWait();
        }

    }

    /**
     * Método para cancelar el añadir los datos para el nivel personalizado
     *
     * @param event
     */
    @FXML
    private void cancelarOnAction(ActionEvent event) {
        Node nodo = (Node) event.getSource();
        nodo.getScene().getWindow().hide();
    }

    /**
     * Método para comprobar el estado del booleano de salir
     *
     * @return boolean
     */
    public boolean haSalido() {
        return salir;
    }

    /**
     * Método para obtener las columnas del textfield de columnas
     *
     * @return String
     */
    public String getColumnas() {
        return columnasText.getText();
    }

    /**
     * Método para obtener las filas del textfield de filas
     *
     * @return String
     */
    public String getFilas() {
        return filasText.getText();
    }

    /**
     * Método para obtener las minas del textfield de minas
     *
     * @return String
     */
    public String getMinas() {
        return minasText.getText();
    }
}
