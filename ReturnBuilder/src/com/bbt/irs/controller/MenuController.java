    /*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.controller;

import com.bbt.irs.deploy.IRS;
import com.bbt.irs.deploy.IRSDialog;
import static com.bbt.irs.deploy.ErrorNameDesc.ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.LOAD_HEADER_FORM_ERROR;
import static com.bbt.irs.deploy.ErrorNameDesc.UPLOAD_TEMPLATE_NOT_FOUND;
import com.bbt.irs.deploy.Messages;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * FXML Controller class
 *
 * @author admin
 */
public class MenuController extends Accordion implements Initializable,Messages {

    @FXML
    private Text text;
    @FXML
    private Image image;
    @FXML
    private ImageView imageView;
    @FXML
    private Button button;
    private final static String versionURL = "";

    private final static String historyURL = "";
    private static final Logger LOGGER = LogManager.getLogger(MainController.class);

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        InputStream fxmlStream = null;

    }

    @FXML
    private void replay() {

    }

    @FXML
    protected void addBasicInfo(ActionEvent event){
         try {
            MainController.getInstance().loadBasicInfo();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @FXML
    protected void viewStaticHeaders(ActionEvent event) {
        if(IRS.getExcelPath()==null){
            IRSDialog.showAlert(UPLOAD_XLS_TITTLE, UPLOAD_TEMPLATE_NOT_FOUND);
            return;
        }
        try {
            MainController.getInstance().loadStaticHeaderForm();
        } catch (IOException ex) {
            IRSDialog.showAlert(ERROR,LOAD_HEADER_FORM_ERROR);
            LOGGER.log(Level.FATAL,LOAD_HEADER_FORM_ERROR,ex);
        }
    }
    
    @FXML
    protected void uploadXLS(ActionEvent event) {
        try {
            MainController.getInstance().loadXLS();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
     @FXML
    protected void confirmInput(ActionEvent event) {
        try {
            MainController.getInstance().loadXLS();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * 05
     *
     * 06 THIS METHOD IS FOR APPLICATION UPDATE 07
     */
    public static String getLatestVersion() throws Exception {

        String data = getData(versionURL);

        return data.substring(data.indexOf("[version]") + 9, data.indexOf("[/version]"));

    }

    public static String getWhatsNew() throws Exception {

        String data = getData(historyURL);

        return data.substring(data.indexOf("[history]") + 9, data.indexOf("[/history]"));

    }

    private static String getData(String address) throws Exception {

        URL url = new URL(address);

        InputStream html = null;
        html = url.openStream();
        int c = 0;
        StringBuffer buffer = new StringBuffer("");
        while (c != -1) {
            c = html.read();
            buffer.append((char) c);

        }
        return buffer.toString();

    }

}
