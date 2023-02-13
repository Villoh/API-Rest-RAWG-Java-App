/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.villoh.api_rest_videojuegos.controladores;

import com.villoh.api_rest_videojuegos.pojos.Juego;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Villoh
 */

public class Juego_Controlador implements Initializable {
    
    @FXML
    private ImageView imgBckgrnd;

    @FXML
    private Label labelPlataformas;

    @FXML
    private Label labelMetacritic;

    @FXML
    private Label labelNombre;

    @FXML
    private Label labelSalida;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    /**
     * Se encarga de poner todos los datos del juego pasado como parametro en los distintos componentes
     * @param juego Juego del que se obtienen los datos
     */
    public void setData(Juego juego){
        this.imgBckgrnd.setImage(getImage(juego.getImgBckgrnd()));
        this.labelNombre.setText(juego.getNombre());
        this.labelPlataformas.setText(juego.getPlataformas());
        this.labelSalida.setText(juego.getFechaSalida());
        this.labelMetacritic.setText(juego.getMetacritic());
    }
    
    /**
     * Obtiene una Imagen de JavaFX a partir de una URL y la devuelve
     * @param url url de donde se obtiene la imagen.
     * @return Image javafx
     */
    public Image getImage (String url){
        Image urlImage = null;
        try {
            URL urlInput = new URL(url);
            urlImage = SwingFXUtils.toFXImage(ImageIO.read(urlInput), null);
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return urlImage;
    }  
}
