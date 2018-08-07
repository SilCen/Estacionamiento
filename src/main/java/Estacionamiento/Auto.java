/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Admin
 */
@Entity
@DiscriminatorValue("AUTO")
public class Auto extends Vehiculo {

    public Auto() {
    }
   
    public Auto (String modelo, String marca, String dominio){
        super (modelo, marca, dominio, Tipo.AUTO);
   }
}
