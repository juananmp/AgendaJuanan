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
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author janto
 */
public class ImportarContacto {
    
    public void UnMarshallAgenda(String otraAgenda,String nombre) throws JAXBException{
     try {
         
        System.out.println("Cargando persona ...");
        JAXBContext jaxbC = JAXBContext.newInstance(Agenda.class);
        Unmarshaller jaxbU = jaxbC.createUnmarshaller();
       
        File XMLfile = new File(otraAgenda);
        Agenda aux = (Agenda) jaxbU.unmarshal(XMLfile);
        BuscarPersona(aux,nombre);
          } catch (JAXBException ex) {
            Logger.getLogger(UnMarshall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void BuscarPersona(Agenda agenda, String nombre) {
      boolean esta = false;
        
        for(int y=0; y<agenda.getPersona().size();y++){
            if(agenda.getPersona().get(y).getName().equals(nombre)){
                esta = true;
                
                Main.nuestraAgenda.persona.add(agenda.getPersona().get(y));
            }
        }
        if(esta){
            ImportarC(Main.nuestraAgenda);
        }else{
            System.out.println("No existe el contacto");
        }
    }

    private void ImportarC(Agenda agendas) {
        
        try {
            File XMLfile = new File("Agenda.xml");
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(agendas, XMLfile);
        } catch (JAXBException ex) {
            Logger.getLogger(ImportarContacto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  
}
