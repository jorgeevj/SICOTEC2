/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import dao.AlmacenFacade;
import entidades.Almacen;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.faces.model.SelectItem;

/**
 *
 * @author Jorge
 */
@Stateless
@LocalBean
public class AlmacenBO {
    @EJB
    private AlmacenFacade almacenFacade;

    public List<Almacen> findAll() {
        List<Almacen> listaAlmacen=almacenFacade.findAll();
        List<SelectItem> listaItemAlmacen=new ArrayList<>();
        for (Almacen la : listaAlmacen) {
            listaItemAlmacen.add(new SelectItem(la.getIdalmacen(), la.getNombre()));
        }
    return almacenFacade.findAll();
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
