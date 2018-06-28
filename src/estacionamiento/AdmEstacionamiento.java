/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamiento;

import DataBase.DB;
import DataBase.LocalDB;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdmEstacionamiento {
    
    public void CargaVehiculos(String mod, String mar, String dom, int tipo, int IDProp){
        ArrayList<Estacionamiento.Vehiculos> listaVehiculos;
        Vehiculos V=null;
        switch (tipo){
            case 1: V = new Autos(mod, mar, dom); 
                    break;
            case 2: V = new Motos(mod, mar, dom);
                    break;
        }
        DB db = new LocalDB();
        listaVehiculos = db.getListaVehiculos();
        listaVehiculos.add(V);
    }
    
    
}
