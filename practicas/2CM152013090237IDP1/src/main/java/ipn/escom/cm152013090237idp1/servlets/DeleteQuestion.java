package ipn.escom.cm152013090237idp1.servlets;

import ipn.escom.cm152013090237idp1.CRUD.QuestionCRUD;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

public class DeleteQuestion extends HttpServlet {
    
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            Cookie hasDelete;
            try{
                int id = Integer.parseInt((String) request.getParameter("id"));
                String path  = this.getServletContext().getRealPath("/");
                QuestionCRUD crud  = new QuestionCRUD(path);
                hasDelete  = new Cookie("hasDelete",Boolean.toString(crud.remove(id)));
                
            }catch (JDOMException | NumberFormatException e){
                hasDelete  = new Cookie("hasDelete",Boolean.toString(false));
            }
            response.addCookie(hasDelete);
            response.sendRedirect("../view/all");
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

}
