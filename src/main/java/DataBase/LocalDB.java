/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Estacionamiento.Vehiculo;
import Estacionamiento.Propietario;
import Estacionamiento.Utils;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class LocalDB implements DB{
    private static ArrayList<Vehiculo> listaVehiculo = new ArrayList();
    private static ArrayList<Propietario> listaPropietario = new ArrayList();
    
    public ArrayList<Vehiculo> getVehiculo(String dom) {
       ArrayList<Vehiculo> lista = new ArrayList();

        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dom.equals(listaVehiculo.get(i).getDominio())) {
                lista.add(listaVehiculo.get(i));
            }
        }
        return lista;
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
     
     public void addPropietario(Propietario prop) {
        listaPropietario.add(prop);
    }
     
     public void addVehiculo(Vehiculo v){
         listaVehiculo.add(v);
     }

    @Override
    public void addRelacion(Propietario prop, Vehiculo v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean CheckRelacion(int dni, String dom) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAbono(Propietario prop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateIngreso(Propietario prop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateSaldo(Propietario prop, Float saldo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getSaldo(Propietario prop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPrecio(Float precio, Vehiculo.Tipo tipo, Utils.Category categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float getPrecio(Vehiculo.Tipo tipo, Utils.Category categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
