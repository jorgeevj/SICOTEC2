/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Personas;

import bo.PersonaBO;
import entidades.Persona;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class PersonasMB {
    @EJB
    private PersonaBO personaBO;
    
    
private List<Persona> lp;
private Persona personaSelec;
    /**
     * Creates a new instance of PersonasMB
     */
    public PersonasMB() {
    }
    @PostConstruct
    public void init() {
     lp=  personaBO.getAllPresonas();
       

    }

    public List<Persona> getLp() {
        return lp;
    }

    public void setLp(List<Persona> lp) {
        this.lp = lp;
    }

    public Persona getPersonaSelec() {
        return personaSelec;
    }

    public void setPersonaSelec(Persona personaSelec) {
        this.personaSelec = personaSelec;
    }
    
    
}
