/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ernesto
 */
public class report extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String parameters = request.getParameter("p1");
            //String staticURL="http://apps3.innosysgy.com:8080/jasperserver/flow.html?j_username=jasperadmin&j_password=J@speradmin@2018_innosys&&output=pdf&_flowId=viewReportFlow&reportUnit=";
            
           // parameters=parameters.replace("-", "&");
            
          //  String fullURL=staticURL+parameters;
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");           
            out.println("<title>Report</title>");            
            out.println("</head>");
            out.println("<body style=\"margin:0;\">");
            out.println("<div>");
           // out.println("<iframe src=\""+fullURL+"\" width=100% height=\"500px\"></iframe>");
            out.println("<iframe src=\"http://apps3.innosysgy.com:8080/Report/showReport?p2="+parameters+"\" width=100% height=\"500px\"></iframe>");
            
               /* out.println("<object data=\"your_url_to_pdf\" type=\"application/pdf\">");
                out.println("<embed src=\"http://apps3.innosysgy.com:8080/Report/showReport?p2="+parameters+"\" type=\"application/pdf\" />");
                out.println("</object>");*/
            
            
            
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
