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
public class asignaturas {

    private String category_name;
    private String si_no;
    
    public void setCategory_name(String category_name){
        this.category_name = category_name;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setSi_no(String si_no) {
        this.si_no = si_no;
    }

    public String getSi_no() {
        return si_no;
    }
    
    public ArrayList<asignaturas> getGet_all_category(){
        
          ArrayList<asignaturas> list_of_categories = new ArrayList<asignaturas>();
                
        try {
          
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM categories");
             
             while (rs.next()) {                
                asignaturas objCategory = new asignaturas();
                objCategory.setCategory_name(rs.getString("category_name"));
                objCategory.setSi_no(rs.getString("si_no"));
                
                list_of_categories.add(objCategory);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list_of_categories;
    }
    
    public void add_category(){
                
        try {
          
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO categories (category_name) values ('"+category_name+"')");
             ps.executeUpdate();
             
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
       
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
    public String edit_Category(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String field_si_no= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
          Statement st=connection.createStatement();
          ResultSet rs=st.executeQuery("select * from categories where si_no="+field_si_no);
          asignaturas obj_Category=new asignaturas();
          rs.next();
          obj_Category.setCategory_name(rs.getString("category_name"));
          obj_Category.setSi_no(rs.getString("si_no"));
          sessionMap.put("editcategory", obj_Category);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/edit.xhtml?faces-redirect=true";   
    }
    
    public String update_category(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String	update_si_no= params.get("update_si_no");
        try {
            DB_connection obj_DB_connection=new DB_connection();
            Connection connection=obj_DB_connection.get_connection();
      PreparedStatement ps=connection.prepareStatement("update categories set category_name=? where si_no=?");
            ps.setString(1, category_name);
            ps.setString(2, update_si_no);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/index.xhtml?faces-redirect=true";   
    }
    
    public String delete_Category(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String field_si_no= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
           PreparedStatement ps=connection.prepareStatement("DELETE FROM  categories where si_no=?");
            ps.setString(1, field_si_no);
            System.out.println(ps);
            ps.executeUpdate();
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/index.xhtml?faces-redirect=true";   
    }
    
    public asignaturas() {
        
    }
    
}
