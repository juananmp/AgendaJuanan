/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajuanan;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author janto
 */
public class ExportarContacto {

    void Exportar(Agenda name, String ficheroXML) {
        try {
            
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File XMLfile = new File(ficheroXML);
            marshaller.marshal(name, XMLfile);
        } catch (JAXBException ex) {
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void CogerXML (String nombre, String fichero) throws JAXBException{
        BuscarPersona(nombre,Main.nuestraAgenda, fichero);     
    }
    private void BuscarPersona(String nombre, Agenda agenda, String fichero) {
        boolean esta = false;
        Agenda agendas = new Agenda();
        for(int y=0; y<agenda.getPersona().size();y++){
            if(agenda.getPersona().get(y).getName().equals(nombre)){
                esta = true;
                agendas = new Agenda();
                agendas.persona.add(agenda.getPersona().get(y));
            }
        }
        if(esta){
            Exportar(agendas, fichero);
        }else{
            System.out.println("No existe el contacto");
        }
    }
    
}
