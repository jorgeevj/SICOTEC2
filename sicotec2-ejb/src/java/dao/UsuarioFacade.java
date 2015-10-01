/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.UsuarioDTO;
import entidades.Usuario;
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
}
