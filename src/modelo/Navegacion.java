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
    
    private static final String BASE_PATH ="/vista/";
    
    
    public static Pane Navegar(String fxml){
    
        try{
        FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(BASE_PATH + fxml));
        Pane nuevaVentana = (Pane) loader.load();
        return nuevaVentana;
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
        return null;
    
    }
    
}
