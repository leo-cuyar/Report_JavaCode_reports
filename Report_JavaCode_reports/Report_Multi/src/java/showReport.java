/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import static java.io.FileDescriptor.in;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author ernesto
 */
public class showReport extends HttpServlet {

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
       
            
            String parameters = request.getParameter("p2");
            String staticURL="http://apps3.innosysgy.com:8080/jasperserver/flow.html?j_username=jasperadmin&j_password=J@speradmin@2018_innosys&output=pdf&_flowId=viewReportFlow&reportUnit=P_org_id=17832&P_earning_period_id=12042";
            
            parameters=parameters.replace("-", "&");
            
            String fullURL=staticURL+parameters;           
            
  
           // response.sendRedirect(fullURL);
            
        URL url = new URL(fullURL);
        //URLConnection yc = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openConnection().getInputStream()));
        
     
        response.setContentType("application/pdf");
        response.setHeader("Content-type","application/pdf");
        response.setHeader("Content-disposition","inline; report.pdf" );
        response.setHeader("Content-Length", "5000");
        
        
        String inputLine;
        StringBuilder a = new StringBuilder();
        while ((inputLine = in.readLine()) != null)
            //a.append(inputLine);
            //response.getWriter().println(inputLine);
            response.getOutputStream().println(inputLine);
            response.getOutputStream().flush();
        in.close();
      
        //response.getWriter().println(a);
       

            
        
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
