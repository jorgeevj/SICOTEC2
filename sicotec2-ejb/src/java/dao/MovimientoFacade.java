/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MovimientoDTO;
import entidades.Item;
import entidades.Movimiento;
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
public class MovimientoFacade extends AbstractFacade<Movimiento> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MovimientoFacade() {
        super(Movimiento.class);
    }
     public List<Item> getItemsByMovimiento(MovimientoDTO mov){
        List<Item> listaItems = new ArrayList<Item>();
        try{
            String jpa = "SELECT i.* "
                       + "FROM movimiento m, "
                       + "     item i, "
                       + "     movimientoItem mi  "
                       + "WHERE m.idmovimiento = m.idmovimientoand   "
                       + "AND   mi.iditem      = i.iditem;";

            Query query = em.createQuery(jpa,Item.class);
            listaItems = query.getResultList();
            
        }catch(Exception e){
            listaItems = new ArrayList<Item>();
        }
        
        return listaItems;
    }
    
    public List<Movimiento> getMovimientoByBusqueda(MovimientoDTO mov){
        List<Movimiento> listaMov = new ArrayList<Movimiento>();
        try{
            String jpa = "SELECT m "
                       + "FROM Movimiento m "
                       + "WHERE 1 = 1 ";
            if(mov.getIdTipoMovimiento() != null){
                jpa += "AND m.idtipoMovimiento = :tipoMov ";
            }
            if(mov.getIdTipoDocumento() != null){
                jpa += "AND m.iddocumento = :documento ";
            }
            if(mov.getEstado() != null){
                jpa += "AND m.estado = :estado ";
            }
            if(mov.getSerie() != null){
                jpa += "AND m.serie = :serie ";
            }
            if(mov.getFechaInicio() != null){
                jpa += "AND m.fecha > :fInicio ";
            }
            if(mov.getFechaFin()!= null){
                jpa += "AND m.fecha < :fFin ";
            }
            if(mov.getCorrelativo()!= null){
                jpa += "AND m.correlativo = :correlativo ";
            }

            Query query = em.createNativeQuery(jpa,Movimiento.class);
            
            if(mov.getIdTipoMovimiento() != null){
                query.setParameter("tipoMov", mov.getTipoMovimiento());
            }
            if(mov.getIdTipoDocumento() != null){
                query.setParameter("documento", mov.getIddocumento());
            }
            if(mov.getEstado() != null){
                query.setParameter("estado", mov.getEstado());
            }
            if(mov.getSerie() != null){
                query.setParameter("serie", mov.getSerie());
            }
            if(mov.getFechaInicio() != null){
                query.setParameter("fInicio", mov.getFechaInicio());
            }
            if(mov.getFechaFin()!= null){
                query.setParameter("fFin", mov.getFechaFin());
            }
            if(mov.getCorrelativo()!= null){
                query.setParameter("correlativo", mov.getCorrelativo());
            }
  
            listaMov = query.getResultList();
        }catch(Exception e){
            listaMov = new ArrayList<Movimiento>();
        }
        
        return listaMov;
    }
    
    public List<Movimiento> getAllMovimientos(){
        List<Movimiento> listaMov = new ArrayList<Movimiento>();
        try{
            String jpa = "SELECT m "
                       + "FROM Movimiento m ";

            Query query = em.createQuery(jpa);
            listaMov = query.getResultList();
        }catch(Exception e){
            listaMov = new ArrayList<Movimiento>();
        }
        
        return listaMov;
    }
}
