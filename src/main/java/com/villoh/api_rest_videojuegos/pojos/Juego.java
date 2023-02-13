/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.villoh.api_rest_videojuegos.pojos;

/**
 *
 * @author Villoh
 */

public class Juego {
    private String nombre;
    private String plataformas;
    private String fechaSalida;
    private String metacritic;
    private String imgBckgrnd;

    public Juego() {
    }

    public Juego(String nombre, String desarrollador, String fechaSalida, String metacritic, String imgBckgrnd) {
        this.nombre = nombre;
        this.plataformas = desarrollador;
        this.fechaSalida = fechaSalida;
        this.metacritic = metacritic;
        this.imgBckgrnd = imgBckgrnd;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(String plataformas) {
        this.plataformas = plataformas;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(String metacritic) {
        this.metacritic = metacritic;
    }

    public String getImgBckgrnd() {
        return imgBckgrnd;
    }

    public void setImgBckgrnd(String imgBckgrnd) {
        this.imgBckgrnd = imgBckgrnd;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Juego{");
        sb.append("nombre=").append(nombre);
        sb.append(", desarrollador=").append(plataformas);
        sb.append(", fechaSalida=").append(fechaSalida);
        sb.append(", metacritic=").append(metacritic);
        sb.append(", imgBckgrnd=").append(imgBckgrnd);
        sb.append('}');
        return sb.toString();
    }
    
    
}
