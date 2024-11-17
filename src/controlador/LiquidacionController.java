package controlador;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import modelo.Empleado;
import modelo.Empresa;
import modelo.PDFGenerator;

public class LiquidacionController implements Initializable {
    
    private static Empresa empresa;
    private static Empleado empleado;


    double sueldoBruto;
    double descuentoJubilacion;
    double descuentoLey;
    double descuentoObraSocial;
  
    @FXML
    private TextField txtPagoHora;
    @FXML
    private TextField txtHoras;
    
    String mesActual = LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, new Locale("es"));
    
    String nombreArchivo = "Recibo " + empleado.getNombre() + " " + empleado.getApellido() + " " + mesActual;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    

    @FXML
    private void handleCalcular(){
        
        int pago = Integer.parseInt(txtPagoHora.getText());
        int horas = Integer.parseInt(txtHoras.getText());

        sueldoBruto = pago * horas;
        descuentoJubilacion = sueldoBruto * 0.11;
        descuentoLey = sueldoBruto * 0.03;
        descuentoObraSocial = sueldoBruto * 0.03;
    }
    
    @FXML
    private void generarRecibo(ActionEvent event) {
      
         PDFGenerator.generarRecibo(
            nombreArchivo, mesActual,
            empleado.getNombre(), empleado.getApellido(), empleado.getId(),
            empresa.getNombre(), String.valueOf(empresa.getTelefono()), empresa.getDireccion(),
            sueldoBruto, descuentoJubilacion, descuentoLey, descuentoObraSocial
        );
    }
    
    
    public static void recuperarEmpresaEmpleado(Empresa emp, Empleado empl){
    
        empresa = emp;
        empleado= empl;    
    }
    
    
}
