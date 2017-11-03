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
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Gersain
 */
@ManagedBean
@RequestScoped
public class programa {

    private String pro_nombre;
    private String pro_id;
    private programa progra;

    public programa getProgra() {
        return progra;
    }
     
    public void setPro_id(String pro_id) {
        this.pro_id = pro_id;
    }

    public String getPro_id() {
        return pro_id;
    }

    public void setPro_nombre(String pro_nombre) {
        this.pro_nombre = pro_nombre;
    }

    public String getPro_nombre() {
        return pro_nombre;
    }
    
  // private List<SelectItem> items_id = new ArrayList <SelectItem> ();
   Map<String, Object> items_id = new HashMap<String, Object>();
   @PostConstruct
    public void init(){
        SelectItem si = new SelectItem();
        
        try {
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM programa");
                                   
             while (rs.next()) {
                items_id.put(rs.getString("pro_nombre").toString(), rs.getString("pro_id").toString());
              }
                                        
           
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Map<String, Object> getItems_id() {
        return items_id;
    }
    
       
    
    public String go_back(){
        return "/index.xhtml?faces-redirect=true";   
    }
    
    public ArrayList<programa> getGet_all_programs(){
        
          ArrayList<programa> list_of_programas = new ArrayList<programa>();
                
        try {
          
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM programa");
             
             while (rs.next()) {                
                programa objPrograma = new programa();
                objPrograma.setPro_nombre(rs.getString("pro_nombre"));
                objPrograma.setPro_id(rs.getString("pro_id"));
                
                list_of_programas.add(objPrograma);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list_of_programas;
    }
    
    public void add_Program(){
                
        try {
          
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO programa (pro_nombre) values ('"+pro_nombre+"')");
             ps.executeUpdate();
             
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
       
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
    public String edit_Program(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String f_pro_id= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
          Statement st=connection.createStatement();
          ResultSet rs=st.executeQuery("select * from programa where pro_id="+f_pro_id);
          programa objPrograma=new programa();
          rs.next();
          objPrograma.setPro_nombre(rs.getString("pro_nombre"));
          objPrograma.setPro_id(rs.getString("pro_id"));
          sessionMap.put("editprograma", objPrograma);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/programa/editprograma.xhtml?faces-redirect=true";   
    }
    
    public String update_Program(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String	update_id= params.get("update_pro");
        try {
            DB_connection obj_DB_connection=new DB_connection();
            Connection connection=obj_DB_connection.get_connection();
            PreparedStatement ps=connection.prepareStatement("update programa set pro_nombre=? where pro_id=?");
            ps.setString(1, pro_nombre);
            ps.setString(2, update_id);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/programa/programa.xhtml?faces-redirect=true";   
    }
    
    public String delete_Program(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String field_si_no= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
           PreparedStatement ps=connection.prepareStatement("DELETE FROM  programa where pro_id=?");
            ps.setString(1, field_si_no);
            System.out.println(ps);
            ps.executeUpdate();
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/programa/programa.xhtml?faces-redirect=true";   
    }
    
    public programa() {
        
    }
    
}
