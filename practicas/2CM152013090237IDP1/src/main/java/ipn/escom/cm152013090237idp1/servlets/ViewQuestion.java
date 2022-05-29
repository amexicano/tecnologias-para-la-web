package ipn.escom.cm152013090237idp1.servlets;

import ipn.escom.cm152013090237idp1.CRUD.QuestionCRUD;
import ipn.escom.cm152013090237idp1.model.Answer;
import ipn.escom.cm152013090237idp1.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

public class ViewQuestion extends HttpServlet {

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
            int id;
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Question</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
                id = Integer.parseInt((String) request.getParameter("id"));
                String path  = this.getServletContext().getRealPath("/");
                QuestionCRUD crud = new QuestionCRUD(path);
                Optional<Question> question = crud.get(id);
                if(!question.isEmpty()){
                    Question q = question.get();
                    out.println("<h1>Question</h1>");
                    out.println("<br/>Question: "+ q.getQuestion());
                    for(Answer answer : q.getAnswerOptions()){
                        out.println("<br/>Answer: "+ answer.getAnswer());
                        out.println(" Rigth Answer:");
                        out.println(answer.getIsRightAnswer() ? "YES" : "NO");
                    }
                    out.print("<br/>");
                    out.println("<a href='../edit/Question?id="+id+"'>Edit Question</a>");
                    out.println("<a href='all'>Back</a>");
                }
            } catch (NumberFormatException | JDOMException e){
                out.println("<meta http-equiv='refresh' content='0;url=/all'>");
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
