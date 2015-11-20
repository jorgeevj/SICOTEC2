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
        String sql="SELECT t FROM Tipoitem t where 1=1 ";
            if(e.getIdtipoItem()!=null && !e.getIdtipoItem().equals("")){
            sql+="and t.idtipoItem like '%"+e.getIdtipoItem()+"%' ";
            }             
            if(e.getNombre()!=null && !e.getNombre().equals("")){
            sql+="and t.nombre like '%"+e.getNombre()+"%' ";
            }            
            if(e.getIdmarca().getIdmarca()!=0){
            sql+="and t.idmarca.idmarca ="+e.getIdmarca().getIdmarca();
            }
            
            
        Query q=em.createQuery(sql,Tipoitem.class);
        lista=q.getResultList();       
        return lista;     
           
    } 
    
    public List<Tipoitem> getBusquedaDuplicados(Tipoitem e){
        List<Tipoitem> lista= new ArrayList<Tipoitem>();
        String sql="SELECT t FROM Tipoitem t where t.idtipoItem='"+e.getIdtipoItem()+"'";
        Query q=em.createQuery(sql,Tipoitem.class);
        lista=q.getResultList();       
        return lista;     
           
    } 
    
    public List<Tipoitem> getBusquedaCaracteristicasDuplicados(Tipoitem e){
        List<Tipoitem> lista= new ArrayList<Tipoitem>();
        String sql="SELECT t FROM Tipoitem t where t.idtipoItem="+e.getIdtipoItem();
        Query q=em.createQuery(sql,Tipoitem.class);
        lista=q.getResultList();       
        return lista;     
           
    } 
    public Tipoitem getTipoItemsNombre(String idtipoItem){
        Tipoitem entidad = new Tipoitem();
        try{
            String sql = "SELECT a "
                   + "FROM Tipoitem a "
                   + "WHERE a.idtipoItem = '" + idtipoItem+"'";
            entidad=em.createQuery(sql, Tipoitem.class).getSingleResult();
        }catch(Exception e){
            entidad = new Tipoitem();
        }
        return entidad;
    }
    
    
    
    public void insertarTipoItem(){
        
    }
    
}
