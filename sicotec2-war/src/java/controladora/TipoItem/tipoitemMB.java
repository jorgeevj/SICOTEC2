/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.TipoItem;

import bo.TipoItemBO;
import dto.TipoItemDTO;
import entidades.Caracteristica;
import entidades.Categoria;
import entidades.Color;
import entidades.Familia;
import entidades.Marca;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean
@SessionScoped
public class tipoitemMB {
   @EJB
    private TipoItemBO tipoItemBO;    
    
    private String codigoItem;
    private String numParte;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String precio;
    private double dsctoCliente;
    private double dsctoDistribuidor;
    
    private String caracteristicaSelect;
    private String marcaSelect;
    private String colorSelect; 
    private String familiaSelect;
    private String categoriaSelect; //posible no uso
    
    private List<TipoItemDTO> lista;
    private TipoItemDTO tipoItemSelect;
    private List<Caracteristica> listaCaracteristicas;
    private List<Marca> listaMarca;
    private List<Color> listaColor;
    private List<Familia> listaFamilia;
    private List<Categoria> listaCategoria;
    private List<Categoria> listaCate;
    
    
    private List<Caracteristica> lista2; //para listar en la tabla caracteristicas

    public tipoitemMB() {
    }
    
    @PostConstruct
    public void init() {
        lista = new ArrayList<>();
        lista2= new ArrayList<>();
        setLista((List<TipoItemDTO>) new ArrayList());
        setLista(tipoItemBO.getAllTipoItem());
        setLista2(listarTablaCaracteristicas());
        listarCaracteristicas();
        listarCategoria();
        listarFamilia();
        listarMarca();
        listarColor();
        
        
    }
    public void onRowSelectTipoItem(SelectEvent event) {
        //tipoItemSelect = (TipoItemDTO) event.getObject();
        
    }
    ///
    public void listarCaracteristicas(){        
        listaCaracteristicas=tipoItemBO.getNombreCaracteristica();
    }    
    public void listarMarca(){
        listaMarca=tipoItemBO.getNombreMarca();
    }
    public void listarColor(){
        listaColor=tipoItemBO.getNombreColor();
    }    
    public void listarFamilia(){
       listaFamilia=tipoItemBO.getNombreFamilia();
    }    
    public void listarCategoria(){
        listaCategoria=tipoItemBO.getNombreCategoria();
    }
    
    ///
    public void buscarItem(ActionEvent actionEvent){        
        
        TipoItemDTO lis=new TipoItemDTO();
        lis.setIdtipoItem(codigoItem);
        lis.setNombre(nombre);        
        lis.setIdMarca(Integer.parseInt(marcaSelect));
        lista=tipoItemBO.buscarTipoItem(lis);
    }
    
    public List<Caracteristica> listarTablaCaracteristicas(){
        Caracteristica obj=new Caracteristica();
        
        obj.setNombre(caracteristicaSelect);
        lista2.add(obj);
       return lista2;
    }
    
    
    public void registrarItem(){
    
    RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('registrarItem').show();");
    
    }
    
    public void modificarItem(){
    
    RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('modificarItem').show();");
    
    }
    
    public void registrarNuevoTipoItem(ActionEvent e){
        TipoItemDTO objTipoItem=new TipoItemDTO();
        objTipoItem.setIdtipoItem(codigoItem);
        objTipoItem.setNumParte(numParte);
        objTipoItem.setNombre(nombre);
        objTipoItem.setDescipcion(descripcion);
        objTipoItem.setTipo(tipo);
        objTipoItem.setPrecioLista(Double.parseDouble(precio));
        objTipoItem.setDesCliente(dsctoCliente);
        objTipoItem.setDesDistribuidor(dsctoDistribuidor);
        
        objTipoItem.setIdFamilia(Integer.parseInt(familiaSelect));
        objTipoItem.setIdMarca(Integer.parseInt(marcaSelect));
        objTipoItem.setIdColor(Integer.parseInt(colorSelect));
        //objTipoItem.setIdCaracteristica(Integer.parseInt(caracteristicaSelect));
        tipoItemBO.registrarTipoItem(objTipoItem);
    }
    
    public void registrarNuevaMarca(ActionEvent e){
        TipoItemDTO objTipoItem=new TipoItemDTO();
        //objTipoItem.setId
        
    }
    
    public void registrarNuevaCaracteristica(ActionEvent e){
        
    }
    
    public void modificarTipoItem(ActionEvent e){
        TipoItemDTO objTipoItem=new TipoItemDTO();
        objTipoItem.setIdtipoItem(codigoItem);
        objTipoItem.setIdFamilia(Integer.parseInt(familiaSelect));        
        objTipoItem.setIdCaracteristica(Integer.parseInt(caracteristicaSelect));
        objTipoItem.setIdMarca(Integer.parseInt(marcaSelect));
        //objTipoItem.setDescripcionCaracteristica(descripcion);
        objTipoItem.setPrecioLista(Double.parseDouble(precio));
        objTipoItem.setDesCliente(dsctoCliente);
        objTipoItem.setDesDistribuidor(dsctoDistribuidor);
        tipoItemBO.modificarTipoItem(objTipoItem);
    }
    
    

    
    public String getNombre() {
        return nombre;
    }

    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getCodigoItem() {
        return codigoItem;
    }

    
    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    
    

    
    public List<TipoItemDTO> getLista() {
        return lista;
    }

    
    public void setLista(List<TipoItemDTO> lista) {
        this.lista = lista;
    }

    
    
    
    public List<Caracteristica> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    
    public void setListaCaracteristicas(List<Caracteristica> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    
    public String getCaracteristicaSelect() {
        return caracteristicaSelect;
    }

    
    public void setCaracteristicaSelect(String caracteristicaSelect) {
        this.caracteristicaSelect = caracteristicaSelect;
    }

    public String getCategoriaSelect() {
        return categoriaSelect;
    }

    public void setCategoriaSelect(String categoriaSelect) {
        this.categoriaSelect = categoriaSelect;
    }

    public String getFamiliaSelect() {
        return familiaSelect;
    }

    public void setFamiliaSelect(String familiaSelect) {
        this.familiaSelect = familiaSelect;
    }

    public String getMarcaSelect() {
        return marcaSelect;
    }

    public void setMarcaSelect(String marcaSelect) {
        this.marcaSelect = marcaSelect;
    }

    public List<Marca> getListaMarca() {
        return listaMarca;
    }

    public void setListaMarca(List<Marca> listaMarca) {
        this.listaMarca = listaMarca;
    }

    public List<Categoria> getListaCategoria() {
        return listaCategoria;
    }

    public void setListaCategoria(List<Categoria> listaCategoria) {
        this.listaCategoria = listaCategoria;
    }

    public List<Familia> getListaFamilia() {
        return listaFamilia;
    }

    public void setListaFamilia(List<Familia> listaFamilia) {
        this.listaFamilia = listaFamilia;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public double getDsctoCliente() {
        return dsctoCliente;
    }

    public void setDsctoCliente(double dsctoCliente) {
        this.dsctoCliente = dsctoCliente;
    }

    public double getDsctoDistribuidor() {
        return dsctoDistribuidor;
    }

    public void setDsctoDistribuidor(double dsctoDistribuidor) {
        this.dsctoDistribuidor = dsctoDistribuidor;
    }

    public TipoItemBO getTipoItemBO() {
        return tipoItemBO;
    }

    public void setTipoItemBO(TipoItemBO tipoItemBO) {
        this.tipoItemBO = tipoItemBO;
    }   

    public String getNumParte() {
        return numParte;
    }

    public void setNumParte(String numParte) {
        this.numParte = numParte;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getColorSelect() {
        return colorSelect;
    }

    public void setColorSelect(String colorSelect) {
        this.colorSelect = colorSelect;
    }

    public List<Color> getListaColor() {
        return listaColor;
    }

    public void setListaColor(List<Color> listaColor) {
        this.listaColor = listaColor;
    }

    public List<Caracteristica> getLista2() {
        return lista2;
    }

    public void setLista2(List<Caracteristica> lista2) {
        this.lista2 = lista2;
    }

    public List<Categoria> getListaCate() {
        return listaCate;
    }

    public void setListaCate(List<Categoria> listaCate) {
        this.listaCate = listaCate;
    }

    public TipoItemDTO getTipoItemSelect() {
        return tipoItemSelect;
    }

    public void setTipoItemSelect(TipoItemDTO tipoItemSelect) {
        this.tipoItemSelect = tipoItemSelect;
    }
    
    
    
    

    
    
}
