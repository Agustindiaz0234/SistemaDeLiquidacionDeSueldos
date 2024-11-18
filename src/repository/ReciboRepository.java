
package repository;

import controlador.PopupAlert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import modelo.Recibo;


public class ReciboRepository {
    
    public static Connection connect() throws SQLException {
    String url = StaticVariables.DATABASE_URL;
    String username = StaticVariables.USERNAME;
    String password = StaticVariables.PASSWORD;

    return DriverManager.getConnection(url, username, password);
}
 
    public static List<Recibo> getAll(int idEmpleado){
    
         List<Recibo> recibos = new ArrayList<>();
        String query = "SELECT id, nombre, pdf FROM recibos WHERE empleado_id=" + idEmpleado;

        try (Connection conn = connect();
             PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombreArchivo = resultSet.getString("nombre");
                byte[] archivo = resultSet.getBytes("pdf");

                Recibo recibo = new Recibo(id, nombreArchivo, archivo);
                recibos.add(recibo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recibos;
        
    }
    
    public static void guardarPDFenBaseDeDatos(int empleadoId, byte[] pdfBytes, String nombre) {
      
        String query = "INSERT INTO recibos (empleado_id, pdf, nombre) VALUES (?,?,?)";
        
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(query)) {
             
            pstmt.setInt(1,empleadoId);
            pstmt.setBytes(2, pdfBytes);
            pstmt.setString(3, nombre);
            
            pstmt.executeUpdate();
            PopupAlert.guardado("Recibo guardado en exito");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
  
       public void delete(int reciboId){

        String query = "DELETE FROM recibos WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setInt(1, reciboId);
          
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
    }
}
