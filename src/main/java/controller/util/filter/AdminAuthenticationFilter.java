package controller.util.filter;


import controller.util.constants.Page;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.util.constants.Attribute.USER_ADMIN;

@WebFilter("/admin/*")
public class AdminAuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = (session != null && session.getAttribute(USER_ADMIN.getAttribute()) != null);
        if (isLoggedIn) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            RequestDispatcher dispatcher = httpRequest.getRequestDispatcher(Page.INDEX.getPage());
            dispatcher.forward(servletRequest, servletResponse);
        }

    }
}
