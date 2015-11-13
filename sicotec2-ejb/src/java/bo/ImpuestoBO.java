/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.ImpuestoFacade;
import dto.ImpuestoDTO;
import entidades.Impuesto;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class ImpuestoBO {
    ImpuestoFacade impuestoFacade;
    public List<ImpuestoDTO> getAllImpuestos(){
        List<ImpuestoDTO> listaDTO = new ArrayList<ImpuestoDTO>();
        listaDTO = this.convertListaEntityToDTO(impuestoFacade.findAll());
        
        return listaDTO;
    }
    
    public List<ImpuestoDTO> convertListaEntityToDTO(List<Impuesto> listaEntidad){
        List<ImpuestoDTO> listaDTO = new ArrayList<ImpuestoDTO>();
        for(Impuesto entidad : listaEntidad){
            ImpuestoDTO dto = this.convertEntityToDTO(entidad);
            
            listaDTO.add(dto);
        }
        
        return listaDTO;
    }
    
    public ImpuestoDTO convertEntityToDTO(Impuesto entidad){
        ImpuestoDTO dto = new ImpuestoDTO();
        dto.setIdImpuesto(entidad.getIdimpuesto());
        dto.setNombre(entidad.getNombre());
        dto.setPorcentaje(entidad.getProcentaje());
        
        return dto;
    }
}
