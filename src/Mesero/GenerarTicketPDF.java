/*
 * Universidad Politecnica de San Luis Potosi
 * Programacion III Java
 * Programacion Orientada a Objetos
 */

package Mesero;

/**
 *
 * @author Ana Karen Cuenca Esquivel
 * @Ana Karen Cuenca Esquivel
 */
import java.io.FileOutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import java.sql.*;
import java.util.ArrayList;

public class GenerarTicketPDF {
    Connection con;
    int id=0;
    int reg=0;
    float totalPrecio = 0;
    String info[]=new String[2];
    private ArrayList < ArrayList<Object> > lista = new ArrayList < >();
    public GenerarTicketPDF(Connection con){
        this.con = con;
    }
    
    public void generaPDF2(int idP){
        Document document = new Document(new Rectangle(288.46f,349.16f));//tamanio estandar de un ticket
        Font font = FontFactory.getFont("Aharoni",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,6);
        Font font2 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font font3 = FontFactory.getFont("Aharoni",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,10);
        font2.setColor(BaseColor.WHITE);
        try {
            // Obtener el nombre y la mesa del cliente
            Statement sts = con.createStatement();
            sts.execute("SELECT mesa, nombreC FROM pedido WHERE id="+idP+" ");
            ResultSet rs = sts.getResultSet();
            while(rs.next()){
                info[0]=Integer.toString(rs.getInt("mesa"));
                info[1]=rs.getString("nombreC");
            }
            
            PdfWriter.getInstance(document, new FileOutputStream("src\\Ticket\\Ticket de "+info[1]+".pdf"));

            document.open();

            Image logo = Image.getInstance("src/img/logoCC.png");
            logo.scaleToFit(40f, 40f); // Tamaño del logo
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
            // Titulo del ticket
            Paragraph title = new Paragraph("Cafeteria CC\n\n",
            new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);//orales
            
            
            Paragraph cliente = new Paragraph("Cliente: "+info[1]+"              \tMesa: "+info[0]+"  \n\n",font);
            cliente.setAlignment(Paragraph.ALIGN_MIDDLE);
            document.add(cliente);

            // Información de la compra
            PdfPTable table = new PdfPTable(3); // 3 columnas
            table.setTotalWidth(200f);//ancho total de la tabla
            table.setLockedWidth(true);//bloquear ancho de tabla
            table.setWidths(new float[]{20f,60f,20f});
            table.getDefaultCell().setBorderColor(BaseColor.WHITE);
            table.getDefaultCell().setBorder(10);
            PdfPCell cell ;

            // Columna de cantidad
            cell = new PdfPCell(new Paragraph("Cant",font2));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBackgroundColor(new BaseColor(102,51,0,127));
            cell.setBorderColor(BaseColor.WHITE);
            table.addCell(cell);

            // Columna de producto
            cell = new PdfPCell(new Paragraph("Producto",font2));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBackgroundColor(new BaseColor(102,51,0,127));
            cell.setBorderColor(BaseColor.WHITE);
            table.addCell(cell);
            
            // Columna de precio unitario
            cell = new PdfPCell(new Paragraph("Precio",font2));
            cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            cell.setBackgroundColor(new BaseColor(102,51,0,127));
            cell.setBorderColor(BaseColor.WHITE);
            table.addCell(cell);
            
            String datos[]=new String[6];
            int cant, cant2;
            float precio, precio2;
            totalPrecio=0;
            try{
                /*Statement sts = con.prepareStatement("SELECT pedido.cantP,pedido.id_platillo,platillos.nombre_platillo,platillos.precio_platillo FROM pedido INNER JOIN "
                        + "platillos ON platillos.id_platillo=pedido.id_platillo WHERE pedido.id_P="+idP+" ");*/
                Statement sts2 = con.createStatement();
                sts2.execute("SELECT pedido.cantP,pedido.id_platillo,platillos.nombre_platillo,platillos.precio_platillo,pedido.estado FROM pedido INNER JOIN "
                        + "platillos ON platillos.id_platillo=pedido.id_platillo WHERE pedido.id="+idP+" && pedido.estado='Finalizado'");
                ResultSet rs2 = sts2.getResultSet();
                while(rs2.next()){
                    datos[0]=Integer.toString(rs2.getInt("pedido.cantP"));
                    cant=Integer.parseInt(datos[0]);
                    datos[1]=rs2.getString("platillos.nombre_platillo");
                    datos[2]=Float.toString(rs2.getFloat("platillos.precio_platillo"));
                    precio=Float.parseFloat(datos[2]);
                    //totalPrecio+=Float.parseFloat(rs.getString("platillos.precio_platillo"));
                    totalPrecio+=cant*precio;
                }
                /*Statement sts2 = con.prepareStatement("SELECT pedido.cantB,pedido.id_bebida,bebidas.nombre_bebida,bebidas.precio_bebida FROM pedido INNER JOIN "
                        + "bebidas ON bebidas.id_bebida=pedido.id_bebida WHERE pedido.id_P="+idP+" ");*/
                Statement sts3 = con.createStatement();
                sts3.execute("SELECT pedido.cantB,pedido.id_bebida,bebidas.nombre_bebida,bebidas.precio_bebida,pedido.estado FROM pedido INNER JOIN "
                        + "bebidas ON bebidas.id_bebida=pedido.id_bebida WHERE pedido.id="+idP+" && pedido.estado='Finalizado'");
                ResultSet rs3 = sts3.getResultSet();
                while(rs3.next()){
                    datos[3]=Integer.toString(rs3.getInt("pedido.cantB"));
                    cant2=Integer.parseInt(datos[3]);
                    datos[4]=rs3.getString("bebidas.nombre_bebida");
                    datos[5]=Float.toString(rs3.getFloat("bebidas.precio_bebida"));
                    precio2=Float.parseFloat(datos[5]);
                    //totalPrecio+=Float.parseFloat(rs2.getString("bebidas.precio_bebida"));
                    totalPrecio+=cant2*precio2;
                }
            }catch(SQLException e){
                System.out.println("ERROR 1: "+e);
            }
            
            for (int i = 0; i < 1; i++) {
                cell = new PdfPCell(new Paragraph(datos[0],font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(datos[1],font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(datos[2],font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);
            }
            
            for (int i = 0; i < 1; i++) {
                cell = new PdfPCell(new Paragraph(datos[3],font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(datos[4],font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(datos[5],font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);
            }
            
            //CALCULAMOS LA ALTURA TOTAL
            float alturaTabla = table.getTotalHeight()+20f;//espacio entre tabla y total
            
            document.add(table);
            
            // Agregar total de la compra
            DecimalFormat df = new DecimalFormat("$###,##0.00");
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss a");
            Paragraph total = new Paragraph("Total: " + df.format(totalPrecio) + "\n" + sdf.format(date),new Font(font3));
            total.setAlignment(Paragraph.ALIGN_RIGHT);
            document.add(total);
            
            document.setPageSize(new Rectangle(283.46f,alturaTabla+total.getTotalLeading()));
            Paragraph extra = new Paragraph("Gracias por venir <3\n\n",font3);
            extra.setAlignment(Paragraph.ALIGN_BASELINE);
            document.add(extra);
            // Cerrar documento
            document.close();
          
            System.out.println("Ticket generado correctamente.");
        }catch(Exception e){
            System.out.println("ERROR 2: "+e);
        }
    }

    /**
     * @return the lista
     */
    public ArrayList < ArrayList<Object> > getLista() {
        return lista;
    }
}

