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
    private int IDProp;
    private ArrayList<Vehiculos> listaVehiculos;
    
    public Propietario (String ApeNomPropietario, int DniPropietario, int IDProp){
        this.ApeNomPropietario = ApeNomPropietario;
        this.DniPropietario = DniPropietario;
        this.IDProp = IDProp;
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
    public int getDniPropietario() {
        return DniPropietario;
    }

    /**
     * @return the IDPropietario
     */
    public int getIDProp() {
        return IDProp;
    }
   
}
