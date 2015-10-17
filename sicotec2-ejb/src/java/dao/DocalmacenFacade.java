/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Docalmacen;
import entidades.Movimiento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
@Stateless
public class DocalmacenFacade extends AbstractFacade<Docalmacen> {

    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocalmacenFacade() {
        super(Docalmacen.class);
    }

    public Docalmacen findBy2key(int idalmacen, int iddocumento) {

        String sql = "Select d from Docalmacen d "
                + "where d.almacen.idalmacen= :idalm "
                + "and d.documento.iddocumento= :iddoc";

        Query query = em.createQuery(sql);
        query.setParameter("idalm", idalmacen);
        query.setParameter("iddoc", iddocumento);
        return (Docalmacen) query.getSingleResult();
    }

}
