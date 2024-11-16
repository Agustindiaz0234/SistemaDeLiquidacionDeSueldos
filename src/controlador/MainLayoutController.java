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


public class MainLayoutController implements Initializable {

    @FXML
    private VBox formContainer;
    @FXML
    private MenuItem btnSalir;

        @FXML
    public void logout(ActionEvent event) {
        Stage stage = (Stage) btnSalir.getParentPopup().getOwnerWindow();
        stage.close();
    }
    
    @FXML
    public void showMenu(){
        loadForm("Menu.fxml");
    }
  
    @FXML
    public void showEmpresa() {
        loadForm("EmpresaList.fxml");
    }
    
    @FXML
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

     public void showUsuarioList(){
     loadForm("UsuarioList.fxml");
     }
     
     public void showUsuarioDetail(){
     loadForm("UsuarioDetail.fxml");
     }
     
     public void showRolList(){
     loadForm("RolList.fxml");
     }
     public void showRolDetail(){
     loadForm("RolDetail.fxml");
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
        showMenu();
      }


    
}
