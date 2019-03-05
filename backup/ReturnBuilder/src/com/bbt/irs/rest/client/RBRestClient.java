/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.rest.client;

import com.bbt.irs.deploy.ErrorNameDesc;
import com.bbt.irs.exception.WebservicesException;
import com.bbt.irs.util.ConfigUtility;
import com.bbt.irs.vo.AppConfigVO;
import com.bbt.irs.vo.AssertionRequestVO;
import com.bbt.irs.vo.AssertionResponseVO;
import com.bbt.irs.vo.AuthVO;
import com.bbt.irs.vo.ExtractorVO;
import com.bbt.irs.vo.FailedReturnTemplate;
import com.bbt.irs.vo.GenWorCollVO;
import com.bbt.irs.vo.ItemCodeAndColumnsVO;
import com.bbt.irs.vo.PinVO;
import com.bbt.irs.vo.RBLoginVO;
import com.bbt.irs.vo.RequestVO;
import com.bbt.irs.vo.ResponseVO;
import com.bbt.irs.vo.RuleSetVO;
import com.bbt.irs.vo.RuleVO;
import com.bbt.irs.vo.RulesetVOS;
import com.bbt.irs.vo.SCTPackagerVO;
import com.bbt.irs.vo.TableModificationVO;
import com.bbt.irs.vo.ViewXMLXSDVO;
import com.bbt.irs.vo.XSDModificationVO;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.io.File;
import java.io.IOException;
import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;

public class RBRestClient implements ErrorNameDesc {

    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(RBRestClient.class);
    private WebTarget webTarget;
    private Client client;
    private static String BASE_URI = "";//"http://localhost:8080/ReturnBuilderWebservices/webresources";//"http://95.168.176.143:9005/ReturnBuilderWebservices/webresources";//"http://95.168.176.143:9005/ReturnBuilderWebservices/webresources";

    public RBRestClient() {
        try {
            ConfigUtility.loadProperties();
            BASE_URI = ConfigUtility.getConfigProps().getProperty("webservicesurl");
            System.out.println("This is the base uti being used " + ConfigUtility.getConfigProps().getProperty("webservicesurl"));
            client = javax.ws.rs.client.ClientBuilder.newClient();
            client.register(MultiPartFeature.class);
            client.register(JacksonJsonProvider.class);
            webTarget = client.target(BASE_URI).path("generic");

        } catch (ClientErrorException ex) {
            Logger.getLogger(RBRestClient.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RBRestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getXml() throws IOException, ConnectException {//ClientErrorException
        WebTarget resource = webTarget;
        return resource.request(javax.ws.rs.core.MediaType.APPLICATION_XML).get(String.class);
    }

    public AppConfigVO loadWebServiceConfig() throws WebservicesException {
        try {
            Response response = webTarget.path("loadWebServiceConfig").request().get();
            System.out.println("testtttt " + response.getStatus());
            AppConfigVO res = (AppConfigVO) response.readEntity(AppConfigVO.class);
            return res;
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ResponseVO uploadExcelFile4TemplateCode(String path) throws IOException, ConnectException, WebservicesException {//ClientErrorException
        final FileDataBodyPart filePart = new FileDataBodyPart("file", new File(path));
        final FormDataMultiPart multipart;
        final Response response;
        ResponseVO res;
        try (FormDataMultiPart formDataMultiPart = new FormDataMultiPart()) {
            multipart = (FormDataMultiPart) formDataMultiPart.field("tope", "tt").bodyPart(filePart);
            response = webTarget.path("uploadExcelFile4TemplateCode").request().post(Entity.entity(multipart, multipart.getMediaType()));
            res = (ResponseVO) response.readEntity(ResponseVO.class);

            System.out.println("response " + response.getStatus());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public Response uploadExcelFile(String path) throws IOException, ConnectException, WebservicesException {//ClientErrorException
        final FileDataBodyPart filePart = new FileDataBodyPart("file", new File(path));
        final FormDataMultiPart multipart;
        final Response response;
        try (FormDataMultiPart formDataMultiPart = new FormDataMultiPart()) {
            multipart = (FormDataMultiPart) formDataMultiPart.field("tope", "tt").bodyPart(filePart);
            response = webTarget.path("excel").request().post(Entity.entity(multipart, multipart.getMediaType()));
            System.out.println("response " + response.getStatus());
            System.out.println("response " + response.readEntity(String.class));
            System.out.println("response " + response.getHeaders());
            //return webTarget.path("excel").request().post(null, Response.class);
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return response;
    }

    public ResponseVO generateXMLXSD(GenWorCollVO genWorCollVO) throws WebservicesException {
        try {
            Response response = webTarget.path("xmlxsdcreator").request().post(Entity.entity(genWorCollVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            ResponseVO res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
            return res;
        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ResponseVO synchronizeExtractor(ExtractorVO extractorVO) throws WebservicesException {
        try {
            Response response = webTarget.path("syncextractor").request().post(Entity.entity(extractorVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            ResponseVO res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
            return res;
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ResponseVO createSmartClient(SCTPackagerVO sctPackagerVO) throws WebservicesException {
        try {
            Response response = webTarget.path("sctcreator").request().post(Entity.entity(sctPackagerVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            ResponseVO res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
            return res;
        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ItemCodeAndColumnsVO assertreq(RequestVO requestvo) throws WebservicesException {
        try {
            Response response = webTarget.path("assertreq").request().post(Entity.entity(requestvo, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            ItemCodeAndColumnsVO res = (ItemCodeAndColumnsVO) response.readEntity(ItemCodeAndColumnsVO.class);
            //System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
            return res;
        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ResponseVO assertrulesubmission(RuleSetVO rulevo) throws WebservicesException {
        try {
            Response response = webTarget.path("assertrulesubmission").request().post(Entity.entity(rulevo, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            ResponseVO res = (ResponseVO) response.readEntity(ResponseVO.class);
            return res;
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ResponseVO assertruleremove(RuleVO rulesetvo) throws WebservicesException {
        try {
            Response response = webTarget.path("assertruleremove").request().post(Entity.entity(rulesetvo, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            ResponseVO res = (ResponseVO) response.readEntity(ResponseVO.class);
            return res;
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public RuleSetVO assertrulesview(String ruleSetCode) throws WebservicesException {
        try {
            System.out.println("ruleSetCode " + ruleSetCode);
            Response response = webTarget.path("assertrulesview").request().post(Entity.entity(ruleSetCode, MediaType.TEXT_PLAIN));
            System.out.println("testtttt " + response.getStatus());
            RuleSetVO res = (RuleSetVO) response.readEntity(RuleSetVO.class);
            return res;
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public RulesetVOS loadRulesets() throws WebservicesException {
        try {
            Response response = webTarget.path("loadRulesets").request().get();
            System.out.println("testtttt " + response.getStatus());
            RulesetVOS res = (RulesetVOS) response.readEntity(RulesetVOS.class);
            return res;
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
    }

    public ResponseVO modifyreturnfield(XSDModificationVO xSDModificationVO) throws WebservicesException {
        ResponseVO res = null;
        try {
            Response response = webTarget.path("modifyreturnfield").request().post(Entity.entity(xSDModificationVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public ResponseVO addTemplateField(TableModificationVO tableModificationVO) throws WebservicesException {
        ResponseVO res = null;
        try {
            System.out.println("tableModificationVO " + tableModificationVO);
            Response response = webTarget.path("addTemplateField").request().post(Entity.entity(tableModificationVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            System.out.println("e " + e.getMessage());
            throw new WebservicesException(PROCESSINEXCEPTION);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return res;//rblogin
    }

    public RBLoginVO rblogin(AuthVO auth) throws WebservicesException {
        RBLoginVO rbloginvo = null;
        try {
            Response response = webTarget.path("rblogin").request().post(Entity.entity(auth, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            rbloginvo = (RBLoginVO) response.readEntity(RBLoginVO.class);
            System.out.println(rbloginvo.getResponseCode() + "  response   " + rbloginvo.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebservicesException("Fatal error while authenticating");
        }
        return rbloginvo;

    }

    public ResponseVO authuser(AuthVO authvo) throws WebservicesException {
        ResponseVO res = null;
        try {

            Response response = webTarget.path("authuser").request().post(Entity.entity(authvo, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebservicesException("Fatal error while authenticating");
        }
        return res;
    }

    public ResponseVO assignPINToUSer(PinVO pinvo) throws WebservicesException {
        ResponseVO res = null;
        try {

            Response response = webTarget.path("authuser").request().post(Entity.entity(pinvo, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public ResponseVO modifyTemplateField(TableModificationVO tableModificationVO) throws WebservicesException {
        ResponseVO res = null;
        try {
            System.out.println("tableModificationVO " + tableModificationVO);
            Response response = webTarget.path("modifyTemplateField").request().post(Entity.entity(tableModificationVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public ResponseVO synchronize(GenWorCollVO genWorCollVO) throws WebservicesException {
        ResponseVO res = null;
        try {
            Response response = webTarget.path("sctsynchronize").request().post(Entity.entity(genWorCollVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public ResponseVO reloadFailedReturns(FailedReturnTemplate failedReturnTemplate) throws WebservicesException {
        ResponseVO res = null;
        try {
            Response response = webTarget.path("reloadfailedreturns").request().post(Entity.entity(failedReturnTemplate, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ResponseVO) response.readEntity(ResponseVO.class);
            System.out.println(res.getResponseCode() + "  response   " + res.getResponseDesc());
        } catch (ProcessingException e) {
            e.printStackTrace();
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public ViewXMLXSDVO viewXSDXML(GenWorCollVO genWorCollVO) throws WebservicesException {
        Response response = null;
        ViewXMLXSDVO res = null;
        try {
            response = webTarget.path("viewxmlxsd").request().post(Entity.entity(genWorCollVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (ViewXMLXSDVO) response.readEntity(ViewXMLXSDVO.class);
            // System.out.println(res() + "  response   "+res.getResponseDescription());
        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public AssertionResponseVO validateAssertion(AssertionRequestVO assertionRequestVO) throws WebservicesException {
        Response response;
        AssertionResponseVO res = null;
        try {
            response = webTarget.path("validateAssertion").request().post(Entity.entity(assertionRequestVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (AssertionResponseVO) response.readEntity(AssertionResponseVO.class);

        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public AssertionResponseVO submitAssertion(AssertionRequestVO assertionRequestVO) throws WebservicesException {
        Response response = null;
        AssertionResponseVO res = null;
        try {
            response = webTarget.path("submitAssertion").request().post(Entity.entity(assertionRequestVO, MediaType.APPLICATION_JSON));
            System.out.println("testtttt " + response.getStatus());
            res = (AssertionResponseVO) response.readEntity(AssertionResponseVO.class);
            // System.out.println(res() + "  response   "+res.getResponseDescription());
        } catch (ProcessingException e) {
            throw new WebservicesException(PROCESSINEXCEPTION);
        }
        return res;
    }

    public void close() {
        client.close();
    }
//      public static void main(String[] args) {
//        try {
//            RBRestClient uploadClient = new RBRestClient();
//            uploadClient.uploadExcelFile();
//        } catch (IOException ex) {
//            Logger.getLogger(RBRestClient.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
}
