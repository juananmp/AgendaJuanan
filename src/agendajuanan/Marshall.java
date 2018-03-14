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
import javax.xml.transform.Result;

/**
 *
 * @author janto
 */
public class Marshall {
       
        CustomErrorHandler customError;
       
        public void Marshall(Agenda agenda){
          try {
            boolean correoCorrecto = false;
            JAXBContext context = JAXBContext.newInstance(Agenda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            Persona p = new Persona();

            Scanner entrada = new Scanner(System.in);

            String name = "";
            String email = "";
            int telephone = 0;

            System.out.print("Ingrese su nombre: ");
            name = entrada.nextLine();
            
              while (!correoCorrecto) {                  
            
            System.out.print("Ingrese su correo: ");
            email = entrada.nextLine();
            if(email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
                correoCorrecto = true;
            }else{
                System.out.println("Correo no correcto");
            }
 }
            System.out.print("Ingrese la numero de telefono: ");
            telephone = entrada.nextInt();

//            ArrayList<Persona> lista = new ArrayList<Persona>();

            p.setName(name);
            p.setEmail(email);
            p.setTelephone(telephone);
            File XMLfile = new File("Agenda.xml");
//            lista.add(p);
            Main.nuestraAgenda.setPersona(p);
            agenda.setPersona(p);
            marshaller.marshal(Main.nuestraAgenda, XMLfile);

            marshaller.marshal(Main.nuestraAgenda, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(Marshall.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
