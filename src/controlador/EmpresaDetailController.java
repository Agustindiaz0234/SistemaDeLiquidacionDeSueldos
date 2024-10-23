/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Empresa;
import modelo.Navegacion;
import repository.EmpresaRepository;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class EmpresaDetailController implements Initializable {

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
    
    private static Empresa empresa;
    
    private static boolean isEdit;
    
    
    
   private EmpresaRepository empresaRepository;

    public EmpresaDetailController() {
        // Inicializamos el repositorio en el constructor
        this.empresaRepository = new EmpresaRepository();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(isEdit){
            
            this.txtNombre.setText(empresa.getNombre());
             this.txtDireccion.setText(empresa.getDireccion());
             this.txtTelefono.setText(String.valueOf(empresa.getTelefono()));
             this.txtId.setText(String.valueOf(empresa.getId()));

        }
    }    

    @FXML
    private void handleGuardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        int telefono = Integer.parseInt(txtTelefono.getText());
        int id = Integer.parseInt(txtId.getText());
        
        Empresa empresa = new Empresa(id, nombre, direccion,telefono);
        
        if(isEdit){
        
            updateEmpresa(empresa);
        
        }else{
            
            insertarEmpresa(empresa);
        
        }
        
      Stage stage = (Stage) btnGuardar.getScene().getWindow();
      Navegacion.cargarVistaPrincipalConContenido("EmpresaList.fxml", stage);
    }
    
    
    public void insertarEmpresa(Empresa empresa){
        
        this.empresaRepository.insert(empresa);
       
    }
    
        public void updateEmpresa(Empresa empresa){
        
        this.empresaRepository.update(empresa);
       
    }
        
        public static void setEditMode(Empresa emp){
        
            isEdit = true;
            
            empresa =  emp;
            
        }
    
}