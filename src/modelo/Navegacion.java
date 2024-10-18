/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author diaza
 */
public class Navegacion {
    private static BorderPane rootLayout;
    private static final String BASE_PATH ="/vista/";
    
    
    public static void Navegar(String fxml, Stage stage){
    
        try{
        FXMLLoader loader = new FXMLLoader(Navegacion.class.getResource(BASE_PATH + fxml));
        Pane nuevaVentana = (Pane) loader.load();
        Scene nuevaEscena = new Scene(nuevaVentana);
        stage.setScene(nuevaEscena);
        stage.show();
        }catch(IOException e){
            System.out.print(e.getMessage());
        }
        
    
    }
    
        public static void cargarVistaPrincipalConContenido(String vistaCentro, Stage stage) {
        try {
            // Cargar la vista principal (BorderPane)
            FXMLLoader loaderPrincipal = new FXMLLoader();
            loaderPrincipal.setLocation(Navegacion.class.getResource(BASE_PATH + "Principal.fxml"));
            rootLayout = (BorderPane) loaderPrincipal.load();

            // Cargar el contenido que va en el centro del BorderPane
            FXMLLoader loaderContenido = new FXMLLoader();
            loaderContenido.setLocation(Navegacion.class.getResource(BASE_PATH + vistaCentro));
            Pane contenidoCentro = (Pane) loaderContenido.load();
            
            // Establecer el contenido en el centro del BorderPane
            rootLayout.setCenter(contenidoCentro);

            // Crear y mostrar la escena con el BorderPane como root
            Scene escena = new Scene(rootLayout);
            stage.setScene(escena);
            stage.show();
            
        } catch (IOException e) {
            System.out.println("Error al cargar la vista principal o el contenido: " + e.getMessage());
        }
    }

 public static void showContent(String viewPath) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Navegacion.class.getResource(BASE_PATH + viewPath));
            Pane newContent = (Pane) loader.load();
            rootLayout.setCenter(newContent);  // Actualiza solo el centro
        } catch (IOException e) {
            System.out.println("Error al cargar la vista: " + e.getMessage());
        }
    }   
}
