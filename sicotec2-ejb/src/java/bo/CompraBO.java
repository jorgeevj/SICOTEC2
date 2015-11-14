/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.AltipoitemFacade;
import dao.CompraFacade;
import dao.DocalmacenFacade;
import dao.LoteFacade;
import dao.PealtipoitemFacade;
import dao.RequerimientosFacade;
import dao.TipoitemFacade;
import dto.CompraDTO;
import dto.LoteDTO;
import dto.PealtipoitemDTO;
import dto.RequerimientoDTO;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Compra;
import entidades.Docalmacen;
import entidades.Empresa;
import entidades.Lote;
import entidades.LotePK;
import entidades.Pealtipoitem;
import entidades.PealtipoitemPK;
import entidades.Requerimientos;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author jc7
 */
@Stateless
@LocalBean
public class CompraBO {
    @EJB
    private AltipoitemFacade altipoitemFacade;
    @EJB
    private LoteBO loteBO;
    @EJB
    private RequerimientosFacade requerimientosFacade;

    @EJB
    private LoteFacade loteFacade;
    @EJB
    private CotizacionBO cotizacionBO;
    @EJB
    private DocalmacenFacade docalmacenFacade;

    @EJB
    private CompraFacade compraFacade;
    @EJB
    private AlmacenFacade almacenFacade = new AlmacenFacade();
    @EJB
    private PealtipoitemFacade pealtipoitemFacade = new PealtipoitemFacade();

    public List<CompraDTO> getAllCompras() {
        List<Compra> listaCompras = compraFacade.findAll();
        List<CompraDTO> listaComprasDTO = convertListEntityToDTO(listaCompras);

        return listaComprasDTO;
    }

    public List<CompraDTO> getComprasByEstado(int estado) {
        List<Compra> listaCompras = compraFacade.getComprasxEstado(estado);
        List<CompraDTO> listaComprasDTO = convertListEntityToDTO(listaCompras);

        return listaComprasDTO;
    }

    public List<CompraDTO> convertListEntityToDTO(List<Compra> listaCompras) {
        List<CompraDTO> listaDTO = new ArrayList<CompraDTO>();
        for (Compra usuario : listaCompras) {
            CompraDTO DTO = new CompraDTO();

            DTO = convertEntityToDTO(usuario);

            listaDTO.add(DTO);
        }

        return listaDTO;
    }

    public CompraDTO convertEntityToDTO(Compra compra) {
        CompraDTO DTO = new CompraDTO();

        DTO.setIdcompra(compra.getIdcompra());
        DTO.setCorrelativo(compra.getCorrelativo());
        DTO.setFecha(compra.getFecha());
        DTO.setTotal(compra.getTotal());
        DTO.setSerie(compra.getSerie());
        DTO.setIdEmpresa(compra.getIdempresa().getIdempresa());
        DTO.setNombreEmpresa(compra.getIdempresa().getNombre());
        DTO.setIdempresa(compra.getIdempresa());

        DTO.setEstado(compra.getEstado());

        Almacen almacen = new Almacen();
        almacen = almacenFacade.getAlmacenById(compra.getIdalmacen());

        DTO.setIdAlmacen(almacen.getIdalmacen());
        DTO.setNombreAlmacen(almacen.getNombre());

        DTO.setIdcompra(compra.getIdcompra());
        DTO.setCorrelativo(compra.getCorrelativo());
        DTO.setFecha(compra.getFecha());
            //DTO.setIdalmacen(compra.getIdalmacen());
        //DTO.setIdempresa(compra.getIdempresa());
        DTO.setTotal(compra.getTotal());
        DTO.setSerie(compra.getSerie());
        DTO.setEstado(compra.getEstado());

//            String nombreAlmacen = almacenFacade.getAlmacenById(DTO.getIdalmacen()).getNombre() ;
//            DTO.setNombreAlmacen(nombreAlmacen);
        return DTO;
    }

    public Compra convertDTOtoEntity(CompraDTO dto, int tipo) {
        Compra entidad = new Compra();
        if (tipo == 0) {
            entidad.setIdcompra(dto.getIdcompra());
        }
        entidad.setFecha(new Date());
        entidad.setIdempresa(new Empresa(dto.getIdEmpresa()));
        entidad.setIdalmacen(dto.getIdAlmacen());
        entidad.setEstado(dto.getEstado());
        for(LoteDTO l:dto.getListaLoteDTO()){
        dto.setTotal(dto.getTotal()+l.getPrecioUni());
        }
        entidad.setTotal(dto.getTotal());
        return entidad;
    }

    public List<CompraDTO> BuscarCompra(CompraDTO dto) {
        List<CompraDTO> listaDto = this.convertListEntityToDTO(compraFacade.buscarCompra(dto));
        return listaDto;
    }

    public Compra insertarNuevoCompra(CompraDTO dto) {
        dto.setTotal(0d);
        Compra entidad = convertDTOtoEntity(dto, 1);
        Docalmacen da = getNewSerieAndCorrelativo(entidad.getIdalmacen());
        entidad.setSerie(String.format("%03d", da.getSerie()));
        entidad.setCorrelativo(String.format("%06d", da.getCorrelativo()));
        entidad = compraFacade.agregarCompra(entidad);
        for(LoteDTO ll:dto.getListaLoteDTO()){
        Lote e=loteBO.convertDTObyEntity(ll);
        Requerimientos r=requerimientosFacade.agregarRequerimiento(convertRequerimientoDTOByEntidad(ll.getRequerimiento()));
        
        for(PealtipoitemDTO pa:ll.getRequerimiento().getPealtipoitemList()){
            Pealtipoitem pal=pealtipoitemFacade.find(new PealtipoitemPK(pa.getPedido().getIdpedido(), pa.getIdalmacen(), pa.getIdtipoitem()));
            pal.setEstado(1);
            pal.setIdrequerimientos(r);
        pealtipoitemFacade.edit(pal);
        }
        
        e.setIdrequerimientos(r);
        e.setCompra(entidad);
        LotePK lpk=new LotePK();
        lpk.setIdcompra(entidad.getIdcompra());
        e.setLotePK(lpk);
        loteFacade.create(e);
        Altipoitem al=altipoitemFacade.find(new AltipoitemPK(dto.getIdAlmacen(), ll.getIdtipoitem()));
        al.setComprados(al.getComprados()+ll.getCantidad());
        altipoitemFacade.edit(al);
        }
        
        
        return entidad;
    }

    public Docalmacen getNewSerieAndCorrelativo(Integer idalmacen) {
        Docalmacen da = docalmacenFacade.findBy2key(idalmacen, 4);
        da = cotizacionBO.updateDocAlm(da);
        return da;
    }

    public List<PealtipoitemDTO> getPedidosbyAlmacen(CompraDTO c) {
        return converListEntidadBytListPealtiDTO(pealtipoitemFacade.getPedidosByAlmacen(c));
    }

    private List<PealtipoitemDTO> converListEntidadBytListPealtiDTO(List<Pealtipoitem> pedidos) {
        List<PealtipoitemDTO> pat = new ArrayList<>();
        for (Pealtipoitem e : pedidos) {
            pat.add(convertEntidadByPealtipoitemDTO(e));
        }

        return pat;
    }

    private PealtipoitemDTO convertEntidadByPealtipoitemDTO(Pealtipoitem e) {
        PealtipoitemDTO dto = new PealtipoitemDTO();
        dto.setAltipoitem(e.getAltipoitem());
        dto.setCantidad(e.getCantidad());
        dto.setIdalmacen(e.getAltipoitem().getAlmacen().getIdalmacen());
        dto.setEstado(e.getEstado());
//       dto.setIdcompra(e.getRequerimientos().getIdcompra());
        dto.setRequerimientos(e.getIdrequerimientos());
        dto.setIdtipoitem(e.getAltipoitem().getTipoitem().getIdtipoItem());
        dto.setNombreItems(e.getAltipoitem().getTipoitem().getNombre());
        dto.setCostoUni(e.getCostoUni());
        dto.setPedido(e.getPedido());
        return dto;

    }

    public List<Lote> getLoteByCompra(CompraDTO co) {
        return loteFacade.getLotesByCompra(co.getIdcompra());
    }

    public List<PealtipoitemDTO> getPedidosByCompraAndItem(CompraDTO co, Lote loteSelec) {
        List<PealtipoitemDTO> l = converListEntidadBytListPealtiDTO(pealtipoitemFacade.getPedidosByCompraAndItem(co, loteSelec));
        return l;
    }
//   public void insertUpdatePealTipoItem(List<PealtipoitemDTO> listDTO){
//        List<Pealtipoitem> listaEntidad = convertDTOtoEntityAlTipoItem(listDTO);
//        for(Pealtipoitem entidadATI : listaEntidad){
//            altipoitemFacace.edit(entidadATI);
//        }
//    }

    private Requerimientos convertRequerimientoDTOByEntidad(RequerimientoDTO requerimiento) {
    Requerimientos r=new  Requerimientos();
    r.setCantidad(requerimiento.getCantidad());
    return r;
    }

    private Pealtipoitem convertPealtipoitemDTObyEntidad(PealtipoitemDTO dto) {
        Pealtipoitem p=new Pealtipoitem();
        p.setAltipoitem(dto.getAltipoitem());
        p.setCantidad(dto.getCantidad());
        p.setCostoUni(dto.getCostoUni());
        p.setEstado(dto.getEstado());
        p.setPealtipoitemPK(new PealtipoitemPK(dto.getPedido().getIdpedido(), dto.getAltipoitem().getAlmacen().getIdalmacen(), dto.getAltipoitem().getTipoitem().getIdtipoItem()));
    return p;
    }

}
