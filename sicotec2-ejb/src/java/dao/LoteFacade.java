/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Lote;
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
public class LoteFacade extends AbstractFacade<Lote> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LoteFacade() {
        super(Lote.class);
    }
    
    public List<Lote> getLotesByCompra(int idCompra){
        List<Lote> lotes = new ArrayList<Lote>();
        try{
            String jpa = "SELECT l "
                        +"FROM Lote l "
                        +"WHERE l.compra.idcompra = :idCompra";
            
            Query query = em.createQuery(jpa);
            query.setParameter("idCompra", idCompra);
            
            lotes = query.getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
            lotes = new ArrayList<Lote>();
        }
        
        return lotes;
    }
    
}
