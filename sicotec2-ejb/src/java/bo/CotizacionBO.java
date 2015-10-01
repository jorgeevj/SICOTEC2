/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.CotizacionFacade;
import dao.MovimientoFacade;
import dao.TipomovimientoFacade;
import dto.AlmacenDTO;
import dto.CotizacionDTO;
import entidades.Cotizacion;
import entidades.Movimiento;
import entidades.Tipomovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Jorge
 * 15.09.2015
 */
@Stateless
@LocalBean
public class CotizacionBO {
    @EJB
    private AlmacenFacade almacenFacade;
  
   
    @EJB
    private CotizacionFacade cotizacionFacade;

    public List<CotizacionDTO> getAllCotizaciones() {
        
        List<CotizacionDTO> lista=convertListaEntxListaDTO(cotizacionFacade.findAll());
        return lista;
    }
    
    public List<CotizacionDTO> BuscarCotizacion(CotizacionDTO dto) {
       List<CotizacionDTO> listCot=new ArrayList<>();
      listCot=convertListaEntxListaDTO(cotizacionFacade.buscarCotizacion(dto));
        
    return listCot;
    }

// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private List<CotizacionDTO> convertListaEntxListaDTO(List<Cotizacion> buscarCotizacion) {
       List<CotizacionDTO> listaDTO=new ArrayList<>();
       for(Cotizacion c :buscarCotizacion){
       listaDTO.add(convetEntityxDTO(c));
       }
       return listaDTO;
//To change body of generated methods, choose Tools | Templates.
    }

    private CotizacionDTO convetEntityxDTO(Cotizacion c) {
       CotizacionDTO dto=new CotizacionDTO();
       dto.setIdcotizacion(c.getIdcotizacion());
       dto.setIdempresa(c.getIdempresa());
       dto.setSerie(c.getSerie());
       dto.setCorrelativo(c.getCorrelativo());
       dto.setDuracion(c.getDuracion());
       dto.setEntrega(c.getEntrega());
       dto.setFechaEnvio(c.getFechaEnvio());
       dto.setEstado(c.getEstado());
       dto.setIdalmacen(c.getIdalmacen());
       dto.setNombAlmacen(almacenFacade.getNombreAlmxID(c.getIdalmacen()).getNombre());
    return dto;   
    }
}