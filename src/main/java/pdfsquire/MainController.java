package pdfsquire;

import java.io.File;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.pdfbox.multipdf.PDFMergerUtility;

public class MainController {
    private Stage primaryStage;
    private final Stage dialogStage = new Stage();
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
        this.buttons = new Button[]{rotateLeftButton, rotateRightButton, mergeButton, splitButton, extractButton};

        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.initOwner(primaryStage);

        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/dialog.fxml"));
        this.dialogScene = new Scene(loader.load(), 190, 80);

        this.dialogController = loader.getController();

        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
    }

    public void openFile(ActionEvent actionEvent) throws IOException {
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
        this.dialogController.initDialog(this.dialogStage, flashText, currentFile, DialogActions.SPLIT);
        LayoutManipulation.showDialogWindow(this.dialogStage, this.dialogScene, "Split file");
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

        LayoutManipulation.flashInfoText(flashText, "File saved as: " + f.getName());
    }

    public void extractFile(ActionEvent actionEvent) {
        this.dialogController.initDialog(this.dialogStage, flashText, currentFile, DialogActions.EXTRACT);
        LayoutManipulation.showDialogWindow(this.dialogStage, this.dialogScene, "Extract pages");
    }

    public void rotateFileLeft(ActionEvent actionEvent) {
        this.dialogController.initDialog(this.dialogStage, flashText, currentFile, DialogActions.ROTATE_LEFT);
        LayoutManipulation.showDialogWindow(this.dialogStage, this.dialogScene, "Rotate pages left");
    }

    public void rotateFileRight(ActionEvent actionEvent) {
        this.dialogController.initDialog(this.dialogStage, flashText, currentFile, DialogActions.ROTATE_RIGHT);
        LayoutManipulation.showDialogWindow(this.dialogStage, this.dialogScene, "Rotate pages right");
    }
}
