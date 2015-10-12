/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.CotizacionFacade;
import dao.EmpresaFacade;
import dao.TipoitemFacade;
import dto.CotizacionDTO;
import dto.EmpresaDTO;
import entidades.Cotizacion;
import entidades.Empresa;
import entidades.Tipoitem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author Jorge
 * 15.09.2015
 */
@Stateless
@LocalBean
public class CotizacionBO {
    @EJB
    private TipoitemFacade tipoitemFacade;
    @EJB
    private EmpresaFacade empresaFacade;
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
    
    public List<SelectItem> listItemEmpresasAll(){
    List<SelectItem> l=new ArrayList<>();
    for(Empresa e:empresasAll()){
    l.add(new SelectItem(e.getIdempresa()+"", e.getNombre()));
    }
        return l;
    }
    
    public List<Empresa> empresasAll(){
        return empresaFacade.findAll();
    }
    public List<Tipoitem> tipoItemAll(){
        return tipoitemFacade.findAll();
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
       dto.setEmpresaDTO(new  EmpresaDTO(c.getIdempresa().getNombre(), c.getIdempresa().getRuc(), c.getIdempresa().getIdempresa(), c.getIdempresa().getTipo()));
       dto.setSerie(c.getSerie());
       dto.setCorrelativo(c.getCorrelativo());
       dto.setDuracion(c.getDuracion());
       dto.setEntrega(c.getEntrega());
       dto.setFechaEnvio(c.getFechaEnvio());
       dto.setEstado(c.getEstado());
       dto.setIdalmacen(c.getIdalmacen());
       dto.setIdalm(c.getIdalmacen());
       dto.setNombAlmacen(almacenFacade.getNombreAlmxID(c.getIdalmacen()).getNombre());
    return dto;   
    }
}