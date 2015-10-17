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
    
    //////
    public List<TipoItemDTO> buscarTipoItem(TipoItemDTO t){       
        Tipoitem r=convertDTOtoEntidad(t);
        List<Tipoitem> lista =tipoItemFacade.getAllBusqueda(r);
        List<TipoItemDTO> lista1=convertEntidadToDTO(lista);
        return lista1;      
    }    
    public void registrarTipoItem(TipoItemDTO t){        
        Tipoitem e=new Tipoitem();
         e=convertDTOtoEntidad(t);        
        tipoItemFacade.create(e);        
    }    
    public void modificarTipoItem(TipoItemDTO t){
        Tipoitem r=convertDTOtoEntidad(t);        
        tipoItemFacade.edit(r);
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
    public Tipoitem convertDTOtoEntidad(TipoItemDTO a){
        Tipoitem e=new Tipoitem();        
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
    
    
}
