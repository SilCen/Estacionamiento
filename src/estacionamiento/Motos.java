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
public class Motos extends Vehiculos {
    private float precio;
   
    
    public Motos (String modelo, String marca, String dominio, String propietario, float precio){
        super (modelo, marca, dominio, propietario);
        this.precio = precio;
        
    }
    
}
