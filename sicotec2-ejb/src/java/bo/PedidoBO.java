/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.AltipoitemFacade;
import dao.PealtipoitemFacade;
import dao.PedidoFacade;
import dto.AltipoitemDTO;
import dto.PealtipoitemDTO;
import dto.PedidoDTO;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Pealtipoitem;
import entidades.PealtipoitemPK;
import entidades.Pedido;
import entidades.Tipoitem;
import java.util.ArrayList;
import java.util.Date;
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
    @EJB
    private PealtipoitemFacade pealtipoitemFacade = new PealtipoitemFacade();
    @EJB
    private AltipoitemFacade altipoitemFacace = new AltipoitemFacade();

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
        pedidoDTO.setCorrelativo(pedido.getCorrelativo());
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
        entidad.setFecha(new Date());
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
    
    public Pedido insertarNuevoPedido(PedidoDTO dto ){
        Pedido entidad = convertDTOtoEntity(dto , 1);
        entidad = pedidoFacade.agregarPedido(entidad);
        return entidad;
    }
    
    public void insertarPedidoTipoItem(List<PealtipoitemDTO> listDTO){
        List<Pealtipoitem> listaEntidad = convertDTOtoEntityPealTipoItem(listDTO);
        
        for(Pealtipoitem entidadPTI : listaEntidad){
            pealtipoitemFacade.create(entidadPTI);
        }
    }
    
    public void insertUpdateAlTipoItem(List<AltipoitemDTO> listDTO){
        List<Altipoitem> listaEntidad = convertDTOtoEntityAlTipoItem(listDTO);
        for(Altipoitem entidadATI : listaEntidad){
            altipoitemFacace.edit(entidadATI);
        }
    }
    
    public List<Altipoitem> convertDTOtoEntityAlTipoItem(List<AltipoitemDTO> listaDTO){
        List<Altipoitem> listaEntidad = new ArrayList<Altipoitem>();
        for(AltipoitemDTO dto : listaDTO){
            
            Altipoitem entidadATI = altipoitemFacace.getAllByTipoItemIdAlmacen(this.converDTOtoEntityAltipoitemAux(dto));
            Altipoitem entidad = new Altipoitem();
            if(entidadATI == null){
                entidad.setAlmacen(dto.getAlmacen());
                entidad.setCantidad(dto.getCantidad());
                entidad.setAltipoitemPK(new AltipoitemPK(dto.getAlmacen().getIdalmacen(), dto.getTipoitem().getIdtipoItem()));
                entidad.setComprados(dto.getComprados());
                entidad.setEstado(dto.getEstado());
                entidad.setReservado(dto.getReservado());
                entidad.setTipoitem(dto.getTipoitem());
                listaEntidad.add(entidad);
            } else{
                entidad.setAlmacen(dto.getAlmacen());
                Integer lastCantidad = entidadATI.getCantidad();
                entidad.setCantidad(lastCantidad);
                entidad.setAltipoitemPK(new AltipoitemPK(dto.getAlmacen().getIdalmacen(), dto.getTipoitem().getIdtipoItem()));
                entidad.setComprados(entidadATI.getComprados());
                entidad.setEstado(entidadATI.getEstado());
                Integer lastReserva = entidadATI.getReservado();
                entidad.setReservado(dto.getReservado()+lastReserva);
                entidad.setTipoitem(dto.getTipoitem());
                listaEntidad.add(entidad);
            }
            
        }
        return listaEntidad;
    }
    
    public Altipoitem converDTOtoEntityAltipoitemAux(AltipoitemDTO dto){
        Altipoitem entidad = new Altipoitem();
            Almacen entidadAlmacen = new Almacen();
            entidadAlmacen.setIdalmacen(dto.getAlmacen().getIdalmacen());
        entidad.setAlmacen(entidadAlmacen);
            Tipoitem entidadTipoItem = new Tipoitem();
            entidadTipoItem.setIdtipoItem(dto.getTipoitem().getIdtipoItem());
        entidad.setTipoitem(entidadTipoItem);
        return entidad;
    }
    
    public void actualizarPedido(PedidoDTO dto){
        Pedido entidad = convertDTOtoEntity(dto, 0);
        pedidoFacade.edit(entidad);
    }
    
    public List<Pealtipoitem> convertDTOtoEntityPealTipoItem(List<PealtipoitemDTO> listDTO){
        List<Pealtipoitem> listaEntidad = new ArrayList<Pealtipoitem>();
        for(PealtipoitemDTO dto : listDTO){
            Pealtipoitem entidad = new Pealtipoitem();
            entidad.setPealtipoitemPK(new PealtipoitemPK(dto.getPedido().getIdpedido(), dto.getAltipoitem().getAlmacen().getIdalmacen(), dto.getAltipoitem().getTipoitem().getIdtipoItem()));
            entidad.setPedido(dto.getPedido());
            entidad.setCantidad(dto.getCantidad());
            entidad.setCostoUni(dto.getCostoUni());
            entidad.setEstado(dto.getEstado());
            entidad.setAltipoitem(dto.getAltipoitem());
            listaEntidad.add(entidad);
        }
        return listaEntidad;
    }
}
