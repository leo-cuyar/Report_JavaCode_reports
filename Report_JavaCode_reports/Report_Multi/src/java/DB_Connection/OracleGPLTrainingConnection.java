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


/**
 *
 * @author ernesto
 */
public class OracleGPLTrainingConnection {
    private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    //local
   // private static final String URL = "jdbc:oracle:thin:@gplatp_high?TNS_ADMIN=C:\\wallet";
    //cloud
    private static final String URL = "jdbc:oracle:thin:@innosysdb2019_high?TNS_ADMIN=/home/wallet_gpl_training";
    public Connection conn = null;
    public Statement stmt = null;
    
    public  OracleGPLTrainingConnection() throws ClassNotFoundException, SQLException{
        
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    
    System.setProperty("oracle.net.tns_admin", "/home/wallet_gpl_training");
    System.setProperty("oracle.net.wallet_location", "/home/wallet_gpl_training");


     conn = (Connection) DriverManager.getConnection(URL,
                                     "PeoplePlay",
                                     "i&1Yv*A9teibs86");
    
    }
    
    public String makeTest() throws SQLException{
     stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("select code from GPL_LOAD_DATA_MONTHPAY_HRJM");
     String out="";
     while (rs.next()) {
            out=out + rs.getString("code");
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
