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
    private ArrayList<Vehiculos> listaVehiculos;
    
    public Propietario (String ApeNomPropietario, int DniPropietario){
        this.ApeNomPropietario = ApeNomPropietario;
        this.DniPropietario = DniPropietario;
        DB db = new LocalDB();
        listaVehiculos= db.getListaVehiculos;
    }
    
    public Vehiculos addVehiculos() 
}
