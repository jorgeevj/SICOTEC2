/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Requerimientos;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jc7
 */
@Stateless
public class RequerimientosFacade extends AbstractFacade<Requerimientos> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequerimientosFacade() {
        super(Requerimientos.class);
    }
    
    public Requerimientos agregarRequerimiento(Requerimientos entidadRequerimiento) {
        getEntityManager().persist(entidadRequerimiento);
        getEntityManager().flush();
        return entidadRequerimiento;
    }
    
}
