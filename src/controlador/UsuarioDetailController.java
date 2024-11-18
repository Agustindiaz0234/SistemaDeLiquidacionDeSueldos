package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modelo.Rol;
import modelo.Usuario;
import repository.RolRepository;
import repository.UsuarioRepository;


public class UsuarioDetailController implements Initializable {

    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtMail;
    @FXML
    private Button btnGuardar;
    
    @FXML
    private ChoiceBox<String> cBoxRol;
    
    private TextField txtId;
    
    private static Usuario usuario;
    
    private static boolean isEdit;
    
    private RolRepository rolRepository;
      
    private UsuarioRepository usuarioRepository;

    public UsuarioDetailController() {
        this.usuarioRepository = new UsuarioRepository();
        this.rolRepository = new RolRepository();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(isEdit){
            
            this.txtUserName.setText(usuario.getUserName());
            this.txtPassword.setText(usuario.getPassword());
            this.txtMail.setText(usuario.getMail());
           
        }
         cargarRoles(cBoxRol);
    }    
    @FXML
    private void handleGuardar(ActionEvent event) {
        
           String userName = txtUserName.getText();
           String password = txtPassword.getText();
           String mail = txtMail.getText();
           String rolSeleccionado = cBoxRol.getValue();
           int idRol = obtenerIdRol(rolSeleccionado);

          

          
       
           if(isEdit){
        
           Usuario usuario1 = new Usuario(usuario.getId(), userName, password, mail, idRol);
            updateUsuario(usuario1);
        
        }else{
            
           Usuario usuario1 = new Usuario( userName, password, mail, idRol);
            insertarUsuario(usuario1);
        
        }
               MainLayoutController mainController = Main.getMainController();
               mainController.showUsuarioList();
    }
    
    
    public void cargarRoles(ChoiceBox<String> choiceBoxRoles){
            
        Rol[] roles = rolRepository.getAll(null);
        
         
            if (roles == null || roles.length == 0) {
               System.out.println("No se encontraron roles");
               return;
           }

            choiceBoxRoles.getItems().clear();
    
  
            for (Rol rol : roles) {
                choiceBoxRoles.getItems().add(rol.getNombre()); 
            }
    }
    
        public int obtenerIdRol(String nombreRol) {

            Rol[] roles = rolRepository.getAll(null);


            for (Rol rol : roles) {
                if (rol.getNombre().equals(nombreRol)) {
                    return rol.getId();
                }
            }
            return -1; 

        }

    
      public void insertarUsuario(Usuario usuario){
        
        this.usuarioRepository.insert(usuario);
       
    }
      
      
        public void updateUsuario(Usuario usuario){
        
        this.usuarioRepository.update(usuario);
       
    }
        
         
        public static void setEditMode(Usuario usu){
        
            isEdit = true;
            
            usuario =  usu;
            
        }
        
         public static void setFalse(){
        isEdit = false;
        }
}
