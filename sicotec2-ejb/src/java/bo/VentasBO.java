/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.AltipoitemFacade;
import dao.ItemFacade;
import dao.VemediopagoFacade;
import dao.VentaFacade;
import dto.ItemDTO;
import dto.VeMedioPagoDTO;
import dto.VentaDTO;
import entidades.Altipoitem;
import entidades.Empresa;
import entidades.Impuesto;
import entidades.Item;
import entidades.Vemediopago;
import entidades.VemediopagoPK;
import entidades.Venta;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class VentasBO {
    @EJB
    private VentaFacade ventaFacade;
    @EJB
    private AlmacenFacade almacenFacade;
    @EJB
    private ItemFacade itemFacade;
    @EJB
    private AltipoitemFacade altipoitemFacade;
    @EJB
    private VemediopagoFacade vemediopagoFacade;
    /*@EJB
    private VemediopagoFacade altipoitemFacade;*/
    
    ItemBO item;
    
    public List<VentaDTO> getVentasByEstado(int estado){
        List<Venta> lista          = ventaFacade.getVentasxEstado(estado);
        List<VentaDTO> listaVentas = convertListEntityToDTO(lista);
                
        return listaVentas;
    }
    
    public List<ItemDTO>listaItemsxVenta(int idVenta){
        List<Item> items         = ventaFacade.getItemsxVenta(idVenta);
        List<ItemDTO> listaItems = convertListEntityToDTOItem(items);
        
        return listaItems;
    }
    
    public List<VentaDTO>getVentasByBusqueda(VentaDTO params){
        List<VentaDTO> listaVentas = new ArrayList<VentaDTO>();
        listaVentas = convertListEntityToDTO(ventaFacade.getVentasByBusqueda(params));
        return listaVentas;
    }
    
    public List<VeMedioPagoDTO>getListaVMedioPago(int idVenta){
        List<VeMedioPagoDTO> listavMedioPago = new ArrayList<VeMedioPagoDTO>();
        List<Vemediopago> listaent = ventaFacade.getListavMedioPago(idVenta);
        for(Vemediopago v : listaent){
            VeMedioPagoDTO dto = ConvertDTOtoEntityMedioPago(v);
            listavMedioPago.add(dto);
        }
        return listavMedioPago;
    }
    
    public void editVenta(VentaDTO venta, List<VeMedioPagoDTO> veMedioPago, int estado){
        if(estado == 2){
            //List<Vemediopago> listaEnt = new ArrayList<Vemediopago>();
            for(VeMedioPagoDTO vDTO : veMedioPago){
                if(!vDTO.isDisableDelete()){
                    Vemediopago vem = new Vemediopago();
                    VemediopagoPK pk = new VemediopagoPK();
                    pk.setIdmedioPago(vDTO.getIdMedioPago());
                    pk.setIdventa(vDTO.getIdVenta());
                    vem.setVemediopagoPK(pk);
                    vem.setMonto(vDTO.getMonto());

                    vemediopagoFacade.create(vem);
                }
            }
            Venta ventaE = this.ConvertDTOtoEntity(venta);
            //ventaE.setVemediopagoList(listaEnt);
            ventaFacade.edit(ventaE);
            List<Item> items = ventaFacade.getItemsxVenta(venta.getIdventa());

            for(Item i : items){
                //RESTARLE LA CANTIDAD DE RESERVADO EN ALTIPOITEM
                altipoitemFacade.updateCantidadReservadoAltipoItem(i.getAltipoitem().getTipoitem().getIdtipoItem(), venta.getIdalmacen(), 1, 2);
                
                Venta v = new Venta();
                v.setIdventa(venta.getIdventa());
                //CAMBIAR EL ESTADO AL ITEM
                i.setEstado("2");
                i.setIdventa(ventaE);
                
                itemFacade.edit(i);
            }
            
        }else if(estado == 4){
            Venta ventaE = this.ConvertDTOtoEntity(venta);
            ventaFacade.edit(ventaE);  
        }
    }
    
    public List<VentaDTO> convertListEntityToDTO(List<Venta> listaVentas){
        List<VentaDTO> listaDTO = new ArrayList<VentaDTO>();
        for(Venta movimiento : listaVentas){
            VentaDTO DTO = new VentaDTO();
            
            DTO = convertEntityToDTO(movimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }

    public VentaDTO convertEntityToDTO(Venta venta){
        VentaDTO DTO = new VentaDTO();
            
        DTO.setIdventa(venta.getIdventa());
        DTO.setEstado(venta.getEstado());
        DTO.setFecha(venta.getFecha());
        DTO.setCorrelativo(venta.getCorrelativo());
        DTO.setDescuento(venta.getDescuento());
        DTO.setSerie(venta.getSerie());
        DTO.setTotal(venta.getTotal());
        DTO.setIdEmpresa(venta.getIdempresa().getIdempresa());
        DTO.setNombreEmpresa(venta.getIdempresa().getNombre());
        DTO.setIdalmacen(venta.getIdalmacen());
        DTO.setNombreImpuesto(venta.getIdimpuesto().getNombre());
        DTO.setPorcentajeImpuesto(venta.getIdimpuesto().getProcentaje()+"");
        DTO.setNombreAlmacen(almacenFacade.getAlmacenById(venta.getIdalmacen()).getNombre());
        DTO.setIdUsuario(venta.getIdusuario());
        DTO.setNombreUsuario(venta.getNombreusuario());
        //PUEDE SER QUE SE CAIGA
        DTO.setIdImpuesto(venta.getIdimpuesto().getIdimpuesto());
        
        int estado = Integer.parseInt(venta.getEstado());
        if(estado == 4){
            DTO.setNombreEstado("CANCELADA");
        }else if(estado == 2){
            DTO.setNombreEstado("PAGADA");
        }else if(estado == 5){
            DTO.setNombreEstado("INCOMPLETA");
        }else if(estado == 1){
            DTO.setNombreEstado("PENDIENTE");
        }else if(estado == 3){
            DTO.setNombreEstado("ENTREGADA");
        }

        return DTO;
    }
    
    public List<ItemDTO> convertListEntityToDTOItem(List<Item> listaItem){
        List<ItemDTO> listaDTO = new ArrayList<ItemDTO>();
        for(Item movimiento : listaItem){
            ItemDTO DTO = new ItemDTO();
            
            DTO = convertEntityToDTO(movimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }

    public ItemDTO convertEntityToDTO(Item item){
        ItemDTO DTO = new ItemDTO();
            
        DTO.setIditem(item.getIditem());
        DTO.setEstado(item.getEstado());
        DTO.setOperatividad(item.getOperatividad());
        DTO.setIdLote(item.getLote().getLotePK().getIdlote());
        //PROBAR
        DTO.setIdTipoItem(item.getLote().getAltipoitem().getTipoitem().getIdtipoItem());
        DTO.setDescTipoItem(item.getLote().getAltipoitem().getTipoitem().getDescripcion());
        DTO.setNumParte(item.getLote().getAltipoitem().getTipoitem().getNumParte());
        DTO.setDescColor(item.getLote().getAltipoitem().getTipoitem().getIdcolor().getNombre());
        DTO.setDescFamilia(item.getLote().getAltipoitem().getTipoitem().getIdfamilia().getNombre());
        DTO.setDescMarca(item.getLote().getAltipoitem().getTipoitem().getIdmarca().getNombre());
        return DTO;
    }
    
    public Venta ConvertDTOtoEntity(VentaDTO dto){
        Venta entidad = new Venta();
        Empresa empresa = new Empresa();
        Impuesto impuesto = new Impuesto();
        
        entidad.setIdventa(dto.getIdventa());
        entidad.setFecha(dto.getFecha());
        entidad.setDescuento(dto.getDescuento());
        entidad.setTotal(dto.getTotal());
        
        //EMPRESA
        empresa.setIdempresa(dto.getIdEmpresa());
        entidad.setIdempresa(empresa);
        
        //IMPUESTO
        impuesto.setIdimpuesto(dto.getIdImpuesto());
        entidad.setIdimpuesto(impuesto);
        
        entidad.setEstado(dto.getEstado());
        entidad.setIddocumento(dto.getIddocumento());
        entidad.setSerie(dto.getSerie());
        entidad.setCorrelativo(dto.getCorrelativo());
        entidad.setIdalmacen(dto.getIdalmacen());
        entidad.setIdusuario(dto.getIdUsuario());
        entidad.setNombreusuario(dto.getNombreUsuario());
        
        return entidad;
    }
    
    public VeMedioPagoDTO ConvertDTOtoEntityMedioPago(Vemediopago v){
        VeMedioPagoDTO vm = new VeMedioPagoDTO();
        
        vm.setIdVenta(v.getVenta().getIdventa());
        vm.setIdMedioPago(v.getMediopago().getIdmedioPago());
        vm.setMonto(v.getMonto());
        vm.setNombreMedioPago(v.getMediopago().getNombre());
        vm.setDisableDelete(true);
        
        return vm;
    }
    
}
