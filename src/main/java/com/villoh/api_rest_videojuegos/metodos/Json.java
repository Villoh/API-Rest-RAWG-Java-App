/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.villoh.api_rest_videojuegos.metodos;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.villoh.api_rest_videojuegos.controladores.Juegos_Controlador;
import com.villoh.api_rest_videojuegos.pojos.Cuenta;
import com.villoh.api_rest_videojuegos.pojos.Juego;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Villoh
 */

public class Json {
    

    private String inputJuego;
    private ObjectMapper oMapper;
    private JsonNode datos;
    private Juegos_Controlador juegosControlador;
    private Juego juego;
    private List<Juego> juegos;
    
    /**
     * Este método procesa una consulta a la API REST de Rawg.IO y le pide un archivo JSON con el juego escrito por teclado.
     * @return 
     */
    public Juego procesarConsulta() {
        juego = new Juego();
        oMapper = new ObjectMapper();
        this.inputJuego = this.juegosControlador.getTextFieldJuegos().getText().toLowerCase(); //la api  es sensible a mayusculas, si le pasamos un nombre con mayusculas, da error y no devuelve el JSON
        if (!this.inputJuego.isBlank()) {
            try {
                this.datos = oMapper.readTree(new URL("https://api.rawg.io/api/games?search=".concat(inputJuego).concat("&page_size=1&key=").concat(Cuenta.getAPI_Key()))).path("results"); //hacemos el request a la api con el nombre del juego pasado por teclado
            } catch (MalformedURLException ex) {
                System.err.println(ex);
            } catch (IOException ex) {
                System.err.println(ex);
            }
            if (this.datos != null) {
                System.out.println(LocalDate.now().toString());
                this.juego.setNombre(datos.get(0).get("name").asText());
                StringBuilder plataformasBuilder = new StringBuilder();
                datos.get(0).path("platforms").forEach(x -> plataformasBuilder.append(x.path("platform").path("name").asText()).append(", "));
                String plataformas = plataformasBuilder.toString();
                if (plataformas.length() > 0) {
                    plataformas = plataformas.substring(0, plataformas.length() - 2);
                }
                this.juego.setPlataformas(plataformas);
                this.juego.setFechaSalida("Fecha salida: ".concat(datos.get(0).get("released").asText()));
                this.juego.setMetacritic("Metacritic (Puntuación): ".concat(datos.get(0).get("metacritic").asText()));
                this.juego.setImgBckgrnd(datos.get(0).get("background_image").asText());
            }
        }
        return juego;
    }
    
    public List<Juego> cargaVideojuegosPopulares(int limiteJuegos) {
        juegos = new ArrayList<>();
        try {
            URL url = new URL(("https://api.rawg.io/api/games?dates=".concat(LocalDate.now().minusMonths(2).toString()).concat(",").concat(LocalDate.now().plusYears(5).toString()).concat("&ordering=-added").concat("&page_size=").concat(String.valueOf(limiteJuegos)).concat("&key=").concat(Cuenta.getAPI_Key())));
            this.datos = oMapper.readTree(url).path("results"); //hacemos el request a la api
        } catch (MalformedURLException ex) {
            System.err.println(ex);
        } catch (IOException ex) {
            System.err.println(ex);
        }
        if (this.datos != null) {
            for (JsonNode juegoNode : datos) {
                juego = new Juego();
                juego.setNombre(juegoNode.get("name").asText());
                StringBuilder plataformasBuilder = new StringBuilder();
                juegoNode.path("platforms").forEach(x -> plataformasBuilder.append(x.path("platform").path("name").asText()).append(", "));
                String plataformas = plataformasBuilder.toString();
                if (plataformas.length() > 0) {
                    plataformas = plataformas.substring(0, plataformas.length() - 2);
                }
                this.juego.setPlataformas(plataformas);
                juego.setFechaSalida("Fecha salida: ".concat(juegoNode.get("released").asText()));
                if(juegoNode.get("metacritic").asText().equals("null")){
                    juego.setMetacritic("No puntuado en Metacritic");
                }else{
                    juego.setMetacritic("Metacritic (Puntuación): ".concat(juegoNode.get("metacritic").asText()));
                }
                juego.setImgBckgrnd(juegoNode.get("background_image").asText());
                // Aquí deberías procesar la lista de plataformas de la misma forma que lo hiciste anteriormente
                juegos.add(juego);
            }
        }
        return juegos;
    }
    
    /**
     * Inicializa el controlador para poder acceder a el
     * @param juegos_Controlador controlador
     */
    public Json(Juegos_Controlador juegos_Controlador){
        juegosControlador = juegos_Controlador;
        oMapper = new ObjectMapper();
    }
}
