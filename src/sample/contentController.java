package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class contentController extends loginController implements Initializable {

    @FXML
    BorderPane mainPane;
    @FXML
    Label menoPriezvisko;

    @FXML
    Button hover;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menoPriezvisko.setText(sample.loginController.nameSurname);
        Home home = new Home();
        mainPane.setCenter(home);
    }

    @FXML
    public void onHomeBtnClick() {
        Home home = new Home();
        mainPane.setCenter(home);
    }

    @FXML
    public void onFinancesBtnClick(){
        Finances finances = new Finances();
        mainPane.setCenter(finances);
    }

    @FXML
    public void onAnimalsBtnClick(){
        Animals animals = new Animals();
        mainPane.setCenter(animals);
    }

    @FXML
    public void onInvoicesBtnClick(){
        Invoices invoices = new Invoices();
        mainPane.setCenter(invoices);
    }

    @FXML
    public void onRepairsBtnClick(){
        Repairs repairs = new Repairs();
        mainPane.setCenter(repairs);
    }

    @FXML
    public void onUsersBtnClick(){
        Users users = new Users();
        mainPane.setCenter(users);
    }

    @FXML
    public void onLogoutBtnClick() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("layout/sample.fxml"));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) menoPriezvisko.getScene().getWindow();
        stage.setScene(scene);
    }


}
