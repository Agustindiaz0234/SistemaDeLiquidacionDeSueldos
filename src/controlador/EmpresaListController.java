package controlador;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Empresa;
import modelo.Navegacion;
import repository.EmpresaRepository;

public class EmpresaListController implements Initializable {

    @FXML
    private Button btnAgregar;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columDireccion;
    @FXML
    private TableColumn columnTelefono;
    @FXML
    private TableView<Empresa> tblEmpresas;
    
    private static ObservableList<Empresa> empresas = FXCollections.observableArrayList(); 
    @FXML
    private TableColumn<Empresa, Void> columBoton;
    
    
    private EmpresaRepository empresaRepository;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;

    public EmpresaListController() {
        // Inicializamos el repositorio en el constructor
        this.empresaRepository = new EmpresaRepository();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        this.columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        
        
    columBoton.setCellFactory(param -> new TableCell<Empresa, Void>() {


   private final Button btn = new Button("Seleccionar");

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setGraphic(null);
            } else {
                final Empresa empresa = getTableView().getItems().get(getIndex());
                
                btn.setOnAction(event -> handleButton(empresa));
                
                setGraphic(btn);
            }
        }
    });
        cargarDatosDesdeBD();
    }    
    
      private void cargarDatosDesdeBD() {
            
            Empresa[] empresas = empresaRepository.getAll();
            tblEmpresas.setItems(FXCollections.observableArrayList(empresas));
        
    }


    @FXML
    private void navegarAgregarEmpresa(ActionEvent event) {
          
          Stage stage = (Stage) btnAgregar.getScene().getWindow();
          Navegacion.Navegar("EmpresaDetail.fxml", stage);
    }
    

    private void handleButton(Empresa empresa){

     ListarEmpleadoController.setEmpresa(empresa);
   
     Stage stage = (Stage) tblEmpresas.getScene().getWindow();
     
     Navegacion.cargarVistaPrincipalConContenido("ListarEmpleado.fxml", stage);  
    }

   
    @FXML
    private void navegarEditarEmpresa(ActionEvent event) {
        
         Empresa selectedEmpresa = tblEmpresas.getSelectionModel().getSelectedItem();
         
         EmpresaDetailController.setEditMode(selectedEmpresa);
         
          Stage stage = (Stage) btnEditar.getScene().getWindow();
          Navegacion.Navegar("EmpresaDetail.fxml", stage);
        
    }

    @FXML
    private void navegarEliminarEmpresa(ActionEvent event) {
        
        Empresa selectedEmpresa = tblEmpresas.getSelectionModel().getSelectedItem();
        
        EmpresaRepository er = new EmpresaRepository();
        
        er.delete(selectedEmpresa.getId());
    }

    
}
