package controller;

import controller.command.Command;
import controller.command.CommandList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class MainController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        Command command = CommandList.valueOf(commandName).getCommand();
        String goTo = command.execute(req, resp);
        req.getRequestDispatcher(goTo).forward(req, resp);
    }
}
