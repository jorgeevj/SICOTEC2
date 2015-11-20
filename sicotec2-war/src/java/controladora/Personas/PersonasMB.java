/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Personas;

import bo.PersonaBO;
import bo.UbigeoBO;
import dto.PersonaDTO;
import dto.TelefpersDTO;
import dto.UbicacionDTO;
import dto.UbipersDTO;
import entidades.Persona;
import entidades.Ubigeo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class PersonasMB {
    @EJB
    private PersonaBO personaBO;
    @EJB
    private UbigeoBO ubigeoBO;
    //DTOSSELECT
    private TelefpersDTO telefPersDTOSelectAdd;
    private PersonaDTO personaDTOSelect;
    private UbipersDTO ubiPersDTOSelect;
    private UbicacionDTO UbicPersAgregar;
    //ARRAYS
    private List<PersonaDTO> listaAllPersonas;
    private List<TelefpersDTO> listaTelefPersAdd;
    private List<UbipersDTO> listaUbiPersAdd;
    private List<Ubigeo> listDepartamentosAdd;
    private List<Ubigeo> listProvinciaAdd;
    private List<Ubigeo> listDistritosAdd;
    
    //BUSCAR
    private String nombreBuscar;
    private String apellidoBuscar;
    private String dniBuscar;
    
    //AGREGAR
    private String nombreAdd;
    private String apellidoAdd;
    private String dniAdd;
    private String emailAdd;
    //UBICACION ADD
    private String codProvinciaAdd;
    private String codDepartamentoAdd;
    private String codDistritoAdd;
    private String numeroUbicAdd;
    private String nombreUbicAdd;
    private UbipersDTO ubiPersDTOADD;
    //TELEFONO ADD
    private String numeroTelefAdd;
    private String tipoTelefAdd;
    private String operadorTelefAdd;
    
    @PostConstruct
    public void init(){
        ubiPersDTOADD = new UbipersDTO();
        setListaAllPersonas(personaBO.getAllPresonasDTO());
        listDepartamentosAdd = ubigeoBO.getAllDepartamentos();
    }

    
    public void buscarPersonas(ActionEvent actionEvent){
        PersonaDTO dto = new PersonaDTO();
        dto.setNombre(getNombreBuscar());
        dto.setApellido(getApellidoBuscar());
        dto.setDni(getDniBuscar());
        setListaAllPersonas(personaBO.findPersona(dto));
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabPersonas");
    }
    
    public void actionLimpiar(ActionEvent actionEvent){
        this.limpiarYrefrescar();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formPersonas");
        context.update("formTabPersonas");
    }
    public void limpiarYrefrescar(){
        setNombreBuscar("");
        setApellidoBuscar("");
        setDniBuscar("");
        setListaAllPersonas(personaBO.getAllPresonasDTO());
    }
    
    public void abrirModalAddPersona(ActionEvent actionEvent){
        setNombreAdd("");
        setApellidoAdd("");
        setDniAdd("");
        setEmailAdd("");
        setListaTelefPersAdd(new ArrayList<TelefpersDTO>());
        setListaUbiPersAdd(new ArrayList<UbipersDTO>());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('addPersona').show();");
        context.update("formAddPersona");
    }
    
    public void quitarTelefPers(ActionEvent actionEvent){
        getListaTelefPersAdd().remove(getTelefPersDTOSelectAdd());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formAddPersona");
    }
    
    public void abrirModalAddTelfPers(ActionEvent actionEvent){
        setNumeroTelefAdd("");
        setOperadorTelefAdd("");
        setNumeroTelefAdd("");
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('addTelfPers').show();");
        context.update("formTelefPers");
    }
    
    public void addTelefPers(ActionEvent actionEvent){
        String msj = this.validaCamposAddTelefPers();
        if(!msj.equals("")){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", msj);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            TelefpersDTO telefPersDTO = new TelefpersDTO();
            telefPersDTO.setNumero(getNumeroTelefAdd());
            telefPersDTO.setOperador(getOperadorTelefAdd());
            telefPersDTO.setTipo(getTipoTelefAdd());
            getListaTelefPersAdd().add(telefPersDTO);
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('addTelfPers').hide();");
            context.update("formAddPersona");
       }
    }
    
    public String validaCamposAddTelefPers(){
        String msj = "";
        if(getNumeroTelefAdd() == null || getNumeroTelefAdd().equals("")){
            msj = "Debe ingresar un telefono";
        } else{
            if(!isNumeric(getNumeroTelefAdd())){
                msj = "El telefono solo debe contener numeros";
            }
        }
        if(getOperadorTelefAdd() == null || getOperadorTelefAdd().equals("")){
            msj = "Debe seleccionar un operador";
        }
        if(getTipoTelefAdd() == null || getTipoTelefAdd().equals("") ){
            msj = "Debe seleccionar un tipo de telefono";
        }
        return msj;
    }
    public void selectTelefPersAdd(SelectEvent selectEvent){
        setTelefPersDTOSelectAdd((TelefpersDTO)selectEvent.getObject());
    }
    
    public void selectPersona(SelectEvent selectEvent){
        setPersonaDTOSelect((PersonaDTO)selectEvent.getObject());
    }
    
    public void agregarNuevaPersona(ActionEvent actionEvent){
        String msj = this.validarCamposPersona();
        if(!msj.equals("")){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", msj);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            PersonaDTO dtoPersona = new PersonaDTO();
            dtoPersona.setApellido(getApellidoAdd());
            dtoPersona.setDni(getDniAdd());
            dtoPersona.setEmail(getEmailAdd());
            dtoPersona.setNombre(getNombreAdd());
            Persona entidad = personaBO.insertNuevaPersona(dtoPersona);
            for(TelefpersDTO dtoTelefPers : getListaTelefPersAdd()){
                dtoTelefPers.setIdpersona(entidad);
            }
            for(UbipersDTO dtoUbiPers : getListaUbiPersAdd()){
                dtoUbiPers.setIdpersona(entidad);
            }

            personaBO.insertTelefNuevaPers(getListaTelefPersAdd());
            personaBO.insertarUbicacionPersona(getListaUbiPersAdd());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('addPersona').hide();");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "OJO", "SE AGREGO A LA NUEVA PERSONA");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public String validarCamposPersona(){
        String msj = "";
        if(getApellidoAdd() == null || getApellidoAdd().equals("")){
            msj = "Debe ingresar un apellido";
        } else{
            if(!isLetter(getApellidoAdd())){
                msj = "El apellido solo debe contener letras";
            }
        }
        if(getDniAdd() == null || getDniAdd().equals("")){
            msj = "Debe ingresar un dni";
        } else{
            if(!isNumeric(getDniAdd())){
                msj = "El dni solo debe contener numeros";
            }
        }
        if(getEmailAdd() == null || getEmailAdd().equals("")){
            msj = "Debe ingresar un email";
        }
        if(getNombreAdd() == null || getNombreAdd().equals("")){
            msj = "Debe ingresar un nombre";
        } else {
            if(!isLetter(getNombreAdd())){
                msj = "El apellido solo debe contener letras";
            }
        }
        return msj;
    }
    
    public void abrirModalAddUbiPers(ActionEvent actionEvent){
        setNombreUbicAdd("");
        setNumeroUbicAdd("");
        setCodDepartamentoAdd("");
        setCodDistritoAdd("");
        setCodProvinciaAdd("");
        setListaUbiPersAdd(new ArrayList<UbipersDTO>());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dialogUbicacionPers').show();");
        context.update("formSelectUbiPers");
    }
    
    public void addListaUbiPersAdd(ActionEvent actionEvent){
        String msj = this.validaCamposAddUbiPers();
        if(!msj.equals("")){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", msj);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            getUbiPersDTOADD().setCodDept(getCodDepartamentoAdd());
            getUbiPersDTOADD().setCodDist(getCodDistritoAdd());
            getUbiPersDTOADD().setCodProv(getCodProvinciaAdd());
            getUbiPersDTOADD().setNombre(getNombreUbicAdd());
            getUbiPersDTOADD().setNumero(getNumeroUbicAdd());
            getUbiPersDTOADD().setPrincipal("");
            Ubigeo u = ubigeoBO.getUbigeo(getUbiPersDTOADD().getCodDept(), getUbiPersDTOADD().getCodProv(), getUbiPersDTOADD().getCodDist());
            getUbiPersDTOADD().setNombreDistrito(u.getDistrito());
            getUbiPersDTOADD().setNombreDepartamento(u.getDepartamento());
            getUbiPersDTOADD().setNombreProvincia(u.getProvincia());
            getListaUbiPersAdd().add(getUbiPersDTOADD());
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('dialogUbicacionPers').hide();");
            context.update("formAddPersona");
        }
    }
    
    public void selectUbiPersAdd(SelectEvent event){
        setUbiPersDTOSelect((UbipersDTO)event.getObject());
    }
    public void removeListaUbiPersAdd(ActionEvent actionEvent){
        if(getUbiPersDTOSelect() != null){
            getListaUbiPersAdd().remove(getUbiPersDTOSelect());
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formAddPersona");
        }
    }
    
    public String validaCamposAddUbiPers(){
        String msj = "";
        if(getNombreUbicAdd().equals("") || getNombreUbicAdd() == null){
            msj = "Debe ingresar un nombre";
        }
        if(getNumeroUbicAdd().equals("") || getNumeroUbicAdd() == null){
            msj = "Debe ingresar un numero";
        } else{
            if(!isNumeric(getNumeroUbicAdd())){
                msj = "Debe ingresar un numero";
            }
        }
        if(getCodDistritoAdd() == null || getCodDistritoAdd().equals("")){
            msj = "Debe seleccionar un distrito";
        }
        if(getCodProvinciaAdd() == null || getCodProvinciaAdd().equals("")){
            msj = "Debe seleccionar una provincia";
        }
        if(getCodDepartamentoAdd() == null || getCodDepartamentoAdd().equals("") ){
            msj = "Debe seleccionar un departamento";
        }
        return msj;
    }
   
    public void cargarProvincias(ValueChangeEvent event) {
        System.out.println("valpr " + getCodDepartamentoAdd());
        
        if(event.getNewValue() == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "DEBE SELECCIONAR UN ELEMENTO");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            listProvinciaAdd = ubigeoBO.getAllProvincias(event.getNewValue().toString());
            listDistritosAdd = new ArrayList<>();
            getUbiPersDTOADD().setCodDept(event.getNewValue().toString());
            getUbiPersDTOADD().setCodProv("");
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formSelectUbiPers");
        }
        /*if(getCodDepartamentoAdd() != null && !getCodDepartamentoAdd().equals("") ){
            listProvinciaAdd = ubigeoBO.getAllProvincias(event.getNewValue().toString());
            listDistritosAdd = new ArrayList<>();
            getUbiPersDTOADD().setCodDept(event.getNewValue().toString());
            getUbiPersDTOADD().setCodProv("");
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formSelectUbiPers");
        } else{
            listProvinciaAdd = new ArrayList<>();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "DEBE SELECCIONAR UN DEPARTAMENTO");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }*/
    }

    public void cargarDistritos(ValueChangeEvent event) {
        if(event.getNewValue() == null){
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "DEBE SELECCIONAR UN ELEMENTO");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } else{
            listDistritosAdd = ubigeoBO.getAllDistritos(getUbiPersDTOADD().getCodDept(), event.getNewValue().toString());
            getUbiPersDTOADD().setCodDist("");
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formSelectUbiPers");
        }
        /*if(getCodProvinciaAdd() != null && !getCodProvinciaAdd().equals("")){
            listDistritosAdd = ubigeoBO.getAllDistritos(getUbiPersDTOADD().getCodDept(), event.getNewValue().toString());
            getUbiPersDTOADD().setCodDist("");
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formSelectUbiPers");
        } else{
            listDistritosAdd = new ArrayList<>();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "OJO", "DEBE SELECCIONAR UNA PROVINCIA");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }*/
    }
    
    private static boolean isNumeric(String cadena){
	try {
            Integer.parseInt(cadena);
            return true;
	} catch (NumberFormatException nfe){
            return false;
	}
    }   
    
    public boolean isLetter(String name) {
        char[] chars = name.toCharArray();
        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }
    
    public List<PersonaDTO> getListaAllPersonas() {
        return listaAllPersonas;
    }

    public void setListaAllPersonas(List<PersonaDTO> listaAllPersonas) {
        this.listaAllPersonas = listaAllPersonas;
    }

    public String getNombreBuscar() {
        return nombreBuscar;
    }

    public void setNombreBuscar(String nombreBuscar) {
        this.nombreBuscar = nombreBuscar;
    }

    public String getApellidoBuscar() {
        return apellidoBuscar;
    }

    public void setApellidoBuscar(String apellidoBuscar) {
        this.apellidoBuscar = apellidoBuscar;
    }

    public String getDniBuscar() {
        return dniBuscar;
    }

    public void setDniBuscar(String dniBuscar) {
        this.dniBuscar = dniBuscar;
    }

    public String getNombreAdd() {
        return nombreAdd;
    }

    public void setNombreAdd(String nombreAdd) {
        this.nombreAdd = nombreAdd;
    }

    public String getApellidoAdd() {
        return apellidoAdd;
    }

    public void setApellidoAdd(String apellidoAdd) {
        this.apellidoAdd = apellidoAdd;
    }

    public String getDniAdd() {
        return dniAdd;
    }

    public void setDniAdd(String dniAdd) {
        this.dniAdd = dniAdd;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }

    public List<TelefpersDTO> getListaTelefPersAdd() {
        return listaTelefPersAdd;
    }

    public void setListaTelefPersAdd(List<TelefpersDTO> listaTelefPersAdd) {
        this.listaTelefPersAdd = listaTelefPersAdd;
    }

    public TelefpersDTO getTelefPersDTOSelectAdd() {
        return telefPersDTOSelectAdd;
    }

    public void setTelefPersDTOSelectAdd(TelefpersDTO telefPersDTOSelectAdd) {
        this.telefPersDTOSelectAdd = telefPersDTOSelectAdd;
    }

    public PersonaDTO getPersonaDTOSelect() {
        return personaDTOSelect;
    }

    public void setPersonaDTOSelect(PersonaDTO personaDTOSelect) {
        this.personaDTOSelect = personaDTOSelect;
    }

    public List<UbipersDTO> getListaUbiPersAdd() {
        return listaUbiPersAdd;
    }

    public void setListaUbiPersAdd(List<UbipersDTO> listaUbiPersAdd) {
        this.listaUbiPersAdd = listaUbiPersAdd;
    }

    public UbipersDTO getUbiPersDTOSelect() {
        return ubiPersDTOSelect;
    }

    public void setUbiPersDTOSelect(UbipersDTO ubiPersDTOSelect) {
        this.ubiPersDTOSelect = ubiPersDTOSelect;
    }

    public PersonaBO getPersonaBO() {
        return personaBO;
    }

    public void setPersonaBO(PersonaBO personaBO) {
        this.personaBO = personaBO;
    }

    public UbigeoBO getUbigeoBO() {
        return ubigeoBO;
    }

    public void setUbigeoBO(UbigeoBO ubigeoBO) {
        this.ubigeoBO = ubigeoBO;
    }

    public UbicacionDTO getUbicPersAgregar() {
        return UbicPersAgregar;
    }

    public void setUbicPersAgregar(UbicacionDTO UbicPersAgregar) {
        this.UbicPersAgregar = UbicPersAgregar;
    }

    public List<Ubigeo> getListDepartamentosAdd() {
        return listDepartamentosAdd;
    }

    public void setListDepartamentosAdd(List<Ubigeo> listDepartamentosAdd) {
        this.listDepartamentosAdd = listDepartamentosAdd;
    }

    public List<Ubigeo> getListProvinciaAdd() {
        return listProvinciaAdd;
    }

    public void setListProvinciaAdd(List<Ubigeo> listProvinciaAdd) {
        this.listProvinciaAdd = listProvinciaAdd;
    }

    public List<Ubigeo> getListDistritosAdd() {
        return listDistritosAdd;
    }

    public void setListDistritosAdd(List<Ubigeo> listDistritosAdd) {
        this.listDistritosAdd = listDistritosAdd;
    }

    public String getCodProvinciaAdd() {
        return codProvinciaAdd;
    }

    public void setCodProvinciaAdd(String codProvinciaAdd) {
        this.codProvinciaAdd = codProvinciaAdd;
    }

    public String getCodDepartamentoAdd() {
        return codDepartamentoAdd;
    }

    public void setCodDepartamentoAdd(String codDepartamentoAdd) {
        this.codDepartamentoAdd = codDepartamentoAdd;
    }

    public String getCodDistritoAdd() {
        return codDistritoAdd;
    }

    public void setCodDistritoAdd(String codDistritoAdd) {
        this.codDistritoAdd = codDistritoAdd;
    }

    public String getNumeroUbicAdd() {
        return numeroUbicAdd;
    }

    public void setNumeroUbicAdd(String numeroUbicAdd) {
        this.numeroUbicAdd = numeroUbicAdd;
    }

    public String getNombreUbicAdd() {
        return nombreUbicAdd;
    }

    public void setNombreUbicAdd(String nombreUbicAdd) {
        this.nombreUbicAdd = nombreUbicAdd;
    }

    public String getNumeroTelefAdd() {
        return numeroTelefAdd;
    }

    public void setNumeroTelefAdd(String numeroTelefAdd) {
        this.numeroTelefAdd = numeroTelefAdd;
    }

    public String getTipoTelefAdd() {
        return tipoTelefAdd;
    }

    public void setTipoTelefAdd(String tipoTelefAdd) {
        this.tipoTelefAdd = tipoTelefAdd;
    }

    public String getOperadorTelefAdd() {
        return operadorTelefAdd;
    }

    public void setOperadorTelefAdd(String operadorTelefAdd) {
        this.operadorTelefAdd = operadorTelefAdd;
    }

    public UbipersDTO getUbiPersDTOADD() {
        return ubiPersDTOADD;
    }

    public void setUbiPersDTOADD(UbipersDTO ubiPersDTOADD) {
        this.ubiPersDTOADD = ubiPersDTOADD;
    }
    
}
