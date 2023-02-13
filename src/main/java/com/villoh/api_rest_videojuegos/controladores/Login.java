package com.villoh.api_rest_videojuegos.controladores;

import com.villoh.api_rest_videojuegos.App;
import com.villoh.api_rest_videojuegos.hilos.Autentificacion_Hilo;
import io.github.palexdev.materialfx.controls.*;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Villoh
 */

public class Login implements Initializable{
    
    @FXML
    private MFXPasswordField pfContrasenia;

    @FXML
    private MFXTextField tfAPIKey;

    @FXML
    private MFXTextField tfCorreo;

    @FXML
    private HBox tittleBar;

    private double xOffset = 0;
    private double yOffset = 0;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDragable(tittleBar);
    }
    
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    /**
     * Cierra la aplicacion.
     * @param event 
     */
    @FXML
    private void closeApp(MouseEvent event) {
        App.st.close();
    }
    
    /**
     * Minimiza
     * @param event 
     */
    @FXML
    private void minStage(MouseEvent event) {
        App.st.setIconified(true);
    }
    
    /**
     * Accion que se realiza al presionar el boton Login( instancia un nuevo hilo y lo inicia, este hilo se encarga de validar los datos y cargar la nueva pantalla)
     */
    @FXML
    private void login(ActionEvent event){
        Thread autentificacion_hilo  = new Autentificacion_Hilo(tfCorreo.getText(), pfContrasenia.getText(), tfAPIKey.getText(), false);
        autentificacion_hilo.start();
    }
    
    /**
     * Accion que se realiza al presionar el boton Invitado( instancia un nuevo hilo y lo inicia, este hilo se encarga de validar los datos y cargar la nueva pantalla)
     */
    @FXML
    private void loginInvitado(ActionEvent event){
        Thread autentificacion_hilo  = new Autentificacion_Hilo("19383900ac4b421883fe878b75e0880e", true);
        autentificacion_hilo.start();
    }
    
    /**
     * Accion que se realiza en el boton Registro( te redirige a la pagina sde rawg para que puedas registrarte)
     */
    @FXML
    private void registro(ActionEvent event){
        openURL("https://rawg.io/login/?forward=developer");
    }
    
    /**
     * Abre una URL en el navegador predeterminado
     * @param url URL que va a abrir 
     */
    private void openURL(String url){
        Desktop desktop = java.awt.Desktop.getDesktop();
        try {
            URI oURL = new URI(url);
            desktop.browse(oURL);
        } catch (URISyntaxException | IOException ex) {
            System.err.println(ex);
        }
    }
    
    /**
     * Hace la interfaz arrastable
     * @param tittleBar
     */
    public void makeStageDragable(HBox tittleBar) {

        tittleBar.setOnMousePressed((event) -> {

            xOffset = event.getSceneX();
            yOffset = event.getSceneY();

        });

        tittleBar.setOnMouseDragged((event) -> {

            App.st.setX(event.getScreenX() - xOffset);
            App.st.setY(event.getScreenY() - yOffset);
            App.st.setOpacity(0.8f);

        });

        tittleBar.setOnDragDone((event) -> {

            App.st.setOpacity(1.0f);

        });

        tittleBar.setOnMouseReleased((event) -> {

            App.st.setOpacity(1.0f);

        });
    } 
}
