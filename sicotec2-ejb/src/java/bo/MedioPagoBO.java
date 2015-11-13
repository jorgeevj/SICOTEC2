/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.MediopagoFacade;
import dto.MedioPagoDTO;
import entidades.Mediopago;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class MedioPagoBO {
    @EJB
    MediopagoFacade medioPagoFacade;
    
    public List<MedioPagoDTO> allMedioPago(){
        List<MedioPagoDTO> listaDTO = new ArrayList<MedioPagoDTO>();
        listaDTO = this.convertListaEntityToDTO(medioPagoFacade.findAll());
        
        return listaDTO;
    }
    
    public List<MedioPagoDTO> convertListaEntityToDTO(List<Mediopago> listaEntidad){
        List<MedioPagoDTO> listaDTO = new ArrayList<MedioPagoDTO>();
        for(Mediopago entidad : listaEntidad){
            MedioPagoDTO dto = this.convertEntityToDTO(entidad);
            
            listaDTO.add(dto);
        }
        
        return listaDTO;
    }
    
    public MedioPagoDTO convertEntityToDTO(Mediopago entidad){
        MedioPagoDTO dto = new MedioPagoDTO();
        dto.setIdMedioPago(entidad.getIdmedioPago());
        dto.setNombre(entidad.getNombre());
        
        return dto;
    }
}
