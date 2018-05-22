/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.services.client;

import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.ws.Dispatch;
import javax.xml.transform.stream.StreamSource;
import javax.xml.ws.Service;
import java.io.StringReader;

/**
 *
 * @author tkola
 */
public class UploadExcel {

    private void uploadFile( ) {
        com.bbt.irs.services.client.FileSubmission_Service service = new com.bbt.irs.services.client.FileSubmission_Service();
        QName portQName = new QName("http://onesumx.bbt.com/", "FileSubmissionPort");
        String req = "<uploadFile  xmlns=\"http://onesumx.bbt.com/\"><arg0>ENTER VALUE</arg0><arg1>ENTER VALUE</arg1></uploadFile>";
        try {
            // Call Web Service Operation
            Dispatch<Source> sourceDispatch;
            sourceDispatch = service.createDispatch(portQName, Source.class, Service.Mode.PAYLOAD);
            Source result = sourceDispatch.invoke(new StreamSource(new StringReader(req)));
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }
    }
    
}
