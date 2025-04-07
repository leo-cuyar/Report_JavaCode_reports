/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Base64;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ernesto
 */
public class showPDFE extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
            //response.setContentType("text/html;charset=UTF-8");

        
        
           ServletOutputStream  outs =  response.getOutputStream ();             
           response.setContentType( "application/pdf" ); 
           response.setHeader("Content-disposition","inline; filename=" +"Payslip.pdf" );           
           BufferedInputStream  bis = null; 
           BufferedOutputStream bos = null; 
           
           try {

            
            String enviroment=  request.getParameter("p1");   
            String parameters = request.getParameter("p2");
            String username="";
            String password="";
            
            if (enviroment.equals("WT")) {username="WT";
                                          password="WT@123";}

            if (enviroment.equals("DEEDS")) {username="DEEDS";
                                          password="DEEDS@123";}            
            
             if (enviroment.equals("BOS")) {username="BOS";
                                          password="BOS@123";} 
             
             if (enviroment.equals("PINN")) {username="PINN";
                                          password="PINN@123";}
            
             if (enviroment.equals("DEMO")) {username="DEMO";
                                          password="DEMO@123";}
                     
             if (enviroment.equals("LC")) {username="LC";
                                          password="LC@123";}
       
             if (enviroment.equals("FMS")) {username="FMS";
                                          password="FMS@123";}
             
             if (enviroment.equals("PAC")) {username="PAC";
                                          password="PAC@123";}
             
             if (enviroment.equals("SUREGIG")) {username="SUREGIG";
                                          password="SUREGIG@123";}
             
             if (enviroment.equals("GSRJ")) {username="GSRJ";
                                          password="GSRJ@123";}
             
             if (enviroment.equals("FTBL")) {username="FTBL";
                                          password="FTBL@123";}

             if (enviroment.equals("SG")) {username="SG";
                                          password="SG@123";}
                          
             if (enviroment.equals("SGD")) {username="SGD";
                                          password="SGD@123";}
             if (enviroment.equals("SF")) {username="SF";
                                          password="SF@123";}
             
             if (enviroment.equals("SFD")) {username="SFD";
                                          password="SFD@123";}
             
             if (enviroment.equals("SA")) {username="SA";
                                          password="SA@123";}
             
             if (enviroment.equals("DMB")) {username="DMB";
                                          password="DMB@123";}

             if (enviroment.equals("CSR")) {username="CSR";
                                          password="CSR@123";}
             
             if (enviroment.equals("CMX")) {username="CMX";
                                          password="CMX@123";}
             
            String staticURL="http://158.101.107.28:8080/jasperserver/flow.html?j_username="+username+"&j_password="+password+"&output=pdf&_flowId=viewReportFlow&reportUnit=";
              
                         
           
            //String staticURL="http://162.251.61.138:8080/jasperserver/flow.html?j_username=jasperadmin&j_password=Inno$y$jasperadmin&output=pdf&_flowId=viewReportFlow&reportUnit=";
            
            //String staticURL="http://192.168.56.2:8082/jasperserver/flow.html?j_username=jasperadmin&j_password=Inno$y$jasperadmin&output=pdf&_flowId=viewReportFlow&reportUnit=";
            
            
           // String staticURL="http://apps4.innosysgy.com:8082/jasperserver/flow.html?j_username=jasperadmin&j_password=Inno$y$jasperadmin&output=pdf&_flowId=viewReportFlow&reportUnit=";
            
          //  String staticURL="http://172.16.3.227:8082/jasperserver/flow.html?j_username=jasperadmin&j_password=Inno$y$jasperadmin&output=pdf&_flowId=viewReportFlow&reportUnit=";
              
            
         //     String staticURL="http://10.0.2.137:8082/jasperserver/flow.html?j_username=jasperadmin&j_password=jasperadmin&output=pdf&_flowId=viewReportFlow&reportUnit=";
 
         
         //String staticURL="http://192.168.0.153:8081/jasperserver/flow.html?j_username=jasperadmin&j_password=jasperadmin&output=pdf&_flowId=viewReportFlow&reportUnit=";
 
         
            
            String originalUrl1=parameters.substring(5);
            
            byte[] decodedBytes = Base64.getUrlDecoder().decode(originalUrl1);
            parameters= new String(decodedBytes);
            
         
            parameters=parameters.replace("-", "&");
            
            parameters=parameters.replace("**", "-");
            
                 
            
            
            String fullURL=staticURL+parameters;  
            

            
           //String fullURL="http://apps3.innosysgy.com:8080/jasperserver/flow.html?j_username=jasperadmin&j_password=J@speradmin@2018_innosys&output=pdf&_flowId=viewReportFlow&reportUnit=/reports/TEST/NIS&P_org_id=17832&P_earning_period_id=12042";
           URL url = new URL(fullURL);
           InputStream isr=url.openConnection().getInputStream();
            bis = new BufferedInputStream(isr);
            bos = new BufferedOutputStream(outs);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while(-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } 
        catch(Exception e)
        {
            System.out.println("Exception ----- Message ---"+e);
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
        

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
