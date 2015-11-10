/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.RequerimientosFacade;
import dto.RequerimientoDTO;
import entidades.Requerimientos;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author jc7
 */
@Stateless
@LocalBean
public class RequerimientoBO {
    
    @EJB
    private RequerimientosFacade requerimientosFacade;
    
    public List<RequerimientoDTO> getAllRequerimientos() {
        List<RequerimientoDTO> lista = new ArrayList<RequerimientoDTO>();
        List<Requerimientos> listaEntidad = requerimientosFacade.findAll();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<RequerimientoDTO> convertListEntityToDTO(List<Requerimientos> listaRequerimientos){
        List<RequerimientoDTO> listaDTO = new ArrayList<RequerimientoDTO>();
        for(Requerimientos unidad : listaRequerimientos){
            RequerimientoDTO DTO = new RequerimientoDTO();
            
            DTO = convertEntityToDTO(unidad);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
   
    public RequerimientoDTO convertEntityToDTO(Requerimientos requerimiento){
        RequerimientoDTO DTO = new RequerimientoDTO();
        DTO.setIdrequerimientos(requerimiento.getIdrequerimientos());
        DTO.setCantidad(requerimiento.getCantidad());
      
       return DTO;
    }
    
    public Requerimientos convertDTOtoEntity(RequerimientoDTO dto, int tipo){
        Requerimientos entidad = new Requerimientos();
        if(tipo == 0){
            entidad.setIdrequerimientos(dto.getIdrequerimientos());
           
        }
           entidad.setCantidad(dto.getCantidad());
        return entidad;
    }
    public Requerimientos insertarRequerimiento(RequerimientoDTO dto ){
        Requerimientos entidad = convertDTOtoEntity(dto , 1);
       
        entidad = requerimientosFacade.agregarRequerimiento(entidad);
        return entidad;
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
