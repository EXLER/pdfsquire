package pdfsquire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("[!] Initializing PDFsquire...");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/pdfsquire.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();
        mainController.initController(primaryStage);

        primaryStage.setTitle("PDFsquire");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("[!] Stopping PDFsquire...");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
