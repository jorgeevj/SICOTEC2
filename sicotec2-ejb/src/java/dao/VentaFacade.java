/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.VentaDTO;
import entidades.Item;
import entidades.Vemediopago;
import entidades.Venta;
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
                        + "FROM Venta v ";
             
             if(idEstado != 0){
                 sql+= "WHERE v.estado = :estado";
             }
                        
             
             Query query = em.createQuery(sql);
             if(idEstado != 0){
                 query.setParameter("estado", idEstado+"");
             }

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
             String sql = "SELECT i "
                        + "FROM Item i " 
                        + "WHERE i.idventa.idventa = :idVenta";
             
             Query query = em.createQuery(sql);
             query.setParameter("idVenta", idVenta);
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
    
    public List<Vemediopago>getListavMedioPago(int idVenta){
        List<Vemediopago> listaVenta = new ArrayList<Vemediopago>();
        try{
            String jpa = "SELECT v "
                       + "FROM Vemediopago v "
                       + "WHERE v.venta.idventa = :idVenta ";
            
            Query query = em.createQuery(jpa);
            query.setParameter("idVenta", idVenta);
            
            listaVenta = query.getResultList();
        }catch(Exception e){
            listaVenta = new ArrayList<Vemediopago>();
        }
        
        return listaVenta;
    }
    
    public List<Venta> getVentasByBusqueda(VentaDTO ven){
        List<Venta> listaVenta = new ArrayList<Venta>();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        try{
            String jpa = "SELECT v "
                       + "FROM Venta v "
                       + "WHERE 1 = 1 ";
            
            if(ven.getEstado()!= "100" && !ven.getEstado().equals("100")){
                jpa += "AND v.estado = :estado ";
            }
            if(ven.getIdalmacen() !=  0){
                jpa += "AND v.idalmacen = :idAlmacen ";
            }
            if(ven.getIdEmpresa() != 0){
                jpa += "AND v.idempresa.idempresa = :idEmpresa ";
            }
            if(ven.getSerie() != null && !ven.getSerie().equals("")){
                jpa += "AND v.serie = :serie ";
            }
            if(ven.getFechaInicio() != null){
                jpa += "AND v.fecha >= '"+sdf.format(ven.getFechaInicio())+"' ";
            }
            if(ven.getFechaFin()!= null){
                jpa += "AND v.fecha <= '"+sdf.format(ven.getFechaFin())+"' ";
            }
            if(ven.getCorrelativo()!= null && !ven.getCorrelativo().equals("")){
                jpa += "AND v.correlativo = :correlativo ";
            }

            Query query = em.createQuery(jpa);
            
            if(ven.getEstado()!= "100" && !ven.getEstado().equals("100")){
                query.setParameter("estado", ven.getEstado());
            }
            if(ven.getIdalmacen() !=  0){
                query.setParameter("idAlmacen", ven.getIdalmacen());
            }
            if(ven.getIdEmpresa() != 0){
                query.setParameter("idEmpresa", ven.getIdEmpresa());
            }
            if(ven.getSerie() != null && !ven.getSerie().equals("")){
                query.setParameter("serie", ven.getSerie());
            }
            if(ven.getCorrelativo()!= null && !ven.getCorrelativo().equals("")){
                query.setParameter("correlativo", ven.getCorrelativo());
            }
  
            listaVenta = query.getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
            listaVenta = new ArrayList<Venta>();
        }
        
        return listaVenta;
    }
}
