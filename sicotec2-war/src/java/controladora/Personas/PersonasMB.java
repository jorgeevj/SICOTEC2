/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Personas;

import bo.PersonaBO;
import dto.PersonaDTO;
import entidades.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class PersonasMB {
    @EJB
    private PersonaBO personaBO;
    
    //ARRAYS
    private List<PersonaDTO> listaAllPersonas;
    //BUSCAR
    private String nombreBuscar;
    private String apellidoBuscar;
    private String dniBuscar;
    
    //AGREGAR
    private String nombreAdd;
    private String apellidoAdd;
    private String dniAdd;
    private String emailAdd;
    
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
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('addPersona').show();");
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
    
}
