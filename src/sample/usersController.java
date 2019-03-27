package sample;

import connectivity.ConnectionClass;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class usersController implements Initializable {

    @FXML
    private TableView<User> usersTable;
    @FXML
    private TableColumn<User,String> nameColumn;
    @FXML
    private TableColumn<User,String> surnameColumn;
    @FXML
    private TableColumn<User,String> usernameColumn;
    @FXML
    private TableColumn<User,String> emailColumn;
    @FXML
    private TableColumn<User,String> typeColumn;
    @FXML
    private TableColumn<User,String> deleteColumn;

    private ResultSet data, resultSetSize;

    private User userArray [];

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Connection connection = ConnectionClass.getConnection();
        String sqlQuery = "SELECT * FROM pouzivatel";
        String countQuery = "SELECT Count(*) FROM pouzivatel";

        try {
            PreparedStatement preparedCountStatement = connection.prepareStatement(countQuery);
            resultSetSize = preparedCountStatement.executeQuery();
            resultSetSize.next();
            PreparedStatement preparedQuery = connection.prepareStatement(sqlQuery);
            data = preparedQuery.executeQuery();
            insertIntoTable();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void insertIntoTable() throws SQLException {
       userArray = new User[Integer.parseInt(resultSetSize.getString(1))];
        data.next();
        for (int i = 0; i < userArray.length; i++) {
            if (!data.isClosed()) {
                userArray[i] = new User(data.getString(5), data.getString(6), data.getString(2), data.getString(7), data.getString(4));
                data.next();

            }
        }
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("Username"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("Email"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        ObservableList<User> userObservableListList = FXCollections.observableArrayList(userArray);
        usersTable.setItems(userObservableListList);
    }

    public void onAddUserBtnClick() throws IOException {
        Stage stage = (Stage) usersTable.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("layout/AddUser.fxml"));
        stage.setTitle("Pridanie Pouzivatela");

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
