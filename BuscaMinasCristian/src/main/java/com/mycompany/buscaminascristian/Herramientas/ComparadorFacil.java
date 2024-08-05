/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian.Herramientas;

import com.mycompany.buscaminascristian.RankingFacil;
import java.util.Comparator;

/**
 * Clase comparador para ordenar el ranking del modo facil
 *
 * @author Cristian
 */
public class ComparadorFacil implements Comparator<RankingFacil> {

    /**
     * Método para comprar Rankings de la dificultad facil
     *
     * @param o1
     * @param o2
     * @return int
     */
    @Override
    public int compare(RankingFacil o1, RankingFacil o2) {

        if (o1.getFacil() < o2.getFacil()) {
            return -1;
        } else if (o1.getFacil() > o2.getFacil()) {
            return 1;
        } else {
            return 0;
        }
    }

}
