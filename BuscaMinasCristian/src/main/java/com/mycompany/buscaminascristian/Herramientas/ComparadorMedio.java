/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian.Herramientas;

import com.mycompany.buscaminascristian.RankingMedio;
import java.util.Comparator;

/**
 * Clase comparador para ordenar el ranking del modo medio
 *
 * @author Cristian
 */
public class ComparadorMedio implements Comparator<RankingMedio> {

    /**
     * Método para comprar Rankings de la dificultad medio
     *
     * @param o1
     * @param o2
     * @return int
     */
    @Override
    public int compare(RankingMedio o1, RankingMedio o2) {
        if (o1.getMedio() < o2.getMedio()) {
            return -1;
        } else if (o1.getMedio() > o2.getMedio()) {
            return 1;
        } else {
            return 0;
        }
    }

}
