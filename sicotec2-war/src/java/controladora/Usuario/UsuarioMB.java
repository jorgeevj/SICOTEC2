/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Usuario;

import bo.RolBO;
import bo.UsuarioBO;
import dto.PersonaDTO;
import dto.RolDTO;
import dto.UsuarioDTO;
import entidades.Persona;
import entidades.Rol;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author jc7
 */
@ManagedBean
@SessionScoped
public class UsuarioMB implements Serializable{
    @EJB
    private UsuarioBO usuarioBO = new UsuarioBO();
    @EJB
    private RolBO rolBO = new RolBO();
    
    
    //LISTAS
    private List<UsuarioDTO> listaUsuario = new ArrayList<UsuarioDTO>();
    private List<RolDTO> listaRol = new ArrayList<RolDTO>();
    
    //BUSQUEDA   
    private int selectEstadoBusqueda = 100;
    private String nombreUsuarioBusqueda;
    private String nombrePersonaBusqueda;
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    private int idRolSelectBusqueda;
        
      
    
    
    @PostConstruct
    public void init(){
        setListaUsuario(getUsuarioBO().getAllUsuarios());
        setListaRol(getRolBO().getAllRoles());
           
    }
     public List<UsuarioDTO> consultar(ActionEvent actionEvent) {
       
         UsuarioDTO dto = new UsuarioDTO();
         Persona entidadPersona = new Persona();
         entidadPersona.setNombre(getNombrePersonaBusqueda());
         
        Rol entidadRol = new Rol();
        entidadRol.setIdrol(getIdRolSelectBusqueda());
       
             
               
            dto.setIdpersona(entidadPersona);
            dto.setIdrol(entidadRol);
            
            Date fechaInicio = getFechaInicioBusqueda();
            Date fechaFin = getFechaFinBusqueda();
            String nombreUsuario   = getNombreUsuarioBusqueda();
            dto.setFechaFin(fechaFin);
            dto.setFechaInicio(fechaInicio);
            dto.setNombre(nombreUsuario);
            

         setListaUsuario(getUsuarioBO().BuscarUsuario(dto));

         return listaUsuario;   
    }
     public void limpiar(){
        
         setNombrePersonaBusqueda(null);
         setNombreUsuarioBusqueda(null);
         setFechaInicioBusqueda(null);
         setFechaFinBusqueda(null);
         
        
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formUsuario");
    }

    public UsuarioBO getUsuarioBO() {
        return usuarioBO;
    }

    public void setUsuarioBO(UsuarioBO usuarioBO) {
        this.usuarioBO = usuarioBO;
    }

    public List<UsuarioDTO> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<UsuarioDTO> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public int getSelectEstadoBusqueda() {
        return selectEstadoBusqueda;
    }

    public void setSelectEstadoBusqueda(int selectEstadoBusqueda) {
        this.selectEstadoBusqueda = selectEstadoBusqueda;
    }

    public String getNombreUsuarioBusqueda() {
        return nombreUsuarioBusqueda;
    }

    public void setNombreUsuarioBusqueda(String nombreUsuarioBusqueda) {
        this.nombreUsuarioBusqueda = nombreUsuarioBusqueda;
    }

    public String getNombrePersonaBusqueda() {
        return nombrePersonaBusqueda;
    }

    public void setNombrePersonaBusqueda(String nombrePersonaBusqueda) {
        this.nombrePersonaBusqueda = nombrePersonaBusqueda;
    }

    public Date getFechaInicioBusqueda() {
        return fechaInicioBusqueda;
    }

    public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
        this.fechaInicioBusqueda = fechaInicioBusqueda;
    }

    public Date getFechaFinBusqueda() {
        return fechaFinBusqueda;
    }

    public void setFechaFinBusqueda(Date fechaFinBusqueda) {
        this.fechaFinBusqueda = fechaFinBusqueda;
    }

    public List<RolDTO> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<RolDTO> listaRol) {
        this.listaRol = listaRol;
    }

    public RolBO getRolBO() {
        return rolBO;
    }

    public void setRolBO(RolBO rolBO) {
        this.rolBO = rolBO;
    }

    public int getIdRolSelectBusqueda() {
        return idRolSelectBusqueda;
    }

    public void setIdRolSelectBusqueda(int idRolSelectBusqueda) {
        this.idRolSelectBusqueda = idRolSelectBusqueda;
    }
    
    
    
}
