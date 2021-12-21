package controller.command.impl;

import controller.command.Command;
import controller.util.constants.Attribute;
import controller.util.constants.JspPath;
import controller.util.constants.Page;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.PatientDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetDiagnosisCommand implements Command {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Patient patient = (Patient) req.getSession().getAttribute(Attribute.PATIENT.getAttribute());
        String diagnosis = req.getParameter("diagnosis");
        PatientDao patientDao = daoFactory.getPatientDao();
        boolean succeeded = patientDao.setDiagnosis(patient.getId(),diagnosis);
        if (succeeded) return Page.PATIENT.getPage();
        req.setAttribute(Attribute.ERROR.getAttribute(),
                "Error updating diagnosis for patient " + patient.getName());
        return Page.ERROR.getPage();

    }
}
