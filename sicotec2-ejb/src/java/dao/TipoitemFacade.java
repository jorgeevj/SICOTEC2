/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

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
public class TipoitemFacade extends AbstractFacade<Tipoitem> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TipoitemFacade() {
        super(Tipoitem.class);
    }
     public List<Tipoitem> getAllTipoItem(){
        List<Tipoitem> ti = new ArrayList<Tipoitem>();
        
        return ti;
    }
    
    public List<Tipoitem> getAllBusqueda(Tipoitem e){
        List<Tipoitem> lista= new ArrayList<Tipoitem>();
        String sql="SELECT t FROM Tipoitem t WHERE t.nombre='"+e.getNombre()+"'";
        Query q=em.createQuery(sql,Tipoitem.class);
        lista=q.getResultList();       
        return lista;
    } 
    public void insertarTipoItem(){
        
    }
    
}
