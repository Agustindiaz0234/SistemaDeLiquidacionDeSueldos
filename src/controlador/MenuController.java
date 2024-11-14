package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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

    private void click(ActionEvent event) {
        
        Stage stage = (Stage) btnEmpresa.getScene().getWindow();

        
        
    }

    @FXML
    private void navegarEmpresa(ActionEvent event) {
        MainLayoutController mainController = Main.getMainController();
        mainController.showEmpresa();
    }

    @FXML
    private void navegarAdministrar(ActionEvent event) {
    }

    @FXML
    private void closeVentana(ActionEvent event) {
        MainLayoutController mainController = Main.getMainController();
        mainController.showAgregarEmpresa();        
    }
    
}
