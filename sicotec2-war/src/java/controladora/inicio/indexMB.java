/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.inicio;

import bo.PermisoBO;
import bo.UsuarioBO;
import dto.PermisoDTO;
import dto.UsuarioDTO;
import entidades.Permiso;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class indexMB {
    @EJB
    private PermisoBO permisoBO;
    @EJB
    private UsuarioBO usuarioBO;

    private UsuarioDTO usuario;
    /**
     * Creates a new instance of indexMB
     */
    public indexMB() {
    }

    @PostConstruct
    public void init() {
        usuario=new UsuarioDTO();
        permisos=new ArrayList<>();
    }
    private List<PermisoDTO> permisos;

    public void validarUsuario() {
        
        if(usuario.getNombre().isEmpty()||usuario.getNombre()==null){
            mostrarMensaje("Debes ingresar un nombre de usuario");
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formsesion:messagesSession");
            return;
        }
        if(usuario.getClave().isEmpty()||usuario.getClave()==null){
            mostrarMensaje("Debes ingresar una Clave");
            RequestContext context = RequestContext.getCurrentInstance();
                context.update("formsesion:messagesSession");
            return;
        }
        if(usuarioBO.validateLogin(usuario).getNombre()==null){
            mostrarMensaje("El usuario no existe");
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formsesion:messagesSession");
            return;
        }
        try {
            usuario=usuarioBO.validateLogin(usuario);
            permisos=permisoBO.getPermisosByRol(usuario.getIdRol());
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/PrincipalJSF.xhtml");
            
        } catch (IOException ex) {
            Logger.getLogger(indexMB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void verificaSession(){
        usuario=usuarioBO.validateLogin(usuario);
      if(usuario.getNombre().equals("")){
            mostrarMensaje("no ha iniciado sesion");
            
          try {
              FacesContext.getCurrentInstance().getExternalContext().redirect("faces/index.xhtml");
              RequestContext context = RequestContext.getCurrentInstance();
                context.update("msgBienvenida");
          } catch (IOException ex) {
              Logger.getLogger(indexMB.class.getName()).log(Level.SEVERE, null, ex);
          }
        }    
            mostrarMensaje(usuario.getIdrol().getNombre()+": " + usuario.getNombre());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("msgBienvenida");
            
    }
    
    public void mostrarMensaje(String msg){
    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, ""));
    }
    
    public void salir(){
        usuario=new UsuarioDTO();
    RequestContext context = RequestContext.getCurrentInstance();
    context.update("formsesion");
    }
    

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<PermisoDTO> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<PermisoDTO> permisos) {
        this.permisos = permisos;
    }

}
