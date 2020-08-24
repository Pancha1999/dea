/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import databases.contactdbcon;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kavin
 */
public class contact extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet contact</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet contact at " + request.getContextPath() + "</h1>");
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
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String fname = request.getParameter("fname");
            String email = request.getParameter("email");
            String Subject = request.getParameter("subject");
            String Message = request.getParameter("message");
            
            

                contactdbcon obj_DB_Connection = new contactdbcon();
                Connection connection = obj_DB_Connection.get_connection();
                PreparedStatement ps = null;

                String sql = "insert into information( emailadd,subjectm,feedback,firstname) values(?,?,?,?)";
                Class.forName("com.mysql.jdbc.Driver");
                
                ps = connection.prepareStatement(sql);
                
                ps.setString(1, email);
                ps.setString(2, Subject);
                ps.setString(3, Message);
                ps.setString(4, fname);
               
                ps.executeUpdate();
                out.println("<script type=\"text/javascript\">");
                out.println("alert('Your  message sent Successfully !!');");
                out.println("location='contact.html';");
                out.println("</script>");
            
            

        } catch (SQLException ex) {
            out.println(ex);
        } catch (ClassNotFoundException ex) {
            out.println(ex);
        }
	}
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
