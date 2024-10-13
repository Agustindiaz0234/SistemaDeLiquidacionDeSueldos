/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlador;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

       
        private static BorderPane rootLayout;
        private static final String BASE_PATH = "/vista/";
                
    @Override
    public void start(Stage primaryStage) {
        
    

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/vista/Principal.fxml"));
           rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            showContent("Menu.fxml");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

     public static void showContent(String viewPath) {
         
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource(BASE_PATH +viewPath));
            Pane newContent = (Pane) loader.load();
            rootLayout.setCenter(newContent);  // Cambia solo el centro del BorderPane
        } catch (IOException e) {
            System.out.println("Error al cargar la vista: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        launch(args);
    }

}