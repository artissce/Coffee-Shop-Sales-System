/*
 * Universidad Politecnica de San Luis Potosi
 * Programacion III Java
 * Programacion Orientada a Objetos
 */

package prueba;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
/**
 *
 * @author Ana Karen Cuenca Esquivel
 * @Ana Karen Cuenca Esquivel
 */
public class Pedidos {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:mysql://localhost:3306/coffee";
        String username = "root";
        String password = "";
        Connection connection = DriverManager.getConnection(url, username, password);
        // Execute a query to select the data you want to display in the combo box.
        String sql = "SELECT mesa FROM pedido WHERE estado='Aceptado';";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        // Add the results of the query to the combo box.
        JComboBox comboBox = new JComboBox();
        try {
            while (resultSet.next()) {
                comboBox.addItem(resultSet.getString("mesa"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Set the selected item in the combo box.
        comboBox.setSelectedIndex(0);

        // Listen for events from the combo box.
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println("Selected item: " + comboBox.getSelectedItem());
                } catch (NullPointerException ex) {
                    System.out.println("No item selected");
                }
            }
        });

        // Add the combo box to the JFrame object.
        JFrame frame = new JFrame("ComboBox Demo");
        frame.add(comboBox);

        // Set the size of the JFrame object.
        frame.setSize(300, 200);

        // Set the location of the JFrame object.
        frame.setLocationRelativeTo(null);

        // Dispose the JFrame object when it is closed.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the JFrame object.
        frame.setVisible(true);

        // Close the connection.
        connection.close();
    }
}
