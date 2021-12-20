package controller.command.impl;

import controller.command.Command;
import controller.util.constants.ViewJsp;
import domain.Doctor;
import domain.DoctorSpecialisation;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.DoctorDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddDoctorCommand implements Command {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DoctorSpecialisation specialisation = DoctorSpecialisation.valueOf(req.getParameter("specialisation"));
        Doctor doctor = new Doctor(-1, name, specialisation, login, password);
        DoctorDao doctorDao = daoFactory.getDoctorDao();
        if (doctorDao.create(doctor)) return ViewJsp.ADMIN_MAIN.getPage();
        return ViewJsp.INVALID_PERSON.getPage();
    }
}
