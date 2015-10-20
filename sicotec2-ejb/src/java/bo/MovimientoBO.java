/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.ItemFacade;
import dao.MovimientoFacade;
import dao.MovimientoitemFacade;
import dao.TipomovimientoFacade;
import dao.UsuarioFacade;
import dto.ItemDTO;
import dto.MovimientoDTO;
import dto.MovimientoitemDTO;
import dto.TipomovimientoDTO;
import entidades.Item;
import entidades.Movimiento;
import entidades.Movimientoitem;
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
    private MovimientoFacade movimentoFacade;
    @EJB
    private MovimientoitemFacade movimentoItemFacade;


    public List<MovimientoDTO> getAllMovimiento(){
        List<MovimientoDTO> lista = new ArrayList<MovimientoDTO>();
        List<Movimiento> listaEntidad = movimentoFacade.getAllMovimientos();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<ItemDTO> getItemsByMov(MovimientoDTO mov){
        List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
        List<Item> lista = movimentoFacade.getItemsByMovimiento(mov);
        
        return listaItems;
    }
    
    public void insertMovimiento(MovimientoDTO mov, List<MovimientoitemDTO> movItem){
        Movimiento movEnt = new Movimiento();
        movEnt = convertDTOToEntity(mov,1);
        Movimiento mv = movimentoFacade.insertMovimiento(movEnt);
        
        for(MovimientoitemDTO DTO : movItem){
            Movimientoitem movI = new Movimientoitem();
            Movimiento m = new Movimiento();
            Item i = new Item();
            
            movI.setEstado(DTO.getEstado());
            m.setIdmovimiento(mv.getIdmovimiento());
            movI.setMovimiento(m);
            i.setIditem(DTO.getItem().getIditem());
            movI.setItem(i);
            
            movimentoItemFacade.create(movI);
        }
    }
    
    public void updateMovimiento(MovimientoDTO mov){
        Movimiento movEnt = new Movimiento();
        movEnt = convertDTOToEntity(mov,0);
        movimentoFacade.edit(movEnt);
    }
    
    public List<MovimientoDTO> busquedaMovimiento(MovimientoDTO mov){
        List<MovimientoDTO> movi = new ArrayList<MovimientoDTO>();
        List<Movimiento> movEnt = movimentoFacade.getMovimientoByBusqueda(mov);
        movi = convertListEntityToDTO(movEnt);
        return movi;
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
        DTO.setSerie(movimiento.getSerie());
        DTO.setIdTipoMovimiento(movimiento.getIdtipoMovimiento().getIdtipoMovimiento());
        DTO.setTipoMovimiento(movimiento.getIdtipoMovimiento().getNombre());
        
        DTO.setEstado(movimiento.getEstado());
        if(movimiento.getEstado() == 0){
            //DTO.setColorEstado("#F44236");//rojo
            //DTO.setColorLetra("white");
        }else{
            //DTO.setColorEstado("#2095F2");//azul
            //DTO.setColorLetra("white");
        }
        return DTO;
    }
    
    //0 = UPDATE, 1 = INSERT
    public Movimiento convertDTOToEntity(MovimientoDTO movimiento, int tipo){
        Movimiento Ent = new Movimiento();
        Tipomovimiento tm = new Tipomovimiento();
        
        tm.setIdtipoMovimiento(movimiento.getIdTipoMovimiento());
        Ent.setIdtipoMovimiento(tm);
        
        if(tipo == 0){
            Ent.setIdmovimiento(movimiento.getIdmovimiento());
        }
        
        Ent.setComentario(movimiento.getComentario());
        Ent.setCorrelativo(movimiento.getCorrelativo());
        Ent.setFecha(movimiento.getFecha());
        Ent.setIdalmacenDestino(movimiento.getIdalmacenDestino());
        Ent.setIdalmacenOrigen(movimiento.getIdalmacenOrigen());
        Ent.setIddocumento(movimiento.getIddocumento());
        Ent.setMotivo(movimiento.getMotivo());
        Ent.setNombreDestino(movimiento.getNombreDestino());
        Ent.setNombreOrigen(movimiento.getNombreOrigen());
        Ent.setCorrelativo(movimiento.getCorrelativo());
        Ent.setSerie(movimiento.getSerie());
        Ent.setEstado(movimiento.getEstado());
        
        
        return Ent;
    }
    
    
}
