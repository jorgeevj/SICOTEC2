package bo;

import dao.CaracteristicaFacade;
import dao.CategoriaFacade;
import dao.ColorFacade;
import dao.FamiliaFacade;
import dao.MarcaFacade;
import dao.TipoitemFacade;
import dto.CaracteristicaDTO;
import dto.TipoItemDTO;
import entidades.Caracteristica;
import entidades.Categoria;
import entidades.Color;
import entidades.Familia;
import entidades.Marca;
import entidades.Pealtipoitem;
import entidades.Tipoitem;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author Faviana
 */
@Stateless
@LocalBean
public class TipoItemBO {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @EJB
    private TipoitemFacade tipoItemFacade;
    @EJB
    private CaracteristicaFacade caracteristicaFacade;
    @EJB
    private MarcaFacade marcaFacade;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private FamiliaFacade familiaFacade;
    @EJB
    private ColorFacade colorFacade;
    
    
    ////
    
    
    public List<Caracteristica> getNombreCaracteristica(){
        List<Caracteristica> lista = caracteristicaFacade.findAll();
        return lista;
    }
    
    
    public List<Marca> getNombreMarca(){
        List<Marca> lista=marcaFacade.findAll();
        return lista;
    }
    
    public List<Color> getNombreColor(){
        List<Color> lista=colorFacade.findAll();
        return lista;
    }
    
     public List<Familia> getNombreFamilia(){
        List<Familia> lista= familiaFacade.findAll();
        return lista;
    }
    
    public List<Categoria> getNombreCategoria(){
        List<Categoria> lista= categoriaFacade.findAll();
        return lista;
    }
    public List<CaracteristicaDTO> getCaracteristicaPorIdItem(String a){
        Caracteristica obj=new Caracteristica();
        List<CaracteristicaDTO> listaDTO;
        List<Caracteristica> lista = tipoItemFacade.find(a).getCaracteristicaList();
//        caracteristicaFacade.getCaracteristicaXTipoItem(a); //lo comentamos por que no es necesario hacer un query
        listaDTO=convertEntidadtoDTOCaracteristica(lista);
        return listaDTO;
    }
    public Caracteristica getCaracteristiaXID(Integer e){
        Caracteristica obj;
        obj=caracteristicaFacade.find(e);
                return obj;
    }
    
    //////
    public boolean buscarTipoItemPorID(TipoItemDTO t){       
        boolean encontro=true;
        Tipoitem r=convertDTOtoEntidad(t,1);
        List<Tipoitem> lista =tipoItemFacade.getBusquedaDuplicados(r);
        if(lista.isEmpty())
        {
            encontro=false;
        }
        
        return encontro;          
    }
    public boolean buscarCaracteristicasPorNombre(String a){
        boolean encontro=false;
        List<Caracteristica> lista = caracteristicaFacade.findAll();        
        for(Caracteristica ite:lista){
            if(ite.getNombre().equalsIgnoreCase(a) )
                encontro=true;            
        }
        
        return encontro; 
        
    }
    public boolean buscarMarcaPorNombre(String a){
        boolean encontro=false;
        List<Marca> lista = marcaFacade.findAll();        
        for(Marca ite:lista){
            if(ite.getNombre().equalsIgnoreCase(a) )
                encontro=true;            
        }
        
        return encontro; 
        
    }
    public List<TipoItemDTO> buscarTipoItem(TipoItemDTO t){       
        Tipoitem r=convertDTOtoEntidad(t,1);
        List<Tipoitem> lista =tipoItemFacade.getAllBusqueda(r);
        List<TipoItemDTO> lista1=convertEntidadToDTO(lista);
        return lista1;      
    }    
    public void registrarTipoItem(TipoItemDTO t,List<Caracteristica> c){ //hacemos que reciba la lista de caracteristicas
        Tipoitem ti=convertDTOtoEntidad(t,1);
        ti.setCaracteristicaList(c);//aqui seteamos la lista para que tambien persista junto a tipoitem
        tipoItemFacade.create(ti); 
    }    
    public void modificarTipoItem(TipoItemDTO t,List<Caracteristica> c){
        Tipoitem ti=convertDTOtoEntidad(t,2);
        ti.setCaracteristicaList(c);//aqui seteamos la lista para que tambien persista junto a tipoitem
        tipoItemFacade.edit(ti);
    }
    
    public void registrarCaracteristica(TipoItemDTO t){
        caracteristicaFacade.create(convertDTOtoEntidadCaracteristica(t));
    }
    public void registrarTipoItemXCaracteristica(TipoItemDTO t){
        caracteristicaFacade.create(covertDTOtoEntidadTipoItemXCaracteristica(t));
    }
    
    public void registrarMarca(TipoItemDTO t){
        marcaFacade.create(convertDTOtoEntidadMarca(t));
    }
    /////
    
    public List<TipoItemDTO> getAllTipoItem(){
        
        List<Tipoitem> lista = tipoItemFacade.findAll();
        List<TipoItemDTO> lista2 = new ArrayList<TipoItemDTO>();  
        
        lista2 = convertEntidadToDTO(lista);
       
        return lista2;
    }
    
    public List<TipoItemDTO> convertEntidadToDTO(List<Tipoitem> lista){
        List<TipoItemDTO> lista3= new ArrayList<TipoItemDTO>();
        
         for(Tipoitem ite: lista){
            
            TipoItemDTO T=new TipoItemDTO();
            T=convertEntidadToDTO(ite);
            lista3.add(T);
        }
         return lista3;
    }
    
    public List<CaracteristicaDTO> convertEntidadtoDTOCaracteristica(List<Caracteristica> lista){
        List<CaracteristicaDTO> lista3=new ArrayList<CaracteristicaDTO>();
        
        for(Caracteristica ite: lista){
            
            CaracteristicaDTO T=new CaracteristicaDTO();
            T=convertEntidadtoDTOCara(ite);
            lista3.add(T);
        }
         return lista3;
    }   
    
    public List<Caracteristica> convertirDTOtoCaracteristicaLista(List<CaracteristicaDTO> lista){
        List<Caracteristica> lista4=new ArrayList<Caracteristica>();
        
        for(CaracteristicaDTO ite: lista){
            Caracteristica T=new Caracteristica();
            T=convertDTOtoEtidadCara(ite);
            lista4.add(T);
        }
        return lista4;
        
    }
    
    public Caracteristica convertDTOtoEtidadCara(CaracteristicaDTO a){
        Caracteristica e=new Caracteristica();  
        e.setIdcaracteristica(a.getIdCaracteristica());
        e.setNombre(a.getNombreCaracteristica());
        e.setDescripcion(a.getDescripcion());
        return e;
        
    }
    public Caracteristica convertDTOtoEntidadCaracteristica(TipoItemDTO b){
        Caracteristica e=new Caracteristica();        
        e.setNombre(b.getCaracteristica().getNombre());
        e.setDescripcion(b.getCaracteristica().getDescripcion());
        return e;
    }
    
    public CaracteristicaDTO convertEntidadtoDTOCara(Caracteristica b){
        CaracteristicaDTO e=new CaracteristicaDTO();
        e.setIdCaracteristica(b.getIdcaracteristica());
        e.setNombreCaracteristica(b.getNombre());
        e.setDescripcion(b.getDescripcion());
        
        return e;
    }
    public Caracteristica covertDTOtoEntidadTipoItemXCaracteristica(TipoItemDTO b){
        Caracteristica e=new Caracteristica();        
        e.setTipoitemList(b.getCaracteristica().getTipoitemList());
        return e;
        
    }
    public Marca convertDTOtoEntidadMarca(TipoItemDTO c){
        Marca e=new Marca();        
        e.setNombre(c.getMarca().getNombre());
        e.setImagen(c.getMarca().getImagen());
        e.setFormato(c.getMarca().getFormato());
        return e;
    }
    
    //1 = insertar o buscar; 2 = editar
    public Tipoitem convertDTOtoEntidad(TipoItemDTO a, int opc){
        Tipoitem e = new Tipoitem(); 
            
            e.setIdtipoItem(a.getIdtipoItem());
            
            e.setNumParte(a.getNumParte());
            e.setNombre(a.getNombre());
            e.setDescripcion(a.getDescipcion());
            e.setTipo(a.getTipo());
            e.setPrecioLista(a.getPrecioLista());
            e.setDesCliente(a.getDesCliente());
            e.setDesDistribuidor(a.getDesDistribuidor());

            e.setIdfamilia(new Familia());
            e.getIdfamilia().setIdfamilia(a.getIdFamilia());
            e.setIdmarca(new Marca());
            e.getIdmarca().setIdmarca(a.getIdMarca());
            e.setIdcolor(new Color());
            e.getIdcolor().setIdcolor(a.getIdColor()); 
        return e;
    }
    public TipoItemDTO convertEntidadToDTO(Tipoitem ite){
        TipoItemDTO T=new TipoItemDTO();            
            T.setIdtipoItem(ite.getIdtipoItem());
            T.setNumParte(ite.getNumParte());
            T.setNombre(ite.getNombre());
            T.setDescipcion(ite.getDescripcion());
            T.setTipo(ite.getTipo());
            T.setPrecioLista(ite.getPrecioLista());
            T.setDesCliente(ite.getDesCliente());
            T.setDesDistribuidor(ite.getDesDistribuidor());
            
            T.setFamilia(ite.getIdfamilia());
            T.setCategoria(ite.getIdfamilia().getIdcategoria());
            T.setIdCategoria(ite.getIdfamilia().getIdcategoria().getIdcategoria());
            T.setMarca(ite.getIdmarca());
            T.setColor(ite.getIdcolor());
            return T;
    }

    /*public List<TipoItemDTO> getAllTipoItemByPedido(){
        List<Tipoitem> listaEntidad = tipoItemFacade.getTipoItemByPedido();
        List<TipoItemDTO> listaDTO = new ArrayList<TipoItemDTO>();
        
        return listaDTO;
    }*/
}
