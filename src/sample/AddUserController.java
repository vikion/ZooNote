package sample;

import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AddUserController {

    @FXML
    private ImageView zaregistruj;
    @FXML
    private TextField menoField;
    @FXML
     private TextField priezviskoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField hesloField;
    @FXML
    private TextField typField;
    @FXML
    private Label zle;

    private String data [] = new String[6];

    public void onBackBtnClick() throws IOException {
        Stage stage = (Stage) zaregistruj.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/Admin.fxml"));
        stage.setTitle("Admin");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void getFromFields(){
        data[0] = menoField.getText();
        data[1] = priezviskoField.getText();
        data[2] = usernameField.getText();
        data[3] = emailField.getText();
        data[4] = hesloField.getText();
        data[5] = typField.getText();
        register();
    }
    private void register(){
        Connection connection = ConnectionClass.getConnection();
        String insertQuery = "INSERT INTO pouzivatel(username,password,typ_konta,meno,priezvisko,email) VALUES(?,?,?,?,?,?)";
        checkFieldData();
        if (checkFieldData()) {
            try {
                PreparedStatement preparedStatementForInsert = connection.prepareStatement(insertQuery);
                for (int i = 0; i < 6; i++) {
                    preparedStatementForInsert.setString(i + 1, data[i]);
                }
                preparedStatementForInsert.executeUpdate();
                zle.setText("Registracia prebehla uspesne!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private boolean checkFieldData(){
        if (data[0] == null ||data[1] == null || data[2] == null || data[3] == null || data[4] == null || data[5] == null){
            zle.setText("Vsetky polia su povinne!");
            return false;
        }
        else if (!data[3].contains("@")){
            zle.setText("Zadali ste naplatny e-mail!");
            return false;
        }
        else if (!data[5].equals("opravar") || !data[5].equals("admin") || !data[5].equals("osetrovatel")){
            zle.setText("Zadali ste zly typ konta!");
            return false;
        }
        return true;
    }

}
