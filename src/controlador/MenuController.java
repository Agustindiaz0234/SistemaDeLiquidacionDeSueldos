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
public class MenuController implements Initializable {

    @FXML
    private Button btnEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(ActionEvent event) {
        
        Stage stage = (Stage) btnEmpresa.getScene().getWindow();
        Navegacion.Navegar("/vista/Empresa.fxml", stage);
        
        
    }
    
}