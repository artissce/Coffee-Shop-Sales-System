/*
 * Universidad Politecnica de San Luis Potosí
 * Programación III
 * Programación Orientada a Objetos
 */

package cc_coffe;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;


/*
 * @author Saul Cervantes Candia
 * @author 177927@upslp.edu.mx
 * @version 1.0
 */
public class Entidad {
    private final Connection con;
    private final ArrayList < ArrayList<Object> > lista = new ArrayList < >();
    
    private final ArrayList<Object> MESA = new ArrayList();
    private String mesa[];
    
    private final ArrayList<Object> INGRE = new ArrayList();
    private String ingre[];
    
    private final ArrayList<Object> ID = new ArrayList();
    private String id[];
    
    public Entidad(Connection con) {
        this.con = con;
    }
    
    public void altaPedido(int mesa, String nombreC, int cantP, int cantB, int id_platillo, int id_bebida, String fecha, String estado){
        try{
            Statement sts = con.createStatement();
            sts.addBatch("INSERT INTO pedido (mesa, nombreC, cantP, cantB, id_platillo, id_bebida, fecha_pedido, estado) VALUES ( "+mesa+",'"+nombreC+"',"+cantP+","+cantB+""
                    + ","+id_platillo+","+id_bebida+",'"+fecha+"','"+estado+"');");
            sts.executeBatch();
        }catch(SQLException e){
            System.out.println("Error "+e);
        }
    }
    
    public void altaPedidoId(int id, int mesa, String nombreC, int cantP, int cantB, int id_platillo, int id_bebida, String fecha, String estado){
        try{
            Statement sts = con.createStatement();
            sts.addBatch("INSERT INTO pedido (id, mesa, nombreC, cantP, cantB, id_platillo, id_bebida, fecha_pedido, estado) VALUES ( "+id+","+mesa+",'"+nombreC+"',"+cantP+","+cantB+""
                    + ","+id_platillo+","+id_bebida+",'"+fecha+"','"+estado+"');");
            sts.executeBatch();
        }catch(SQLException e){
            System.out.println("Error "+e);
        }
    }
    
    public void consultaPedidos(){
        int reg = 0;
        int x,y,cant;
        try{
            getLista().clear();
            Statement sts = con.createStatement();
            sts.execute("SELECT id,nombreC,cantP,cantB,estado FROM pedido");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                getLista().add(new ArrayList<>());
                getLista().get(reg).add(rs.getInt("id"));
                getLista().get(reg).add(rs.getString("nombreC"));
                x=rs.getInt("cantP");
                y=rs.getInt("cantB");
                cant=x+y;
                getLista().get(reg).add(cant);
                getLista().get(reg).add(rs.getString("estado"));
                reg++;
            }
            Iterator miIterator = getLista().iterator();
            while(miIterator.hasNext()){
                System.out.println(miIterator.next()+"\t");
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    public boolean dispoMesa(int mesa){
        try{
            Statement sts = con.createStatement();
            sts.execute("SELECT mesa FROM pedido WHERE estado='Finalizado';");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                if(mesa == rs.getInt("mesa")){
                    return true; // la mesa  está disponible
                }
            }
            return false; // la mesa no está disponible
        }catch(SQLException ex){
            System.out.println(ex.getNextException());      //catch si es una cadena o no
            return false; // no se pudo verificar la disponibilidad de la mesa
        }catch(NumberFormatException ex){
            System.out.println(ex);
            return false; // no se pudo verificar la disponibilidad de la mesa
        }
        
    }
    
    public String[] consultaMesa(int mesa){
        String resul[] = new String[2];
        try{
            Statement sts = con.createStatement();
            sts.execute("Select * from pedido where mesa='"+mesa+"'");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                resul[0] = rs.getString("nombreC");
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());      //catch si es una cadena o no
        }catch(NumberFormatException ex){
            System.out.println(ex);
        }
        return resul;
    }
    
    public void consultaIngredientes(){
        int reg = 0;
        try{
            getLista().clear();
            Statement sts = con.createStatement();
            sts.execute("SELECT * FROM ingredientes");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                getLista().add(new ArrayList<>());
                getLista().get(reg).add(rs.getInt("id_ingrediente"));
                getLista().get(reg).add(rs.getString("nombre_ingrediente"));
                getLista().get(reg).add(rs.getFloat("cant"));
                reg++;
            }
            Iterator miIterator = getLista().iterator();
            while(miIterator.hasNext()){
                System.out.println(miIterator.next()+"\t");
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void obtenerMesa(){
        try{
            int cont=0;
            Statement sts = con.createStatement();
            sts.execute("SELECT * FROM pedido");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                MESA.add(rs.getInt("Id"));
                cont++;
            }
            mesa=new String[cont];
            for (int mes = 0; mes < cont; mes++) {
                mesa[mes] = MESA.get(mes).toString();
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void modificaPedido(int id, int estado){
        try{
            Statement sts = con.createStatement();
            if(estado==1){
                sts.executeUpdate("UPDATE pedido SET estado = 'Finalizado' WHERE id="+id+" ");
            }else{
                sts.executeUpdate("UPDATE pedido SET estado = 'Cancelado' WHERE id="+id+" "); 
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void modificaIngre(int id, int ope, float cant){
        try{
            Statement sts = con.createStatement();
            if(ope==1){
                sts.executeUpdate("UPDATE ingredientes SET cant = cant+'"+cant+"' WHERE id_ingrediente="+id+" ");
            }else{
                sts.executeUpdate("UPDATE ingredientes SET cant = cant-'"+cant+"' WHERE id_ingrediente="+id+" "); 
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public void obtenerIngre(){
        try{
            int cont=0;
            Statement sts = con.createStatement();
            sts.execute("SELECT * FROM ingredientes");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                INGRE.add(rs.getInt("Id_ingrediente"));
                cont++;
            }
            ingre=new String[cont];
            for (int ing = 0; ing < cont; ing++) {
                ingre[ing] = INGRE.get(ing).toString();
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public final DefaultTableModel actualizaTabla(){//pedidos
        DefaultTableModel modelo = new DefaultTableModel();
        Object datos[] = new Object[4];
        modelo.addColumn("Id");
        modelo.addColumn("NombreCliente");
        modelo.addColumn("Cantidad");
        modelo.addColumn("Estado");
        
        consultaPedidos();
        for (int j = 0; j < getLista().size(); j++) {
            datos[0] = getLista().get(j).get(0);
            datos[1] = getLista().get(j).get(1);
            datos[2] = getLista().get(j).get(2);
            datos[3] = getLista().get(j).get(3);
            modelo.addRow(datos);
        }
        return modelo;
    }
    
    public final DefaultTableModel actualizaTabla2(){//ingredientes
        DefaultTableModel modelo = new DefaultTableModel();
        Object datos[] = new Object[3];
        modelo.addColumn("ID del Ingrediente");
        modelo.addColumn("Nombre de Ingrediente");
        modelo.addColumn("Cantidad");
        
        consultaIngredientes();
        for (int j = 0; j < getLista().size(); j++) {
            datos[0] = getLista().get(j).get(0);
            datos[1] = getLista().get(j).get(1);
            datos[2] = getLista().get(j).get(2);
            modelo.addRow(datos);
        }
        return modelo;
    }
    
    public void obtenerId(){
        try{
            int cont=0;
            Statement sts = con.createStatement();
            sts.execute("SELECT * FROM pedido");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                ID.add(rs.getInt("Id"));
                cont++;
            }
            id=new String[cont];
            for (int id2 = 0; id2 < cont; id2++) {
                id[id2] = ID.get(id2).toString();
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public String[] consultaId(int id){
        String resul[] = new String[3];
        try{
            Statement sts = con.createStatement();
            sts.execute("Select * from pedido where id='"+id+"'");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                resul[0] = rs.getString("nombreC");
                resul[1] = Integer.toString(rs.getInt("mesa"));
                resul[2] = rs.getString("fecha_pedido");
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());      //catch si es una cadena o no
        }catch(NumberFormatException ex){
            System.out.println(ex);
        }
        return resul;
    }
    
    public void consultaPedidoPart(int id, int est){
        int reg = 0;
        int id2=0;
        try{
            getLista().clear();
            Statement sts = con.createStatement();
            sts.execute("SELECT pedido.id_platillo,platillos.nombre_platillo,pedido.cantP FROM pedido INNER JOIN platillos "
                    + "ON platillos.id_platillo=pedido.id_platillo WHERE pedido.id="+id+" ");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                getLista().add(new ArrayList<>());
                getLista().get(reg).add(++id2);
                getLista().get(reg).add(rs.getString("platillos.nombre_platillo"));
                getLista().get(reg).add(rs.getInt("pedido.cantP"));
                reg++;
            }
            sts.execute("SELECT pedido.id_bebida,bebidas.nombre_bebida,pedido.cantB FROM pedido INNER JOIN bebidas "
                    + "ON bebidas.id_bebida=pedido.id_bebida WHERE pedido.id="+id+" ");
            ResultSet rs2 = sts.getResultSet();
            while(rs2.next()){
                getLista().add(new ArrayList<>());
                getLista().get(reg).add(++id2);
                getLista().get(reg).add(rs2.getString("bebidas.nombre_bebida"));
                getLista().get(reg).add(rs2.getInt("pedido.cantB"));
                reg++;
            }
            Iterator miIterator = getLista().iterator();
            while(miIterator.hasNext()){
                System.out.println(miIterator.next()+"\t");
            }
        }catch(SQLException ex){
            System.out.println(ex.getNextException());
        }
    }
    
    public final DefaultTableModel pedidoParticular(int id, int estado){//pedido particular de la mesa
        DefaultTableModel modelo = new DefaultTableModel();
        Object datos[] = new Object[3];
        modelo.addColumn("ID Producto");
        modelo.addColumn("Platillo/Bebida");
        modelo.addColumn("Cantidad");
        
        consultaPedidoPart(id,estado);
        for (int j = 0; j < getLista().size(); j++) {
            datos[0] = getLista().get(j).get(0);
            datos[1] = getLista().get(j).get(1);
            datos[2] = getLista().get(j).get(2);
            modelo.addRow(datos);
        }
        return modelo;
    }
    
    public ArrayList < ArrayList<Object> > getLista(){
        return lista;
    }

    /**
     * @return the mesa
     */
    public String[] getMesa() {
        return mesa;
    }
    
    public String[] getIngre() {
        return ingre;
    }
    
    public String[] getId() {
        return id;
    }
    
    public boolean ingresar(int id, String contra) throws SQLException{
        short t=0;
        Statement s = con.createStatement();
        String query = "SELECT * FROM empleado WHERE (id='"+id+"')AND (contra='"+contra+"') ";
        
        ResultSet a = s.executeQuery(query);
        
        while(a.next()){//para ver si esta en la base de datos
            System.out.println(a.getInt("id"));
            System.out.println(a.getString("contra"));
            t++;
        }
        if(t==1){
            return true;
        }else{
            return false;
        }
    }
    
    public int tipoE(int id) throws SQLException{
        Statement s = con.createStatement();
        String query = "SELECT tipo FROM empleado WHERE (id='"+id+"') ";
        int e=0;
        ResultSet a = s.executeQuery(query);
        while(a.next()){//para ver si esta en la base de datos
            e = a.getInt("tipo");
            
        }
        return e;
    }
    
    public int registro(String nombre, String telefono, int tipo, String contra) throws SQLException{
        Statement s = con.createStatement();
        int e=4;
        String query = "INSERT INTO `empleado` (`contra`, `nombre`, `telefono`, `tipo`) VALUES ('"+contra
                +"', '"+nombre+"', '"+telefono+"', '"+tipo+"')";
        int a = s.executeUpdate(query);
        if(a==0){
            JOptionPane.showMessageDialog(null, "No se realizo el registro:c");
            e=0;
        }else if(a==1){
            JOptionPane.showMessageDialog(null, "Se realizo el registro:D");
            e=1;
        }
        return e;
    }
    
    public void mostrarDatos(int id) throws SQLException{
        Statement s = con.createStatement();
        String query = "SELECT * FROM empleado WHERE (id='"+id+"') ";
        int e=0;
        ResultSet a = s.executeQuery(query);
        while(a.next()){//para ver si esta en la base de datos
            e = a.getInt("tipo");
            
        }
    }
    
    
    public boolean checarIngredientesB (int idBebida) throws SQLException{
        // Obtener los ingredientes de la bebida
        String query = "SELECT id_ingrediente, cantidad FROM bebidas_ingredientes WHERE id_bebida = '"+idBebida+"';";
        PreparedStatement  stmt1 = con.prepareStatement(query);
        //stmt1.setInt(1, idBebida);
        ResultSet rs = stmt1.executeQuery();

        // Verificar la disponibilidad de cada ingrediente
        while (rs.next()) {
            int idIngrediente = rs.getInt("id_ingrediente");
            int cantidadBebida = rs.getInt("cantidad");

            query = "SELECT cant FROM ingredientes WHERE id_ingrediente = '"+idIngrediente+"'";
            stmt1 = con.prepareStatement(query);
            //stmt1.setInt(1, idIngrediente);
            rs = stmt1.executeQuery();

            if (rs.next()) {
                int cantidadIngrediente = rs.getInt("cant");
                if (cantidadIngrediente < cantidadBebida) {
                   JOptionPane.showMessageDialog(null, "No hay suficiente ingrediente para la bebida");
                    return false;
                }/*else{
                    query = "UPDATE ingredientes INNER JOIN bebidas_ingredientes ON "
                            + "ingredientes.id_ingrediente = bebidas_ingredientes.id_ingrediente "
                            + "SET cant = cant - bebidas_ingredientes.cantidad WHERE bebidas_ingredientes.id_bebida = '"+idBebida+"';";
                    stmt1 = con.prepareStatement(query);
                }*/
            } else {
                // El ingrediente no existe en la tabla de ingredientes, el pedido no se puede realizar
                return false;
            }
        }
        
        // Si se llega a este punto, hay suficiente cantidad de todos los ingredientes, el pedido se puede realizar
        return true;

    }
    
    public void reducirIngredienteB(int idBebida) throws SQLException{
        Statement sts = con.createStatement();
        sts.executeUpdate("UPDATE ingredientes INNER JOIN bebidas_ingredientes ON ingredientes.id_ingrediente = bebidas_ingredientes.id_ingrediente "
                + "SET cant = cant - bebidas_ingredientes.cantidad WHERE bebidas_ingredientes.id_bebida = '"+idBebida+"';");
    }
    public boolean checarIngredientesP (int idP) throws SQLException{
        // Obtener los ingredientes del platillo
        String query = "SELECT id_ingrediente, cantidad FROM platilloss_ingredientes WHERE id_platillo = '"+idP+"';";
        PreparedStatement  stmt1 = con.prepareStatement(query);
        ResultSet rs = stmt1.executeQuery();

        // Verificar la disponibilidad de cada ingrediente
        while (rs.next()) {
            int idIngrediente = rs.getInt("id_ingrediente");
            int cantidadPlatillo = rs.getInt("cantidad");

            query = "SELECT cant FROM ingredientes WHERE id_ingrediente = '"+idIngrediente+"'";
            stmt1 = con.prepareStatement(query);
            //stmt1.setInt(1, idIngrediente);
            rs = stmt1.executeQuery();

            if (rs.next()) {
                int cantidadIngrediente = rs.getInt("cant");
                if (cantidadIngrediente < cantidadPlatillo) {
                    JOptionPane.showMessageDialog(null, "No hay suficiente ingrediente para el platillo");
                    return false;
                }/*else{
                    query = "UPDATE ingredientes INNER JOIN platilloss_ingredientes ON "
                            + "ingredientes.id_ingrediente = platilloss_ingredientes.id_ingrediente "
                            + "SET ingredientes.cant = ingredientes.cant - platilloss_ingredientes.cantidad WHERE platilloss_ingredientes.id_platillo = '"+idP+"';";
                    stmt1 = con.prepareStatement(query);
                }*/
            } else {
                // El ingrediente no existe en la tabla de ingredientes, el pedido no se puede realizar
                return false;
            }
        }
        // Si se llega a este punto, hay suficiente cantidad de todos los ingredientes, el pedido se puede realizar
        return true;

    }
    public int totalBebida(int idB) throws SQLException{
        // Definimos la consulta
        String sql = "SELECT id_bebida, SUM(cantB) AS totalBebidas FROM pedido WHERE id_bebida='"+idB+"'";

        // Creamos un objeto Statement para ejecutar la consulta
        Statement stmt = con.createStatement();

        // Ejecutamos la consulta y obtenemos un objeto ResultSet con los resultados
        ResultSet rs = stmt.executeQuery(sql);

        // Si la consulta devuelve algún resultado, lo guardamos en una variable entera
        int totalBebidas = 0;
        if (rs.next()) {
            totalBebidas = rs.getInt("totalBebidas");
        }else{
            totalBebidas=0;
        }

        
        return totalBebidas;
        
    }
    
    public int totalPlatillo(int idP) throws SQLException{
        // Definimos la consulta
        String sql = "SELECT id_platillo, SUM(cantP) AS totalPlatillo FROM pedido WHERE id_platillo='"+idP+"'";

        // Creamos un objeto Statement para ejecutar la consulta
        Statement stmt = con.createStatement();

        // Ejecutamos la consulta y obtenemos un objeto ResultSet con los resultados
        ResultSet rs = stmt.executeQuery(sql);

        // Si la consulta devuelve algún resultado, lo guardamos en una variable entera
        int totalPlatillo = 0;
        if (rs.next()) {
            totalPlatillo = rs.getInt("totalPlatillo");
        }else{
            totalPlatillo=0;
        }

        
        return totalPlatillo;
        
    }
    public void reducirIngredienteP(int idPlatillo) throws SQLException{
        Statement sts = con.createStatement();
        sts.executeUpdate("UPDATE ingredientes INNER JOIN platilloss_ingredientes ON ingredientes.id_ingrediente = platilloss_ingredientes.id_ingrediente "
                + "SET ingredientes.cant = ingredientes.cant - platilloss_ingredientes.cantidad WHERE platilloss_ingredientes.id_platillo = '"+idPlatillo+"';");
    }
}




