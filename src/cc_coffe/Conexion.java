/*
 * Universidad Politecnica de San Luis Potosí
 * Programación III
 * Programación Orientada a Objetos
 */

package cc_coffe;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * @author Saul Cervantes Candia
 * @author 177927@upslp.edu.mx
 * @version 1.0
 */
public class Conexion {
    private Connection con;
    public Connection Conexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coffee","root","");
            System.out.println("OK :)");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error"+e);
        }
        return con;
    }
}
