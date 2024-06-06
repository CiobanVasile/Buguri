package org.example;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Timer;
import java.util.TimerTask;

public class TesterController implements Observer {
    private Timer timer;
    @FXML
    public ListView<Buguri> ListViewBuguri;

    @FXML
    public ListView<Istoric_Buguri> ListViewIstoricBuguri;
    @FXML
    public TextField Denumire;
    @FXML
    public TextField Descriere;
    @FXML
    public TextField DescriereUpdate;
    @FXML
    public TextField DenumireUpdate;
    @FXML
    public TextField DenumireCautare;
    private boolean newDataAvailable = false;
    private Service service;
    private Stage stage;
    private Tester testeri;
    public TesterController() {

    }

    public void setServer(Service server, Stage stage, Tester testeri) throws SQLException {
        this.testeri = testeri;
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
        }, 0, 3500);// Schedule refresh every 2 seconds (2000 milliseconds)
    }


    public void init() throws SQLException {
        initmodels();

    }

    public void initmodels() throws SQLException {
        ListViewBuguri.getItems().clear();
        ListViewBuguri.getItems().setAll((Collection<? extends Buguri>) service.getAllBuguri());

        ListViewIstoricBuguri.getItems().clear();
        ListViewIstoricBuguri.getItems().setAll((Collection<? extends Istoric_Buguri>) service.getAllIstoricBuguri());

    }

    public void AdaugaBug(ActionEvent actionEvent) {
        String denumire = Denumire.getText();
        String descriere = Descriere.getText();
        Buguri bug = new Buguri(denumire,descriere, LocalDateTime.now().toString());
        Istoric_Buguri istbug = new Istoric_Buguri(denumire,descriere, LocalDateTime.now().toString());
        System.out.println(bug);
        try {
            service.addBug(bug);
            service.addIstoricBug(istbug);
            initmodels();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateBug(ActionEvent actionEvent) {
        String denumire = DenumireUpdate.getText();
        String descriere = DescriereUpdate.getText();
        Buguri bug = ListViewBuguri.getSelectionModel().getSelectedItem();
        bug.setDenumire(denumire);
        bug.setDescriere(descriere);
        Istoric_Buguri istbug = new Istoric_Buguri(bug.getDenumire(),bug.getDescriere(), LocalDateTime.now().toString());
        System.out.println(bug.getId());
        System.out.println(istbug.getId());
        try {
            service.updateBug(bug.getId(),bug);
            service.updateIstoricBug(istbug.getId(),istbug);
            initmodels();
        } catch (SQLException e) {
            e.printStackTrace();}
        }
    public void goBacktoLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org.example/LogInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LogInController controller = fxmlLoader.getController();
        controller.setServer(service, stage);
        stage.setScene(scene);
        stage.show();
    }

    public void FindBug(){
        String denumire = DenumireCautare.getText();
        ListViewBuguri.getItems().clear();
        ListViewBuguri.getItems().setAll((Collection<? extends Buguri>) service.getBuguriByDenumire(denumire));
    }

    @Override
    public void update() throws SQLException {
        initmodels();
    }
}
