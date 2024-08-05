/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian;

import javafx.scene.control.Button;

/**
 * Clase que crea el botón personalizado que será cada una de las casillas de
 * este buscaminas que extiende es decir que hereda de Button los botones en
 * javafx para tener todo lo que ofrece un botón sumado a elementos que he
 * creado para facilitarme el trabajo
 *
 * @author Cristian
 */
public class BotonPersonalizado extends Button {

    private boolean tieneBomba;//Booleano para controlar si tiene bomba el botón
    private int minasCercanas;//Entero para determinar cuantas minas tiene alrededor
    private boolean tieneBandera;//Booleano para controlar si el botón tiene bandera

    /**
     * Constructor del botón personalizado
     *
     */
    public BotonPersonalizado() {
        tieneBomba = false;
        minasCercanas = 0;
        tieneBandera = false;
    }

    /**
     * Método para poner una bomba en el botón
     *
     */
    public void setBomba() {
        tieneBomba = true;
    }

    /**
     * Método para comprobar si tiene bomba
     *
     * @return boolean
     */
    public boolean tieneBomba() {
        return tieneBomba;
    }

    /**
     * Método para obtener las minas cercanas a esta casilla
     *
     * @return int
     */
    public int getMinasCercanas() {
        return minasCercanas;
    }

    /**
     * Método para colocar las minas cercanas a esta casilla
     *
     * @param minasCercanas
     */
    public void setMinasCercanas(int minasCercanas) {
        this.minasCercanas = minasCercanas;
    }

    /**
     * Método para saber si el botón tiene bandera o no
     *
     * @return boolean
     */
    public boolean isTieneBandera() {
        return tieneBandera;
    }

    /**
     * Método para establecer una bandera
     *
     * @param tieneBandera
     */
    public void setTieneBandera(boolean tieneBandera) {
        this.tieneBandera = tieneBandera;
    }

}
