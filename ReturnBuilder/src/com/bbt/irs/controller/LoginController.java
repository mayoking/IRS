/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import static com.bbt.irs.deploy.ErrorNameDesc.LOGINERROR;
import com.bbt.irs.entity.UserAccess;
import com.bbt.irs.deploy.IRS;
import static com.bbt.irs.deploy.Messages.CAP_LOCK_ON;
import static com.bbt.irs.deploy.Messages.PASSWORD_CAN_NOT_BLANK;
import static com.bbt.irs.deploy.Messages.PASSWORD_STYLE;
import static com.bbt.irs.deploy.Messages.USER_ID_CAN_NOT_BLANK;
import com.bbt.irs.deploy.Validation;
import com.bbt.irs.deploy.Version;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LoginController implements Initializable {

    static ArrayList list = null;
   private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    @FXML
    private TextField userId;
    @FXML
    private Label loadingText;
    @FXML
    private PasswordField password;
    @FXML
    private Text actiontarget;
    @FXML
    private Label label;
    @FXML
    private GridPane gridLogin;
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
    private Task worker;
    private boolean myownlock;
    String username;

    // com.sun.glass.ui.Robot robot = com.sun.glass.ui.Application.GetApplication().createRobot();
    public UserAccess loginInput = new UserAccess();
    @FXML
    private void handleSubmitButtonAction(ActionEvent event) {
        LOGGER.info("You clicked me!");
        worker = this.createWorker();
        loading.progressProperty().unbind();
        loading.progressProperty().bind(worker.progressProperty());
        validateLoginInfo();
        worker.cancel(true);
        loading.progressProperty().unbind();
        loading.setProgress(0.0);
        loading.setVisible(true);

    }
    /**
     * The method is reponsible for validating login Input
     * @return 
     */
    public boolean validateLoginInfo(){
        try {
            IRS.loginUser = userId.getText();           
            loginInput.setUserid(userId.getText());
            username = userId.getText();
            LOGGER.info("username is " + username);
            loginInput.setPassword(password.getText());

            if (userId.getText() == null || "".equals(userId.getText())) {
                actiontarget.setText(USER_ID_CAN_NOT_BLANK);
                isUserIdErr = true;
                return isUserIdErr;
            } else {
                isUserIdErr = false;
            }
            if (password.getText() == null || "".equals(password.getText())) {
                actiontarget.setText(PASSWORD_CAN_NOT_BLANK);
                isUserIdErr = true;
                return isUserIdErr;               
            } else {
                isUserIdErr = false;
            }
            if (!isUserIdErr) {
                actiontarget.setText("");
                IRS.getInstance().enterApplication();
            } else {
                actiontarget.setText("");
                IRS.getInstance().enterApplication();
            }
        } catch (Exception ex) {
            LOGGER.log(Level.FATAL,LOGINERROR,ex);
        }
        return isUserIdErr;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        version.setText(Version.version);
        password.setStyle(PASSWORD_STYLE);
        imagebox.setVisible(false);
        loadingText.setVisible(false);
        password.setOnKeyPressed(new EventHandler<javafx.scene.input.KeyEvent>() {
            @Override
            public void handle(javafx.scene.input.KeyEvent event) {
                String letter = "";
                boolean test = false;
                String tt = event.getText();
                if (tt != null && !"".equals(tt)) {
                    if (Validation.sAlphaNumeric.contains(tt)) {
                        test = Character.isUpperCase(event.getText().charAt(0));//testJSCapslock();//testCapsLock();//
                    }
                    if (test) {
                        actiontarget.setText(CAP_LOCK_ON);
                    } else {
                        actiontarget.setText("");
                    }
                }
            }
        });

       
     

        userId.setOnKeyPressed(new EventHandler() {
            @Override
            public void handle(Event event) {
                if (isUserIdErr) {
                    //new LasrraDialog().textDialog("Caps Lock On", "The Caps Lock is ON");
                    actiontarget.setText("");
                    isUserIdErr = false;
                }
            }
        });

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
