package ipn.escom.cm152013090237idp1.servlets;

import ipn.escom.cm152013090237idp1.CRUD.QuestionCRUD;
import java.io.IOException;
import javax.servlet.ServletException;
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
        /* TODO output your page here. You may use following sample code. */
        try{
            int id = Integer.parseInt((String) request.getParameter("id"));
            String path  = this.getServletContext().getRealPath("/");
            QuestionCRUD crud  = new QuestionCRUD(path);
            crud.remove(id);
        }catch (JDOMException | NumberFormatException e){}
        response.sendRedirect("../view/all");
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
