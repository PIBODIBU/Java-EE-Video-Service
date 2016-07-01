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

    private HttpSession createNewSession(HttpServletRequest request) {
        HttpSession session = request.getSession();

        session.setAttribute("userId", 1000 + new Random().nextInt(1000000 - 1000) + 1);
        session.setAttribute("visitCount", 0);

        return session;
    }

    private HttpSession checkSession(HttpServletRequest request, HttpSession session) {
        session.setAttribute("lastAccess", new Date(session.getLastAccessedTime()));

        if (session.isNew()) {
            System.out.println("doGet() -> session is new");

            session = createNewSession(request);
        } else {
            System.out.println("doGet() -> Session is not new");

            session.setAttribute("visitCount", (Integer) session.getAttribute("visitCount") + 1);
        }

        return session;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action") != null) {
            if (request.getParameter("action").equals("invalidate_session")) {
                request.getSession(false).invalidate();
            }
        }

        request.setAttribute("session", checkSession(request, request.getSession(true)));

        getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //request.setAttribute("session", checkSession(request, request.getSession(true)));

        getServletContext().getRequestDispatcher(VIEW_TEMPLATE_PATH).forward(request, response);
    }
}
