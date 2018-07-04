/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estacionamiento;

import DataBase.DB;
import DataBase.LocalDB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class AdmEstacionamiento {
       
    public void printMenu(){
        System.out.print("Menu\n\n"
                + "1-Agregar Propietario\n"
                + "2-Agregar Vehiculo\n"
                + "3-Salir\n"
                + "Ingrese opcion: ");
    }
    private boolean runCommand(int option){
        boolean salir = false;
        switch (option){
            case 1:
                agregarPropietario();
                break;
            case 2:
                agregarVehiculo();
                break;
            case 3:
                break;    
            default:
                break;
        }
        return salir;
    }
    
    public void runMenu(){
        int option =0;
        boolean cmd;
        do {printMenu();
        option = Utils.readIntCLI();
        cmd = runCommand(option);
        }while (!cmd);
        
    }
       
    private void agregarPropietario(){
        System.out.print("Nombre del propietario: ");
        String apellidoNombre =Utils.readStringCLI();
        System.out.print("Ingrese Dni: ");
        int dni = Utils.readIntCLI(); 
        Propietario prop = new Propietario (apellidoNombre, dni); 
        //cine= admCines.addCine(nombre);
    }
    
    private void agregarVehiculo(){
    }
    
    public void cargaVehiculos(String mod, String mar, String dom, int tipo, int propId){
        ArrayList<Estacionamiento.Vehiculos> listaVehiculos;
        Vehiculos V = null;
        
        DB db = new LocalDB();
        listaVehiculos = db.getListaVehiculos();
        
        switch (tipo){
            case 1: V = new Autos(mod, mar, dom, propId); 
                    break;
            case 2: V = new Motos(mod, mar, dom, propId);
                    break;
        }
        listaVehiculos.add(V);
    }
    
    
}
