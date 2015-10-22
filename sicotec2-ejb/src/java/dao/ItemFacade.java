/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CotipoitemDTO;
import dto.MovimientoDTO;
import dto.MovimientoitemDTOVista;
import entidades.Item;
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

    public List<Item> getItemForVenta(int idalmacen,CotipoitemDTO ct) {
        String jpa = "SELECT i "
                + "FROM Item i, Lote l, Compra c "
                + "where c.idcompra=l.idcompra.idcompra "
                + "and i.idlote.idlote=l.idlote "
                + "and l.altipoitem.tipoitem.idtipoItem='"+ct.getTipoitem().getIdtipoItem()+"' "
                + "and c.idalmacen=l.altipoitem.almacen.idalmacen "
                + "and c.idalmacen= "+idalmacen+" "
                + "and i.estado=0 "
                + "and i.operatividad=0 "
                + "order by c.fecha";

        return em.createQuery(jpa, Item.class).setMaxResults(ct.getCantidad()).getResultList();
    }
    public List<MovimientoitemDTOVista> getAllItems(){
        List<MovimientoitemDTOVista> listaItems = new ArrayList<MovimientoitemDTOVista>();
        try{
             String ejbQuery = "{CALL vistaItems_x_movimiento(?)}";
             Query query = em.createNativeQuery(ejbQuery, MovimientoitemDTOVista.class);
             query.setParameter(1, 0);
             
            listaItems = query.getResultList();   
        }catch(Exception e){
            System.out.println(e.getMessage());
            listaItems = new ArrayList<MovimientoitemDTOVista>();
        }
        
        return listaItems;
    }
    
}
