/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;

import Estacionamiento.Auto;
import Estacionamiento.Moto;
import Estacionamiento.Propietario;
import Estacionamiento.Utils;
import Estacionamiento.Vehiculo;
import Estacionamiento.Vehiculo.Tipo;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Renzo-PC
 */
public class MySqlConnector2 implements DB {

    private static Connection conn = null;
    private static int connections;
    private static String user = "root";
    private static String password = "root";

    public static void Init() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://localhost/estacionamiento?"
                        + "user=" + user
                        + "&password=" + password
                        + "&serverTimezone=UTC");

            } catch (SQLException ex) {
                // handle any errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }

        }
        connections++;
    }

    public static void Close() {
        connections--;
        if (connections <= 0) {
            connections = 0;
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(MySqlConnector2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static ResultSet execute(String query) {
// assume that conn is an already created JDBC connection (see previous examples)

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            //rs = stmt.executeQuery(query);
            // or alternatively, if you don't know ahead of time that
            // the query will be a SELECT...

            if (stmt.execute(query)) {
                rs = stmt.getResultSet();
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed
        }
        return rs;
    }

    @Override
    public ArrayList<Vehiculo> getVehiculo(String dom) {
        ArrayList<Vehiculo> list = new ArrayList();
        ResultSet rs;
        Init();

        rs = execute("SELECT * FROM vehiculo "
                + "where dominio = '" + dom + "'");

        try {
            while (rs.next()) {
                Vehiculo V = null;
                Tipo coso = Tipo.values()[rs.getInt("Tipo")];
                String mod = rs.getString("modelo");
                String mar = rs.getString("marca");

                switch (coso) {
                    case AUTO:
                        V = new Auto(mod, mar, dom);
                        break;
                    case MOTO:
                        V = new Moto(mod, mar, dom);
                        break;
                }

                list.add(V);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnector2.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Close();
        return list;
    }

    @Override
    public Propietario getUser(int dni) {
        Propietario prop = null;
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd

        rs = execute("SELECT * FROM propietario "
                + "where idPropietario = " + dni);

        try {
            while (rs.next()) {
                String ApeNomProp = rs.getString("ApeNom");
                Boolean abono = rs.getBoolean("abono");
                Date ultimoIngreso = rs.getDate("ultimoIngreso");
                prop = new Propietario(ApeNomProp, dni);
                prop.setAbono(abono);
                if (ultimoIngreso != null) {
                    Calendar calen = Calendar.getInstance();
                    calen.setTime(ultimoIngreso); //aqui transformo de date a calen
                    prop.setUltimoIngreso(calen); //seteo el ultimo ingreso
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnector2.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Close();
        return prop;
    }

    //@Override
    //public Propietario getUser(int dni) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
    @Override
    public void addPropietario(Propietario prop) {
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String str = "insert into propietario (idPropietario, ApeNom, abono) values(%d, '%s', %d)";
        // Concatenation of two strings
        String datoprop = String.format(str, prop.getDni(), prop.getApeNomPropietario(), (prop.isAbono()) ? 1 : 0);
        rs = execute(datoprop);
        //Close();    
    }

    @Override
    public void addVehiculo(Vehiculo v) {
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String str = "insert into vehiculo (modelo, dominio, marca, Tipo) values('%s', '%s', '%s', %d)";
        // Concatenation of two strings
        String datovehiculo = String.format(str, v.getModelo(), v.getDominio(), v.getMarca(), v.getTipo().ordinal());
        rs = execute(datovehiculo);
        //Close();    
    }

    @Override
    public void addRelacion(Propietario prop, Vehiculo v) {
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "insert into propxv (id_Prop, id_Vehiculo) values(%d, '%s')";
        // Concatenation of two strings
        String datorelacion = String.format(relacion, prop.getDni(), v.getDominio());
        rs = execute(datorelacion);
    }

    @Override
    public boolean CheckRelacion(int dni, String dom) {
        boolean exist = false;
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String consulta = "SELECT * FROM propxv where id_Prop = %d and id_Vehiculo = '%s'";
        rs = execute(String.format(consulta, dni, dom));

        try {
            exist = rs.next();

        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnector2.class.getName()).log(Level.SEVERE, null, ex);
        }

        //Close();
        return exist;
    }

    @Override
    public void updateAbono(Propietario prop) {
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "UPDATE propietario SET abono = %d WHERE idPropietario = %d";
        // Concatenation of two strings
        String datorelacion = String.format(relacion, (prop.isAbono()) ? 1 : 0, prop.getDni());
        rs = execute(datorelacion);
        
        relacion = "INSERT INTO abono (propid, saldo) VALUES (%d, 0)";
        // Concatenation of two strings
        datorelacion = String.format(relacion, prop.getDni());
        rs = execute(datorelacion);
    }

    @Override
    public void updateIngreso(Propietario prop) {
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "UPDATE propietario SET ultimoIngreso = '%s' WHERE idPropietario = %d";
        // Concatenation of two strings
        String date = prop.getUltimoIngreso().getTime().toString();

        String datorelacion = String.format(relacion, date, prop.getDni());
        // rs = execute(datorelacion);
    }

    @Override
    public void updateSaldo(Propietario prop, float saldo) {
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "UPDATE abono SET saldo = '%.2f' WHERE propid = %d";
        // Concatenation of two strings
        String datorelacion = String.format(relacion, saldo, prop.getDni());
        rs = execute(datorelacion);
    }

    @Override
    public float getSaldo(Propietario prop) {
        ResultSet rs;
        float saldo = 0;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "SELECT saldo FROM abono WHERE propid = %d";
        // Concatenation of two strings
        String datorelacion = String.format(relacion, prop.getDni());
        rs = execute(datorelacion);

        try {
            if (rs.next()) {
                saldo = rs.getFloat("saldo");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnector2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return saldo;
    }

    @Override
    public void addPrecio(Float precio, Tipo tipo, Utils.Category categoria) {
        //
        ResultSet rs;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "INSERT INTO precio(precio, tipo_vehic, categoria) VALUES ('%s', %d, %d)";
        // Concatenation of two strings
        String datorelacion = String.format(relacion, precio.toString(), tipo.ordinal(), categoria.ordinal());
        rs = execute(datorelacion);    }

    @Override
    public float getPrecio(Tipo tipo, Utils.Category categoria) {
        ResultSet rs;
        float precio = 0;
        Init(); //es la funcion que inicializa la conex a la bd
        String relacion = "SELECT precio FROM precio WHERE tipo_vehic = %d and categoria = %d";
        // Concatenation of two strings
        String datorelacion = String.format(relacion, tipo.ordinal(), categoria.ordinal());
        rs = execute(datorelacion);

        try {
            if (rs.next()) {
                precio = rs.getFloat("precio");
            }

        } catch (SQLException ex) {
            Logger.getLogger(MySqlConnector2.class.getName()).log(Level.SEVERE, null, ex);
        }
        return precio;
    }
}
