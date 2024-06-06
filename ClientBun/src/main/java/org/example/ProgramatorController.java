package org.example;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class ProgramatorController implements Observer {
    private Timer timer;
    @FXML
    public TextField DenumireCautare;
    private Service service;
    private Stage stage;
    private Programatori programator;
    @FXML
    public ListView<Buguri> ListViewBuguri;

    public ProgramatorController() {

    }

    public void setServer(Service server, Stage stage,Programatori programator) throws SQLException {
        this.programator = programator;
        this.service = server;
        this.stage = stage;
        service.addObserver(this);
        init();
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    try {
                        initmodels();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
            }
        }, 0, 3500);
    }

    public void init() throws SQLException {
        initmodels();
    }

    public void initmodels() throws SQLException {
        ListViewBuguri.getItems().clear();
        ListViewBuguri.getItems().setAll((Collection<? extends Buguri>) service.getAllBuguri());
    }

    public void deleteBug() throws SQLException {
        Buguri bug = ListViewBuguri.getSelectionModel().getSelectedItem();
        service.deleteBug(bug.getId());
        try {
            initmodels();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void FindBug(){
        String denumire = DenumireCautare.getText();
        ListViewBuguri.getItems().clear();
        ListViewBuguri.getItems().setAll((Collection<? extends Buguri>) service.getBuguriByDenumire(denumire));
    }

    public void goBacktoLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org.example/LogInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LogInController controller = fxmlLoader.getController();
        controller.setServer(service, stage);
        stage.setScene(scene);
        stage.show();
    }

    public void sortBuguriAfterDate(){
        ListViewBuguri.getItems().clear();
        ListViewBuguri.getItems().setAll((Collection<? extends Buguri>) service.sortBuguriAfterDate());
    }

    @Override
    public void update() throws SQLException {
        initmodels();
    }
}
