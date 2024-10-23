package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Empleados;
import modelo.Empresa;

public class ListarEmpleadoController implements Initializable {

    @FXML
    private Label lblNombreEmpresa;
    
    @FXML
    private TableView<Empleados> tblEmpleados;
    @FXML
    private TableColumn colNombre;
    @FXML
    private TableColumn colApellido;
    @FXML
    private TableColumn colTelefono;
    @FXML
    private TableColumn colMail;
    
    private static Empresa empresa;
    
   public static ObservableList<Empleados> listaEmpleados = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
           lblNombreEmpresa.setText(empresa.getNombre());
           
           this.colNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
           this.colApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
           this.colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
           this.colMail.setCellValueFactory(new PropertyValueFactory("mail"));
  
    }    
    
    public static void setEmpresa(Empresa emp) {   
      empresa = emp;
    }
}
