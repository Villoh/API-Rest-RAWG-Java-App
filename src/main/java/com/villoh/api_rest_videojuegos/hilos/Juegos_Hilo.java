/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.villoh.api_rest_videojuegos.hilos;

import com.villoh.api_rest_videojuegos.App;
import com.villoh.api_rest_videojuegos.controladores.Juego_Controlador;
import static com.villoh.api_rest_videojuegos.controladores.Juegos_Controlador.column;
import static com.villoh.api_rest_videojuegos.controladores.Juegos_Controlador.row;
import com.villoh.api_rest_videojuegos.pojos.Juego;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 *
 * @author Villoh
 */

public class Juegos_Hilo extends Thread {
    
    private Juego juego;
    private int limite; //Limite de columnas (juegos en una fila)
    private GridPane gridPaneJuegos;
    private Juego_Controlador juegoControlador;
    private AnchorPane cardBox;
    private FXMLLoader fxmlLoader;
    
    public Juegos_Hilo(Juego juego, int limite, GridPane gridPaneJuegos){
        this.juego = juego;
        this.limite = limite;
        this.gridPaneJuegos = gridPaneJuegos;
    }
    
    @Override
    public void run() {
        inicializaJuego();
    }
    
    /**
     * Carga todos los juegos que se encuentran en el array, en el gridpane del controlador de juegos.
     */
    public void inicializaJuego(){
        try {
            fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("Juego.fxml"));
            cardBox = fxmlLoader.load();
            juegoControlador = fxmlLoader.getController();
            juegoControlador.setData(juego);
            
            
            Platform.runLater(() -> {
                if(limite == 1){
                    if(column == 0){
                        gridPaneJuegos.add(cardBox, column++, row);
                    }
                } else {
                    if (column == limite) {
                        column = 0;
                        gridPaneJuegos.add(cardBox, column, row++);
                    } else {
                        gridPaneJuegos.add(cardBox, column++, row);
                    }
                }
            });
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
