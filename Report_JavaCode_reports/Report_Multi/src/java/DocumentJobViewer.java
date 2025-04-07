/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import DB_Connection.OracleCloudConnection;
import DB_Connection.OracleConnection;
import DB_Connection.OracleGPLTrainingConnection;
import DB_Connection.OracleOnPreConnectionTestGPL;
import Shared.BlobObject;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.converter.pdf.PdfConverter;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
 import java.io.FileInputStream;
    import java.io.*;
import java.sql.Blob;
    import org.apache.poi.hssf.usermodel.HSSFWorkbook;
    import org.apache.poi.hssf.usermodel.HSSFSheet;
    import org.apache.poi.ss.usermodel.*;
    import java.util.Iterator;
import javax.servlet.ServletOutputStream;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hwpf.HWPFDocument;


/**
 *
 * @author ernesto
 */

public class DocumentJobViewer extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        
           ServletOutputStream  outs =  response.getOutputStream ();             
                    
           BufferedInputStream  bis = null; 
           BufferedOutputStream bos = null; 
           
           String blobId = request.getParameter("did");
           String serv = request.getParameter("serv");
           
           //OracleConnection cxt=new OracleConnection();
           
           OracleCloudConnection cxt=new OracleCloudConnection(serv);
           BlobObject blobDocument=cxt.getJobBlob(Integer.valueOf(blobId));
           
           
           
           String documentType=getDocumentType(blobDocument.getFileName());
           System.out.println(documentType);
           byte[] imgData = null;
           switch (documentType) {
           
               case ".jpg":
                   response.setContentType( "image/jpg" ); 
                   response.setHeader("Content-disposition","inline; filename=" +"preview.jpg" );                  
                   imgData = blobDocument.getObjectData().getBytes(1,(int)blobDocument.getObjectData().length());
                   outs.write(imgData);
                   outs.flush();
                   outs.close();                   
                   
               break;
                case ".png":
                   response.setContentType( "image/jpg" ); 
                   response.setHeader("Content-disposition","inline; filename=" +"preview.png" );                    
                   imgData = blobDocument.getObjectData().getBytes(1,(int)blobDocument.getObjectData().length());
                   outs.write(imgData);
                   outs.flush();
                   outs.close();                   
                   
               break;
               
              case ".bmp":
                   response.setContentType( "image/jpg" ); 
                   response.setHeader("Content-disposition","inline; filename=" +"preview.bmp" );               
                   imgData = blobDocument.getObjectData().getBytes(1,(int)blobDocument.getObjectData().length());
                   outs.write(imgData);
                   outs.flush();
                   outs.close();                  
               break;
               
              case ".gif":
                   response.setContentType( "image/jpg" ); 
                   response.setHeader("Content-disposition","inline; filename=" +"preview.gif" );               
                   imgData = blobDocument.getObjectData().getBytes(1,(int)blobDocument.getObjectData().length());
                   outs.write(imgData);
                   outs.flush();
                   outs.close();                  
               break;
           
              case ".jpeg":
                   response.setContentType( "image/jpg" ); 
                   response.setHeader("Content-disposition","inline; filename=" +"preview.jpeg" );               
                   imgData = blobDocument.getObjectData().getBytes(1,(int)blobDocument.getObjectData().length());
                   outs.write(imgData);
                   outs.flush();
                   outs.close();                  
               break;
               
               case ".txt":
                   response.setContentType("text/html;charset=UTF-8"); 
                   response.setHeader("Content-disposition","inline; filename=" +"preview.txt" );               
                   imgData = blobDocument.getObjectData().getBytes(1,(int)blobDocument.getObjectData().length());
                   outs.write(imgData);
                   outs.flush();
                   outs.close();                   

               break;
               
               case ".pdf":
                   
                    response.setContentType( "application/pdf" ); 
                    response.setHeader("Content-disposition","inline; filename=" +"Report.pdf" );     
                    try {

                                InputStream isr=blobDocument.getObjectData().getBinaryStream();
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

               break;
               
              case ".docx":
                    response.setContentType( "application/pdf" ); 
                    response.setHeader("Content-disposition","inline; filename=" +"Report.pdf" );  
                    try {
                    InputStream doc = blobDocument.getObjectData().getBinaryStream();
                    XWPFDocument document = new XWPFDocument(doc);
                    PdfOptions options = PdfOptions.create();
                    //OutputStream out1 = new FileOutputStream(new File("D:\\test1.pdf"));
                    PdfConverter.getInstance().convert(document, outs, options);
                    System.out.println("Done");
                } catch (FileNotFoundException ex) {
                    System.out.println(ex.getMessage());
                } catch (IOException ex) {

                    //System.out.println(ex.getMessage());
                }

               break;  
               
              case ".xls":
                    response.setContentType("text/html;charset=UTF-8");
                       
                               /* TODO output your page here. You may use following sample code. */
                               outs.println("<!DOCTYPE html>");
                               outs.println("<html>");
                               outs.println("<head>");
                               outs.println("<title>Servlet Test</title>");            
                               outs.println("</head>");
                               outs.println("<body>");
                               outs.println("<table cellspacing=\"0\" cellpadding=\"0\">");



                         InputStream doc = blobDocument.getObjectData().getBinaryStream();
                         //FileInputStream input_document = new FileInputStream(new File("D:\\Libro1.xls"));
                                       // Read workbook into HSSFWorkbook
                                       HSSFWorkbook my_xls_workbook = new HSSFWorkbook(doc); 
                                       // Read worksheet into HSSFSheet
                                       HSSFSheet my_worksheet = my_xls_workbook.getSheetAt(0); 
                                       // To iterate over the rows                   
                                       Iterator<Row> rowIterator1 = my_worksheet.iterator();

                                       Integer[] cellWidth=new Integer[10000];
                                       int maxLenght=0;
                                       int i=0;
                                       while(rowIterator1.hasNext()) {                                                  
                                        Row row = rowIterator1.next();
                                       int cantCell=0;         
                                       Iterator<Cell> cellIterator1 = row.cellIterator();

                                                       while(cellIterator1.hasNext()) {
                                                       Cell cell = cellIterator1.next();     
                                                       if (cell.getCellType() != Cell.CELL_TYPE_BLANK) {cantCell++;
                                                                                                       }                                    
                                                       }
                                                       cellWidth[i]=cantCell;
                                                       i++;
                                                       if (cantCell>maxLenght) {maxLenght=cantCell;}

                                       }

                                     Iterator<Row> rowIterator = my_worksheet.iterator();
                                     int tdWidthMax=maxLenght*70;  

                                       int i1=0;
                                       while(rowIterator.hasNext()) {                                                  
                                               Row row = rowIterator.next(); 



                                               short num=row.getLastCellNum();
                                               Short nObj=new Short(num);
                                               int numCell=nObj.intValue();


                                              outs.println("<TR>");

                                               outs.println("<table cellspacing=\"0\" cellpadding=\"0\" width=\""+String.valueOf(tdWidthMax)+"\">");
                                               outs.println("<TR>");

                                               Iterator<Cell> cellIterator = row.cellIterator();

                                                       while(cellIterator.hasNext()) {

                                                               Cell cell = cellIterator.next(); //Fetch CELL

                                                               CellStyle cellStyle = cell.getCellStyle();
                                                               /*if (cellStyle.getIndex() == 0) cellStyle = cell.getRow().getRowStyle();
                                                               if (cellStyle == null) cellStyle = cell.getSheet().getColumnStyle(cell.getColumnIndex());
                                                               if (cellStyle == null) cellStyle = cell.getCellStyle();*/


                                                               Color c=cellStyle.getFillBackgroundColorColor();
                                                               Color c1=cellStyle.getFillForegroundColorColor();
                                                               HSSFColor xSSFColor = (HSSFColor) c1;


                                                               short[] hexColor=xSSFColor.getTriplet();


                                                               Short rObj=new Short(hexColor[0]);
                                                               int r=rObj.intValue();


                                                               Short gObj=new Short(hexColor[1]);
                                                               int g=gObj.intValue();


                                                               Short bObj=new Short(hexColor[2]);
                                                               int b=bObj.intValue();

                                                               java.awt.Color col=new java.awt.Color(hexColor[0],hexColor[1],hexColor[2]);

                                                               int[] rgb=getColorPattern(cellStyle.getFillForegroundColor(), my_xls_workbook);

                                                               switch(cell.getCellType()) { //Identify CELL type

                                                               case Cell.CELL_TYPE_STRING:

                                                                   outs.println("<td style=\"background-color:rgba("+String.valueOf(rgb[0])+","+String.valueOf(rgb[1])+","+String.valueOf(rgb[2])+","+"1);width:"+String.valueOf(tdWidthMax/cellWidth[i1])+"px;\">");
                                                                   outs.println(cell.getStringCellValue());
                                                                       break;
                                                               case Cell.CELL_TYPE_NUMERIC:                                                 

                                                                   DataFormatter df = new DataFormatter();                               
                                                                   String asItLooksInExcel = df.formatCellValue(cell);                                                
                                                                   outs.println("<td style=\"background-color:rgba("+String.valueOf(rgb[0])+","+String.valueOf(rgb[1])+","+String.valueOf(rgb[2])+","+"1);width:"+String.valueOf(tdWidthMax/cellWidth[i1])+"px;\">");
                                                                   outs.println(asItLooksInExcel);
                                                                   break;

                                                               case Cell.CELL_TYPE_BLANK: 
                                                                   outs.println("");
                                                                   break;

                                                               }

                                                              outs.println("</td>");
                                                       }


                                      i1++;
                                      outs.println("</TR>");
                                      outs.println("</table>");
                                      outs.println("</TR>");
                                       }
                                       //Finally add the table to PDF document

                                   //    iText_xls_2_pdf.close();                
                                       //we created our pdf file..
                                       doc.close(); //close xls      

                        outs.println("</table>");
                        outs.println("</body>");
                               outs.println("</html>");



                           

               break;  
               
               
               
               
               
               
               
               
               
                       

               
               
               
               
           
           }
           
           
           
          /* 
           
           try {

            InputStream isr=blobDocument.getObjectData().getBinaryStream();
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
        }*/
           
           
           
           
           
           
           
           

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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocumentViewer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DocumentViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DocumentViewer.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DocumentViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public String getDocumentType(String name){
     String out="unknow";
       int lenName=name.length();
       if (lenName>=5){
          int pos=name.lastIndexOf(".");
          if (pos > 0){
             out=name.substring(pos);
          }      
       }     
     return out;    
    }
    
        private static int[] getColorPattern(short colorIdx,HSSFWorkbook workbook ){        
    short[] triplet = null;    
    HSSFPalette palette = workbook.getCustomPalette();
    HSSFColor color = palette.getColor(colorIdx);
    triplet = color.getTriplet(); 
    
    
    
                                            Short rObj=new Short(triplet[0]);
                                            int r=rObj.intValue();
                                           
                                            
                                            Short gObj=new Short(triplet[1]);
                                            int g=gObj.intValue();
                                           
                                            
                                            Short bObj=new Short(triplet[2]);
                                            int b=bObj.intValue();
    
    
    if (((r==0) && (g==0)) && (b==0)){
    r=255;
    g=255;
    b=255;
    }
    int[] out=new int[]{r,g,b};
    
    System.out.println("color : " + String.valueOf(r) +"," + String.valueOf(g) + "," +    String.valueOf(b));
    return out;
}

}
