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
    private int propId;
    private Tipo coso;
    public enum Tipo {AUTO, MOTO};
    
    protected Vehiculos(String modelo, String marca, String dominio, int propId, Tipo coso){
        this.modelo = modelo;
        this.marca = marca;
        this.dominio = dominio;
        this.propId = propId;
        this.coso = coso;
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
    
    public int getPropId(){
    return propId; 
    }
    
    public Tipo getTipo(){
    return coso;
    }
}
