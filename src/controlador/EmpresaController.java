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

    private Button btnMenu;
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
    
    private ObservableList<Empresa> empresas; 
    @FXML
    private TableColumn<Empresa, Void> columBoton;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        empresas = FXCollections.observableArrayList();
        
        this.columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        this.columDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        this.columnTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        
        // Configurar la columna de botones
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

       
        Empresa e = new Empresa(1,"Aguscorp","Parana344", 345448566);
        Empresa e1 = new Empresa(2,"5competencia","mitre444", 345466554);
        Empresa e2 = new Empresa(3,"Autoservicio santa rosa","Parana924", 345454321);
        
        this.empresas.add(e);
        this.empresas.add(e1);
        this.empresas.add(e2);
        this.tblEmpresas.setItems(empresas);
    }    


    @FXML
    private void navegarAgregarEmpresa(ActionEvent event) {
        

        Empresa e4 = new Empresa(4,"Holamundo", "Messi", 34544566);
        
        this.empresas.add(e4);
        this.tblEmpresas.setItems(empresas);
        
       
//Stage stage = (Stage) btnAgregar.getScene().getWindow();
          //Navegacion.Navegar("RegistrarEmpresa.fxml", stage);
    }
    
    
    private void handleButton(Empresa empresa){
    
        System.out.println("Boton presionado para la empresa " + empresa.getId());
    }
    
}
