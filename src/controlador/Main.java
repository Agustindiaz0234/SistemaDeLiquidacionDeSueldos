package controlador;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {    
    
    private static MainLayoutController mainController; 
    
    @Override
    public void start(Stage primaryStage) {
    try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/vista/MainLayout.fxml"));
            BorderPane root = loader.load();

            mainController = loader.getController();

            Scene scene = new Scene(root, 800, 600);
            primaryStage.setScene(scene);
//            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
       public static MainLayoutController getMainController() {
        return mainController;
    }
    public static void main(String[] args) {
        launch(args);
    }
}