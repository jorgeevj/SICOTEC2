/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.VentaFacade;
import dto.ItemDTO;
import dto.VentaDTO;
import entidades.Item;
import entidades.Venta;
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
    
    ItemBO item;
    
    public List<VentaDTO> getVentasByEstado(int estado){
        List<Venta> lista = ventaFacade.getVentasxEstado(estado);
        List<VentaDTO> listaVentas = convertListEntityToDTO(lista);
                
        return listaVentas;
    }
    
    public List<ItemDTO>listaItemsxVenta(int idVenta){
        List<Item> items = ventaFacade.getItemsxVenta(idVenta);
        List<ItemDTO> listaItems = convertListEntityToDTOItem(items);
        
        return listaItems;
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
        DTO.setIdLote(item.getIdlote().getIdlote());

        return DTO;
    }
    
}
