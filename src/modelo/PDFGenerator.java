
package modelo;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import repository.ReciboRepository;

public class PDFGenerator {

    public static void generarRecibo(String nombreArchivo,  String mesActual, 
                                     String nombreEmpleado, String apellidoEmpleado, int idEmpleado,
                                     String nombreEmpresa, String telefonoEmpresa, String direccionEmpresa,
                                     double sueldoBruto, double descuentoJubilacion, double descuentoLey, double descuentoObraSocial) {
        try {
           

            Document document = new Document();
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();

            // Fuente para el texto
            Font fontBold = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
            Font fontNormal = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);

            // Tabla 1: Datos del empleado y de la empresa
            PdfPTable tablaDatosGenerales = new PdfPTable(3);
            tablaDatosGenerales.setWidthPercentage(100);
            tablaDatosGenerales.setSpacingBefore(10f);

            // Encabezados de la tabla
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase("Nombre", fontBold)));
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase("Apellido / Teléfono", fontBold)));
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase("Mes / Dirección", fontBold)));

            // Fila 1: Datos del empleado
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase(nombreEmpleado, fontNormal)));
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase(apellidoEmpleado, fontNormal)));
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase(mesActual, fontNormal)));

            // Fila 2: Datos de la empresa
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase(nombreEmpresa, fontNormal)));
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase(telefonoEmpresa, fontNormal)));
            tablaDatosGenerales.addCell(new PdfPCell(new Phrase(direccionEmpresa, fontNormal)));

            document.add(tablaDatosGenerales);

            // Tabla 2: Sueldo y descuentos
            PdfPTable tablaDescuentos = new PdfPTable(2);
            tablaDescuentos.setWidthPercentage(100);
            tablaDescuentos.setSpacingBefore(10f);

            // Encabezados de la tabla
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Descripción", fontBold)));
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Monto", fontBold)));

            // Fila 1: Sueldo Bruto
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Sueldo Bruto", fontNormal)));
            tablaDescuentos.addCell(new PdfPCell(new Phrase("$" + sueldoBruto, fontNormal)));

            // Fila 2: Descuento Jubilación
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Jubilación (11%)", fontNormal)));
            tablaDescuentos.addCell(new PdfPCell(new Phrase("$" + descuentoJubilacion, fontNormal)));

            // Fila 3: Descuento Ley 19.032
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Ley 19.032 (3%)", fontNormal)));
            tablaDescuentos.addCell(new PdfPCell(new Phrase("$" + descuentoLey, fontNormal)));

            // Fila 4: Descuento Obra Social
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Obra Social (3%)", fontNormal)));
            tablaDescuentos.addCell(new PdfPCell(new Phrase("$" + descuentoObraSocial, fontNormal)));

            // Fila 5: Total Neto
            double totalNeto = sueldoBruto - (descuentoJubilacion + descuentoLey + descuentoObraSocial);
            tablaDescuentos.addCell(new PdfPCell(new Phrase("Total Neto", fontBold)));
            tablaDescuentos.addCell(new PdfPCell(new Phrase("$" + totalNeto, fontBold)));

            document.add(tablaDescuentos);

            document.close();
         guardarEnDB(byteArrayOutputStream.toByteArray(), nombreArchivo, idEmpleado);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public static void guardarEnDB(byte[] pdfBytes, String nombre, int empleadoId){
        try{
              ReciboRepository reciboRepo = new ReciboRepository();
              reciboRepo.guardarPDFenBaseDeDatos(empleadoId, pdfBytes, nombre);
              System.out.println("PDF guardado en la base de datos.");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
