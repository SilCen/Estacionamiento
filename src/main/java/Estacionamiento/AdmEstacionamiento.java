/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Estacionamiento;

import DataBase.DB;
import DataBase.MySqlConnector2;
import Estacionamiento.Vehiculo.Tipo;
import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class AdmEstacionamiento {

    private DB db = new MySqlConnector2();

    public void printMenu() {
        System.out.print("Menu\n\n"
                + "1-Agregar Propietario\n"
                + "2-Agregar Vehiculo\n"
                + "3-AB de Abono\n"
                + "4-Cobrar Estacionamiento\n"
                + "5-Salir\n"
                + "Ingrese opcion: ");
    }

    private boolean runCommand(int option) {
        
        boolean salir = false;
        switch (option) {
            case 1:
                AdmEstacionamiento.this.agregarPropietario(); 
                break;
            case 2:
                agregarVehiculo();
                break;
            case 3:
                abAbono();
                break;
            case 4:
                cobrarEstacionamiento();
                break;
            case 5:
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
    
    private void agregarPropietario() {
        System.out.print("Ingrese DNI: ");  
        int dni = Utils.readIntCLI(); 
        agregarPropietario(dni);
    }
    
    private Propietario agregarPropietario(int dni) {
        Propietario prop = db.getUser(dni);
        if (prop != null) {
            System.out.println("Usuario existente: " + dni);
        } else {
            System.out.print("Nombre del propietario: ");
            String apellidoNombre = Utils.readStringCLI();

            prop = new Propietario(apellidoNombre, dni);
            db.addPropietario(prop);
        }
        return prop;
    }

   private void agregarVehiculo() {
        System.out.println("Ingrese el DNI del propietario: ");
        int dni = Utils.readIntCLI();
        Propietario prop = db.getUser(dni);
        if ( prop == null) {
            System.out.println("El propietario no existe, debe ingresarlo");
            prop = agregarPropietario(dni);
        }

        System.out.println("Ingrese el dominio: ");
        String dom = Utils.readStringCLI();

        if (db.CheckRelacion(dni, dom)) {
            System.out.println("Este vehiculo ya ha sido registrado con este propietario (DNI): " + dni + " Dominio: " + dom);
            return;
        }
        altaDomDni(dom, prop);
    }

    public void altaDomDni(String dom, Propietario prop) {
        ArrayList<Vehiculo> lista = db.getVehiculo(dom);
        
        if (lista.isEmpty()) {
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
            cargaVehiculo(mod, mar, dom, tipo, prop);
            
            System.out.println("La registracion del vehiculo fue exitosa");
        } else {
            Vehiculo v = lista.get(0);
            db.addRelacion(prop, v);
            System.out.println("Se asigno el vehiculo con dominio: " + dom + " a: " + prop.getDni());
        }
    }

    public void cargaVehiculo(String mod, String mar, String dom, Tipo coso, Propietario prop) {
     
        Vehiculo V = null;

        switch (coso) {
            case AUTO:
                V = new Auto(mod, mar, dom);
                break;
            case MOTO:
                V = new Moto(mod, mar, dom);
                break;
        }
        db.addVehiculo(V);
        db.addRelacion(prop, V);
    }

    public void abAbono() {
        Propietario prop;
        System.out.print("Ingrese DNI: ");
        int dni = Utils.readIntCLI();
        prop = db.getUser(dni);
        if (prop == null) {
            System.out.println("El propietario no existe ");
            return;
        }
        boolean isAbono = prop.isAbono();

        if (isAbono) {
            System.out.println("El Propietario tiene abono activado, desea desactivarlo? (Si/No)");
        } else {
            System.out.println("El Propietario tiene abono desactivado, desea activarlo? (Si/No)");
        }
        if (Utils.readStringCLI().equals("Si")) {
            if (isAbono) {
                isAbono = false;
                System.out.println("Abono desactivado ");
            } else {
                isAbono = true;
                System.out.println("Abono activado ");
            }

            prop.setAbono(isAbono);
            db.updateAbono(prop);
        }
   }

    public void cobrarEstacionamiento() {
        ArrayList<Vehiculo> lista;
        
        System.out.print("Ingrese DNI: ");
        int dni = Utils.readIntCLI();
        Propietario prop = db.getUser(dni);
      
        if (prop != null) {
            System.out.println("Usuario existente: " + dni);
            if(prop.ingresoPropietario()){
                System.out.println("El propietario ya ingreso");
                return;
            }
            boolean isAbono = prop.isAbono();
            System.out.print("Ingrese Dominio: ");
            String dom = Utils.readStringCLI();
            boolean existeDom = db.CheckRelacion(dni, dom);
            if(!existeDom){
                altaDomDni(dom,prop);
            }
            lista = db.getVehiculo(dom);
            Vehiculo v = lista.get(0);          
            Tipo devTipo = v.getTipo();
            db.updateIngreso(prop);
            if (isAbono) {
                System.out.println("Cobramos precio con Abono de tipo: "+ devTipo);
            }
            else{
                System.out.println("Cobramos precio sin Abono de tipo: "+ devTipo);
            }
        }
        else {
            System.out.println("Usuario no existte");
       }
    }
}
