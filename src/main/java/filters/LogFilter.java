package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"}, initParams = {@WebInitParam(name = "db_pass", value = "suPPer_secret_pAss")})
public class LogFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        String dbPass = filterConfig.getInitParameter("db_pass");

        System.out.println("Web init param: " + dbPass);
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("IP address: " + request.getRemoteAddr());

        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
