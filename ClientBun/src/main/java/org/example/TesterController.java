package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

public class TesterController {
    @FXML
    public ListView<Buguri> ListViewBuguri;
    @FXML
    public TextField Denumire;
    @FXML
    public TextField Descriere;
    private Service service;
    private Stage stage;
    private Tester testeri;

    public TesterController() {

    }

    public void setServer(Service server, Stage stage, Tester testeri) throws SQLException {
        this.testeri = testeri;
        this.service = server;
        this.stage = stage;
        init();
    }

    public void init() throws SQLException {
        initmodels();

    }

    public void initmodels() throws SQLException {
        ListViewBuguri.getItems().clear();
        ListViewBuguri.getItems().setAll((Collection<? extends Buguri>) service.getAllBuguri());
    }

    public void AdaugaBug(ActionEvent actionEvent) {
        String denumire = Denumire.getText();
        String descriere = Descriere.getText();
        Buguri bug = new Buguri(denumire,descriere, LocalDateTime.now().toString());
        System.out.println(bug);
        try {
            service.addBug(bug);
            initmodels();
        } catch (SQLException e) {
            e.printStackTrace();
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
