/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import DataBase.DB;
import DataBase.LocalDB;
import Estacionamiento.Vehiculos.Tipo;
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
        //ArrayList<Estacionamiento.Propietario> listaPropietarios;

        //listaPropietarios = db.getListaPropietarios();

        System.out.print("Ingrese DNI: ");
        int dni = Utils.readIntCLI();
        /*if (checkUser(dni)) {
            System.out.println("Usuario existente: " + dni);
        } else {
            System.out.print("Nombre del propietario: ");
            String apellidoNombre = Utils.readStringCLI();

            Propietario prop = new Propietario(apellidoNombre, dni);
            listaPropietarios.add(prop);
        }*/
        agregarDniProp(dni);
    }
    private void agregarDniProp(int dni) {
        ArrayList<Estacionamiento.Propietario> listaPropietarios;

        listaPropietarios = db.getListaPropietarios();

        if (checkUser(dni)) {
            System.out.println("Usuario existente: " + dni);
        } else {
            System.out.print("Nombre del propietario: ");
            String apellidoNombre = Utils.readStringCLI();

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
        System.out.println("Ingrese el DNI del propietario: ");
        int dni = Utils.readIntCLI();

        if (!checkUser(dni)) {
            System.out.println("El propietario no existe, debe ingresarlo");
            agregarDniProp(dni);
        }

        System.out.println("Ingrese el dominio: ");
        String dom = Utils.readStringCLI();

        if (checkVehiculo(dom, dni)) {
            System.out.println("Este vehiculo ya ha sido registrado con este propietario (DNI): " + dni + " Dominio: " + dom);
            return;
        }

        if (!checkDominio(dom)) {
            System.out.println("Ingrese el modelo: ");
            String mod = Utils.readStringCLI();
            System.out.println("Ingrese la marca: ");
            String mar = Utils.readStringCLI();
            System.out.println("Ingrese el tipo de (1. Auto - 2. Moto): ");
            int tipoNum = Utils.readIntCLI();
            Tipo tipo=null;
            switch (tipoNum) {
                case 1:
                    tipo = Tipo.AUTO;
                    break;

                case 2:
                    tipo = Tipo.MOTO;
                    break;

            }
        
        
        cargaVehiculos(mod, mar, dom, tipo, dni);
        System.out.println("La registracion del vehiculo fue exitosa");
        }
        else
        {
         Vehiculos v = getVehiculo(dom);
         cargaVehiculos(v.getModelo(),v.getMarca(),dom,v.getTipo(),dni);
         System.out.println("Se asigno el vehiculo con dominio: " + dom +" a: " + dni);
        }
        
        }
    
    public Vehiculos getVehiculo(String dom){
    ArrayList<Estacionamiento.Vehiculos> listaVehiculo;
    Vehiculos v = null;
    listaVehiculo = db.getListaVehiculos();
        
        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dom.equals(listaVehiculo.get(i).getDominio())){
                v = listaVehiculo.get(i);
                break;
            }
            }
        return v;
    }
            
    public boolean checkDominio(String dom){
     ArrayList<Estacionamiento.Vehiculos> listaVehiculo;
        boolean result= false;
        
        listaVehiculo = db.getListaVehiculos();
        
        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dom.equals(listaVehiculo.get(i).getDominio())){
                result = true;
                break;
            }
            }
        return result;
    }
    
    public void cargaVehiculos(String mod, String mar, String dom, Tipo coso, int propId) {
        ArrayList<Estacionamiento.Vehiculos> listaVehiculos;
        Vehiculos V = null;

        listaVehiculos = db.getListaVehiculos(); 

        switch (coso) {
            case AUTO:
                V = new Autos(mod, mar, dom, propId);
                break;
            case MOTO:
                V = new Motos(mod, mar, dom, propId);
                break;
        }
        listaVehiculos.add(V);
    }

}
