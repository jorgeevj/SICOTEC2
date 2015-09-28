/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Compra;
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
public class CompraFacade extends AbstractFacade<Compra> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    public List<Compra> getAllCompra(){
        List<Compra> tCompra = new ArrayList<Compra>();
        
        
        try{
            String jpa = "SELECT t "
                       + "FROM Compra t "
                       + "JOIN FETCH t.idempresa";
                   
            Query query = em.createQuery(jpa,Compra.class);
            tCompra = query.getResultList();
            
        }catch(Exception e){
            
            tCompra = new ArrayList<Compra>();
        }
        
        
        return tCompra;
    }
}
