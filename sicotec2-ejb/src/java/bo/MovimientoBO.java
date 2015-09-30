/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.MovimientoFacade;
import dao.TipomovimientoFacade;
import dao.UsuarioFacade;
import dto.MovimientoDTO;
import dto.TipomovimientoDTO;
import entidades.Movimiento;
import entidades.Tipomovimiento;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author rikardo308
 */
@Stateless
@LocalBean
public class MovimientoBO {

    @EJB
    private MovimientoFacade movimentoFacade = new MovimientoFacade();

    public List<MovimientoDTO> getAllMovimiento(){
        List<MovimientoDTO> lista = new ArrayList<MovimientoDTO>();
        List<Movimiento> listaEntidad = movimentoFacade.findAll();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<MovimientoDTO> convertListEntityToDTO(List<Movimiento> listaMovimiento){
        List<MovimientoDTO> listaDTO = new ArrayList<MovimientoDTO>();
        for(Movimiento movimiento : listaMovimiento){
            MovimientoDTO DTO = new MovimientoDTO();
            
            DTO = convertEntityToDTO(movimiento);
            
            listaDTO.add(DTO);
        }
        
        return listaDTO;
    }
    
    public MovimientoDTO convertEntityToDTO(Movimiento movimiento){
        MovimientoDTO DTO = new MovimientoDTO();
            
        DTO.setIdmovimiento(movimiento.getIdmovimiento());
        DTO.setComentario(movimiento.getComentario());
        DTO.setCorrelativo(movimiento.getCorrelativo());
        DTO.setFecha(movimiento.getFecha());
        DTO.setIdalmacenDestino(movimiento.getIdalmacenDestino());
        DTO.setIdalmacenOrigen(movimiento.getIdalmacenOrigen());
        DTO.setIddocumento(movimiento.getIddocumento());
        DTO.setMotivo(movimiento.getMotivo());
        DTO.setNombreDestino(movimiento.getNombreDestino());
        DTO.setNombreOrigen(movimiento.getNombreOrigen());
        DTO.setnSerie(movimiento.getSerie());
        DTO.setIdTipoMovimiento(movimiento.getIdtipoMovimiento().getIdtipoMovimiento());
        DTO.setTipoMovimiento(movimiento.getIdtipoMovimiento().getNombre());
        
        DTO.setEstado(movimiento.getEstado());
        if(movimiento.getEstado() == 0){
            DTO.setColorEstado("#F44236");//rojo
            DTO.setColorLetra("white");
        }else{
            DTO.setColorEstado("#2095F2");//azul
            DTO.setColorLetra("white");
        }
        return DTO;
    }
    
    
}
