package pdfsquire;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class DialogController {
    @FXML
    private Text textInfo;
    @FXML
    private TextField textField;
    @FXML
    private Button submitButton;

    public void initDialog(DialogStates state) {
        textInfo.setText(state.toString());
    }
}
