/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Personas;

import bo.PersonaBO;
import dto.PersonaDTO;
import dto.TelefpersDTO;
import entidades.Persona;
import java.util.ArrayList;
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
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class PersonasMB {
    @EJB
    private PersonaBO personaBO;
    
    //DTOSSELECT
    private TelefpersDTO telefPersDTOSelectAdd;
    private PersonaDTO personaDTOSelect;
    //ARRAYS
    private List<PersonaDTO> listaAllPersonas;
    private List<TelefpersDTO> listaTelefPersDTO;
    //BUSCAR
    private String nombreBuscar;
    private String apellidoBuscar;
    private String dniBuscar;
    
    //AGREGAR
    private String nombreAdd;
    private String apellidoAdd;
    private String dniAdd;
    private String emailAdd;
    //AGREGAR TELEFONO PERSONA
    private String numeroAdd;
    private String tipoAdd;
    private String operadorAdd;
    
    @PostConstruct
    public void init(){
        setListaAllPersonas(personaBO.getAllPresonasDTO());
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
        setListaTelefPersDTO(new ArrayList<TelefpersDTO>());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('addPersona').show();");
        context.update("tabTelefPers");
    }
    
    public void abrirModalAddTelfPers(ActionEvent actionEvent){
        this.limpiarYrefrescarTelfPers();
    }
    
    public void limpiarYrefrescarTelfPers(){
        setNumeroAdd("");
        setTipoAdd("0");
        setOperadorAdd("0");
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTelefPers");
        context.execute("PF('addTelfPers').show();");
    }
    
    public void addTelefPers(ActionEvent actionEvent){
        TelefpersDTO dto = new TelefpersDTO();
        dto.setNumero(getNumeroAdd());
        dto.setTipo(getTipoAdd());
        dto.setOperador(getOperadorAdd());
        getListaTelefPersDTO().add(dto);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formAddPersona");
        context.execute("PF('addTelfPers').hide();");
    }
    
    public void quitarTelefPers(ActionEvent actionEvent){
        getListaTelefPersDTO().remove(getTelefPersDTOSelectAdd());
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formAddPersona");
    }
    
    public void selectTelefPersAdd(SelectEvent selectEvent){
        setTelefPersDTOSelectAdd((TelefpersDTO)selectEvent.getObject());
    }
    
    public void selectPersona(SelectEvent selectEvent){
        setPersonaDTOSelect((PersonaDTO)selectEvent.getObject());
    }
    
    public void agregarNuevaPersona(ActionEvent actionEvent){
        PersonaDTO dtoPersona = new PersonaDTO();
        dtoPersona.setApellido(getApellidoAdd());
        dtoPersona.setDni(getDniAdd());
        dtoPersona.setEmail(getEmailAdd());
        dtoPersona.setNombre(getNombreAdd());
        Persona entidad = personaBO.insertNuevaPersona(dtoPersona);
        personaBO.insertTelefNuevaPers(this.getTelefPersDTOInsert(getListaTelefPersDTO(), entidad.getIdpersona()));
        
    }
    
    public List<TelefpersDTO> getTelefPersDTOInsert(List<TelefpersDTO> lista,Integer idPersona){
        Persona entidad = new Persona();
        entidad.setIdpersona(idPersona);
        for(TelefpersDTO dto : lista){
            dto.setIdpersona(entidad);
        }
        return lista;
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

    public List<TelefpersDTO> getListaTelefPersDTO() {
        return listaTelefPersDTO;
    }

    public void setListaTelefPersDTO(List<TelefpersDTO> listaTelefPersDTO) {
        this.listaTelefPersDTO = listaTelefPersDTO;
    }

    public String getNumeroAdd() {
        return numeroAdd;
    }

    public void setNumeroAdd(String numeroAdd) {
        this.numeroAdd = numeroAdd;
    }

    public String getTipoAdd() {
        return tipoAdd;
    }

    public void setTipoAdd(String tipoAdd) {
        this.tipoAdd = tipoAdd;
    }

    public String getOperadorAdd() {
        return operadorAdd;
    }

    public void setOperadorAdd(String operadorAdd) {
        this.operadorAdd = operadorAdd;
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
    
}
