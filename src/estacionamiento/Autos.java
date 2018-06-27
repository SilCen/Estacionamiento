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
public class Autos extends Vehiculos {
    private float precioConAbono;
    private float precioSinAbono;
    
    public Autos (String modelo, String marca, String dominio, String propietario, float precioConAbono, float precioSinAbono){
        super (modelo, marca, dominio, propietario);
        this.precioConAbono = precioConAbono;
        this.precioSinAbono = precioSinAbono;
    }
    
}
