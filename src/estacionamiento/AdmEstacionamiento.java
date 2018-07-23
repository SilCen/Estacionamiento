/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import DataBase.DB;
import DataBase.LocalDB;
import Estacionamiento.Vehiculo.Tipo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdmEstacionamiento {
private DB db = new LocalDB();
int posicionDni;

    public void printMenu() {
        System.out.print("Menu\n\n"
                + "1-Agregar Propietario\n"
                + "2-Agregar Vehiculo\n"
                + "3-AB de Abono\n"
                + "4-Salir\n"
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
                abAbono();
                break;
            case 4:
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
        listaPropietario = db.getListaPropietario();

        boolean result = false;
        for (int i = 0; i < listaPropietario.size(); i++) {
            if (dni == listaPropietario.get(i).getDni()) {
                posicionDni = i;
                result = true;
                break;
            }
        }
        return result;
    }
    
    private void agregarPropietario() {
        System.out.print("Ingrese DNI: ");
        int dni = Utils.readIntCLI();
        agregarDniProp(dni);
    }
    private void agregarDniProp(int dni) {
        ArrayList<Estacionamiento.Propietario> listaPropietario;
        listaPropietario = db.getListaPropietario();

        if (checkUser(dni)) {
            System.out.println("Usuario existente: " + dni);
        } else {
            System.out.print("Nombre del propietario: ");
            String apellidoNombre = Utils.readStringCLI();

            Propietario prop = new Propietario(apellidoNombre, dni);
            listaPropietario.add(prop);
        }
    }
    
    private boolean checkVehiculo(String dom, int dni) {
        ArrayList<Vehiculo> listaVehiculo; //declaro lista
        boolean result = false;

        listaVehiculo = db.getListaVehiculo();

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

            int tipoNum = 0;
            int cont = 0;
            do {
                cont++;
                tipoNum = Utils.readIntCLI();
                if (tipoNum <= Tipo.values().length) {
                    break;
                }
                System.out.println("Ingreso incorrecto");
            } while (cont < 3);

            Tipo tipo = null;
            switch (tipoNum) {
                case 1:
                    tipo = Tipo.AUTO;
                    break;

                case 2:
                    tipo = Tipo.MOTO;
                    break;

                default:
                    return;
            }
            cargaVehiculo(mod, mar, dom, tipo, dni);
            System.out.println("La registracion del vehiculo fue exitosa");
        } else {
            Vehiculo v = getVehiculo(dom);
            cargaVehiculo(v.getModelo(), v.getMarca(), dom, v.getTipo(), dni);
            System.out.println("Se asigno el vehiculo con dominio: " + dom + " a: " + dni);
        }
    }

    public Vehiculo getVehiculo(String dom){
    ArrayList<Estacionamiento.Vehiculo> listaVehiculo;
    Vehiculo v = null;
    listaVehiculo = db.getListaVehiculo();
        
        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dom.equals(listaVehiculo.get(i).getDominio())){
                v = listaVehiculo.get(i);
                break;
            }
            }
        return v;
    }
            
    public boolean checkDominio(String dom) {
        ArrayList<Estacionamiento.Vehiculo> listaVehiculo;
        boolean result = false;

        listaVehiculo = db.getListaVehiculo();

        for (int i = 0; i < listaVehiculo.size(); i++) {
            if (dom.equals(listaVehiculo.get(i).getDominio())) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    public void cargaVehiculo(String mod, String mar, String dom, Tipo coso, int propId) {
        ArrayList<Estacionamiento.Vehiculo> listaVehiculo;
        Vehiculo V = null;

        listaVehiculo = db.getListaVehiculo(); 

        switch (coso) {
            case AUTO:
                V = new Auto(mod, mar, dom, propId);
                break;
            case MOTO:
                V = new Moto(mod, mar, dom, propId);
                break;
        }
        listaVehiculo.add(V);
    }
    
    public void abAbono(){
        
        ArrayList<Estacionamiento.Propietario> listaPropietario;
  
        listaPropietario = db.getListaPropietario();
        System.out.print("Ingrese DNI: ");
        int dni = Utils.readIntCLI();
        if (checkUser(dni)) {
                 System.out.println("Desea activar el abono?(S/N): ");
                 boolean valorAbono;
                 if(listaPropietario.get(posicionDni).isAbono() == false){
                    listaPropietario.get(posicionDni).setAbono(true);
                    valorAbono = true;
                    System.out.println("Abono activado " + valorAbono);
                 }else{
                     
                 }
        }
    }

}
