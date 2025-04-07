/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

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
public class OracleOnPreConnection {
    private static final String DRIVER_CLASS_NAME = "oracle.jdbc.driver.OracleDriver";
    //private static final String URL = "jdbc:oracle:thin:@apps3.innosysgy.com:1521:orasrv1";
    
    private static final String URL = "jdbc:oracle:thin:@//localhost:1521/orcl";
    
    
    public Connection conn = null;
    public Statement stmt = null;
    
    public  OracleOnPreConnection() throws ClassNotFoundException, SQLException{
        
    Class.forName("oracle.jdbc.driver.OracleDriver");
    
/*
     conn = (Connection) DriverManager.getConnection(URL,
                                     "apps",
                                     "Ma$ter$2019");*/

conn = (Connection) DriverManager.getConnection(URL,
                                     "gil",
                                     "gil");
    
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
    
   public List<String> getRepublicBankFormat() throws SQLException{
     List<String>  out=new ArrayList<String>();    
     stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("select '30001005'||'|'||replace(a.bank_account_no_hist,'-','')||'|'||c.BANK_ACCT_TYPE||'|'||a.first_name_hist||' '||a.surname_hist||'|'|| a.net_pay||'|'||'Payroll;' new_format"
             + " from pa_pmg_payrolldtl a"
             + " join HR_RCM_EMPLOYEE b on a.EMP_ID=b.id"
             + " join HR_RCM_INDIVIDUAL c on b.IND_ID=c.id"
             + " where upper(bank_branch_id_hist) like  '%REPUBLIC%BANK%'"
             + " and length(trim( replace(bank_account_no_hist,'-','')))<=12"
             + " and a.earnings_period_id=7383"
             );
     
     while (rs.next()) {
            out.add(rs.getString("new_format")+"\r\n");
        }     
 
     return out;
    }
    
    
    
}
