/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bbt.irs.xml;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.stream.StreamResult;
import jlibs.xml.sax.XMLDocument;
import jlibs.xml.xsd.XSInstance;
import jlibs.xml.xsd.XSParser;
import org.apache.xerces.xs.*;

/**
 *
 * @author tkola
 */
public class SampleXMLGenerator {

    public static void main(String[] args) {
        try {
            XSModel xsModel = new XSParser().parse("dbr300.xsd");
            XSInstance xsInstance = new XSInstance();
            xsInstance.minimumElementsGenerated = 2;
            xsInstance.maximumElementsGenerated = 4;
            xsInstance.generateOptionalElements = Boolean.TRUE;
            QName rootElement = new QName( "dbr300.xsd");
            XMLDocument sampleXml = new XMLDocument(new StreamResult(System.out), true, 4, null);
            xsInstance.generate(xsModel, rootElement, sampleXml);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(SampleXMLGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
