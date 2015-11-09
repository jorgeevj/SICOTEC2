/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UsuarioDTO;
import entidades.Usuario;
import java.text.SimpleDateFormat;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "sicotec2-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
      public Usuario validateLogin(UsuarioDTO usuario){
        Usuario u = new Usuario();
        try{
            String jpa = "SELECT u "
                       + "FROM Usuario u "
                       + "WHERE u.nombre = :nombre "
                       + "AND   u.clave  = :clave";

            Query query = em.createQuery(jpa,Usuario.class);
            query.setParameter("nombre",usuario.getNombre());
            query.setParameter("clave",usuario.getClave());

            u = (Usuario)query.getSingleResult();
            
        }catch(Exception e){
            u = new Usuario();
        }
        
        return u;
    } 
    public List<Usuario> buscarUsuario(UsuarioDTO dto) {
            List<Usuario> l = new ArrayList<Usuario>();
             SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            String sql = "SELECT c FROM Usuario c WHERE 1=1 ";
     
           try{
               
                if(dto.getNombre()!=null && !dto.getNombre().equals("")){
                sql+=" and c.nombre like '%"+dto.getNombre()+"%' ";
                }
                if(dto.getIdpersona().getNombre()!=null && !dto.getIdpersona().getNombre().equals("")){
                sql+="and c.idpersona.nombre like '%"+dto.getIdpersona().getNombre()+"%' ";
                }
                if(dto.getFechaInicio() != null){
                sql += "and c.fecha >= '"+sdf.format(dto.getFechaInicio())+"' ";
                }
                if(dto.getFechaFin()!= null){
                sql += "and c.fecha <= '"+sdf.format(dto.getFechaFin())+"' ";
                }
                if(dto.getIdrol().getIdrol()!= 0){
                sql += "and c.idrol.idrol = " + dto.getIdrol().getIdrol()+" ";
                }
                
               l=em.createQuery(sql, Usuario.class).getResultList();
           } catch(Exception e){
               l = new ArrayList<Usuario>();
           } 
          return l;    
    }
     
}
