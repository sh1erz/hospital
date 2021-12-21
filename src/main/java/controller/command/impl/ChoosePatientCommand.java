package controller.command.impl;

import controller.command.Command;
import controller.util.constants.Page;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static controller.util.constants.Attribute.PATIENT;

public class ChoosePatientCommand implements Command {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int patientId = Integer.parseInt(req.getParameter("patient"));
        Optional<Patient> patient = daoFactory.getPatientDao().get(patientId);
        if (patient.isEmpty()) {
            return Page.INVALID_PERSON.getPage();
        }
        req.getSession().setAttribute(PATIENT.getAttribute(), patient.get());
        return Page.PATIENT.getPage();
    }
}
