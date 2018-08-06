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
public class Moto extends Vehiculo {
        
    public Moto (String modelo, String marca, String dominio, int propId){
        super (modelo, marca, dominio, propId, Tipo.MOTO);
            
    }
    
}
