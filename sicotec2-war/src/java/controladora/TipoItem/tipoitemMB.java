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
import entidades.Familia;
import entidades.Marca;
import java.util.ArrayList;
import java.util.Date;
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
    
    private String nombre;
    private String codigoItem;
    private String caracteristicas;
    private String categoria;
    private String almacen;
    private Date fechaRegistro;
    private String caracteristicaSelect;
    private String categoriaSelect;
    private String familiaSelect;
    private String marcaSelect;
    
    
    private List<TipoItemDTO> lista;
    private List<Caracteristica> listaCaracteristicas;
    private List<Marca> listaMarca;
    private List<Categoria> listaCategoria;
    private List<Familia> listaFamilia;

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
        lis.setIdtipoItem(Integer.parseInt(codigoItem));
        lis.setNombre(nombre);
        lis.setDesCliente(categoria);
        lis.setDesDistribuidor(almacen);
        //setLista(tipoItemBO.getBusqueda(lis));
        lista=tipoItemBO.getBusqueda(lis);
    }
    
    public void listarCaracteristicas(){
        
        listaCaracteristicas=tipoItemBO.getNombreCaracteristica();
    }
    public void registrarItem(){
    
    RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('registrarItem').show();");
    
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

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the codigoItem
     */
    public String getCodigoItem() {
        return codigoItem;
    }

    /**
     * @param codigoItem the codigoItem to set
     */
    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }

    /**
     * @return the caracteristicas
     */
    public String getCaracteristicas() {
        return caracteristicas;
    }

    /**
     * @param caracteristicas the caracteristicas to set
     */
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the almacen
     */
    public String getAlmacen() {
        return almacen;
    }

    /**
     * @param almacen the almacen to set
     */
    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    /**
     * @return the fechaRegistro
     */
    
    /**
     * @return the lista
     */
    public List<TipoItemDTO> getLista() {
        return lista;
    }

    /**
     * @param lista the lista to set
     */
    public void setLista(List<TipoItemDTO> lista) {
        this.lista = lista;
    }

    /**
     * @return the fechaRegistro
     */
    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * @param fechaRegistro the fechaRegistro to set
     */
    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * @return the listaCaracteristicas
     */
    public List<Caracteristica> getListaCaracteristicas() {
        return listaCaracteristicas;
    }

    /**
     * @param listaCaracteristicas the listaCaracteristicas to set
     */
    public void setListaCaracteristicas(List<Caracteristica> listaCaracteristicas) {
        this.listaCaracteristicas = listaCaracteristicas;
    }

    /**
     * @return the caracteristicaSelect
     */
    public String getCaracteristicaSelect() {
        return caracteristicaSelect;
    }

    /**
     * @param caracteristicaSelect the caracteristicaSelect to set
     */
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

    
    
}
