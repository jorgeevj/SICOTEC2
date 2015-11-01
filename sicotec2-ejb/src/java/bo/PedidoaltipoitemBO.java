/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PealtipoitemFacade;
import dao.TipoitemFacade;
import dto.PealtipoitemDTO;
import entidades.Pealtipoitem;
import entidades.PealtipoitemPK;
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
            PealtipoitemDTO DTO = new PealtipoitemDTO();
            
            DTO = convertEntityToDTO(usuario);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
     public PealtipoitemDTO convertEntityToDTO(Pealtipoitem pealtipoitem){
        PealtipoitemDTO DTO = new PealtipoitemDTO();
            
            DTO.setAltipoitem(pealtipoitem.getRequerimientos().getAltipoitem());
            DTO.setCantidad(pealtipoitem.getCantidad());
            DTO.setCostoUni(pealtipoitem.getCostoUni());
            DTO.setEstado(pealtipoitem.getEstado());
            DTO.setIdcompra(pealtipoitem.getRequerimientos().getIdcompra());
            DTO.setPedido(pealtipoitem.getPedido());
            String idTipoItem = DTO.getAltipoitem().getAltipoitemPK().getIdtipoItem();
            System.out.println("dto.nombreItems: " + idTipoItem);
            String nombreItems=tipoitemFacade.getTipoItemsNombre(idTipoItem).getNombre();
            
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
        
        entidad.getRequerimientos().setAltipoitem(dto.getAltipoitem());
        entidad.setCantidad(dto.getCantidad());
        entidad.setCostoUni(dto.getCostoUni());
        entidad.setEstado(dto.getEstado());
        entidad.setPedido(dto.getPedido());
        entidad.getRequerimientos().setIdcompra(dto.getIdcompra());
        
        
        
        return entidad;
    }
     public List<PealtipoitemDTO> AlmacenForPedidos(PealtipoitemDTO t){       
        //Pealtipoitem r=convertDTOtoEntity(t);
        List<Pealtipoitem> lista =pealtipoitemFacade.getAlmacenForPedido(new Pealtipoitem());
        List<PealtipoitemDTO> lista1= new ArrayList<PealtipoitemDTO>();/*convertListEntityToDTO(lista);*/
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
     
}
