
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empleado;
import modelo.filter.EmpleadoFilter;
import repository.StaticVariables;

public class EmpleadoRepository {
    

  public Connection connect() throws SQLException {
    String url = StaticVariables.DATABASE_URL;
    String username = StaticVariables.USERNAME;
    String password = StaticVariables.PASSWORD;

    return DriverManager.getConnection(url, username, password);
}

    public Empleado[] getAll(EmpleadoFilter empleadoFilter) {
    String query = "SELECT id, nombre, apellido, telefono, mail, empresaId FROM empleados ";
    StringBuilder where = new StringBuilder();
    if (empleadoFilter != null) {
    boolean hasPreviousCondition = false;

    if (empleadoFilter.getEmpresaId() != null) {
        where.append("empresaId = '").append(empleadoFilter.getEmpresaId()).append("' ");
        hasPreviousCondition = true; 
    }

    }
    
    query = query + (where.length() >0 ? "WHERE " + where.toString() : "");
    
    List<Empleado> empleados = new ArrayList<>(); 

    try (Connection conn = connect();
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            int telefono = rs.getInt("telefono");
            String mail = rs.getString("mail");
            int empresaId = rs.getInt("empresaId");
            
            Empleado empleado = new Empleado(id, nombre, apellido, telefono, mail, empresaId);
            empleados.add(empleado);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return empleados.toArray(new Empleado[0]);
}

public void insert(Empleado empleado){

        String query = "INSERT INTO empleados ( nombre, apellido, telefono, mail, empresaId) VALUES (?,?,?,?,?)";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setInt(3, empleado.getTelefono());
            stmt.setString(4, empleado.getMail());
            stmt.setInt(5, empleado.getEmpresaId());
            
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
 
    public void update(Empleado empleado){

        String query = "UPDATE empleados set nombre = ?, apellido = ?, telefono = ?, mail = ?, empresaId = ? WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setString(1, empleado.getNombre());
            stmt.setString(2, empleado.getApellido());
            stmt.setInt(3, empleado.getTelefono());
            stmt.setString(4, empleado.getMail());
            stmt.setInt(5, empleado.getEmpresaId());
            stmt.setInt(6, empleado.getId());
            
            
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
    
       public void delete(int empleadoId){

        String deleteRecibosQuery = "DELETE FROM recibos WHERE empleado_Id = ?";
        String deleteEmpleadoQuery = "DELETE FROM empleados WHERE id = ?";

        try (Connection conn = connect()) {
            conn.setAutoCommit(false);

            try (PreparedStatement deleteRecibosStmt = conn.prepareStatement(deleteRecibosQuery)) {
                deleteRecibosStmt.setInt(1, empleadoId);
                deleteRecibosStmt.executeUpdate();
            }

            try (PreparedStatement deleteEmpleadoStmt = conn.prepareStatement(deleteEmpleadoQuery)) {
                deleteEmpleadoStmt.setInt(1, empleadoId);
                deleteEmpleadoStmt.executeUpdate();
            }

            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try (Connection conn = connect()) {
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
  }
}
