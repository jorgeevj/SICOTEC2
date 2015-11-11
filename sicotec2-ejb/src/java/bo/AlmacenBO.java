/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dao.DocumentoFacade;
import dto.AlmacenDTO;
import entidades.Almacen;
import entidades.Docalmacen;
import entidades.Documento;
import entidades.Pealtipoitem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author Jorge
 */
    @Stateless
@LocalBean
public class AlmacenBO {
    @EJB
    private AlmacenFacade almacenFacade;
    @EJB
    private DocumentoFacade documentoFacade;
    
    
    public List<Documento> getNombreDocumento(){
        List<Documento> lista=documentoFacade.findAll();
        return lista;
    }

    public List<Almacen> findAll() {
        List<Almacen> listaAlmacen=almacenFacade.findAll();
        List<SelectItem> listaItemAlmacen=new ArrayList<>();
        for (Almacen la : listaAlmacen) {
            listaItemAlmacen.add(new SelectItem(la.getIdalmacen(), la.getNombre()));
        }
    return almacenFacade.findAll();
    }
    
    public List<AlmacenDTO> getAllAlmaces(){
        List<AlmacenDTO> lista = new ArrayList<AlmacenDTO>();
        List<Almacen> listaEntidad= almacenFacade.findAll();
        lista = convertEntidadToDTO(listaEntidad);
        return lista;
    }
    
    public Documento getDocumentoXID(Integer e){
        Documento obj;
        obj=documentoFacade.find(e);
                return obj;
    }
    
    public List<AlmacenDTO> buscarAlmacen(AlmacenDTO t){
        Almacen r=convertDTOtoEntidad(t);
        List<Almacen> lista=almacenFacade.getAllBusqueda(r);
        List<AlmacenDTO> lista1=convertEntidadToDTO(lista);
        return lista1;
    }
    
    public List<AlmacenDTO> convertEntidadToDTO(List<Almacen> lista){
        List<AlmacenDTO> lista3= new ArrayList<AlmacenDTO>();
        
         for(Almacen ite: lista){
            
            AlmacenDTO T=new AlmacenDTO();
            T=convertEntidadtoDTO(ite);
            lista3.add(T);
        }
         return lista3;
    }
    public void registrarAlmacen(AlmacenDTO t,List<Docalmacen> c){ 
        Almacen ti=convertDTOtoEntidad(t);
        ti.setDocalmacenList(c);
        almacenFacade.create(ti); 
    } 
    
     public void modificarAlmacen(AlmacenDTO t,List<Docalmacen> c){ 
        Almacen ti=convertDTOtoEntidad(t);
        ti.setDocalmacenList(c);
        almacenFacade.edit(ti); 
    } 
    public List<AlmacenDTO> convertListEntityToDTO(List<Almacen> listaMovimiento){
        List<AlmacenDTO> listaDTO = new ArrayList<AlmacenDTO>();
        for(Almacen almacen : listaMovimiento){
            AlmacenDTO DTO = new AlmacenDTO();
            
            DTO = convertEntityToDTO(almacen);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
 public Almacen convertDTOtoEntidad(AlmacenDTO a){
        Almacen e=new Almacen();  
          e.setIdalmacen(a.getIdalmacen());
          e.setDireccion(a.getDireccion());
          e.setTelefono(a.getTelefono());
          e.setNombre(a.getNombre());
          e.setCodDist(a.getCodDist());
          e.setCodDept(a.getCodDept());
          e.setCodProv(a.getCodProv());  
          
            
        return e;
    }
 
 public AlmacenDTO convertEntidadtoDTO(Almacen a){
        AlmacenDTO e=new AlmacenDTO(); 
          e.setIdalmacen(a.getIdalmacen());
          e.setDireccion(a.getDireccion());
          e.setTelefono(a.getTelefono());
          e.setNombre(a.getNombre());
          e.setCodDist(a.getCodDist());
          e.setCodDept(a.getCodDept());
          e.setCodProv(a.getCodProv());  
        return e;
    }
 
    public AlmacenDTO convertEntityToDTO(Almacen almacen){
        AlmacenDTO DTO = new AlmacenDTO();
            
        DTO.setIdalmacen(almacen.getIdalmacen());
        DTO.setNombre(almacen.getNombre());
        
        return DTO;
    }
       
     
}
