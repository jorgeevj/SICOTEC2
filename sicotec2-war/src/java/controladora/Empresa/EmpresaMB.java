/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Empresa;

import bo.EmpresaBO;
import bo.PersonaBO;
import bo.UbigeoBO;
import dto.EmppersonaDTO;
import dto.EmpresaDTO;
import dto.PersonaDTO;
import dto.TelefonoDTO;
import dto.TipoDTO;
import dto.UbicacionDTO;
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

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class EmpresaMB {

    @EJB
    private UbigeoBO ubigeoBO;

    @EJB
    private PersonaBO personaBO;

    @EJB
    private EmpresaBO empresaBO;

    private EmpresaDTO consultaEmpresaDTO;
    private EmpresaDTO EmpresaSelectDTO;
    private List<EmpresaDTO> listaEmpresa;
    private String[] selectTipoEmp;
    private List<TipoDTO> tipoEmp;
    private List<EmppersonaDTO> listaEmpPersona;
    private EmppersonaDTO EmpPersonaSelectDTO;

    private List<UbicacionDTO> listaUbicEmp;
    private UbicacionDTO UbicEmpSelectDTO;
    private UbicacionDTO UbicEmpAgregar;

    private List<TelefonoDTO> listaTelEmp;
    private TelefonoDTO TelEmpSelectDTO;
    private TelefonoDTO TelEmpAgregaDTO;
    
    private List<PersonaDTO> listaPersonas;
    private List<PersonaDTO> filtroPersonas;
    private PersonaDTO consultaPersona;
    private PersonaDTO personaSelected;

    private List<Ubigeo> listDepartamentos;
    private List<Ubigeo> listProvincia;
    private List<Ubigeo> listDistritos;

    /**
     * Creates a new instance of EmpresaMB
     */
    public EmpresaMB() {
    }

    @PostConstruct
    public void init() {
        tipoEmp = empresaBO.getALLTipos();
        
        listDepartamentos = ubigeoBO.getAllDepartamentos();
        limpiarSelectPersonas();
        limpiarEmpresas();
        limpiarRegistrarEmpresa();
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

    public void btnRegistrarEmp(ActionEvent actionEvent) {
        limpiarRegistrarEmpresa();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlRegEmpresa').show();");
        context.update("formRegEmp");
    }

    public void btnEditEmp(ActionEvent actionEvent) {

    }

    //REGISTRAR
    public void btnGuardarEmpresa(ActionEvent actionEvent) {
       consultaEmpresaDTO.setEmppersonaListDTO(listaEmpPersona);
       consultaEmpresaDTO.setUbicacionList(listaUbicEmp);
       consultaEmpresaDTO.setTipoArray(selectTipoEmp);
       consultaEmpresaDTO.setTelefonoList(listaTelEmp);
       empresaBO.guardarEmpresa(consultaEmpresaDTO);
       limpiarEmpresas();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlRegEmpresa').hide();");
        context.update("formEmpresa");
    }

    public void btnAgreReprReg(ActionEvent actionEvent) {
        limpiarSelectPersonas();
        cargarPersonas();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectPersona').show();");
        context.update("formSelectPer");
    }

    public void limpiarSelectPersonas() {
        listaPersonas = new ArrayList<>();
        consultaPersona = new PersonaDTO();
    }

    public void btnQuitReprReg(ActionEvent actionEvent) {
        listaEmpPersona.remove(EmpPersonaSelectDTO);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formRegEmp");
    }

    public void btnLimpiarRegEmp(ActionEvent actionEvent) {
        limpiarRegistrarEmpresa();
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formRegEmp");

    }

    public void limpiarRegistrarEmpresa() {
        consultaEmpresaDTO = new EmpresaDTO();
        listaEmpPersona = new ArrayList<>();
        listaUbicEmp = new ArrayList<>();
        listaTelEmp=new ArrayList<>();
        selectTipoEmp=new String[]{};
    }

    public void btnBusPersonaRegEmp(ActionEvent actionEvent) {
        cargarPersonas();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectPersona').show();");
        context.update("formSelectPer");
    }
    
    private void cargarPersonas(){
    listaPersonas = personaBO.findPersona(consultaPersona);
        int l = listaPersonas.size();
        for (int i = l - 1; i >= 0; i--) {
            for (EmppersonaDTO ep : listaEmpPersona) {
                if (listaPersonas.get(i).getIdpersona() == ep.getIdpersona()) {
                    listaPersonas.remove(i);
                }
            }
        }
    }

    public void btnSelectPersonaRegEmp(ActionEvent actionEvent) {

    }

    public void btnAceptarPersona(ActionEvent actionEvent) {
        listaEmpPersona.add(new EmppersonaDTO(personaSelected, null, personaSelected.getIdpersona()));
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectPersona').hide();");
        context.update("formRegEmp");
    }

    public void btnAgreUbiReg(ActionEvent actionEvent) {
        UbicEmpAgregar = new UbicacionDTO();
        listProvincia = new ArrayList<>();
        listDistritos = new ArrayList<>();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectUbicacion').show();");
        context.update("formSelectUbi");
    }

    public void cargarProvincias(ValueChangeEvent event) {
        listProvincia = ubigeoBO.getAllProvincias(event.getNewValue().toString());
        listDistritos = new ArrayList<>();
        UbicEmpAgregar.setCodProv(null);
    }

    public void cargarDistritos(ValueChangeEvent event) {
        listDistritos = ubigeoBO.getAllDistritos(UbicEmpAgregar.getCodDept(), event.getNewValue().toString());

    }

    public void btnQuitUbiReg(ActionEvent actionEvent) {
        listaUbicEmp.remove(UbicEmpSelectDTO);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formRegEmp");
    }

    public void btnAceptarUbicacion(ActionEvent actionEvent) {
        for(UbicacionDTO ub:listaUbicEmp){
        if(UbicEmpAgregar.getNombre().equals(ub.getNombre())&& UbicEmpAgregar.getNumero().equals(ub.getNumero())){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "La Direccion y numero ya Existe", ""));
        RequestContext context = RequestContext.getCurrentInstance();
           context.update("formSelectUbi");
        return;
        }
        }
        Ubigeo u = ubigeoBO.getUbigeo(UbicEmpAgregar.getCodDept(), UbicEmpAgregar.getCodProv(), UbicEmpAgregar.getCodDist());
        UbicEmpAgregar.setDept(u.getDepartamento());
        UbicEmpAgregar.setProv(u.getProvincia());
        UbicEmpAgregar.setDist(u.getDistrito());
        listaUbicEmp.add(UbicEmpAgregar);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectUbicacion').hide();");
        context.update("formRegEmp");
    }
    
    public void btnAgreTelReg(ActionEvent actionEvent) {
        limpiarAgregarTelefonos();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectTelefono').show();");
        context.update("formSelectTel");
    }
    
    private void limpiarAgregarTelefonos() {
       TelEmpAgregaDTO=new TelefonoDTO();
    }
    
    public void btnAceptarTelefono(ActionEvent actionEvent) {
        listaTelEmp.add(TelEmpAgregaDTO);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlSelectTelefono').hide();");
        context.update("formRegEmp");
    }
    
    public void btnQuitTelReg(ActionEvent actionEvent) {
        listaTelEmp.remove(TelEmpSelectDTO);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formRegEmp");
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

    public String[] getSelectTipoEmp() {
        return selectTipoEmp;
    }

    public void setSelectTipoEmp(String[] selectTipoEmp) {
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

    public PersonaDTO getPersonaSelected() {
        return personaSelected;
    }

    public void setPersonaSelected(PersonaDTO personaSelected) {
        this.personaSelected = personaSelected;
    }

    public List<PersonaDTO> getFiltroPersonas() {
        return filtroPersonas;
    }

    public void setFiltroPersonas(List<PersonaDTO> filtroPersonas) {
        this.filtroPersonas = filtroPersonas;
    }

    public PersonaDTO getConsultaPersona() {
        return consultaPersona;
    }

    public void setConsultaPersona(PersonaDTO consultaPersona) {
        this.consultaPersona = consultaPersona;
    }

    public UbicacionDTO getUbicEmpAgregar() {
        return UbicEmpAgregar;
    }

    public void setUbicEmpAgregar(UbicacionDTO UbicEmpAgregar) {
        this.UbicEmpAgregar = UbicEmpAgregar;
    }

    public List<Ubigeo> getListDepartamentos() {
        return listDepartamentos;
    }

    public void setListDepartamentos(List<Ubigeo> listDepartamentos) {
        this.listDepartamentos = listDepartamentos;
    }

    public List<Ubigeo> getListProvincia() {
        return listProvincia;
    }

    public void setListProvincia(List<Ubigeo> listProvincia) {
        this.listProvincia = listProvincia;
    }

    public List<Ubigeo> getListDistritos() {
        return listDistritos;
    }

    public void setListDistritos(List<Ubigeo> listDistritos) {
        this.listDistritos = listDistritos;
    }

    public TelefonoDTO getTelEmpAgregaDTO() {
        return TelEmpAgregaDTO;
    }

    public void setTelEmpAgregaDTO(TelefonoDTO TelEmpAgregaDTO) {
        this.TelEmpAgregaDTO = TelEmpAgregaDTO;
    }

    
    

}
