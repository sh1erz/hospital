package controller.command.impl;

import controller.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class AuthorizeCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Map<String,String[]> params = req.getParameterMap();
        String p = params.get("password")[0];
        String login = params.get("login")[0];

        if(p != null && login != null){
            String page = login.equals("admin") ? "/admin/main" : "doctor/main";
            resp.sendRedirect(page);
        }
        else {
            resp.sendRedirect("/index.jsp");
        }
        return null;
    }
}
