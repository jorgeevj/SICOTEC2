/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CompraDTO;
import dto.PealtipoitemDTO;
import dto.RequerimientoDTO;
import dto.TipoItemDTO;
import entidades.Lote;
import entidades.Pealtipoitem;
import entidades.Requerimientos;
import entidades.Tipoitem;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Jorge
 */
@Stateless
public class PealtipoitemFacade extends AbstractFacade<Pealtipoitem> {

    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PealtipoitemFacade() {
        super(Pealtipoitem.class);
    }

    public List<Pealtipoitem> getAllPealtipoitem() {
        List<Pealtipoitem> tPealtipoitem = new ArrayList<Pealtipoitem>();

        try {
            String jpa = "SELECT t "
                    + "FROM Pealtipoitem t "
                    + "JOIN FETCH t.idtipoItem "
                    + "JOIN FETCH t.idalmacen ";

            Query query = em.createQuery(jpa, Pealtipoitem.class);
            tPealtipoitem = query.getResultList();

        } catch (Exception e) {

            tPealtipoitem = new ArrayList<Pealtipoitem>();
        }

        return tPealtipoitem;
    }

    public List<Pealtipoitem> getAlmacenForPedido(Pealtipoitem a) {
        List<Pealtipoitem> entidad = new ArrayList<Pealtipoitem>();
        try{
            String jpa = "SELECT pti "
                + "FROM Pealtipoitem pti "
                + "WHERE pti.estado = 0 "
                + "AND pti.pealtipoitemPK.idalmacen = " + a.getAltipoitem().getAlmacen().getIdalmacen();
            
            entidad = em.createQuery(jpa, Pealtipoitem.class).getResultList();
        } catch(Exception e){
            
        }
        

        return entidad;

    }

    public Tipoitem getTipoItemById(TipoItemDTO dto) {
        Tipoitem entidad = new Tipoitem();
        try {
            String sql = "SELECT a "
                   + "FROM Tipoitem a "
                   + "WHERE a.idTipoitem = '" +dto.getIdtipoItem()+"'";
            entidad=em.createQuery(sql, Tipoitem.class).getSingleResult();
        }catch(Exception e){
            entidad = new Tipoitem();
        }

        return entidad;
    }

    public List<Pealtipoitem> getPRE(int idalmacen, PealtipoitemDTO ct) {
        String jpa = "SELECT i "
                + "FROM Pealtipoitem i, Altipoitem a "
                + "where i.idalmacen=a.idalmacen "
                + "and a.idalmacen= " + idalmacen + " "
                + "and i.estado=0 ";

        return em.createQuery(jpa, Pealtipoitem.class).setMaxResults(ct.getCantidad()).getResultList();
    }

    public List<Pealtipoitem> getPedidosByAlmacen(CompraDTO c) {
        String jpa = "SELECT i "
                + "FROM Pealtipoitem i "
                + "where i.altipoitem.almacen.idalmacen=" + c.getIdAlmacen() + " "
                + "and i.estado=0 ";

        return em.createQuery(jpa, Pealtipoitem.class).getResultList();
    }

    public List<Pealtipoitem> getPedidosByCompraAndItem(CompraDTO co, Lote loteSelec) {
        String jpa = "SELECT i "
                + "FROM Pealtipoitem i "
                + "where i.altipoitem.almacen.idalmacen=" + co.getIdAlmacen() + " "
                + "and i.altipoitem.tipoitem.idtipoItem= '"+ loteSelec.getAltipoitem().getTipoitem().getIdtipoItem()+"' "
                + "and i.idcompra.idcompra= "+ co.getIdcompra()+" "
                + "and i.estado=1";

        return em.createQuery(jpa, Pealtipoitem.class).getResultList();

    }

    public List<Pealtipoitem> getAllItemsByPedido(Pealtipoitem entidad){
        List<Pealtipoitem> entidadLista = new ArrayList<Pealtipoitem>();
        String jpa = "SELECT pti " +
                     "  FROM Pealtipoitem pti, " +
                     "       Pedido p " +
                     " WHERE pti.pealtipoitemPK.idpedido = p.idpedido " +
                     "   AND pti.estado = 0 "+ 
                     "   AND pti.idrequerimientos = null " +
                     "   AND p.idpedido = " + entidad.getPedido().getIdpedido();
        entidadLista = em.createQuery(jpa, Pealtipoitem.class).getResultList();
        return entidadLista;
    }

    
    public boolean cambiarEstadoPeAlTipoItem(int idEstado, int idPeAlTipoItem){
         boolean tof = false;
        try{
            String sql = "UPDATE pealtipoitem "
                       + "SET    estado = "+idEstado+" "
                       + "WHERE  idpedido = "+idPeAlTipoItem;
                    
            Query query = em.createNativeQuery(sql);
            
            int i = query.executeUpdate();
            if(i == 1){
                tof = true;
            }
        }catch(Exception e){
           System.out.println(e.getMessage());
        }
        
        return tof;
     }

    
    public Pealtipoitem getAllBytipoItemPedidoAlmacenId(Pealtipoitem entidad){
        Pealtipoitem entidadPti = new Pealtipoitem();
        try{
            String jpa = "SELECT pti "
                        +"  FROM Pealtipoitem pti"
                        +" WHERE pti.pealtipoitemPK.idpedido = " + entidad.getPedido().getIdpedido()+" "
                        +"   AND pti.pealtipoitemPK.idalmacen = " + entidad.getAltipoitem().getAlmacen().getIdalmacen()+" "
                        +"   AND pti.pealtipoitemPK.idtipoItem = '" + entidad.getAltipoitem().getTipoitem().getIdtipoItem()+"'";
            entidadPti = em.createQuery(jpa, Pealtipoitem.class).getSingleResult();
        } catch(Exception e){
            entidadPti = new Pealtipoitem();
        }
        return entidadPti;
    }
    
    public List<Pealtipoitem> getAllPealtipoitemsByPedido(Pealtipoitem entidad){
        List<Pealtipoitem> listaEntidad = new ArrayList<Pealtipoitem>();
        try{
            String jpa = "  SELECT p " +
                         "    FROM Pealtipoitem p " +
                         "   WHERE p.pealtipoitemPK.idpedido = " + entidad.getPedido().getIdpedido();
            listaEntidad = em.createQuery(jpa, Pealtipoitem.class).getResultList();
        } catch(Exception e){
            listaEntidad = new ArrayList<Pealtipoitem>();
        }
        return listaEntidad;
    }

    public List<Pealtipoitem> getPedidosbyRequerimiento(RequerimientoDTO r) {
        List<Pealtipoitem> lpa;
        String jpa = "  SELECT p " +
                         "    FROM Pealtipoitem p " +
                         "   WHERE p.idrequerimientos.idrequerimientos = " + r.getIdrequerimientos();
            lpa= em.createQuery(jpa, Pealtipoitem.class).getResultList();
            return lpa;
    }
}
