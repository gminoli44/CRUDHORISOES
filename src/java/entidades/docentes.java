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
public class docentes {

    private String doc_id;
    private String doc_programa;
    private String doc_documento;
    private String doc_nombres;
    private String doc_apellidos;
    private String doc_correo;
    private String doc_tipocontrato;

    public void setDoc_id(String doc_id) {
        this.doc_id = doc_id;
    }

    public String getDoc_id() {
        return doc_id;
    }

    public void setDoc_programa(String doc_programa) {
        this.doc_programa = doc_programa;
    }

    public String getDoc_programa() {
        return doc_programa;
    }

    public void setDoc_documento(String doc_documento) {
        this.doc_documento = doc_documento;
    }

    public String getDoc_documento() {
        return doc_documento;
    }

    public void setDoc_nombres(String doc_nombres) {
        this.doc_nombres = doc_nombres;
    }

    public String getDoc_nombres() {
        return doc_nombres;
    }

    public void setDoc_apellidos(String doc_apellidos) {
        this.doc_apellidos = doc_apellidos;
    }

    public String getDoc_apellidos() {
        return doc_apellidos;
    }

    public void setDoc_correo(String doc_correo) {
        this.doc_correo = doc_correo;
    }

    public String getDoc_correo() {
        return doc_correo;
    }

    public void setDoc_tipocontrato(String doc_tipocontrato) {
        this.doc_tipocontrato = doc_tipocontrato;
    }

    public String getDoc_tipocontrato() {
        return doc_tipocontrato;
    }
    
    public String go_back(){
        return "/index.xhtml?faces-redirect=true";   
    }
    
    
    public ArrayList<docentes> getGet_all_doc(){
        
          ArrayList<docentes> list_of_categories = new ArrayList<docentes>();
                
        try {
          
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM docentes INNER JOIN programa where doc_programa=pro_id");
             
             while (rs.next()) {                
                docentes objCategory = new docentes();
                objCategory.setDoc_apellidos(rs.getString("doc_apellidos"));
                objCategory.setDoc_correo(rs.getString("doc_correo"));
                objCategory.setDoc_documento(rs.getString("doc_documento"));
                objCategory.setDoc_id(rs.getString("doc_id"));
                objCategory.setDoc_nombres(rs.getString("doc_nombres"));
                objCategory.setDoc_programa(rs.getString("pro_nombre"));
                
                switch(rs.getString("doc_tipocontrato")){
                    case "1":
                        objCategory.setDoc_tipocontrato("Prestación de Servicios");
                        break;
                    case "2":
                        objCategory.setDoc_tipocontrato("Termino Indefido");
                        break;
                    case "3":
                        objCategory.setDoc_tipocontrato("Catedra");
                        break;
                    default:
                }
                
                              
                list_of_categories.add(objCategory);
            }
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return list_of_categories;
    }
    
    public void add_doc(){
                
        try {
          
             Connection connection = null;
             DB_connection obj_dbconnecion = new DB_connection();
             connection = obj_dbconnecion.get_connection();
             PreparedStatement ps = connection.prepareStatement("INSERT INTO docentes (doc_programa,doc_documento,doc_nombres,doc_apellidos,doc_correo,doc_tipocontrato) values ('"+doc_programa+"','"+doc_documento+"','"+doc_nombres+"','"+doc_apellidos+"','"+doc_correo+"','"+doc_tipocontrato+"')");
             ps.executeUpdate();
             
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
       
    }
    
    private Map<String,Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap(); 
    
    public String edit_Doc(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String doc_id= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
          Statement st=connection.createStatement();
          ResultSet rs=st.executeQuery("select * from docentes where doc_id="+doc_id);
          docentes obj_Doc=new docentes();
          rs.next();
          obj_Doc.setDoc_apellidos(rs.getString("doc_apellidos"));
          obj_Doc.setDoc_correo(rs.getString("doc_correo"));
          obj_Doc.setDoc_documento(rs.getString("doc_documento"));
          obj_Doc.setDoc_id(rs.getString("doc_id"));
          obj_Doc.setDoc_nombres(rs.getString("doc_nombres"));
          obj_Doc.setDoc_programa(rs.getString("doc_programa"));
          obj_Doc.setDoc_tipocontrato(rs.getString("doc_tipocontrato"));
          
      
          sessionMap.put("editdoc", obj_Doc);  
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/docente/editdocentes.xhtml?faces-redirect=true";   
    }
    
    public String update_doc(){
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
	String	update_doc= params.get("update_doc");
        try {
            DB_connection obj_DB_connection=new DB_connection();
            Connection connection=obj_DB_connection.get_connection();
      PreparedStatement ps=connection.prepareStatement("update docentes set doc_programa=?, doc_documento=?, doc_nombres=?, doc_apellidos=?, doc_correo=?, doc_tipocontrato=? where doc_id=?");
            ps.setString(1, doc_programa);
            ps.setString(2, doc_documento);
            ps.setString(3, doc_nombres);
            ps.setString(4, doc_apellidos);
            ps.setString(5, doc_correo);
            ps.setString(6, doc_tipocontrato);
            ps.setString(7, update_doc);
            System.out.println(ps);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
       return "/docente/docente.xhtml?faces-redirect=true";   
    }
    
    public String delete_Doc(){
     FacesContext fc = FacesContext.getCurrentInstance();
     Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
     String field_doc_id= params.get("action");
     try {
          DB_connection obj_DB_connection=new DB_connection();
          Connection connection=obj_DB_connection.get_connection();
           PreparedStatement ps=connection.prepareStatement("DELETE FROM  docentes where doc_id=?");
            ps.setString(1, field_doc_id);
            System.out.println(ps);
            ps.executeUpdate();
      } catch (Exception e) {
            System.out.println(e);
      }
     return "/docente/docente.xhtml?faces-redirect=true";   
    }
    
    public docentes() {
        
    }
    
}
