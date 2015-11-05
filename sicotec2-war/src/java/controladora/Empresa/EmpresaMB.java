/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Empresa;

import bo.EmpresaBO;
import dto.EmppersonaDTO;
import dto.EmpresaDTO;
import dto.PersonaDTO;
import dto.TelefonoDTO;
import dto.TipoDTO;
import dto.UbicacionDTO;
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
public class EmpresaMB {

    @EJB
    private EmpresaBO empresaBO;

    private EmpresaDTO consultaEmpresaDTO;
    private EmpresaDTO EmpresaSelectDTO;
    private List<EmpresaDTO> listaEmpresa;
    private List<TipoDTO> selectTipoEmp;
    private List<TipoDTO> tipoEmp;
    private List<EmppersonaDTO> listaEmpPersona;
    private EmppersonaDTO EmpPersonaSelectDTO;
    private List<UbicacionDTO> listaUbicEmp;
    private UbicacionDTO UbicEmpSelectDTO;
    private List<TelefonoDTO> listaTelEmp;
    private TelefonoDTO TelEmpSelectDTO;
    private List<PersonaDTO> listaPersonas;
    private List<PersonaDTO> listaPersonasSelected;
    private PersonaDTO personaSelected;
    /**
     * Creates a new instance of EmpresaMB
     */
    public EmpresaMB() {
    }

    @PostConstruct
    public void init() {
        tipoEmp = empresaBO.getALLTipos();
        listaPersonas=empresaBO.getAllPersonas();
        limpiarEmpresas();
    }

    public void btnLimpiarEmp(ActionEvent actionEvent) {
        limpiarEmpresas();
    }

    public void limpiarEmpresas() {
        consultaEmpresaDTO = new EmpresaDTO();
        listaEmpresa = empresaBO.getAllEmpresas();
    }

    public void btnConsultarEmp(ActionEvent actionEvent) {
        listaEmpresa = empresaBO.findByConsulta(consultaEmpresaDTO);
    }

    //REGISTRAR

    public void btnGuardarEmpresa(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlRegEmpresa').hide();");
        context.update("formEmpresa");
    }

    public void btnAgreReprReg(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlRegEmpresa').hide();");
        context.update("formEmpresa");
    }

    public void btnRegistrarEmp(ActionEvent actionEvent) {

    }

    public void btnLimpiarRegEmp(ActionEvent actionEvent) {

    }

    public void seleccionarCliente(ActionEvent actionEvent) {

    }

    public EmpresaDTO getConsultaEmpresaDTO() {
        return consultaEmpresaDTO;
    }

    public void setConsultaEmpresaDTO(EmpresaDTO consultaEmpresaDTO) {
        this.consultaEmpresaDTO = consultaEmpresaDTO;
    }

    public List<EmpresaDTO> getListaEmpresa() {
        return listaEmpresa;
    }

    public void setListaEmpresa(List<EmpresaDTO> listaEmpresa) {
        this.listaEmpresa = listaEmpresa;
    }

    public EmpresaDTO getEmpresaSelectDTO() {
        return EmpresaSelectDTO;
    }

    public void setEmpresaSelectDTO(EmpresaDTO EmpresaSelectDTO) {
        this.EmpresaSelectDTO = EmpresaSelectDTO;
    }

    public List<TipoDTO> getSelectTipoEmp() {
        return selectTipoEmp;
    }

    public void setSelectTipoEmp(List<TipoDTO> selectTipoEmp) {
        this.selectTipoEmp = selectTipoEmp;
    }

    public List<TipoDTO> getTipoEmp() {
        return tipoEmp;
    }

    public void setTipoEmp(List<TipoDTO> tipoEmp) {
        this.tipoEmp = tipoEmp;
    }

    public List<EmppersonaDTO> getListaEmpPersona() {
        return listaEmpPersona;
    }

    public void setListaEmpPersona(List<EmppersonaDTO> listaEmpPersona) {
        this.listaEmpPersona = listaEmpPersona;
    }

    public List<UbicacionDTO> getListaUbicEmp() {
        return listaUbicEmp;
    }

    public void setListaUbicEmp(List<UbicacionDTO> listaUbicEmp) {
        this.listaUbicEmp = listaUbicEmp;
    }

    public List<TelefonoDTO> getListaTelEmp() {
        return listaTelEmp;
    }

    public void setListaTelEmp(List<TelefonoDTO> listaTelEmp) {
        this.listaTelEmp = listaTelEmp;
    }

    public EmppersonaDTO getEmpPersonaSelectDTO() {
        return EmpPersonaSelectDTO;
    }

    public void setEmpPersonaSelectDTO(EmppersonaDTO EmpPersonaSelectDTO) {
        this.EmpPersonaSelectDTO = EmpPersonaSelectDTO;
    }

    public UbicacionDTO getUbicEmpSelectDTO() {
        return UbicEmpSelectDTO;
    }

    public void setUbicEmpSelectDTO(UbicacionDTO UbicEmpSelectDTO) {
        this.UbicEmpSelectDTO = UbicEmpSelectDTO;
    }

    public TelefonoDTO getTelEmpSelectDTO() {
        return TelEmpSelectDTO;
    }

    public void setTelEmpSelectDTO(TelefonoDTO TelEmpSelectDTO) {
        this.TelEmpSelectDTO = TelEmpSelectDTO;
    }

    public List<PersonaDTO> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<PersonaDTO> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public List<PersonaDTO> getListaPersonasSelected() {
        return listaPersonasSelected;
    }

    public void setListaPersonasSelected(List<PersonaDTO> listaPersonasSelected) {
        this.listaPersonasSelected = listaPersonasSelected;
    }

    public PersonaDTO getPersonaSelected() {
        return personaSelected;
    }

    public void setPersonaSelected(PersonaDTO personaSelected) {
        this.personaSelected = personaSelected;
    }
}
