/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Estacionamiento.Vehiculos;
import Estacionamiento.Propietario;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LocalDB extends DB{
    private static ArrayList<Vehiculos> listaVehiculos = new ArrayList();
    private static ArrayList<Propietario> listaPropietario = new ArrayList();
    
    public ArrayList<Vehiculos>getListaVehiculos(){
        return listaVehiculos;
    }
    
    public ArrayList<Propietario>getListaPropietarios(){
        return listaPropietario;
    }
    
}
