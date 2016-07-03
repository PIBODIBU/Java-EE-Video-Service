package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Random;

@WebServlet(urlPatterns = {"/home"}, initParams = {@WebInitParam(name = "db_pass", value = "suPPer_secret_pAss")})
public class HomeServlet extends HttpServlet {

    public static final String VIEW_TEMPLATE_PATH = "/jsp/home.jsp";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher(VIEW_TEMPLATE_PATH).forward(request, response);
    }
}
