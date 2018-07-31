/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author Admin
 */
public class Propietario {

    private String ApeNomPropietario;
    private int DniPropietario;
    private boolean abono;
    private Calendar ultimoIngreso = new GregorianCalendar(1970,1,1);
    
    public Propietario (String ApeNomPropietario, int DniPropietario){
        this.ApeNomPropietario = ApeNomPropietario;
        this.DniPropietario = DniPropietario;
        abono = false;
        
    }
   
    public boolean ingresoPropietario(){
        Calendar hoy = Calendar.getInstance();
        if(ultimoIngreso.get(Calendar.YEAR) == hoy.get(Calendar.YEAR) &&
                ultimoIngreso.get(Calendar.MONTH) == hoy.get(Calendar.MONTH) &&
                ultimoIngreso.get(Calendar.DAY_OF_MONTH) == hoy.get(Calendar.DAY_OF_MONTH)){
           return true;
        }
        ultimoIngreso = hoy;
        return false;
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

    /**
     * @param ApeNomPropietario the ApeNomPropietario to set
     */
    public void setApeNomPropietario(String ApeNomPropietario) {
        this.ApeNomPropietario = ApeNomPropietario;
    }

    /**
     * @param DniPropietario the DniPropietario to set
     */
    public void setDniPropietario(int DniPropietario) {
        this.DniPropietario = DniPropietario;
    }
    
}
