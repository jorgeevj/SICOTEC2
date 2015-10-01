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
                       + "WHERE m.idtipoMovimiento = :tipoMov "
                       + "AND   m.iddocumento      = :documento "
                       + "AND   m.estado           = :estado "
                       + "AND   m.serie            = :serie";

            Query query = em.createNativeQuery(jpa,Movimiento.class);
            query.setParameter("tipoMov", mov.getTipoMovimiento());
            query.setParameter("documento", mov.getIddocumento());
            query.setParameter("estado", mov.getEstado());
            query.setParameter("serie", mov.getSerie());
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
