package edu.jsu.mcis.cs310.coursedb.dao;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SectionDAO {

    public SectionDAO(DAOFactory aThis) {
    }
    private DAOFactory daoFactory;

    public String find(String subjectid, String courseNumber, int termId) {
        String jsonResult = "";
        JsonArray sectionArray = new JsonArray();
        
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Connection conn = daoFactory.getConnection();
            String sql = "SELECT * FROM section WHERE subjectid = ? AND num = ? AND termid = ? ORDER BY crn";
            
            if (conn.isValid(0)) {
                
                ps = conn.prepareStatement(sql);
                ps.setString(1, subjectid);
                ps.setString(2, courseNumber);
                ps.setInt(3, termId);
                rs = ps.executeQuery();
                   
                    while (rs.next()) {
                    JsonObject sectionObject = new JsonObject();
                    
                        sectionObject.put("Subject ID",String.valueOf(subjectid));
                    
                        sectionObject.put("Course_Num",String.valueOf(courseNumber)); 
                        
                        sectionObject.put("TID",String.valueOf(termId)); 
                    }
                }
                        
        }catch (Exception e) {e.printStackTrace();
                        
                       
                
                jsonResult = sectionArray.toString();
        }
        return jsonResult;
    }
}

    


            

        
    

    


