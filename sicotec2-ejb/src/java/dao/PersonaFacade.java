/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PersonaDTO;
import entidades.Persona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public List<Persona> findPersona(Persona p) {
    String jpa="Select p from Persona p where 1=1 ";
    if(p.getNombre()!=null && !p.getNombre().equals("")){
    jpa+="and p.nombre like '%"+p.getNombre()+"%'";
    }
    if(p.getApellido()!=null && !p.getApellido().equals("")){
    jpa+="and p.apellido like '%"+p.getApellido()+"%' ";
    }
    if(p.getDni()!=null && !p.getDni().equals("")){
    jpa+="and p.dni like '%"+p.getDni()+"%' ";
    }
    if(p.getEmail()!=null && !p.getEmail().equals("")){
    jpa+="and p.email like '%"+p.getEmail()+"%' ";
    }
    return em.createQuery(jpa, Persona.class).getResultList();
    
    }
    
}
