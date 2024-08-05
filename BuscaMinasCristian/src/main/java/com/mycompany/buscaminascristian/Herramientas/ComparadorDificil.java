/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian.Herramientas;

import com.mycompany.buscaminascristian.RankingDificil;
import java.util.Comparator;

/**
 * Clase comparador para ordenar el ranking del modo dificil
 *
 * @author Cristian
 */
public class ComparadorDificil implements Comparator<RankingDificil> {

    /**
     * Método para comprar Rankings de la dificultad dificil
     *
     * @param o1
     * @param o2
     * @return int
     */
    @Override
    public int compare(RankingDificil o1, RankingDificil o2) {
        if (o1.getDificil() < o2.getDificil()) {
            return -1;
        } else if (o1.getDificil() > o2.getDificil()) {
            return 1;
        } else {
            return 0;
        }
    }

}
