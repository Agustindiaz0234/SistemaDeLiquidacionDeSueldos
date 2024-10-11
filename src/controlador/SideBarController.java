/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import modelo.Navegacion;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class SideBarController implements Initializable {

    @FXML
    private Button btnEmpresa;
    @FXML
    private Button btnMenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void navegarEmpresa(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Navegacion.Navegar("/vista/Empresa.fxml", stage); 
    }

    @FXML
    private void navegarMenu(ActionEvent event) {
         Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Navegacion.Navegar("/vista/Menu.fxml", stage);
    }
    
}
