/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import dto.AlmacenDTO;
import entidades.Almacen;
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
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
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
    public AlmacenDTO convertEntityToDTO(Almacen almacen){
        AlmacenDTO DTO = new AlmacenDTO();
            
        DTO.setIdalmacen(almacen.getIdalmacen());
        DTO.setNombre(almacen.getNombre());
        
        return DTO;
    }
       
     
}
