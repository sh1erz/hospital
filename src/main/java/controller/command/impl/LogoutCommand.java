package controller.command.impl;

import controller.command.Command;
import controller.util.constants.Page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.getSession().invalidate();
        return Page.INDEX.getPage();
    }
}
