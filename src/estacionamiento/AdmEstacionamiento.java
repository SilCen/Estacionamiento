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
                agregarPropietario();
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

    private boolean checkUser(int dni) {
        return (db.getUser(dni) != null);
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
        altaDomDni(dom, dni);
    }

    public void altaDomDni(String dom, int dni) {
        if (!db.checkDominio(dom)) {
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
            Vehiculo v = db.getVehiculo(dom);
            cargaVehiculo(v.getModelo(), v.getMarca(), dom, v.getTipo(), dni);
            System.out.println("Se asigno el vehiculo con dominio: " + dom + " a: " + dni);
        }
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
                prop.setAbono(false);
                System.out.println("Abono desactivado ");
            } else {
                prop.setAbono(true);
                System.out.println("Abono activado ");
            }
        }
   }

    public void cobrarEstacionamiento() {
        System.out.print("Ingrese DNI: ");
        int dni = Utils.readIntCLI();
        Propietario prop = db.getUser(dni);
      
        if (prop != null) {
            System.out.println("Usuario existente: " + dni);
            boolean isAbono = prop.isAbono();
            System.out.print("Ingrese Dominio: ");
            String dom = Utils.readStringCLI();
            boolean existeDom = checkVehiculo(dom, dni);
            if(!existeDom){
                altaDomDni(dom,dni);
            }
            Vehiculo v = db.getVehiculo(dom);          
            Tipo devTipo = v.getTipo();
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
