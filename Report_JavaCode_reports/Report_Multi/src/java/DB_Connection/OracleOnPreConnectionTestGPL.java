/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

import Shared.BlobObject;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 59268
 */
public class OracleOnPreConnectionTestGPL {
    private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    //private static final String URL = "jdbc:oracle:thin:@apps3.innosysgy.com:1521:orasrv1";
    
    private static final String URL = "jdbc:oracle:thin:@10.25.10.67:1521:hrmissrv";
    
    
    public Connection conn = null;
    public Statement stmt = null;
    
    public  OracleOnPreConnectionTestGPL() throws ClassNotFoundException, SQLException{
        
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
/*
     conn = (Connection) DriverManager.getConnection(URL,
                                     "apps",
                                     "Ma$ter$2019");*/

conn = (Connection) DriverManager.getConnection(URL,
                                     "apps",
                                     "GWI$2020apps");
    
    }
    
    public String makeTest() throws SQLException{
     stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("select HOBBIES from HR_RCM_HOBBY");
     String out="";
     while (rs.next()) {
            out=out + rs.getString("HOBBIES");
        }     
 
     return out;
    }
    
 public BlobObject getBlob(int id_media) throws SQLException{
       stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select FILE_NAME, FILE_DATA, FILE_MIME from MEDIA_DETAIL where id="+String.valueOf(id_media));  
       BlobObject out=null;
       if (rs.next()) {  
           String filename = rs.getString(1);           
           Blob blobObject = rs.getBlob(2);
           String mime=rs.getString(3);
           out=new BlobObject(filename, mime, blobObject);
       }
       return out;
    }
    
 public BlobObject getJobBlob(int id_media) throws SQLException{
       stmt = conn.createStatement();
       ResultSet rs = stmt.executeQuery("select FILE_NAME, FILE_DATA, FILE_MIME from HR_HCF_JOB where id="+String.valueOf(id_media));  
       BlobObject out=null;
       if (rs.next()) {  
           String filename = rs.getString(1);           
           Blob blobObject = rs.getBlob(2);
           String mime=rs.getString(3);
           out=new BlobObject(filename, mime, blobObject);
       }
       return out;
    }
}
