/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.AltipoitemFacade;
import dao.CategoriaFacade;
import dao.CotipoitemFacade;
import dao.CotizacionFacade;
import dao.DocalmacenFacade;
import dao.EmpresaFacade;
import dao.ItemFacade;
import dao.TipoitemFacade;
import dao.VentaFacade;
import dto.CotipoitemDTO;
import dto.CotizacionDTO;
import dto.EmpresaDTO;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Categoria;
import entidades.Cotipoitem;
import entidades.CotipoitemPK;
import entidades.Cotizacion;
import entidades.Docalmacen;
import entidades.Empresa;
import entidades.Impuesto;
import entidades.Item;
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
    private AltipoitemFacade altipoitemFacade;
    
    @EJB
    private ItemFacade itemFacade;
    
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
    
    public boolean isEmpDistribudora(Empresa e) {
        e = empresaFacade.find(e.getIdempresa());
        for (Tipo t : e.getTipoList()) {
            if (t.getIdtipo() == 1) {
                return true;
            }
        }
        return false;
    }
    
    public Docalmacen updateDocAlm(Docalmacen dal) {
        Docalmacen d = docalmacenFacade.findBy2key(dal.getDocalmacenPK().getIdalmacen(), dal.getDocalmacenPK().getIddocumento());
        if (d.getCorrelativo() < 999999) {
            dal.setCorrelativo(d.getCorrelativo() + 1);
            dal.setSerie(d.getSerie());
        } else {
            dal.setSerie(d.getSerie() + 1);
            dal.setCorrelativo(1);
        }
        docalmacenFacade.edit(dal);
        return dal;
    }
    
    public Cotizacion guardarCrear(Cotizacion c) {
        Docalmacen da = docalmacenFacade.findBy2key(c.getIdalmacen(), 1);
        da = updateDocAlm(da);
        if (c.getDuracion() == null) {
            c.setDuracion(0);
        }
        c.setSerie(String.format("%03d", da.getSerie()));
        c.setCorrelativo(String.format("%06d", da.getCorrelativo()));
        return cotizacionFacade.guardaCot(c);
    }
    
    public void guardarEditar(CotizacionDTO c) {
        cotizacionFacade.edit(convertDTObyEntidad(c));
    }
    
    public Cotizacion convertDTObyEntidad(CotizacionDTO c) {
        Cotizacion entidad = new Cotizacion();
        entidad.setIdcotizacion(c.getIdcotizacion());
        entidad.setIdempresa(c.getIdempresa());
        entidad.setIdalmacen(c.getIdalmacen());
        entidad.setEntrega(c.getEntrega());
        entidad.setDuracion(c.getDuracion());
        entidad.setEstado(c.getEstado());
        entidad.setFechaEnvio(c.getFechaEnvio());
        entidad.setSerie(c.getSerie());
        entidad.setCorrelativo(c.getCorrelativo());
//    entidad.setCotipoitemList(c.getCotipoitemList());

        return entidad;
    }
    
    public void eliminarItemsByCOt(Integer idcotizacion) {
        List<Cotipoitem> ct = cotipoitemFacade.findByCot(idcotizacion);
        for (Cotipoitem e : ct) {
            cotipoitemFacade.remove(e);
        }
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
//        dto.setCotipoitemList(c.getCotipoitemList());
        dto.setNombAlmacen(almacenFacade.getNombreAlmxID(c.getIdalmacen()).getNombre());
        return dto;
    }
    
    public List<CotipoitemDTO> getListCotipoitemByidCot(int idcotizacion) {
        return converListCotipoitemByDTO(cotipoitemFacade.findByCot(idcotizacion));
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
    
    public void generaVentaCrea(List<CotipoitemDTO> ct, Cotizacion c) {
//        Veitem vi;
        List<Item> li;
        Altipoitem ati;
        double total = 0, descuento = 0;
        Venta v = new Venta();
        v.setEstado("1");
        v.setFecha(new Date());
        v.setIdempresa(c.getIdempresa());
        v.setIdimpuesto(new Impuesto(1));
        for (CotipoitemDTO dto : ct) {
            total += dto.getCantidad() * dto.getPrecio();
            descuento += dto.getCantidad() * dto.getDescuento();
        }
        v.setTotal(total);
        v.setIdalmacen(c.getIdalmacen());
        v.setDescuento(descuento);
        v.setIddocumento(2);
        Docalmacen da=updateDocAlm(docalmacenFacade.findBy2key(v.getIdalmacen(), 2));
        v.setCorrelativo(String.format("%06d",da.getCorrelativo()));
        v.setSerie(String.format("%03d",da.getSerie()));
        v = ventaFacade.createVenta(v);
        for (CotipoitemDTO dto : ct) {
            li = itemFacade.getItemForVenta(dto, v);
            for (Item it : li) {
                it.setEstado("2");
                it.setIdventa(v);
                itemFacade.edit(it);
                
                ati = altipoitemFacade.find(new AltipoitemPK(v.getIdalmacen(), dto.getTipoitem().getIdtipoItem()));
                ati.setCantidad(ati.getCantidad() - 1);
                altipoitemFacade.edit(ati);
            }
            
        }
    }
    
    private List<CotipoitemDTO> converListCotipoitemByDTO(List<Cotipoitem> lct) {
        List<CotipoitemDTO> ldto = new ArrayList<>();
        for (Cotipoitem ct : lct) {
            ldto.add(convertCotipoitenByDTO(ct));
        }
        return ldto;
    }
    
    private CotipoitemDTO convertCotipoitenByDTO(Cotipoitem ct) {
        CotipoitemDTO dto = new CotipoitemDTO();
        dto.setAlmacen(ct.getCotizacion().getIdalmacen());
        dto.setCantidad(ct.getCantidad());
        dto.setCotizacion(ct.getCotizacion());
        dto.setDescuento(ct.getDescuento());
        dto.setPrecio(ct.getPrecio());
        dto.setTipoitem(ct.getTipoitem());
        return dto;
    }
    
    public void generaVentaEdit(List<CotipoitemDTO> listaCoItemSelect, CotizacionDTO cotizacionSelec) {
        
        generaVentaCrea(listaCoItemSelect, convertDTObyEntidad(cotizacionSelec));
        
    }
    
}
