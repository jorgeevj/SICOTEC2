/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.CompraFacade;
import dao.DocalmacenFacade;
import dao.LoteFacade;
import dao.PealtipoitemFacade;
import dao.TipoitemFacade;
import dto.CompraDTO;
import dto.PealtipoitemDTO;
import entidades.Almacen;
import entidades.Compra;
import entidades.Docalmacen;
import entidades.Empresa;
import entidades.Lote;
import entidades.Pealtipoitem;
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
    private LoteFacade loteFacade;
    @EJB
    private CotizacionBO cotizacionBO;
    @EJB
    private DocalmacenFacade docalmacenFacade;
  
    @EJB
    private CompraFacade compraFacade ;
    @EJB
    private AlmacenFacade almacenFacade = new AlmacenFacade();
     @EJB
    private PealtipoitemFacade pealtipoitemFacade=new PealtipoitemFacade(); 
    

    
    public List<CompraDTO> getAllCompras() {
        List<Compra> listaCompras       = compraFacade.findAll();
        List<CompraDTO> listaComprasDTO = convertListEntityToDTO(listaCompras);
        
        return listaComprasDTO;
    }
    
    public List<CompraDTO> getComprasByEstado(int estado){
        List<Compra> listaCompras       = compraFacade.getComprasxEstado(estado);
        List<CompraDTO> listaComprasDTO = convertListEntityToDTO(listaCompras);
        
        return listaComprasDTO;
    }
    
    public List<CompraDTO> convertListEntityToDTO(List<Compra> listaCompras){
        List<CompraDTO> listaDTO = new ArrayList<CompraDTO>();
        for(Compra usuario : listaCompras){
            CompraDTO DTO = new CompraDTO();
            
            DTO = convertEntityToDTO(usuario);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public CompraDTO convertEntityToDTO(Compra compra){
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
        //DTO.setNombreAlmacen(almacen.getNombre());

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
    public Compra convertDTOtoEntity(CompraDTO dto, int tipo){
        Compra entidad = new Compra();
        if(tipo == 0){
            entidad.setIdcompra(dto.getIdcompra());
        }
        entidad.setFecha(new Date());
        //entidad.setIdempresa(dto.getIdempresa());
        //entidad.setIdalmacen(dto.getIdalmacen());
            Empresa entidadEmpresa = new Empresa();
            entidadEmpresa.setIdempresa(dto.getIdEmpresa());
        entidad.setIdempresa(entidadEmpresa);
        entidad.setIdalmacen(dto.getIdAlmacen());
//        entidad.setSerie(dto.getSerie());
//        entidad.setCorrelativo(dto.getCorrelativo());
        entidad.setEstado(dto.getEstado());
        entidad.setTotal(dto.getTotal());
//        entidad.setIdempresa(dto.getIdempresa());
       
      
        return entidad;
    }
    public List<CompraDTO> BuscarCompra(CompraDTO dto) {
       List<CompraDTO> listaDto = this.convertListEntityToDTO(compraFacade.buscarCompra(dto));
        return listaDto;
    }
    
    public Compra insertarNuevoCompra(CompraDTO dto ){
        Compra entidad = convertDTOtoEntity(dto , 1);
        Docalmacen da=getNewSerieAndCorrelativo(entidad.getIdalmacen());
        entidad.setSerie(String.format("%03d", da.getSerie()));
        entidad.setCorrelativo(String.format("%06d", da.getCorrelativo()));
        entidad = compraFacade.agregarCompra(entidad);
        return entidad;
    }
   public Docalmacen getNewSerieAndCorrelativo(Integer idalmacen){
   Docalmacen da = docalmacenFacade.findBy2key(idalmacen, 4);
        da = cotizacionBO.updateDocAlm(da);
       return da;
   }
   
   public List<PealtipoitemDTO> getPedidosbyAlmacen(CompraDTO c){
   return converListEntidadBytListPealtiDTO(pealtipoitemFacade.getPedidosByAlmacen(c)) ;
   }

    private List<PealtipoitemDTO> converListEntidadBytListPealtiDTO(List<Pealtipoitem> pedidos) {
        List<PealtipoitemDTO> pat=new ArrayList<>();
        for(Pealtipoitem e:pedidos){
        pat.add(convertEntidadByPealtipoitemDTO(e));
        }
        
        return pat;
                }

    private PealtipoitemDTO convertEntidadByPealtipoitemDTO(Pealtipoitem e) {
       PealtipoitemDTO dto=new PealtipoitemDTO();
       dto.setAltipoitem(e.getAltipoitem());
       dto.setCantidad(e.getCantidad());
       dto.setIdalmacen(e.getAltipoitem().getAlmacen().getIdalmacen());
       dto.setEstado(e.getEstado());
//       dto.setIdcompra(e.getRequerimientos().getIdcompra());
       dto.setRequerimientos(e.getIdrequerimientos());
       dto.setNombreItems(e.getAltipoitem().getTipoitem().getNombre());
       dto.setCostoUni(e.getCostoUni());
       dto.setPedido(e.getPedido());
       return dto;
    
    }

    public List<Lote> getLoteByCompra(CompraDTO co) {
       return loteFacade.getLotesByCompra(co.getIdcompra());
    }

    public List<PealtipoitemDTO> getPedidosByCompraAndItem(CompraDTO co, Lote loteSelec) {
        List<PealtipoitemDTO> l=converListEntidadBytListPealtiDTO(pealtipoitemFacade.getPedidosByCompraAndItem(co,loteSelec));
        return l;
    }
   
   
}
