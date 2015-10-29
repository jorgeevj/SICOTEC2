/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entidades.Item;
import entidades.Venta;
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
public class VentaFacade extends AbstractFacade<Venta> {

    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VentaFacade() {
        super(Venta.class);
    }

    public Venta createVenta(Venta v) {
        em.persist(v);
        em.flush();
        return v;
    }
    
    public List<Venta> getVentasxEstado(int idEstado){
         List<Venta> compras = new ArrayList<Venta>();
         try{
             String sql = "SELECT v "
                        + "FROM Venta v "
                        + "WHERE v.estado = :estado";
             
             Query query = em.createQuery(sql);
             query.setParameter("estado", idEstado+"");
             
             compras = query.getResultList();
         }catch(Exception e){
             System.out.println(e.getMessage());
             compras = new ArrayList<Venta>();
         }
         
         return compras;
     }
    
    public List<Item> getItemsxVenta(int idVenta){
        List<Item> items = new ArrayList<Item>();
         try{
             String sql = "SELECT i.* "
                        + "FROM veitem vi, "
                        + "     item i "
                        + "WHERE vi.iditem = i.iditem "
                        + "AND vi.idventa = "+idVenta;
             
             Query query = em.createNativeQuery(sql, Item.class);
             
             items = query.getResultList();
         }catch(Exception e){
             System.out.println(e.getMessage());
             items = new ArrayList<Item>();
         }
         
         return items;
    }
    
    
    public boolean cambiarEstadoVenta(int idEstado, int idVenta){
         boolean tof = false;
        try{
            String sql = "UPDATE venta "
                       + "SET estado = "+idEstado+" "
                       + "WHERE idventa = "+idVenta;
                    
            Query query = em.createNativeQuery(sql);
            
            int i = query.executeUpdate();
            if(i == 1){
                tof = true;
            }
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        return tof;
     }
}
