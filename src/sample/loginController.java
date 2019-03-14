package sample;


import connectivity.ConnectionClass;
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
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class loginController implements Initializable {

    ConnectionClass connectionClass = new ConnectionClass();
    Connection connection = connectionClass.getConnection();

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
    private void prihlasSa() throws SQLException {
        String login= meno.getText().toLowerCase();
        String pass = heslo.getText();

        String getUsernameSql = "SELECT username FROM uzivatel WHERE username = '" + login + "';";
        String getPasswordSql = "SELECT password FROM uzivatel WHERE password = '" + pass + "';";

        Statement statementUsername = connection.createStatement();
        ResultSet username = statementUsername.executeQuery(getUsernameSql);
        username.first();

        Statement statementPassword = connection.createStatement();
        ResultSet password = statementPassword.executeQuery(getPasswordSql);
        password.first();


        System.out.println(login);
        System.out.println(pass);
        System.out.println(username.getString(1));
        System.out.println(password.getString(1));


        if ((username.getString(1)).equals(login) && (password.getString(1)).equals(pass)) {
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

