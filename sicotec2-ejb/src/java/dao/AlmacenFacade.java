/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AlmacenDTO;
import entidades.Almacen;
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
public class AlmacenFacade extends AbstractFacade<Almacen> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AlmacenFacade() {
        super(Almacen.class);
    }
     public Almacen getNombreAlmxID(int id){
          return em.createQuery("select a from Almacen a where a.idalmacen="+id, Almacen.class).getSingleResult(); 
    }

    public Almacen getAlmacenById(Integer idAlmacen){
        Almacen entidad = new Almacen();
        try{
            String sql = "SELECT a "
                   + "FROM Almacen a "
                   + "WHERE a.idalmacen = " + idAlmacen;
            entidad=em.createQuery(sql, Almacen.class).getSingleResult();
        }catch(Exception e){
            entidad = new Almacen();
        }
        
        return entidad;
    }
    //
    public List<Almacen> getAlmacenForPedido(Almacen a) {
        
        String jpa = "SELECT a.nombre, pti.idalmacen, pti.idtipoItem, pti.idpedido, ti.nombre"
                    + "FROM Almacen a, Tipoitem ti, Pealtipoitem pti, Altipoitem ati "
                    + "WHERE a.idalmacen = pti.pealtipoitemPK.idalmacen "
                    + "AND pti.pealtipoitemPK.idtipoItem = ati.altipoitemPK.idtipoItem "
                    + "AND pti.pealtipoitemPK.idalmacen = ati.altipoitemPK.idalmacen "
                    + "AND ti.idtipoItem = ati.altipoitemPK.idtipoItem "
                    + "AND a.idalmacen = '"+a.getIdalmacen()+"'";
               
        
        
        return em.createQuery(jpa,Almacen.class).getResultList();
        
    }
    
    public List<Almacen> getAllBusqueda(Almacen e){
        List<Almacen> lista= new ArrayList<Almacen>();
        String sql="SELECT t FROM Almacen t where 1=1 ";
            if(e.getNombre()!=null && !e.getNombre().equals("")){
            sql+="and t.nombre like '%"+e.getNombre()+"%' ";
            }             
            if(e.getDireccion()!=null && !e.getDireccion().equals("")){
            sql+="and t.direccion like '%"+e.getDireccion()+"%' ";
            }   
            if(e.getCodDept()!=null && !e.getCodDept().equals("")){
            sql+="and t.codDept like '%"+e.getCodDept()+"%' ";
            } 
            if(e.getCodProv()!=null && !e.getCodProv().equals("")){
            sql+="and t.codProv like '%"+e.getCodProv()+"%' ";
            } 
            if(e.getCodDist()!=null && !e.getCodDist().equals("")){
            sql+="and t.codDist like '%"+e.getCodDist()+"%' ";
            } 
            
        Query q=em.createQuery(sql,Almacen.class);
        lista=q.getResultList();       
        return lista;     
           
    } 
}

