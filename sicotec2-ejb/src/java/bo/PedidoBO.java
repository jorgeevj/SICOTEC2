/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

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
        pedidoDTO.setEmpresa(pedido.getIdempresa());
        pedidoDTO.setIdEmpresa(pedido.getIdempresa().getIdempresa());
        pedidoDTO.setNombreEmpresa(pedido.getIdempresa().getNombre());
        pedidoDTO.setFecha(pedido.getFecha());
        return pedidoDTO;
    }
}
