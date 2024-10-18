package controlador;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import modelo.Navegacion;

public class Main extends Application {            
    @Override
    public void start(Stage primaryStage) {
     Navegacion.cargarVistaPrincipalConContenido("Menu.fxml", primaryStage);
    }
    public static void main(String[] args) {
        launch(args);
    }
}