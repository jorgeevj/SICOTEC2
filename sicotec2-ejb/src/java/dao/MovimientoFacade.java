/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.MovimientoDTO;
import entidades.Item;
import entidades.Movimiento;
import java.text.SimpleDateFormat;
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
                       + "FROM item i, "
                       + "     movimientoItem mi "
                       + "WHERE mi.idmovimiento = "+mov.getIdmovimiento()+" "
                       + "AND   mi.iditem      = i.iditem;";

            Query query = em.createNativeQuery(jpa,Item.class);
            listaItems = query.getResultList();
            
        }catch(Exception e){
            listaItems = new ArrayList<Item>();
        }
        
        return listaItems;
    }
    
    public List<Movimiento> getMovimientoByBusqueda(MovimientoDTO mov){
        List<Movimiento> listaMov = new ArrayList<Movimiento>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            String jpa = "SELECT m "
                       + "FROM Movimiento m "
                       + "WHERE 1 = 1 ";
            
            if(mov.getIdTipoMovimiento() != 0){
                jpa += "AND m.idtipoMovimiento.idtipoMovimiento = :tipoMov ";
            }
            if(mov.getIddocumento() !=  0){
                jpa += "AND m.iddocumento = :documento ";
            }
            if(mov.getEstado() != 100){
                jpa += "AND m.estado = :estado ";
            }
            if(mov.getSerie() != null && !mov.getSerie().equals("")){
                
                jpa += "AND m.serie = :serie ";
            }
            if(mov.getFechaInicio() != null){
                jpa += "AND m.fecha >= '"+sdf.format(mov.getFechaInicio())+"' ";
            }
            if(mov.getFechaFin()!= null){
                jpa += "AND m.fecha <= '"+sdf.format(mov.getFechaFin())+"' ";
            }
            if(mov.getCorrelativo()!= null && !mov.getCorrelativo().equals("")){
                jpa += "AND m.correlativo = :correlativo ";
            }

            Query query = em.createQuery(jpa);
            
            if(mov.getIdTipoMovimiento() != 0){
                query.setParameter("tipoMov", mov.getIdTipoMovimiento());
            }
            if(mov.getIddocumento() != 0){
                query.setParameter("documento", mov.getIddocumento());
            }
            if(mov.getEstado() != 100){
                query.setParameter("estado", mov.getEstado());
            }
            if(mov.getSerie() != null && !mov.getSerie().equals("")){
                query.setParameter("serie", mov.getSerie());
            }
            if(mov.getCorrelativo()!= null && !mov.getCorrelativo().equals("")){
                query.setParameter("correlativo", mov.getCorrelativo());
            }
  
            listaMov = query.getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
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
    
    public Movimiento insertMovimiento(Movimiento mov){
        getEntityManager().persist(mov);
        getEntityManager().flush();
        
        return mov;
    }
}
