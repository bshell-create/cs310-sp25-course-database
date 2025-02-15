package edu.jsu.mcis.cs310.coursedb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import com.github.cliftonlabs.json_simple.*;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termId, int crn) {
        //System.out.println("" + studentid +", "+ termId +", " + crn);
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                String sql = "INSERT INTO registration (studentid, termId, crn) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termId);
                ps.setInt(3, crn);
                
                // Execute the update and check if the insertion was successful
                result = ps.executeUpdate()>0;
                //System.out.println(result);
                }
            } 
        catch (Exception e) {e.printStackTrace();}
        
            
        finally {
                if (ps != null) {
                    try 
                    {
                    ps.close();
                    }
                    catch (Exception e) {e.printStackTrace();}
                    
                }
            }
            return result;
    }
    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                 String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
                 ps = conn.prepareStatement(sql);
                 ps.setInt(1, studentid);
                 ps.setInt(2, termid);
                 ps.setInt(3, crn);
                 result = (ps.executeUpdate() > 0);
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { 
                try { ps.close();
                 } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                String sql = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);                
                result = (ps.executeUpdate() > 0);
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { 
                try { ps.close();
                } catch (Exception e) { e.printStackTrace(); } }
            
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        String jsonResult = "";
        JsonArray registrationArray = new JsonArray();
        
        try {
            
            Connection conn = daoFactory.getConnection();
            String sql = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                ps = conn.prepareStatement(sql);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                rs = ps.executeQuery();
                
                                
                while (rs.next()) {
                    
                    JsonObject registrationObject = new JsonObject();
                    //String columnData = rs.getString("CRN"); 
                    registrationObject.put("STU",String.valueOf(studentid));
                    
                    registrationObject.put("TID",String.valueOf(termid));
                    
                    //registrationObject.put("CRN",String.valueOf(crn));
                    
                    //registrationObject.add(registrationArray);
                    registrationArray.add(registrationObject);
                }
                jsonResult = registrationArray.toString();
            }    
}
        catch (Exception e) { e.printStackTrace(); }

        finally {
            if (rs != null) { 
                try { rs.close();
                } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { 
                try { ps.close(); 
                } catch (Exception e) { e.printStackTrace(); } }
        }
    return jsonResult;
}
}


