/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Cotipoitem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class CotipoitemFacade extends AbstractFacade<Cotipoitem> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotipoitemFacade() {
        super(Cotipoitem.class);
    }
    public List<Cotipoitem> findByCot(int idCotizacion){
        String jpa="select ct from Cotipoitem ct "
                + "where ct.cotizacion.idcotizacion="+idCotizacion;
    return em.createQuery(jpa, Cotipoitem.class).getResultList();
    }
}
