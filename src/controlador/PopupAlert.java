package controlador;

import javafx.scene.control.Alert;

public class PopupAlert {
    
    public static void noSeleccion(String selecc){
            Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningún "+ selecc);
               alert.showAndWait();
    }
    
      
    public static void noEmpresa(){
            Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No se seleccionó ningúna empresa");
               alert.showAndWait();   
    }
    
     public static void noPermiso(){
      Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("No tiene los permisos para acceder a esta funcion");
               alert.showAndWait(); 
     }
     
     public static void noLogged(){
       Alert alert = new Alert(Alert.AlertType.WARNING);
               alert.setTitle("Advertencia");
               alert.setHeaderText(null);
               alert.setContentText("Debe iniciar sesion para acceder a esta funcion");
               alert.showAndWait(); 
     }
    
}
