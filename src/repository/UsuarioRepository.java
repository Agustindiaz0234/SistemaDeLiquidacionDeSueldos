package repository;

import modelo.filter.UsuarioFilter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Usuario;

public class UsuarioRepository {
    
      public Connection connect() throws SQLException {
    String url = StaticVariables.DATABASE_URL;
    String username = StaticVariables.USERNAME;
    String password = StaticVariables.PASSWORD;

    return DriverManager.getConnection(url, username, password);
}
      
       public Usuario[] getAll(UsuarioFilter usuarioFilter) {
    String query = "SELECT id, userName, password, mail, idRol FROM usuarios ";
    
    
    List<Usuario> usuarios = new ArrayList<>(); 

    try (Connection conn = connect(); 
         PreparedStatement stmt = conn.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String userName = rs.getString("userName");
            String password = rs.getString("password");
            String mail = rs.getString("mail");
            int idRol = rs.getInt("idRol");

            Usuario usuario = new Usuario(id, userName, password, mail, idRol);
            usuarios.add(usuario);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return usuarios.toArray(new Usuario[0]);
}

       public void insert(Usuario usuario){

        String query = "INSERT INTO usuarios (id, userName, password, mail, idRol) VALUES (?,?,?,?,?)";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
            stmt.setInt(1, usuario.getId());
            stmt.setString(2, usuario.getUserName());
            stmt.setString(3, usuario.getPassword());
            stmt.setString(4, usuario.getMail());
            stmt.setInt(5, usuario.getIdRol());

            
            stmt.executeUpdate();
        
        }catch (SQLException e) {
        if (e.getSQLState().equals("23000")) { // Código de error SQL para clave duplicada en MySQL
            System.out.println("Error: Ya existe un usuario con el ID " + usuario.getId());
        } else {
            e.printStackTrace(); // Para otros errores
        }
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
    
        public void update(Usuario usuario){

        String query = "UPDATE usuarios set userName = ?, password = ?, mail = ?, idRol=? WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setString(1, usuario.getUserName());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3, usuario.getMail());
            stmt.setInt(4, usuario.getIdRol());
            stmt.setInt(5, usuario.getId());

            
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
    
       public void delete(int usuarioId){

        String query = "DELETE FROM usuarios WHERE id= ?";
        try{Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query);
            
           
            stmt.setInt(1, usuarioId);
          
            stmt.executeUpdate();
        
        }catch(Exception e){
        
        e.printStackTrace();
        
        }
}
       
        public Usuario getByUserName(String userName) {
        String query = "SELECT * FROM usuarios WHERE username = ?";
        try (Connection conn = connect();
            PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String mail = rs.getString("mail");
                int idRol = rs.getInt("idRol");
                return new Usuario(id, username, password, mail, idRol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
