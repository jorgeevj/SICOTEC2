/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.LoteFacade;
import dto.LoteDTO;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Compra;
import entidades.Lote;
import entidades.LotePK;
import entidades.Requerimientos;
import entidades.Unidades;
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
    
    public List<LoteDTO> getLotesByCompra(int idCompra){
        List<LoteDTO> lotesDTO = new ArrayList<LoteDTO>();
        List<Lote> lotes       = loteFacade.getLotesByCompra(idCompra);
        
        lotesDTO = convertListEntityToDTO(lotes);
        
        return lotesDTO;
    }
    
        public List<LoteDTO> convertListEntityToDTO(List<Lote> listaItem){
        List<LoteDTO> listaDTO = new ArrayList<LoteDTO>();
        for(Lote movimiento : listaItem){
            LoteDTO DTO = new LoteDTO();
            
            DTO = convertEntityToDTO(movimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }

    public LoteDTO convertEntityToDTO(Lote lote){
        LoteDTO DTO = new LoteDTO();
            
        DTO.setCantidadConvertida((lote.getCantidad()*lote.getIdunidades().getUnidades()));
        DTO.setCantidad(lote.getCantidad());
        DTO.setIdLote(lote.getLotePK().getIdlote());
        DTO.setPrecioUni(lote.getPrecioUni());
        DTO.setNombreTipoItem(lote.getAltipoitem().getTipoitem().getNombre());
        DTO.setDescripcionTipoItem(lote.getAltipoitem().getTipoitem().getDescripcion());
        DTO.setNumParte(lote.getAltipoitem().getTipoitem().getNumParte());
        DTO.setIdtipoitem(lote.getAltipoitem().getTipoitem().getIdtipoItem());
        DTO.setNombreTipoItem(lote.getAltipoitem().getTipoitem().getNombre());
        DTO.setIdUMedida(lote.getIdunidades().getIdunidades());
        DTO.setDescUMedida(lote.getIdunidades().getNombre());
        
        return DTO;
    }

    public Lote convertDTObyEntity(LoteDTO dto) {
        Lote l=new Lote();
        l.setAltipoitem(new Altipoitem(new AltipoitemPK(dto.getIdAlmacen(), dto.getIdtipoitem())));
        l.setCantidad(dto.getCantidad());
        l.setPrecioUni(dto.getPrecioUni());
        l.setIdunidades(new Unidades(dto.getUnidadDTO().getIdunidades()));
    return l;
    }
}
