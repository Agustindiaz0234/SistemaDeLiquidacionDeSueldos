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
import javafx.scene.layout.VBox;
import modelo.Usuario;
import repository.UsuarioRepository;

public class UsuarioListController implements Initializable {

    private VBox cardContainer;
    
     
    private UsuarioRepository usuarioRepository;
    @FXML
    private TableView<Usuario> tblUsuarios;
    @FXML
    private TableColumn colUserName;
    @FXML
    private TableColumn colMail;
    
    MainLayoutController mainController = Main.getMainController();
    
      private static ObservableList<Usuario> usuarios = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn colId;
    @FXML
    private TableColumn<?, ?> columRol;
    
    public UsuarioListController() {
        this.usuarioRepository = new UsuarioRepository();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.colId.setCellValueFactory(new PropertyValueFactory("id"));
        this.colUserName.setCellValueFactory(new PropertyValueFactory("userName"));
        this.colMail.setCellValueFactory(new PropertyValueFactory("mail"));
        
        cargarDatosDesdeDB();
    }  
    

    @FXML
    private void navegarAgregarUsuario(ActionEvent event) {
           UsuarioDetailController.setFalse();        
           mainController.showUsuarioDetail();
    }

    @FXML
    private void navegarEditarUsuario(ActionEvent event) {
           Usuario selectedUsuario = tblUsuarios.getSelectionModel().getSelectedItem();
           
           if(selectedUsuario== null){
           PopupAlert.noSeleccion("usuario");
           }else{
           UsuarioDetailController.setEditMode(selectedUsuario);
           mainController.showUsuarioDetail();
           }
      }

    @FXML
    private void navegarEliminarUsuario(ActionEvent event) {
           Usuario selectedUsuario = tblUsuarios.getSelectionModel().getSelectedItem();
          
           if(selectedUsuario== null){
           
               PopupAlert.noSeleccion("usuario");
           
           }else{
        
                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                confirmationAlert.setTitle("Confirmación de eliminación");
                confirmationAlert.setHeaderText(null);
                confirmationAlert.setContentText("¿Estás seguro de que deseas eliminar este usuario?");

                Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
          
            UsuarioRepository us = new UsuarioRepository();
            us.delete(selectedUsuario.getId());
            cargarDatosDesdeDB();
            
        }
        
           }
    
    }
    
    public void cargarDatosDesdeDB(){
        Usuario[] usuarios = usuarioRepository.getAll(null);
          tblUsuarios.setItems(FXCollections.observableArrayList(usuarios));
    }
}
