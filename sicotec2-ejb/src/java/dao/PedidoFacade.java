/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.PedidoDTO;
import entidades.Pedido;
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
public class PedidoFacade extends AbstractFacade<Pedido> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PedidoFacade() {
        super(Pedido.class);
    }
    public List<Pedido> getAllPedidos(){
        List<Pedido> listaPedido = new ArrayList<Pedido>();
        
        try{
            String jpa = "SELECT p "
                       + "FROM Pedido p"
                       + "JOIN FETCH p.idempresa";

            Query query = em.createQuery(jpa,Pedido.class);
            listaPedido = query.getResultList();
            
        }catch(Exception e){
            listaPedido = new ArrayList<Pedido>();
        }
        return listaPedido;
    }
    
    public List<Pedido> getPedidoFiltro(PedidoDTO pedidoDto){
        List<Pedido> listaPedido = new ArrayList<Pedido>();
        
        try{
            String jpa = "SELECT p "
                       + "  FROM Pedido p,"
                       + "       Empresa e"
                       + " WHERE e.idempresa = p.idempresa.idempresa ";
            
            if(pedidoDto.getIdEmpresa().getIdempresa() != 0){
                jpa += " AND p.idempresa.idempresa = " + pedidoDto.getIdEmpresa().getIdempresa();
            }
            if(!pedidoDto.getIdEmpresa().getRuc().equals("") && pedidoDto.getIdEmpresa().getRuc() != null){
                jpa += " AND e.ruc = '" + pedidoDto.getIdEmpresa().getRuc()+"'";
            }
            if(pedidoDto.getIdalmacen() != 0){
                jpa += " AND p.idalmacen = " + pedidoDto.getIdalmacen();
            }
            
              listaPedido=em.createQuery(jpa, Pedido.class).getResultList();
        }catch(Exception e){
            System.out.println("mensaje: " + e.getMessage());
            listaPedido = new ArrayList<Pedido>();
        }
        return listaPedido;
    }
    
    public Pedido agregarPedido(Pedido entidadPedido) {
        getEntityManager().persist(entidadPedido);
        getEntityManager().flush();
        return entidadPedido;
    }
}
