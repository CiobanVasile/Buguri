package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org.example/LogInView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LogInController controller = fxmlLoader.getController();
        RepositoryProgramatoriORM repoProgramatori = new RepositoryProgramatoriORM();
        RepositoryTesteriORM repoTesteri = new RepositoryTesteriORM();
        RepositoryBuguriORM repoBuguri = new RepositoryBuguriORM();
        RepositoryIstoricBuguriORM repoIstoricBuguri = new RepositoryIstoricBuguriORM();
        Service service =  new Service(repoProgramatori, repoBuguri,repoTesteri,repoIstoricBuguri);
        controller.setServer(service, stage);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}