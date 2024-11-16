package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import modelo.Usuario;
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
    
    private ChoiceBox<String> cBoxRol;
    @FXML
    private TextField txtIdRol;
    @FXML
    private TextField txtId;
    
    private static Usuario usuario;
    
    private static boolean isEdit;
    
      
    private UsuarioRepository usuarioRepository;

    public UsuarioDetailController() {
        this.usuarioRepository = new UsuarioRepository();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(isEdit){
            
            this.txtUserName.setText(usuario.getUserName());
            this.txtPassword.setText(usuario.getPassword());
            this.txtMail.setText(usuario.getMail());
            this.txtId.setText(String.valueOf(usuario.getId()));
            this.txtIdRol.setText(String.valueOf(usuario.getIdRol()));
//          cBoxRol.getItems().addAll("Opción 1", "Opción 2", "Opción 3");
//          cBoxRol.setValue("Opción 1"); // Opción seleccionada por defecto
        }
        
    }    
    @FXML
    private void handleGuardar(ActionEvent event) {
        
           String userName = txtUserName.getText();
           String password = txtPassword.getText();
           String mail = txtMail.getText();
           int id = Integer.parseInt(txtId.getText());
           int idRol = Integer.parseInt(txtIdRol.getText());
          

           Usuario usuario = new Usuario(id, userName, password, mail, idRol);
       
           if(isEdit){
        
            updateUsuario(usuario);
        
        }else{
            
            insertarUsuario(usuario);
        
        }
               MainLayoutController mainController = Main.getMainController();
               mainController.showUsuarioList();
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
