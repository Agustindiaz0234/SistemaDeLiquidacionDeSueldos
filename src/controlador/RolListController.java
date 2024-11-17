
package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Rol;
import repository.RolRepository;

public class RolListController implements Initializable {

    @FXML
    private TableView<Rol> tblRoles;
    @FXML
    private TableColumn colIdRol;
    @FXML
    private TableColumn colNombreRol;
    
    private RolRepository rolRepository;

  MainLayoutController mainController = Main.getMainController();
    
      private static ObservableList<Rol> roles = FXCollections.observableArrayList(); 
      
       public RolListController() {
         this.rolRepository = new RolRepository();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.colNombreRol.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.colIdRol.setCellValueFactory(new PropertyValueFactory("id"));
        cargarDatosDesdeDB();
    }    

    @FXML
    private void navegarAgregarRol(ActionEvent event) {
          RolDetailController.setFalse();
          mainController.showRolDetail();
    }

    @FXML
    private void navegarEditarRol(ActionEvent event) {
         
        Rol selectedRol = tblRoles.getSelectionModel().getSelectedItem();
        
        if(selectedRol== null){
           
             PopupAlert.noSeleccion("rol");
           
         }else{
           
             RolDetailController.setEditMode(selectedRol);
             mainController.showRolDetail();
            
         }
    }

    @FXML
    private void navegarEliminarRol(ActionEvent event) {
           Rol selectedRol = tblRoles.getSelectionModel().getSelectedItem();
           
            if(selectedRol== null){                          
                PopupAlert.noSeleccion("rol");
          
            }else{
        
            
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmación de eliminación");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("¿Estás seguro de que deseas eliminar este rol?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();

          if (result.isPresent() && result.get() == ButtonType.OK) {
          
            RolRepository us = new RolRepository();
            us.delete(selectedRol.getId());
            cargarDatosDesdeDB();
            
        }        
            }
    }
    
    public void cargarDatosDesdeDB(){
           Rol[] roles = rolRepository.getAll(null);
          tblRoles.setItems(FXCollections.observableArrayList(roles));
    }
}
