/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.Venta;

import bo.AlmacenBO;
import bo.EmpresaBO;
import bo.ImpuestoBO;
import bo.MedioPagoBO;
import bo.VentasBO;
import dto.AlmacenDTO;
import dto.EmpresaDTO;
import dto.ImpuestoDTO;
import dto.ItemDTO;
import dto.MedioPagoDTO;
import dto.VeMedioPagoDTO;
import dto.VentaDTO;
import entidades.Mediopago;
import entidades.Vemediopago;
import entidades.Venta;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author rikardo308
 */
@ManagedBean
@SessionScoped
public class ventaMB {
    @EJB
    private VentasBO ventasBO = new VentasBO();
    @EJB
    private AlmacenBO almacenBO = new AlmacenBO();
    @EJB
    private EmpresaBO empresaBO = new EmpresaBO();
    @EJB
    private MedioPagoBO medioPagoBO = new MedioPagoBO();
    @EJB
    private ImpuestoBO impuestoBO = new ImpuestoBO();
    
    //LISTAS
    private List<VentaDTO> listaVentas = new ArrayList<VentaDTO>();
    private List<AlmacenDTO> listaAlmacenes = new ArrayList<AlmacenDTO>();
    private List<EmpresaDTO> listaEmpresas = new ArrayList<EmpresaDTO>();
    private List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
    private List<MedioPagoDTO> listaMedioPago = new ArrayList<MedioPagoDTO>();
    private List<ImpuestoDTO> listaImpuestos = new ArrayList<ImpuestoDTO>();
    private ArrayList listaEstados = new ArrayList();
    private List<VeMedioPagoDTO> listaVentaMedioPago = new ArrayList<VeMedioPagoDTO>();
    
    //DTOS SELECC
    private VentaDTO ventaSeleccionada = new VentaDTO();
    
    //BUSQUEDA
    private Date fechaInicioBusqueda;
    private Date fechaFinBusqueda;
    private int selectEstadoBusqueda = 100;
    private String serieBusqueda;
    private String correlativoBusqueda;
    private int selectEmpresaBusqueda = 0;
    private int selectAlmacenBusqueda = 0;
    private boolean disableVerItems = true;
    private boolean disableEditar = true;
    
    //EDITAR ESTADO
    private int selectEstadoEdit = 100;
    private boolean panelVisibleEstadoPagada = false;
    private int selectMedioPagoEdit = 0;
    private String cantidadMedioPagoEdit;
    private Double cantidadMedioPagoAcu = 0.0;
    private int selectImpuestoEdit = 0;
    
    @PostConstruct
    public void init(){
        setListaEstados(this.llenarEstados());
        setListaVentas(ventasBO.getVentasByEstado(0));
        setListaAlmacenes(almacenBO.getAllAlmaces());
        setListaEmpresas(empresaBO.getAllEmpresas());
        setListaMedioPago(medioPagoBO.allMedioPago());
        setListaImpuestos(impuestoBO.getAllImpuestos());
    }
    
    public void buscarVenta(){
        VentaDTO dto = new VentaDTO();
        dto.setEstado(getSelectEstadoBusqueda()+"");
        dto.setFechaInicio(getFechaInicioBusqueda());
        dto.setFechaFin(getFechaFinBusqueda());
        dto.setSerie(getSerieBusqueda());
        dto.setCorrelativo(getCorrelativoBusqueda());
        dto.setIdEmpresa(getSelectEmpresaBusqueda());
        dto.setIdalmacen(getSelectAlmacenBusqueda());
        setDisableEditar(true);
        setDisableVerItems(true);
        
        setListaVentas(ventasBO.getVentasByBusqueda(dto));
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTabla");
        context.update("formBotones");
    }
    
    public void limpiar(){
        setSelectEstadoBusqueda(100);
        setSerieBusqueda(null);
        setFechaFinBusqueda(null);
        setFechaInicioBusqueda(null);
        setCorrelativoBusqueda(null);
        setSelectEmpresaBusqueda(0);
        setSelectAlmacenBusqueda(0);
        
        setDisableEditar(true);
        setDisableVerItems(true);
        setListaVentas(ventasBO.getVentasByEstado(0));
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formBusqueda");
        context.update("formBotones");
        context.update("formTabla");

    }
    
    public void selectventa(SelectEvent event){
        setVentaSeleccionada((VentaDTO)event.getObject());
        setDisableEditar(false);
        setDisableVerItems(false);
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formBotones");
    }
    
    public void verItems(){
        setListaItems(ventasBO.listaItemsxVenta(getVentaSeleccionada().getIdventa()));
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formTablaItems");
        context.execute("PF('dialog_ver_items').show();");
    }
    
    public void abrirCambiarEstado(){
        RequestContext context = RequestContext.getCurrentInstance();
        int estado = Integer.parseInt(getVentaSeleccionada().getEstado());
        System.out.println(estado);
        if(estado == 0){//CANCELADA
            setPanelVisibleEstadoPagada(false);
            setSelectEstadoEdit(0);
        }else if(estado == 1){//PAGADA
            setPanelVisibleEstadoPagada(true);
            setSelectEstadoEdit(1);
        }else if(estado == 2){
            setSelectEstadoEdit(1);
            setPanelVisibleEstadoPagada(false);
        }
        context.update("formEditarEstado");
        context.execute("PF('dialog_editar').show();");  
    }
    
    public void selectEstado(){
        if(getSelectEstadoEdit() == 0){
            setPanelVisibleEstadoPagada(false);
        }else if(getSelectEstadoEdit() == 1){
            if(Integer.parseInt(getVentaSeleccionada().getEstado()) == 1){
                setPanelVisibleEstadoPagada(false);
            }else{
                setPanelVisibleEstadoPagada(true);
            }
        }/*else if(getSelectEstadoEdit() == 2){
            setPanelVisibleEstadoPagada(true);
        }*/
        else if(getSelectEstadoEdit() == 100){
            setPanelVisibleEstadoPagada(false);
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.update("formEditarEstado");
    }
    
    public void agregarMedioPago(){
        Double monto = Double.parseDouble(getCantidadMedioPagoEdit());
        monto = monto + getCantidadMedioPagoAcu();
        
        if(getCantidadMedioPagoEdit() != null && !getCantidadMedioPagoEdit().equals("") && Double.parseDouble(getCantidadMedioPagoEdit()) > 0 
           && getSelectMedioPagoEdit() != 0 && monto <= getVentaSeleccionada().getTotal()){
             VeMedioPagoDTO dto = new VeMedioPagoDTO();
             
             dto.setIdMedioPago(getSelectMedioPagoEdit());
             dto.setIdVenta(getVentaSeleccionada().getIdventa());
             dto.setMonto(Double.parseDouble(getCantidadMedioPagoEdit()));
             for(MedioPagoDTO mp : listaMedioPago){
                 if(getSelectMedioPagoEdit() == mp.getIdMedioPago()){
                     dto.setNombreMedioPago(mp.getNombre());
                 }
             }
             
             setCantidadMedioPagoAcu(getCantidadMedioPagoAcu() + Double.parseDouble(getCantidadMedioPagoEdit()));
             getListaVentaMedioPago().add(dto);
             RequestContext context = RequestContext.getCurrentInstance();
             setCantidadMedioPagoEdit("");
             context.update("formEditarEstado");
        }
    }
    
    public void eliminarVentaMedioPago(ActionEvent actionEvent){
        if(getListaVentaMedioPago().size() > 0){
            int index = (int)actionEvent.getComponent().getAttributes().get("indexMp");
            Double monto = (Double)actionEvent.getComponent().getAttributes().get("montoMP");
            getListaVentaMedioPago().remove(index);
            
            setCantidadMedioPagoAcu(getCantidadMedioPagoAcu() - monto);
            
            RequestContext context = RequestContext.getCurrentInstance();
            context.update("formEditarEstado");
        }
    }
    
    public String validateEdit(){
        int estado = getSelectEstadoEdit();
        String sms = "";
        if(estado == 100){
            sms = "Seleccione un estado";
            return sms;
        }
        else if(getSelectImpuestoEdit() == 0){
            sms = "Seleccione un impuesto";
            return sms;
        }
        
        if(estado == 0){
            
        }else if(estado == 1){
            if(getCantidadMedioPagoAcu() == 0.0){
                sms = "Ingrese minimo un medio de pago con su cantidad";
            }
        }
        return sms;
    }
    
    public void editarVenta(){
        String sms = this.validateEdit();
        VentaDTO venta = new VentaDTO();
        int estado = getSelectEstadoEdit();
        if(!sms.equals("")){//FALTA
            System.out.println(sms);
        }else{
            if(estado == 0){
                venta = getVentaSeleccionada();
                venta.setEstado("0");//CANCELADA(ELIMINADA)
                venta.setIdImpuesto(getSelectImpuestoEdit());
            }else if(estado == 1){
                    venta = getVentaSeleccionada();
                if((venta.getTotal() - getCantidadMedioPagoAcu()) == 0.0){//SE PAGO TODO
                    venta.setEstado("1");//PAGADA
                    venta.setIdImpuesto(getSelectImpuestoEdit());
                }else{
                    venta.setEstado("2");//INCOMPLETA
                    venta.setIdImpuesto(getSelectImpuestoEdit());
                }
            }
             ventasBO.editVenta(venta, getListaVentaMedioPago(), estado);
        }
    }
    
    
    public ArrayList llenarEstados() {
        ArrayList estados = new ArrayList();
        estados.add(new SelectItem(0,"Cancelada"));
        estados.add(new SelectItem(1,"Pagada"));
        //estados.add(new SelectItem(2,"Incompleta"));
        
        return estados;
    }

    /**
     * @return the listaVentas
     */
    public List<VentaDTO> getListaVentas() {
        return listaVentas;
    }

    /**
     * @param listaVentas the listaVentas to set
     */
    public void setListaVentas(List<VentaDTO> listaVentas) {
        this.listaVentas = listaVentas;
    }

    /**
     * @return the listaEstados
     */
    public ArrayList getListaEstados() {
        return listaEstados;
    }

    /**
     * @param listaEstados the listaEstados to set
     */
    public void setListaEstados(ArrayList listaEstados) {
        this.listaEstados = listaEstados;
    }

    /**
     * @return the fechaInicioBusqueda
     */
    public Date getFechaInicioBusqueda() {
        return fechaInicioBusqueda;
    }

    /**
     * @param fechaInicioBusqueda the fechaInicioBusqueda to set
     */
    public void setFechaInicioBusqueda(Date fechaInicioBusqueda) {
        this.fechaInicioBusqueda = fechaInicioBusqueda;
    }

    /**
     * @return the fechaFinBusqueda
     */
    public Date getFechaFinBusqueda() {
        return fechaFinBusqueda;
    }

    /**
     * @param fechaFinBusqueda the fechaFinBusqueda to set
     */
    public void setFechaFinBusqueda(Date fechaFinBusqueda) {
        this.fechaFinBusqueda = fechaFinBusqueda;
    }

    /**
     * @return the selectEstadoBusqueda
     */
    public int getSelectEstadoBusqueda() {
        return selectEstadoBusqueda;
    }

    /**
     * @param selectEstadoBusqueda the selectEstadoBusqueda to set
     */
    public void setSelectEstadoBusqueda(int selectEstadoBusqueda) {
        this.selectEstadoBusqueda = selectEstadoBusqueda;
    }

    /**
     * @return the serieBusqueda
     */
    public String getSerieBusqueda() {
        return serieBusqueda;
    }

    /**
     * @param serieBusqueda the serieBusqueda to set
     */
    public void setSerieBusqueda(String serieBusqueda) {
        this.serieBusqueda = serieBusqueda;
    }

    /**
     * @return the correlativoBusqueda
     */
    public String getCorrelativoBusqueda() {
        return correlativoBusqueda;
    }

    /**
     * @param correlativoBusqueda the correlativoBusqueda to set
     */
    public void setCorrelativoBusqueda(String correlativoBusqueda) {
        this.correlativoBusqueda = correlativoBusqueda;
    }

    /**
     * @return the selectEmpresaBusqueda
     */
    public int getSelectEmpresaBusqueda() {
        return selectEmpresaBusqueda;
    }

    /**
     * @param selectEmpresaBusqueda the selectEmpresaBusqueda to set
     */
    public void setSelectEmpresaBusqueda(int selectEmpresaBusqueda) {
        this.selectEmpresaBusqueda = selectEmpresaBusqueda;
    }

    /**
     * @return the selectAlmacenBusqueda
     */
    public int getSelectAlmacenBusqueda() {
        return selectAlmacenBusqueda;
    }

    /**
     * @param selectAlmacenBusqueda the selectAlmacenBusqueda to set
     */
    public void setSelectAlmacenBusqueda(int selectAlmacenBusqueda) {
        this.selectAlmacenBusqueda = selectAlmacenBusqueda;
    }

    /**
     * @return the listaAlmacenes
     */
    public List<AlmacenDTO> getListaAlmacenes() {
        return listaAlmacenes;
    }

    /**
     * @param listaAlmacenes the listaAlmacenes to set
     */
    public void setListaAlmacenes(List<AlmacenDTO> listaAlmacenes) {
        this.listaAlmacenes = listaAlmacenes;
    }

    /**
     * @return the listaEmpresas
     */
    public List<EmpresaDTO> getListaEmpresas() {
        return listaEmpresas;
    }

    /**
     * @param listaEmpresas the listaEmpresas to set
     */
    public void setListaEmpresas(List<EmpresaDTO> listaEmpresas) {
        this.listaEmpresas = listaEmpresas;
    }

    /**
     * @return the listaItems
     */
    public List<ItemDTO> getListaItems() {
        return listaItems;
    }

    /**
     * @param listaItems the listaItems to set
     */
    public void setListaItems(List<ItemDTO> listaItems) {
        this.listaItems = listaItems;
    }

    /**
     * @return the ventaSeleccionada
     */
    public VentaDTO getVentaSeleccionada() {
        return ventaSeleccionada;
    }

    /**
     * @param ventaSeleccionada the ventaSeleccionada to set
     */
    public void setVentaSeleccionada(VentaDTO ventaSeleccionada) {
        this.ventaSeleccionada = ventaSeleccionada;
    }

    /**
     * @return the selectEstadoEdit
     */
    public int getSelectEstadoEdit() {
        return selectEstadoEdit;
    }

    /**
     * @param selectEstadoEdit the selectEstadoEdit to set
     */
    public void setSelectEstadoEdit(int selectEstadoEdit) {
        this.selectEstadoEdit = selectEstadoEdit;
    }

    /**
     * @return the panelVisibleEstadoPagada
     */
    public boolean isPanelVisibleEstadoPagada() {
        return panelVisibleEstadoPagada;
    }

    /**
     * @param panelVisibleEstadoPagada the panelVisibleEstadoPagada to set
     */
    public void setPanelVisibleEstadoPagada(boolean panelVisibleEstadoPagada) {
        this.panelVisibleEstadoPagada = panelVisibleEstadoPagada;
    }

    /**
     * @return the listaMedioPago
     */
    public List<MedioPagoDTO> getListaMedioPago() {
        return listaMedioPago;
    }

    /**
     * @param listaMedioPago the listaMedioPago to set
     */
    public void setListaMedioPago(List<MedioPagoDTO> listaMedioPago) {
        this.listaMedioPago = listaMedioPago;
    }

    /**
     * @return the selectMedioPagoEdit
     */
    public int getSelectMedioPagoEdit() {
        return selectMedioPagoEdit;
    }

    /**
     * @param selectMedioPagoEdit the selectMedioPagoEdit to set
     */
    public void setSelectMedioPagoEdit(int selectMedioPagoEdit) {
        this.selectMedioPagoEdit = selectMedioPagoEdit;
    }

    /**
     * @return the cantidadMedioPagoEdit
     */
    public String getCantidadMedioPagoEdit() {
        return cantidadMedioPagoEdit;
    }

    /**
     * @param cantidadMedioPagoEdit the cantidadMedioPagoEdit to set
     */
    public void setCantidadMedioPagoEdit(String cantidadMedioPagoEdit) {
        this.cantidadMedioPagoEdit = cantidadMedioPagoEdit;
    }

    /**
     * @return the listaVentaMedioPago
     */
    public List<VeMedioPagoDTO> getListaVentaMedioPago() {
        return listaVentaMedioPago;
    }

    /**
     * @param listaVentaMedioPago the listaVentaMedioPago to set
     */
    public void setListaVentaMedioPago(List<VeMedioPagoDTO> listaVentaMedioPago) {
        this.listaVentaMedioPago = listaVentaMedioPago;
    }

    /**
     * @return the cantidadMedioPagoAcu
     */
    public Double getCantidadMedioPagoAcu() {
        return cantidadMedioPagoAcu;
    }

    /**
     * @param cantidadMedioPagoAcu the cantidadMedioPagoAcu to set
     */
    public void setCantidadMedioPagoAcu(Double cantidadMedioPagoAcu) {
        this.cantidadMedioPagoAcu = cantidadMedioPagoAcu;
    }

    /**
     * @return the selectImpuestoEdit
     */
    public int getSelectImpuestoEdit() {
        return selectImpuestoEdit;
    }

    /**
     * @param selectImpuestoEdit the selectImpuestoEdit to set
     */
    public void setSelectImpuestoEdit(int selectImpuestoEdit) {
        this.selectImpuestoEdit = selectImpuestoEdit;
    }

    /**
     * @return the listaImpuestos
     */
    public List<ImpuestoDTO> getListaImpuestos() {
        return listaImpuestos;
    }

    /**
     * @param listaImpuestos the listaImpuestos to set
     */
    public void setListaImpuestos(List<ImpuestoDTO> listaImpuestos) {
        this.listaImpuestos = listaImpuestos;
    }

    /**
     * @return the disableVerItems
     */
    public boolean isDisableVerItems() {
        return disableVerItems;
    }

    /**
     * @param disableVerItems the disableVerItems to set
     */
    public void setDisableVerItems(boolean disableVerItems) {
        this.disableVerItems = disableVerItems;
    }

    /**
     * @return the disableEditar
     */
    public boolean isDisableEditar() {
        return disableEditar;
    }

    /**
     * @param disableEditar the disableEditar to set
     */
    public void setDisableEditar(boolean disableEditar) {
        this.disableEditar = disableEditar;
    }
    
}
