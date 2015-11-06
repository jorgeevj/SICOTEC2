/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora.TipoItem;

import bo.TipoItemBO;
import dto.CaracteristicaDTO;
import dto.TipoItemDTO;
import entidades.Caracteristica;
import entidades.Categoria;
import entidades.Color;
import entidades.Familia;
import entidades.Marca;
import entidades.Tipoitem;
import java.util.ArrayList;
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
    
    
    private String nombreCaracteristica;
    private String descripcionCaracteristica;
    
    private String codigoMarca;
    private String nombreMarca;
    private String imagenMarca; //modificar para guardar tipo img
    private String formatoMarca;
    
    private String caracteristicaSelect;
    private String marcaSelect;
    private String colorSelect; 
    private String familiaSelect;
    private String categoriaSelect; //posible no uso
    
    private boolean btnEditarEstado;
    private boolean btnQuitarEstado;
    
    private List<TipoItemDTO> lista;
    private TipoItemDTO tipoItemSelect;
    private Caracteristica caracteristicasTablaSelect;//aqui se hizo el cambio de objeto
    private List<Caracteristica> listaCaracteristicas;
    private List<Marca> listaMarca;
    private List<Color> listaColor;
    private List<Familia> listaFamilia;
    private List<Categoria> listaCategoria;
    private List<Categoria> listaCate;
    
    
    private List<Caracteristica> lista2; //para listar en la tabla caracteristicas
    private List<CaracteristicaDTO> lista3;
    private List<Caracteristica> lista4;

    public tipoitemMB() {
    }
    
    @PostConstruct
    public void init() {
        lista = new ArrayList<>();
        lista2= new ArrayList<>();
        lista3= new ArrayList<>();
        setLista(tipoItemBO.getAllTipoItem());
        tipoItemSelect=new TipoItemDTO();
        tipoItemSelect.setFamilia(new Familia());
        tipoItemSelect.setCategoria(new Categoria());
        tipoItemSelect.setColor(new Color());
        tipoItemSelect.setMarca(new Marca());
        tipoItemSelect.setCaracteristica(new Caracteristica());
        //setLista2(listarTablaCaracteristicas());
        listarCaracteristicas();
        listarCategoria();
        listarFamilia();
        listarMarca();
        listarColor();
        btnEditarEstado = true;
        btnQuitarEstado = true;
        
        
    }
    
    ///
    public void listarCaracteristicas(){        
        listaCaracteristicas=tipoItemBO.getNombreCaracteristica();
        for(Caracteristica obj:lista2){
            for(Caracteristica ite: listaCaracteristicas){
                if(ite.getIdcaracteristica()==obj.getIdcaracteristica())
                {
                    listaCaracteristicas.remove(ite);
                    break;
                }
            }
        }
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
    public void onRowSelectTipoItem(SelectEvent event) {
        btnEditarEstado = false;
        
    } 
    
    public void onRowSelectCaracteristica(SelectEvent event) {
        btnQuitarEstado = false;
        
    }
    public String validarRegistroCaracteristicas(){
        String msjError="";
        if(buscarPorNomCara())
            msjError="El nombre de la caracteristica ya existe";
        return msjError;
    }
    public String validarRegistroMarca(){
        String msjError="";
        if(buscarPorNomMarca())
            msjError="El nombre de la marca ya existe";
        return msjError;
    }
    public String validarRegistro(){
        String msjError="";
        
        if(getCodigoItem().equals("") || getCodigoItem()==null)
            msjError="Ingrese Codigo del item";
        if(buscarPorIDItem())
            msjError="Codigo del item ya existe";
        if(getNombre().equals("") || getNombre()==null)
            msjError="Ingrese nombre del item";        
        else if(getPrecio()==null)
            msjError="Precio del Item debe ser numero";
        else if(Integer.parseInt(getPrecio())==0.0)
            msjError="Ingrese un precio del item mayor que cero";
        else if(Double.parseDouble(getPrecio())<0.0)
            msjError="Precio del Item no puede ser negativo";
        else if(getLista2().isEmpty())
            msjError="eliga Caracteristicas";
        else if(getNumParte().equals("") || getNumParte().equals(0))
            msjError="Ingrese un numero de parte";
        else if(getDescripcion()==null || getDescripcion().equals(""))
            msjError="Ingrese una descripcion del item";
        else if(Integer.parseInt(getColorSelect())==0)
            msjError="Seleccione un color";
        else if(Integer.parseInt(getFamiliaSelect())==0 )
            msjError="Seleccione una familia";
        else if(Integer.parseInt(getMarcaSelect())==0 )
            msjError="Seleccione una marca";
        
        else if(getDsctoCliente() < 0.0)
            msjError="el dscto Cliente no puede ser negativo";
        
        else if(getDsctoDistribuidor() < 0.0)
            msjError="el dscto Distribuidor no puede ser negativo";
        else if(getTipo().equals("") || getTipo()==null)
            msjError="Ingrese un Tipo";
        
                                 
          return msjError;  
    }
    
    public String validarEditar(){
        String msjError="";
        if(tipoItemSelect.getNombre().equals("") || tipoItemSelect.getNombre().equals(null))
            msjError="Ingrese nombre del item";
        else if(tipoItemSelect.getPrecioLista()==null)
            msjError="Ingrese precio del item";
        else if(tipoItemSelect.getPrecioLista()<0.0 )
            msjError="El precio del item no puede ser negativo";        
        else if(getLista2().isEmpty())
            msjError="eliga Caracteristicas";
        else if(tipoItemSelect.getNumParte()==null|| tipoItemSelect.getNumParte().equals(""))
            msjError="Ingrese un numero de parte";
        else if(tipoItemSelect.getDescipcion()==null || tipoItemSelect.getDescipcion().equals(""))
            msjError="Ingrese una descripcion del item";
        else if(tipoItemSelect.getColor().getIdcolor()==0)
            msjError="Seleccione un color";
        else if( tipoItemSelect.getMarca().getIdmarca()==0)
            msjError="Seleccione una marca";
        else if( tipoItemSelect.getFamilia().getIdfamilia()==0)
            msjError="Seleccione una familia";
        else if(tipoItemSelect.getDesCliente()==null)
            msjError="Ingrese un dscto Cliente";
        else if(tipoItemSelect.getDesCliente()< 0.0)
            msjError="El dscto Cliente no puede ser negativo";
        else if(tipoItemSelect.getDesDistribuidor()==null)
            msjError="Ingrese un dscto Distribuidor";
        else if(tipoItemSelect.getDesDistribuidor() < 0.0 )
            msjError="El dscto Distribuidor no puede ser negativo";
        
        else if(tipoItemSelect.getTipo().equals("") || tipoItemSelect.getTipo().equals(null))
            msjError="Ingrese un Tipo";
        else
        {
            try{ double precio=tipoItemSelect.getPrecioLista(); }catch(Exception e){ msjError="Precio debe ser numerico";}
        }
                                 
          return msjError;  
    }
    ///
    
    public void buscarItem(ActionEvent actionEvent){        
        
        TipoItemDTO lis=new TipoItemDTO();
        lis.setIdtipoItem(codigoItem);
        lis.setNombre(nombre);        
        lis.setIdMarca(Integer.parseInt(marcaSelect));
        lista=tipoItemBO.buscarTipoItem(lis);
    }
    
    public boolean buscarPorIDItem(){
        TipoItemDTO lis=new TipoItemDTO();
        lis.setIdtipoItem(codigoItem);
        boolean encontro=tipoItemBO.buscarTipoItemPorID(lis);
        return encontro;
    }
    
    public boolean buscarPorNomCara(){
        boolean encontro=tipoItemBO.buscarCaracteristicasPorNombre(nombreCaracteristica);
        return encontro;
    }
    
    public boolean buscarPorNomMarca(){
        boolean encontro=tipoItemBO.buscarMarcaPorNombre(nombreMarca);
                return encontro;
    }
    public List<Caracteristica> listarTablaCaracteristicas(ActionEvent actionEvent){
        Caracteristica obj=new Caracteristica();
        
        obj=tipoItemBO.getCaracteristiaXID(Integer.parseInt(caracteristicaSelect));
        lista2.add(obj);
        for(Caracteristica ite: listaCaracteristicas){
            if(ite.getIdcaracteristica()==obj.getIdcaracteristica())
            {
                listaCaracteristicas.remove(ite);
                break;
            }
        }
        
       return lista2;
    }
    
    public List<Caracteristica> listarTabla3Caracteristicas(ActionEvent actionEvent){
        Caracteristica obj=new Caracteristica();
        
        obj=tipoItemBO.getCaracteristiaXID(Integer.parseInt(caracteristicaSelect));
        lista2.add(obj);
        for(Caracteristica ite: listaCaracteristicas){
            if(ite.getIdcaracteristica()==obj.getIdcaracteristica())
            {
                listaCaracteristicas.remove(ite);
                break;
            }
        }
        
       return lista2;
    }
    
    
    
    
    
    public List<Caracteristica> quitarElementosTablaCaracteristicas(ActionEvent actionEvent){
        //el error no esta en el metodo sino en el de objeto que colocarte en la vista al seleccionar de la tabala
        //solo cambiaremos el tipo de objeto de DTO a Entidad de caracteristicasTablaSelect
        Caracteristica obj;
        obj=tipoItemBO.getCaracteristiaXID(caracteristicasTablaSelect.getIdcaracteristica());
        listaCaracteristicas.add(obj);
        
        for(Caracteristica ite1: lista2){
            if(ite1.getIdcaracteristica()==obj.getIdcaracteristica())
            {
                lista2.remove(ite1);
                break;
            }
        }
        
        return lista2;
       
    }
    
    
    public void registrarItem(){
    
    RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('registrarItem').show();");
    init();
    
    }
    
    public void modificarItem(){
    lista2= new ArrayList<>();
    lista3=tipoItemBO.getCaracteristicaPorIdItem(tipoItemSelect.getIdtipoItem());
    lista2=tipoItemBO.convertirDTOtoCaracteristicaLista(lista3);
    listarCaracteristicas();
    btnQuitarEstado = true;
    RequestContext context = RequestContext.getCurrentInstance(); 
    context.execute("PF('modificarItem').show();");
    
    
    }
    
    public void registrarCaracteristica(){
        RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('crearCaraItem').show();");
    }
    
    public void registrarCaracteristicaMod(){
        RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('crearCaraItemMod').show();");
    }
    
    public void registrarMarca(){
        RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('crearMarcaItem').show();");
    }
    
    public void registrarMarcaMod(){
        RequestContext context = RequestContext.getCurrentInstance(); 
    
    context.execute("PF('crearMarcaItemMod').show();");
    }
    
    
    
    public void registrarNuevoTipoItem(ActionEvent e){
        String sms = validarRegistro();
        if(sms != ""){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sms, "!!!"));
        }else{
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

            //hasta aqui bien ahora debes meter tambien la lista de caracteristicas dentro de la entidad tipoiten
            //el DTO al igual que una entidad debia de contener la lista de caracteristicas
            //pero como tu DTO lo creaste sin esa lista 
            //(ojo revisa bien la entidad que atributos tiene para que no te falte ninguno al crear el dto)
            //lo que hare es meterle otro parametro a tu metodo
            tipoItemBO.registrarTipoItem(objTipoItem,lista2);// este es el metodo que debes usar para todo el registro 
            //comentamos este metodo---> registrarTipoItemXCaracteristica();
            RequestContext context = RequestContext.getCurrentInstance();   
            context.execute("PF('registrarItem').hide();");
            
            btnQuitarEstado = true;
        }
    }
    
    public void registrarNuevaMarca(ActionEvent e){
        String sms=validarRegistroMarca();
        if(sms!=""){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sms, "!!!"));
        }else{
            TipoItemDTO objTipoItem=new TipoItemDTO();
            objTipoItem.setMarca(new Marca());        
            objTipoItem.getMarca().setNombre(nombreMarca);
            //objTipoItem.getMarca().setImagen(imagenMarca);
            objTipoItem.getMarca().setFormato(nombre);
            tipoItemBO.registrarMarca(objTipoItem);
            listarMarca();
            RequestContext context = RequestContext.getCurrentInstance();   
            context.execute("PF('crearMarcaItem').hide();");
            RequestContext context2 = RequestContext.getCurrentInstance();   
            context2.execute("PF('crearMarcaItemMod').hide();");
        }
        
        
    }
    
    public void registrarNuevaCaracteristica(ActionEvent e){
        String sms=validarRegistroCaracteristicas();
        if(sms!=""){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sms, "!!!"));
        }else{
        TipoItemDTO objTipoItem=new TipoItemDTO();
        objTipoItem.setCaracteristica(new Caracteristica());       
        objTipoItem.getCaracteristica().setNombre(nombreCaracteristica);
        objTipoItem.getCaracteristica().setDescripcion(descripcionCaracteristica);
        tipoItemBO.registrarCaracteristica(objTipoItem);
        listarCaracteristicas();
        RequestContext context = RequestContext.getCurrentInstance();   
            context.execute("PF('crearCaraItem').hide();");
        RequestContext context2 = RequestContext.getCurrentInstance();   
        context2.execute("PF('crearCaraItemMod').hide();");
        
        }
    }
    
    public void registrarTipoItemXCaracteristica(){//este metodo esta mal no lo usare
        
        TipoItemDTO objTipoItem=new TipoItemDTO();   
        List<Tipoitem> lista4=new ArrayList<Tipoitem>(); // la lista que necessitas llenar es de tipo "Caracteristica"   no tipoItem     
        
            Tipoitem obj1=new Tipoitem();
            obj1.setIdtipoItem(codigoItem);            
            obj1.setCaracteristicaList(lista2);
            lista4.add(obj1);
            objTipoItem.setCaracteristica(new Caracteristica());            
            objTipoItem.getCaracteristica().setTipoitemList(lista4);
            tipoItemBO.registrarTipoItemXCaracteristica(objTipoItem);
        
        
        
    }
    
    public void modificarTipoItem(ActionEvent e){
        String sms = validarEditar() ;
        if(sms != ""){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, sms, "!!!"));
        }else{
        TipoItemDTO objTipoItem=new TipoItemDTO();
        objTipoItem.setIdtipoItem(tipoItemSelect.getIdtipoItem());
        objTipoItem.setNumParte(tipoItemSelect.getNumParte());
        objTipoItem.setNombre(tipoItemSelect.getNombre());
        objTipoItem.setDescipcion(tipoItemSelect.getDescipcion());
        objTipoItem.setTipo(tipoItemSelect.getTipo());
        objTipoItem.setPrecioLista(tipoItemSelect.getPrecioLista());
        objTipoItem.setDesCliente(tipoItemSelect.getDesCliente());
        objTipoItem.setDesDistribuidor(tipoItemSelect.getDesDistribuidor());

        objTipoItem.setIdFamilia(tipoItemSelect.getFamilia().getIdfamilia());
        objTipoItem.setIdMarca(tipoItemSelect.getMarca().getIdmarca());
        objTipoItem.setIdColor(tipoItemSelect.getColor().getIdcolor());
        tipoItemBO.modificarTipoItem(objTipoItem,lista2);
        RequestContext context = RequestContext.getCurrentInstance();   
        context.execute("PF('modificarItem').hide();");
        btnQuitarEstado = true;
        }
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

    

    public String getNombreCaracteristica() {
        return nombreCaracteristica;
    }

    public void setNombreCaracteristica(String nombreCaracteristica) {
        this.nombreCaracteristica = nombreCaracteristica;
    }

    public String getDescripcionCaracteristica() {
        return descripcionCaracteristica;
    }

    public void setDescripcionCaracteristica(String descripcionCaracteristica) {
        this.descripcionCaracteristica = descripcionCaracteristica;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getImagenMarca() {
        return imagenMarca;
    }

    public void setImagenMarca(String imagenMarca) {
        this.imagenMarca = imagenMarca;
    }

    public String getFormatoMarca() {
        return formatoMarca;
    }

    public void setFormatoMarca(String formatoMarca) {
        this.formatoMarca = formatoMarca;
    }

    

    public boolean isBtnEditarEstado() {
        return btnEditarEstado;
    }

    public void setBtnEditarEstado(boolean btnEditarEstado) {
        this.btnEditarEstado = btnEditarEstado;
    }

    public Caracteristica getCaracteristicasTablaSelect() {
        return caracteristicasTablaSelect;
    }

    public void setCaracteristicasTablaSelect(Caracteristica caracteristicasTablaSelect) {
        this.caracteristicasTablaSelect = caracteristicasTablaSelect;
    }

    public List<CaracteristicaDTO> getLista3() {
        return lista3;
    }

    public void setLista3(List<CaracteristicaDTO> lista3) {
        this.lista3 = lista3;
    }

    public List<Caracteristica> getLista4() {
        return lista4;
    }

    public void setLista4(List<Caracteristica> lista4) {
        this.lista4 = lista4;
    }

    public boolean isBtnQuitarEstado() {
        return btnQuitarEstado;
    }

    public void setBtnQuitarEstado(boolean btnQuitarEstado) {
        this.btnQuitarEstado = btnQuitarEstado;
    }
    
    
    
    

    
    
}
