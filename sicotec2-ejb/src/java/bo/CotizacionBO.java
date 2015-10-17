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
import entidades.Tipoitem;
import java.util.ArrayList;
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

    public Docalmacen updateDocAlm(Docalmacen dal) {
        Docalmacen d = docalmacenFacade.findBy2key(dal.getDocalmacenPK().getIdalmacen(), dal.getDocalmacenPK().getIddocumento());
        dal.setCorrelativo(d.getCorrelativo() + 1);
        dal.setSerie(d.getSerie());
        docalmacenFacade.edit(dal);
        return dal;
    }

    public Cotizacion guardarCrear(Cotizacion c) {
        Docalmacen da = docalmacenFacade.findBy2key(c.getIdalmacen(), 1);
        da = updateDocAlm(da);
        c.setSerie(da.getSerie() + "");
        c.setCorrelativo(da.getCorrelativo() + "");
//        c.setDuracion(Integer.SIZE);
        return cotizacionFacade.guardaCot(c);
//        return null;
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

}
