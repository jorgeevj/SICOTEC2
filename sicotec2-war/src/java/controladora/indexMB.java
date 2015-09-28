/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladora;

import entidades.Permiso;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jorge
 */
@ManagedBean
@SessionScoped
public class indexMB {

    /**
     * Creates a new instance of indexMB
     */
    public indexMB() {
    }
    @PostConstruct
     public void init(){
       permisos=listaPermiso();  
     }
     private List<Permiso> permisos;
    public List<Permiso> listaPermiso(){
        permisos=new ArrayList<>();
        Permiso p=new Permiso();
        p.setIdpermiso(1);
        p.setNombre("Cotizaciones");
        p.setUrl("http://localhost:8080/sicotec2-war/faces/cotizacion/CotizacionJSF.xhtml");
        permisos.add(p);
        
        p=new Permiso();
        p.setIdpermiso(2);
        p.setNombre("Pedidos");
        p.setUrl("http://localhost:8080/sicotec2-war/faces/cotizacion/CotizacionJSF.xhtml");
        permisos.add(p);
   return permisos;
   }

    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }
    
}
