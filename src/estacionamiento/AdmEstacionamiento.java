/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import DataBase.DB;
import DataBase.LocalDB;
import java.util.ArrayList;


/**
 *
 * @author Admin
 */
public class AdmEstacionamiento {
private DB db = new LocalDB();

    public void printMenu() {
        System.out.print("Menu\n\n"
                + "1-Agregar Propietario\n"
                + "2-Agregar Vehiculo\n"
                + "3-Salir\n"
                + "Ingrese opcion: ");
    }

    private boolean runCommand(int option) {
        boolean salir = false;
        switch (option) {
            case 1:
                agregarPropietario();
                break;
            case 2:
                agregarVehiculo();
                break;
            case 3:
                salir = true;
                break;
            default:
                break;
        }
        return salir;
    }

    public void runMenu() {
        int option = 0;
        boolean cmd;
        do {
            printMenu();
            option = Utils.readIntCLI();
            cmd = runCommand(option);
        } while (!cmd);

    }

    private boolean checkUser(int dni) {
        ArrayList<Propietario> listaPropietario; //declaro lista
        boolean result = false;

        listaPropietario = db.getListaPropietarios();

        for (int i = 0; i < listaPropietario.size(); i++) {
            if (dni == listaPropietario.get(i).getDni()) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    private void agregarPropietario() {
        ArrayList<Estacionamiento.Propietario> listaPropietarios;

        listaPropietarios = db.getListaPropietarios();

        System.out.print("Nombre del propietario: ");
        String apellidoNombre = Utils.readStringCLI();
        System.out.print("Ingrese Dni: ");
        int dni = Utils.readIntCLI();
        if (checkUser(dni)) {
            System.out.println("Usuario existente: " + dni);
        } else {
            Propietario prop = new Propietario(apellidoNombre, dni);
            listaPropietarios.add(prop);
        }
    }
    
    private boolean checkVehiculo(String dom, int dni) {
        ArrayList<Vehiculos> listaVehiculo; //declaro lista
        boolean result = false;

        listaVehiculo = db.getListaVehiculos();

        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dni == listaVehiculo.get(i).getPropId()
                    && dom.equals(listaVehiculo.get(i).getDominio())) {
                result = true;
                break;
            }
        }
        return result;
    }

    private void agregarVehiculo() {
        System.out.print("Ingrese el DNI del propietario: ");
        int dni = Utils.readIntCLI();
        
        if (!checkUser(dni)){
        agregarPropietario();
        }
             
        System.out.print("Ingrese el dominio: ");
        String dom = Utils.readStringCLI();
        if (checkVehiculo(dom, dni)){
            System.out.println("Este vehiculo ya ha sido registrado con este usuario: " + dni + " Dominio: " + dom);
            return;
        }
        System.out.print("Ingrese el modelo: ");
        String mod = Utils.readStringCLI();
        System.out.print("Ingrese la marca: ");
        String mar = Utils.readStringCLI();
        System.out.print("Ingrese el tipo de (1. Auto - 2. Moto): ");
        int tipo = Utils.readIntCLI();
        cargaVehiculos(mod, mar, dom, tipo, dni);
    }

    //private String buscarPropietario(){
    // String propietario;
    //   return propietario;
    //}
    public void cargaVehiculos(String mod, String mar, String dom, int tipo, int propId) {
        ArrayList<Estacionamiento.Vehiculos> listaVehiculos;
        Vehiculos V = null;

        listaVehiculos = db.getListaVehiculos(); 

        switch (tipo) {
            case 1:
                V = new Autos(mod, mar, dom, propId);
                break;
            case 2:
                V = new Motos(mod, mar, dom, propId);
                break;
        }
        listaVehiculos.add(V);
    }

}
