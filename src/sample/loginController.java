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

    public static String nameSurname;

    @FXML
    private void prihlasSa(){
        String login= meno.getText();
        String pass = heslo.getText();

        RequestUsersData request = new RequestUsersData();
        User user = request.getUsersData(login, pass);
        System.out.println(login);

        if (user != null) {
            if (user.getType().equals("admin")) {
                try {
                    Stage stage = (Stage) meno.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Admin.fxml"));
                    stage.setTitle("ZooNote");

                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    stage.show();
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Nepodarilo sa nacitat AdminLayout");
                }
            }

            if (user.getType().equals("opravar")) {
                    try {
                        Stage stage = (Stage) prihlas.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Mechanic.fxml"));
                        stage.setTitle("ZooNote");

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println(user.getType());
                        System.out.println("Nepodarilo sa nacitat RepairsLayout");
                    }
                }
                if (user.getType().equals("osetrovatel")) {
                    try {
                        Stage stage = (Stage) meno.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Attendant.fxml"));
                        stage.setTitle("ZooNote");

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Nepodarilo sa nacitat AttendantLayout");
                    }
                }
            } else{
                zle.setText("Zle uživatelske meno alebo heslo!!");
                meno.setText("");
                heslo.setText("");
        }
    }


    @FXML
    private void ZabudolPass(){
        try {
            Stage stage = (Stage) zabudolHeslo.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/ResetPass.fxml"));
            stage.setTitle("Obnovenie Hesla");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa nacitat Reset Pass");
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

}

