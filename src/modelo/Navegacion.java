/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author diaza
 */
public class Navegacion {
    
    
    
    public static void Navegar(String fxml, Stage stage){
    
        try{
        FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(fxml));
        Pane nuevaVentana = (Pane) loader.load();
        Scene nuevaEscena = new Scene(nuevaVentana);
        stage.setScene(nuevaEscena);
        stage.show();
        
        
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
    
    }
    
}
