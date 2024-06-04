package org.example;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ProgramatorController {
    private Service service;
    private Stage stage;
    private Programatori programator;

    public ProgramatorController() {

    }

    public void setServer(Service server, Stage stage,Programatori programator){
        this.programator = programator;
        this.service = server;
        this.stage = stage;
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
