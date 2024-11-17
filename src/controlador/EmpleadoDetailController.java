
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import modelo.Empleado;
import modelo.Empresa;
import repository.EmpleadoRepository;
import repository.EmpresaRepository;

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class EmpleadoDetailController implements Initializable {

    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtTelefono;
    @FXML
    private TextField txtId;
    @FXML
    private Button btnGuardar;
    @FXML
    private TextField txtApellido;
    @FXML
    private TextField txtMail;
    @FXML
    private Label lblEmpresa;
    
    private static Empleado empleado;
      
    private static Empresa empresa;
    
    private static boolean isEdit;
    
    private EmpresaRepository empresaRepository;

    private EmpleadoRepository empleadoRepository;
  
     public EmpleadoDetailController() {
        this.empresaRepository = new EmpresaRepository();
        this.empleadoRepository = new EmpleadoRepository();
    }
  
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        if(empresa == null && empleado != null){
             empresa =  this.empresaRepository.getById(empleado.getEmpresaId());
        }
        
        lblEmpresa.setText(empresa.getNombre());
        if(isEdit){
            
            this.txtNombre.setText(empleado.getNombre());
             this.txtApellido.setText(empleado.getApellido());
             this.txtTelefono.setText(String.valueOf(empleado.getTelefono()));
             this.txtMail.setText(empleado.getMail());
             this.txtId.setText(String.valueOf(empleado.getId()));

        }
    }    

    @FXML
    private void handleGuardar(ActionEvent event) {
         String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        int telefono = Integer.parseInt(txtTelefono.getText());
        String mail = txtMail.getText();
        
        Empleado emp = new Empleado( nombre, apellido,telefono, mail, empresa.getId());
        
        if(isEdit){
        
            updateEmpleado(emp);
        
        }else{
            
            insertarEmpleado(emp);
        
        }  

          MainLayoutController mainController = Main.getMainController(); 
          mainController.showEmpleadoList(empresa);
    }
    
      public void insertarEmpleado(Empleado empleado){
        
        this.empleadoRepository.insert(empleado);
       
    }
    
        public void updateEmpleado(Empleado empleado){
        
        this.empleadoRepository.update(empleado);
       
    }
    
    
    public static void setEditMode(Empleado emp){
        
            isEdit = true;
            
            empleado =  emp;
            
        }
    
      public static void setEmpresa(Empresa emp) {   
      empresa = emp;
    }
      
      public static void setFalse(){
      isEdit = false;
      }
}
