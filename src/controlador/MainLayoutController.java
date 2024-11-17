package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modelo.Empleado;
import modelo.Empresa;
import modelo.Usuario;


public class MainLayoutController implements Initializable {

    @FXML
    private VBox formContainer;
    @FXML
    private MenuItem btnSalir;

        @FXML
    public void logout(ActionEvent event) {
        
        Session.setCurrentUser(null);
        loadForm("Login.fxml");
    }
    
    @FXML
    public void showMenu(){
        Usuario loggedInUser = Session.getCurrentUser(); 
        if(loggedInUser==null){
        PopupAlert.noLogged();
        return;
        }
        loadForm("Menu.fxml");
    }
  
    @FXML
    public void showEmpresa() {
        Usuario loggedInUser = Session.getCurrentUser(); 
    
        if(loggedInUser==null){
        
         PopupAlert.noLogged();
        
         return;
        
        }
        loadForm("EmpresaList.fxml");
    }
    
    public void showAgregarEmpresa(){
        loadForm("EmpresaDetail.fxml");
    }

     public void showEmpleadoList(Empresa empresa) {
        EmpleadoListController.setEmpresa(empresa);
        loadForm("EmpleadoList.fxml");
    }
     
     public void showEmpleadoDetail(Empresa empresa, Empleado empleado){
         if(empleado != null){
         
             EmpleadoDetailController.setEditMode(empleado);
         
         }
          
         EmpleadoDetailController.setEmpresa(empresa);
        
          loadForm("EmpleadoDetail.fxml");
     }

    @FXML
     public void showUsuarioList(){
     Usuario loggedInUser = Session.getCurrentUser(); 
    
     if(loggedInUser==null){
     
         PopupAlert.noLogged();
     
         return;
     }
     
     if(loggedInUser.getIdRol()==1){
     
         loadForm("UsuarioList.fxml");
     }else{
     
         PopupAlert.noPermiso();
        
     }
     }
     
     public void showUsuarioDetail(){
     loadForm("UsuarioDetail.fxml");
     }
     
    @FXML
     public void showRolList(){
         Usuario loggedInUser = Session.getCurrentUser(); 
     if(loggedInUser==null){
     
         PopupAlert.noLogged();
     
         return;
     
     }
     
     if(loggedInUser.getIdRol()==1){
     
         loadForm("RolList.fxml");
     }else{
     
         PopupAlert.noPermiso();
     
     }
     }
     public void showRolDetail(){
         
     loadForm("RolDetail.fxml");
     }
     
     public void showLiquidacion(){
     loadForm("Liquidacion.fxml");
     }
     
     public void showReciboList(){
     loadForm("ReciboList.fxml");
     }
     
     public void showLogin(){
         
     loadForm("Login.fxml");
     
     }
     
    private void loadForm(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/"+fxmlFile));
            VBox form = loader.load();
            formContainer.getChildren().clear(); 
            formContainer.getChildren().add(form); 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showLogin();
      }

    @FXML
    public void cerrarVentana() {
         Stage stage = (Stage) btnSalir.getParentPopup().getOwnerWindow();
        stage.close();
    }


}
