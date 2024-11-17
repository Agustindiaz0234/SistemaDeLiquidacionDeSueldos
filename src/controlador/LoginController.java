package controlador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Usuario;
import repository.UsuarioRepository;

public class LoginController {

    @FXML
    private TextField txtUserName;
    @FXML
    private PasswordField txtPassword;

    private UsuarioRepository usuarioRepository;
    
   

    public LoginController() {
        this.usuarioRepository = new UsuarioRepository();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String userName = txtUserName.getText();
        String password = txtPassword.getText();
        
  
        if (userName.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password.");
            return;
        }

        if (userName.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password.");
            return;
        }

                Usuario usuario = usuarioRepository.getByUserName(userName);
        
        if (usuario != null && usuario.getPassword().equals(password)) {
   
             Session.setCurrentUser(usuario);
             MainLayoutController mainController = Main.getMainController();
             mainController.showMenu();

        } else {
            showError("Invalid username or password.");
        }
    }

    private void showError(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Login Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
