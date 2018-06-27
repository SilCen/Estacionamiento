/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

/**
 *
 * @author Admin
 */
public class Vehiculos {
    private String modelo;
    private String marca;
    private String dominio;
       
    protected Vehiculos(String modelo, String marca, String dominio){
        this.modelo = modelo;
        this.marca = marca;
        this.dominio = dominio;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @return the dominio
     */
    public String getDominio() {
        return dominio;
    }
    
    
}
