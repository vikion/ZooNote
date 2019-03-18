package sample;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class contentController implements Initializable {

    @FXML
    BorderPane mainPane;
    @FXML
    Label menoPriezvisko;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menoPriezvisko.setText(sample.loginController.nameSurname);
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


}
