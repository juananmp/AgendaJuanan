/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajuanan;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author janto
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static Agenda nuestraAgenda = new Agenda();

    public static void main(String[] args) throws JAXBException {
        Marshall m = new Marshall();

        UnMarshall u = new UnMarshall();

        File file = new File("Agenda.xml");
        if (file.exists() && !file.isDirectory()) {
            u.UnMarshallAgenda(file);
        }

        File xsdFile = new File("ValidarAgenda.xsd");

        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario

        while (!salir) {

            System.out.println("1. Añadir contacto");
            System.out.println("2. Mostrar usuario creado (UnMarshall)");
            System.out.println("3. Well Formed (DTD)");
            System.out.println("4. Validar XSD");
            System.out.println("5. XPath");
            System.out.println("6. Mostrar todos los nombres de Agenda");
            System.out.println("7. Exportar contacto ");
            System.out.println("8. Importar contacto ");
            System.out.println("9. Salir");

            try {

                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();

                switch (opcion) {
                    case 1:
//                        Persona p = new Persona();
                        Agenda agenda = new Agenda();
//                        agenda.setPersona(p);
                        m.Marshall(agenda);
                        break;

                    case 2:
//                         u.UnMarshall();
                        u.UnMarshallAgenda(file);
                        break;
                    case 3:
                        //¿Tratar la excepcion y seguir corriendo el programa?

                        try {
                            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//                          dbFactory.setValidating(true);
                            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                            //In this case we are creating a different ErrorHandler, if not we do like the well-formed Checker+
                            CustomErrorHandler customErrorHandler = new CustomErrorHandler();
                            dBuilder.setErrorHandler(customErrorHandler);

                            Document doc = dBuilder.parse(file);

                            if (customErrorHandler.isValid()) {
                                System.out.println(file + " was valid!");
                            } else {
                                System.out.println(file + " was not valid!");
                            }
                        } catch (ParserConfigurationException ex) {
                            System.out.println(file + " error while parsing!");
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (SAXException ex) {
                            System.out.println(file + " was not well-formed!");
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            System.out.println(file + " was not accesible!");
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    case 4:

                        try {

                            SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                            Schema schema = schemaFactory.newSchema(xsdFile);
                            Validator validator = schema.newValidator();
                            validator.validate(new StreamSource(file));
                            System.out.println(file + " is valid against the " + xsdFile + " Schema");
                        } catch (SAXException ex) {
                            System.out.println(file + " is not valid against the " + xsdFile + " Schema");
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (IOException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        break;
                    case 5:
                        XPathEvaluator xpath = new XPathEvaluator();
                        xpath.XPath();
                        break;

                    case 6:
                        XQuery xq = new XQuery();
                        xq.ejecutarQuery();
                        break;
                    case 7:
                        ExportarContacto ex = new ExportarContacto();
                        Scanner entrada = new Scanner(System.in);
                        System.out.print("Ingrese su nombre del contacto: ");
                        String name = entrada.nextLine();
                        Scanner entrad = new Scanner(System.in);
                        System.out.print("Ingrese nombre del fichero: ");
                        String ficheroXML = entrad.nextLine();
                        ex.CogerXML(name,ficheroXML);
                        break;
                    case 8:
                        ImportarContacto im = new ImportarContacto();
                        Scanner entradass = new Scanner(System.in);
                        System.out.print("Ingrese su nombre del contacto: ");
                        String names = entradass.nextLine();
                        Scanner entradados = new Scanner(System.in);
                        System.out.print("Ingrese nombre del fichero: ");
                        String ficheroXMLd = entradados.nextLine();
                        im.UnMarshallAgenda(ficheroXMLd, names);
                        break;
                    case 9:
                        salir = true;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }

    }

}
