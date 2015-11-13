/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AltipoitemFacade;
import dao.CompraFacade;
import dao.DocalmacenFacade;
import dao.ItemFacade;
import dao.MovimientoFacade;
import dao.MovimientoitemFacade;
import dao.TipomovimientoFacade;
import dao.UsuarioFacade;
import dao.VentaFacade;
import dto.ItemDTO;
import dto.MovimientoDTO;
import dto.MovimientoitemDTO;
import dto.TipoItemDTO;
import dto.TipomovimientoDTO;
import entidades.Almacen;
import entidades.Altipoitem;
import entidades.AltipoitemPK;
import entidades.Docalmacen;
import entidades.Item;
import entidades.Lote;
import entidades.LotePK;
import entidades.Movimiento;
import entidades.Movimientoitem;
import entidades.MovimientoitemPK;
import entidades.Movimientoitemvista;
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
    @EJB
    private VentaFacade ventaFacade;
    @EJB
    private DocalmacenFacade docalmacenFacade;
    @EJB
    private CotizacionBO cotizacionBO;
    @EJB
    private TipoItemBO tipoItemBO;
    
    public List<MovimientoDTO> getAllMovimiento(){
        List<MovimientoDTO> lista = new ArrayList<MovimientoDTO>();
        List<Movimiento> listaEntidad = movimentoFacade.getAllMovimientos();
        
        lista = convertListEntityToDTO(listaEntidad);
        return lista;
    }
    
    public List<ItemDTO> getItemsByMov(int idMovimiento){
        List<ItemDTO> listaItems = new ArrayList<ItemDTO>();
        List<Item> lista= movimentoFacade.getItemsByMovimiento(idMovimiento);
        for(Item i : lista){
            ItemDTO dto = new ItemDTO();
            dto.setIditem(i.getIditem());
            dto.setDescTipoItem(i.getAltipoitem().getTipoitem().getDescripcion());
            dto.setDescMarca(i.getAltipoitem().getTipoitem().getIdmarca().getNombre());
            dto.setDescFamilia(i.getAltipoitem().getTipoitem().getIdfamilia().getNombre());
            listaItems.add(dto);
        }
        return listaItems;
    }
    
    public void insertMovimiento(MovimientoDTO mov, List<ItemDTO> itemsReg, int idTipoMovimiento){
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
                LotePK lotepk = new LotePK();
                lotepk.setIdlote(DTO.getIdLote());
                lotepk.setIdcompra(mov.getIdCompra());
                l.setLotePK(lotepk);
                
                i.setLote(l);
                i.setEstado(DTO.getEstado());
                i.setIditem(DTO.getIditem());
                Altipoitem alt = new Altipoitem();
                Almacen al = new Almacen();
                al.setIdalmacen(idAlmacenDestino);
                alt.setAlmacen(al);
                AltipoitemPK pk = new AltipoitemPK();
                pk.setIdalmacen(idAlmacenDestino);
                pk.setIdtipoItem(DTO.getIdTipoItem());
                alt.setAltipoitemPK(pk);
                
                i.setAltipoitem(alt);
                i.setOperatividad(DTO.getOperatividad());
                
                itemFacade.create(i);
                
                //INSERTAR EL MOV-ITEM
                Movimientoitem movI = new Movimientoitem();
                MovimientoitemPK movIPK = new MovimientoitemPK();
                movIPK.setIditem(i.getIditem());
                movIPK.setIdmovimiento(idMovimiento);
                Item i2 = new Item();

                movI.setItem(i);
                movI.setEstado(1);
                movI.setMovimiento(m);
                movI.setMovimientoitemPK(movIPK);

                movimentoItemFacade.create(movI);
                
                //AGREGAR ITEMS I EN ALMACEN N
                boolean tof = alltipoitemFacede.updateCantidadAltipoItem(DTO.getIdTipoItem(), idAlmacenDestino,1,1);
                /*if(!tof){//EN CASO NO EXISTA EL ITEM
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
                    
                    al.setAltipoitemPK(pk);
                    
                    alltipoitemFacede.create(al);
                }*/
            }
            //CAMBIAR DE ESTADO A LA COMPRA
            boolean tof1 = compraFacade.cambiarEstadoCompra(2, mov.getIdCompra());
            
        }else if(idTipoMovimiento == 2){
            int idAlmacenOring = mov.getIdalmacenOrigen();
            for(ItemDTO DTO : itemsReg){
                //INSERTAR EL MOV-ITEM
                Movimientoitem movI = new Movimientoitem();
                MovimientoitemPK movIPK = new MovimientoitemPK();
                Item i = new Item();
                
                movIPK.setIditem(DTO.getIditem());
                movIPK.setIdmovimiento(idMovimiento);
                i.setIditem(DTO.getIditem());

                movI.setItem(i);
                movI.setEstado(1);
                movI.setMovimiento(m);
                movI.setMovimientoitemPK(movIPK);

                movimentoItemFacade.create(movI);
                
                itemFacade.cambiarEstadoItem(2, DTO.getIditem());
                alltipoitemFacede.updateCantidadAltipoItem(DTO.getIdTipoItem(), idAlmacenOring, 1,2);
                ventaFacade.cambiarEstadoVenta(2, mov.getIdVenta());
            }
        }else if(idTipoMovimiento == 3){
            int idAlmacenOringe  = mov.getIdalmacenOrigen();
            int idAlmacenDestino = mov.getIdalmacenDestino();
            
            for(ItemDTO DTO : itemsReg){
                alltipoitemFacede.updateCantidadAltipoItem(DTO.getIdTipoItem(),idAlmacenOringe,1,2);
                alltipoitemFacede.updateCantidadAltipoItem(DTO.getIdTipoItem(),idAlmacenDestino,1,1);
                
                itemFacade.cambiarAlmacenItem(DTO.getIditem(), idAlmacenDestino);
                /*if(!tof){//EN CASO NO EXISTA EL ITEM
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
                    
                    al.setAltipoitemPK(pk);
                    
                    alltipoitemFacede.create(al);
                }
                */
                
                //INSERTAR EL MOV-ITEM
                Item i = new Item();
                i.setIditem(DTO.getIditem());
                
                Movimientoitem movI = new Movimientoitem();
                MovimientoitemPK movIPK = new MovimientoitemPK();
                movIPK.setIditem(DTO.getIditem());
                movIPK.setIdmovimiento(idMovimiento);
                Item i2 = new Item();

                movI.setItem(i);
                movI.setEstado(1);
                movI.setMovimiento(m);
                movI.setMovimientoitemPK(movIPK);

                movimentoItemFacade.create(movI);
            }
        }
    }
    
    public List<TipoItemDTO> getItemsByAlmacen(int idAlmacen){
        List<TipoItemDTO> tipoItems = new ArrayList<TipoItemDTO>();
        
        tipoItems = itemFacade.getItemsByAlmacen(idAlmacen);
        
        return tipoItems;
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
            DTO.setDescripcionEstado("PROCESO");
        }else if(movimiento.getEstado() == 1){
            DTO.setDescripcionEstado("COMPLETADO");
        }else if(movimiento.getEstado() == 2){
            DTO.setDescripcionEstado("CANCELADO");
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
            Ent.setCorrelativo(movimiento.getCorrelativo());
            Ent.setSerie(movimiento.getSerie());
            
        }else{
            if(movimiento.getIdTipoMovimiento() == 1){
                Docalmacen da=getNewSerieAndCorrelativo(movimiento.getIdalmacenDestino());
                Ent.setSerie(String.format("%03d", da.getSerie()));
                Ent.setCorrelativo(String.format("%06d", da.getCorrelativo()));
            }else if(movimiento.getIdTipoMovimiento() == 2){
                Docalmacen da=getNewSerieAndCorrelativo(movimiento.getIdalmacenOrigen());
                Ent.setSerie(String.format("%03d", da.getSerie()));
                Ent.setCorrelativo(String.format("%06d", da.getCorrelativo()));
            }
        }
        
        Ent.setComentario(movimiento.getComentario());
        Ent.setFecha(movimiento.getFecha());
        Ent.setIdalmacenDestino(movimiento.getIdalmacenDestino());
        Ent.setIdalmacenOrigen(movimiento.getIdalmacenOrigen());
        Ent.setIddocumento(movimiento.getIddocumento());
        Ent.setMotivo(movimiento.getMotivo());
        Ent.setNombreDestino(movimiento.getNombreDestino());
        Ent.setNombreOrigen(movimiento.getNombreOrigen());
        Ent.setEstado(movimiento.getEstado());
        
        
        return Ent;
    }
    public Docalmacen getNewSerieAndCorrelativo(Integer idalmacen){
   Docalmacen da = docalmacenFacade.findBy2key(idalmacen, 4);
        da = cotizacionBO.updateDocAlm(da);
       return da;
   }
    
}
