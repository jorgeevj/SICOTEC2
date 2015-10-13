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
    private String dsctoCliente;
    private String dsctoDistribuidor;
    
    private String caracteristicas;
    private String categoria;
    private String marca;
    
    private String caracteristicaSelect;
    private String categoriaSelect;
    private String familiaSelect;
    private String marcaSelect;
    private String colorSelect; 
    
    private List<TipoItemDTO> lista;
    private List<Caracteristica> listaCaracteristicas;
    private List<Marca> listaMarca;
    private List<Categoria> listaCategoria;
    private List<Familia> listaFamilia;
    private List<Color> listaColor;

    public tipoitemMB() {
    }
    
    @PostConstruct
    public void init(){ 
     lista=new ArrayList<>();
        setLista((List<TipoItemDTO>) new ArrayList());

     setLista(tipoItemBO.getAllTipoItem());
     listarCaracteristicas();
    listarCategoria();
    listarFamilia();
    listarMarca();
    }
    
    public void guardar(){
        
        System.out.println(getNombre()); 
        //ut.mostrarNotificacion(RequestContext.getCurrentInstance(), "holi", 1000);
    }
    
    public void buscar(ActionEvent actionEvent){        
        
        TipoItemDTO lis=new TipoItemDTO();
        lis.setIdtipoItem(codigoItem);
        lis.setNombre(nombre);
        
        
        lista=tipoItemBO.getBusqueda(lis);
    }
    
    public void listarCaracteristicas(){
        
        listaCaracteristicas=tipoItemBO.getNombreCaracteristica();
    }
    public void registrarItem(){
    
    RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('registrarItem').show();");
    
    }
    
    public void registrarNuevoTipoItem(ActionEvent e){
        TipoItemDTO objTipoItem=new TipoItemDTO();
        objTipoItem.setIdtipoItem(codigoItem);
        objTipoItem.setIdFamilia(Integer.parseInt(familiaSelect));        
        objTipoItem.setIdCaracteristica(Integer.parseInt(caracteristicaSelect));
        objTipoItem.setIdMarca(Integer.parseInt(marcaSelect));
        //objTipoItem.setDescripcionCaracteristica(descripcion);
        objTipoItem.setPrecioLista(Double.parseDouble(precio));
        objTipoItem.setDesCliente(dsctoCliente);
        objTipoItem.setDesDistribuidor(dsctoDistribuidor);
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
        tipoItemBO.actualizarTipoItem(objTipoItem);
    }
    
    public void listarMarca(){
        listaMarca=tipoItemBO.getNombreMarca();
    }
    
    public void listarFamilia(){
       listaFamilia=tipoItemBO.getNombreFamilia();
    }
    
    public void listarCategoria(){
        listaCategoria=tipoItemBO.getNombreCategoria();
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

    
    public String getCaracteristicas() {
        return caracteristicas;
    }

    
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    
    public String getCategoria() {
        return categoria;
    }

    
    public void setCategoria(String categoria) {
        this.categoria = categoria;
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

    public String getDsctoCliente() {
        return dsctoCliente;
    }

    public void setDsctoCliente(String dsctoCliente) {
        this.dsctoCliente = dsctoCliente;
    }

    public String getDsctoDistribuidor() {
        return dsctoDistribuidor;
    }

    public void setDsctoDistribuidor(String dsctoDistribuidor) {
        this.dsctoDistribuidor = dsctoDistribuidor;
    }

    public TipoItemBO getTipoItemBO() {
        return tipoItemBO;
    }

    public void setTipoItemBO(TipoItemBO tipoItemBO) {
        this.tipoItemBO = tipoItemBO;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
    
    
    
    

    
    
}
