/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inicio;

import DataBase.DB;
import DataBase.MySqlConnector2;
import DataBase.VehiculoJpaController;
import Estacionamiento.AdmEstacionamiento;
import Estacionamiento.Auto;
import Estacionamiento.Moto;
import Estacionamiento.Vehiculo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class Main {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception, Throwable {
        /*
        Vehiculo v = new Moto("modelo1", "Marca1", "A");
        Vehiculo v2 = new Auto("modelo1", "Marca1", "B");
        
        VehiculoJpaController vjc = new VehiculoJpaController();
        vjc.add(v);
        vjc.add(v2);
        vjc.finalize();
        
        */
        
        DB db = new MySqlConnector2();
        ArrayList<Vehiculo> list = db.getVehiculo("A");
        System.out.println("ver2 " + list.get(0).getMarca());
        
        
    }
      //AdmEstacionamiento consola = new AdmEstacionamiento();
      //consola.runMenu();
    }    
