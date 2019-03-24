package sample;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import connectivity.ConnectionClass;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class ResetPass implements Initializable {
    public boolean send(String mail ,String kod) {
        try {
            String host = "smtp.gmail.com";
            String user = "mochnacky@spse-po.sk";
            String pass = "Divergencia128";
            String to = mail;
            String from = "mochnacky@spse-po.sk";
            String subject = "OBNOVENIE HESLA!!";
            String text = kod;
            boolean sessionDebug = false;

            Properties props = System.getProperties();

            props.put("mail.smtp.user", "username");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "25");
            props.put("mail.debug", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.EnableSSL.enable", "true");

            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
            msg.setText(text);

            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
            return true;
        } catch (Exception ex) {
            System.out.println("Chyba nepodarilo sa odoslat emial");
            return  false;

        }
    }
    static String kodS ="";
    public void kod(){
        String kod="";
        for (int i=0;i<6;i++){
            kod=kod+Integer.toString((int)(Math.random()*10));
        }
        kodS=kod;
    }
   /* ObservableList vyber= FXCollections.observableArrayList();
    @FXML
    private ChoiceBox<String> cb;*/

   @FXML
   private ImageView back;
    @FXML
    BorderPane mainPane;
    @FXML
    private TextField loginRess;

    @FXML
    private TextField kodRess;

    @FXML
    private Label ErrorResPass;

   @FXML
   private void odosli() throws SQLException {
        String xLogin=loginRess.getText().toLowerCase();

       String sqlEmail = "SELECT email FROM pouzivatel WHERE username = ?" ;


       Connection connection = ConnectionClass.getConnection();

       PreparedStatement statementForEmail = connection.prepareStatement(sqlEmail);
       statementForEmail.setString(1, xLogin);
       ResultSet Email = statementForEmail.executeQuery();
       Email.next();
       if (!Email.isClosed() || !Email.isClosed()) {
           String gmail =Email.getString(1);
           kod();
           System.out.println(kodS);
           System.out.println(gmail);


            if (send(gmail,kodS)==true) {
                ResetPass2 pane = new ResetPass2();
                mainPane.setCenter(pane);
            }else ErrorResPass.setText("NEPODARLO SA ODOSLAT EMAIL, SKUSTE TO ZNOVA");

           ErrorResPass.setText("Odosielanie prebehlo úspešne");
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
            System.out.println("Nepodarilo sa nacitat Prihlasenie");
        }
    }

    @FXML
     public void overit(){
       if (kodRess.getText().equals(kodS)){
           System.out.println("huraa spravnz kod");
       }
       else {
           ErrorResPass.setText("Overovací kód nie je správny");
           kodRess.setText("");
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
