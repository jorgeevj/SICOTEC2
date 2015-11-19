/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.EmpresaDTO;
import entidades.Emppersona;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class EmppersonaFacade extends AbstractFacade<Emppersona> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmppersonaFacade() {
        super(Emppersona.class);
    }

    public List<Emppersona> getPersonaByEmpresa(EmpresaDTO dto) {
        String jpa="select p from Emppersona p "
                + "where p.empresa.idempresa="+dto.getIdempresa();
    return em.createQuery(jpa,Emppersona.class).getResultList();
    }
    
}
