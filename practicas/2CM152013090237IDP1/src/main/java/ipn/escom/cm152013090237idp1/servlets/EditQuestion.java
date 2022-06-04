package ipn.escom.cm152013090237idp1.servlets;

import ipn.escom.cm152013090237idp1.CRUD.QuestionCRUD;
import ipn.escom.cm152013090237idp1.model.Answer;
import ipn.escom.cm152013090237idp1.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

public class EditQuestion extends HttpServlet {
    
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
            int id = Integer.parseInt((String) request.getParameter("id"));
            /* TODO output your page here. You may use following sample code. */
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditQuestion</title>");            
            out.println("</head>");
            out.println("<body>");
            try{
                String path = this.getServletContext().getRealPath("/");
                QuestionCRUD crud = new QuestionCRUD(path);
                
                Optional<Question> q  = crud.get(id);
                out.println("<h1>Edit Question</h1>");
                out.println("<form id='form' action='../edit/Question' method='post'>  ");
                out.println("Question:");
                out.println("<br/>");
                out.println("<input type='text' name='question' id='question'value='"+q.get().getQuestion()+"' required />");
                out.println("<br/>");
                List<Answer> list = q.get().getAnswerOptions();
                for(int i=0;i < list.size();i++){
                    out.println("Option "+(i+1)+":");
                    out.println("<br/>");
                    out.println("<input type='text' name='option"+i+"' id='option"+i+"' value='"+list.get(i).getAnswer()+"' required />");
                    out.println("<br/>");
                }
                out.println("<br/>");
                out.println("Right Answer:");
                out.println("<select name='selection' id='selection' required>");
                out.println("<option value='' selected>Select an option</option>");
                for(int i=0;i < list.size();i++){
                    out.println("<option value='option"+i+"' "+(list.get(i).getIsRightAnswer() ? "selected" : "")+">Option "+(i+1)+"</option>");
                }
                out.println("</select>");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<input type='hidden' name='id' id='id' value='"+id+"' required/>");
                out.println("<input type='submit' value='Edit Question'/>  ");
                out.println("</form>");
                out.println("<a href='../view/all'>Back</a>");
                out.println("<br/>");
            } catch (JDOMException e){
                
            }
            out.println("</body>");
            out.println("</html>");
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
            /* TODO output your page here. You may use following sample code. */
            String rightAnswer = (String) request.getParameter("selection");
            Question q = new Question((String) request.getParameter("question"));

            for(String value : Arrays.asList("option0","option1","option2","option3")){

                q.addAnswerOption(new Answer(
                        (String) request.getParameter(value),
                        rightAnswer.equals(value)
                ));
            }

            int id = Integer.parseInt((String) request.getParameter("id"));
            String path = this.getServletContext().getRealPath("/");
            QuestionCRUD crud = new QuestionCRUD(path);
            crud.update(id, q);
        } catch (JDOMException ex) {}
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
