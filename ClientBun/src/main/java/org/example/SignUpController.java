package org.example;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignUpController {

    private Stage stage;
    private Service service;

    private Programatori programator;
    private Tester tester;

    @FXML
    TextField emailTextFieldProgramator;
    @FXML
    TextField passwordTextFieldProgramator;
    @FXML
    TextField emailTextFieldTester;
    @FXML
    TextField passwordTextFieldTester;

    public void setServer(Service server, Stage stage){
        this.service = server;
        this.stage = stage;
    }

    public void SignUpProgramator(){
        try {
            String email = emailTextFieldProgramator.getText();
            String password = passwordTextFieldProgramator.getText();
            Programatori programator = new Programatori(email, password);
            service.addProgramator(programator);
            emailTextFieldProgramator.clear();
            passwordTextFieldProgramator.clear();
            emailTextFieldTester.clear();
            passwordTextFieldTester.clear();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void SignUpTester(){
        try {
            String email = emailTextFieldTester.getText();
            String password = passwordTextFieldTester.getText();
            Tester tester = new Tester(email, password);
            service.addTester(tester);
            emailTextFieldProgramator.clear();
            passwordTextFieldProgramator.clear();
            emailTextFieldTester.clear();
            passwordTextFieldTester.clear();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void goBacktoLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org.example/LogInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LogInController controller = fxmlLoader.getController();
        controller.setServer(service, stage);
        stage.setScene(scene);
        stage.show();
    }

}
