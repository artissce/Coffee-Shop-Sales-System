/*
 * Universidad Politecnica de San Luis Potosi
 * Programacion III
 * Programacion Orientada a Objetos

 */

package cc_coffe;
import java.sql.*;

/**
 * Esta es la clase principal y esta en el paquete cc_coffe
 * @author Ana Karen Cuenca Esquivel
 * @author Saúl Cervantes Candia 
 */
public class CC_Coffe {
/**
 * En el main de esta clase crea dos objetos, uno para la conexion y otro para el login {@link Login#Login(java.sql.Connection)}
 * @param args 
 */
    public static void main(String[] args){
        Conexion obj = new Conexion();
        Connection con = obj.Conexion();
        new Login(con).setVisible(true);
    }
}
