package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Usuario;

public class MenuController implements Initializable {

    private Button btnEmpresa;
    @FXML
    private Button btnEmpresas;
    @FXML
    private Button btnAdministrar;
    @FXML
    private Button btnSalir;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navegarEmpresa(ActionEvent event) {
        MainLayoutController mainController = Main.getMainController();
        mainController.showEmpresa();
    }

    @FXML
    private void navegarAdministrar(ActionEvent event) {
        Usuario loggedUsuario = Session.getCurrentUser();
        if(loggedUsuario.getIdRol() == 1){
         MainLayoutController mainController = Main.getMainController();
         mainController.showUsuarioList();
        }else{
        PopupAlert.noPermiso();
        }
    }

    
}
