package sample;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ResetPass implements Initializable {
   /* ObservableList vyber= FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> cb;*/

   @FXML
   private ImageView back;

    @FXML
    private TextField loginRess;

    @FXML
    private Label ErrorResPass;

   @FXML
   private void odosli() throws SQLException {
        String xLogin=loginRess.getText().toLowerCase();

       String sqlId = "SELECT id_pouzivatel FROM pouzivatel WHERE username = ?" ;


       Connection connection = ConnectionClass.getConnection();

       PreparedStatement statementForId = connection.prepareStatement(sqlId);
       statementForId.setString(1, xLogin);
       ResultSet Id = statementForId.executeQuery();
       Id.next();
       if (!Id.isClosed() || !Id.isClosed()) {
           Messages ResetPass = new Messages("Dôležitá", "Zabudnuté heslo", xLogin, 1, Id.getInt(1));
       }
       else{
           ErrorResPass.setText("Takéto uživateľske meno neexistuje");
           loginRess.setText("");
       }
   }

   @FXML
    private void spat(){
        try {
            Stage stage = (Stage) back.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/sample.fxml"));
            stage.setTitle("Prihlásenie");

            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Nepodarilo sa nacitat Reset Pass");
        }
    }

    public void initialize(URL location, ResourceBundle resources) {


    }


   /* private void LoadData(){
        vyber.addAll(vyber);
        String a="Ošetrovateľ ";
        String b="Opravar";
        vyber.addAll(a,b);
        cb.getItems().addAll(vyber);
    }
*/

}
