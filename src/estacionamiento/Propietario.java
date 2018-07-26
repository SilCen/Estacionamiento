/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Propietario {

    private String ApeNomPropietario;
    private int DniPropietario;
    private boolean abono;
    
    public Propietario (String ApeNomPropietario, int DniPropietario){
        this.ApeNomPropietario = ApeNomPropietario;
        this.DniPropietario = DniPropietario;
        abono = false;
    }

    /**
     * @return the ApeNomPropietario
     */
    public String getApeNomPropietario() {
        return ApeNomPropietario;
    }

    /**
     * @return the DniPropietario
     */
    public int getDni() {
        return DniPropietario;
    }
    
    /**
     * @return the abono
     */
    public boolean isAbono() {
        return abono;
    }

    /**
     * @param abono the abono to set
     */
    public void setAbono(boolean abono) {
        this.abono = abono;
    }
    
}
