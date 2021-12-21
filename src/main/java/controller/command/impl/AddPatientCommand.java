package controller.command.impl;

import controller.command.Command;
import controller.util.constants.Page;
import domain.Patient;
import model.dao.impl.DaoFactoryImpl;
import model.dao.interfaces.DaoFactory;
import model.dao.interfaces.PatientDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddPatientCommand implements Command {
    DaoFactory daoFactory = DaoFactoryImpl.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String name = req.getParameter("name");
        String strBirth = req.getParameter("birth");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Date birth = null;
        try {
            birth = format.parse(strBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int doctorId = Integer.parseInt(req.getParameter("doctor"));
        if(addPatient(new Patient(-1, name, birth, doctorId))){
            return Page.ADMIN_MAIN.getPage();
        }
        return Page.INVALID_PERSON.getPage();
    }

    private boolean addPatient(Patient patient) {
        PatientDao patientDao = daoFactory.getPatientDao();
        return patientDao.create(patient);
    }

}
