
package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Empleado;
import modelo.Empresa;
import modelo.filter.EmpleadoFilter;
import repository.EmpleadoRepository;

public class EmpleadoListController implements Initializable {

    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEditar;
    @FXML
    private TableView<Empleado> tblEmpleados;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columDireccion;
    @FXML
    private TableColumn columnTelefono;
    @FXML
    private TableColumn columnMail;
    
    private EmpleadoRepository empleadoRepository;
    
    private static Empresa empresa;

 public EmpleadoListController() {
        // Inicializamos el repositorio en el constructor
        this.empleadoRepository = new EmpleadoRepository();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columDireccion.setCellValueFactory(new PropertyValueFactory("apellido"));
        this.columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        this.columnMail.setCellValueFactory(new PropertyValueFactory("mail"));
        cargarDatosDesdeBD();
    }  
    
     private void cargarDatosDesdeBD() {
            
            EmpleadoFilter empleadoFilter = new EmpleadoFilter();
            empleadoFilter.setEmpresaId(empresa.getId());
            Empleado[] empleados = empleadoRepository.getAll(empleadoFilter);
            tblEmpleados.setItems(FXCollections.observableArrayList(empleados));
        
    }

    @FXML
    private void navegarEliminarEmpleado(ActionEvent event) {
      
        
        Empleado selectedEmpleado = tblEmpleados.getSelectionModel().getSelectedItem();
        
        if(selectedEmpleado == null){
          Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningún empleado");
               alert.showAndWait();
         }else{
        
        EmpleadoRepository er = new EmpleadoRepository();
        
        er.delete(selectedEmpleado.getId());
        cargarDatosDesdeBD();
        }
    }

    @FXML
    private void navegarAgregarEmpleado(ActionEvent event) {
          MainLayoutController mainController = Main.getMainController(); 
          EmpleadoDetailController.setFalse();
          mainController.showEmpleadoDetail(empresa, null); 
    }

    @FXML
    private void navegarEditarEmpleado(ActionEvent event) {
            Empleado selectedEmpleado = tblEmpleados.getSelectionModel().getSelectedItem();
            
         if(selectedEmpleado == null){
          Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningún empleado");
               alert.showAndWait();
         }else{
           MainLayoutController mainController = Main.getMainController(); 
           mainController.showEmpleadoDetail(empresa, selectedEmpleado); 
         }
         }
    
      public static void setEmpresa(Empresa emp) {   
      empresa = emp;
    }
    
}
