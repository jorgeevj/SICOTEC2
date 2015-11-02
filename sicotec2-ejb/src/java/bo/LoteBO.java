/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.LoteFacade;
import dto.loteDTO;
import entidades.Lote;
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
public class LoteBO {
    
    @EJB
    LoteFacade loteFacade;
    
    public List<loteDTO> getLotesByCompra(int idCompra){
        List<loteDTO> lotesDTO = new ArrayList<loteDTO>();
        List<Lote> lotes = loteFacade.getLotesByCompra(idCompra);
        lotesDTO = convertListEntityToDTO(lotes);
        
        return lotesDTO;
    }
    
        public List<loteDTO> convertListEntityToDTO(List<Lote> listaItem){
        List<loteDTO> listaDTO = new ArrayList<loteDTO>();
        for(Lote movimiento : listaItem){
            loteDTO DTO = new loteDTO();
            
            DTO = convertEntityToDTO(movimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }

    public loteDTO convertEntityToDTO(Lote lote){
        loteDTO DTO = new loteDTO();
            
        DTO.setCantidad(lote.getCantidad());
        DTO.setIdLote(lote.getLotePK().getIdlote());
        DTO.setPrecioUni(lote.getPrecioUni());
        DTO.setNombreTipoItem(lote.getAltipoitem().getTipoitem().getNombre());
        DTO.setDescripcionTipoItem(lote.getAltipoitem().getTipoitem().getDescripcion());
        DTO.setNumParte(lote.getAltipoitem().getTipoitem().getNumParte());
        DTO.setIdtipoitem(lote.getAltipoitem().getTipoitem().getIdtipoItem());
        
        return DTO;
    }
}
