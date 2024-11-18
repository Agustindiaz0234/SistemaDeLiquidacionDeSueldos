package controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import modelo.Empresa;
import repository.EmpresaRepository;


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
        this.empresaRepository = new EmpresaRepository();
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(isEdit){
            
            this.txtNombre.setText(empresa.getNombre());
             this.txtDireccion.setText(empresa.getDireccion());
             this.txtTelefono.setText(String.valueOf(empresa.getTelefono()));
            
        }
    }    

    @FXML
    private void handleGuardar(ActionEvent event) {
        String nombre = txtNombre.getText();
        String direccion = txtDireccion.getText();
        int telefono = Integer.parseInt(txtTelefono.getText());
        
       
        if(isEdit){
        Empresa empresa1 = new Empresa(empresa.getId(),nombre, direccion, telefono);
            updateEmpresa(empresa1);
        
        }else{
          Empresa empresa1 = new Empresa(nombre, direccion, telefono);  
            insertarEmpresa(empresa1);
        
        }
            MainLayoutController mainController = Main.getMainController();
            mainController.showEmpresa();
    }
    
    
    public void insertarEmpresa(Empresa empresa){
        System.out.println("Aqui funciona");
        this.empresaRepository.insert(empresa);
       
    }
    
        public void updateEmpresa(Empresa empresa){
        
        this.empresaRepository.update(empresa);
       
    }
        
        public static void setEditMode(Empresa emp){
        
            isEdit = true;
            
            empresa =  emp;
            
        }
        
        public static void setFalse(){
        isEdit = false;
        }
    
}