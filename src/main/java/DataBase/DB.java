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
public abstract interface DB {
    public ArrayList<Vehiculo> getVehiculo(String dom);
    public Propietario getUser(int dni);
    public void addPropietario(Propietario prop);
    public void addVehiculo(Vehiculo v);
    public void addRelacion(Propietario prop, Vehiculo v);
    public boolean CheckRelacion(int dni, String dom);
    public void updateAbono(Propietario prop);
    public void updateIngreso(Propietario prop);
    public void updateSaldo(Propietario prop, Float saldo);
    public float getSaldo(Propietario prop);
    public void addPrecio(Float precio, Vehiculo.Tipo tipo, Utils.Category categoria);
    public float getPrecio(Vehiculo.Tipo tipo, Utils.Category categoria);
    }


    
