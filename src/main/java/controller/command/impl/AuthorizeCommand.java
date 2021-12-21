package controller.command.impl;

import controller.command.Command;
import controller.util.constants.ViewJsp;
import domain.Doctor;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.DoctorDao;
import model.dao.interfaces.PatientDao;
import model.exceptions.InvalidUserTypeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.util.constants.Attribute.*;

public class AuthorizeCommand implements Command {
    private final DaoFactory daoFactory = DaoFactoryImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String p = req.getParameter("password");
        String login = req.getParameter("login");
        String userType = req.getParameter("accountType");

        switch (userType) {
            case "admin": {
                if (isAdminValid(login, p)) {
                    req.getSession().setAttribute(USER_ADMIN.getAttribute(), "admin");
                    //setAdminAttributes(req);
                    return ViewJsp.ADMIN_MAIN.getPage();
                }
                return ViewJsp.INVALID_LOGIN.getPage();
            }
            case "doctor": {
                if (isDoctorValid(login, p)) {
                    Doctor doctor = daoFactory.getDoctorDao().get(login).orElseThrow();
                    req.getSession().setAttribute(USER_DOCTOR.getAttribute(), doctor);
                    return ViewJsp.DOCTOR_MAIN.getPage();
                }
                return ViewJsp.INVALID_LOGIN.getPage();
            }
            default:
                throw new InvalidUserTypeException();
        }

    }

    private boolean isAdminValid(String login, String password) {
        return login.equals("admin") && password.equals("admin");
    }

    private boolean isDoctorValid(String login, String password) {
        DoctorDao doctorDao = daoFactory.getDoctorDao();
        return password.equals(doctorDao.getPassword(login));
    }
}
