/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs;

import com.bbt.irs.entity.UserAccess;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


public class LoginController implements Initializable {

    static ArrayList list = null;
    Logger logger = Logger.getLogger("Demographic");

    @FXML
    private TextField userId;
    @FXML
    private TextField otherlg;
    @FXML
    private Label otherlglabel;
    @FXML
    private Label loadingText;
    @FXML
    private Label loclabel;
    @FXML
    private Label codelabel;
    @FXML
    private PasswordField password;
    @FXML
    private Text actiontarget;
    @FXML
    private Label label;
    //public ComboBox<KeyValuePair> combobox = null;
    @FXML
    private GridPane gridLogin;
    @FXML
    private ComboBox lga;
    @FXML
    private ComboBox locationDesc;
    @FXML
    private ComboBox locationDesc2;
    @FXML
    private Text locationcode;
    @FXML
     private UserAccess loggedUser;
    @FXML
    private ProgressIndicator loading;
    @FXML
    private Label version;
    @FXML
    private ImageView imagebox;

    private boolean isLGAErr;
    private boolean isUserIdErr;
    private boolean ispasswordErr;
    private boolean updated;
    private boolean yesLock;
    private boolean islocationDescErr;
    private Task worker;
    private boolean myownlock;
    String username;

    // com.sun.glass.ui.Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
    public UserAccess loginInput = new UserAccess();
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        worker = this.createWorker();
        loading.progressProperty().unbind();
        loading.progressProperty().bind(worker.progressProperty());

        try {
            IRS.loginUser = userId.getText();
//            imagebox.setVisible(true);
//             loadingText.setVisible(true);
//            
            loginInput.setUserid(userId.getText());
            username = userId.getText();
            System.out.println("username is " + username);
            loginInput.setPassword(password.getText());

            if (userId.getText() == null || "".equals(userId.getText())) {
                actiontarget.setText("UserID can not be blank");
                isUserIdErr = true;
                return;
//                //loginInput.setLocationId(String.valueOf(locDesc.getLocationCode()));
            } else {

                isUserIdErr = false;

            }

            if (password.getText() == null || "".equals(password.getText())) {
                actiontarget.setText("Password can not be blank");
                isUserIdErr = true;
                return;
//                //loginInput.setLocationId(String.valueOf(locDesc.getLocationCode()));
            } else {

                isUserIdErr = false;

            }

            if (true) {

                actiontarget.setText("");
                IRS.getInstance().enterApplication();

            } else {
                actiontarget.setText("");
                IRS.getInstance().enterApplication();
            }
        } catch (Exception ex) {
            //logger.info(ex);
        }
        //finally{

        worker.cancel(true);
        loading.progressProperty().unbind();
        loading.setProgress(0.0);
        loading.setVisible(true);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //  version.setText(Double.toString(Version.version));
        version.setText(Version.version);
        // lga = new ComboBox<>();
        password.setStyle("-fx-text-box-border:green");

        imagebox.setVisible(false);
        loadingText.setVisible(false);

        password.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                if (!islocationDescErr && !isLGAErr) {
                    actiontarget.setText("");
                }

                System.out.println(" Testig CapsLock ");
                String letter = "";
                boolean test = false;
                String tt = event.getText();
                System.out.println(" Testing CapsLock " + tt);
                if (tt != null && !"".equals(tt)) {
                    if (Validation.sAlphaNumeric.contains(tt)) {
                        System.out.println("about to test");
                        test = Character.isUpperCase(event.getText().charAt(0));//testJSCapslock();//testCapsLock();//
                    }
                    System.out.println(" Inside test " + test);
                    if (test) {
                        //new LasrraDialog().textDialog("Caps Lock On", "The Caps Lock is ON");
                        actiontarget.setText("Caps Lock  is on");
                    } else {
                        actiontarget.setText("");
                    }
                }
            }
        });

       
     

        userId.setOnKeyPressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (!islocationDescErr && !isLGAErr) {
                    actiontarget.setText("");
                }
                if (isUserIdErr) {
                    //new LasrraDialog().textDialog("Caps Lock On", "The Caps Lock is ON");
                    actiontarget.setText("");
                    isUserIdErr = false;
                }
            }
        });

    }

    @FXML
    public void clearComboError() {

        actiontarget.setText("");

    }
    public Task createWorker() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                for (int i = 0; i < 10; i++) {
                    // Thread.sleep(2000);
                    //updateMessage("2000 milliseconds");
                    //updateProgress(i + 1, 10);
                }
                return true;
            }
        };
    }

  
}
