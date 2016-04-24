/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.PermisoFacade;
import dto.PermisoDTO;
import entidades.Permiso;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class PermisoBO {
    
    @EJB
    private PermisoFacade permisoFacade;
    
    public List<PermisoDTO> getPermisosByRol(int idRol){
        List<PermisoDTO> permisos = new ArrayList<PermisoDTO>();
        
        List<Permiso> permisosEnt = permisoFacade.permisosByRol(idRol);
        permisos = this.convertListEntityToDTO(permisosEnt);
        
        return permisos;
    }
    
    public List<PermisoDTO> convertListEntityToDTO(List<Permiso> listaUsuarios){
        List<PermisoDTO> listaDTO = new ArrayList<PermisoDTO>();
        for(Permiso permiso : listaUsuarios){
            PermisoDTO DTO = new PermisoDTO();
            
            DTO = convertEntityToDTO(permiso);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public PermisoDTO convertEntityToDTO(Permiso permiso){
        PermisoDTO DTO = new PermisoDTO();
            
        DTO.setIdpermiso(permiso.getIdpermiso());
        DTO.setNombre(permiso.getNombre());
        DTO.setUrl(permiso.getUrl());

        return DTO;
    }
}
