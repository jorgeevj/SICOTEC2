/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PersonaFacade;
import entidades.Persona;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Jorge
 */
@Stateless
@LocalBean
public class PersonaBO {
    @EJB
    private PersonaFacade personaFacade;

    public List<Persona> getAllPresonas() {
        
        return personaFacade.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
