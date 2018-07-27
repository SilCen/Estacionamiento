/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Estacionamiento.Vehiculo;
import Estacionamiento.Propietario;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LocalDB extends DB{
    private static ArrayList<Vehiculo> listaVehiculo = new ArrayList();
    private static ArrayList<Propietario> listaPropietario = new ArrayList();
    
    public ArrayList<Vehiculo>getListaVehiculo(){
        return listaVehiculo;
    }
    
    public ArrayList<Propietario>getListaPropietario(){
        return listaPropietario;
    }
    
    public boolean checkDominio(String dom) {
        return getVehiculo(dom)!=null;
    }
    
     public Vehiculo getVehiculo(String dom) {
        Vehiculo v = null;

        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dom.equals(listaVehiculo.get(i).getDominio())) {
                v = listaVehiculo.get(i);
                break;
            }
        }
        return v;
    }
     
     public Propietario getUser(int dni) {
       Propietario P = null;
       
        for (int i = 0; i < listaPropietario.size(); i++) {
            if (dni == listaPropietario.get(i).getDni()) {
                P = listaPropietario.get(i);
                break;
            }
        }
        return P;
    }

}
