
package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Empresa;
import repository.StaticVariables;

public class EmpresaRepository {
    

  public Connection connect() throws SQLException {
    String url = StaticVariables.DATABASE_URL;
    String username = StaticVariables.USERNAME;
    String password = StaticVariables.PASSWORD;

    return DriverManager.getConnection(url, username, password);
}

    public Empresa[] getAll() {
    String query = "SELECT id, nombre, direccion, telefono FROM empresas";
    List<Empresa> empresas = new ArrayList<>();  // Usamos una lista temporal

    try (Connection conn = connect();  // Reutilizamos el método connect
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String direccion = rs.getString("direccion");
            int telefono = rs.getInt("telefono");

            Empresa empresa = new Empresa(id, nombre, direccion, telefono);
            empresas.add(empresa);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    // Convertir la lista a un array y retornarlo
    return empresas.toArray(new Empresa[0]);
}

public void insert(Empresa empresa){

        String query = "INSERT INTO empresas (id, nombre, direccion, telefono) VALUES (?,?,?,?)";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, empresa.getId());
            stmt.setString(2, empresa.getNombre());
            stmt.setString(3, empresa.getDireccion());
            stmt.setInt(4, empresa.getTelefono());
            
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
 
    public void update(Empresa empresa){

        String query = "UPDATE empresas set nombre = ?, direccion = ?, telefono = ? WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setString(1, empresa.getNombre());
            stmt.setString(2, empresa.getDireccion());
            stmt.setInt(3, empresa.getTelefono());
            stmt.setInt(4, empresa.getId());
            
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
    
       public void delete(int empresaId){

        String query = "DELETE empresas WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setInt(1, empresaId);
          
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
}
