package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    
    public UsuarioListController() {
        this.usuarioRepository = new UsuarioRepository();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
           UsuarioDetailController.setEditMode(selectedUsuario);
           mainController.showUsuarioDetail();
    }

    @FXML
    private void navegarEliminarUsuario(ActionEvent event) {
           Usuario selectedUsuario = tblUsuarios.getSelectionModel().getSelectedItem();
           UsuarioRepository us = new UsuarioRepository();
           us.delete(selectedUsuario.getId());
           cargarDatosDesdeDB();
    }
    
    public void cargarDatosDesdeDB(){
        Usuario[] usuarios = usuarioRepository.getAll(null);
          tblUsuarios.setItems(FXCollections.observableArrayList(usuarios));
    }
}
