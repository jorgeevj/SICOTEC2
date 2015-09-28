/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AbstractFacade;
import dao.UsuarioFacade;
import dto.UsuarioDTO;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Jorge
 * 15.09.2015
 */
@Stateless
@LocalBean
public class UsuarioBO {
    
    @EJB
    private UsuarioFacade usuarioFacade = new UsuarioFacade();
    
    public UsuarioDTO validateLogin(UsuarioDTO usuario){
        UsuarioDTO u = new UsuarioDTO();
        
        Usuario us = usuarioFacade.validateLogin(usuario);
        if(us.getIdusuario() != null){
            u = convertEntityToDTO(us);
        }
        
        return u;
    }
    
    public List<UsuarioDTO> getAllUsuarios() {
        List<Usuario> listaUsuarios       = usuarioFacade.findAll();
        List<UsuarioDTO> listaUsuariosDTO = convertListEntityToDTO(listaUsuarios);
        
        return listaUsuariosDTO;
    }
    
    public List<UsuarioDTO> convertListEntityToDTO(List<Usuario> listaUsuarios){
        List<UsuarioDTO> listaDTO = new ArrayList<UsuarioDTO>();
        for(Usuario usuario : listaUsuarios){
            UsuarioDTO DTO = new UsuarioDTO();
            
            DTO = convertEntityToDTO(usuario);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public UsuarioDTO convertEntityToDTO(Usuario usuario){
        UsuarioDTO DTO = new UsuarioDTO();
            
        DTO.setIdusuario(usuario.getIdusuario());
        DTO.setIdpersona(usuario.getIdpersona());
        DTO.setNombre(usuario.getNombre());
        DTO.setClave(usuario.getClave());
        DTO.setIdRol(usuario.getIdrol().getIdrol());

        return DTO;
    }
}
