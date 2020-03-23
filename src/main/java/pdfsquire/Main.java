package pdfsquire;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/pdfsquire.fxml"));
        Parent root = loader.load();

        MainController mainController = loader.getController();
        mainController.initController(primaryStage);

        primaryStage.setTitle("PDFsquire");
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.getIcons().add(new Image("icon.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
