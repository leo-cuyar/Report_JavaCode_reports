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
 * @author 59268
 */
public class OracleCloudConnection {
     private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    //local
   // private static final String URL = "jdbc:oracle:thin:@gplatp_high?TNS_ADMIN=C:\\wallet";
    //cloud
    private String URL = "jdbc:oracle:thin:@innosysdb2019_tp?TNS_ADMIN=/home/wallet_gpl";
    public Connection conn = null;
    public Statement stmt = null;
    
    public  OracleCloudConnection(String serv) throws ClassNotFoundException, SQLException{
        
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
    
    
    
    String user="";
    String password="";
    
    if  (serv.equals("prd")) {
        user="apps";
        password="GwrFVUsBNHOyd$_P2020";  
        URL = "jdbc:oracle:thin:@//150.136.100.215:1521/INNODB1.sub11052147110.innovcn.oraclevcn.com";
        //System.setProperty("oracle.net.tns_admin", "/home/wallet_gpl");
        //System.setProperty("oracle.net.wallet_location", "/home/wallet_gpl");
    } else if (serv.equals("tra")){
        user="PeoplePay";
        password="QeXTV3WN8mNqn$2019";  
        URL = "jdbc:oracle:thin:@innosysdb2019_tp?TNS_ADMIN=/home/wallet_gpl";
        System.setProperty("oracle.net.tns_admin", "/home/wallet_gpl");
        System.setProperty("oracle.net.wallet_location", "/home/wallet_gpl");
    } else if (serv.equals("PASS")) {
        URL = "jdbc:oracle:thin:@//150.136.92.24:1521/pplpay1.sub10132011350.innovcn.oraclevcn.com";
         user="GPL";
        password="WiV1$_Y67oZhe";  
    }  else if (serv.equals("ICOM")) {
        URL = "jdbc:oracle:thin:@//192.168.56.1:1521/xepdb1";
         user="icom";
        password="Icom#2021";  
    
    } else if (serv.equals("DEMO")) {
       user="pplpaydemo";
        password="tcknIJYG1Kf4Xt$$YzUbJh";  
        URL = "jdbc:oracle:thin:@innobasu_high?TNS_ADMIN=/home/wallet_demo";
        System.setProperty("oracle.net.tns_admin", "/home/wallet_demo");
        System.setProperty("oracle.net.wallet_location", "/home/wallet_demo"); 
    }

     conn = (Connection) DriverManager.getConnection(URL,
                                     user,
                                     password);
    
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
