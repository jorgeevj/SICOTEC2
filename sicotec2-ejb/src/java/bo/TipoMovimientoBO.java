/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.TipomovimientoFacade;
import dto.TipomovimientoDTO;
import entidades.Tipomovimiento;
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
public class TipoMovimientoBO {
    
    @EJB
    private TipomovimientoFacade tMovimentoFacade;
    
    public List<TipomovimientoDTO> getAllTipoMovimiento(){
        List<TipomovimientoDTO> lista = new ArrayList<TipomovimientoDTO>();
        List<Tipomovimiento> listaEntidad = tMovimentoFacade.findAll();
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<TipomovimientoDTO> convertListEntityToDTO(List<Tipomovimiento> listaTipoMovimiento){
        List<TipomovimientoDTO> listaDTO = new ArrayList<TipomovimientoDTO>();
        for(Tipomovimiento tMovimiento : listaTipoMovimiento){
            TipomovimientoDTO DTO = new TipomovimientoDTO();
            
            DTO = convertEntityToDTO(tMovimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public TipomovimientoDTO convertEntityToDTO(Tipomovimiento tipoMovimiento){
        TipomovimientoDTO DTO = new TipomovimientoDTO();
            
        DTO.setIdtipoMovimiento(tipoMovimiento.getIdtipoMovimiento());
        DTO.setNombre(tipoMovimiento.getNombre());

        return DTO;
    }
}
