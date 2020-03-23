package pdfsquire;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class MainController {
    private Stage primaryStage;
    private Scene dialogScene;
    private DialogController dialogController;

    private Button[] buttons;

    private final FileChooser fileChooser = new FileChooser();
    private File currentFile;

    @FXML
    private ImageView pdfImageView;
    @FXML
    private Text pdfText, flashText;
    @FXML
    private Button rotateLeftButton, rotateRightButton, mergeButton, splitButton, extractButton;

    public void initController(Stage stage) throws IOException {
        this.primaryStage = stage;
        this.buttons = new Button[] {rotateLeftButton, rotateRightButton, mergeButton, splitButton, extractButton};

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/dialog.fxml"));
        this.dialogScene = new Scene(loader.load(), 190, 80);

        this.dialogController = loader.getController();
    }

    public void openFile(ActionEvent actionEvent) throws IOException {
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

        currentFile = fileChooser.showOpenDialog(this.primaryStage);
        if (currentFile != null) {
            this.primaryStage.setTitle("PDFsquire - " + currentFile.getName());

            LayoutManipulation.changeImageView(pdfImageView, pdfText, currentFile.getName());
            if (buttons[0].isDisabled()) {
                for (Button button : this.buttons) {
                    LayoutManipulation.changeButtonStatus(button);
                }
            }
        }
    }

    public void splitFile(ActionEvent actionEvent) {
        this.dialogController.initDialog(DialogStates.SPLIT);
        LayoutManipulation.showDialogWindow(this.primaryStage, this.dialogScene, "Split file");
    }

    public void mergeFile(ActionEvent actionEvent) throws IOException {
        PDFMergerUtility pdfMerger = new PDFMergerUtility();
        pdfMerger.addSource(currentFile);

        List<File> mergeFiles = fileChooser.showOpenMultipleDialog(this.primaryStage);
        if (mergeFiles != null) {
            for (File file : mergeFiles) {
                pdfMerger.addSource(file);
            }
        }

        File f = fileChooser.showSaveDialog(this.primaryStage);
        pdfMerger.setDestinationFileName(f.getAbsolutePath());
        pdfMerger.mergeDocuments(null);

        LayoutManipulation.flashInfoText(flashText, "Merged file saved as: " + f.getName());
    }

    public void extractFile(ActionEvent actionEvent) {
        LayoutManipulation.showDialogWindow(this.primaryStage, this.dialogScene, "Extract pages");
    }

    public void rotateFileLeft(ActionEvent actionEvent) {
        System.out.println("[!] Rotated left.");
    }

    public void rotateFileRight(ActionEvent actionEvent) {
        System.out.println("[!] Rotated right.");
    }

    public void exitApplication(ActionEvent actionEvent) {
        this.primaryStage.close();
    }
}
