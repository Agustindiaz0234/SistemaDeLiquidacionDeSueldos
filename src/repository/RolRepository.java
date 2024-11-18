package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Rol;
import modelo.filter.RolFilter;


public class RolRepository {
    
    public Connection connect() throws SQLException {
    
    String url = StaticVariables.DATABASE_URL;
    String username = StaticVariables.USERNAME;
    String password = StaticVariables.PASSWORD;

    return DriverManager.getConnection(url, username, password);
}
         
  public Rol[] getAll(RolFilter rolFilter) {
   
      String query = "SELECT id, nombre FROM roles ";
    
   
      StringBuilder where = new StringBuilder();
    
    
      query = query + (where.length() >0 ? "WHERE " + where.toString() : "");
    
    
      List<Rol> roles = new ArrayList<>();  
    
      try (Connection conn = connect();
         
              PreparedStatement stmt = conn.prepareStatement(query);
         
              ResultSet rs = stmt.executeQuery()) {

      
          while (rs.next()) {
            
              int id = rs.getInt("id");
            
              String nombre = rs.getString("nombre");
          
            
              Rol rol = new Rol(id, nombre);
            
              roles.add(rol);
        }

            }catch(SQLException e){
                e.printStackTrace();
            }

    return roles.toArray(new Rol[0]);
}
  
   public void insert(Rol rol){

        String query = "INSERT INTO roles (id, nombre) VALUES (?,?)";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, rol.getId());
            stmt.setString(2, rol.getNombre());
           

            
            stmt.executeUpdate();
        
        }catch (SQLException e) {
        if (e.getSQLState().equals("23000")) { 
            System.out.println("Error: Ya existe un usuario con el ID " + rol.getId());
        } else {
            e.printStackTrace();
        }
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
    
        public void update(Rol rol){

        String query = "UPDATE roles set nombre = ? WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setString(1, rol.getNombre());
            stmt.setInt(2, rol.getId());

            
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
    
       public void delete(int rolId){

        String query = "DELETE FROM roles WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setInt(1, rolId);
          
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
}
