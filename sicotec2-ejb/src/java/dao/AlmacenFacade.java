/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.AlmacenDTO;
import entidades.Almacen;
import entidades.Pealtipoitem;
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
}

