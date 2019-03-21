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

    private ResultSet accName,accSurname;

    public static String nameSurname;

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
        username.next();

        String sqlAccountType = "SELECT typ_konta FROM pouzivatel WHERE username = ? AND password = ?";
        String sqlName =        "SELECT meno FROM pouzivatel WHERE username = ? AND password = ?";
        String sqlSurname =     "SELECT priezvisko FROM pouzivatel WHERE username = ? AND password = ?";

        PreparedStatement statementForPassword = connection.prepareStatement(sqlPassword);
        statementForPassword.setString(1, login);
        statementForPassword.setString(2, pass);
        ResultSet password = statementForPassword.executeQuery();
        password.next();

        PreparedStatement statementForAccountType = connection.prepareStatement(sqlAccountType);
        statementForAccountType.setString(1,login);
        statementForAccountType.setString(2, pass);
        ResultSet accountType = statementForAccountType.executeQuery();
        accountType.next();

        PreparedStatement statementForName = connection.prepareStatement(sqlName);
       statementForName.setString(1,login);
        statementForName.setString(2, pass);
        accName = statementForName.executeQuery();
        accName.next();
        if (!accName.isClosed()) {
            nameSurname = accName.getString(1);
        }

        PreparedStatement statementForSurname = connection.prepareStatement(sqlSurname);
       statementForSurname.setString(1,login);
        statementForSurname.setString(2, pass);
        accSurname = statementForSurname.executeQuery();
        accSurname.next();

        if (!accSurname.isClosed()) {
            nameSurname = nameSurname + " " + accSurname.getString(1);
        }

        if (!accSurname.isClosed() || !accName.isClosed()) {
            if ((username.getString(1)).equals(login) && (password.getString(1)).equals(pass)) {
                if (accountType.getString(1).equals("admin")) {
                    try {
                        Stage stage = (Stage) meno.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Admin.fxml"));
                        stage.setTitle("ZooNote");


                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Nepodarilo sa nacitat AdminLayout");
                    }
                }

                if (accountType.getString(1).equals("opravar")) {
                    try {
                        Stage stage = (Stage) prihlas.getScene().getWindow();
                        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Mechanic.fxml"));
                        stage.setTitle("ZooNote");

                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println(accountType.getString(1));
                        System.out.println("Nepodarilo sa nacitat RepairsLayout");
                    }
                }
                if (accountType.getString(1).equals("osetrovatel")) {
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
            }
        } else {
            zle.setText("Zle uživatelske meno alebo heslo!!");
            meno.setText("");
            heslo.setText("");
        }
    }

    /*public String  getNameSurname(){
        //System.out.println(nameSurname+" GET");
        return nameSurname;
    }
    public void setNameSurname(String nameSurname){
        contentController.menoPriezvisko.setText(nameSurname);
    }*/

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

