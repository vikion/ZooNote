package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

class ResetPass3 extends AnchorPane  {
    ResetPass3() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("layout/ResetPass3.fxml"));

        fxmlLoader.setRoot(this);

        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

