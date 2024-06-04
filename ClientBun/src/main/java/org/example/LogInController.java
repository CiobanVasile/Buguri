package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LogInController {

    private Stage stage;
    private Service service;

    private Programatori programator;
    private Tester tester;

    @FXML
    TextField emailTextField;
    @FXML
    TextField passwordTextField;

    public void setServer(Service server, Stage stage){
        this.service = server;
        this.stage = stage;
    }

    private void showErrorAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Eroare");
        alert.setHeaderText(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void LogIn(ActionEvent event){
        String email = emailTextField.getText();
        String password = passwordTextField.getText();
        if(service.getProgramatorByEmail(email) !=null){
            programator = service.getProgramatorByEmail(email);
            if(programator.getPassword().equals(password)){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/ProgramatoriView.fxml"));
                    Scene scene = new Scene(loader.load());
                    ProgramatorController controller = loader.getController();
                    controller.setServer(service,stage,programator);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    showErrorAlert("Eroare", e.getMessage());
                }
            }
            else{
                showErrorAlert("Eroare", "Parola incorecta");
            }
        }
        else if(service.getTesterByEmail(email) !=null){
            tester = service.getTesterByEmail(email);
            if(tester.getPassword().equals(password)){
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/TesteriView.fxml"));
                    Scene scene = new Scene(loader.load());
                    TesterController controller = loader.getController();
                    controller.setServer(service,stage,tester);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    showErrorAlert("Eroare", e.getMessage());
                }
            }
            else{
                showErrorAlert("Eroare", "Parola incorecta");
            }
        }
        else{
            showErrorAlert("Eroare", "Email incorect");
        }

   }

    public void goToSignUp(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org.example/SignInView.fxml"));
            Scene scene = new Scene(loader.load());
            SignUpController controller = loader.getController();
            controller.setServer(service,stage);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            showErrorAlert("Eroare", e.getMessage());
        }
    }
}
