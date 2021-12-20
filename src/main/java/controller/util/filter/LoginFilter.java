package controller.util.filter;

import controller.util.constants.ViewJsp;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/index.jsp")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;

        HttpSession session = httpRequest.getSession(false);
        RequestDispatcher dispatcher;
        if (session == null) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (session.getAttribute("doctorUser") != null){
            dispatcher = servletRequest.getRequestDispatcher(ViewJsp.DOCTOR_MAIN.getPage());
            dispatcher.forward(servletRequest, servletResponse);
        }else if (session.getAttribute("doctorUser") != null){
            dispatcher = servletRequest.getRequestDispatcher(ViewJsp.DOCTOR_MAIN.getPage());

        }else {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        dispatcher.forward(servletRequest, servletResponse);
    }


}
