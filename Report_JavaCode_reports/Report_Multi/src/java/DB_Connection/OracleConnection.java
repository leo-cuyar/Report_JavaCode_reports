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
public class OracleConnection {
    private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    //local
   // private static final String URL = "jdbc:oracle:thin:@gplatp_high?TNS_ADMIN=C:\\wallet";
    //cloud
    private static final String URL = "jdbc:oracle:thin:@innosysdb_tp?TNS_ADMIN=/home/wallet_GIL";
    public Connection conn = null;
    public Statement stmt = null;
    
    public  OracleConnection() throws ClassNotFoundException, SQLException{
        
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    //local
   /* System.setProperty("oracle.net.tns_admin", "C:\\wallet");
    System.setProperty("oracle.net.wallet_location", "C:\\wallet");*/
    
    //cloud
    
    System.setProperty("oracle.net.tns_admin", "/home/wallet_GIL");
    System.setProperty("oracle.net.wallet_location", "/home/wallet_GIL");

    //local
  /*  conn = (Connection) DriverManager.getConnection(URL,
                                     "peoplePay",
                                     "i&1Yv*A9teibs86");*/
    
    //colud
     conn = (Connection) DriverManager.getConnection(URL,
                                     "gil",
                                     "lU3daNMVdVxRvkaOQ6gr$2019");
    
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
    
}
