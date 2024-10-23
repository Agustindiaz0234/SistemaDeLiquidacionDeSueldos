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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import modelo.Navegacion;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class SideBarController implements Initializable {

    @FXML
    private ToggleButton btnMenu;
    @FXML
    private ToggleGroup navGroup;
    @FXML
    private ToggleButton btnEmpresa;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   

    @FXML
    private void NavegarMenu(ActionEvent event) {
        
        Navegacion.showContent("Menu.fxml");
        
    }



    @FXML
    private void navegarEmpresa(ActionEvent event) {
 
        Navegacion.showContent("EmpresaList.fxml");
        
    }
    
}
