/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.CotizacionFacade;
import dao.MovimientoFacade;
import dao.TipomovimientoFacade;
import dto.CotizacionDTO;
import entidades.Cotizacion;
import entidades.Movimiento;
import entidades.Tipomovimiento;
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
    private TipomovimientoFacade tipomovimientoFacade;
    @EJB
    private MovimientoFacade movimientoFacade;
   
    @EJB
    private CotizacionFacade cotizacionFacade;

    public List<Cotizacion> getAllCotizaciones() {
        
        List<Cotizacion> lista=cotizacionFacade.findAll();
        return lista;
    }
    
    public List<Cotizacion> BuscarCotizacion(CotizacionDTO dto) {
    return cotizacionFacade.buscarCotizacion(dto);
    }

// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    public void prueba() {
    List<Movimiento> t =movimientoFacade.findAll();
    List<Tipomovimiento> g =tipomovimientoFacade.findAll();
    System.out.print("hola");
    }
}
