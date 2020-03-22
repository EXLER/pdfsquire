package pdfsquire;

import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Controller {
    private Stage stage;

    private final FileChooser fileChooser = new FileChooser();

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void openFile(ActionEvent actionEvent) {
        fileChooser.setTitle("Open PDF file");
        fileChooser.showOpenDialog(this.stage);
    }
}
