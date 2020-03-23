package pdfsquire;

import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

public class Controller {
    private Stage stage;
    private Button[] buttons;

    private final FileChooser fileChooser = new FileChooser();
    private PDDocument document;
    private File file;

    @FXML
    private ImageView pdfImageView;
    @FXML
    private Text pdfText;
    @FXML
    private Button rotateLeftButton, rotateRightButton, mergeButton, splitButton, extractButton;

    public void initController(Stage stage) {
        this.stage = stage;
        this.buttons = new Button[] {rotateLeftButton, rotateRightButton, mergeButton, splitButton, extractButton};
    }

    public void openFile(ActionEvent actionEvent) throws IOException {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        file = fileChooser.showOpenDialog(this.stage);
        if (file != null) {
            document = PDDocument.load(file);
            this.stage.setTitle("PDFsquire - " + file.getName());

            LayoutManipulation.changeImageView(pdfImageView, pdfText, file.getName());
            if (buttons[0].isDisabled()) {
                for (Button button : this.buttons) {
                    LayoutManipulation.changeButtonStatus(button);
                }
            }
        }
    }

    public void splitFile(ActionEvent actionEvent) {
    }

    public void mergeFile(ActionEvent actionEvent) {
    }

    public void extractFile(ActionEvent actionEvent) {
    }

    public void rotateFileLeft(ActionEvent actionEvent) {
        System.out.println("[!] Rotated left.");
    }

    public void rotateFileRight(ActionEvent actionEvent) {
        System.out.println("[!] Rotated right.");
    }

    public void exitApplication(ActionEvent actionEvent) {
        this.stage.close();
    }
}
