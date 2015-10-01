/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CotizacionDTO;
import entidades.Cotizacion;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Jorge
 */
@Stateless
public class CotizacionFacade extends AbstractFacade<Cotizacion> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CotizacionFacade() {
        super(Cotizacion.class);
    }
    public List<Cotizacion> buscarCotizacion(CotizacionDTO dto) {
   
            String sql = "SELECT c FROM Cotizacion c where 1=1 ";
            if(dto.getIdcotizacion()!=0){
            sql+="and c.idcotizacion = "+dto.getIdcotizacion()+" ";
            }      
            if(dto.getIdempresa().getNombre()!=null && !dto.getIdempresa().getNombre().equals("")){
            sql+="and c.idempresa.nombre = '"+dto.getIdempresa().getNombre()+"' ";
            }
            if(dto.getIdempresa().getRuc()!=null && !dto.getIdempresa().getRuc().equals("")){
            sql+="and c.idempresa.ruc = '"+dto.getIdempresa().getRuc()+"' ";
            }
            if(dto.getEstado()!=0){
            sql+="and c.estado = "+dto.getEstado()+" ";
            }
            if(dto.getEntrega()!=0){
            sql+="and c.entrega = "+dto.getEntrega()+" ";
            }
            if(dto.getIdalmacen()!=0){
            sql+="and c.idalmacen = "+dto.getIdalmacen()+" ";
            }
           List<Cotizacion> l=em.createQuery(sql, Cotizacion.class).getResultList();
          return l; 
        
    } 
}
