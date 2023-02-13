/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.villoh.api_rest_videojuegos.hilos;

import com.villoh.api_rest_videojuegos.App;
import com.villoh.api_rest_videojuegos.metodos.Autentificacion;
import com.villoh.api_rest_videojuegos.pojos.Cuenta;
import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Villoh
 */

public class Autentificacion_Hilo extends Thread{
    private String correo;
    private String contrasenia;
    private String APIKey;
    private boolean invitado;
    public static String estadoLogin;
    private static Cuenta cuenta;
    
    public Autentificacion_Hilo(String correo, String contrasenia, String APIKey, boolean invitado){
        this.correo =  correo;
        this.contrasenia = contrasenia;
        this.APIKey = APIKey;
        this.invitado = invitado;
    }
    
    public Autentificacion_Hilo(String APIKey, boolean invitado){
        this.APIKey = APIKey;
        this.invitado = invitado;
    }
    
    @Override
    public void run() {
        validateData();
    }
    
    /**
     * Metodo que se encarga de validar el usuario y la API Key, y si son correctos carga el main menu y carga los datos de la clase Cuenta con
     * el email y la API Key para poder reutilizarlos.
     */
    private void validateData(){
        if (invitado) {
            if (Autentificacion.validateApiKey(APIKey)) {
                estadoLogin = "correcto";
                Platform.runLater(() -> {
                    Cuenta.setEmail("Invitado");
                    Cuenta.setAPI_Key(APIKey);
                    cargaMainMenuFXML();
                });
            }
            else{
                estadoLogin = "errorAPI";
                Platform.runLater(() -> {
                    Autentificacion.mensajeLogin(AlertType.ERROR, "Error", "Error al loguearse", "La API Key proporcionada no existe");
                });
            }
        } else if (invitado == false) {
            if (Autentificacion.validateUser(correo, contrasenia)) {
                if (Autentificacion.validateApiKey(APIKey)) {
                    estadoLogin = "correcto";
                    Platform.runLater(() -> {
                        cargaMainMenuFXML();
                        Cuenta.setEmail(correo);
                        Cuenta.setAPI_Key(APIKey);
                    });
                }
                else{
                    estadoLogin = "errorAPI";
                    Platform.runLater(() -> {
                        Autentificacion.mensajeLogin(AlertType.ERROR, "Error", "Error al loguearse", "La API Key proporcionada no existe");
                    });
                }
            }else{
                estadoLogin = "errorCorreoOEmail";
                Platform.runLater(() -> {
                    Autentificacion.mensajeLogin(AlertType.ERROR, "Error", "Error al loguearse", "El email o la contraseña introducida no son correctas");
                });
            }
        }
    }
    
    /**
     * Carga fxml "Main_Menu"
     */
    public static void cargaMainMenuFXML(){
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Main_Menu.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            App.st.setScene(scene);
            App.st.sizeToScene();
            App.st.centerOnScreen();
            App.st.sizeToScene();
            App.st.centerOnScreen();
            App.st.show();
        } catch (IOException ex) {
            System.err.println(ex);
        }
    }
}
