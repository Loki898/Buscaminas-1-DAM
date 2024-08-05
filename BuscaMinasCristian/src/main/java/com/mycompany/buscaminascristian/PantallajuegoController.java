/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.mycompany.buscaminascristian;

import com.mycompany.buscaminascristian.Herramientas.Auxiliar;
import com.mycompany.buscaminascristian.Herramientas.ComparadorDificil;
import com.mycompany.buscaminascristian.Herramientas.ComparadorFacil;
import com.mycompany.buscaminascristian.Herramientas.ComparadorMedio;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javafx.util.Duration;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class Esta clase es la del propio juego del buscaminas que se
 * encarga de controlar todo en lo que el juego se refiere como el tablero, el
 * menú, el buscaminas...
 *
 * @author Cristian
 */
public class PantallajuegoController implements Initializable {

    @FXML
    private StackPane tablero;//Stackpane donde se generará el buscaminas
    @FXML
    private Text temporizador;//Texto que muestra el tiempo 
    @FXML
    private Text textoTiempo;//Texto que pone Texto: para antes del tiempo

    private GridPane buscaminas;//GridPane donde se generará el buscaminas 

    private BotonPersonalizado botones[][];//Matriz de botones para guardar todas las casillas del buscaminas

    private int[] datosPersonalizado;//Array que guarda los datos de configuración de la dificultad personalizado

    private Timeline timeline;//Timeline que es una animación para ir mostrando el tiempo opción que me mencionó Matteo e investigue un poco para saber como usarlo

    private int tiempoSegundos;//Entero para guardar los segundos

    private int tiempoMinutos;//Entero para guardar los minutos

    private boolean perder;//Booleano para controlar si el usuario ha perdido

    private boolean[] dificultad;//Array de booleanos para controlar en qué dificultad nos encontramos

    private Jugador jugador;//Variable Jugador para guardar el jugador que está actualemente jugando

    /**
     * Initializes the controller class. Inicializa el array de booleanos a
     * false todas las posiciones, inicializa el GridPane del buscaminas e
     * inicializa el contador para que comienze a contar el tiempo
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dificultad = new boolean[4];
        dificultad[0] = false;
        dificultad[1] = false;
        dificultad[2] = false;
        dificultad[3] = false;
        buscaminas = new GridPane();
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> {
            tiempoSegundos++;
            if (tiempoSegundos >= 60) {
                tiempoMinutos++;
                tiempoSegundos = 0;
            }
            temporizador.setText(tiempoMinutos + ":" + tiempoSegundos + "s");
        }));
        timeline.setCycleCount(Animation.INDEFINITE);

    }

    /**
     * Método que inicializa la dificultad facil del buscaminas llamando a la
     * función que inicia el tiempo, inicializando la matriz de los botones al
     * tamaño de la dificultad facil, genera el tablero llamando a su respectiva
     * función, establece en el array de booleanos que es la dificultad facil,
     * genera las minas de la propia dificultad y los números de las bombas
     * adyacentes a su casilla llamando a sus respectivos métodos
     *
     * @param event
     */
    public void dificultadFacil(ActionEvent event) {
        iniciarContadorTiempo();
        botones = new BotonPersonalizado[8][8];
        generarTablero(8, 8);
        dificultad[0] = true;
        generarMinas(10);
        generarNumeros();
    }

    /**
     * Método que inicializa la dificultad medio del buscaminas llamando a la
     * función que inicia el tiempo, inicializando la matriz de los botones al
     * tamaño de la dificultad medio, genera el tablero llamando a su respectiva
     * función, establece en el array de booleanos que es la dificultad medio,
     * genera las minas de la propia dificultad y los números de las bombas
     * adyacentes a su casilla llamando a sus respectivos métodos
     *
     * @param event
     */
    public void dificultadMedio(ActionEvent event) {
        iniciarContadorTiempo();
        botones = new BotonPersonalizado[16][16];
        dificultad[1] = true;
        generarTablero(16, 16);
        generarMinas(40);
        generarNumeros();
    }

    /**
     * Método que inicializa la dificultad dificil del buscaminas llamando a la
     * función que inicia el tiempo, inicializando la matriz de los botones al
     * tamaño de la dificultad dificil, genera el tablero llamando a su
     * respectiva función, establece en el array de booleanos que es la
     * dificultad dificil, genera las minas de la propia dificultad y los
     * números de las bombas adyacentes a su casilla llamando a sus respectivos
     * métodos
     *
     * @param event
     */
    public void dificultadDificil(ActionEvent event) {
        iniciarContadorTiempo();
        botones = new BotonPersonalizado[16][30];
        dificultad[2] = true;
        generarTablero(16, 30);
        generarMinas(99);
        generarNumeros();
    }

    /**
     * Método que inicializa la dificultad personalizado del buscaminas llamando
     * a la función que inicia el tiempo, inicializando la matriz de los botones
     * al tamaño de la dificultad personalizado, genera el tablero llamando a su
     * respectiva función, establece en el array de booleanos que es la
     * dificultad personalizado, genera las minas de la propia dificultad y los
     * números de las bombas adyacentes a su casilla llamando a sus respectivos
     * métodos
     *
     * @param event
     */
    public void dificultadPersonalizada(ActionEvent event) {
        iniciarContadorTiempo();
        botones = new BotonPersonalizado[datosPersonalizado[0]][datosPersonalizado[1]];
        dificultad[3] = true;
        generarTablero(datosPersonalizado[0], datosPersonalizado[1]);
        generarMinas(datosPersonalizado[2]);
        generarNumeros();
    }

    /**
     * Método que genera el tablero del buscaminas según las filas y las
     * columnas que haya recibido por parámetros
     *
     * @param filas
     * @param columnas
     */
    private void generarTablero(int filas, int columnas) {
        perder = false;//establece el booleano de perder a false para que no haya confusión al iniciar la partida
        buscaminas.getChildren().clear();
        tablero.getChildren().clear();
        //Bucles anidados que generan los botones y los añade al buscaminas y al array de botones
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                //Creamos un botón personalizado que hemos creado con nuestros atributos
                BotonPersonalizado boton = new BotonPersonalizado();
                boton.setMaxWidth(30);
                boton.setMinWidth(30);
                boton.setMaxHeight(30);
                boton.setMinHeight(30);
                boton.setOnMouseClicked(this::comprobarBoton);
                buscaminas.add(boton, i, j);
                botones[i][j] = boton;
            }
        }
        buscaminas.setVisible(true);
        buscaminas.setAlignment(Pos.CENTER);
        tablero.getChildren().add(buscaminas);
    }

    /**
     * Método que es lanzado cuando se pulsa la opción del menu de salir y
     * cierra la ventana volviendo a la ventana del menú principal
     *
     * @param event
     * @throws IOException
     */
    @FXML
    private void salir(ActionEvent event) throws IOException {
        MenuItem menuItem = (MenuItem) event.getSource();
        Stage stage = (Stage) menuItem.getParentPopup().getOwnerWindow();
        stage.hide();
    }

    /**
     * Método que genera las minas aleatoriamente en el tablero poniendo a true
     * el atributo de tieneBomba de los botones personalizados
     *
     * @param numeroMinas
     */
    private void generarMinas(int numeroMinas) {
        for (int i = 0; i < numeroMinas; i++) {
            int columna = (int) (Math.random() * buscaminas.getColumnCount());
            int filas = (int) (Math.random() * buscaminas.getRowCount());
            BotonPersonalizado boton = botones[columna][filas];
            //Nos aseguramos de que no ponga dos bombas en un mismo sitio haciendo que si es así esta iteración no cuente
            if (!boton.tieneBomba()) {
                boton.setBomba();
            } else {
                i--;
            }
        }
    }

    /**
     * Método que sondea el contenido del botón, es decir primero recoge el
     * botón que ha sido pulsado y después evalua con que botón del ratón se ha
     * clickado, si es con el botón izquierdo comprueba si tiene bandera y si no
     * la tiene comprueba si tiene bomba y si no llama a la función adyacentes
     * para comprobar las casillas colindantes y si es el derecho comprueba si
     * ya llevaba bandera o no y si llevaba la quita y si no llevaba le pone una
     * Una vez hecho todo comprueba si se ha ganado ya o no
     *
     * @param event
     */
    public void comprobarBoton(MouseEvent event) {
        BotonPersonalizado boton = (BotonPersonalizado) event.getSource();
        if (event.getButton() == MouseButton.PRIMARY) {
            if (!boton.isTieneBandera()) {
                if (boton.tieneBomba()) {
                    perderJuego();
                } else {
                    adyacentes(boton);
                }
            }

        } else if (event.getButton() == MouseButton.SECONDARY) {
            if (boton.isTieneBandera()) {
                boton.setGraphic(null);
                boton.setTieneBandera(false);
            } else {
                Image imagen = new Image("file:./src/main/resources/com/mycompany/buscaminascristian/bandera.jpg");
                ImageView bandera = new ImageView(imagen);
                bandera.setFitHeight(30);
                bandera.setFitWidth(30);
                boton.setGraphic(bandera);
                boton.setTieneBandera(true);
            }

        }
        if (comprobarVictoria()) {
            ganarJuego();
        }

    }

    /**
     * Método para desactivar todos los botones del buscaminas
     */
    public void desactivarBotones() {
        for (int i = 0; i < buscaminas.getColumnCount(); i++) {
            for (int j = 0; j < buscaminas.getRowCount(); j++) {
                BotonPersonalizado boton = botones[i][j];
                boton.setDisable(true);
            }
        }
    }

    /**
     * Método para establecer el jugador que está jugando
     *
     * @param jugador
     */
    public void setJugador(Jugador jugador) {
        this.jugador = jugador;
    }

    /**
     * Método para recuperar el jugador que ha jugado
     *
     * @return Jugador
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Método para cuando se pulsa una mina que para el tiempo, coloca el
     * boleano de perder a true, muiestra todas las bombas que habían y llama al
     * método alertaPerdido que muestra por pantalla que has perdido
     */
    public void perderJuego() {
        pararContadorTiempo();
        perder = true;
        for (int i = 0; i < buscaminas.getColumnCount(); i++) {
            for (int j = 0; j < buscaminas.getRowCount(); j++) {
                BotonPersonalizado boton = botones[i][j];
                if (boton.tieneBomba()) {
                    boton.setText("*");
                    boton.setStyle("-fx-background-color: lightpink");
                }
                boton.setDisable(true);
            }
        }
        alertaPerdido();
    }

    /**
     * Método que es llamado cuando se comprueba que has ganado y comprueba que
     * efectivamente no hayas perdido y lanza la alerta para decirte que
     * ganaste, para el contador del tiempo, te muestra todas las bombas que
     * evitaste en el tablero y llama a guardarGanador para ver si su tiempo
     * entra en el ranking de la dificultad elegida
     */
    public void ganarJuego() {
        //Esto está porque compruebo la victoria evaluando si están desactivados los botones sin mina y no ponerlo me llevaba a falsas victorias al perder
        if (!perder) {
            pararContadorTiempo();
            alertaGanaste();
            for (int i = 0; i < buscaminas.getColumnCount(); i++) {
                for (int j = 0; j < buscaminas.getRowCount(); j++) {
                    BotonPersonalizado boton = botones[i][j];
                    if (boton.tieneBomba()) {
                        boton.setText("*");
                        boton.setStyle("-fx-background-color: lightpink");
                    }
                    boton.setDisable(true);
                }
            }
            guardarGanador();
        }

    }

    /**
     * Método que comprueba si ya se ha alcanzado la victoria o no comprobando
     * si todos los botones sin bomba están deshabilitados
     *
     * @return boolean
     */
    public boolean comprobarVictoria() {
        for (int i = 0; i < buscaminas.getColumnCount(); i++) {
            for (int j = 0; j < buscaminas.getRowCount(); j++) {
                BotonPersonalizado boton = botones[i][j];
                if (!boton.tieneBomba() && !boton.isDisabled()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Método que inicia el contador del tiempo poniendo los minutos y segundos
     * a 0
     */
    public void iniciarContadorTiempo() {
        tiempoMinutos = 0;
        tiempoSegundos = 0;
        timeline.play();
    }

    /**
     * Método que para el contador del tiempo
     */
    public void pararContadorTiempo() {
        timeline.stop();
    }

    /**
     * Método que comprueba las casillas adyacentes evaluando si sus minas
     * adyacentes son 0 lanza de nuevo la función para evaluar el botón de esa
     * posición y si es un número lo muestra Para este método y para el de
     * generar los números comencé a hacerlo de otra manera con varios if
     * anidados y demás pero tardaba mucho y no conseguía terminarlo del todo
     * bien y pedí un consejo de ayuda a chatGpt y me ofreció esta alternativa
     * que es con dos bucles que van a controlar todas las combinaciones para
     * los botones adyacentes que hay y decidí llevarla a cabo porque era simple
     * y muy efectiva
     *
     * @param boton
     */
    public void adyacentes(BotonPersonalizado boton) {
        //Obtenemos la posición del botón 
        int columnaActual = buscaminas.getColumnIndex(boton);
        int filaActual = buscaminas.getRowIndex(boton);
        //Evaluamos si no hay ninguna mina cercana para entrar al if
        if (boton.getMinasCercanas() == 0) {
            if (!boton.isTieneBandera()) {
                boton.setDisable(true);
            }
            /*Mediante un bucle anidado comprobamos todas las posiciones adyacentes del botón ya que la x y la y van de
            -1 a 1 y para comprobar las casillas adyacente son combinaciones de la columna actual y la fila actual +1,-1 
            o la propia fila o columna, entonces como son combinaciones utilizamos un bucle anidado para comprobar todas*/
            for (int x = -1; x <= 1; x++) {
                for (int y = -1; y <= 1; y++) {
                    if (x == 0 && y == 0 && boton.isTieneBandera()) {
                        continue; //Saltar la celda si es la propia actual o si tiene bandera
                    }
                    //Obtenemos la posición del botón nuevo a comprobar
                    int newX = columnaActual + x;
                    int newY = filaActual + y;

                    //Comprobamos si la posición del nuevo botón está dentro de los límites del buscaminas
                    if (newX >= 0 && newX < buscaminas.getColumnCount() && newY >= 0 && newY < buscaminas.getRowCount()) {
                        BotonPersonalizado adyacente = botones[newX][newY];

                        //Nos aseguramos de no llamar a un botón que ya estuviera deshabilitado para evitar volver a casillas ya comprobadas y llamamos de nuevo a la función
                        if (!adyacente.isDisabled()) {
                            adyacentes(adyacente);
                        }
                    }
                }
            }
            //Si hay minas cercanas muestra el número de las mismas y lo desactiva
        } else {
            boton.setText(boton.getMinasCercanas() + "");
            boton.setDisable(true);
        }
    }

    /**
     * Método para establecer los datos para el nivel personalizado
     *
     * @param datos
     */
    public void setDatosPersonalizado(int[] datos) {
        datosPersonalizado = datos;
    }

    /**
     * Método que sirve para generar los números de los botones en el buscaminas
     * que guarda las filas y columnas del buscaminas y con un bucle anidado que
     * las recorra todas comprueba cuantas bombas tiene colindando es botón Para
     * este método y para el de adyacentes comencé a hacerlo de otra manera con
     * varios if anidados y demás pero tardaba mucho y no conseguía terminarlo
     * del todo bien y pedí un consejo de ayuda a chatGpt y me ofreció esta
     * alternativa que es con dos bucles que van a controlar todas las
     * combinaciones para los botones adyacentes que hay y decidí llevarla a
     * cabo porque era simple y muy efectiva
     */
    public void generarNumeros() {
        //Guardamo las filas y las columnas que posee el buscaminas
        int columnas = buscaminas.getColumnCount();
        int filas = buscaminas.getRowCount();
        //Y comenzamos a comprobar todos los botones
        for (int i = 0; i < columnas; i++) {
            for (int j = 0; j < filas; j++) {
                //Si no tiene bomba entra
                if (!botones[i][j].tieneBomba()) {
                    //Inicializa una variable para guardar las minas cercanas a 0
                    int minasCercanas = 0;

                    /*Mediante un bucle anidado comprobamos todas las posiciones adyacentes del botón ya que la x y la y van de
                    -1 a 1 y para comprobar las casillas adyacente son combinaciones de la columna actual y la fila actual +1,-1 
                    o la propia fila o columna, entonces como son combinaciones utilizamos un bucle anidado para comprobar todas*/
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (x == 0 && y == 0) {
                                continue; //Salta la celda si es la propia celda que está llamando a comprobar sus vecinas
                            }
                            //Obtenemos los nuevos valores para comprobar una de las casillas colindantes
                            int newX = i + x;
                            int newY = j + y;

                            //Comprobamos que esté dentro de los márgenes del buscaminas
                            if (newX >= 0 && newX < columnas && newY >= 0 && newY < filas) {
                                //Si detecta una mina le suma uno al entero de minas cercanas
                                if (botones[newX][newY].tieneBomba()) {
                                    minasCercanas++;
                                }
                            }
                        }
                    }
                    //Una vez haya terminado de comprobar las adyacentes le atribuye al atributo minasCercanas el entero de las minas que ha detectado cercanas
                    botones[i][j].setMinasCercanas(minasCercanas);
                }
            }
        }
    }

    /**
     * Método que es llamado por el botón de reiniciar del menú y lo que hace es
     * según la dificultad actual volver a generar el tablero de dicha
     * dificultad de nuevo
     *
     * @param event
     */
    @FXML
    private void reiniciarIntento(ActionEvent event) {
        if (dificultad[0]) {
            dificultadFacil(event);
        }
        if (dificultad[1]) {
            dificultadMedio(event);
        }
        if (dificultad[2]) {
            dificultadDificil(event);
        }
        if (dificultad[3]) {
            dificultadPersonalizada(event);
        }
    }

    /**
     * Método que se encarga de guardar en el fichero de su respectivo ranking
     * la partida actual si el tiempo se encuentra entre los tiempos a batir
     */
    public void guardarGanador() {
        //Si la dificultad actual es facil
        if (dificultad[0]) {
            //Convierte todo el tiempo en segundos para guardarlo en el fichero
            tiempoSegundos = tiempoMinutos * 60 + tiempoSegundos;
            //Crea una nueva instancia de su respectivo ranking
            RankingFacil rank = new RankingFacil(jugador.getNombre(), jugador.getPathImagen(), tiempoSegundos);
            //Obtiene el ranking actual y lo guarda en un arraylist y añade la nueva victoria
            ArrayList<RankingFacil> nuevoRankingFacil = Auxiliar.leerFacil("RankingFacil.txt");
            nuevoRankingFacil.add(rank);
            //Ordena el array
            Collections.sort(nuevoRankingFacil, new ComparadorFacil());
            //Si el array supera las 10 posiciones elimina la posición final para que solo hayan 10
            if (nuevoRankingFacil.size() > 10) {
                nuevoRankingFacil.remove(nuevoRankingFacil.size() - 1);
            }
            //Y guarda el nuevo ranking que se ha creado
            Auxiliar.grabarFacil(nuevoRankingFacil);
        }
        //Si la dificultad actual es medio
        if (dificultad[1]) {
            //Convierte todo el tiempo en segundos para guardarlo en el fichero
            tiempoSegundos = tiempoMinutos * 60 + tiempoSegundos;
            //Crea una nueva instancia de su respectivo ranking
            RankingMedio rank = new RankingMedio(jugador.getNombre(), jugador.getPathImagen(), tiempoSegundos);
            //Obtiene el ranking actual y lo guarda en un arraylist y añade la nueva victoria
            ArrayList<RankingMedio> nuevoRankingMedio = Auxiliar.leerMedio("RankingMedio.txt");
            nuevoRankingMedio.add(rank);
            //Ordena el array
            Collections.sort(nuevoRankingMedio, new ComparadorMedio());
            //Si el array supera las 10 posiciones elimina la posición final para que solo hayan 10
            if (nuevoRankingMedio.size() > 10) {
                nuevoRankingMedio.remove(nuevoRankingMedio.size() - 1);
            }
            //Y guarda el nuevo ranking que se ha creado
            Auxiliar.grabarMedio(nuevoRankingMedio);
        }
        //Si la dificultad actual es dificil
        if (dificultad[2]) {
            //Convierte todo el tiempo en segundos para guardarlo en el fichero
            tiempoSegundos = tiempoMinutos * 60 + tiempoSegundos;
            //Crea una nueva instancia de su respectivo ranking
            RankingDificil rank = new RankingDificil(jugador.getNombre(), jugador.getPathImagen(), tiempoSegundos);
            //Obtiene el ranking actual y lo guarda en un arraylist y añade la nueva victoria
            ArrayList<RankingDificil> nuevoRankingDificil = Auxiliar.leerDificil("RankingMedio.txt");
            nuevoRankingDificil.add(rank);
            //Ordena el array
            Collections.sort(nuevoRankingDificil, new ComparadorDificil());
            //Si el array supera las 10 posiciones elimina la posición final para que solo hayan 10
            if (nuevoRankingDificil.size() > 10) {
                nuevoRankingDificil.remove(nuevoRankingDificil.size() - 1);
            }
            //Y guarda el nuevo ranking que se ha creado
            Auxiliar.grabarDificil(nuevoRankingDificil);
        }
    }

    /**
     * Método que lanza una alerta para decirle al usuario que ha ganado
     */
    public void alertaGanaste() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("¡¡¡¡¡Victoria!!!!!");
        alert.setHeaderText(null);
        alert.setContentText("Enhorabuena por su victoria");
        alert.showAndWait();
    }

    /**
     * Método que lanza una alerta para decirle al usuario que ha perdido
     */
    public void alertaPerdido() {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Derrota :(");
        alert.setHeaderText(null);
        alert.setContentText("Has tocado una mina y has perdido, inténtalo de nuevo");
        alert.showAndWait();
    }

    /**
     * Método que es llamado al pulsar la opción del menú de información y que
     * lanza una alerta que te cuenta un poco de información sobre este
     * buscaminas
     *
     * @param event
     */
    @FXML
    private void obtenerInformacion(ActionEvent event) {
        Alert error = new Alert(Alert.AlertType.INFORMATION);
        error.setTitle("Información");
        error.setHeaderText("Buscaminas hecho por Cristian Rodríguez Ruiz");
        error.setContentText("Hecho en el mes de junio de 2024, un saludo mi corrector");
        Optional<ButtonType> resultado = error.showAndWait();
    }

}
