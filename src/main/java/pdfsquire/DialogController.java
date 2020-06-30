package pdfsquire;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class DialogController {
    private Stage stage;
    private Text flashText;

    private DialogActions action;
    private File currentFile;

    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private Text textInfo;
    @FXML
    private TextField textField;

    public void initDialog(Stage stage, Text flashText, File file, DialogActions action) {
        this.stage = stage;
        this.flashText = flashText;
        this.action = action;
        this.currentFile = file;
        textInfo.setText(action.toString());
    }

    public void dialogAction(ActionEvent actionEvent) throws IOException {
        File saveFile = null;
        PDDocument document = PDDocument.load(this.currentFile);
        String[] userInput = textField.getText().split("-");
        int start, end;

        if (userInput.length == 1) {
            start = Integer.parseInt(userInput[0]);
            end = start;
        } else {
            start = Integer.parseInt(userInput[0]);
            end = Integer.parseInt(userInput[1]);
        }

        if (start < 1 || document.getNumberOfPages() + 1 > end) {
            if (this.action == DialogActions.EXTRACT) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text file", "*.txt"));

                PDFTextStripper stripper = new PDFTextStripper();
                stripper.setStartPage(start);
                stripper.setEndPage(end);
                String pdfText = stripper.getText(document);

                saveFile = fileChooser.showSaveDialog(this.stage);
                FileWriter fw = new FileWriter(saveFile);
                fw.write(pdfText);
                fw.close();

            } else if (this.action == DialogActions.SPLIT) {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

                Splitter splitter = new Splitter();
                splitter.setSplitAtPage(end - start + 1);
                splitter.setStartPage(start);
                splitter.setEndPage(end);
                List<PDDocument> splitList = splitter.split(document);

                for (PDDocument doc : splitList) {
                    saveFile = fileChooser.showSaveDialog(this.stage);
                    doc.save(saveFile.getAbsolutePath());
                    doc.close();
                }
            } else {
                fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
                for (int i = start; i < end + 1; i++) {
                    if (this.action == DialogActions.ROTATE_LEFT) {
                        document.getPage(i-1).setRotation(-90);
                    }
                    else {
                        document.getPage(i-1).setRotation(90);
                    }
                }

                saveFile = fileChooser.showSaveDialog(this.stage);
                document.save(saveFile.getAbsolutePath());
                document.close();
            }
        }
        else {
            LayoutManipulation.flashInfoText(this.flashText, "Invalid page number given.");
            return;
        }

        if (saveFile != null) {
            LayoutManipulation.flashInfoText(this.flashText, "File saved as: " + saveFile.getName());
        }
        else {
            LayoutManipulation.flashInfoText(this.flashText, "An unknown error occurred.");
        }
        this.stage.close();
    }
}
