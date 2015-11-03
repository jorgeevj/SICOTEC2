/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CotipoitemDTO;
import dto.MovimientoDTO;
import entidades.Movimientoitemvista;
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
public class ItemFacade extends AbstractFacade<Item> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }

    public List<Item> getItemForVenta(CotipoitemDTO ct,Venta v) {
        String jpa = "select i from Item i "
                + "where i.altipoitem.tipoitem.idtipoItem='"+ct.getTipoitem().getIdtipoItem()+"' "
                + "and i.altipoitem.almacen.idalmacen= "+v.getIdalmacen()+" "
                + "and i.estado='0' "
                + "and i.operatividad='0' "
                + "order by i.lote.compra.fecha";

        return em.createQuery(jpa, Item.class).setMaxResults(ct.getCantidad()).getResultList();
    }
    public List<Movimientoitemvista> getAllItems(){
        List<Movimientoitemvista> listaItems = new ArrayList<Movimientoitemvista>();
        try{
             String ejbQuery = "{CALL vistaItems_x_movimiento(?,?)}";
             Query query = em.createNativeQuery(ejbQuery, Movimientoitemvista.class);
             query.setParameter(1, 0);
             query.setParameter(2, 0);
             
            listaItems = query.getResultList();   
        }catch(Exception e){
            System.out.println(e.getMessage());
            listaItems = new ArrayList<Movimientoitemvista>();
        }
        
        return listaItems;
    }
    
    public List<Movimientoitemvista> getItemsByAlmacen(int idAlmacen){
        List<Movimientoitemvista> listaItems = new ArrayList<Movimientoitemvista>();
        try{
             String ejbQuery = "{CALL vistaItems_x_movimiento(?,?)}";
             Query query = em.createNativeQuery(ejbQuery, Movimientoitemvista.class);
             query.setParameter(1, 0);
             query.setParameter(2, idAlmacen);
             
            listaItems = query.getResultList();   
        }catch(Exception e){
            System.out.println(e.getMessage());
            listaItems = new ArrayList<Movimientoitemvista>();
        }
        
        return listaItems;
    }
    
    public Item insertItem(Item item){
        getEntityManager().persist(item);
        getEntityManager().flush();
        
        return item;
    }
    
}
