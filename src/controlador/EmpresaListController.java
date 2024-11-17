package controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import modelo.Empresa;
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
    
    MainLayoutController mainController = Main.getMainController();
    
    private EmpresaRepository empresaRepository;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;

    public EmpresaListController() {
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
            
            Empresa[] empresas = empresaRepository.getAll(null);
            tblEmpresas.setItems(FXCollections.observableArrayList(empresas));
        
    }


    @FXML
    private void navegarAgregarEmpresa(ActionEvent event) { 
        EmpresaDetailController.setFalse();
        mainController.showAgregarEmpresa();
    }
    

    private void handleButton(Empresa empresa){
        mainController.showEmpleadoList(empresa); 
    }

   
    @FXML
    private void navegarEditarEmpresa(ActionEvent event) {
        
         Empresa selectedEmpresa = tblEmpresas.getSelectionModel().getSelectedItem();
      
         if(selectedEmpresa == null){
               PopupAlert.noEmpresa();
         }else{
                EmpresaDetailController.setEditMode(selectedEmpresa);
                mainController.showAgregarEmpresa();
         }
    }

    @FXML
    private void navegarEliminarEmpresa(ActionEvent event) {
        
        Empresa selectedEmpresa = tblEmpresas.getSelectionModel().getSelectedItem();
        
          if(selectedEmpresa == null){
           PopupAlert.noEmpresa();
          }else{
        
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmación de eliminación");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("¿Estás seguro de que deseas eliminar esta empresa?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
                  EmpresaRepository er = new EmpresaRepository();
                  er.delete(selectedEmpresa.getId());
                  cargarDatosDesdeBD();
          }
          }
    }

    
}
