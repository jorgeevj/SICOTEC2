/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AltipoitemFacade;
import dao.CompraFacade;
import dao.ItemFacade;
import dao.MovimientoFacade;
import dao.MovimientoitemFacade;
import dao.TipomovimientoFacade;
import dao.UsuarioFacade;
import dto.ItemDTO;
import dto.MovimientoDTO;
import dto.MovimientoitemDTO;
import dto.MovimientoitemDTOVista;
import dto.TipomovimientoDTO;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Item;
import entidades.Lote;
import entidades.Movimiento;
import entidades.Movimientoitem;
import entidades.MovimientoitemPK;
import entidades.Tipoitem;
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
    @EJB
    private ItemBO itemBO;
    @EJB
    private ItemFacade itemFacade;
    @EJB
    private AltipoitemFacade alltipoitemFacede;
    @EJB
    private CompraFacade compraFacade;
    
    public List<MovimientoDTO> getAllMovimiento(){
        List<MovimientoDTO> lista = new ArrayList<MovimientoDTO>();
        List<Movimiento> listaEntidad = movimentoFacade.getAllMovimientos();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<MovimientoitemDTOVista> getItemsByMov(MovimientoDTO mov){
        List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
        List<MovimientoitemDTOVista> lista = movimentoFacade.getItemsByMovimiento(mov);
        
        return lista;
    }
    
    public void insertMovimiento(MovimientoDTO mov, List<MovimientoitemDTO> movItem, List<ItemDTO> itemsReg, int idTipoMovimiento){
        Movimiento movEnt = new Movimiento();
        
        movEnt = convertDTOToEntity(mov,1);
        Movimiento mv = movimentoFacade.insertMovimiento(movEnt);
        
        int idMovimiento = mv.getIdmovimiento();
        Movimiento m = new Movimiento();
        m.setIdmovimiento(idMovimiento);
        
        if(idTipoMovimiento == 1){
            int idAlmacenDestino = mov.getIdalmacenDestino();
            for(ItemDTO DTO : itemsReg){
                //INSERTAR EL NUEVO ITEM
                Item i = new Item();
                Lote l = new Lote();
                l.setIdlote(DTO.getIdLote());
                i.setIdlote(l);
                i.setEstado(DTO.getEstado());
                i.setOperatividad(DTO.getOperatividad());
                
                Item i1 = itemFacade.insertItem(i);
                
                //INSERTAR EL MOV-ITEM
                Movimientoitem movI = new Movimientoitem();
                MovimientoitemPK movIPK = new MovimientoitemPK();
                movIPK.setIditem(i1.getIditem());
                movIPK.setIdmovimiento(idMovimiento);
                Item i2 = new Item();
                i.setIditem(i1.getIditem());

                movI.setItem(i);
                movI.setEstado(1);
                movI.setMovimiento(m);
                movI.setMovimientoitemPK(movIPK);

                movimentoItemFacade.create(movI);
                
                //AGREGAR ITEMS I EN ALMACEN N
                boolean tof = alltipoitemFacede.updateCantidadAltipoItem(DTO.getIdTipoItem(), idAlmacenDestino, DTO.getCantidad());
                if(!tof){//EN CASO NO EXISTA EL ITEM
                    //INSERTAR NUEVO ITEM EN ALMACEN
                    Altipoitem al = new Altipoitem();
                    Almacen alm = new Almacen();
                    Tipoitem ti = new Tipoitem();
                    alm.setIdalmacen(idAlmacenDestino);
                    al.setAlmacen(alm);
                    ti.setIdtipoItem(DTO.getIdTipoItem());
                    al.setTipoitem(ti);
                    al.setCantidad(DTO.getCantidad());
                    al.setEstado(1);
                    al.setReservado(0);
                    
                    AltipoitemPK pk = new AltipoitemPK();
                    pk.setIdalmacen(idAlmacenDestino);
                    pk.setIdtipoItem(DTO.getIdTipoItem());
                    
                    alltipoitemFacede.create(al);
                }
            }
            //CAMBIAR DE ESTADO A LA COMPRA
            boolean tof1 = compraFacade.cambiarEstadoCompra(2, mov.getIdCompra());
            
        }else if(idTipoMovimiento == 2){
            int idAlmacenOring = mov.getIdalmacenOrigen();
            for(MovimientoitemDTO DTO : movItem){
                //INSERTAR EL MOV-ITEM
                Movimientoitem movI = new Movimientoitem();
                MovimientoitemPK movIPK = new MovimientoitemPK();
                movIPK.setIditem(DTO.getItem().getIditem());
                movIPK.setIdmovimiento(idMovimiento);
                Item i = new Item();
                i.setIditem(DTO.getItem().getIditem());

                movI.setItem(i);
                movI.setEstado(DTO.getEstado());
                movI.setMovimiento(m);
                movI.setMovimientoitemPK(movIPK);

                movimentoItemFacade.create(movI);
            }
        }else if(idTipoMovimiento == 3){
            int idAlmacenOringe = mov.getIdalmacenOrigen();
            int idAlmacenDestino = mov.getIdalmacenDestino();
            
            
        }
    }
    
    public List<MovimientoitemDTOVista> getItemsByAlmacen(int idAlmacen){
        List<MovimientoitemDTOVista> items = new ArrayList<MovimientoitemDTOVista>();
        items = itemFacade.getItemsByAlmacen(idAlmacen);
        
        return items;
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
