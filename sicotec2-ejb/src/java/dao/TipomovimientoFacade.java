/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Tipomovimiento;
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
public class TipomovimientoFacade extends AbstractFacade<Tipomovimiento> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipomovimientoFacade() {
        super(Tipomovimiento.class);
    }
    public List<Tipomovimiento> getAllTMovimiento(){
        List<Tipomovimiento> tMovimiento = new ArrayList<Tipomovimiento>();
        
        
        try{
            String jpa = "SELECT t "
                       + "FROM Tipomovimiento t";

            Query query = em.createQuery(jpa,Tipomovimiento.class);
            tMovimiento = query.getResultList();
            
        }catch(Exception e){
            
            tMovimiento = new ArrayList<Tipomovimiento>();
        }
        
        
        return tMovimiento;
    }
}
