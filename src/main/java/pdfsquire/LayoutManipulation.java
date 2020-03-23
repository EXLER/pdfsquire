package pdfsquire;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class LayoutManipulation {

    static public void changeImageView(ImageView imageView, Text text, String filename) {
        imageView.setImage(new Image("pdf.png"));
        imageView.setFitWidth(118);
        imageView.setFitHeight(134);
        imageView.setLayoutX(140);
        imageView.setLayoutY(14);
        text.setText(filename);
    }

    static public void changeButtonStatus(Button button) {
        button.setDisable(!button.isDisabled());
    }
}
