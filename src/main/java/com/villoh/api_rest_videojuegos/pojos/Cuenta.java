/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.villoh.api_rest_videojuegos.pojos;

/**
 *
 * @author Villoh
 */
public class Cuenta {
    public static String email;
    public static String API_Key;
    
    public Cuenta(String email, String API_Key){
        Cuenta.email = email;
        Cuenta.API_Key = API_Key;
    }

    public Cuenta() {
    }
    
    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Cuenta.email = email;
    }

    public static String getAPI_Key() {
        return API_Key;
    }

    public static void setAPI_Key(String API_Key) {
        Cuenta.API_Key = API_Key;
    }
}
