package ipn.escom.cm152013090237idp1.servlets;

import ipn.escom.cm152013090237idp1.CRUD.QuestionCRUD;
import ipn.escom.cm152013090237idp1.model.Answer;
import ipn.escom.cm152013090237idp1.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jdom2.JDOMException;

public class AddQuestion extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher requestDispatch;
        requestDispatch = this.getServletContext()
                .getRequestDispatcher("/question_form.html");
        requestDispatch.include(request, response);
        response.setContentType("text/html;charset=UTF-8");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
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
            String path = this.getServletContext().getRealPath("/");
            QuestionCRUD crud = new QuestionCRUD(path);
            response.addCookie(
                new Cookie(
                    "hasAdded",
                    Boolean.toString(crud.add(q))
                )
            );
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
    }

}
