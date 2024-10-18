/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controlador;

import java.net.URL;
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

/**
 * FXML Controller class
 *
 * @author diaza
 */
public class EmpresaController implements Initializable {

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
    
    private static ObservableList<Empresa> empresas = FXCollections.observableArrayList();; 
    @FXML
    private TableColumn<Empresa, Void> columBoton;
    @FXML


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
        this.tblEmpresas.setItems(empresas);
    }    


    @FXML
    private void navegarAgregarEmpresa(ActionEvent event) {
          
          Stage stage = (Stage) btnAgregar.getScene().getWindow();
          Navegacion.Navegar("agregarEmpresa.fxml", stage);
    }
    
     public static void agregarEmpresas(Empresa e) {
      EmpresaController.empresas.add(e);
    }
    
    private void handleButton(Empresa empresa){
     System.out.println("Boton presionado para la empresa " + empresa.getId());
    }

    
}
