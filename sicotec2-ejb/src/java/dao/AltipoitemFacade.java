/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Altipoitem;
import java.util.ArrayList;
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
    
    public boolean updateCantidadAltipoItem(String idTipoItem, int idAlmacen, int cantidad){
        boolean tof = false;
        try{
            String sql = "UPDATE altipoitem "
                       + "SET cantidad = cantidad + "+cantidad+" "
                       + "WHERE idtipoitem = '"+idTipoItem+"' "
                       + "AND idalmacen = "+idAlmacen;
                    
            Query query = em.createNativeQuery(sql);
            
            int i = query.executeUpdate();
            if(i == 1){
                tof = true;
            }
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        return tof;
    }

    public Altipoitem getAllByTipoItemIdAlmacen(Altipoitem entidadAl){
        Altipoitem entidad = new Altipoitem();
        try{
            String jpa = "SELECT a "
                       + "FROM Altipoitem a "
                       + "WHERE 1 = 1 "
                       + "AND   a.altipoitemPK.idalmacen  = "+ entidadAl.getAlmacen().getIdalmacen()+ " "
                       + "AND   a.altipoitemPK.idtipoItem = '"+ entidadAl.getTipoitem().getIdtipoItem()+"'" ;
            entidad = em.createQuery(jpa,Altipoitem.class).getSingleResult();
        } catch(Exception e){
            
        }
        return entidad;
    }
}
