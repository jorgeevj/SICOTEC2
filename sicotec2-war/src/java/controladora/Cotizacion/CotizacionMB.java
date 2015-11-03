/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Cotizacion;

import bo.AlmacenBO;
import bo.CotizacionBO;
import dto.CotipoitemDTO;
import dto.CotizacionDTO;
import dto.EmpresaDTO;
import entidades.Almacen;
import entidades.Categoria;
import entidades.Cotipoitem;
import entidades.Cotizacion;
import entidades.Empresa;
import entidades.Tipoitem;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class CotizacionMB {

    @EJB
    private AlmacenBO almacenBO;

    @EJB
    private CotizacionBO cotizacionBO;

    private List<CotizacionDTO> listaCotizacion;
    private CotizacionDTO campos;
    private CotizacionDTO cotizacionSelec;
    private List<Empresa> listaEmpresas;

    private Empresa emp;
    private List<Almacen> listaAlmacenes;
    private String selectAlmacen;
    private boolean btnEditarEstado;
    private boolean btnVerEstado;

    private List<Tipoitem> listaTipoItem;
    private List<Tipoitem> filtroTipoItem;
    private Tipoitem tipoItemSelect;
    private CotipoitemDTO cotipoItemSelect;
    private CotipoitemDTO catipoItemtemp;
    private List<Categoria> listaCategoria;
    private List<CotipoitemDTO> listaCotipoItem;
    private List<CotipoitemDTO> listaCoItemSelect;
    private Cotizacion camposCrear;
    private boolean btnAgregarItem;
    private boolean btnQuitarItem;
    private double chamgePrecioCrea;
    private double minPrecioCrea;
    private CotipoitemDTO cotipoItemSelectEdit;

    /**
     * Creates a new instance of CotizacionMB
     */
    public CotizacionMB() {
    }

    @PostConstruct
    public void init() {
        
        listaEmpresas = cotizacionBO.empresasAll();

        listaAlmacenes = almacenBO.findAll();
        listaTipoItem = cotizacionBO.tipoItemAll();
        listaCategoria = cotizacionBO.findCategoriaAll();
        limpiarCotizaciones();
        limpiarcamposCrear();
        cotipoItemSelectEdit=new CotipoitemDTO();
        cotipoItemSelect = new CotipoitemDTO();
    }

    public List<CotizacionDTO> consultar(ActionEvent actionEvent) {
        cotizacionSelec = new CotizacionDTO();
        listaCotizacion = cotizacionBO.BuscarCotizacion(campos);
        return listaCotizacion;
    }
    public void limpiar(ActionEvent actionEvent){
    limpiarCotizaciones();
    }
    public void limpiarCotizaciones(){
    cotizacionSelec = new CotizacionDTO();
    campos=new CotizacionDTO();
    listaCotizacion = cotizacionBO.getAllCotizaciones();
    btnEditarEstado = true;
    btnVerEstado=true;
    }

    public void onRowSelectCot(SelectEvent event) {
        btnEditarEstado = false;
        btnVerEstado=false;
    }

    public void crear(ActionEvent actionEvent) {
        limpiarcamposCrear();
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('regCot').show();");
    }

    // CREAR
    private void limpiarcamposCrear() {
        cotizacionSelec = new CotizacionDTO();
        camposCrear = new Cotizacion();
        camposCrear.setEstado(0);
        camposCrear.setIdempresa(new Empresa());
        tipoItemSelect = null;
        listaCoItemSelect=new ArrayList<>();
        listaCotipoItem=new ArrayList<>(); 
        btnAgregarItem = true;
        btnQuitarItem = true;
        cotipoItemSelect = new CotipoitemDTO();
    }

    public void guardarCrear(ActionEvent actionEvent) {
        if (cotizacionSelec.getDuracion()<=0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Para enviar debes colocar la duracion", ""));
            return;
        }
        if (listaCotipoItem.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay Items Agregados", ""));
            return;
        }
        
        camposCrear.setEstado(1);
        camposCrear = cotizacionBO.guardarCrear(camposCrear);
        listaCotizacion=cotizacionBO.getAllCotizaciones();
        for (int i = 0; i < listaCotipoItem.size(); i++) {
            listaCotipoItem.get(i).setCotizacion(camposCrear);
            listaCotipoItem.get(i).setDescuento(listaCotipoItem.get(i).getTipoitem().getPrecioLista() - listaCotipoItem.get(i).getPrecio());
        }
        cotizacionBO.guardarCrearItems(listaCotipoItem);
        cerrarCrear(actionEvent);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Guardo Correctamente la Cotizacion", ""));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCotizacion");
    }

    public void cerrarCrear(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('regCot').hide();");
//        listaCotipoItem = new ArrayList<>();
//        listaCoItemSelect = new ArrayList<>();
//        cotipoItemSelect = new CotipoitemDTO();
//        camposCrear = new Cotizacion();
//        camposCrear.setIdempresa(new Empresa());
        btnAgregarItem = true;
        btnQuitarItem = true;

    }

    public void selectAgregarCrear(SelectEvent event) {
        btnAgregarItem = false;
    }

    public void agregarCrear(ActionEvent actionEvent) {
        for (CotipoitemDTO ct : listaCotipoItem) {
            if (ct.getTipoitem().getIdtipoItem().equals(tipoItemSelect.getIdtipoItem())) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Ya selecciono", tipoItemSelect.getIdtipoItem()));
                return;
            }
        }
        
        camposCrear.setIdempresa(cotizacionSelec.getIdempresa());
        
        catipoItemtemp = new CotipoitemDTO();
        catipoItemtemp.setCotizacion(camposCrear);
        catipoItemtemp.setTipoitem(tipoItemSelect);
        catipoItemtemp.setPrecio(tipoItemSelect.getPrecioLista());
        catipoItemtemp.setCantidad(1);
        listaCotipoItem.add(catipoItemtemp);
    }

    public void quitarCrear(ActionEvent actionEvent) {
        int size = listaCotipoItem.size();
        for (int i = size - 1; 0 <= i; i--) {
            for (int j = 0; j < listaCoItemSelect.size(); j++) {
                if (listaCotipoItem.get(i).getTipoitem().getIdtipoItem().equals(listaCoItemSelect.get(j).getTipoitem().getIdtipoItem())) {
                    listaCotipoItem.remove(i);
                    if (listaCotipoItem.isEmpty()) {
                        listaCoItemSelect.removeAll(listaCoItemSelect);
                        btnQuitarItem = true;
                        return;
                    }
                }
            }
        }
        listaCoItemSelect.removeAll(listaCoItemSelect);
        btnQuitarItem = true;
    }

    public void selectQuitarCrear(SelectEvent event) {
        btnQuitarItem = false;
    }

    public void openPrecioCrea(ActionEvent actionEvent) {
        cotipoItemSelect=(CotipoitemDTO) actionEvent.getComponent().getAttributes().get("myattribute");
        chamgePrecioCrea = cotipoItemSelect.getPrecio();
        if (cotizacionBO.isEmpDistribudora(camposCrear.getIdempresa())) {
            minPrecioCrea = cotipoItemSelect.getTipoitem().getPrecioLista() - (cotipoItemSelect.getTipoitem().getDesDistribuidor() * cotipoItemSelect.getTipoitem().getPrecioLista() / 100);
        } else {
            minPrecioCrea = cotipoItemSelect.getTipoitem().getPrecioLista() - (cotipoItemSelect.getTipoitem().getDesCliente() * cotipoItemSelect.getTipoitem().getPrecioLista() / 100);

        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editItemPrecio').show();");
    }

    public double totalCotizacion() {
        double total = 0;
        for (CotipoitemDTO l : listaCotipoItem) {
            total = total + (l.getPrecio() * l.getCantidad());
        }
        return total;
    }

    public double totalAprovado() {
        double total = 0;
        for (CotipoitemDTO l : listaCoItemSelect) {
            total = total + (l.getPrecio() * l.getCantidad());
        }
        return total;
    }

    public void cerrarPrecioCrear(ActionEvent actionEvent) {
        chamgePrecioCrea = 0;
    }

    public void aceptarPrecioCrea() {
        if (chamgePrecioCrea < minPrecioCrea) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "El precio debe ser mayor o igual al minimo", ""));

            return;
        }
        cotipoItemSelect.setPrecio(chamgePrecioCrea);
        cotipoItemSelectEdit.setPrecio(chamgePrecioCrea);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editItemPrecio').hide();");
    }

    public void enviaCrear(ActionEvent actionEvent) {
       if (camposCrear.getDuracion()==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Duracion minima de Envio es 1 dia", ""));

            return;
        }
        camposCrear.setFechaEnvio(new Date());
        camposCrear.setEstado(3);
        guardarCrear(actionEvent);
        cerrarCrear(actionEvent);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha Enviado la cotizacion", ""));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCotizacion");
    }

    public void aprobarCrea(ActionEvent actionEvent) {
        if (listaCoItemSelect.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha Seleccionado ningun Item", ""));
            return;
        }
        for (CotipoitemDTO lci : listaCoItemSelect) {
        if(lci.getCantidad()>lci.getStock()){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay suficiente Stock de algun iten seleccionado", ""));    
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("messages");
        return;
        } 
        }
        for(CotipoitemDTO cti:listaCoItemSelect){
        if(cti.getStock()<cti.getCantidad()){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay suficiente Stock de un Iten Seleccionado", ""));
            return;
        }
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('aproVentaCrea').show();");
    }

    public void acepAproCrear(ActionEvent actionEvent) {
        
        camposCrear.setEstado(2);
        guardarCrear(actionEvent);
        cotizacionBO.generaVentaCrea(listaCoItemSelect, camposCrear);
        cerrarAproCrear(actionEvent);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha generado la Venta Correctamente", ""));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCotizacion");    
    }
    
    
    

    public void cerrarAproCrear(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('aproVentaCrea').hide();");
    }

    public void almacenSelect() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("TipoitemSelect");
    }

    // FIN CREAR
    
   
    // EDITAR
    public void editar(ActionEvent actionEvent) {
        if(cotizacionSelec.getEstado()!=1){
        switch(cotizacionSelec.getEstado()){
            case 2: FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No puedes editar una Cotizacion Aprobada", ""));
                break;
            case 3: FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No puedes editar una Cotizacion Enviada", ""));
                break;
            case 4: FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No puedes editar una Cotizacion Caducada", ""));
                break;
        }
        return;
        }
        
        listaCoItemSelect=new ArrayList<>();
        cotipoItemSelectEdit=new CotipoitemDTO();
        listaCotipoItem=cotizacionBO.getListCotipoitemByidCot(cotizacionSelec.getIdcotizacion());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editCot').show();");
//        context.update();
    }
    public void openPrecioedit(ActionEvent actionEvent) {
        cotipoItemSelectEdit=(CotipoitemDTO) actionEvent.getComponent().getAttributes().get("myattribute");
        
        chamgePrecioCrea = cotipoItemSelectEdit.getPrecio();
        if (cotizacionBO.isEmpDistribudora(cotipoItemSelectEdit.getCotizacion().getIdempresa())) {
            minPrecioCrea = cotipoItemSelectEdit.getTipoitem().getPrecioLista() - (cotipoItemSelectEdit.getTipoitem().getDesDistribuidor() * cotipoItemSelectEdit.getTipoitem().getPrecioLista() / 100);
        } else {
            minPrecioCrea = cotipoItemSelectEdit.getTipoitem().getPrecioLista() - (cotipoItemSelectEdit.getTipoitem().getDesCliente() * cotipoItemSelectEdit.getTipoitem().getPrecioLista() / 100);

        }
    }
    public void acepAproEditar(ActionEvent actionEvent) {
        cotizacionSelec.setEstado(2);
        GuardarEditar(actionEvent);
        cotizacionBO.generaVentaEdit(listaCoItemSelect, cotizacionSelec);
        cerrarAproEdit(actionEvent);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha generado la Venta Correctamente", ""));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCotizacion"); 
    }
    public void aprobarEdit(ActionEvent actionEvent) {
        if (listaCoItemSelect.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha Seleccionado ningun Item", ""));
            return;
        }
        for (CotipoitemDTO lci : listaCoItemSelect) {
        if(lci.getCantidad()>lci.getStock()){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay suficiente Stock de algun iten seleccionado", ""));    
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("messagesEdit");
        return;
        } 
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('aproVentaEdit').show();");
    }
    
    public void cerrarAproEdit(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('aproVentaEdit').hide();");
    }
    public void GuardarEditar(ActionEvent actionEvent){
      if (listaCotipoItem.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay Items Agregados", ""));
            return;
        }
        
        cotizacionBO.guardarEditar(cotizacionSelec);
        cotizacionBO.eliminarItemsByCOt(cotizacionSelec.getIdcotizacion());
        for (int i = 0; i < listaCotipoItem.size(); i++) {
            camposCrear.setIdcotizacion(cotizacionSelec.getIdcotizacion());
            listaCotipoItem.get(i).setCotizacion(camposCrear);
            listaCotipoItem.get(i).setDescuento(listaCotipoItem.get(i).getTipoitem().getPrecioLista() - listaCotipoItem.get(i).getPrecio());
        }
        cotizacionBO.guardarCrearItems(listaCotipoItem);
        cerrarEditar(actionEvent);  
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se Edito Correctamente la Cotizacion", ""));
            
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCotizacion");
    }
    public void enviaEditar(ActionEvent actionEvent) {
        cotizacionSelec.setFechaEnvio(new Date());
        cotizacionSelec.setEstado(3);
        GuardarEditar(actionEvent);
        cerrarEditar(actionEvent);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se ha Enviado la cotizacion", ""));
    RequestContext context = RequestContext.getCurrentInstance();
        context.update("formCotizacion");
    }
    public void cerrarEditar(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editCot').hide();");
    }
    // FIN EDITAR
//VER
    public void ver(ActionEvent actionEvent){
    listaCotipoItem=cotizacionBO.getListCotipoitemByidCot(cotizacionSelec.getIdcotizacion());
    
    RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('verCotizacion').show();");
//        context.update("formVerCot");
    }
    
    //FIN VER
    
    public CotizacionBO getCotizacionBO() {
        return cotizacionBO;
    }

    public void setCotizacionBO(CotizacionBO cotizacionBO) {
        this.cotizacionBO = cotizacionBO;
    }

    public CotizacionDTO getCampos() {
        return campos;
    }

    public void setCampos(CotizacionDTO campos) {
        this.campos = campos;
    }

    public List<Almacen> getListaAlmacenes() {
        return listaAlmacenes;
    }

    public void setListaAlmacenes(List<Almacen> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }

    public AlmacenBO getAlmacenBO() {
        return almacenBO;
    }

    public void setAlmacenBO(AlmacenBO almacenBO) {
        this.almacenBO = almacenBO;
    }

    public String getSelectAlmacen() {
        return selectAlmacen;
    }

    public void setSelectAlmacen(String selectAlmacen) {
        this.selectAlmacen = selectAlmacen;
    }

    public Empresa getEmp() {
        return emp;
    }

    public void setEmp(Empresa emp) {
        this.emp = emp;
    }

    public List<CotizacionDTO> getListaCotizacion() {
        return listaCotizacion;
    }

    public void setListaCotizacion(List<CotizacionDTO> listaCotizacion) {
        this.listaCotizacion = listaCotizacion;
    }

    public CotizacionDTO getCotizacionSelec() {
        return cotizacionSelec;
    }

    public void setCotizacionSelec(CotizacionDTO cotizacionSelec) {
        this.cotizacionSelec = cotizacionSelec;
    }

    public boolean isBtnEditarEstado() {
        return btnEditarEstado;
    }

    public void setBtnEditarEstado(boolean btnEditarEstado) {
        this.btnEditarEstado = btnEditarEstado;
    }

    public List<Empresa> getListaEmpresas() {
        return listaEmpresas;
    }

    public void setListaEmpresas(List<Empresa> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    public List<Tipoitem> getListaTipoItem() {
        return listaTipoItem;
    }

    public void setListaTipoItem(List<Tipoitem> listaTipoItem) {
        this.listaTipoItem = listaTipoItem;
    }

    public List<Tipoitem> getFiltroTipoItem() {
        return filtroTipoItem;
    }

    public void setFiltroTipoItem(List<Tipoitem> filtroTipoItem) {
        this.filtroTipoItem = filtroTipoItem;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<CotipoitemDTO> getListaCotipoItem() {
        return listaCotipoItem;
    }

    public void setListaCotipoItem(List<CotipoitemDTO> listaCotipoItem) {
        this.listaCotipoItem = listaCotipoItem;
    }

    public CotipoitemDTO getCotipoItemSelect() {
        return cotipoItemSelect;
    }

    public void setCotipoItemSelect(CotipoitemDTO cotipoItemSelect) {
        this.cotipoItemSelect = cotipoItemSelect;
    }

    public Tipoitem getTipoItemSelect() {
        return tipoItemSelect;
    }

    public void setTipoItemSelect(Tipoitem tipoItemSelect) {
        this.tipoItemSelect = tipoItemSelect;
    }

    public List<CotipoitemDTO> getListaCoItemSelect() {
        return listaCoItemSelect;
    }

    public void setListaCoItemSelect(List<CotipoitemDTO> listaCoItemSelect) {
        this.listaCoItemSelect = listaCoItemSelect;
    }

    public Cotizacion getCamposCrear() {
        return camposCrear;
    }

    public void setCamposCrear(Cotizacion camposCrear) {
        this.camposCrear = camposCrear;
    }

    public boolean isBtnAgregarItem() {
        return btnAgregarItem;
    }

    public void setBtnAgregarItem(boolean btnAgregarItem) {
        this.btnAgregarItem = btnAgregarItem;
    }

    public boolean isBtnQuitarItem() {
        return btnQuitarItem;
    }

    public void setBtnQuitarItem(boolean btnQuitarItem) {
        this.btnQuitarItem = btnQuitarItem;
    }

    public double getChamgePrecioCrea() {
        return chamgePrecioCrea;
    }

    public void setChamgePrecioCrea(double chamgePrecioCrea) {
        this.chamgePrecioCrea = chamgePrecioCrea;
    }

    public double getMinPrecioCrea() {
        return minPrecioCrea;
    }

    public void setMinPrecioCrea(double minPrecioCrea) {
        this.minPrecioCrea = minPrecioCrea;
    }

    public CotipoitemDTO getCotipoItemSelectEdit() {
        return cotipoItemSelectEdit;
    }

    public void setCotipoItemSelectEdit(CotipoitemDTO cotipoItemSelectEdit) {
        this.cotipoItemSelectEdit = cotipoItemSelectEdit;
    }

    public CotipoitemDTO getCatipoItemtemp() {
        return catipoItemtemp;
    }

    public void setCatipoItemtemp(CotipoitemDTO catipoItemtemp) {
        this.catipoItemtemp = catipoItemtemp;
    }

    public boolean isBtnVerEstado() {
        return btnVerEstado;
    }

    public void setBtnVerEstado(boolean btnVerEstado) {
        this.btnVerEstado = btnVerEstado;
    }

}
