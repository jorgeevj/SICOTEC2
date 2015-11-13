/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.UnidadesFacade;
import dto.UnidadDTO;
import entidades.Unidades;
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
public class UnidadBO {
    

    @EJB
    private UnidadesFacade unidadFacade = new UnidadesFacade();
    
    
    public List<UnidadDTO> getAllUnidad() {
        List<UnidadDTO> lista = new ArrayList<UnidadDTO>();
        List<Unidades> listaEntidad = unidadFacade.findAll();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<UnidadDTO> convertListEntityToDTO(List<Unidades> listaUnidades){
        List<UnidadDTO> listaDTO = new ArrayList<UnidadDTO>();
        for(Unidades unidad : listaUnidades){
            UnidadDTO DTO = new UnidadDTO();
            
            DTO = convertEntityToDTO(unidad);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
   
    public UnidadDTO convertEntityToDTO(Unidades unidad){
        UnidadDTO DTO = new UnidadDTO();
        DTO.setIdunidades(unidad.getIdunidades());
        DTO.setNombre(unidad.getNombre());
        DTO.setUnidades(unidad.getUnidades());
        
       return DTO;
    }

    public UnidadDTO findByIdUnidad(Integer idUMedida) {
    return convertEntityToDTO(unidadFacade.find(idUMedida));
    }

   
}
