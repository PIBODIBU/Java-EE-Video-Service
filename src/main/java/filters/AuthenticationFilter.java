package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Date;

@WebFilter(urlPatterns = {"/*"})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("Date: " + new Date().toString());
        chain.doFilter(request, response);
    }

    public void destroy() {

    }
}
