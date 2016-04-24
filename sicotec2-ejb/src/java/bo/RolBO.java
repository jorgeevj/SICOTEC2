/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.RolFacade;
import dto.RolDTO;
import entidades.Rol;
import java.util.ArrayList;
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
public class RolBO {
    
    @EJB
    private RolFacade rolFacade;
    
    
    public List<RolDTO> getAllRoles() {
        List<RolDTO> lista = new ArrayList<RolDTO>();
        List<Rol> listaEntidad = rolFacade.findAll();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<RolDTO> convertListEntityToDTO(List<Rol> listaRoles){
        List<RolDTO> listaDTO = new ArrayList<RolDTO>();
        for(Rol rol : listaRoles){
            RolDTO DTO = new RolDTO();
            
            DTO = convertEntityToDTO(rol);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
   
    public RolDTO convertEntityToDTO(Rol rol){
        RolDTO DTO = new RolDTO();
        
        DTO.setIdrol(rol.getIdrol());
        DTO.setNombre(rol.getNombre());
        DTO.setEstado(rol.getEstado());
                 
       return DTO;
    }
}
