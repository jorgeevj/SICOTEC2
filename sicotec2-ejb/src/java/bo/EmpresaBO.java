/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.EmpresaFacade;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Cesar
 */
@Stateless
@LocalBean
public class EmpresaBO {
    @EJB
    private EmpresaFacade empresaFacade = new EmpresaFacade();
}
