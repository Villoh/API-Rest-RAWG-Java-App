package com.villoh.api_rest_videojuegos.controladores;

import com.villoh.api_rest_videojuegos.App;
import com.villoh.api_rest_videojuegos.pojos.Cuenta;
import com.villoh.api_rest_videojuegos.pojos.Juego;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Villoh
 */

public class Main_Menu implements Initializable{
    
    @FXML
    private HBox windowHeader;
    
    @FXML
    private MFXButton buttonCuenta;
    
    @FXML
    private ImageView imageJuegos;
    
    @FXML
    private ImageView imageCuenta;

    @FXML
    private MFXButton buttonJuegos;
    
    @FXML
    private AnchorPane panelPrincipal;
    
    
    private List<Juego> juegos;
    
    private double xOffset = 0;
    private double yOffset = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        makeStageDragable(windowHeader);
        cargaJuegos();
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
    
    @FXML
    private void openJuegosScene(ActionEvent event){
        cargaJuegos();
    }
    
    @FXML
    private void openCuentaScene(ActionEvent event){
        cargaCuentaFXML();
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
    
    /**
     * Carga un AnchorPane con los datos de la cuenta en otro AnchorPane
     */
    public void cargaCuentaFXML(){

        FXMLLoader loader = new FXMLLoader(App.class.getResource("Cuenta.fxml"));
        try {
            AnchorPane cuenta = loader.load();
            Label labelEmail = (Label) cuenta.lookup("#labelEmail");
            labelEmail.setText(Cuenta.getEmail());
            panelPrincipal.getChildren().setAll(cuenta);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
    
    /**
     * Carga un AnchorPane con los juegos en otro AnchorPane
     */
    public void cargaJuegos(){
        FXMLLoader loader = new FXMLLoader(App.class.getResource("Juegos.fxml"));
        try {
            AnchorPane juegosPanel = loader.load();
            panelPrincipal.getChildren().setAll(juegosPanel);
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
  
}