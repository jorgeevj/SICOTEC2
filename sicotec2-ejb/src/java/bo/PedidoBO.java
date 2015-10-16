/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.PedidoFacade;
import dto.PedidoDTO;
import entidades.Pedido;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Cesar
 */
@Stateless
@LocalBean
public class PedidoBO {

    @EJB
    private PedidoFacade pedidoFacade = new PedidoFacade();
    @EJB
    private AlmacenFacade almacenFacade = new AlmacenFacade();

    public List<PedidoDTO> getAllPedido() {
        List<Pedido> listEntidad = pedidoFacade.findAll();
        List<PedidoDTO> listDTO = new ArrayList<PedidoDTO>();
        listDTO = this.convertEntityToDTOList(listEntidad);
        return listDTO;
    }

    public List<PedidoDTO> convertEntityToDTOList(List<Pedido> listaPedido) {
        List<PedidoDTO> listDTO = new ArrayList<PedidoDTO>();
        for (Pedido pedido : listaPedido) {
            PedidoDTO DTO = new PedidoDTO();
            DTO = converEntityToDTO(pedido);

            listDTO.add(DTO);
        }

        return listDTO;
    }

    public PedidoDTO converEntityToDTO(Pedido pedido) {
        PedidoDTO pedidoDTO = new PedidoDTO();
        
        pedidoDTO.setIdpedido(pedido.getIdpedido());
        pedidoDTO.setFecha(pedido.getFecha());
        pedidoDTO.setIdEmpresa(pedido.getIdempresa());
            pedidoDTO.setEmpresaId(pedido.getIdempresa().getIdempresa());
            pedidoDTO.setNombreEmpresa(pedido.getIdempresa().getNombre());
        pedidoDTO.setSerie(pedido.getSerie());
        pedidoDTO.setCorrelativo(pedido.getSerie());
        pedidoDTO.setIdalmacen(pedido.getIdalmacen());
        String nombreAlmacen = almacenFacade.getAlmacenById(pedidoDTO.getIdalmacen()).getNombre() ;
        pedidoDTO.setNombreAlmacen(nombreAlmacen);
        return pedidoDTO;
    }
    //0 UPDATE , 1 INSERT
    public Pedido convertDTOtoEntity(PedidoDTO dto, int tipo){
        Pedido entidad = new Pedido();
        if(tipo == 0){
            entidad.setIdpedido(dto.getIdpedido());
        }
        entidad.setIdpedido(dto.getIdpedido());
        entidad.setFecha(dto.getFecha());
        entidad.setIdempresa(dto.getIdEmpresa());
        entidad.setSerie(dto.getSerie());
        entidad.setCorrelativo(dto.getCorrelativo());
        entidad.setIdalmacen(dto.getIdalmacen());
        return entidad;
    }
    
    public List<PedidoDTO> getPedidosByFiltro(PedidoDTO dto){
        List<PedidoDTO> listaDTO = this.convertEntityToDTOList(pedidoFacade.getPedidoFiltro(dto));
        return listaDTO;
    }
    
    public void insertarNuevoPedido(PedidoDTO dto){
        Pedido entidad = convertDTOtoEntity(dto , 0);
        pedidoFacade.create(entidad);
    }
}
