/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Pealtipoitem;
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
    
}
