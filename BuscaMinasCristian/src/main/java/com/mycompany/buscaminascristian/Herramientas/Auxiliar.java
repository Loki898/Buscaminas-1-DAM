/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.buscaminascristian.Herramientas;

import com.mycompany.buscaminascristian.Jugador;
import com.mycompany.buscaminascristian.RankingDificil;
import com.mycompany.buscaminascristian.RankingMedio;
import com.mycompany.buscaminascristian.RankingFacil;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import javafx.collections.ObservableList;

/**
 * Clase auxiliar para recoger todos los métodos que trabajan con los ficheros
 * 
 * @author Cristian
 */
public class Auxiliar {
/**
 * Método que lee el fichero de jugadores y devuelve un ArrayList de jugadores
 * 
 * @param nomFich
 * @return ArrayList
 */
    public static ArrayList<Jugador> leerJugadores(String nomFich) {
        //ArrayList<Persona> personal = new ArrayList<>();
        ArrayList<Jugador> jugadores = new ArrayList<>();
        try {
            File f = new File(nomFich);
            Scanner lector = new Scanner(f);

            while (lector.hasNext()) {
                String valor = lector.nextLine();
                String[] jugador = valor.split(",");
                Jugador a = new Jugador(jugador[0], jugador[1]);
                jugadores.add(a);
            }

            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        return jugadores;
    }
/**
 * Método que se encarga de leer el fichero del ranking de la dificultad facil
 * 
 * @param nomFich
 * @return ArrayList
 */
    public static ArrayList<RankingFacil> leerFacil(String nomFich) {
        ArrayList<RankingFacil> jugadores = new ArrayList<>();
        try {
            File f = new File(nomFich);
            Scanner lector = new Scanner(f);

            while (lector.hasNext()) {
                String valor = lector.nextLine();
                String[] jugador = valor.split(",");
                RankingFacil rankingFacil = new RankingFacil(jugador[0], jugador[2], Integer.parseInt(jugador[1]));
                jugadores.add(rankingFacil);
            }

            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        return jugadores;
    }
/**
 * Método que se encarga de leer el fichero del ranking de la dificultad medio
 * 
 * @param nomFich
 * @return ArrayList
 */
    public static ArrayList<RankingMedio> leerMedio(String nomFich) {
        //ArrayList<Persona> personal = new ArrayList<>();
        ArrayList<RankingMedio> jugadores = new ArrayList<>();
        try {
            // Intentamos abrir el fichero
            File f = new File(nomFich);
            Scanner lector = new Scanner(f);

            // Si llega aquí es que ha abierto el fichero :)
            while (lector.hasNext()) {
                String valor = lector.nextLine();
                String[] jugador = valor.split(",");
                RankingMedio rankingMedio = new RankingMedio(jugador[0], jugador[2], Integer.parseInt(jugador[1]));
                jugadores.add(rankingMedio);
            }

            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        return jugadores;
    }
/**
 * Método que se encarga de leer el fichero del ranking de la dificultad dificil
 * 
 * @param nomFich
 * @return ArrayList
 */
    public static ArrayList<RankingDificil> leerDificil(String nomFich) {
        //ArrayList<Persona> personal = new ArrayList<>();
        ArrayList<RankingDificil> jugadores = new ArrayList<>();
        try {
            // Intentamos abrir el fichero
            File f = new File(nomFich);
            Scanner lector = new Scanner(f);

            // Si llega aquí es que ha abierto el fichero :)
            while (lector.hasNext()) {
                String valor = lector.nextLine();
                String[] jugador = valor.split(",");
                RankingDificil rankingDificil = new RankingDificil(jugador[0], jugador[2], Integer.parseInt(jugador[1]));
                jugadores.add(rankingDificil);
            }

            lector.close();
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo");
        }
        return jugadores;
    }
/**
 * Método para guardar en el fichero de jugadores los jugadores que cambien durante el transcurso del programa
 * 
 * @param archivo
 * @param lista 
 */
    public static void grabarJugadores(String archivo, ObservableList<Jugador> lista) {
        try {
            FileWriter fw = new FileWriter(archivo);

            for (Jugador jugador : lista) {
                fw.write(jugador.getNombre() + "," + jugador.getPathImagen());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para guardar el ranking de la dificultad facil en un fichero
     * 
     * @param lista 
     */
    public static void grabarFacil(ArrayList<RankingFacil> lista){
        try {
            FileWriter fw = new FileWriter("RankingFacil.txt");

            for (RankingFacil rankingFacil : lista) {
                String seg = String.valueOf(rankingFacil.getFacil());
                fw.write(rankingFacil.getJugador() + "," + seg + "," + rankingFacil.getPathImagen());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para guardar el ranking de la dificultad medio en un fichero
     * 
     * @param lista 
     */
    public static void grabarMedio(ArrayList<RankingMedio> lista){
        try {
            FileWriter fw = new FileWriter("RankingMedio.txt");

            for (RankingMedio rankingMedio : lista) {
                String seg = String.valueOf(rankingMedio.getMedio());
                fw.write(rankingMedio.getJugador() + "," + seg + "," + rankingMedio.getPathImagen());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Método para guardar el ranking de la dificultad dificil en un fichero
     * 
     * @param lista 
     */
    public static void grabarDificil(ArrayList<RankingDificil> lista){
        try {
            FileWriter fw = new FileWriter("RankingDificil.txt");

            for (RankingDificil rankingDificil : lista) {
                String seg = String.valueOf(rankingDificil.getDificil());
                fw.write(rankingDificil.getJugador() + "," + seg + "," + rankingDificil.getPathImagen());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
