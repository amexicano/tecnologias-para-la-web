package ipn.escom.cm152013090237idp1.servlets;

import ipn.escom.cm152013090237idp1.CRUD.QuestionCRUD;
import ipn.escom.cm152013090237idp1.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

public class ViewAllQuestion extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Questions</title>");            
            out.println("</head>");
            out.println("<body style='display:flex;align-items:center;flex-flow:column;'>");
            String path = this.getServletContext().getRealPath("/");
            QuestionCRUD crud;
            try {
                crud = new QuestionCRUD(path);
                out.println("<table border='1' width='90%'");
                out.println("<caption align='center'><h1>Create, Read, Update, Delete</h1></caption>");
                out.println("<tr>");
                out.println("<th>Question</th>");
                out.println("<th>Options</th>");
                out.println("</tr>");
                List <Question> questions = crud.getAll();
                for(int i = 0;i < questions.size();i++){
                    out.println("<tr>");
                    out.println("<td>"+questions.get(i).getQuestion()+"</td>");
                    out.println("<td>");
                    out.println("<a href='Question?id="+(i+1)+"'>View</a></br>");
                    out.println("<a href='../edit/Question?id="+(i+1)+"'>Edit</a></br>");
                    out.println("<a href='../delete/Question?id="+(i+1)+"'>Delete</a></br>");
                    out.println("</td>");
                    out.println("</tr>");
                }
                out.print("</table>");
                out.println("</br></br>");
                out.println("<a href='../add/Question'>Add Question</a>");
            } catch (JDOMException ex) {
                Logger.getLogger(ViewAllQuestion.class.getName()).log(Level.SEVERE, null, ex);
            }
            out.println("</body>");
            out.println("</html>");
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
    }

}


/*
int id ;
try{
    id = Integer.parseInt((String) request.getParameter("id"));
    Cookie[] cookies = request.getCookies();
    for(Cookie cookie : cookies){
        if(cookie.getName().equals("hasDelete"));
        if(cookie.getName().equals("hasSaved"));
    }
} catch(NumberFormatException nfe){} 
 */
