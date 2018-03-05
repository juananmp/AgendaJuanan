/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajuanan;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author janto
 */
public class XPathEvaluator {
    public void XPath(){
         File xmlFile = new File("Agenda.xml");
        //Expresion result needs to be a Number

        //Libros con precio mayor que
        //String expressionXPath = "count(//libro[precio > 30.0])";

        //Número de autores total
        String expressionXPath = "count(//name)";

        //Libros con más de un autor
        //String expressionXPath = "count(//libro[count(autor)>1])";
        
        //Precio de todos los libros novela
        //String expressionXPath = "sum(Biblioteca/libro[@categoria='Novela']/precio)";
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);
            XPath xpath = XPathFactory.newInstance().newXPath();
            //Evaluate expression
            Double result = (Double) xpath.evaluate(expressionXPath, doc, XPathConstants.NUMBER);
            System.out.println("El total de usuarios registrados es: " + result);
        } catch (SAXException ex) {
            Logger.getLogger(XPathEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(XPathEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(XPathEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XPathEvaluator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }

