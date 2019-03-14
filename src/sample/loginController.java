package sample;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class loginController implements Initializable {
    private String x= "jaro";
    private String y= "opica";
    @FXML
    private TextField meno;
    @FXML
    private PasswordField heslo;
    @FXML
    private Label zabudolHeslo;
    @FXML
    private ImageView prihlas;
    @FXML
    private Label zle;


    @FXML
    private void prihlasSa() {
        String login= meno.getText().toLowerCase();
        String pass = heslo.getText();



        if (x.equals(login) && y.equals(pass)) {
            try {
                Stage stage = (Stage) meno.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Uvod.fxml"));

                Parent root = loader.load();
                stage.setTitle("Uvod");
                stage.setResizable(true);

                UvodController controller = loader.getController();


                Scene scene = new Scene(root,960,540);

                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Nepodarilo sa otvoriť súbor!!!");
            }
        } else {
            zle.setText("Zle uživatelske meno alebo heslo!!");
            meno.setText("");
            heslo.setText("");
        }



    }



    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}

