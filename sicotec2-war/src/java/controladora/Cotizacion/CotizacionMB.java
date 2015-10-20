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
    private CotizacionDTO camposGuardar;
    private CotizacionDTO cotizacionSelec;
    private List<Empresa> listaEmpresas;

    private Empresa emp;
    private List<Almacen> listaAlmacenes;
    private String selectAlmacen;
    private boolean btnEditarEstado;

    private List<Tipoitem> listaTipoItem;
    private List<Tipoitem> filtroTipoItem;
    private Tipoitem tipoItemSelect;
    private CotipoitemDTO cotipoItemSelect;
    private List<Categoria> listaCategoria;
    private List<CotipoitemDTO> listaCotipoItem;
    private List<CotipoitemDTO> listaCoItemSelect;
    private Cotizacion camposCrear;
    private boolean btnAgregarItem;
    private boolean btnQuitarItem;
    private double chamgePrecioCrea;
    private double minPrecioCrea;

    /**
     * Creates a new instance of CotizacionMB
     */
    public CotizacionMB() {
    }

    @PostConstruct
    public void init() {

        campos = new CotizacionDTO();
        campos.setIdempresa(new Empresa());
        btnEditarEstado = true;
        listaCotizacion = cotizacionBO.getAllCotizaciones();
        cotizacionSelec = new CotizacionDTO();
        cotizacionSelec.setIdempresa(new Empresa());
        cotizacionSelec.setEmpresaDTO(new EmpresaDTO());

        listaEmpresas = cotizacionBO.empresasAll();

        camposGuardar = new CotizacionDTO();
        camposGuardar.setIdempresa(new Empresa());

        listaAlmacenes = almacenBO.findAll();
        listaTipoItem = cotizacionBO.tipoItemAll();
        listaCategoria = cotizacionBO.findCategoriaAll();
        listaCotipoItem = new ArrayList<>();
        listaCoItemSelect = new ArrayList<>();
        cotipoItemSelect = new CotipoitemDTO();
        camposCrear = new Cotizacion();
        camposCrear.setEstado(0);
        camposCrear.setIdempresa(new Empresa());
        btnAgregarItem = true;
        btnQuitarItem = true;

    }

    public List<CotizacionDTO> consultar(ActionEvent actionEvent) {
        listaCotizacion = cotizacionBO.BuscarCotizacion(campos);
        return listaCotizacion;
    }

    public void onRowSelectCot(SelectEvent event) {
//        cotizacionSelec = (CotizacionDTO) event.getObject();
        btnEditarEstado = false;
    }

    public void crear(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('regCot').show();");
    }

    // CREAR
    public void guardarCrear(ActionEvent actionEvent) {
        if (listaCotipoItem.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay Items Agregados", ""));
            return;
        }

        camposCrear = cotizacionBO.guardarCrear(camposCrear);
        for (int i = 0; i < listaCotipoItem.size(); i++) {
            listaCotipoItem.get(i).setCotizacion(camposCrear);
            listaCotipoItem.get(i).setDescuento(listaCotipoItem.get(i).getTipoitem().getPrecioLista() - listaCotipoItem.get(i).getPrecio());
        }
        cotizacionBO.guardarCrearItems(listaCotipoItem);
        cerrarCrear(actionEvent);
    }

    public void cerrarCrear(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('regCot').hide();");
        listaCotipoItem = new ArrayList<>();
        listaCoItemSelect = new ArrayList<>();
        cotipoItemSelect = new CotipoitemDTO();
        camposCrear = new Cotizacion();
        camposCrear.setIdempresa(new Empresa());
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

        cotipoItemSelect = new CotipoitemDTO();
        cotipoItemSelect.setCotizacion(camposCrear);
        cotipoItemSelect.setTipoitem(tipoItemSelect);
        cotipoItemSelect.setPrecio(tipoItemSelect.getPrecioLista());
        cotipoItemSelect.setCantidad(1);
        listaCotipoItem.add(cotipoItemSelect);
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
        chamgePrecioCrea = cotipoItemSelect.getPrecio();
        if (cotizacionBO.isEmpDistribudora(camposCrear.getIdempresa())) {
            minPrecioCrea = cotipoItemSelect.getPrecio() - (cotipoItemSelect.getTipoitem().getDesDistribuidor() * cotipoItemSelect.getPrecio() / 100);
        } else {
            minPrecioCrea = cotipoItemSelect.getPrecio() - cotipoItemSelect.getTipoitem().getDesCliente() * cotipoItemSelect.getPrecio();

        }
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
         RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editItemPrecio').hide();");
    }

    public void enviaCrear(ActionEvent actionEvent) {
        camposCrear.setFechaEnvio(new Date());
        camposCrear.setEstado(2);
        guardarCrear(actionEvent);
        cerrarCrear(actionEvent);
    }

    public void aprobarCrea(ActionEvent actionEvent) {
        if (listaCoItemSelect.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No se ha Seleccionado ningun Item", ""));
            return;
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('aproVentaCrea').show();");
    }

    public void acepAproCrear(ActionEvent actionEvent) {
        camposCrear.setEstado(1);
        guardarCrear(actionEvent);
        cotizacionBO.generaVentaCrea(listaCoItemSelect,camposCrear);
        cerrarAproCrear(actionEvent);
    }

    public void cerrarAproCrear(ActionEvent actionEvent) {

    }

    public void almacenSelect() {
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("TipoitemSelect");
    }

    // FIN CREAR
    // EDITAR
    public void editar(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editCot').show();");
    }

    public void cerrarEditar(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('editCot').hide();");
    }
    // FIN EDITAR

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

    public CotizacionDTO getCamposGuardar() {
        return camposGuardar;
    }

    public void setCamposGuardar(CotizacionDTO camposGuardar) {
        this.camposGuardar = camposGuardar;
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

}
