
package controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.List;
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
import javafx.stage.DirectoryChooser;
import modelo.Empleado;
import modelo.Recibo;
import repository.ReciboRepository;

public class ReciboListController implements Initializable {

    @FXML
    private TableView<Recibo> tblRecibo;
    @FXML
    private TableColumn columNombre;
    @FXML
    private TableColumn columBtnDescargar;
    
    private static Empleado empleado;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    this.columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
    
    columBtnDescargar.setCellFactory(param -> new TableCell<Recibo, Void>() {
   private final Button btn = new Button("Descargar");

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            
            if (empty) {
                setGraphic(null);
            } else {
                final Recibo recibo = getTableView().getItems().get(getIndex());
                
                btn.setOnAction(event -> handleButton(recibo));
                
                setGraphic(btn);
            }
        }
    });
    
    cargarArchivos(empleado.getId());
    }    
    
     private void cargarArchivos(int empleadoId) {
        List<Recibo> recibos = ReciboRepository.getAll(empleadoId);
        ObservableList<Recibo> data = FXCollections.observableArrayList(recibos);
        tblRecibo.setItems(data);
    }
    
    
    public static void handleButton(Recibo recibo){
        
    DirectoryChooser directoryChooser = new DirectoryChooser();
    directoryChooser.setTitle("Seleccionar Carpeta de Descarga");

    File selectedDirectory = directoryChooser.showDialog(null);

    if (selectedDirectory != null) {
        try {
            
            File archivoDescarga = new File(selectedDirectory, recibo.getNombre() + ".pdf");
          
            Files.write(archivoDescarga.toPath(), recibo.getArchivo());

            System.out.println("Archivo descargado en: " + archivoDescarga.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    } else {
        System.out.println("No se seleccionó ninguna carpeta.");
    }
    
    }
    
    public static void setEmpleado(Empleado emp){
    empleado = emp;
    }

    @FXML
    private void navegarEliminarRecibo(ActionEvent event) {

      Recibo selectedRecibo = tblRecibo.getSelectionModel().getSelectedItem();
        
        if(selectedRecibo == null){
          Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningún recibo");
               alert.showAndWait();
         }else{
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmación de eliminación");
            confirmationAlert.setHeaderText(null);
            confirmationAlert.setContentText("¿Estás seguro de que deseas eliminar este recibo?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                ReciboRepository recibo = new ReciboRepository();
                recibo.delete(selectedRecibo.getId());
                cargarArchivos(empleado.getId());
        }
    }
}
}
