/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.CategoriaFacade;
import dao.CotipoitemFacade;
import dao.CotizacionFacade;
import dao.DocalmacenFacade;
import dao.EmpresaFacade;
import dao.TipoitemFacade;
import dao.VeitemFacade;
import dao.VentaFacade;
import dto.CotipoitemDTO;
import dto.CotizacionDTO;
import dto.EmpresaDTO;
import entidades.Almacen;
import entidades.Categoria;
import entidades.Cotipoitem;
import entidades.CotipoitemPK;
import entidades.Cotizacion;
import entidades.Docalmacen;
import entidades.Documento;
import entidades.Empresa;
import entidades.Tipo;
import entidades.Tipoitem;
import entidades.Venta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author Jorge 15.09.2015
 */
@Stateless
@LocalBean
public class CotizacionBO {
    @EJB
    private VeitemFacade veitemFacade;
    @EJB
    private VentaFacade ventaFacade;

    @EJB
    private CotipoitemFacade cotipoitemFacade;

    @EJB
    private DocalmacenFacade docalmacenFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private TipoitemFacade tipoitemFacade;
    @EJB
    private EmpresaFacade empresaFacade;
    @EJB
    private AlmacenFacade almacenFacade;
    @EJB
    private CotizacionFacade cotizacionFacade;
    
    public boolean isEmpDistribudora(Empresa e){
        e=empresaFacade.find(e.getIdempresa());
        for (Tipo t : e.getTipoList()) {
        if(t.getIdtipo()==1){
            return true;
        }
        }
    return false;
    }
    
    
    public Docalmacen updateDocAlm(Docalmacen dal) {
        Docalmacen d = docalmacenFacade.findBy2key(dal.getDocalmacenPK().getIdalmacen(), dal.getDocalmacenPK().getIddocumento());
        if(d.getCorrelativo()<999999){
            dal.setCorrelativo(d.getCorrelativo() + 1);
            dal.setSerie(d.getSerie());
        }else{
            dal.setSerie(d.getSerie()+1);
            dal.setCorrelativo(1);
        }
        docalmacenFacade.edit(dal);
        return dal;
    }

    public Cotizacion guardarCrear(Cotizacion c) {
        Docalmacen da = docalmacenFacade.findBy2key(c.getIdalmacen(), 1);
        da = updateDocAlm(da);
        c.setSerie(String.format("%03d", da.getSerie()));
        c.setCorrelativo(String.format("%06d", da.getCorrelativo()));
        return cotizacionFacade.guardaCot(c);
    }

    public void guardarCrearItems(List<CotipoitemDTO> ct) {
        for (CotipoitemDTO c : ct) {
            cotipoitemFacade.create(cotipoitenDTOByEntidad(c));
        }
    }

    public List<Categoria> findCategoriaAll() {
        return categoriaFacade.findAll();
    }

    public List<CotizacionDTO> getAllCotizaciones() {
        List<CotizacionDTO> lista = convertListaEntxListaDTO(cotizacionFacade.findAll());
        return lista;
    }

    public List<CotizacionDTO> BuscarCotizacion(CotizacionDTO dto) {
        List<CotizacionDTO> listCot = new ArrayList<>();
        listCot = convertListaEntxListaDTO(cotizacionFacade.buscarCotizacion(dto));

        return listCot;
    }

    public List<SelectItem> listItemEmpresasAll() {
        List<SelectItem> l = new ArrayList<>();
        for (Empresa e : empresasAll()) {
            l.add(new SelectItem(e.getIdempresa() + "", e.getNombre()));
        }
        return l;
    }

    public List<Empresa> empresasAll() {
        return empresaFacade.findAll();
    }

    public List<Tipoitem> tipoItemAll() {
        return tipoitemFacade.findAll();
    }
// Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    private List<CotizacionDTO> convertListaEntxListaDTO(List<Cotizacion> buscarCotizacion) {
        List<CotizacionDTO> listaDTO = new ArrayList<>();
        for (Cotizacion c : buscarCotizacion) {
            listaDTO.add(convetEntityxDTO(c));
        }
        return listaDTO;
//To change body of generated methods, choose Tools | Templates.
    }

    private CotizacionDTO convetEntityxDTO(Cotizacion c) {
        CotizacionDTO dto = new CotizacionDTO();
        dto.setIdcotizacion(c.getIdcotizacion());
        dto.setIdempresa(c.getIdempresa());
        dto.setEmpresaDTO(new EmpresaDTO(c.getIdempresa().getNombre(), c.getIdempresa().getRuc(), c.getIdempresa().getIdempresa()));
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

   

    private Cotipoitem cotipoitenDTOByEntidad(CotipoitemDTO c) {
        Cotipoitem cti = new Cotipoitem();
        cti.setCotipoitemPK(new CotipoitemPK(c.getCotizacion().getIdcotizacion(), c.getTipoitem().getIdtipoItem()));
        cti.setCantidad(c.getCantidad());
        cti.setCotizacion(c.getCotizacion());
        cti.setTipoitem(c.getTipoitem());
        cti.setDescuento(c.getDescuento());
        cti.setPrecio(c.getPrecio());
        return cti;
    }

    public void generaVentaCrea(List<CotipoitemDTO> ct,Cotizacion c) {
        
        Venta v=new Venta();
        v.setEstado("Generada");
        v.setFecha(new Date());
        v.setIdempresa(c.getIdempresa());
        v=ventaFacade.createVenta(v);
        for (CotipoitemDTO dto : ct) {
            
        }  
    }

}
