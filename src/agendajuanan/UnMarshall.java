/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajuanan;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author janto
 */
public class UnMarshall {
     File XMLfile;
    //¿file agenda?
    public Agenda UnMarshallAgenda( File XMLfile) throws JAXBException{
     try {
        System.out.println("Cargando persona ...");
        JAXBContext jaxbC = JAXBContext.newInstance(Agenda.class);
        Unmarshaller jaxbU = jaxbC.createUnmarshaller();
       
        XMLfile = new File("Agenda.xml");
        Main.nuestraAgenda = (Agenda) jaxbU.unmarshal(XMLfile);
         System.out.println(Main.nuestraAgenda.toString());
         
            return Main.nuestraAgenda;
        
          } catch (JAXBException ex) {
            Logger.getLogger(UnMarshall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     
    }
    public Persona UnMarshallPersona(File persona){
        try {
            System.out.println("Cargando persona ...");
            JAXBContext jaxbC = JAXBContext.newInstance(Persona.class);
            Unmarshaller jaxbU = jaxbC.createUnmarshaller();
            Persona p = (Persona) jaxbU.unmarshal(persona);
            return p;
        } catch (JAXBException ex) {
            Logger.getLogger(UnMarshall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}

     