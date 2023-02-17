/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.villoh.api_rest_videojuegos.controladores;

import com.villoh.api_rest_videojuegos.hilos.Juegos_Hilo;
import com.villoh.api_rest_videojuegos.metodos.Json;
import com.villoh.api_rest_videojuegos.pojos.Juego;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author Villoh
 */

public class Juegos_Controlador implements Initializable {

    @FXML
    private GridPane gridPaneJuegos;
    
    
    @FXML
    private AnchorPane rootPane;

    @FXML
    private MFXTextField textFieldJuegos;
    
    private List<Juego> juegos;
    
    public static int column;
    public static int row;
    
    private Json json;
    private Juego juego;
    private Thread hiloJuego;
    
    @FXML
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        json =  new Json();
        juegos = new ArrayList<>(json.cargaVideojuegosPopulares(30));
        cargaJuegos(juegos);
    }
    
    /**
     * Evento cada vez que se deja de presionar una tecla
     * @param event 
     */
    @FXML
    private void procesaPeticion(KeyEvent event){
        if (!textFieldJuegos.getText().isEmpty()) {
            cargaJuego(creaJuego());
        }else{
            cargaJuegos(juegos);
        }
    }
    
    /**
     * Carga una lista de juegos en el gridPane
     * @param juegos 
     */
    private void cargaJuegos(List<Juego> juegos){
        column = 0;
        row = 1;
        if(gridPaneJuegos.getChildren() != null){
            gridPaneJuegos.getChildren().clear();
        }
        for (Juego jueg : juegos) {
            hiloJuego = new Juegos_Hilo(jueg, 2, gridPaneJuegos);
            hiloJuego.start();
        }
    }
    
    /**
     * Obtiene un juego mediante los metodos de JSON
     * @return juego
     */
    private Juego creaJuego() {
        juego = json.procesarConsulta(this.textFieldJuegos.getText());
        return juego;
    }
    
    /**
     * Carga un juego en el gridPane
     * @param juego juego a cargar
     */
    private void cargaJuego(Juego juego){
        column = 0;
        row = 1;
        gridPaneJuegos.getChildren().clear();
        hiloJuego = new Juegos_Hilo(juego, 1, gridPaneJuegos);
        hiloJuego.start();
    } 
}
