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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Empresa;
import modelo.Navegacion;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class AgregarEmpresaController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtDireccion;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnGuardar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleGuardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        int telefono = Integer.parseInt(txtTelefono.getText());
        int id = Integer.parseInt(txtId.getText());
        
        Empresa empresa = new Empresa(id, nombre, direccion,telefono);
        
        EmpresaController.agregarEmpresas(empresa);
        
          Stage stage = (Stage) btnGuardar.getScene().getWindow();
   
      Navegacion.cargarVistaPrincipalConContenido("Empresa.fxml", stage);
  
    }
    
}
