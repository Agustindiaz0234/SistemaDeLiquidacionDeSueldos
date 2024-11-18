
package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
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
    
    @FXML
    private TableColumn<Empleado, Void> columBoton;
    
     MainLayoutController mainController = Main.getMainController(); 
    @FXML
    private TableColumn<Empleado, Void> columBoton1;

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
        
     columBoton.setCellFactory(param -> new TableCell<Empleado, Void>() {
   private final Button btn = new Button("Seleccionar");

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setGraphic(null);
            } else {
                final Empleado empleado = getTableView().getItems().get(getIndex());
                
                btn.setOnAction(event -> handleButton(empleado));
                
                setGraphic(btn);
            }
        }
    });
     
        columBoton1.setCellFactory(param -> new TableCell<Empleado, Void>() {
   private final Button btn = new Button("Seleccionar");

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setGraphic(null);
            } else {
                final Empleado empleado = getTableView().getItems().get(getIndex());
                
                btn.setOnAction(event -> handleButton1(empleado));
                
                setGraphic(btn);
            }
        }
    });
        
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
        System.out.println(selectedEmpleado.getId());
        
        if(selectedEmpleado == null){
        PopupAlert.noSeleccion("empleado");
         }else{
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmación de eliminación");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("¿Estás seguro de que deseas eliminar este empleado?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            EmpleadoRepository er = new EmpleadoRepository();
            er.delete(selectedEmpleado.getId());
            cargarDatosDesdeBD();
           }
        }
    }

    @FXML
    private void navegarAgregarEmpleado(ActionEvent event) {
          EmpleadoDetailController.setFalse();
          mainController.showEmpleadoDetail(empresa, null); 
    }

    @FXML
    private void navegarEditarEmpleado(ActionEvent event) {
            Empleado selectedEmpleado = tblEmpleados.getSelectionModel().getSelectedItem();
            
         if(selectedEmpleado == null){
             PopupAlert.noSeleccion("empleado");
         }else{
          
           mainController.showEmpleadoDetail(empresa, selectedEmpleado); 
         }
         }
    
      public static void setEmpresa(Empresa emp) {   
      empresa = emp;
    }
      
      public void handleButton(Empleado emp){
          LiquidacionController.recuperarEmpresaEmpleado(empresa, emp);
          mainController.showLiquidacion();
      
      }
      
           public void handleButton1(Empleado emp){
         ReciboListController.setEmpleado(emp);
          mainController.showReciboList();
      
      }
    
}
