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
import java.sql.*;
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


    @FXML
    private void prihlasSa() throws SQLException {
        String login= meno.getText().toLowerCase();
        String pass = heslo.getText();

        String sqlUsername = "SELECT username FROM pouzivatel WHERE username = ? AND password = ?";
        String sqlPassword = "SELECT password FROM pouzivatel WHERE username = ? AND password = ?";

        Connection connection = ConnectionClass.getConnection();

        PreparedStatement statementForUsername = connection.prepareStatement(sqlUsername);
        statementForUsername.setString(1, login);
        statementForUsername.setString(2, pass);
        ResultSet username = statementForUsername.executeQuery();
        username.first();

        PreparedStatement statementForPassword = connection.prepareStatement(sqlPassword);
        statementForPassword.setString(1, login);
        statementForPassword.setString(2, pass);
        ResultSet password = statementForPassword.executeQuery();
        password.first();



        if ((username.getString(1)).equals(login) && (password.getString(1)).equals(pass)) {
            try {
                Stage stage = (Stage) prihlas.getScene().getWindow();
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

