/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Usuario;

import bo.PersonaBO;
import bo.RolBO;
import bo.UsuarioBO;
import dto.PersonaDTO;
import dto.RolDTO;
import dto.UsuarioDTO;
import entidades.Persona;
import entidades.Rol;
import entidades.Usuario;
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
import org.primefaces.event.SelectEvent;

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
    @EJB
    private PersonaBO personaBO = new PersonaBO();
    
    
    //LISTAS
    private List<UsuarioDTO> listaUsuario = new ArrayList<UsuarioDTO>();
    private List<RolDTO> listaRol = new ArrayList<RolDTO>();
    private List<RolDTO> listaRolAdd = new ArrayList<RolDTO>();
    private List<PersonaDTO> listaPersonaAdd = new ArrayList<PersonaDTO>();
    private List<RolDTO> listaRolEdit = new ArrayList<RolDTO>();
    private List<PersonaDTO> listaPersonaEdit = new ArrayList<PersonaDTO>();
    
    //BUSQUEDA   
    private int selectEstadoBusqueda = 100;
    private String nombreUsuarioBusqueda;
    private String nombrePersonaBusqueda;
    private String apellidoPersonaBusqueda;
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    private int idRolSelectBusqueda;
    
    //AGREGAR
     private Integer idusuarioNuevo;   
     private Date fechaNuevo;
     private String nombreNuevo;
     private String claveNuevo;
     private int idpersonaNuevo;
     private int idrolNuevo;
     private PersonaDTO personaTablaSelect;
     private boolean btnRegistrarEstado;
     
    //EDITAR
     private UsuarioDTO objUsuarioEditar;
     private boolean disableEditar = true;    
     private Integer usuarioEdit;
     private Integer rolesEdit;
     private Integer personasEdit;
     private String nombreEdit;
     private String claveEdit;
     private Date fechaEdit;
     private Integer idUsuarioEdit;
      
    
    
    @PostConstruct
    public void init(){
        setListaUsuario(getUsuarioBO().getAllUsuarios());
        setListaRol(getRolBO().getAllRoles());
        setListaRolAdd(this.comboRoles());
        setListaPersonaAdd(this.comboPersonas());
        setDisableEditar(true);
        setBtnRegistrarEstado(true);
    }
    
    public void onRowSelectPersona(){
        btnRegistrarEstado=false;
        
    }
     public List<UsuarioDTO> consultar(ActionEvent actionEvent) {
       
         UsuarioDTO dto = new UsuarioDTO();
         Persona entidadPersona = new Persona();
         entidadPersona.setApellido(getApellidoPersonaBusqueda());
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

         return getListaUsuario();
    }
     public void limpiar(){
        
         setNombrePersonaBusqueda(null);
         setNombreUsuarioBusqueda(null);
         setApellidoPersonaBusqueda(null);
         setFechaInicioBusqueda(null);
         setFechaFinBusqueda(null);
         setIdRolSelectBusqueda(100);
        //disableEditar = true;
        
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.update("formUsuario");
    }
    public void addNuevoUsuario(ActionEvent actionEvent){
          UsuarioDTO dto=new UsuarioDTO();
          Persona entidadPers = new Persona();
          entidadPers.setIdpersona(personaTablaSelect.getIdpersona());
           dto.setIdpersona(entidadPers);
           
           Rol entidadRol=new Rol();
           entidadRol.setIdrol(getIdrolNuevo());
           dto.setIdrol(entidadRol);
           
           //dto.setIdRol(getIdrolNuevo());
           dto.setClave(getClaveNuevo());
           dto.setNombre(getNombreNuevo());
           dto.setFecha(new Date());

           Usuario entidad = usuarioBO.insertarNuevoUsuario(dto);
           
           setListaUsuario(usuarioBO.getAllUsuarios());
          
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("formUsuario");
        this.cerrar();
        setPersonaTablaSelect(null);
        
    }
    public void crear(ActionEvent actionEvent){
       setDisableEditar(true);
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addUsuariosModal').show();");
    }
    
    public void cerrar(){
        limpiaCrearUsuario();
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.execute("PF('addUsuariosModal').hide();");
    }
    private void limpiaCrearUsuario() {
        setClaveNuevo(null);
        setNombreNuevo(null);
        setIdpersonaNuevo(0);
        setIdrolNuevo(0);
    }
    public List<RolDTO> comboRoles(){
         List<RolDTO> listaDto = rolBO.getAllRoles();
         return listaDto;
     }
     public List<PersonaDTO> comboPersonas(){
         List<PersonaDTO> listaDto = personaBO.getAllPresonasDTO();
         return listaDto;
     }
     public void selectUsuarioEditar(SelectEvent event) {
        setDisableEditar(false);
        setObjUsuarioEditar((UsuarioDTO)event.getObject()); 
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("formUsuario");
    }
     public void Edit(ActionEvent actionEvent){
         setIdUsuarioEdit(objUsuarioEditar.getIdusuario());
         setListaPersonaEdit(this.comboPersonas());
         setListaRolEdit(this.comboRoles());
         setPersonasEdit(getObjUsuarioEditar().getIdPersona());
         setRolesEdit(getObjUsuarioEditar().getIdRol());
         setNombreEdit(getObjUsuarioEditar().getNombre());
         setClaveEdit(getObjUsuarioEditar().getClave());
         setFechaEdit(getObjUsuarioEditar().getFecha());
         for(PersonaDTO dto:listaPersonaEdit){
             if((int)dto.getIdpersona()==objUsuarioEditar.getIdpersona().getIdpersona()){
             personaTablaSelect=dto;
             break;
             }
         }
        RequestContext context = RequestContext.getCurrentInstance(); 
        context.update("formEditUsuario");
        context.execute("PF('editUsuarioModal').show();");
    }
     public void editarUsuario(ActionEvent actionEvent){
             UsuarioDTO dto = new UsuarioDTO();
             dto.setIdusuario(getIdUsuarioEdit());
             dto.setClave(getClaveEdit());
             dto.setNombre(getNombreEdit());
             dto.setFecha(getFechaEdit());
             
             Persona entidadPersona = new Persona();
             if(personaTablaSelect==null){
             entidadPersona.setIdpersona(getPersonasEdit());
             }else
                 entidadPersona.setIdpersona(personaTablaSelect.getIdpersona());
             dto.setIdpersona(entidadPersona);
             
              Rol entidadRol = new Rol();
              entidadRol.setIdrol(getRolesEdit());
              dto.setIdrol(entidadRol);
              
              usuarioBO.actualizarUsuario(dto);
              setListaUsuario(usuarioBO.getAllUsuarios());
              setDisableEditar(true);

            RequestContext context = RequestContext.getCurrentInstance(); 
            context.update("formUsuario");
            context.execute("PF('editUsuarioModal').hide();");
            setPersonaTablaSelect(null);
           
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

    public String getApellidoPersonaBusqueda() {
        return apellidoPersonaBusqueda;
    }

    public void setApellidoPersonaBusqueda(String apellidoPersonaBusqueda) {
        this.apellidoPersonaBusqueda = apellidoPersonaBusqueda;
    }

    public Integer getIdusuarioNuevo() {
        return idusuarioNuevo;
    }

    public void setIdusuarioNuevo(Integer idusuarioNuevo) {
        this.idusuarioNuevo = idusuarioNuevo;
    }

    public Date getFechaNuevo() {
        return fechaNuevo;
    }

    public void setFechaNuevo(Date fechaNuevo) {
        this.fechaNuevo = fechaNuevo;
    }

    public String getNombreNuevo() {
        return nombreNuevo;
    }

    public void setNombreNuevo(String nombreNuevo) {
        this.nombreNuevo = nombreNuevo;
    }

    public String getClaveNuevo() {
        return claveNuevo;
    }

    public void setClaveNuevo(String claveNuevo) {
        this.claveNuevo = claveNuevo;
    }

    public int getIdpersonaNuevo() {
        return idpersonaNuevo;
    }

    public void setIdpersonaNuevo(int idpersonaNuevo) {
        this.idpersonaNuevo = idpersonaNuevo;
    }

    public int getIdrolNuevo() {
        return idrolNuevo;
    }

    public void setIdrolNuevo(int idrolNuevo) {
        this.idrolNuevo = idrolNuevo;
    }

    public List<RolDTO> getListaRolAdd() {
        return listaRolAdd;
    }

    public void setListaRolAdd(List<RolDTO> listaRolAdd) {
        this.listaRolAdd = listaRolAdd;
    }

    public PersonaBO getPersonaBO() {
        return personaBO;
    }

    public void setPersonaBO(PersonaBO personaBO) {
        this.personaBO = personaBO;
    }

    public List<PersonaDTO> getListaPersonaAdd() {
        return listaPersonaAdd;
    }

    public void setListaPersonaAdd(List<PersonaDTO> listaPersonaAdd) {
        this.listaPersonaAdd = listaPersonaAdd;
    }

    public UsuarioDTO getObjUsuarioEditar() {
        return objUsuarioEditar;
    }

    public void setObjUsuarioEditar(UsuarioDTO objUsuarioEditar) {
        this.objUsuarioEditar = objUsuarioEditar;
    }

    public boolean isDisableEditar() {
        return disableEditar;
    }

    public void setDisableEditar(boolean disableEditar) {
        this.disableEditar = disableEditar;
    }

    public Integer getUsuarioEdit() {
        return usuarioEdit;
    }

    public void setUsuarioEdit(Integer usuarioEdit) {
        this.usuarioEdit = usuarioEdit;
    }

    public Integer getRolesEdit() {
        return rolesEdit;
    }

    public void setRolesEdit(Integer rolesEdit) {
        this.rolesEdit = rolesEdit;
    }

    public Integer getPersonasEdit() {
        return personasEdit;
    }

    public void setPersonasEdit(Integer personasEdit) {
        this.personasEdit = personasEdit;
    }

    public String getNombreEdit() {
        return nombreEdit;
    }

    public void setNombreEdit(String nombreEdit) {
        this.nombreEdit = nombreEdit;
    }

    public String getClaveEdit() {
        return claveEdit;
    }

    public void setClaveEdit(String claveEdit) {
        this.claveEdit = claveEdit;
    }

    public Date getFechaEdit() {
        return fechaEdit;
    }

    public void setFechaEdit(Date fechaEdit) {
        this.fechaEdit = fechaEdit;
    }

    public Integer getIdUsuarioEdit() {
        return idUsuarioEdit;
    }

    public void setIdUsuarioEdit(Integer idUsuarioEdit) {
        this.idUsuarioEdit = idUsuarioEdit;
    }

    public List<RolDTO> getListaRolEdit() {
        return listaRolEdit;
    }

    public void setListaRolEdit(List<RolDTO> listaRolEdit) {
        this.listaRolEdit = listaRolEdit;
    }

    public List<PersonaDTO> getListaPersonaEdit() {
        return listaPersonaEdit;
    }

    public void setListaPersonaEdit(List<PersonaDTO> listaPersonaEdit) {
        this.listaPersonaEdit = listaPersonaEdit;
    }

    public PersonaDTO getPersonaTablaSelect() {
        return personaTablaSelect;
    }

    public void setPersonaTablaSelect(PersonaDTO personaTablaSelect) {
        this.personaTablaSelect = personaTablaSelect;
    }

    public boolean isBtnRegistrarEstado() {
        return btnRegistrarEstado;
    }

    public void setBtnRegistrarEstado(boolean btnRegistrarEstado) {
        this.btnRegistrarEstado = btnRegistrarEstado;
    }
    
    
    
}
