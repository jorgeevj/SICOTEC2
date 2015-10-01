package bo;

import dao.CaracteristicaFacade;
import dao.CategoriaFacade;
import dao.FamiliaFacade;
import dao.MarcaFacade;
import dao.TipoitemFacade;
import dto.CaracteristicaDTO;
import dto.TipoItemDTO;
import entidades.Caracteristica;
import entidades.Categoria;
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
    
    
    public List<TipoItemDTO> getBusqueda(TipoItemDTO t){
       
        Tipoitem r=convertDTOtoEntidad(t);
        List<Tipoitem> lista =tipoItemFacade.getAllBusqueda(r);
        List<TipoItemDTO> lista1=convertEntidadToDTO(lista);
        return lista1;
        
    }
    
    public List<Caracteristica> getNombreCaracteristica(){
        List<Caracteristica> lista = caracteristicaFacade.findAll();
        return lista;
    }
    
    public List<Marca> getNombreMarca(){
        List<Marca> lista=marcaFacade.findAll();
        return lista;
    }
    
    public List<Categoria> getNombreCategoria(){
        List<Categoria> lista= categoriaFacade.findAll();
        return lista;
    }
    
    public List<Familia> getNombreFamilia(){
        List<Familia> lista= familiaFacade.findAll();
        return lista;
    }
    
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
    
    public TipoItemDTO convertEntidadToDTO(Tipoitem ite){
        TipoItemDTO T=new TipoItemDTO();
            T.setNombre(ite.getNombre());
            T.setDesCliente(ite.getDesCliente());
            T.setDesDistribuidor(ite.getDesDistribuidor());
            T.setPrecioLista(ite.getPrecioLista());
            T.setTipo(ite.getTipo());
            return T;
    }
    
    public Tipoitem convertDTOtoEntidad(TipoItemDTO a){
        Tipoitem e=new Tipoitem();
        e.setNombre(a.getNombre());
        e.setDesCliente(a.getDesCliente());
        e.setDesDistribuidor(a.getDesDistribuidor());
        e.setPrecioLista(a.getPrecioLista());
        e.setTipo(a.getTipo());
        return e;
        
    }
}
