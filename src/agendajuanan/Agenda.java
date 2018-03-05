/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agendajuanan;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author janto
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="Agenda")
public class Agenda implements Serializable {

        @XmlElement(name="Persona")
        ArrayList<Persona> persona;

    public Agenda(){
        persona = new ArrayList<>();
    }
    public ArrayList<Persona> getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona.add(persona);
    }

    @Override
    public String toString() {
        return "Agenda{" + "persona=" + persona.toString() + '}';
    }
    
}
