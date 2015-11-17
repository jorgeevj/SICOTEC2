/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PealtipoitemFacade;
import dao.TipoitemFacade;
import dto.PealtipoitemDTO;
import dto.RequerimientoDTO;
import entidades.Pealtipoitem;
import entidades.PealtipoitemPK;
import entidades.Pedido;
import entidades.Requerimientos;
import entidades.Tipoitem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.*;

/**
 *
 * @author Cesar
 */
@Stateless
@LocalBean
public class PedidoaltipoitemBO {
    @EJB
    private PealtipoitemFacade pealtipoitemFacade;
    
    @EJB
    private TipoitemFacade tipoitemFacade;
    
    public List<PealtipoitemDTO> getAllPealtipoitems(){
        List<PealtipoitemDTO> lista = new ArrayList<PealtipoitemDTO>();
        List<Pealtipoitem> listaEntidad = pealtipoitemFacade.getAllPealtipoitem();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
     public List<PealtipoitemDTO> convertListEntityToDTO(List<Pealtipoitem> listaPealtipoitem){
        List<PealtipoitemDTO> listaDTO = new ArrayList<PealtipoitemDTO>();
         
        for(Pealtipoitem usuario: listaPealtipoitem){
        
            listaDTO.add(convertEntityToDTO(usuario));
        }
        
        return listaDTO;
    }
     public PealtipoitemDTO convertEntityToDTO(Pealtipoitem pealtipoitem){
        PealtipoitemDTO DTO = new PealtipoitemDTO();
        String estado = null;
            DTO.setAltipoitem(pealtipoitem.getAltipoitem());
            DTO.setCantidad(pealtipoitem.getCantidad());
            DTO.setCostoUni(pealtipoitem.getCostoUni());
            DTO.setEstado(pealtipoitem.getEstado());
            DTO.setIdalmacen(pealtipoitem.getAltipoitem().getAlmacen().getIdalmacen());
            DTO.setIdtipoitem(pealtipoitem.getAltipoitem().getTipoitem().getIdtipoItem());
            DTO.setRequerimientos(pealtipoitem.getIdrequerimientos());
            DTO.setPedido(new Pedido(pealtipoitem.getPealtipoitemPK().getIdpedido()));
            DTO.setIdPedido(pealtipoitem.getPealtipoitemPK().getIdpedido());
            String idTipoItem = DTO.getAltipoitem().getAltipoitemPK().getIdtipoItem();
            String nombreItems=tipoitemFacade.getTipoItemsNombre(idTipoItem).getNombre();
            if(pealtipoitem.getEstado() == 0){
                estado = "CREADA";
            } else{
                estado = "EN COMPRA";
            }
            //DTO.setNombreEstado(estado);
            DTO.setTotal(pealtipoitem.getCostoUni()*pealtipoitem.getCantidad());
            System.out.println("nombre: " + nombreItems);
            DTO.setNombreItems(nombreItems);
            /*String aux = (pealtipoitem.getAltipoitem().getAlmacen().getIdalmacen()).toString();
            Tipoitem entidad=tipoitemFacade.getTipoItemsNombre(aux);
             DTO.setNombreItems(entidad.getNombre());*/
             DTO.setCostoUni(pealtipoitem.getCostoUni());
        return DTO;
    }
      public Pealtipoitem convertDTOtoEntity(PealtipoitemDTO dto){
        Pealtipoitem entidad = new Pealtipoitem();
        
        entidad.setAltipoitem(dto.getAltipoitem());
        entidad.setCantidad(dto.getCantidad());
        entidad.setCostoUni(dto.getCostoUni());
        entidad.setEstado(dto.getEstado());
        entidad.setPedido(dto.getPedido());
//        entidad.setIdcompra(dto.getIdcompra());
        entidad.setIdrequerimientos(dto.getRequerimientos());
        
        
        
        return entidad;
    }
     public List<PealtipoitemDTO> AlmacenForPedidos(PealtipoitemDTO t){       
        Pealtipoitem r=convertDTOtoEntity(t);
        List<Pealtipoitem> lista =pealtipoitemFacade.getAlmacenForPedido(r);
        List<PealtipoitemDTO> lista1= convertListEntityToDTO(lista);
        return lista1;      
    }  
      
      public List<PealtipoitemDTO> getTipoItemByAlmacen(PealtipoitemDTO dto){
          Pealtipoitem entidad = convertDTOtoEntity(dto);
          List<Pealtipoitem> listaEntidad = pealtipoitemFacade.getAlmacenForPedido(entidad);
        List<PealtipoitemDTO> listaDTO = convertListEntityToDTO(listaEntidad);
        
        return listaDTO;
        
        
    }
      
      public List<PealtipoitemDTO> getItemsForPedido(PealtipoitemDTO dto){
          Pealtipoitem entidad = convertDTOtoEntity(dto);
          List<Pealtipoitem> listaEntidad = pealtipoitemFacade.getAllItemsByPedido(entidad);
          List<PealtipoitemDTO> listaDTO = convertListEntityToDTO(listaEntidad);
          return listaDTO;
      }
      
//    private PealtipoitemDTO convertEntityToDTO(PealtipoitemDTO pealtipoitem) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
     
      public List<PealtipoitemDTO> getAllItemsByPedido(PealtipoitemDTO dto){
          /*Pealtipoitem entidad = this.convertDTOtoEntity(dto);
          List<Pealtipoitem> listaEntidad = pealtipoitemFacade.getAllPealtipoitemsByPedido(entidad);*/
          List<PealtipoitemDTO> listaDto = null;//this.convertListEntityToDTO(listaEntidad);
          return listaDto;
      }
      
      public List<PealtipoitemDTO> getPealtipoitemByRequerimiento(RequerimientoDTO r){
      return convertListEntityToDTO(pealtipoitemFacade.getPedidosbyRequerimiento(r));
      }
}
