/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Altipoitem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class AltipoitemFacade extends AbstractFacade<Altipoitem> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AltipoitemFacade() {
        super(Altipoitem.class);
    }
    public List<Altipoitem> buscaByIDs(Altipoitem at){
        String sql="select a from Altipoitem a "
                + "where  a.altipoitemPK.idalmacen= "+ at.getAlmacen().getIdalmacen()+" "
                + "and a.altipoitemPK.idtipoItem='"+ at.getTipoitem().getIdtipoItem()+"'" ;
    return em.createQuery(sql, Altipoitem.class).getResultList();
    }
}
