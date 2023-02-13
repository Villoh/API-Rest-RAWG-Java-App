/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.villoh.api_rest_videojuegos.metodos;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

/**
 *
 * @author Villoh
 */

public class Autentificacion {
    
    /**
     * Metodo que valida la api key de RAWG
     * @param apiKey
     * @return 
     */
    public static boolean validateApiKey(String apiKey) {
        try {
            URL url = new URL("https://api.rawg.io/api/games?key=" + apiKey);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            return responseCode == 200;
        }catch (MalformedURLException ex) {
            System.err.println(ex);
        }catch (ProtocolException ex) {
            System.err.println(ex);
        }catch (IOException ex) {
            System.err.println(ex);
        }
        return false;
    }
    
    /**
     * Metodo que valida un usuario
     * @param email
     * @param password
     * @return 
     */
    public static boolean validateUser(String email, String password){
        try{
            URL url = new URL("https://api.rawg.io/api/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setRequestProperty("Content-Type", "application/json");

            String requestBody = "{\"email\": \"" + email + "\",\"password\": \"" + password + "\"}";
            try (OutputStream outputStream = connection.getOutputStream()) {
                outputStream.write(requestBody.getBytes());
                outputStream.flush();
            }
            int responseCode = connection.getResponseCode();
            return responseCode == 200; // el inicio de sesión ha sido exitoso, procesar la respuesta
            // procesar la respuesta aquí
            // el inicio de sesión ha fallado, mostrar un mensaje de error al usuario
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (ProtocolException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return false;
    }
    
    /**
     * Mensaje en función de una serie de parametros
     * @param tipoAlerta
     * @param titulo
     * @param cabecera
     * @param texto 
     */
    public static void mensajeLogin(AlertType tipoAlerta, String titulo, String cabecera, String texto){
        Alert mensaje = new Alert(tipoAlerta);
        mensaje.setTitle(titulo);
        mensaje.setHeaderText(cabecera);
        mensaje.setContentText(texto);
        mensaje.initStyle(StageStyle.UNDECORATED);
        mensaje.show();
    }
}
