/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import conexion.DB_connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Gersain
 */
@ManagedBean
@RequestScoped
public class Index {

 
    public String ir_docentes(){
     return "/docente/docente.xhtml?faces-redirect=true";   
    }
    
    public String ir_asignaturas(){
     return "/asignaturas/asignaturas.xhtml?faces-redirect=true";   
    }
    
    public String ir_programa(){
     return "programa/programa.xhtml?faces-redirect=true";   
    }
    
    public String ir_cargaacademica(){
     return "/cargaacademica/cargaacademica.xhtml?faces-redirect=true";   
    }
    
    public Index() {
        
    }
    
}
