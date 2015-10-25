/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AltipoitemDTO;
import entidades.Altipoitem;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    public Altipoitem getAllByTipoItemIdAlmacen(Altipoitem entidadAl){
        Altipoitem entidad = new Altipoitem();
        try{
            String jpa = "SELECT a "
                       + "FROM altipoitem a "
                       + "WHERE 1 = 1 "
                       + "AND   a.idalmacen  = :almacen "
                       + "AND   a.idtipoItem = :tipoItem " ;
            Query query = em.createNativeQuery(jpa,Altipoitem.class);
            
            query.setParameter("almacen", entidadAl.getAlmacen().getIdalmacen());
            query.setParameter("tipoItem", entidadAl.getTipoitem().getIdtipoItem());
            
            entidad = (Altipoitem)query.getSingleResult();
        } catch(Exception e){
            entidad = new Altipoitem();
        }
        return entidad;
    }
}
