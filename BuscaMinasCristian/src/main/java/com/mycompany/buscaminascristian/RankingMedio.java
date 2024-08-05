/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase encargada de gestionar el ranking de la dificultad medio que guardará
 * la información del fichero RankingMedio para su futura muestra por pantalla
 *
 * @author Cristian
 */
public class RankingMedio {

    private IntegerProperty medio = new SimpleIntegerProperty();//Property que guarda el tiempo del jugador para su correcta representación en el tableview
    private StringProperty jugador = new SimpleStringProperty();//Property para guardar el nombre del jugador para su correcta representación en el tableview
    private StringProperty pathImagen = new SimpleStringProperty();//Property para guardar la ruta de la imagen para su correcta representación en el tableview

    /**
     * Constructor que establece el nombre, la imagen y el tiempo de la posición
     * del ranking
     *
     * @param jugador
     * @param imagen
     * @param tiempo
     */
    public RankingMedio(String jugador, String imagen, int tiempo) {
        this.jugador.setValue(jugador);
        this.pathImagen.setValue(imagen);
        medio.setValue(tiempo);
    }

    /**
     * Método que devuelve la MedioProperty que recoje el tiempo
     *
     * @return IntegerProperty
     */
    public final IntegerProperty MedioProperty() {
        return this.medio;
    }

    /**
     * Método que devuelve la el tiempo
     *
     * @return int
     */
    public final int getMedio() {
        return this.MedioProperty().get();
    }

    /**
     * Método que establece el tiempo
     *
     * @param tiempo
     */
    public final void setMedio(int tiempo) {
        this.MedioProperty().set(tiempo);
    }

    /**
     * Método que devuelve la jugador property que guarda el nombre del jugador
     *
     * @return StringProperty
     */
    public final StringProperty JugadorProperty() {
        return this.jugador;
    }

    /**
     * Método que devuelve el nombre del jugador en una string
     *
     * @return String
     */
    public final String getJugador() {
        return this.JugadorProperty().get();
    }

    /**
     * Método para establecer el nombre del jugador
     *
     * @param jugador
     */
    public final void setJugador(String jugador) {
        this.JugadorProperty().set(jugador);
    }

    /**
     * Método que devuelve la path imagen property que guarda la ruta de la
     * imagen
     *
     * @return StringProperty
     */
    public final StringProperty pathImagenProperty() {
        return this.pathImagen;
    }

    /**
     * Método que devuelve la ruta de la imagen en una string
     *
     * @return String
     */
    public String getPathImagen() {
        return this.pathImagenProperty().get();
    }

    /**
     * Método que establece la ruta de la imagen
     *
     * @param pathImagen
     */
    public void setPathImagen(String pathImagen) {
        this.pathImagenProperty().set(pathImagen);
    }
}