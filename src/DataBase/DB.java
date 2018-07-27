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
    abstract public ArrayList<Vehiculo>getListaVehiculo();
    abstract public ArrayList<Propietario>getListaPropietario();
    abstract public boolean checkDominio(String dom);
    abstract public Vehiculo getVehiculo(String dom);
    abstract public Propietario getUser(int dni);
    abstract public void addPropietario(Propietario prop);
    abstract public void addVehiculo(Vehiculo v);
    }


    
