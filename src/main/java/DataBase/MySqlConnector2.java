/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBase;


import Estacionamiento.Auto;
import Estacionamiento.Moto;
import Estacionamiento.Propietario;
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
/**
 *
 * @author Renzo-PC
 */
public class MySqlConnector2 implements DB{

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
            connections = 0 ;
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
        
        rs = execute("SELECT * FROM vehiculo " +
                     "where dominio = '"+ dom +"'");
        
        try {
            while (rs.next()) {
                Vehiculo V= null;
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
            
            
        Close();
        return list;
    }

    @Override
    public Propietario getUser(int dni) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addPropietario(Propietario prop) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addVehiculo(Vehiculo v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
