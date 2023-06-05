/*
 * Universidad Politecnica de San Luis Potosi
 * Programacion III Java
 * Programacion Orientada a Objetos
 */

package prueba;

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

public class GenerarTicketPDF {

    public static void main(String[] args) {
        Document document = new Document(new Rectangle(288.46f,349.16f));//tamanio estandar de un ticket
        Font font = FontFactory.getFont("Aharoni",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,6);
        Font font2 = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);
        Font font3 = FontFactory.getFont("Aharoni",BaseFont.IDENTITY_H,BaseFont.EMBEDDED,10);
        font2.setColor(BaseColor.WHITE);
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/anita/OneDrive - Universidad Politécnica de San Luis Potosí/4to/progra3/Proyecto/Prueba/tickets/ticket.pdf"));

            document.open();

            Image logo = Image.getInstance("src/img/logoCC.png");
            logo.scaleToFit(40f, 40f); // Tamaño del logo
            logo.setAlignment(Element.ALIGN_CENTER);
            document.add(logo);
            // Titulo del ticket
            Paragraph title = new Paragraph("Cafeteria CC\n\n",
            new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD));
            title.setAlignment(Paragraph.ALIGN_CENTER);
            document.add(title);
            
            Paragraph cliente = new Paragraph("Cliente:                     \tMesa:     \n\n",font);
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

            // Datos de la compra (ejemplo)
//            table.addCell("1");
//            table.addCell("Producto de ejemplo");
//            table.addCell("$9.99");

            //TENEMOS QUE SABER LA CANTIDAD DE PRODUCTOS PARA QUE CAMBIE EL TAMANIO DEL LARGO
            int cantP = 5;
            float totalPrecio = 0;
            for (int i = 0; i < cantP; i++) {
                cell = new PdfPCell(new Paragraph("1",font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("Producto ejemplo "+i,font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
               cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);
                
                cell = new PdfPCell(new Paragraph("$9.99",font));
                cell.setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                cell.setBackgroundColor(new BaseColor(192,192,192,64));
                cell.setBorderColor(BaseColor.WHITE);
                table.addCell(cell);
                totalPrecio+=9.99f;
            }
            //CALCULAMOS LA ALTURA TOTAL
            float alturaTabla = table.getTotalHeight()+20f;//espacio entre tabla y total
            
            
            // Agregar tabla al documento
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

        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

