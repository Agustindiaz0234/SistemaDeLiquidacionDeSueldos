
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Rol;
import repository.RolRepository;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class RolDetailController implements Initializable {

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtNombre;
    @FXML
    private Button btnGuardar;
    
    private static Rol rol;
    
    private static boolean isEdit;

       
    private RolRepository rolRepository;

    public RolDetailController() {
        this.rolRepository = new RolRepository();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        if(isEdit){
            
            this.txtNombre.setText(rol.getNombre());
            this.txtId.setText(String.valueOf(rol.getId()));
            
          }    

      
   }
    
         @FXML
    private void handleGuardar(ActionEvent event) {
        
           String userName = txtNombre.getText();
           int id = Integer.parseInt(txtId.getText());
          

           Rol rol = new Rol(id, userName);
       
           if(isEdit){
        
            updateRol(rol);
        
        }else{
            
            insertarRol(rol);
        
        }
               MainLayoutController mainController = Main.getMainController();
               mainController.showRolList();
    }
    
    
      public void insertarRol(Rol rol){
        
        this.rolRepository.insert(rol);
       
    }
      
      
        public void updateRol(Rol rol){
        
        this.rolRepository.update(rol);
       
    }
        
            
        public static void setEditMode(Rol rl){
        
            isEdit = true;
            
            rol =  rl;
            
        }
        
         public static void setFalse(){
        isEdit = false;
        }
}
