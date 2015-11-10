/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AbstractFacade;
import dao.UsuarioFacade;
import dto.UsuarioDTO;
import entidades.Persona;
import entidades.Rol;
import entidades.Usuario;
import java.util.ArrayList;
import java.util.Date;
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
        DTO.setIdrol(usuario.getIdrol());
        DTO.setIdRol(usuario.getIdrol().getIdrol());
        DTO.setFecha(usuario.getFecha());
        DTO.setIdPersona(usuario.getIdpersona().getIdpersona());
        
        return DTO;
    }
    //0 = UPDATE, 1 = INSERT
    public Usuario convertDTOtoEntity(UsuarioDTO dto, int tipo){
        Usuario entidad = new Usuario();
        if(tipo == 0){
            entidad.setIdusuario(dto.getIdusuario());
            
        }
            entidad.setFecha(new Date());
            entidad.setNombre(dto.getNombre());
            entidad.setClave(dto.getClave());
            
            Persona entidadPersona = new Persona();
            entidadPersona.setIdpersona(dto.getIdPersona());
            entidad.setIdpersona(entidadPersona);
            
            Rol entidadRol = new Rol();
            entidadRol.setIdrol(dto.getIdRol());
            entidad.setIdrol(entidadRol);
            
        return entidad;
    }
    
    public List<UsuarioDTO> BuscarUsuario(UsuarioDTO dto) {
       List<UsuarioDTO> listaDto = this.convertListEntityToDTO(usuarioFacade.buscarUsuario(dto));
        return listaDto;
    }
    
    public Usuario insertarNuevoUsuario(UsuarioDTO dto ){
        Usuario entidad = convertDTOtoEntity(dto , 1);
        entidad = usuarioFacade.agregarUsuario(entidad);
        
        return entidad;
    }
    public void actualizarUsuario(UsuarioDTO dto){
        Usuario entidad = convertDTOtoEntity(dto, 0);
        usuarioFacade.edit(entidad);
    }
}
