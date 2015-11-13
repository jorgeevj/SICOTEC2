/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CotipoitemDTO;
import dto.MovimientoDTO;
import dto.TipoItemDTO;
import entidades.Altipoitem;
import entidades.Movimientoitemvista;
import entidades.Item;
import entidades.Tipoitem;
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
    
    public List<TipoItemDTO> getItemsByAlmacen(int idAlmacen){
        List<TipoItemDTO> listaItems = new ArrayList<TipoItemDTO>();
        try{
             String ejbQuery = "SELECT al "
                             + "FROM Altipoitem al "
                             + "WHERE  al.almacen.idalmacen = "+idAlmacen+" "
                             + "AND    al.estado = 1";
             Query query = em.createQuery(ejbQuery);
             
            List<Altipoitem>altipoitem = query.getResultList();   
            for(Altipoitem al : altipoitem){
                TipoItemDTO dto = new TipoItemDTO();
                dto.setIdtipoItem(al.getTipoitem().getIdtipoItem());
                dto.setDescipcion(al.getTipoitem().getDescripcion());
                dto.setCantidad(al.getCantidad() - al.getReservado() - al.getComprados());
                dto.setNumParte(al.getTipoitem().getNumParte());
                
                String ejbQuery1 = "SELECT idlote "
                                 + "FROM lote "
                                 + "WHERE idtipoitem = '"+al.getTipoitem().getIdtipoItem()+"' "
                                 + "AND idalmacen = "+idAlmacen;
                
                int idLote = (int)em.createNativeQuery(ejbQuery1).getSingleResult();
                dto.setIdLote(idLote);
                
                listaItems.add(dto);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            listaItems = new ArrayList<TipoItemDTO>();
        }
        
        return listaItems;
    }
    
    public boolean validateCod(String cod){
        boolean tof = false;
        try{
            String sql = "SELECT i "
                       + "FROM Item i "
                       + "WHERE i.iditem = :idItem";
            Query query = em.createQuery(sql);
            query.setParameter("idItem", cod);
            
            Item i = (Item)query.getSingleResult();
            if(i != null){
                tof = true;
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
        return tof;
    }
    
    public boolean validateCodTranslado(String idTipoItem, String cod){
        boolean tof = true;
        try{
            String sql = "SELECT i "
                       + "FROM Item i "
                       + "WHERE i.iditem = :idItem";
            Query query = em.createQuery(sql);
            query.setParameter("idItem", cod);
            
            Item i = (Item)query.getSingleResult();
            if(i != null){
                if(i.getAltipoitem().getAltipoitemPK().getIdtipoItem().equals(idTipoItem)){
                    if(i.getEstado().equals("0")){
                        tof = false;
                    }
                }
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            tof = true;
        }
        
        return tof;
    }
    
    public boolean cambiarEstadoItem(int idEstado, String idItem){
         boolean tof = false;
        try{
            String sql = "UPDATE item "
                       + "SET    estado = "+idEstado+" "
                       + "WHERE  iditem = "+idItem;
                    
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
    
    public boolean cambiarAlmacenItem(String idItem, int idAlmacen){
         boolean tof = false;
        try{
            String sql = "UPDATE item "
                       + "SET    idalmacen = "+idAlmacen+" "
                       + "WHERE  iditem = '"+idItem+"'";
                    
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
    
    public Item insertItem(Item item){
        getEntityManager().persist(item);
        getEntityManager().flush();
        
        return item;
    }
    
}
