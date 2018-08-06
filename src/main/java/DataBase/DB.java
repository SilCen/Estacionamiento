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
public abstract class DB {
    abstract public ArrayList<Vehiculo> getVehiculo(String dom);
    abstract public Propietario getUser(int dni);
    abstract public void addPropietario(Propietario prop);
    abstract public void addVehiculo(Vehiculo v);
    }


    
