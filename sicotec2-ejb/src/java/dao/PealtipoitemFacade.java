/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PealtipoitemDTO;
import dto.TipoItemDTO;
import entidades.Pealtipoitem;
import entidades.Tipoitem;
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
public class PealtipoitemFacade extends AbstractFacade<Pealtipoitem> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PealtipoitemFacade() {
        super(Pealtipoitem.class);
    }
    
     public List<Pealtipoitem> getAllPealtipoitem(){
        List<Pealtipoitem> tPealtipoitem = new ArrayList<Pealtipoitem>();
        
        
        try{
            String jpa = "SELECT t "
                       + "FROM Pealtipoitem t "
                       + "JOIN FETCH t.idtipoItem "
                       + "JOIN FETCH t.idalmacen ";
                   
                   
            Query query = em.createQuery(jpa,Pealtipoitem.class);
            tPealtipoitem = query.getResultList();
            
        }catch(Exception e){
            
            tPealtipoitem = new ArrayList<Pealtipoitem>();
        }
        
        
        return tPealtipoitem;
    }
     
     
      public List<Pealtipoitem> getAlmacenForPedido(Pealtipoitem a) {
        
        String jpa = "SELECT pti "
                    + "FROM Pealtipoitem pti , Almacen a, Tipoitem ti, Altipoitem ati "
                    + "WHERE a.idalmacen = pti.pealtipoitemPK.idalmacen "
                    + "AND pti.pealtipoitemPK.idtipoItem = ati.altipoitemPK.idtipoItem "
                    + "AND pti.pealtipoitemPK.idalmacen = ati.altipoitemPK.idalmacen "
                    + "AND ti.idtipoItem = ati.altipoitemPK.idtipoItem "
                    + "AND pti.pealtipoitemPK.idalmacen =  "+a.getAltipoitem().getAlmacen().getIdalmacen();

        return em.createQuery(jpa,Pealtipoitem.class).getResultList();
        
    }
      public Tipoitem getTipoItemById(TipoItemDTO dto){
        Tipoitem entidad = new Tipoitem();
        try{
            String sql = "SELECT a "
                   + "FROM Tipoitem a "
                   + "WHERE a.idTipoitem = " +dto.getIdtipoItem();
            entidad=em.createQuery(sql, Tipoitem.class).getSingleResult();
        }catch(Exception e){
            entidad = new Tipoitem();
        }
        
        return entidad;
    }
      public List<Pealtipoitem> getPRE(int idalmacen,PealtipoitemDTO ct) {
        String jpa = "SELECT i "
                + "FROM Pealtipoitem i, Altipoitem a "
                + "where i.idalmacen=a.idalmacen "
                + "and a.idalmacen= "+idalmacen+" "
                + "and i.estado=0 ";

        return em.createQuery(jpa, Pealtipoitem.class).setMaxResults(ct.getCantidad()).getResultList();
    }
    
}
