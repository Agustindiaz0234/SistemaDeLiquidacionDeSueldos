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
public class EmpresaController implements Initializable {

    private Button btnMenu;
    @FXML
    private Button btnAgregar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    private void volverMenu(ActionEvent event) {
        Stage stage = (Stage) btnMenu.getScene().getWindow();
       
    }

    @FXML
    private void navegarAgregarEmpresa(ActionEvent event) {
    }
    
}
