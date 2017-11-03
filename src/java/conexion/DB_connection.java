/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Gersain
 */
public class DB_connection {
    
    public static void main(String[] args){
        DB_connection obl_con = new DB_connection();
        
        System.out.println(obl_con.get_connection());
        
    }
    
    public Connection get_connection(){
        
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/crud_horisoes","root","");
        } catch (Exception e) {
            System.out.println(e);
        }
        return connection;
    }
    
}
