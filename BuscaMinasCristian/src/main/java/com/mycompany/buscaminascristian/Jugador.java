/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Clase jugador para gestionar los jugadores que hay para jugar al buscaminas
 *
 * @author Cristian
 */
public class Jugador {
// StringProperty para poder mostrar correctamente los datos en un tableview

    private StringProperty Nombre = new SimpleStringProperty();
    private StringProperty pathImagen = new SimpleStringProperty();

    /**
     * Constructor de jugador que le pasa por parámetros el nombre del jugador y
     * la ruta de la imagen del mismo
     *
     * @param nombre
     * @param rutaimagen
     */
    public Jugador(String nombre, String rutaimagen) {
        this.Nombre.setValue(nombre);
        this.pathImagen.setValue(rutaimagen);
    }

    /**
     * Método para obtener la nombreProperty
     *
     * @return StringProperty
     */
    public final StringProperty NombreProperty() {
        return this.Nombre;
    }

    /**
     * Método para obtener de la nombreProperty el nombre en una String
     *
     * @return String
     */
    public final String getNombre() {
        return this.NombreProperty().get();
    }

    /**
     * Método para obtener la pathImagenProperty
     *
     * @return StringProperty
     */
    public final StringProperty pathImagenProperty() {
        return this.pathImagen;
    }

    /**
     * Método para recuperar la ruta de la imagen
     *
     * @return String
     */
    public String getPathImagen() {
        return this.pathImagenProperty().get();
    }

    /**
     * Método para establecer la ruta de la imagen
     *
     * @param pathImagen
     */
    public void setPathImagen(String pathImagen) {
        this.pathImagenProperty().set(pathImagen);
    }

}
