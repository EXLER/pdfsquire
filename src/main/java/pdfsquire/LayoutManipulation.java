package pdfsquire;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Timer;

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

    static public void flashInfoText(Text text, String message) {
        text.setVisible(true);
        text.setText(message);

        Timer t = new java.util.Timer();
        t.schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        text.setVisible(false);
                        t.cancel();
                    }
                },
                2000
        );
    }

    static public void showDialogWindow(Stage dialog, Scene root, String windowTitle) {
        dialog.setTitle(windowTitle);
        dialog.setScene(root);
        dialog.show();
    }
}
