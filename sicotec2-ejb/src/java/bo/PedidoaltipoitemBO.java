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
            
            DTO.setAltipoitem(pealtipoitem.getAltipoitem());
            DTO.setCantidad(pealtipoitem.getCantidad());
            DTO.setCostoUni(pealtipoitem.getCostoUni());
            DTO.setEstado(pealtipoitem.getEstado());
            DTO.setIdcompra(pealtipoitem.getIdcompra());
            DTO.setPedido(pealtipoitem.getPedido());
            String nombreItems=tipoitemFacade.getTipoItemsNombre(DTO.getNombreItems()).getIdtipoItem();
            DTO.setNombreItems(nombreItems);
          
        return DTO;
    }
      
//    private PealtipoitemDTO convertEntityToDTO(PealtipoitemDTO pealtipoitem) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
     
}
