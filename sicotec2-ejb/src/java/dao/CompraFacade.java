/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.CompraDTO;
import entidades.Compra;
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
public class CompraFacade extends AbstractFacade<Compra> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompraFacade() {
        super(Compra.class);
    }
    public List<Compra> getAllCompra(){
        List<Compra> tCompra = new ArrayList<Compra>();
        
        
        try{
            String jpa = "SELECT t "
                       + "FROM Compra t "
                       + "JOIN FETCH t.idempresa "
                       + "JOIN FETCH t.idalmacen ";
                    
                   
            Query query = em.createQuery(jpa,Compra.class);
            tCompra = query.getResultList();
            
        }catch(Exception e){
            
            tCompra = new ArrayList<Compra>();
        }
        
        
        return tCompra;
    }
     public List<Compra> buscarCompra(CompraDTO dto) {
            List<Compra> l = new ArrayList<Compra>();
            String sql = "SELECT c FROM Compra c WHERE 1=1 ";
     
           try{
               System.out.println("nombreEmpressaquery" + dto.getIdempresa().getNombre());
               if(dto.getIdempresa().getNombre()!=null && !dto.getIdempresa().getNombre().equals("")){
                   System.out.println("entra if");
                sql+=" and c.idempresa.nombre like '%"+dto.getIdempresa().getNombre()+"%' ";
               }
                if(dto.getSerie()!=null && !dto.getSerie().equals("")){
                sql+="and c.serie like '%"+dto.getSerie()+"%' ";
            }
                
               l=em.createQuery(sql, Compra.class).getResultList();
           } catch(Exception e){
               l = new ArrayList<Compra>();
           }
            
            
           
          return l; 
        
    }
}
